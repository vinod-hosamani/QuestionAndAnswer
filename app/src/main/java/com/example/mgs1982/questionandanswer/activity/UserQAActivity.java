package com.example.mgs1982.questionandanswer.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.mgs1982.questionandanswer.R;
import com.example.mgs1982.questionandanswer.base.BaseActivity;
import com.example.mgs1982.questionandanswer.model.QuestionModel;
import com.example.mgs1982.questionandanswer.util.QuestionDao;

import java.util.List;

/**
 * Created by MGS1982 on 7/19/2017.
 */

public class UserQAActivity extends BaseActivity implements UserQAActivityInterface
{
    QuestionDao questionDao;
    List<QuestionModel> questionList;
    UserSelectedQuestionFragment userSelectionFragment;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_qa_layout);
        initView();
        setValues();
        setClickListener();

        openNextQuestionInFragment(questionList);

    }

    @Override
    public void initView() {

    }

    @Override
    public void setValues()
    {
        questionDao=new QuestionDao(this);
        questionList=questionDao.getAllQuestions();
    }

    @Override
    public void setClickListener() {

    }

    @Override
    public void onBackPressed() {
        finish();
    }

    public void openNextQuestionInFragment(List<QuestionModel> quesList)
    {
        userSelectionFragment=new UserSelectedQuestionFragment(this,quesList);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.question_frame,userSelectionFragment,"specific question")
                .addToBackStack(null)
                .commit();


    }
}
