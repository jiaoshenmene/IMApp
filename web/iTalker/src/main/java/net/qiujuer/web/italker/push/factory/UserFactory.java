package net.qiujuer.web.italker.push.factory;

import net.qiujuer.web.italker.push.bean.db.User;
import net.qiujuer.web.italker.push.utils.Hib;
import org.hibernate.Session;

/*
 * @Author: 杜甲 Email:815319775@qq.com
 * @Date: 2019-04-15 13:53
 */
public class UserFactory {
    /**
     * 用户注册
     * 注册的操作需要写入数据库，并返回数据库中的User信息
     * @param account 账户
     * @param password 密码
     * @param name 用户名
     * @return User
     * */
    public static User register(String account, String password, String name){
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        // 账户就是手机号
        user.setPhone(account);

        // 进行数据库操作

        // 首先创建一个会话
        Session session = Hib.session();
        // 开启一个事物
        session.beginTransaction();
        try{
            // 保存操作
            session.save(user);
            //提交我们的事务
            session.getTransaction().commit();
            return user;
        } catch (Exception e){
            session.getTransaction().rollback();
            return null;
        }
    }
}
