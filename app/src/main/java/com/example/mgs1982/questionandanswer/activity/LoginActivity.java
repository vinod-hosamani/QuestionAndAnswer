package com.example.mgs1982.questionandanswer.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.Toast;

import com.example.mgs1982.questionandanswer.R;
import com.example.mgs1982.questionandanswer.base.BaseActivity;
import com.example.mgs1982.questionandanswer.presenter.LoginPresenter;
import com.example.mgs1982.questionandanswer.presenter.LoginPresenterInterface;

/**
 * Created by MGS1982 on 7/19/2017.
 */

public class LoginActivity extends BaseActivity implements LoginActivityInterface, View.OnClickListener
{
    AppCompatTextView loginActivityTextView;
    AppCompatTextView loginErrorTextView;
    AppCompatEditText userNameEditText;
    AppCompatEditText passwordEditText;
    AppCompatButton loginButton;

    LoginPresenterInterface loginPresenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        setValues();
        setClickListener();
    }


    @Override
    public void initView()
    {
        loginActivityTextView=(AppCompatTextView)findViewById(R.id.login_activity_text);
        loginErrorTextView=(AppCompatTextView)findViewById(R.id.login_error_text);
        userNameEditText=(AppCompatEditText)findViewById(R.id.user_name_editText);
        passwordEditText=(AppCompatEditText)findViewById(R.id.password_editText);
        loginButton=(AppCompatButton)findViewById(R.id.login_button);

        loginPresenter=new LoginPresenter(this);
    }

    @Override
    public void setValues()
    {
       loginActivityTextView.setText(R.string.login_activity_text);
        loginButton.setText(R.string.login_button_text);
        loginErrorTextView.setTextColor(Color.RED);
    }

    @Override
    public void setClickListener()
    {
     loginButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.login_button:
                String userName=userNameEditText.getText().toString();
                String password=passwordEditText.getText().toString();
                loginPresenter.authenticateUser(userName,password);
           break;
        }

    }

    @Override
    public void authenticationSuccess(String username)
    {
        if(username.equals("admin"))
        {
            Intent intent=new Intent(this,AdminActivity.class);
            startActivity(intent);
        }
        else
        {
            Intent intent=new Intent(this,UserQAActivity.class);
            startActivity(intent);
        }

    }

    @Override
    public void authenticatoinFailure()
    {
        Toast.makeText(this, "error", Toast.LENGTH_SHORT).show();
        loginErrorTextView.setText(R.string.login_error_message);
    }


}
