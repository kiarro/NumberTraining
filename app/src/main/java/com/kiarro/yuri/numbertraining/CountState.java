package com.kiarro.yuri.numbertraining;

import java.util.Random;

public class CountState {
    private int firstNumber;
    private int secondNumber;

    private int minAddition;
    private int maxAddition;

    private int maxAttempts;
    private int attempts;


    public CountState(){
        InitState();
    }

    public void Restart(){
        InitState();
    }

    private void InitState(){
        minAddition = -10;
        maxAddition = 10;
        maxAttempts = 3;

        attempts = 0;

        GenerateNext();
    }

    private boolean CompareResult(int num){
        return num == (firstNumber+secondNumber);
    }

    private void GenerateNext(){
        firstNumber = (new Random()).nextInt(maxAddition-minAddition)+minAddition;
        secondNumber = (new Random()).nextInt(maxAddition-minAddition)+minAddition;

    }


    /**
     * @param num
     * @return
     * 0  - right answer
     * -1 - wrong answer. number of attempts bigger then max attempts
     * >0 - wrong answer. attempt number
     */
    public int CheckResult(int num){
        int res=0;
        boolean check = CompareResult(num);
        if (check) {
            res = 0;
            GenerateNext();
            attempts = 0;
        } else {
            attempts++;
            if (attempts<maxAttempts) {
                res = attempts;
            } else {
                InitState();
                attempts=0;
                res = -1;
            }
        }
        return res;
    }


    public String getStatement(){
        String res = String.valueOf(firstNumber);
        if (secondNumber>=0) {
            res += "+";
        }
        res += String.valueOf(secondNumber);
        res += "=";
        return res;
    }

    public int getAttempts(){ return attempts; }
    public int getMaxAttempts(){ return maxAttempts; }

}
