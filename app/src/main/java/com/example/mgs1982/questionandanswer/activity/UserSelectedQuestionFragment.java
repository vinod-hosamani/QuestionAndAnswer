package com.example.mgs1982.questionandanswer.activity;


import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mgs1982.questionandanswer.R;
import com.example.mgs1982.questionandanswer.model.QuestionModel;

import java.util.List;
import java.util.Locale;

import static android.app.Activity.RESULT_OK;

/**
 * Created by MGS1982 on 7/20/2017.
 */

public class UserSelectedQuestionFragment extends Fragment implements View.OnClickListener
{
   /* private static final int REQ_CODE_SPEECH_INPUT = 654;

    AppCompatTextView question;
    AppCompatTextView optionA;
    AppCompatTextView optionB;
    AppCompatTextView optionC;
    AppCompatTextView optionD;

    AppCompatImageButton micImageButton;
    AppCompatImageButton nextImageButton;


    List<QuestionModel> quesList;
    Context context;
    int count;
    public UserSelectedQuestionFragment(Context context, List<QuestionModel> modelList)
    {
        this.context=context;
        this.quesList=modelList;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view=inflater.inflate(R.layout.user_giving_ans_fragment,container,false);
        initView(view);

        if((count+1)<quesList.size())
            setValues(quesList.get(++count));
        else
            Toast.makeText(context, "your answer are over", Toast.LENGTH_SHORT).show();
        setClickListener();
        return view;

    }

    public void initView(View view)
    {
        question=view.findViewById(R.id.question);
        optionA=view.findViewById(R.id.option_a);
        optionB=view.findViewById(R.id.option_b);
        optionC=view.findViewById(R.id.option_c);
        optionD=view.findViewById(R.id.option_d);

        micImageButton=view.findViewById(R.id.mic_to_speak);
        nextImageButton=view.findViewById(R.id.action_next_question);

        count=-1;
    }

    public void setValues(QuestionModel model)
    {
        question.setText(model.getQuestion());
        optionA.setText(getString(R.string.opt_a) +" "+model.getOptionA());
        optionB.setText(getString(R.string.opt_b) +" "+model.getOptionB());
        optionC.setText(getString(R.string.opt_c) + " "+model.getOptionC());
        optionD.setText(getString(R.string.opt_d) +" "+model.getOptionD());
    }

    public void setClickListener()
    {
        micImageButton.setOnClickListener(this);
        nextImageButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view)
    {
         switch (view.getId())
         {
             case R.id.mic_to_speak:
                 speakAnswer();
                 break;

             case R.id.action_next_question:
                 openNextQuestion();
                 break;
         }
    }

    private void openNextQuestion()
    {
        if((count+1)< quesList.size())
        {
            setValues(quesList.get(++count));
            optionA.setTextColor(Color.BLACK);
            optionB.setTextColor(Color.BLACK);
            optionC.setTextColor(Color.BLACK);
            optionD.setTextColor(Color.BLACK);
        }
        else
        {
            Toast.makeText(context, "questions are over", Toast.LENGTH_SHORT).show();
        }
    }
    private void speakAnswer()
    {
        Intent intent=new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,"give your speech");
        try
        {
            startActivityForResult(intent,REQ_CODE_SPEECH_INPUT);
        }
        catch (ActivityNotFoundException a)
        {
            Toast.makeText(context, "your device is not supported for voice recognization", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode)
        {
            case REQ_CODE_SPEECH_INPUT:
                if(requestCode == RESULT_OK && data!=null)
                {
                    List<String> result=data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    selectAnswer(result.get(0));
                }
                break;
        }
    }

    private void selectAnswer(String voiceIp)
    {
        boolean rightAnsFlag=false;
        Toast.makeText(context, voiceIp, Toast.LENGTH_SHORT).show();

        if(voiceIp.equalsIgnoreCase("option a") || voiceIp.equalsIgnoreCase(quesList.get(count).getOptionA()))
        {
            if(quesList.get(count).getOptionA().equals(quesList.get(count).getRightAns()))
            {
                optionA.setTextColor(Color.GREEN);
                rightAnsFlag=true;
            }
            else
            {
                optionA.setTextColor(Color.RED);
                rightAnsFlag=false;

            }
        }

        else if(voiceIp.equalsIgnoreCase("option b") || voiceIp.equalsIgnoreCase(quesList.get(count).getOptionB()))
        {
            if(quesList.get(count).getOptionB().equalsIgnoreCase(quesList.get(count).getRightAns()))
            {
                optionB.setTextColor(Color.GREEN);
                rightAnsFlag=true;
            }
            else
            {
                optionB.setTextColor(Color.BLACK);
                rightAnsFlag=true;
            }
        }
        else if(voiceIp.equalsIgnoreCase("option c") || voiceIp.equalsIgnoreCase(quesList.get(count).getOptionC()))
        {
            if(quesList.get(count).getOptionC().equalsIgnoreCase(quesList.get(count).getRightAns()))
            {
                optionB.setTextColor(Color.GREEN);
                rightAnsFlag=true;
            }
            else
            {
                optionB.setTextColor(Color.BLACK);
                rightAnsFlag=true;
            }
        }
        else if(voiceIp.equalsIgnoreCase("option d") || voiceIp.equalsIgnoreCase(quesList.get(count).getOptionD()))
        {
            if(quesList.get(count).getOptionD().equalsIgnoreCase(quesList.get(count).getRightAns()))
            {
                optionB.setTextColor(Color.GREEN);
                rightAnsFlag=true;
            }
            else
            {
                optionB.setTextColor(Color.BLACK);
                rightAnsFlag=true;
            }
        }
        else
        {
            AlertDialog.Builder builder=new AlertDialog.Builder(context);
            builder.setTitle("ERROR");
            builder.setMessage("your answer was not proper");
            builder.show();
        }
        if(rightAnsFlag)
        {
            AlertDialog.Builder builder=new AlertDialog.Builder(context);
            builder.setTitle("RIGHT Answer");
            builder.setMessage("you answer is right");
            builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    if((count+1)<quesList.size())
                    {
                        setValues(quesList.get(++count));
                        optionA.setTextColor(Color.BLACK);
                        optionB.setTextColor(Color.BLACK);
                        optionC.setTextColor(Color.BLACK);
                        optionD.setTextColor(Color.BLACK);
                    }
                    else {
                        Toast.makeText(context, "question are over", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            builder.show();
        }
        else
        {
            AlertDialog.Builder builder=new AlertDialog.Builder(context);
            builder.setTitle("wrong answer");
            builder.setMessage("your answer is wrong");
            builder.setPositiveButton("over", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                    if ((count+1) < quesList.size())
                    {
                        setValues(quesList.get(++count));
                        optionA.setTextColor(Color.BLACK);
                        optionB.setTextColor(Color.BLACK);
                        optionC.setTextColor(Color.BLACK);
                        optionD.setTextColor(Color.BLACK);
                    }
                    else
                    {
                        Toast.makeText(context,"questions are over", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            builder.show();
        }
    }*/


    private static final int REQ_CODE_SPEECH_INPUT = 654;
    AppCompatTextView question;
    AppCompatTextView optionA;
    AppCompatTextView optionB;
    AppCompatTextView optionC;
    AppCompatTextView optionD;

    AppCompatImageButton micImageButton;
    AppCompatImageButton nextImageButton;

    List<QuestionModel> quesList;
    Context context;
    int count;
    public UserSelectedQuestionFragment(Context context, List<QuestionModel> modelList)
    {
        this.context = context;
        this.quesList = modelList;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.user_giving_ans_fragment, container, false);
        initView(view);

        if ((count+1) < quesList.size())
            setValues(quesList.get(++count));
        else
            Toast.makeText(context, "your questions are over", Toast.LENGTH_SHORT).show();
        setClickListener();
        return view;
    }

    public void initView(View view)
    {
        question = view.findViewById(R.id.question);
        optionA = view.findViewById(R.id.option_a);
        optionB = view.findViewById(R.id.option_b);
        optionC = view.findViewById(R.id.option_c);
        optionD = view.findViewById(R.id.option_d);
        micImageButton = view.findViewById(R.id.mic_to_speak);
        nextImageButton = view.findViewById(R.id.action_next_question);

        count = -1;
    }

    public void setValues(QuestionModel model)
    {
        question.setText(model.getQuestion());
        optionA.setText(getString(R.string.opt_a) +" "+ model.getOptionA());
        optionB.setText(getString(R.string.opt_b) +" "+ model.getOptionB());
        optionC.setText(getString(R.string.opt_c) +" "+ model.getOptionC());
        optionD.setText(getString(R.string.opt_d) +" "+ model.getOptionD());
    }

    public void setClickListener()
    {
        micImageButton.setOnClickListener(this);
        nextImageButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.mic_to_speak:
                speakAnswer();
                break;

            case R.id.action_next_question:
                openNextQuestion();
                break;
        }
    }

    private void openNextQuestion()
    {
        if ((count+1) < quesList.size())
        {
            setValues(quesList.get(++count));
            optionA.setTextColor(Color.BLACK);
            optionB.setTextColor(Color.BLACK);
            optionC.setTextColor(Color.BLACK);
            optionD.setTextColor(Color.BLACK);
        }
        else
        {
            Toast.makeText(context, "questions over", Toast.LENGTH_SHORT).show();
        }

    }

    private void speakAnswer()
    {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE,Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,"speech promt");
        try
        {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        }
        catch (ActivityNotFoundException a)
        {
            Toast.makeText(context,"speech not supported",Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode)
        {
            case REQ_CODE_SPEECH_INPUT:
                if (resultCode == RESULT_OK && data != null)
                {
                    List<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    selectAnswer(result.get(0));
                }
                break;
        }
    }

    private void selectAnswer(String voiceIp)
    {
        boolean rightAnsFlag = false;
        Toast.makeText(context, voiceIp, Toast.LENGTH_SHORT).show();

        if (voiceIp.equalsIgnoreCase("option a") ||voiceIp.equalsIgnoreCase(quesList.get(count).getOptionA()))
        {
            if (quesList.get(count).getOptionA().equals(quesList.get(count).getRightAns()))
            {
                optionA.setTextColor(Color.GREEN);
                rightAnsFlag = true;
            }
            else
            {
                optionA.setTextColor(Color.RED);
                rightAnsFlag = false;
            }
        }

        else if (voiceIp.equalsIgnoreCase("option b") || voiceIp.equalsIgnoreCase(quesList.get(count).getOptionB()))
        {
            if (quesList.get(count).getOptionB().equals(quesList.get(count).getRightAns()))
            {
                optionB.setTextColor(Color.GREEN);
                rightAnsFlag = true;
            }
            else
            {
                optionB.setTextColor(Color.RED);
                rightAnsFlag = false;
            }
        }

        else if (voiceIp.equalsIgnoreCase("option c") || voiceIp.equalsIgnoreCase(quesList.get(count).getOptionC()))
        {
            if (quesList.get(count).getOptionC().equals(quesList.get(count).getRightAns()))
            {
                optionC.setTextColor(Color.GREEN);
                rightAnsFlag = true;
            }
            else
            {
                optionC.setTextColor(Color.RED);
                rightAnsFlag = false;
            }
        }
        else if (voiceIp.equalsIgnoreCase("option d") || voiceIp.equalsIgnoreCase(quesList.get(count).getOptionD()))
        {
            if (quesList.get(count).getOptionA().equals(quesList.get(count).getRightAns()))
            {
                optionD.setTextColor(Color.GREEN);
                rightAnsFlag = true;
            }
            else
            {
                optionD.setTextColor(Color.RED);
                rightAnsFlag = false;
            }
        }
        else
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Error");
            builder.setMessage("your answer was not proper");
            builder.show();
        }

        if (rightAnsFlag)
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Right Ans");
            builder.setMessage("your answer is right");
            builder.setPositiveButton(R.string.ok_button, new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface dialogInterface, int i)
                {
                    if ((count+1) < quesList.size())
                    {
                        setValues(quesList.get(++count));
                        optionA.setTextColor(Color.BLACK);
                        optionB.setTextColor(Color.BLACK);
                        optionC.setTextColor(Color.BLACK);
                        optionD.setTextColor(Color.BLACK);
                    }
                    else
                    {
                        Toast.makeText(context, "questions over", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            builder.show();
        }
        else
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Wrong Ans");
            builder.setMessage("your answer is wrong");
            builder.setPositiveButton("over buttton", new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface dialogInterface, int i)
                {
                    if ((count+1) < quesList.size())
                    {
                        setValues(quesList.get(++count));
                        optionA.setTextColor(Color.BLACK);
                        optionB.setTextColor(Color.BLACK);
                        optionC.setTextColor(Color.BLACK);
                        optionD.setTextColor(Color.BLACK);
                    }
                    else
                    {
                        Toast.makeText(context,"questions over", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            builder.show();
        }
    }
}
