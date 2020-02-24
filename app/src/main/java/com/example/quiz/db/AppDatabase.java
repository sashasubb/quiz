package com.example.quiz.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.quiz.model.Question;
import com.example.quiz.repos.QuestionDao;

@Database(entities = {Question.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract QuestionDao questionDao();
}