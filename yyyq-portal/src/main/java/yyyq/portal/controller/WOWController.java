package yyyq.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import yyyq.common.annotation.Auth;

@Controller
@RequestMapping("/wow")
public class WOWController extends BaseController{

    @RequestMapping("/job")
    @Auth
    public String join() {
        return "wow/job";
    }

    @RequestMapping("/photo")
    @Auth
    public String photo() {
        return "wow/photo";
    }
}
