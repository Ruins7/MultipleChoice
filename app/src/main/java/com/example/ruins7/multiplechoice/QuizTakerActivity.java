package com.example.ruins7.multiplechoice;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ruins7.multiplechoice.dao.QuizDB;
import com.example.ruins7.multiplechoice.entity.Question;

import java.util.ArrayList;

/**
 * Created by ruins7 on 2016-10-02.
 */

public class QuizTakerActivity extends AppCompatActivity {

    private static TextView qtusername;
    private static TextView qtime;
    private static TextView qthistroy;

    private static EditText questioncontent;
    private static EditText answer1;
    private static EditText answer2;
    private static EditText answer3;
    private static EditText answer4;

    private static QuizDB quizDB;

    private static final int totalItemForOnePage = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qt_activity);

        qtusername = (TextView) findViewById(R.id.qtusername);
        qtime = (TextView) findViewById(R.id.qtime);
        qthistroy = (TextView) findViewById(R.id.qthistroy);

        questioncontent = (EditText) findViewById(R.id.questioncontent);
        answer1 = (EditText) findViewById(R.id.answer1);
        answer2 = (EditText) findViewById(R.id.answer2);
        answer3 = (EditText) findViewById(R.id.answer3);
        answer4 = (EditText) findViewById(R.id.answer4);

        //activity接收传值,获得当前登录用户信息
        Bundle bundle = this.getIntent().getExtras();
        String loginusername = bundle.getString("username");
        int loginuid = bundle.getInt("uid");

        //在页面上显示登录username
        qtusername.setText(loginusername);

        Toast.makeText(QuizTakerActivity.this, "Welcome back, " + loginusername, Toast.LENGTH_LONG).show();


        //TODO: onCreate时随机查询一条问题，答对或者超时后再随机查询另一条问题，设置计数器，如果到5时就询问是否继续，不继续则logoff，此时保存记录
        //TODO: 到时间没有作答或者答错，则在正确答案上显示绿色，答错到答案显示红色，如果答对则显示下一题
        //查询question,只需要变换页数即可
        ArrayList<Question> questionlist = new ArrayList<Question>();
        quizDB = new QuizDB(QuizTakerActivity.this);
        int pageNum = 0;
        questionlist = quizDB.selectQuestion(totalItemForOnePage, pageNum);
        for (Question question: questionlist){
            Log.v("question", question.getQid() +"   "+ question.getQuestionContent() +"   " + question.getCorrentAnswer() +"  "
                    + question.getIncorrectAnswer1() + "   " + question.getIncorrectAnswer2() +"  "+ question.getIncorrectAnswer3() + "   "+
                    question.getTime());
        }









    }
}
