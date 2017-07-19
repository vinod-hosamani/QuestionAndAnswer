package com.example.mgs1982.questionandanswer.util;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.mgs1982.questionandanswer.model.QuestionModel;

import java.util.ArrayList;
import java.util.List;

public class QuestionDao extends SQLiteOpenHelper
{
    public static final int DB_VERSION=1;
    public static final String QUES_DB_NAME="question_mgr";
    public static final String QUES_TBL_NAME = "question_tbl";
    public static final String KEY_ID = "ques_id";
    public static final String KEY_QUES = "question";
    public static final String KEY_OPT_A = "option_A";
    public static final String KEY_OPT_B = "option_B";
    public static final String KEY_OPT_C = "option_C";
    public static final String KEY_OPT_D = "option_D";
    public static final String KEY_RIGHT_ANS = "right_Ans";

public QuestionDao(Context context)
{
    super(context,QUES_DB_NAME,null,DB_VERSION);
}

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String createTblQuery="create table"+QUES_TBL_NAME+"("+
                ""+KEY_ID+"INTEGER PRIMARY KEY AUTOINCREMENT,"+
                ""+KEY_QUES+"TEXT,"+
                ""+KEY_OPT_A+"TEXT,"+
                ""+KEY_OPT_B+"TEXT,"+
                ""+KEY_OPT_C+"TEXT,"+
                ""+KEY_OPT_D+"TEXT,"+
                ""+KEY_RIGHT_ANS+"TEXT)";
        db.execSQL(createTblQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS"+QUES_TBL_NAME);
            onCreate(db);

    }

    public void addQuestion(QuestionModel model)
    {
        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues values=new ContentValues();
        values.put(KEY_QUES,model.getQuestion());
        values.put(KEY_OPT_A,model.getOptionA());
        values.put(KEY_OPT_B,model.getOptionB());
        values.put(KEY_OPT_C,model.getOptionC());
        values.put(KEY_OPT_D,model.getOptionD());
        values.put(KEY_RIGHT_ANS,model.getRightAns());
        db.insert(QUES_TBL_NAME,null,values);
        db.close();
    }

    public List<QuestionModel> getAllQuestions()
    {
        List<QuestionModel> questionList=new ArrayList<>();
        String selectQuery="SELECT * FROM "+QUES_TBL_NAME;

        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery(selectQuery,null);
        if(cursor.moveToFirst())
        {
            do
            {
                QuestionModel model=new QuestionModel();
                        model.setQuesId(cursor.getInt(0));
                        model.setQuestion(cursor.getString(1));
                        model.setOptionA(cursor.getString(2));
                        model.setOptionB(cursor.getString(3));
                        model.setOptionC(cursor.getString(4));
                        model.setOptionD(cursor.getString(5));
                        model.setRightAns(cursor.getString(6));

                questionList.add(model);
              }
            while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return questionList;
    }
}