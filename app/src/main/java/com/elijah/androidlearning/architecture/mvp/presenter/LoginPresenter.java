package com.elijah.androidlearning.architecture.mvp.presenter;

import com.elijah.androidlearning.architecture.mvp.model.UserBiz;
import com.elijah.androidlearning.architecture.mvp.view.IMvpLoginView;

/**
 * @FileName null.java
 * @Description TODO
 * @Author 80254912
 * @Date 2023/4/14
 */
public class LoginPresenter {
    private UserBiz userBiz;
    private IMvpLoginView iMvpLoginView;

    public LoginPresenter(IMvpLoginView iMvpLoginView) {
        this.iMvpLoginView = iMvpLoginView;
        this.userBiz = new UserBiz();
    }

    public void login() {
        String userName = iMvpLoginView.getUserName();
        String password = iMvpLoginView.getPassword();
        boolean isLoginSuccessful = userBiz.login(userName, password);
        iMvpLoginView.onLoginResult(isLoginSuccessful);
    }
}
