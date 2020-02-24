package com.example.quiz.feature;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quiz.R;
import com.example.quiz.model.Question;
import com.example.quiz.model.Result;
import com.example.quiz.repos.QuestionRepo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GameActivity extends AppCompatActivity {

    public static final String RESULT_KEY = "result_key";

    private QuestionRepo questionRepo;

    private Result result;

    @BindView(R.id.question_text)
    TextView questionText;
    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.radio)
    RadioGroup radioGroup;
    @BindView(R.id.answer_1)
    RadioButton answer_1;
    @BindView(R.id.answer_2)
    RadioButton answer_2;
    @BindView(R.id.answer_3)
    RadioButton answer_3;
    @BindView(R.id.result_txt)
    TextView questionCountText;

    private int currentQuestion;
    private int questionCount;
    private List<Question> questionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        initRepos();
        ButterKnife.bind(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        questionList = questionRepo.getQuestion();
        currentQuestion = 0;
        questionCount = questionList.size();
        result = new Result();
        result.setAnswerCount(questionCount);
        prepareViewS(0);
    }

    private void initRepos() {
        questionRepo = QuestionRepo.instance();
    }

    private void prepareViewS(int number) {
        Question question = questionList.get(number);
        questionText.setText(question.getQuestion());
        answer_1.setText(question.getAnswer_1());
        answer_2.setText(question.getAnswer_2());
        answer_3.setText(question.getAnswer_3());
        radioGroup.clearCheck();
        String questionCuntTxt = String.format(
                getResources().getString(R.string.question_count),
                String.valueOf(currentQuestion + 1),
                String.valueOf(questionCount)
        );
        this.questionCountText.setText(questionCuntTxt);
    }

    @OnClick(R.id.next)
    public void onNextClick() {
        if (!validateAnswer()) {
            Toast.makeText(this, R.string.retry, Toast.LENGTH_SHORT).show();
            return;
        }
        ;
        currentQuestion++;
        if (currentQuestion > (questionCount - 1)) {
            finishWithResult();
        } else {
            prepareViewS(currentQuestion);
        }
    }

    private boolean validateAnswer() {
        RadioButton button = findViewById(radioGroup.getCheckedRadioButtonId());
        if (button == null) {
            return false;
        }
        Question question = questionList.get(currentQuestion);
        String answer = button.getText().toString();
        if (answer.equals(question.getTrue_answer())) {
            result.writeTrueAnswer();
        }
        return true;
    }

    private void finishWithResult() {
        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra(RESULT_KEY, result);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}
