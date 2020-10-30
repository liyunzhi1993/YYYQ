package yyyq.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yyyq.auth.entity.Game;
import yyyq.auth.entity.GameNav;
import yyyq.auth.entity.User;
import yyyq.common.exception.CustomException;
import yyyq.auth.service.GameService;
import yyyq.common.controller.BaseController;
import yyyq.common.model.ActionResultModel;
import yyyq.common.model.auth.GameNavModel;

import java.util.ArrayList;
import java.util.List;

/**
 * GameController
 *
 * @author liyunzhi
 * @date 2018/8/7 14:39
 */
@RestController
@RequestMapping("/game")
public class GameController extends BaseController {

    @Autowired
    private GameService gameService;

    @PostMapping("/getListByUser")
    public ActionResultModel<List<Game>> getListByUser() {
        ActionResultModel<List<Game>> actionResultModel = new ActionResultModel<>();
        try {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            actionResultModel.setActionResult(gameService.getGameListByUserId(user.userId));
        } catch (CustomException ex) {
            actionResultModel.setHasErrorMessage(ex.getMessage());
        }
        return actionResultModel;
    }

    @PostMapping("/getGameNavList")
    public ActionResultModel<List<GameNavModel>> getGameNavList() {
        ActionResultModel<List<GameNavModel>> actionResultModel = new ActionResultModel<>();
        try {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            actionResultModel.setActionResult(gameService.getGameNavList(user.userId));
        } catch (CustomException ex) {
            actionResultModel.setHasErrorMessage(ex.getMessage());
        }
        return actionResultModel;
    }
}
