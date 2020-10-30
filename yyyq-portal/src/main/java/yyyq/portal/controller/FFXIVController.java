package yyyq.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import yyyq.common.annotation.Auth;

/**
 * 最终幻想14
 *
 * @author liyunzhi
 * @date 2018/8/9 16:21
 */
@Controller
@RequestMapping("/ffxiv")
public class FFXIVController extends BaseController {
    @RequestMapping("/job")
    @Auth
    public String join() {
        return "ffxiv/job";
    }
}
