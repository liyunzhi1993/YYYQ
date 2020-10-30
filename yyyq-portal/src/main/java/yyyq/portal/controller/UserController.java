package yyyq.portal.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import yyyq.common.annotation.Auth;
import yyyq.common.constants.SecurityConstants;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController{

    @RequestMapping("/join")
    public String join() {
        return "user/join";
    }

    @RequestMapping("/index")
    @Auth
    public String index(){
        return "user/wow";
    }

    @RequestMapping("character")
    @Auth
    public String character(){
        return "user/character";
    }

    @RequestMapping("/photo")
    @Auth
    public String photo(){
        return "user/photo";
    }

    @RequestMapping("/logout")
    public String logout(HttpServletResponse response)
    {
        Cookie cookie = new Cookie(SecurityConstants.HEADER_STRING,null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
        return "/user/join";
    }
}
