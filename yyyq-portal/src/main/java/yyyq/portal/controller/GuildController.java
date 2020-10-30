package yyyq.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import yyyq.common.annotation.Auth;

/**
 * GuildController
 *
 * @author liyunzhi
 * @date 2018/10/26 16:35
 */
@Controller
@RequestMapping("/guild")
public class GuildController extends BaseController {
    @RequestMapping("/index")
    @Auth
    public String index(){
        return "guild/index";
    }


    @RequestMapping("/list")
    @Auth
    public String list(){
        return "guild/list";
    }
}
