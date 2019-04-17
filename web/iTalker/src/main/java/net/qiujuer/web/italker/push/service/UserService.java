package net.qiujuer.web.italker.push.service;

import com.google.common.base.Strings;
import net.qiujuer.web.italker.push.bean.api.base.ResponseModel;
import net.qiujuer.web.italker.push.bean.api.user.UpdateInfoModel;
import net.qiujuer.web.italker.push.bean.card.UserCard;
import net.qiujuer.web.italker.push.bean.db.User;
import net.qiujuer.web.italker.push.factory.UserFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;

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

    //拉取联系人
    @GET
    @Path("/contact")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseModel<List<UserCard>> contact(){
        User self = getSelf();
        // 拿到我的联系人
        List<User> users = UserFactory.contacts(self);
        // 转换为UserCard
        List<UserCard> userCards= users.stream()
                .map(user -> new UserCard(user,true))
                .collect(Collectors.toList());

        return ResponseModel.buildOk(userCards);
    }

    // 关注人
    // 简化：关注人的操作其实是双方同时关注，
    @PUT //修改类请求使用PUT
    @Path("/follow/{followId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseModel<UserCard> follow(@PathParam("followId") String followId){
        User self = getSelf();
        // 自己不能关注自己
        if (self.getId().equalsIgnoreCase(followId)
            || Strings.isNullOrEmpty(followId)){
            return ResponseModel.buildParameterError();
        }

        // 找到我要关注的人
        User followUser = UserFactory.findById(followId);
        if (followUser == null) {
            // 未找到人
            return ResponseModel.buildNotFoundUserError(null);
        }
        // 备注默认没有，后可以扩展
        followUser = UserFactory.follow(self, followUser,null);
        if(followUser == null){
            //关注失败，返回服务器异常
            return ResponseModel.buildServiceError();
        }
        // TODO 通知我关注的人我关注他


        // 返回关注的人信息
        return ResponseModel.buildOk(new UserCard(followUser,true));
    }

    // 获取某人的信息
    @GET //修改类请求使用PUT
    @Path("{id}") // localhost/api/user/{id}
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseModel<UserCard> getUser(@PathParam("id") String id){
        if (Strings.isNullOrEmpty(id)){
            return ResponseModel.buildParameterError();
        }

        User self = getSelf();
        if (self.getId().equalsIgnoreCase(id)) {
            // 返回自己，不必查询数据库
            return ResponseModel.buildOk(new UserCard(self,true));
        }

        User user = UserFactory.findById(id);
        if (user == null){
            return ResponseModel.buildNotFoundUserError(null);
        }

        boolean isFollow = UserFactory.getUserFollow(self, user) != null;
        return ResponseModel.buildOk(new UserCard(user, isFollow));

    }

    // 搜索人的接口实现
    @GET //搜索人 ，不涉及数据修改，只是查询
    @Path("/search/{name:(.*)?}")//名字为任意字符，可以为空 // localhost/api/user/search/
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseModel<List<UserCard>> search(@DefaultValue("") @PathParam("name") String name){
        User self = getSelf();
        // 先查询数据
        List<User> searchUsers = UserFactory.search(name);
        // 把查询的人封装为UserCard
        // 判断这些人是否有我已经关注的人，
        // 如果有，则返回的关注状态中应该已经设置好状态

        // 拿出我的联系人
        final List<User> constacts = UserFactory.contacts(self);

        // 把User->UserCard
        List<UserCard> userCards = searchUsers.stream()
                .map(user -> {
                    //判断这个人是否是我自己 || 在我的联系人中
                    boolean isFollow = user.getId().equalsIgnoreCase(self.getId())
                            // 进行联系人的任意匹配，匹配其中的Id字段
                            || constacts.stream().anyMatch(
                                    contactUser-> contactUser.getId()
                                            .equalsIgnoreCase(user.getId())
                    );

                    return new UserCard(user,isFollow);
                }).collect(Collectors.toList());
        // 返回
        return ResponseModel.buildOk(userCards);
    }
}
