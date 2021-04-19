package com.saihtoo.quizapp;

//Question class where all the questions are stored
public class Question {
    private final String[] questions =
            {
                    "How to get a response from an activity in Android?",
                    "Which component is NOT part of Android architecture?",
                    "Which company developed android??",
                    "Android is ...",
                    "Android OS is based on ..."
            };

    private final String[][] options =
            {
                    {"startActivityToResult()", "startActivityForResult()", "startActivityFromResult()"},
                    {"Android Document", "Libraries", "Android Framework"},
                    {"Apple", "Nokia", "Android Inc"},
                    {"Web server", "Web browser", "Operation system"},
                    {"Windows", "Linux", "Solaris"}
            };

    private final String[] correctAns =
            {
                    "startActivityForResult()",
                    "Android Document",
                    "Android Inc",
                    "Operation system",
                    "Linux"
            };

    //Getter methods
    public String getQuestion(int questionNum) {
        return questions[questionNum - 1];
    }

    public String getOption(int questionNum, int optionNum) {
        return options[questionNum - 1][optionNum - 1];
    }

    public String getAnswer(int questionNum) {
        return correctAns[questionNum - 1];
    }
}
