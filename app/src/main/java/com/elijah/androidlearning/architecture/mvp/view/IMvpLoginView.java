package com.elijah.androidlearning.architecture.mvp.view;

/**
 * @FileName null.java
 * @Description TODO
 * @Author 80254912
 * @Date 2023/4/14
 */
public interface IMvpLoginView {
    String getUserName();

    String getPassword();

    void onLoginResult(Boolean isLoginSuccess);
}
