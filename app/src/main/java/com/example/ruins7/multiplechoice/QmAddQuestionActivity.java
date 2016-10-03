package com.example.ruins7.multiplechoice;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ruins7.multiplechoice.dao.QuizDB;
import com.example.ruins7.multiplechoice.entity.Question;

/**
 * Created by ruins7 on 2016-10-02.
 */

public class QmAddQuestionActivity extends AppCompatActivity {

    private static EditText questioncontent;
    private static EditText conrrectanswer;
    private static EditText inconrrectanswer1;
    private static EditText inconrrectanswer2;
    private static EditText inconrrectanswer3;
    private static EditText qtime;
    private static String questioncontentstr;
    private static String conrrectanswerstr;
    private static String inconrrectanswer1str;
    private static String inconrrectanswer2str;
    private static String inconrrectanswer3str;
    private static int qtimeint;

    private static QuizDB quizDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qm_addquestion_activity);

        Logoff logofffunction = new Logoff();
        GotoAddUser gotoaddUser = new GotoAddUser();
        AddQuestion addQuestion = new AddQuestion();

        //不可点击titles
        EditText questiontitle = (EditText) findViewById(R.id.questiontitle);
        EditText questioncontenttitle = (EditText) findViewById(R.id.questioncontenttitle);
        EditText correctanswertitle = (EditText) findViewById(R.id.correctanswertitle);
        EditText incorrectanswertitle = (EditText) findViewById(R.id.incorrectanswertitle);
        EditText timetitle = (EditText) findViewById(R.id.timetitle);

        //内容
        questioncontent = (EditText) findViewById(R.id.questioncontent);
        conrrectanswer = (EditText) findViewById(R.id.conrrectanswer);
        inconrrectanswer1 = (EditText) findViewById(R.id.inconrrectanswer1);
        inconrrectanswer2 = (EditText) findViewById(R.id.inconrrectanswer2);
        inconrrectanswer3 = (EditText) findViewById(R.id.inconrrectanswer3);
        qtime = (EditText) findViewById(R.id.qtime);

        //buttons
        Button gotoadduser = (Button) findViewById(R.id.gotoadduser);
        Button logoff = (Button) findViewById(R.id.logoff);
        Button submitquestion = (Button) findViewById(R.id.submitquestion);

        //设置titles不可点击
        questiontitle.setEnabled(false);
        questioncontenttitle.setEnabled(false);
        correctanswertitle.setEnabled(false);
        incorrectanswertitle.setEnabled(false);
        timetitle.setEnabled(false);

        //设置触发函数
        logoff.setOnClickListener(logofffunction);
        gotoadduser.setOnClickListener(gotoaddUser);
        submitquestion.setOnClickListener(addQuestion);

        //冒泡提示登录成功
        Toast.makeText(QmAddQuestionActivity.this, "Login Successfully", Toast.LENGTH_LONG).show();

    }

    //logoff
    class Logoff implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Intent intent_ivtime = new Intent();
            intent_ivtime.setClass(QmAddQuestionActivity.this, MainActivity.class);
            QmAddQuestionActivity.this.startActivity(intent_ivtime);
        }
    }

    //跳转到add user
    class GotoAddUser implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Intent intent_ivtime = new Intent();
            intent_ivtime.setClass(QmAddQuestionActivity.this, QmAddUserActivity.class);
            QmAddQuestionActivity.this.startActivity(intent_ivtime);
        }
    }

    //add new question
    class AddQuestion implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            questioncontentstr = questioncontent.getText().toString();
            conrrectanswerstr = conrrectanswer.getText().toString();
            inconrrectanswer1str = inconrrectanswer1.getText().toString();
            inconrrectanswer2str = inconrrectanswer2.getText().toString();
            inconrrectanswer3str = inconrrectanswer3.getText().toString();
            qtimeint = Integer.valueOf(qtime.getText().toString());
            if(questioncontentstr.trim().equals("") || conrrectanswerstr.trim().equals("") || inconrrectanswer1str.trim().equals("") || inconrrectanswer2str.trim().equals("") || inconrrectanswer3str.trim().equals("") || qtimeint < 10 || qtimeint > 30){
                Toast.makeText(QmAddQuestionActivity.this, "Please fill the required information correctly", Toast.LENGTH_LONG).show();
            }else{
                Question newQuestion = new Question();
                newQuestion.setQuestionContent(questioncontentstr);
                newQuestion.setCorrentAnswer(conrrectanswerstr);
                newQuestion.setIncorrectAnswer1(inconrrectanswer1str);
                newQuestion.setIncorrectAnswer2(inconrrectanswer2str);
                newQuestion.setIncorrectAnswer3(inconrrectanswer3str);
                newQuestion.setTime(qtimeint);
                quizDB = new QuizDB(QmAddQuestionActivity.this);
                Long result = quizDB.insertQuestion(newQuestion);
                if(result > 0){
                    Toast.makeText(QmAddQuestionActivity.this, "Add Question Successfully, add another one", Toast.LENGTH_LONG).show();
                    questioncontent.setText("");
                    conrrectanswer.setText("");
                    inconrrectanswer1.setText("");
                    inconrrectanswer2.setText("");
                    inconrrectanswer3.setText("");
                    qtime.setText("10");
                }else{
                    Toast.makeText(QmAddQuestionActivity.this, "Failed to Add Question", Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}
