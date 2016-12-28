package com.example.bigzhg.geoquiz;

/**
 * Created by BigZhg on 2016/11/18.
 */
public class Question {
    private int mTextResId;
    private boolean mAnswerTrue;
    // For issure: the Next/Prev button can be cleared the status of Cheater.
    private boolean mAnswerShown;

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

    // Changed it for fix that the next/prev button can be cleared the status of Cheater
    public Question(int testResId, boolean answerTrue, boolean answerShown) {
        mTextResId = testResId;
        mAnswerTrue = answerTrue;
        mAnswerShown = answerShown;
    }

    /* The following method is for fix that the next/prev button can be cleared the status of Cheater
     */
    public boolean getAnswerShown() {
        return mAnswerShown;
    }

    public void setAnswerShown(boolean answerShown) {
        mAnswerShown = answerShown;
    }

}
