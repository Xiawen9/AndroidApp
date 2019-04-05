package com.xiawen.geoquiz;

public class Question {
    private int mTextResId;
    private boolean mAnswerTrue;
    private int isAnswerd;

    public Question(int mTextResId, boolean mAnswerTrue, int isAnswerd){
        this.mTextResId = mTextResId;
        this.mAnswerTrue = mAnswerTrue;
        this.isAnswerd = isAnswerd;
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

    public boolean isAnswerTrue() {
        return mAnswerTrue;
    }

    public void setAnswerTrue(boolean answerTrue) {
        mAnswerTrue = answerTrue;
    }
}
