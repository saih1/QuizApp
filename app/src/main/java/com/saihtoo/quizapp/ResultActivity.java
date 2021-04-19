package com.saihtoo.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity implements View.OnClickListener
{
    //Variables
    TextView scoreView, congratsView;
    Button retakeBtn, finishBtn;
    String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        scoreView = findViewById(R.id.resultScoreText);
        retakeBtn = findViewById(R.id.retakeBtn);
        finishBtn = findViewById(R.id.finishBtn);
        congratsView = findViewById(R.id.congratsText);

        Intent intent = getIntent();
        int score = intent.getIntExtra("Score", 0);
        int questionNum = intent.getIntExtra("questionNumber", 0);
        userName = intent.getStringExtra("Name");

        congratsView.setText("Congratulations " + userName + "!");
        scoreView.setText(score + " / " + questionNum);

        retakeBtn.setOnClickListener(this);
        finishBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.retakeBtn:
                startActivity(new Intent(getApplicationContext(), MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP).putExtra("Name", userName));
                break;
            case R.id.finishBtn:
                this.finishAffinity();
                break;
        }
    }
}