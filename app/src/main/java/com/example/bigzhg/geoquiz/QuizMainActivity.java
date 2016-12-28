package com.example.bigzhg.geoquiz;

//import android.provider.ContactsContract;
import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


public class QuizMainActivity extends AppCompatActivity {

    // private Button mTrueButton;
    // private Button mFalseButton;
    // private Button mNextButton;
    private TextView mQuestionTextView;

    // Added it for Log
    private static final String TAG = "QuizActivity";
    // For Saving index: mCurrentIndex
    private static final String KEY_INDEX = "index";
    // For saving mIsCheater
    private static final String KEY_IS_CHEATER = "isCheater";

    private static final int REQUEST_CODE_CHEAT = 0;

    private Question[] mQuestionBank = new Question[] {
            new Question(R.string.question_oceans, true, false),
            new Question(R.string.question_mideast, false, false),
            new Question(R.string.question_africa, false, false),
            new Question(R.string.question_americas, true, false),
            new Question(R.string.question_asia, true, false),
    };

    //private boolean mIsCheater = false;

    private int mCurrentIndex = 0;

    private void updateQuestion() {
        int question = mQuestionBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);
    }

    private void checkAnswer(boolean userPressedTrue) {
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();

        int messageResId;

        //if (mIsCheater) {
        if (mQuestionBank[mCurrentIndex].getAnswerShown()) {
            messageResId = R.string.judgment_toast;
        } else {
            if (userPressedTrue == answerIsTrue) {
                messageResId = R.string.correct_toast;
            } else {
                messageResId = R.string.incorrect_toast;
            }
        }

        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate(Bundle) called");  // For Log
        setContentView(R.layout.activity_quiz_main);

        mQuestionTextView = (TextView)findViewById(R.id.question_text_view);
        // int question = mQuestionBank[mCurrentIndex].getTextResId();
        // mQuestionTextView.setText(question);
        /*
         * Click arae of Question Text, jump to next question!
         */
        mQuestionTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                // int question = mQuestionBank[mCurrentIndex].getTextResId();
                // mQuestionTextView.setText(question);
                updateQuestion();
            }
        });

        Button mTrueButton = (Button) findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
            }
        });

        Button mFalseButton = (Button)findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });

        ImageButton mNextButton = (ImageButton)findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                // int question = mQuestionBank[mCurrentIndex].getTextResId();
                // mQuestionTextView.setText(question);
                // mIsCheater = false;
                updateQuestion();
            }
        });

        ImageButton mPrevButton = (ImageButton)findViewById(R.id.prev_button);
        mPrevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (--mCurrentIndex < 0) {
                    mCurrentIndex = mQuestionBank.length - 1;
                }
                // int question = mQuestionBank[mCurrentIndex].getTextResId();
                // mQuestionTextView.setText(question);
                // mIsCheater = false;
                updateQuestion();
            }
        });

        Button mCheatButton = (Button)findViewById(R.id.cheat_button);
        mCheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start CheatActivity
                // Intent i = new Intent(QuizMainActivity.this, CheatActivity.class);
                boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();
                Intent i = CheatActivity.newIntent(QuizMainActivity.this, answerIsTrue);
                //startActivity(i);
                startActivityForResult(i, REQUEST_CODE_CHEAT);
            }
        });

        // For Saving mCurrentIndex & mIsCheater when the Screen is from Landscape to portrait
        if (savedInstanceState != null) {
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
            //mIsCheater = savedInstanceState.getBoolean(KEY_IS_CHEATER, false);
            mQuestionBank[mCurrentIndex].setAnswerShown(
                    savedInstanceState.getBoolean(KEY_IS_CHEATER, false));
        }

        updateQuestion();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (requestCode == REQUEST_CODE_CHEAT) {
            if (data == null) {
                return;
            }
            //mIsCheater = CheatActivity.wasAnswerShown(data);
            mQuestionBank[mCurrentIndex].setAnswerShown(CheatActivity.wasAnswerShown(data));
        }
    }

    // For Saving mCurrentIndex & mIsCheater when the Screen is from Landscape to portrait
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "onSaveInstanceState");
        savedInstanceState.putInt(KEY_INDEX, mCurrentIndex);
        //savedInstanceState.putBoolean(KEY_IS_CHEATER, mIsCheater);
        savedInstanceState.putBoolean(
                KEY_IS_CHEATER,
                mQuestionBank[mCurrentIndex].getAnswerShown());
    }

    // For TEST activity lifecycle
    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() called");
    }
    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called");
    }
    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");
    }
    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called");
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }
}
