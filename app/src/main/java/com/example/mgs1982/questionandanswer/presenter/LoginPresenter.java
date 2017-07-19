package com.example.mgs1982.questionandanswer.presenter;

import com.example.mgs1982.questionandanswer.activity.LoginActivity;
import com.example.mgs1982.questionandanswer.activity.LoginActivityInterface;

/**
 * Created by MGS1982 on 7/19/2017.
 */

public class LoginPresenter implements LoginPresenterInterface {

    LoginActivityInterface loginActivity;

    public LoginPresenter(LoginActivity loginActivity)
    {
        this.loginActivity=loginActivity;
    }

    @Override
    public void authenticateUser(String userName, String password)
    {
        if(userName.equals("user") && password.equals("user")|| userName.equals("admin") && password.equals("admin"))
        {
            loginActivity.authenticationSuccess(userName);
        }
        else
        {
            loginActivity.authenticatoinFailure();
        }
    }
}
