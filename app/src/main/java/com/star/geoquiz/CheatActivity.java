package com.star.geoquiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class CheatActivity extends Activity {

    public static final String EXTRA_ANSWER_IS_TRUE = "com.star.geoquiz.answer_is_true";
    public static final String EXTRA_ANSWER_SHOWN = "com.star.geoquiz.answer_shown";

    private boolean mAnswerIsTrue;

    private boolean mAnswerShown;

    private TextView mAnswerTextView;
    private Button mShowAnswerButton;

    private TextView mShowCurrentApiTextView;

    private void setAnswerShownResult(boolean isAnswerShown) {
        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown);
        setResult(RESULT_OK, data);
    }

    private void updateCheating() {
        if (mAnswerIsTrue) {
            mAnswerTextView.setText(R.string.true_button);
        } else {
            mAnswerTextView.setText(R.string.false_button);
        }
        mAnswerShown = true;
        setAnswerShownResult(true);
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        savedInstanceState.putBoolean(EXTRA_ANSWER_IS_TRUE, mAnswerIsTrue);
        savedInstanceState.putBoolean(EXTRA_ANSWER_SHOWN, mAnswerShown);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        setAnswerShownResult(false);

        mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);

        mAnswerTextView = (TextView) findViewById(R.id.answerTextView);
        mShowAnswerButton = (Button) findViewById(R.id.showAnswerButton);

        mShowCurrentApiTextView = (TextView) findViewById(R.id.showCurrentApiTextView);
        mShowCurrentApiTextView.setText("API level " + Build.VERSION.SDK_INT);

        mShowAnswerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateCheating();
            }
        });

        if (savedInstanceState != null) {
            mAnswerIsTrue = savedInstanceState.getBoolean(EXTRA_ANSWER_IS_TRUE);
            mAnswerShown = savedInstanceState.getBoolean(EXTRA_ANSWER_SHOWN);
            if (mAnswerShown) {
                updateCheating();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.cheat, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
