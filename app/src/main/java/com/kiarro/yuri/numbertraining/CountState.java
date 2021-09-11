package com.kiarro.yuri.numbertraining;

import java.util.Random;

public class CountState {
    private int currentNumber;
    private int additionNumber;
    private int resultNumber;

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

        currentNumber = 0;
        additionNumber = 0;
        resultNumber = 0;
    }

    private boolean CompareResult(int num){
        return num == resultNumber;
    }

    private void GenerateNext(){
        additionNumber = (new Random()).nextInt(maxAddition-minAddition)+minAddition;
        resultNumber = currentNumber+additionNumber;
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
                attempts=0;
                res = -1;
            }
        }
        return res;
    }


    public int getCurrentNumber(){
        return currentNumber;
    }
    public int getAdditionNumber(){
        return additionNumber;
    }

}