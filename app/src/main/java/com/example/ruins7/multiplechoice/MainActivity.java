package com.example.ruins7.multiplechoice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ruins7.multiplechoice.dao.QuizDB;
import com.example.ruins7.multiplechoice.entity.User;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static String username;
    private static String password;
    private static EditText usernametext;
    private static EditText passwordtext;
    private static EditText appname;

    private static QuizDB quizDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UserLogin userLogin = new UserLogin();

        appname = (EditText) findViewById(R.id.appname);
        Button login = (Button) findViewById(R.id.login);

        usernametext = (EditText) findViewById(R.id.username);

        passwordtext = (EditText) findViewById(R.id.password);

        appname.setEnabled(false);

        usernametext.setFocusable(true);
        usernametext.setFocusableInTouchMode(true);

        passwordtext.setFocusable(true);
        passwordtext.setFocusableInTouchMode(true);

        login.setOnClickListener(userLogin);

    }

    class UserLogin implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            username = usernametext.getText().toString();
            password = passwordtext.getText().toString();
            if(username.trim().equals("") || password.trim().equals("")){
                Toast.makeText(MainActivity.this, "Username or Password can not be empty", Toast.LENGTH_SHORT).show();
            }else{
                User loginUser = new User();
                loginUser.setUsername(username);
                loginUser.setPassword(password);
                quizDB = new QuizDB(MainActivity.this);
                ArrayList<User> userArrayList = quizDB.selectUser(loginUser);
                if (userArrayList.size() > 0) {
                    for (User user : userArrayList) {
                        loginUser = user;
                        Log.v("user", user.getUid() + " -- " + user.getUsername() + " -- " + user.getPassword() + " -- " + user.getUtype());
                    }
                    //登录成功，判断跳转
                    if (loginUser.getUtype() == 1) {
                        //quiz master
                        Intent intent_ivtime = new Intent();
                        intent_ivtime.setClass(MainActivity.this, QmAddQuestionActivity.class);
                        MainActivity.this.startActivity(intent_ivtime);
                    } else if (loginUser.getUtype() == 2) {
                        //quiz taker
                        //activity之间使用Bundle对象传值
                        Bundle bundle = new Bundle();
                        Intent intent_ivtime = new Intent();
                        intent_ivtime.setClass(MainActivity.this, QuizTakerActivity.class);
                        //赋值
                        bundle.putString("username", loginUser.getUsername());
                        bundle.putInt("uid", loginUser.getUid());
                        intent_ivtime.putExtras(bundle);
                        MainActivity.this.startActivity(intent_ivtime);
                    }

                } else {
                    //登录失败，重新登录，泡提示
                    Toast.makeText(MainActivity.this, "wrong username or password", Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}
