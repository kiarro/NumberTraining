package com.kiarro.yuri.numbertraining;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    CountState state;

    TextView TxtPrevious;
    TextView TxtAddition;
    EditText EdtResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Init();
    }

    protected void Init(){
        state = new CountState();

        TxtPrevious = (TextView)findViewById(R.id.previousNum);
        TxtAddition = (TextView)findViewById(R.id.additionNum);
        EdtResult = (EditText)findViewById(R.id.result);
    }

    public void Check(View v){
        int num = Integer.parseInt(EdtResult.getText().toString());
        int res = state.CheckResult(num);
        if (res==0) {
            // right answer handler
            TxtPrevious.setText(state.getCurrentNumber());
            TxtAddition.setText(state.getAdditionNumber());
        }
        if (res>0) {
            // wrong answer handler
        }
        if (res==-1){
            // too much attempts handler
            TxtPrevious.setText(state.getCurrentNumber());
            TxtAddition.setText(state.getAdditionNumber());

        }
    }
}