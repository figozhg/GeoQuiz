package com.example.bigzhg.geoquiz;

import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {

    private static final String EXTRA_ANSWER_IS_TRUE =
            "com.example.bigzhg.geoquiz.answer_is_ture";
    private static final String EXTRA_ANSWER_SHOWN =
            "com.example.bigzhg.geoquiz.answer_shown";
    // For saving ANSWER IS SHOWN when the Screen is from Landscape to portrait
    private static final String KEY_ANSWER_SHOWN = "wasShown";

    private boolean mAnswerShown = false;

    private boolean mAnswerIsTrue;

    private TextView mAnswerTextView;
    //private Button mShowAnswer;

    private static final String TAG = "CheatActivity";

    public static Intent newIntent(Context pachageContext, boolean answerIsTrue) {
        Intent i = new Intent(pachageContext, CheatActivity.class);
        i.putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue);
        return i;
    }

    public static boolean wasAnswerShown(Intent result) {
        return result.getBooleanExtra(EXTRA_ANSWER_SHOWN, false);
    }

    private void updateShowAnswer() {
        if (mAnswerShown) {
            if (mAnswerIsTrue) {
                mAnswerTextView.setText(R.string.true_button);
            } else {
                mAnswerTextView.setText(R.string.false_button);
            }
            setAnswerShownResult(true);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE,false);

        mAnswerTextView = (TextView) findViewById(R.id.answer_text_view);

        Button mShowAnswer = (Button) findViewById(R.id.show_answer_button);
        mShowAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                if (mAnswerIsTrue) {
                    mAnswerTextView.setText(R.string.true_button);
                } else {
                    mAnswerTextView.setText(R.string.false_button);
                }
                setAnswerShownResult(true);
                */
                mAnswerShown = true;
                updateShowAnswer();
            }
        });

        // For Saving mAnswerIsTrue when the Screen is from Landscape to portrait
        if (savedInstanceState != null) {
            mAnswerShown = savedInstanceState.getBoolean(KEY_ANSWER_SHOWN, false);
        }

        updateShowAnswer();
    }

    // For Saving mAnswerIsTrue when the Screen is from Landscape to portrait
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "onSave: mAnswerIsTrue");
        savedInstanceState.putBoolean(KEY_ANSWER_SHOWN, mAnswerShown);
    }

    private void setAnswerShownResult(boolean isAnswerShown) {
        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown);
        setResult(RESULT_OK, data);
    }
}



