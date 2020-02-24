package com.example.quiz.repos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.quiz.model.Question;

import java.util.List;

@Dao
public interface QuestionDao {

    @Query("SELECT * FROM question")
    List<Question> getAll();

    @Query("SELECT COUNT(*) FROM question")
    Integer size();

    @Query("SELECT * FROM question WHERE id = :id")
    Question getById(long id);

    @Insert
    void insert(Question question);

    @Update
    void update(Question question);

    @Delete
    void delete(Question question);
}
