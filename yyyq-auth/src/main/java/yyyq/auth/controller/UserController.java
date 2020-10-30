package yyyq.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;
import yyyq.auth.entity.User;
import yyyq.common.exception.CustomException;
import yyyq.auth.model.EditUserModel;
import yyyq.auth.model.LoginUserModel;
import yyyq.auth.model.RegisterUserModel;
import yyyq.auth.security.JwtTokenProvider;
import yyyq.auth.service.GameService;
import yyyq.auth.service.UserService;
import yyyq.common.controller.BaseController;
import yyyq.common.model.ActionResultModel;
import yyyq.common.model.auth.UserModel;

import java.io.IOException;

/**
 * UserController
 *
 * @author liyunzhi
 * @date 2018/7/5 11:12
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private GameService gameService;

    @PostMapping("/register")
    public ActionResultModel<String> register(@RequestBody RegisterUserModel registerUserModel) {
        ActionResultModel<String> actionResultModel = new ActionResultModel<>();
        try {
            actionResultModel.setActionResult(userService.register(registerUserModel));
        } catch (CustomException ex) {
            actionResultModel.setHasErrorMessage(ex.getMessage());
        }
        return actionResultModel;
    }

    @PostMapping("/login")
    public ActionResultModel<String> login(@RequestBody LoginUserModel loginUserModel) {
        ActionResultModel<String> actionResultModel = new ActionResultModel<>();
        try {
            actionResultModel.setActionResult(userService.login(loginUserModel));
        } catch (CustomException ex) {
            actionResultModel.setHasErrorMessage(ex.getMessage());
        }
        return actionResultModel;
    }

    @PostMapping("/current")
    public ActionResultModel<UserModel> currentUser() {
        ActionResultModel<UserModel> actionResultModel = new ActionResultModel<>();
        try {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            UserModel userModel = new UserModel();
            userModel.userId=user.userId;
            userModel.userName = user.userName;
            userModel.avatar=user.avatar;
            userModel.sex=user.sex;
            userModel.banner=user.banner;
            userModel.nickName=user.nickName;
            userModel.gameNavModelList=gameService.getGameNavList(user.userId);
            actionResultModel.setActionResult(userModel);
        } catch (CustomException ex) {
            actionResultModel.setHasErrorMessage(ex.getMessage());
        }
        return actionResultModel;
    }

    @PostMapping("uploadAvatar")
    public ActionResultModel<String> uploadAvatar() {
        ActionResultModel<String> actionResultModel = new ActionResultModel<>();
        try {
            try {
                User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                MultipartFile file = ((StandardMultipartHttpServletRequest) request).getFile("avatar");
                actionResultModel.setActionResult(userService.uploadAvatar(file.getOriginalFilename(), file.getBytes(), user.userId));
            } catch (IOException e) {
                actionResultModel.setHasErrorMessage("图片异常请重新上传");
            }
        } catch (CustomException ex) {
            actionResultModel.setHasErrorMessage(ex.getMessage());
        }
        return actionResultModel;
    }

    @PostMapping("editUser")
    public ActionResultModel<String> editUser(@RequestBody EditUserModel editUserModel) {
        ActionResultModel<String> actionResultModel = new ActionResultModel<>();
        try {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            editUserModel.userId = user.userId;
            userService.editUser(editUserModel);
        } catch (CustomException ex) {
            actionResultModel.setHasErrorMessage(ex.getMessage());
        }
        return actionResultModel;
    }
}
