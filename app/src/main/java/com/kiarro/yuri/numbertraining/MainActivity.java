package com.kiarro.yuri.numbertraining;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    CountState state;

    TextView TxtStatement;
    TextView TxtWResult;
    EditText EdtResult;
    TextView TxtAttempts;
    ImageView ImgWrong;
    TextView TxtRating;
    Button BtnCheck;
    TextView TxtAgain;

    AnimatorSet animWrongAnswer;

    boolean isCompareAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InitElements();
        Init();

        EdtResult.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i==KeyEvent.KEYCODE_ENDCALL){
                    Check(textView);

                    return true;
                }

                return false;
            }
        });

    }

    protected void InitElements(){
        TxtStatement = (TextView)findViewById(R.id.statement);
        TxtWResult = (TextView)findViewById(R.id.Wresult);
        EdtResult = (EditText)findViewById(R.id.result);
        TxtAttempts = (TextView)findViewById(R.id.attempts);
        ImgWrong = (ImageView)findViewById(R.id.imgWrong);
        TxtRating = (TextView)findViewById(R.id.rating);
        BtnCheck = (Button)findViewById(R.id.btnSend);
        TxtAgain = (TextView)findViewById(R.id.txt_again);

        ResetWrongAnswer();
    }

    protected void Init(){
        state = new CountState();
        isCompareAnswer = true;

        UpdateNumbers();
        UpdateAttempts();
    }

    public void Check(View v){
        if (!isCompareAnswer) {
            generateNext();
            ResetWrongAnswer();
            return;
        }

        int num;
        try {
            num = Integer.parseInt(EdtResult.getText().toString());
        } catch(Exception e){
            return;
        }
        int res = state.CheckResult(num);
        if (res==0) {
            // right answer handler
            SetRightAnswer(String.valueOf(num));

//            UpdateNumbers();
        }
        if (res>0) {
            // wrong answer handler
            SetWrongAnswer(String.valueOf(num));
        }
        if (res==-1){
            // too much attempts handler
            UpdateNumbers();
            ResetWrongAnswer();
        }
        EdtResult.setText("");
        UpdateAttempts();
    }

    public void UpdateNumbers(){
        TxtStatement.setText(state.getStatement());
    }

    public void UpdateAttempts(){
        int i1 = state.getAttempts();
        int i2 = state.getMaxAttempts();

        TxtAttempts.setText(i1+" / "+i2);

        String rate = state.getRating();
        String r;
        if (rate == null){
            r = getString(R.string.too_small);
        } else {
            r = getString(R.string.rating) + rate;
        }
        TxtRating.setText(r);
    }

    private void SetWrongAnswer(String txt){
        isCompareAnswer = true;
        TxtWResult.setText(txt);
        ImgWrong.setImageResource(R.drawable.ic_nok);
        ImgWrong.setAlpha(1f);

        BtnCheck.setText(R.string.btn_send);
        TxtAgain.setVisibility(View.VISIBLE);
    }

    private void ResetWrongAnswer(){
        isCompareAnswer = true;
        TxtWResult.setText("");
        ImgWrong.setAlpha(0f);

        BtnCheck.setText(R.string.btn_send);
        TxtAgain.setVisibility(View.INVISIBLE);
    }

    private void SetRightAnswer(String txt){
        isCompareAnswer = false;
        TxtWResult.setText(txt);
        ImgWrong.setImageResource(R.drawable.ic_ok);
        ImgWrong.setAlpha(1f);

        BtnCheck.setText(R.string.btn_next);
        TxtAgain.setVisibility(View.INVISIBLE);
    }

    private void generateNext(){
        state.GenerateNext();
        UpdateNumbers();
    }


}