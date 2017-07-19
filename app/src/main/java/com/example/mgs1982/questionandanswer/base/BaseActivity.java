package com.example.mgs1982.questionandanswer.base;

import android.support.v7.app.AppCompatActivity;

/**
 * Created by MGS1982 on 7/19/2017.
 */

public abstract class BaseActivity extends AppCompatActivity {

    public abstract void initView();
    public abstract void setValues();
    public abstract void setClickListener();
}
