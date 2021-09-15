package com.kiarro.yuri.numbertraining;

import android.util.Pair;
import android.os.Bundle;

public class Statistic {
    // array with bottom of diapason
    private Pair<Double, String>[] Rates = new Pair[]{
            new Pair(0.98, "5+"),
            new Pair(0.90, "5"),
            new Pair(0.85, "5-"),
            new Pair(0.80, "4+"),
            new Pair(0.75, "4-"),
            new Pair(0.70, "3+"),
            new Pair(0.60, "3"),
            new Pair(0.50, "3-"),
            new Pair(0.40, "2+"),
            new Pair(0.30, "2"),
            new Pair(0.00, "2-")
    };


    private int allAnswers = 0;
    private int rightAnswers = 0;

    public void AddWrong(){
        allAnswers++;
    }
    public void AddRight(){
        allAnswers++;
        rightAnswers++;
    }
    public int GetAllAnswers(){
        return allAnswers;
    }
    public int GetRightAnswers(){
        return rightAnswers;
    }
    public double GetRatingInPercent(){
        if (allAnswers>0){
            return (double)rightAnswers/allAnswers;
        } else {
            return 0;
        }
    }
    public String GetRatingInFive(){
        String res = null;
        if (allAnswers<5){
            return res;
        }
        double rate = GetRatingInPercent();
        for (int i=0; i<Rates.length; i++){
            if (Rates[i].first<=rate) {
                res = Rates[i].second;
                break;
            }
        }
        return res;
    }

}
