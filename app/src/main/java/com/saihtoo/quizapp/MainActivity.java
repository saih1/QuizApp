package com.saihtoo.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    //Variables
    TextView inputName;
    Button startBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inputName = findViewById(R.id.editName);
        startBtn = findViewById(R.id.buttonStart);
        inputName.setText(getIntent().getStringExtra("Name"));
    }

    public void btnStart(View view) {
        if (inputName.getText().toString().isEmpty())
            Toast.makeText(this, "Enter you name",Toast.LENGTH_SHORT).show();
        else {
            Intent intent = new Intent(getApplicationContext(), QuizActivity.class);
            intent.putExtra("Username", inputName.getText().toString());
            startActivity(intent);
        }
    }
}