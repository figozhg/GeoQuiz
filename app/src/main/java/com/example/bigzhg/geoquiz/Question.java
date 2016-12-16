package com.example.bigzhg.geoquiz;

/**
 * Created by BigZhg on 2016/11/18.
 */
public class Question {
    private int mTextResId;
    private boolean mAnswerTrue;

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

    public Question(int testResId, boolean answerTrue) {
        mTextResId = testResId;
        mAnswerTrue = answerTrue;
    }
}
