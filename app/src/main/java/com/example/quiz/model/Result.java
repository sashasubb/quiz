package com.example.quiz.model;

import java.io.Serializable;

public class Result implements Serializable {
    private int trueAnswerCount;
    private int answerCount;

    public Result () {
        trueAnswerCount = 0;
    }

    public int getTrueAnswerCount() {
        return trueAnswerCount;
    }

    public void writeTrueAnswer () {
        trueAnswerCount++;
    }

    public int getAnswerCount() {
        return answerCount;
    }

    public void setAnswerCount(int answerCount) {
        this.answerCount = answerCount;
    }
}
