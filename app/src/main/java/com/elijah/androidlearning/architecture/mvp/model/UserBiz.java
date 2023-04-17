package com.elijah.androidlearning.architecture.mvp.model;

/**
 * @FileName null.java
 * @Description TODO
 * @Author 80254912
 * @Date 2023/4/14
 */
public class UserBiz implements IUserBiz {

    @Override
    public boolean login(String userName, String password) {

        if (userName.equals("Yijun") && password.equals("12315")) {
            User user = new User();
            user.setUserName(userName);
            user.setPassword(password);
            return true;
        }
        return false;
    }
}
