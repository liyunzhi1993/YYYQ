package yyyq.gateway.feignclient;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import yyyq.common.constants.SecurityConstants;
import yyyq.common.model.ActionResultModel;
import yyyq.common.model.auth.UserModel;

import java.util.List;


/**
 * AuthClient
 *
 * @author liyunzhi
 * @date 2018/7/13 17:09
 */
@FeignClient(value = "yyyq-auth")
public interface AuthClient {
    
    @PostMapping(value = "/user/current")
    ActionResultModel<UserModel> current(@RequestHeader(SecurityConstants.HEADER_STRING) String token);
}
