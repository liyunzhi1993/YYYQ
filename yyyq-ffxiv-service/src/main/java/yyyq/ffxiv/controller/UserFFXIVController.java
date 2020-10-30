package yyyq.ffxiv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import yyyq.common.exception.CustomException;
import yyyq.common.model.auth.UserModel;
import yyyq.ffxiv.entity.UserFFXIV;
import yyyq.ffxiv.model.select.UserFFXIVSelectListModel;
import yyyq.ffxiv.service.UserFFXIVService;
import yyyq.common.controller.BaseController;
import yyyq.common.model.ActionResultModel;


/**
 * UserController
 *
 * @author liyunzhi
 * @date 2018/7/5 11:12
 */
@RestController
@RequestMapping("/userffxiv")
public class UserFFXIVController extends BaseController {

    @Autowired
    private UserFFXIVService userFFXIVService;

    @PostMapping("/get")
    public ActionResultModel<UserFFXIV> getUserFFXIV() {
        ActionResultModel<UserFFXIV> actionResultModel = new ActionResultModel<>();
        try {
            actionResultModel.setActionResult(userFFXIVService.getUserFFXIV(userModel));
        } catch (CustomException ex) {
            actionResultModel.setHasErrorMessage(ex.getMessage());
        }
        return actionResultModel;
    }
    
    @PostMapping("/save")
    public ActionResultModel<String> login(@RequestBody UserFFXIV userFFXIV) {
        ActionResultModel<String> actionResultModel = new ActionResultModel<>();
        try {
            userFFXIVService.saveUserFFXIV(userFFXIV,userModel);
        } catch (CustomException ex) {
            actionResultModel.setHasErrorMessage(ex.getMessage());
        }
        return actionResultModel;
    }

    @PostMapping("/getSelectList")
    public ActionResultModel<UserFFXIVSelectListModel> getUserFFXIVSelectList() {
        ActionResultModel<UserFFXIVSelectListModel> actionResultModel = new ActionResultModel<>();
        try {
            actionResultModel.setActionResult(userFFXIVService.getUserFFXIVSelectList());
        } catch (CustomException ex) {
            actionResultModel.setHasErrorMessage(ex.getMessage());
        }
        return actionResultModel;
    }
}
