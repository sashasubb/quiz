package com.example.quiz.feature;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quiz.R;
import com.example.quiz.model.Result;
import com.example.quiz.repos.BestScoreRepo;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ResultActivity extends AppCompatActivity {

    private Result result;

    private BestScoreRepo bestScoreRepo;

    @BindView(R.id.result)
    TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Intent intent = getIntent();
        result = (Result) intent.getSerializableExtra(GameActivity.RESULT_KEY);
        bestScoreRepo = BestScoreRepo.instance();
        ButterKnife.bind(this);
        prepareResult();
    }

    private void prepareResult() {
        String resultText = String.format(
                getResources().getString(R.string.result),
                String.valueOf(result.getTrueAnswerCount()),
                String.valueOf(result.getAnswerCount())
        );
        this.resultText.setText(resultText);
    }

    @Override
    protected void onStart() {
        super.onStart();
        int lastResult = bestScoreRepo.getBestScore();
        int nowResult = result.getTrueAnswerCount();
        if (lastResult < nowResult) {
            bestScoreRepo.setBestScore(nowResult);
        }
    }

    @OnClick(R.id.try_again)
    public void onTryAgainClick() {
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }
}
