package com.example.quiz;

import android.app.Application;

import androidx.room.Room;

import com.example.quiz.db.AppDatabase;
import com.example.quiz.model.Question;
import com.example.quiz.repos.QuestionDao;
import com.example.quiz.repos.QuestionRepo;

public class App extends Application {

    public static App instance;

    private AppDatabase database;
    private QuestionRepo questionRepo;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        database = Room.databaseBuilder(this, AppDatabase.class, "database")
                .allowMainThreadQueries()
                .build();
        questionRepo = QuestionRepo.instance();
        insert();
    }

    public static App getInstance() {
        return instance;
    }

    public AppDatabase getDatabase() {
        return database;
    }

    private void insert() {
        questionRepo.insert(new Question(1,
                "Question Text 1",
                "Answer 1",
                "Answer 2",
                "Answer 3",
                "Answer 2"));
        questionRepo.insert(new Question(2,
                "Question Text 2",
                "Answer 1",
                "Answer 2",
                "Answer 3",
                "Answer 3"));
        questionRepo.insert(new Question(3,
                "Question Text 3",
                "Answer 1",
                "Answer 2",
                "Answer 3",
                "Answer 1"));
        questionRepo.setWasInserted();
    }
}
