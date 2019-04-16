package net.qiujuer.web.italker.push.bean.api.account;

import com.google.gson.annotations.Expose;

/*
 * @Author: 杜甲 Email:815319775@qq.com
 * @Date: 2019-04-16 10:57
 */
public class LoginModel {
    @Expose
    private String account;
    @Expose
    private String password;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
