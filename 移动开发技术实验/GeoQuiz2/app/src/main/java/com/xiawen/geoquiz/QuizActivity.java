package com.xiawen.geoquiz;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

import static android.view.Gravity.TOP;

public class QuizActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "QuizActivity";
    private static final String KEY_INDEX = "index";
    private static final int REQUEST_CODE_CHEAT = 0;
    private Button mTrueButton;
    private Button mFalseButton;
    private Button mCheatButton;
    private Button mEnglishButton;
    private Button mChineseButton;
    private TextView mQuestionTextView;
    private ImageButton mNextButton;
    private ImageButton mPrevButton;
    private double correctAnswer = 0;
    private int answerLength = 0;
   // private boolean mCheater = false;
    private static final  String bug2 = "bug2";
    private static final  String bug3 = "bug3";
    private ContentResolver mResolver;
    private Uri muri;
    private int overScore;

    private Question[] mQuestionBank = new Question[]{  };
    private boolean[] mIsCheater ;
    private  static final String  KEY_ANSWER = "KEY_ANSWER";
    private int mCurrentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate(Bundle) called");
        setContentView(R.layout.activity_quiz);

        mQuestionBank = new Question[6];
        QuizDBOpenHelper helper = new QuizDBOpenHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();
        muri = Uri.parse("content://com.xiawen.geoquiz/info");
        mResolver = getContentResolver();
        int j = 0;
        Cursor cursor = mResolver.query(muri, new String[]{"TextResId","TextQuestion","answer","isAnswerd"},
                null,null,null);
        while(cursor.moveToNext()){
            boolean qst = true;
            if(cursor.getString(2).equals("true")) qst = true;
            else qst = false;
            Question qs = new Question(j, cursor.getString(1),qst, cursor.getInt(2));
            mQuestionBank[j++] = qs;
        }
        cursor.close();

        mIsCheater = new boolean[mQuestionBank.length];

        mEnglishButton = (Button) findViewById(R.id.English_button);
        mChineseButton = (Button) findViewById(R.id.Chinese_button);
        mEnglishButton.setOnClickListener(this);
        mChineseButton.setOnClickListener(this);

        for (int i = 0; i < mQuestionBank.length; i++)
            mIsCheater[i] = false;
        if (savedInstanceState != null) {
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
            int[] answerList = savedInstanceState.getIntArray(KEY_ANSWER);
            for(int i = 0; i < mQuestionBank.length; i++){
                mQuestionBank[i].setIsAnswerd(answerList[i]);
            }
            //mCheater = savedInstanceState.getBoolean(bug2,false);
            mIsCheater = savedInstanceState.getBooleanArray(bug3);
        }
        mPrevButton = (ImageButton)findViewById(R.id.pre_button);
        mNextButton = (ImageButton)findViewById(R.id.next_button);
        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
        mTrueButton = (Button) findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
            }
        });
        mFalseButton = (Button) findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                //mIsCheater[mCurrentIndex] = false;
                answerLength++;
                //mIsCheater[mCurrentIndex] = false;
                System.out.println("回答的问题数"+answerLength);
                System.out.println("回答"+mQuestionBank.length);
                if(answerLength==mQuestionBank.length){
                    overScore = (int)correctAnswer*10;
                    mTrueButton.setEnabled(false);
                    mFalseButton.setEnabled(false);
                    mNextButton.setEnabled(false);
                    mPrevButton.setEnabled(false);
                    mCheatButton.setEnabled(false);
                    changeActivity();
                }
                updateQuestion();
            }
        });
        mCheatButton = (Button)findViewById(R.id.cheat_button);
        mCheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIsCheater[mCurrentIndex] = true;
                boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();
                Intent intent = CheatActivity.newIntent(QuizActivity.this, answerIsTrue);
                startActivityForResult(intent, REQUEST_CODE_CHEAT);
            }
        });
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
            mIsCheater[mCurrentIndex] = CheatActivity.wasAnswerShown(data);
            //mIsCheater = CheatActivity.wasAnswerShown(data);
        }
    }
    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() called");
    }
    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");
    }
    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called");
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG, "onSaveInstanceState");
        outState.putInt(KEY_INDEX,mCurrentIndex);
        int [] answerList = new int[mQuestionBank.length];
        for(int i = 0; i < answerList.length; i++){
            answerList[i] = mQuestionBank[i].getIsAnswerd();
        }
        outState.putInt(KEY_INDEX, mCurrentIndex);
        outState.putIntArray(KEY_ANSWER,answerList);
        //outState.putBoolean(bug2, mIsCheater);
        outState.putBooleanArray(bug3,mIsCheater);
    }
    public  void ButtonEnabled(){
        if(mQuestionBank[mCurrentIndex].getIsAnswerd()!=0){
            mTrueButton.setEnabled(false);
            mFalseButton.setEnabled(false);
        }else {
            mTrueButton.setEnabled(true);
            mFalseButton.setEnabled(true);
        }
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
    private void updateQuestion() {
        String question = mQuestionBank[mCurrentIndex].getQuestionText();
        mQuestionTextView.setText(question);
        ButtonEnabled();
    }
    private void checkAnswer(boolean userPressedTrue) {
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();
        int messageResId = 0;
        mQuestionBank[mCurrentIndex].setIsAnswerd(1);
        if (mIsCheater[mCurrentIndex])
            messageResId = R.string.judgment_toast;
        else {
            if (userPressedTrue == answerIsTrue) {
                mQuestionBank[mCurrentIndex].setIsAnswerd(1);
                messageResId = R.string.correct_toast;
                correctAnswer++;
            } else {
                mQuestionBank[mCurrentIndex].setIsAnswerd(-1);
                messageResId = R.string.incorrect_toast;
            }
        }
        ButtonEnabled();
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onClick(View v) {
        System.out.println("点击*"+v.getId()+"  点击**"+R.id.Chinese_button);
        if(v.getId() == R.id.Chinese_button)
            changeAppLanguage(Locale.SIMPLIFIED_CHINESE);
        else changeAppLanguage(Locale.US);
    }
    public void changeActivity(){
        Intent intent = new Intent(this,OverActivity.class);
        intent.putExtra("score",overScore);
        System.out.println("得分1："+overScore);
        startActivity(intent);
    }

    public void changeAppLanguage(Locale locale) {
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        Configuration configuration = getResources().getConfiguration();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            configuration.setLocale(locale);
        } else {
            configuration.locale = locale;
        }
        getResources().updateConfiguration(configuration, metrics);
        Intent intent = new Intent(this, QuizActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
