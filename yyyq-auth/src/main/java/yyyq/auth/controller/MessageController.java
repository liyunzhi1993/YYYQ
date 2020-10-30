package yyyq.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yyyq.common.exception.CustomException;
import yyyq.auth.service.MessageService;
import yyyq.common.model.ActionResultModel;

/**
 * 消息发送
 *
 * @author liyunzhi
 * @date 2018/7/25 15:56
 */
@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @PostMapping("/sendRegisterCode")
    public ActionResultModel<String> register(@RequestBody String phone) {
        ActionResultModel<String> actionResultModel = new ActionResultModel<>();
        try {
            if (messageService.sendRegisterCode(phone)) {
                actionResultModel.setActionResult("发送短信成功");
            } else {
                actionResultModel.setHasErrorMessage("发送短信失败");
            }
        } catch (CustomException ex) {
            actionResultModel.setHasErrorMessage(ex.getMessage());
        }
        return actionResultModel;
    }
}
