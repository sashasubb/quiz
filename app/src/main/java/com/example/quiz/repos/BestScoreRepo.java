package com.example.quiz.repos;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.quiz.App;

public class BestScoreRepo {

    private static BestScoreRepo bestScoreRepo;

    public static final String BEST_SCORE_KEY = "best_score";

    private final Context context;

    public BestScoreRepo(Context context) {
        this.context = context;
    }

    public static BestScoreRepo instance() {
        if (bestScoreRepo == null) {
            bestScoreRepo = new BestScoreRepo(App.getInstance().getApplicationContext());
        }
        return bestScoreRepo;
    }

    public void setBestScore(Integer bestScore) {
        getPref().edit()
                .putInt(BEST_SCORE_KEY, bestScore)
                .apply();
    }

    public Integer getBestScore() {
        return getPref().getInt(BEST_SCORE_KEY, 0);
    }

    private SharedPreferences getPref() {
        return context.getSharedPreferences(BEST_SCORE_KEY, Context.MODE_PRIVATE);
    }
}
