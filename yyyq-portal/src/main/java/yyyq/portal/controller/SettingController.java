package yyyq.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import yyyq.common.annotation.Auth;

/**
 * SettingController
 *
 * @author liyunzhi
 * @date 2018/7/31 10:51
 */
@Controller
@RequestMapping("/setting")
public class SettingController extends BaseController {
    @RequestMapping("/profile")
    @Auth
    public String profile() {
        return "setting/profile";
    }

    @RequestMapping("/game")
    @Auth
    public String game() {
        return "setting/game";
    }

    @RequestMapping("/ffxiv")
    @Auth
    public String ffxiv() {
        return "setting/ffxiv";
    }

    @RequestMapping("/bindGuild")
    @Auth
    public String bindGuild() {
        return "setting/bindGuild";
    }

    @RequestMapping("/wow")
    @Auth
    public String wow() {
        return "setting/wow";
    }
}
