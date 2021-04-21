package com.saihtoo.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity implements View.OnClickListener
{
    //Variables
    final int MAX_QUESTION_NUM = 5;
    int submitClick = 1, questionCount = 1, correctCount;
    String userName;
    String selectedOption;
    ProgressBar progress;
    TextView welcomeText, questionText;
    Button option1, option2, option3, submitBtn, selectedButton;

    //Initialise the Question class
    Question q = new Question();

    //Method to get the correct answer from the Question class
    public String getAnswer(int questionNum) {
        return q.getAnswer(questionNum);
    }

    //Method to get and display Question set from the Question class
    public void getQuestionSet(int questionNum) {
        questionText.setText(q.getQuestion(questionNum));
        option1.setText(q.getOption(questionNum, 1));
        option2.setText(q.getOption(questionNum, 2));
        option3.setText(q.getOption(questionNum, 3));
    }

    //Method to display the Correct and Wrong answer
    public void displayCorrectAns(int questionNum) {
        if (!selectedOption.equals(getAnswer(questionNum)))
            selectedButton.setBackgroundColor(getResources().getColor(R.color.design_default_color_error));
        getCorrectBtn(questionNum).setBackgroundColor(getResources().getColor(R.color.green));
    }

    //Method to get the Correct button in relation to the answer retrieved from the Question class
    public Button getCorrectBtn(int questionNum) {
        if (option1.getText().toString().equals(getAnswer(questionNum)))
            return option1;
        else if (option2.getText().toString().equals(getAnswer(questionNum)))
            return option2;
        else
            return option3;
    }

    //Method to reset all button colors
    public void resetButton() {
        option1.setBackgroundColor(getResources().getColor(R.color.purple_500));
        option2.setBackgroundColor(getResources().getColor(R.color.purple_500));
        option3.setBackgroundColor(getResources().getColor(R.color.purple_500));
    }

    //Method to display the selected button
    public void selectButton(Button optionNum) {
        resetButton();
        optionNum.setBackgroundColor(getResources().getColor(R.color.purple_700));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        progress = findViewById(R.id.progressBar);
        progress.setMax(MAX_QUESTION_NUM);
        questionText = findViewById(R.id.questionView);
        welcomeText = findViewById(R.id.welcomeText);
        progress = findViewById(R.id.progressBar);
        option1 = findViewById(R.id.answer1);
        option2 = findViewById(R.id.answer2);
        option3 = findViewById(R.id.answer3);
        submitBtn = findViewById(R.id.submitBtn);

        Intent intent = getIntent();
        userName = intent.getStringExtra("Username");
        welcomeText.setText("Welcome, " + userName+"!");

        option1.setOnClickListener(this);
        option2.setOnClickListener(this);
        option3.setOnClickListener(this);
        submitBtn.setOnClickListener(this);

        getQuestionSet(1);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.answer1:
                selectButton(option1);
                selectedOption = option1.getText().toString();
                selectedButton = option1;
                break;

            case R.id.answer2:
                selectButton(option2);
                selectedOption = option2.getText().toString();
                selectedButton = option2;
                break;

            case R.id.answer3:
                selectButton(option3);
                selectedOption = option3.getText().toString();
                selectedButton = option3;
                break;

            case R.id.submitBtn:
                if (submitClick == 1) {
                    displayCorrectAns(questionCount);
                    if (selectedOption.equals(getAnswer(questionCount)))
                        correctCount++;
                    questionCount++;
                    progress.setProgress(questionCount - 1);
                    submitBtn.setText("Next");
                    submitClick++;
                }
                else if (submitClick == 2) {
                    resetButton();
                    submitBtn.setText("Submit");
                    if (questionCount <= MAX_QUESTION_NUM)
                        getQuestionSet(questionCount);
                    else {
                        Intent intent = new Intent(getBaseContext(), ResultActivity.class);
                        intent.putExtra("Score", correctCount);
                        intent.putExtra("questionNumber", MAX_QUESTION_NUM);
                        intent.putExtra("Name", userName);
                        startActivity(intent);
                    }
                    submitClick = 1;
                } break;
        }
    }
}