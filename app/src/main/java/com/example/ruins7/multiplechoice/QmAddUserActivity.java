package com.example.ruins7.multiplechoice;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ruins7.multiplechoice.dao.QuizDB;
import com.example.ruins7.multiplechoice.entity.User;

/**
 * Created by ruins7 on 2016-10-02.
 */

public class QmAddUserActivity extends AppCompatActivity {

    private static EditText qtname;
    private static EditText qtpassword;
    private static Spinner usertype;
    private static String usertypeinstring;
    private static int usertypeinint;
    private static String qtnamestr;
    private static String qtpasswordstr;

    private static QuizDB quizDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qm_adduser_avtivity);

        Logoff logofffunction = new Logoff();
        GotoAddQuestion gotoaddQuestion = new GotoAddQuestion();
        AddUser addUser = new AddUser();

        //不可点击titiles
        EditText qttitle = (EditText) findViewById(R.id.qttitle);
        EditText qtnametitle = (EditText) findViewById(R.id.qtnametitle);
        EditText qttypetitle = (EditText) findViewById(R.id.qttypetitle);
        EditText qtpsswordtitle = (EditText) findViewById(R.id.qtpsswordtitle);

        //内容
        qtname = (EditText) findViewById(R.id.qtname);
        qtpassword = (EditText) findViewById(R.id.qtpassword);
        usertype = (Spinner) findViewById(R.id.usertypespinner);
        usertype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                String[] usertypes = getResources().getStringArray(R.array.usertypes);
                usertypeinstring = usertypes[pos];
                if (usertypeinstring.equals("quiz master")) {
                    usertypeinint = 1;
                } else if (usertypeinstring.equals("quiz taker")) {
                    usertypeinint = 2;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //if nothing selected, type would be 2(quiz taker)
                usertypeinint = 2;
            }
        });

        //buttons
        Button gotoaddquestion = (Button) findViewById(R.id.gotoaddquestion);
        Button logoff = (Button) findViewById(R.id.logoff);
        Button submitqt = (Button) findViewById(R.id.submitqt);


        //设置不可点击
        qttitle.setEnabled(false);
        qtnametitle.setEnabled(false);
        qttypetitle.setEnabled(false);
        qtpsswordtitle.setEnabled(false);

        //触发函数
        logoff.setOnClickListener(logofffunction);
        gotoaddquestion.setOnClickListener(gotoaddQuestion);
        submitqt.setOnClickListener(addUser);

    }

    //logoff
    class Logoff implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Intent intent_ivtime = new Intent();
            intent_ivtime.setClass(QmAddUserActivity.this, MainActivity.class);
            QmAddUserActivity.this.startActivity(intent_ivtime);
        }
    }

    //跳转到add question
    class GotoAddQuestion implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Intent intent_ivtime = new Intent();
            intent_ivtime.setClass(QmAddUserActivity.this, QmAddQuestionActivity.class);
            QmAddUserActivity.this.startActivity(intent_ivtime);
        }
    }

    //add user
    class AddUser implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            qtnamestr = qtname.getText().toString();
            qtpasswordstr= qtpassword.getText().toString();
            if(qtnamestr.trim().equals("") || qtpasswordstr.trim().equals("")){
                Toast.makeText(QmAddUserActivity.this, "Please fill the required information correctly", Toast.LENGTH_LONG).show();
            }else{
                User newUser = new User();
                newUser.setUsername(qtnamestr);
                newUser.setPassword(qtpasswordstr);
                newUser.setUtype(usertypeinint);
                quizDB = new QuizDB(QmAddUserActivity.this);
                Long result = quizDB.insertUser(newUser);
                if(result > 0){
                    Toast.makeText(QmAddUserActivity.this, "Add User Successfully, add another one", Toast.LENGTH_LONG).show();
                    qtname.setText("");
                    qtpassword.setText("");
                }else{
                    Toast.makeText(QmAddUserActivity.this, "Failed to Add User", Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}
