package com.example.ruins7.multiplechoice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ruins7.multiplechoice.dao.QuizDB;
import com.example.ruins7.multiplechoice.entity.User;

import junit.framework.Test;

import org.w3c.dom.Text;

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
        ClickToFade clickToFade = new ClickToFade();

        appname = (EditText) findViewById(R.id.appname);
        Button login = (Button) findViewById(R.id.login);

        usernametext = (EditText) findViewById(R.id.username);
        usernametext.setText("username");
        //username = usernametext.getText().toString();

        passwordtext = (EditText) findViewById(R.id.password);
        passwordtext.setText("password");
        //password = passwordtext.getText().toString();

        appname.setEnabled(false);

        login.setOnClickListener(userLogin);

        usernametext.setFocusable(true);
        usernametext.setFocusableInTouchMode(true);

        passwordtext.setFocusable(true);
        passwordtext.setFocusableInTouchMode(true);

        usernametext.setOnFocusChangeListener(clickToFade);
        passwordtext.setOnFocusChangeListener(clickToFade);



    }

    class UserLogin implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            username = usernametext.getText().toString();
            password = passwordtext.getText().toString();
            Log.v("t",username + "     "+ password);

            User loginUser = new User();
            loginUser.setUsername(username);
            loginUser.setPassword(password);
            quizDB = new QuizDB(MainActivity.this);
            ArrayList<User> userArrayList = quizDB.selectUser(loginUser);
            if(userArrayList.size() > 0){
                //登录成功，跳转
                Log.v("tag","login successfully");
            }else{
                //登录失败，重新登录
                Log.v("tag","login fail");
            }


        }
    }

    //获取光标
    class ClickToFade implements View.OnFocusChangeListener {

        @Override
        public void onFocusChange(View v, boolean hasFocus) {

            if(hasFocus){
                v.requestFocus();
                if(v.getId() == R.id.username){
                    if(((EditText) v).getText().toString().equals("username")) {
                        usernametext.setText("");
                    }
                }else if(v.getId() == R.id.password){
                    if(((EditText) v).getText().toString().equals("password")) {
                        passwordtext.setText("");
                    }
                }

            }else{
                v.clearFocus();
                if(v.getId() == R.id.username){
                    if(((EditText) v).getText().toString().equals("")){
                        usernametext.setText("username");
                    }
                }else if(v.getId() == R.id.password){
                    if(((EditText) v).getText().toString().equals("")){
                        usernametext.setText("password");
                    }
                }
            }

        }
    }
}
