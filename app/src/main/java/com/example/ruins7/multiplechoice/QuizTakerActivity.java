package com.example.ruins7.multiplechoice;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ruins7.multiplechoice.dao.QuizDB;
import com.example.ruins7.multiplechoice.entity.History;
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
    private static RadioGroup answersgroup;
    private static RadioButton answer1Button;
    private static RadioButton answer2Button;
    private static RadioButton answer3Button;
    private static RadioButton answer4Button;
    private static RadioButton seletedButton;

    private static QuizDB quizDB;
    private static int[] answersOrder;
    private static Question question;
    private static History history;
    private static ArrayList<String> answers;
    private static int loginuid;
    private static String loginusername;
    private static Bundle bundle;
    private static String selectedAnswer = "";
    private static int flagForQuestionNum = 0;

    private static Handler handler;
    private static int time = 0;//为给定时器传值用
    private static Runnable runnable1;
    private static Runnable runnable2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qt_activity);

        qtusername = (TextView) findViewById(R.id.qtusername);
        qtime = (TextView) findViewById(R.id.qtime);
        qthistroy = (TextView) findViewById(R.id.qthistroy);

        questioncontent = (EditText) findViewById(R.id.questioncontent);
        answersgroup = (RadioGroup) findViewById(R.id.answersgroup);
        answer1Button = (RadioButton) findViewById(R.id.answer1);
        answer2Button = (RadioButton) findViewById(R.id.answer2);
        answer3Button = (RadioButton) findViewById(R.id.answer3);
        answer4Button = (RadioButton) findViewById(R.id.answer4);

        //activity接收传值,获得当前登录用户信息
        bundle = this.getIntent().getExtras();
        loginusername = bundle.getString("username");
        loginuid = bundle.getInt("uid");

        //单选框绑定事件
        Mradiogroup mradiogroup = new Mradiogroup();
        answersgroup.setOnCheckedChangeListener(mradiogroup);

        //在页面上显示登录username，并显示欢迎语句
        qtusername.setText(loginusername);
        Toast.makeText(QuizTakerActivity.this, "Welcome back, " + loginusername, Toast.LENGTH_SHORT).show();

        //先检查flagForQuestionNum，再随机查询一个问题, 并显示在页面上
        checkFlagForQuestionNum();

        //查询当前用户的history
        getLoginUserHistory(loginuid);


    }

    //获取选择的答案
    class Mradiogroup implements RadioGroup.OnCheckedChangeListener {

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            quizDB = new QuizDB(QuizTakerActivity.this);
            history = new History();

            //用户点击了选项后停止计时
            stopTime(runnable1);

            if (checkedId == answer1Button.getId()) {
                seletedButton = answer1Button;
                selectedAnswer = answer1Button.getText().toString();
            } else if (checkedId == answer2Button.getId()) {
                seletedButton = answer2Button;
                selectedAnswer = answer2Button.getText().toString();
            } else if (checkedId == answer3Button.getId()) {
                seletedButton = answer3Button;
                selectedAnswer = answer3Button.getText().toString();
            } else if (checkedId == answer4Button.getId()) {
                seletedButton = answer4Button;
                selectedAnswer = answer4Button.getText().toString();
            }

            if (selectedAnswer.equals(question.getCorrentAnswer())) {
                //选对，显示提示,显示颜色，设置history的结果
                Toast.makeText(QuizTakerActivity.this, "correct answer", Toast.LENGTH_SHORT).show();
                seletedButton.setBackgroundColor(Color.parseColor("#93CF5D"));//绿色
                history.setResult("correct");
            } else {
                //选错，显示提示，显示颜色，设置history的结果，
                Toast.makeText(QuizTakerActivity.this, "wrong answer", Toast.LENGTH_SHORT).show();
                seletedButton.setBackgroundColor(Color.parseColor("#EA4335"));//红色
                history.setResult("incorrect");
            }

            //添加记录
            history.setUid(loginuid);
            history.setQid(question.getQid());
            history.setGavenAnswer(selectedAnswer);
            //TODO spend time
            history.setSpendTime(8);
            quizDB.insertHistory(history);

            //清理已选的选项
            selectedAnswer = "";
            answer1Button.setChecked(false);
            answer2Button.setChecked(false);
            answer3Button.setChecked(false);
            answer4Button.setChecked(false);

            handler = new Handler();
            runnable1 = new Runnable() {
                @Override
                public void run() {
                    seletedButton.setBackgroundColor(Color.parseColor("#00000000"));//设置为透明背景
                    //检查FlagForQuestionNum
                    checkFlagForQuestionNum();
                }
            };
            handler.postDelayed(runnable1, 2000);

//            new Handler().postDelayed(new Runnable(){
//                @Override
//                public void run() {
//                    seletedButton.setBackgroundColor(Color.parseColor("#00000000"));//设置为透明背景
//                    //检查FlagForQuestionNum
//                    checkFlagForQuestionNum();
//                }
//            }, 2000);//主线程停留2秒
        }
    }

    //检查当前round的答题数
    public void checkFlagForQuestionNum() {

        if (flagForQuestionNum == 5) {
            //询问是否进行下一轮,flagForQuestionNum 归零
            flagForQuestionNum = 0;
            AlertDialog.Builder builder = new AlertDialog.Builder(QuizTakerActivity.this);
            builder.setTitle("Quiz");
            builder.setMessage("Another round？");
            builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //continue
                    Log.v("yes", "yessss");
                    getRandomQuesetion();
                }
            });
            builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //logoff
                    Intent intent_ivtime = new Intent();
                    intent_ivtime.setClass(QuizTakerActivity.this, MainActivity.class);
                    QuizTakerActivity.this.startActivity(intent_ivtime);
                }
            });
            builder.show();
        } else {
            getRandomQuesetion();
        }
    }

    //随机查询一个问题，并显示在页面上
    public void getRandomQuesetion() {

        quizDB = new QuizDB(QuizTakerActivity.this);
        question = quizDB.selectQuestion();
        Log.v("question", question.getQid() + "   " + question.getQuestionContent() + "   " + question.getCorrentAnswer() + "  "
                + question.getIncorrectAnswer1() + "   " + question.getIncorrectAnswer2() + "  " + question.getIncorrectAnswer3() + "   " +
                question.getTime());

        questioncontent.setText(flagForQuestionNum + 1 + ".  " + question.getQuestionContent());

        answers = new ArrayList<String>();
        answers.add(question.getCorrentAnswer());
        answers.add(question.getIncorrectAnswer1());
        answers.add(question.getIncorrectAnswer2());
        answers.add(question.getIncorrectAnswer3());

        //随机对应给选项
        answersOrder = generateRandomOrder();

        //TODO 这个for是测试
        for (int j = 0; j < answersOrder.length; j++) {
            Log.v("order", String.valueOf(answersOrder[j]));
        }

        //随机位置显示答案
        answer1Button.setText(answers.get(answersOrder[0] - 1));
        answer2Button.setText(answers.get(answersOrder[1] - 1));
        answer3Button.setText(answers.get(answersOrder[2] - 1));
        answer4Button.setText(answers.get(answersOrder[3] - 1));

        //history
        getLoginUserHistory(loginuid);

        //显示该题目的时间
        qtime.setText(String.valueOf(question.getTime()));

        //设置计时器
        timerForAnswerQuestion(question.getTime());

        //递增当前round的答题数
        flagForQuestionNum++;
    }

    //查询当前用户的答题记录,并显示在页面上,随着答题进程递增
    public void getLoginUserHistory(int loginUid) {

        int total = 0;
        int correct = 0;
        quizDB = new QuizDB(QuizTakerActivity.this);
        //total number of history
        ArrayList<History> historieslist = quizDB.selectHistory(loginUid);
        if (historieslist == null || historieslist.size() < 0) {
            total = 0;
        } else {
            total = historieslist.size();
        }
        quizDB = new QuizDB(QuizTakerActivity.this);
        //correct number of history
        ArrayList<History> correctHistorylist = quizDB.selectCorrectHistory(loginUid);
        if (correctHistorylist == null || correctHistorylist.size() < 0) {
            correct = 0;
        } else {
            correct = correctHistorylist.size();
        }
        qthistroy.setText(String.valueOf(correct) + "/" + String.valueOf(total));

    }

    //随机产生一个1-4的整数排列，为了给页面上的答案随机赋值
    public int[] generateRandomOrder() {
        /**
         * 随机指定范围内N个不重复的数
         * 最简单最基本的方法
         * @param min 指定范围最小值(但不包括）
         * @param max 指定范围最大值(但不包括）
         * @param n 随机数个数
         */
        int min = 0;
        int max = 5;
        int n = 4;
        if (n > (max - min + 1) || max < min) {
            return null;
        }
        int[] result = new int[n];
        int count = 0;
        while (count < n) {
            int num = (int) (Math.random() * (max - min)) + min;
            boolean flag = true;
            for (int j = 0; j < n; j++) {
                if (num == result[j]) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                result[count] = num;
                count++;
            }
        }
        return result;
    }

    //答题计时器
    public void timerForAnswerQuestion(int limitTime) {
        time = limitTime;
        handler = new Handler();
        runnable1 = new Runnable() {
            @Override
            public void run() {
                //要做的事情，这里再次调用此Runnable对象，以实现每秒实现一次的定时器操作
                qtime.setText(String.valueOf(time--));
                handler.postDelayed(this, 1000);
                if (time < 1) {
                    //显示正确答案
                    if (answer1Button.getText().toString().equals(question.getCorrentAnswer())) {
                        answer1Button.setBackgroundColor(Color.parseColor("#93CF5D"));//绿色
                    } else if (answer2Button.getText().toString().equals(question.getCorrentAnswer())) {
                        answer2Button.setBackgroundColor(Color.parseColor("#93CF5D"));//绿色
                    } else if (answer3Button.getText().toString().equals(question.getCorrentAnswer())) {
                        answer3Button.setBackgroundColor(Color.parseColor("#93CF5D"));//绿色
                    } else if (answer4Button.getText().toString().equals(question.getCorrentAnswer())) {
                        answer4Button.setBackgroundColor(Color.parseColor("#93CF5D"));//绿色
                    }
                }

                if (time == -1) {
                    //停止计时
                    stopTime(runnable1);
                    //清空正确标记
                    answer1Button.setBackgroundColor(Color.parseColor("#00000000"));//设置为透明背景
                    answer2Button.setBackgroundColor(Color.parseColor("#00000000"));//设置为透明背景
                    answer3Button.setBackgroundColor(Color.parseColor("#00000000"));//设置为透明背景
                    answer4Button.setBackgroundColor(Color.parseColor("#00000000"));//设置为透明背景

                    //insert history
                    quizDB = new QuizDB(QuizTakerActivity.this);
                    history = new History();
                    history.setUid(loginuid);
                    history.setQid(question.getQid());
                    history.setGavenAnswer("");
                    history.setResult("incorrect");
                    history.setSpendTime(question.getTime());
                    quizDB.insertHistory(history);
                    //检查当前round答题数
                    checkFlagForQuestionNum();
                }

            }
        };
        //启动计时器
        handler.postDelayed(runnable1, 1000);//每1秒执行一次runnable
    }

    //停止计时器
    public void stopTime(Runnable runnable) {
        handler.removeCallbacks(runnable);
    }

}



