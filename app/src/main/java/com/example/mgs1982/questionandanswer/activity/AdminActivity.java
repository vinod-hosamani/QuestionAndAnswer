package com.example.mgs1982.questionandanswer.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.example.mgs1982.questionandanswer.R;
import com.example.mgs1982.questionandanswer.base.BaseActivity;
import com.example.mgs1982.questionandanswer.presenter.AdminPresenter;
import com.example.mgs1982.questionandanswer.presenter.AdminPresenterInterface;
import com.example.mgs1982.questionandanswer.util.Constants;

/**
 * Created by MGS1982 on 7/19/2017.
 */

public class AdminActivity extends BaseActivity implements AdminActivityInterface, View.OnClickListener
{
    AppCompatTextView addQuesTextView;
    AppCompatEditText quesEditText;
    AppCompatEditText optionAEditText;
    AppCompatEditText optionBEditText;
    AppCompatEditText optionCEditText;
    AppCompatEditText optionDEditText;
    AppCompatEditText rightAnsEditText;
    AppCompatButton addQuesButton;

    AdminPresenterInterface adminPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        initView();
        setValues();
        setClickListener();

    }

    @Override
    public void initView()
    {
        addQuesTextView=(AppCompatTextView)findViewById(R.id.add_question_title);
        addQuesButton=(AppCompatButton)findViewById(R.id.add_ques_button);
        quesEditText=(AppCompatEditText)findViewById(R.id.add_ques_editText);
        optionAEditText=(AppCompatEditText)findViewById(R.id.add_optionA_editText);
        optionBEditText=(AppCompatEditText)findViewById(R.id.add_optionB_editText);
        optionCEditText=(AppCompatEditText)findViewById(R.id.add_optionC_editText);
        optionDEditText=(AppCompatEditText)findViewById(R.id.add_optionD_editText);
        rightAnsEditText=(AppCompatEditText)findViewById(R.id.add_right_ans);

        adminPresenter=new AdminPresenter(this,this);
    }
    @Override
    public void setValues()
    {
        addQuesTextView.setText(R.string.add_question_title);
        addQuesButton.setText(R.string.add_question_button);
    }
    @Override
    public void setClickListener()
    {
          addQuesButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
           case R.id.add_ques_button:
               Bundle bundle=new Bundle();
               bundle.putString(Constants.KEY_QUES,quesEditText.getText().toString());
               bundle.putString(Constants.KEY_OPT_A,quesEditText.getText().toString());
               bundle.putString(Constants.KEY_OPT_B,quesEditText.getText().toString());
               bundle.putString(Constants.KEY_OPT_C,quesEditText.getText().toString());
               bundle.putString(Constants.KEY_OPT_D,quesEditText.getText().toString());
               bundle.putString(Constants.KEY_RIGHT_ANS,rightAnsEditText.getText().toString());
               adminPresenter.addQuestion(bundle);
               break;

        }

    }
    @Override
    public void quesAddedSuccess(String message)
    {
        AlertDialog.Builder alertDialog=new AlertDialog.Builder(this);
        alertDialog.setTitle(R.string.alert_success_title);
        alertDialog.setMessage(message);

        alertDialog.setPositiveButton(R.string.ok_button, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
                finish();
                startActivity(getIntent());
            }
        });

    }

    @Override
    public void quesAddedFailure(String message)
    {
       AlertDialog.Builder alertDialog=new AlertDialog.Builder(this);
        alertDialog.setTitle(R.string.aler_error_title);
        alertDialog.setMessage(message);
        alertDialog.show();
    }



}
