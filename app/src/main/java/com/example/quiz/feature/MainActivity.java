package com.example.quiz.feature;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Spanned;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quiz.R;
import com.example.quiz.repos.BestScoreRepo;
import com.example.quiz.repos.QuestionRepo;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private static final String POLICY_URL = "http://google.com";

    private BestScoreRepo bestScoreRepo;
    private QuestionRepo questionRepo;

    @BindView(R.id.best_score)
    TextView bestScoreText;
    @BindView(R.id.privacy_policy)
    TextView privacyPolicy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initRepo();
        ButterKnife.bind(this);
        initViews();
    }

    private void initRepo() {
        bestScoreRepo = BestScoreRepo.instance();
        questionRepo = QuestionRepo.instance();
    }

    private void prepareBestScore() {
        String bestScore = String.format(
                getResources().getString(R.string.best_score_text),
                bestScoreRepo.getBestScore().toString(),
                String.valueOf(questionRepo.getSize())
        );
        bestScoreText.setText(bestScore);
    }

    private void initViews() {
        prepareBestScore();
        String htmlTaggedString = "<u>" + getResources().getString(R.string.privacy_policy) + "</u>";
        Spanned textSpan = android.text.Html.fromHtml(htmlTaggedString);
        privacyPolicy.setText(textSpan);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        prepareBestScore();
    }

    @OnClick(R.id.privacy_policy)
    public void onPolicyClick() {
        Uri uri = Uri.parse(POLICY_URL);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    @OnClick(R.id.start)
    public void onStartClick() {
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }

}
