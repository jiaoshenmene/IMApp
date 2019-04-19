package net.qiujuer.web.italker.push.factory;

import com.google.common.base.Strings;
import net.qiujuer.web.italker.push.bean.db.User;
import net.qiujuer.web.italker.push.bean.db.UserFollow;
import net.qiujuer.web.italker.push.utils.Hib;
import net.qiujuer.web.italker.push.utils.TextUtil;
import org.hibernate.Session;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

/*
 * @Author: 杜甲 Email:815319775@qq.com
 * @Date: 2019-04-15 13:53
 */
public class UserFactory {

    // 通过Token 找到User
    // 只能自己使用，查询的信息是个人信息，非他人信息
    public static User findByToken(String token) {
        return Hib.query(session -> (User) session
                .createQuery("from User where token=:token")
                .setParameter("token", token)
                .uniqueResult());
    }

    // 通过phone 找到User
    public static User findByPhone(String phone) {
        return Hib.query(session -> (User) session
                .createQuery("from User where phone=:inPhone")
                .setParameter("inPhone", phone)
                .uniqueResult());
    }

    // 通过Name找到User
    public static User findByName(String name) {
        return Hib.query(session -> (User) session
                .createQuery("from User where name=:name")
                .setParameter("name", name)
                .uniqueResult());
    }

    // 通过id找到User
    public static User findById(String id) {
        //  通过id查询
        return Hib.query(session -> (User) session.get(User.class, id));
    }

    /**
     * 更新用户信息到数据库
     * @param user
     * @return User
     *
     * */
    public static User update(User user){
        return Hib.query(session -> {
            session.saveOrUpdate(user);
            return user;
        });
    }

    /**
     * 给当前的账户绑定PushId
     * @param user 自己的User
     * @param pushId 自己设备的PushId
     * @return User
     * */
    public static User bindPushId(User user, String pushId){
        //
        if (Strings.isNullOrEmpty(pushId)){
            return null;
        }

        // 第一步，查询是否有其他设备绑定了这个设备Id
        // 取消绑定，避免推送混乱
        // 查询的列表不能包括自己
        Hib.queryOnly(session -> {
            @SuppressWarnings("unckecked")
            List<User> userList = (List<User>) session
                    .createQuery("from User where lower(pushId)=:pushId and id !=:userId")
                    .setParameter("pushId",pushId.toLowerCase())
                    .setParameter("userId",user.getId())
                    .list();

            for (User u : userList){
                //更新为null
                u.setPushId(null);
                session.saveOrUpdate(u);
            }
        });

        if (pushId.equalsIgnoreCase(user.getPushId())){
            //如果当前需要绑定的设备Id，之前已经绑定过了
            //那么不需要额外绑定
            return user;
        } else {
            // 如果当前账户之前的设备Id，和需要绑定的不同
            // 那么需要单点登录，让之前的设备退出账户，给之前的设备推送一条退出的消息
            if (Strings.isNullOrEmpty(user.getPushId())){
                // TODO 推送一条退出消息

            }
            // 更新新的设备Id
            user.setPushId(pushId);
            return update(user);

        }



    }


    /**
     * 使用账户和密码进行登录
     * */
    public static User login(String account, String password){
        final String accountStr = account.trim();
        // 把原文进行同样的处理，然后才能匹配
        final String encodePassword = encodePassword(password);
        // 寻找
        User user = Hib.query(session -> (User)session.createQuery("from User Where phone=:phone and password=:password")
                    .setParameter("phone",accountStr)
                    .setParameter("password",encodePassword)
                    .uniqueResult());

        if (user != null) {
            user = login(user);
        }
        return user;

    }

    /**
     * 用户注册
     * 注册的操作需要写入数据库，并返回数据库中的User信息
     * @param account 账户
     * @param password 密码
     * @param name 用户名
     * @return User
     * */
    public static User register(String account, String password, String name){
        // 去除账户中的首位空格
        account = account.trim();
        // 密码进行加密
        //处理密码
        password = encodePassword(password);

        User user = createUser(account,password,name);
        if (user != null){
            user = login(user);
        }
        return user;
    }

    /**
     * 注册部分的新建用户逻辑
     * @param account 手机号
     * @param password 密码
     * @param name     名字
     * @return 返回一个用户
     * */
    private static User createUser(String account, String password, String name){
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        // 账户就是手机号
        user.setPhone(account);

        // 数据库存储
        // 数据库存储
        return Hib.query(session -> {
            session.save(user);
            return user;
        });

    }

    /**
     * 把一个User进行登录操作
     * 本质上是对token进行操作
     * @param user
     * @return User
     * */
    private static User login(User user){
        // 使用一个随机的UUID值充当Token
        String newToken = UUID.randomUUID().toString();
        // 进行一个Base64格式化
        newToken = TextUtil.encodeBase64(newToken);
        user.setToken(newToken);

        return update(user);

    }

    private static String encodePassword(String password){
        // 密码进行首位空格
        password = password.trim();
        password = TextUtil.getMD5(password);

        // 进行MD5非对称加密，加盐会更安全，盐也要存储
        // 再进行一次对称的Base64加密，当然可以采取加盐方案
        return TextUtil.encodeBase64(password);
    }

    /**
     * 获取我的联系人的列表
     * @param self user
     * @return
     * */
    public static List<User> contacts(User self){
        return Hib.query(session -> {
            // 重新加载一次用户信息到self中，和当前的session绑定
            session.load(self,self.getId());
            Set<UserFollow> flows = self.getFollowing();

            // 使用简写方式
            return flows.stream()
                    .map(UserFollow::getTarget)
                    .collect(Collectors.toList());
        });
    }

    /**
     * 关注人的操作
     * @param origin 发起者
     * @param target 被关注的人
     * @param alias 备注名
     * @return 被关注的人的信息
     * */
    public static User follow(final User origin, final User target, final String alias){
        UserFollow follow = getUserFollow(origin, target);
        if (follow != null){
            // 已关注，直接返回
            return follow.getTarget();
        }
        return Hib.query(session -> {
            session.load(origin,origin.getId());
            session.load(target,target.getId());

            // 我关注人的时候，同时他也关注我，
            // 所以需要添加两天UserFollow数据
            UserFollow originFollow = new UserFollow();
            originFollow.setOrigin(origin);
            originFollow.setTarget(target);
            // 备注是我对他的备注，他对我默认没有备注
            originFollow.setAlias(alias);

            // 发起者是他，我是被关注的人
            UserFollow targetFollow = new UserFollow();
            targetFollow.setOrigin(target);
            targetFollow.setTarget(origin);

            // 保存数据库
            session.save(originFollow);
            session.save(targetFollow);

            return target;
        });

    }

    /**
     * 查询两个人是否已经关注
     * @Param origin 发起者
     * @param target 被关注人
     * @return 返回中间类
    */
    public static UserFollow getUserFollow(final User origin, final User target){
        return Hib.query(session -> (UserFollow) session.createQuery("from UserFollow where originId = :originId and targetId = :targetId")
                   .setParameter("originId",origin.getId())
                   .setParameter("targetId", target.getId())
                   .setMaxResults(1)
                    // 查询一条数据
                   .uniqueResult());

    }

    /**
     * 搜索联系人的实现
     * @Param name 查询的name,允许为空
     * @return   查询到的用户集合，如果name为空，则返回最近的用户
     * */
    public static List<User> search(String name){

        if (Strings.isNullOrEmpty(name))
            name = "";//保证不能为null的情况，减少后面的一些判断和额外错误
        final String searchName = "%" + name + "%"; //模糊匹配
        return Hib.query(session -> {
            // 查询的条件：name 忽略大小写，并且使用like（模糊）查询
            // 头像和描述必须完善才能查询到
            return (List<User>) session.createQuery("from User where lower(name) like :name and portrait is not null and description is not null ")
                    .setParameter("name", searchName)
                    .setMaxResults(20) //最多20条
                    .list();

        });

    }

}
