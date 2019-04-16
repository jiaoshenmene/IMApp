package net.qiujuer.web.italker.push.provider;

import com.google.common.base.Strings;
import net.qiujuer.web.italker.push.bean.api.base.ResponseModel;
import net.qiujuer.web.italker.push.bean.db.User;
import net.qiujuer.web.italker.push.factory.UserFactory;
import org.glassfish.jersey.server.ContainerRequest;
import org.glassfish.jersey.server.SubjectSecurityContext;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.io.IOException;
import java.security.Principal;

/**
 * 用于所有的请求的接口的过滤和拦截
 * @Author: 杜甲 Email:815319775@qq.com
 * @Date: 2019-04-16 18:57
 */

public class AuthRequestFilter implements ContainerRequestFilter {

    // 实现接口的过滤方法
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        // 检测是否是登录注册接口
        String relationPath = ((ContainerRequest) requestContext).getPath(false);
        if (relationPath.startsWith("account/login") ||
                relationPath.startsWith("account/register")){
            // 直接返回
            return;
        }
        // 从Headers中去找到第一个token节点
        String token =requestContext.getHeaders().getFirst("token");
        if (!Strings.isNullOrEmpty(token)){
            //通过token查询自己信息
            final User self = UserFactory.findByToken(token);
            if (self != null){
                // 给当前请求添加一个上下文
                requestContext.setSecurityContext(new SecurityContext() {
                    // 主体部分
                    @Override
                    public Principal getUserPrincipal() {
                        // User 实现接口
                        return self;
                    }

                    @Override
                    public boolean isUserInRole(String role) {
                        // 可以在这里写入用户的权限，role 是权限名
                        // 可以管理管理员权限等待
                        return true;
                    }

                    @Override
                    public boolean isSecure() {
                        // 默认返回false，一般检查HTTPS
                        return false;
                    }

                    @Override
                    public String getAuthenticationScheme() {
                        //
                        return null;
                    }
                });
                return;
            }

        }

        //直接返回一个账户需要登录的Model
        ResponseModel model = ResponseModel.buildAccountError();

        // 拦截，停止一个请求的继续下发，调用该方法后直接返回请求
        // 不会走到Service中去
        requestContext.abortWith(Response.status(Response.Status.OK)
                .entity(model)
                .build());
    }
}
