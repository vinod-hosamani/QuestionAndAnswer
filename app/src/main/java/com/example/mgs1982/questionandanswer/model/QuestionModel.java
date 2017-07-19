package com.example.mgs1982.questionandanswer.model;

/**
 * Created by MGS1982 on 7/19/2017.
 */

public class QuestionModel
{
    private int quesId;
    private String question;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private String rightAns;

    public int getQuesId() {
        return quesId;
    }

    public String getQuestion() {
        return question;
    }

    public String getOptionA() {
        return optionA;
    }

    public String getOptionB() {
        return optionB;
    }

    public String getOptionC() {
        return optionC;
    }

    public String getOptionD() {
        return optionD;
    }

    public String getRightAns() {
        return rightAns;
    }

    public void setQuesId(int quesId) {
        this.quesId = quesId;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setOptionA(String optionA) {
        this.optionA = optionA;
    }

    public void setOptionB(String optionB) {
        this.optionB = optionB;
    }

    public void setOptionC(String optionC) {
        this.optionC = optionC;
    }

    public void setOptionD(String optionD) {
        this.optionD = optionD;
    }

    public void setRightAns(String rightAns) {
        this.rightAns = rightAns;
    }
}
