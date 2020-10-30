package yyyq.common.controller;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonParseException;
import org.apache.catalina.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import yyyq.common.annotation.Auth;
import yyyq.common.model.ActionResultModel;
import yyyq.common.model.auth.UserModel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class BaseController {

    private Logger logger = LoggerFactory.getLogger(BaseController.class);
    protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected ActionResultModel actionResultModel=new ActionResultModel();
    protected UserModel userModel;

    /**
     * 初始化Req&Res
     * @param request
     * @param response
     */
    @ModelAttribute
    public void initReqAndResp(HttpServletRequest request, HttpServletResponse response){
        this.request = request;
        userModel = JSON.parseObject(request.getHeader("userModel"),UserModel.class);
        this.response = response;
    }

    @ExceptionHandler
    @ResponseBody
    protected ActionResultModel exceptionHandler(Exception exception) {
        if (exception instanceof JsonParseException) {
            logger.error("异常：", exception);
            return actionResultModel.setHasErrorMessage("json参数异常");
        } else if (exception instanceof MethodArgumentTypeMismatchException) {
            logger.error("异常：", exception);
            return actionResultModel.setHasErrorMessage("不合法的参数请求");
        } else if (exception instanceof HttpMessageNotReadableException) {
            logger.error("异常：", exception);
            return actionResultModel.setHasErrorMessage("参数不正确");
        } else {
            logger.error("异常：", exception);
        }
        return actionResultModel.setHasErrorMessage(exception.getMessage() + " : " + exception.toString());
    }

    /**
     * ActionResultModel
     *
     * @param model
     * @param <T>
     * @return
     */
    protected <T> ActionResultModel<T> actionSuccess(T model) {
        return new ActionResultModel<>(false, "",model);
    }
}
