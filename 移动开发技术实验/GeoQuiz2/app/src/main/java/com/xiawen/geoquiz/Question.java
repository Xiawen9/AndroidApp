package com.xiawen.geoquiz;

public class Question {
    private int mTextResId;
    private String mQuestionText;
    private boolean mAnswerTrue;
    private int isAnswerd;

    public Question(int mTextResId, String mQuestionText,boolean mAnswerTrue, int isAnswerd){
        this.mTextResId = mTextResId;
        this.mQuestionText = mQuestionText;
        this.mAnswerTrue = mAnswerTrue;
        this.isAnswerd = isAnswerd;
    }

    public String getQuestionText() {
        return mQuestionText;
    }

    public void setQuestionText(String questionText) {
        mQuestionText = questionText;
    }

    public int getIsAnswerd() {
        return isAnswerd;
    }

    public void setIsAnswerd(int isAnswerd) {
        this.isAnswerd = isAnswerd;
    }

    public int getTextResId() {
        return mTextResId;
    }

    public void setTextResId(int textResId) {
        mTextResId = textResId;
    }

    public Question(int textResId, boolean answerTrue) {
        mTextResId = textResId;
        mAnswerTrue = answerTrue;

    }

    public boolean isAnswerTrue() {
        return mAnswerTrue;
    }

    public void setAnswerTrue(boolean answerTrue) {
        mAnswerTrue = answerTrue;
    }
}
