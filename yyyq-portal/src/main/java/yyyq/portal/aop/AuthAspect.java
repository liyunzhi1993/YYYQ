package yyyq.portal.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import yyyq.common.annotation.Auth;
import yyyq.common.constants.SecurityConstants;
import yyyq.common.model.ActionResultModel;
import yyyq.common.model.auth.GameNavModel;
import yyyq.common.model.auth.UserModel;
import yyyq.portal.feignclient.AuthClient;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 *
 *
 * @author liyunzhi
 * @date 2018/7/13 14:31
 */
@Aspect
@Component
@Order(0)
public class AuthAspect {

    @Autowired
    private AuthClient authClient;

    @Around(value = "@annotation(auth)")
    public Object Auth(ProceedingJoinPoint joinPoint,Auth auth) throws Throwable {
        {
            String token=null;

            RequestAttributes ra = RequestContextHolder.getRequestAttributes();
            ServletRequestAttributes sra = (ServletRequestAttributes) ra;
            HttpServletRequest request = sra.getRequest();

            Cookie[] cookies = request.getCookies();
            if (cookies == null) {
                return SecurityConstants.LOGIN_URL;
            }

            for (Cookie cookie : cookies) {
                if (SecurityConstants.HEADER_STRING.equals(cookie.getName())) {
                    token = cookie.getValue();
                    break;
                }
            }

            ActionResultModel<UserModel> actionResultModel = null;
            try {
                actionResultModel = authClient.current(token);
                List<GameNavModel> gameNavModelList=actionResultModel.data.gameNavModelList;
                for (GameNavModel gameNavModel : gameNavModelList) {
                    if (request.getRequestURI().equals(gameNavModel.gameNavUrl)) {
                        gameNavModel.isActive=true;
                        break;
                    }
                }
            } catch (Exception ex) {
                return SecurityConstants.LOGIN_URL;
            }
            if (actionResultModel.getHasError()) {
                return SecurityConstants.LOGIN_URL;
            }
            Object[] args = joinPoint.getArgs();
            request.setAttribute("user",actionResultModel.data);
            Object returnValue=joinPoint.proceed(args);
            return returnValue;
        }
    }
}
