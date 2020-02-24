package com.example.quiz.repos;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.quiz.App;
import com.example.quiz.model.Question;

import java.util.List;

public class QuestionRepo {

    private static final String WAS_INSERTED_KEY = "inserted_key";

    private static QuestionRepo questionRepo;
    private final QuestionDao questionDao;

    private QuestionRepo() {
        questionDao = App.getInstance().getDatabase().questionDao();
    }

    public static QuestionRepo instance() {
        if (questionRepo == null) {
            questionRepo = new QuestionRepo();
        }
        return questionRepo;
    }

    public List<Question> getQuestion() {
        return questionDao.getAll();
    }

    public void setWasInserted() {
        getPref()
                .edit()
                .putBoolean(WAS_INSERTED_KEY, true)
                .apply();
    }

    public void insert(Question question) {
        if(!getPref().getBoolean(WAS_INSERTED_KEY, false)) {
            questionDao.insert(question);
        }
    }

    public int getSize() {
        return questionDao.size();
    }

    private SharedPreferences getPref() {
        return App.getInstance().getApplicationContext()
                .getSharedPreferences(WAS_INSERTED_KEY, Context.MODE_PRIVATE);
    }
}
