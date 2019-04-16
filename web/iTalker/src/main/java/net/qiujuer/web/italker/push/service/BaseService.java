package net.qiujuer.web.italker.push.service;

import net.qiujuer.web.italker.push.bean.db.User;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;

/*
 * @Author: 杜甲 Email:815319775@qq.com
 * @Date: 2019-04-16 19:20
 */
public class BaseService {
    //添加一个上下文注解，该注解会给securityContext赋值
    //具体的值为我们的拦截器中所返回的SecurityContext
    @Context
    protected SecurityContext securityContext;

    /**
     * 从上下文中直接获取
     * */
    protected User getSelf(){
        return (User) securityContext.getUserPrincipal();
    }
}
