package com.example.mgs1982.questionandanswer.presenter;

import android.content.Context;
import android.os.Bundle;

import com.example.mgs1982.questionandanswer.R;
import com.example.mgs1982.questionandanswer.activity.AdminActivityInterface;
import com.example.mgs1982.questionandanswer.model.QuestionModel;
import com.example.mgs1982.questionandanswer.util.Constants;
import com.example.mgs1982.questionandanswer.util.QuestionDao;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by MGS1982 on 7/19/2017.
 */

public class AdminPresenter implements AdminPresenterInterface
{
    AdminActivityInterface adminActivity;
    Context context;
    QuestionDao questionDao;

    public AdminPresenter(Context context,AdminActivityInterface adminActivity)
    {
        this.adminActivity=adminActivity;
        this.context=context;
        questionDao = new QuestionDao(context);
    }

    @Override
    public void addQuestion(Bundle bundle)
    {
        QuestionModel model=new QuestionModel();
        model.setQuestion(bundle.getString(Constants.KEY_QUES));
        model.setOptionA(bundle.getString(Constants.KEY_OPT_A));
        model.setOptionB(bundle.getString(Constants.KEY_OPT_B));
        model.setOptionC(bundle.getString(Constants.KEY_OPT_C));
        model.setOptionD(bundle.getString(Constants.KEY_OPT_D));
        model.setRightAns(bundle.getString(Constants.KEY_RIGHT_ANS));


        if(verifyAdminIp(model))
        {
            try
            {
                questionDao.addQuestion(model);
                adminActivity.quesAddedSuccess(context.getString(R.string.add_question_success));
            }
            catch (Exception e)
            {
                e.printStackTrace();
                adminActivity.quesAddedFailure(context.getString(R.string.add_question_failure));
            }
        }
        else
        {
            adminActivity.quesAddedFailure("your right answer must contain within your options");
        }
    }

    private boolean verifyAdminIp(QuestionModel model)
    {
        if (model.getOptionA().equals(model.getRightAns()))
            return true;
        else if (model.getOptionB().equals(model.getRightAns()))
            return true;
        else if (model.getOptionC().equals(model.getRightAns()))
            return true;
        else if (model.getOptionD().equals(model.getRightAns()))
            return true;
        return false;
    }
}
