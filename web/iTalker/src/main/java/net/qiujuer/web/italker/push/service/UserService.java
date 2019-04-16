package net.qiujuer.web.italker.push.service;

import com.google.common.base.Strings;
import net.qiujuer.web.italker.push.bean.api.base.ResponseModel;
import net.qiujuer.web.italker.push.bean.api.user.UpdateInfoModel;
import net.qiujuer.web.italker.push.bean.card.UserCard;
import net.qiujuer.web.italker.push.bean.db.User;
import net.qiujuer.web.italker.push.factory.UserFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/*
 * @Author: 杜甲 Email:815319775@qq.com
 * @Date: 2019-04-16 16:52
 */
// 用户信息 localhost/api/user/...

@Path("/user")
public class UserService extends BaseService {

    //用户信息修改接口
    //返回自己的个人信息
    // 无须指定Path就是当前目录
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseModel<UserCard> update(UpdateInfoModel model){

        if (!UpdateInfoModel.check(model)){
            return ResponseModel.buildParameterError();
        }

        // 拿到自己的个人信息
        User user = getSelf();
        //更新用户信息
        user = model.updateToUser(user);
        user = UserFactory.update(user);
        // 架构自己的用户信息
        UserCard card = new UserCard(user,true);
        // 返回
        return ResponseModel.buildOk(card);
    }


}
