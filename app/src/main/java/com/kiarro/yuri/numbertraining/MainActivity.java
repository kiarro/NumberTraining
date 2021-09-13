package com.kiarro.yuri.numbertraining;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
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

    AnimatorSet animWrongAnswer;

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


    }

    protected void Init(){
        state = new CountState();

        UpdateNumbers();
        UpdateAttempts();
    }

    public void Check(View v){
        int num;
        try {
            num = Integer.parseInt(EdtResult.getText().toString());
        } catch(Exception e){
            return;
        }
        int res = state.CheckResult(num);
        if (res==0) {
            // right answer handler
            ImgWrong.setVisibility(View.INVISIBLE);
            UpdateNumbers();
        }
        if (res>0) {
            // wrong answer handler
//            animWrongAnswer.start();
            ImgWrong.setVisibility(View.VISIBLE);
        }
        if (res==-1){
            // too much attempts handler
            UpdateNumbers();
//            animWrongAnswer.start();
            ImgWrong.setVisibility(View.INVISIBLE);
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
    }


}