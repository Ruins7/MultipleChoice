package com.example.ruins7.multiplechoice;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.ruins7.multiplechoice.dao.QuizDB;
import com.example.ruins7.multiplechoice.entity.History;
import com.example.ruins7.multiplechoice.entity.User;

import java.util.ArrayList;

/**
 * Created by ruins7 on 2016-10-04.
 */

public class QmDelHistoryActivity extends AppCompatActivity {

    private static EditText historyTitle;
    private static EditText historyUsernameTitle;
    private static EditText historyUsername;
    private static EditText resultTitle;
    private static EditText results;
    private static Button searchHistory;
    private static Button deleteButton;

    private static int totalHis;
    private static int correctHis;
    private static int uid;

    private static QuizDB quizDB;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qm_deletehistory_activity);

        GetHistory getHistory = new GetHistory();
        DeleteHistory deleteHistory = new DeleteHistory();

        historyTitle = (EditText) findViewById(R.id.historytitle);
        historyUsernameTitle = (EditText) findViewById(R.id.historyusernametitle);
        historyUsername = (EditText) findViewById(R.id.historyusername);
        resultTitle = (EditText) findViewById(R.id.resultstitle);
        results = (EditText) findViewById(R.id.results);
        searchHistory = (Button) findViewById(R.id.searchhistory);
        deleteButton = (Button) findViewById(R.id.delbutton);

        historyTitle.setEnabled(false);
        historyUsernameTitle.setEnabled(false);
        resultTitle.setEnabled(false);
        results.setEnabled(false);

        searchHistory.setOnClickListener(getHistory);
        deleteButton.setOnClickListener(deleteHistory);


    }

    //查询history
    class GetHistory implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            //先根据username 查询uid
            quizDB = new QuizDB(QmDelHistoryActivity.this);
            ArrayList<User> userlist = quizDB.selectUser(historyUsername.getText().toString());

            if(userlist != null && userlist.size() > 0){
                uid = userlist.get(0).getUid();
                quizDB = new QuizDB(QmDelHistoryActivity.this);
                ArrayList<History> totaleHislist = quizDB.selectHistory(uid);
                if(totaleHislist == null || totaleHislist.size() < 0){
                    totalHis = 0;
                }else{
                    totalHis = totaleHislist.size();
                }

                quizDB = new QuizDB(QmDelHistoryActivity.this);
                ArrayList<History> correctHislist = quizDB.selectCorrectHistory(uid);
                if(correctHislist == null || correctHislist.size() < 0){
                    correctHis = 0;
                }else{
                    correctHis = correctHislist.size();
                }
                //显示查询结果
                results.setText(correctHis + "  /  " + totalHis);
            }else{
                //显示查询结果
                results.setText("No such user");
                Toast.makeText(QmDelHistoryActivity.this, "No such user.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    //delete history
    class DeleteHistory implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            if(!results.getText().toString().trim().equals("") && !results.getText().toString().equals("No such user")){
                quizDB = new QuizDB(QmDelHistoryActivity.this);
                int result = quizDB.deleteHistory(uid);
                if(result > 0){
                    Toast.makeText(QmDelHistoryActivity.this, "Delete history successfully", Toast.LENGTH_SHORT).show();
                }else if(result == 0){
                    Toast.makeText(QmDelHistoryActivity.this, "Nothing to be deleted", Toast.LENGTH_SHORT).show();
                }else if(result < 0){
                    Toast.makeText(QmDelHistoryActivity.this, "Fail to delete history", Toast.LENGTH_SHORT).show();
                }
                //跳转到add question 页面
                Intent intent_ivtime = new Intent();
                intent_ivtime.setClass(QmDelHistoryActivity.this, QmAddQuestionActivity.class);
                QmDelHistoryActivity.this.startActivity(intent_ivtime);
            }else{
                Toast.makeText(QmDelHistoryActivity.this, "Nothing to be deleted, please change a vaild username", Toast.LENGTH_SHORT).show();
            }

        }
    }
}
