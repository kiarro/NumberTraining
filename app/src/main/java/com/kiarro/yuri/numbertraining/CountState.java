package com.kiarro.yuri.numbertraining;

import java.util.Random;

public class CountState {
    private int firstNumber;
    private int secondNumber;

    private int minAddition;
    private int maxAddition;

    private int maxAttempts;
    private int attempts;

    public Statistic statistic;

    public CountState(){
        InitState();
    }

    public void Restart(){
        InitState();
    }

    private void InitState(){
        statistic = new Statistic();

        minAddition = -99;
        maxAddition = 99;
        maxAttempts = -1;

        attempts = 0;

        GenerateNext();
    }

    private boolean CompareResult(int num){
        return num == (firstNumber+secondNumber);
    }

    public void GenerateNext(){
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
            attempts = 0;
            statistic.AddRight();
        } else {
            attempts++;
            statistic.AddWrong();
            if (maxAttempts<0 || attempts<maxAttempts) {
                res = attempts;
            } else {
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

    public void setNumberBorders(int min, int max){
        minAddition = min;
        maxAddition = max;
    }

    public String getRating(){
        return statistic.GetRatingInFive();
    }

}
