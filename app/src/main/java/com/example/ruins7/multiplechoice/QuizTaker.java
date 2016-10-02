package com.example.ruins7.multiplechoice;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.ruins7.multiplechoice.dao.DBOpenHelper;

/**
 * Created by ruins7 on 2016-09-25.
 */

public class QuizTaker {

//    DBOpenHelper dbHelper;
//    TextView tv;
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        Log.v("debug","..................");
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//		/*tv = (TextView) findViewById(R.id.tv);*/
//        // 创建MySQLiteOpenHelper辅助类对象
//        dbHelper = new DBOpenHelper(this, "quizapp.db", null, 1);
//
//        // 查询数据
//        String result = queryData(dbHelper);
//        Log.v("debug","..result.."+result);
//		/*tv.setTextColor(Color.RED);
//		tv.setTextSize(20.0f);
//		tv.setText("名字\t等级\n" + result);*/
//
//    }
//
//    //从数据库中查询数据
//    public String queryData(DBOpenHelper dbHelper){
//        String result = "";
//        //获得数据库对象
//        SQLiteDatabase db = dbHelper.getReadableDatabase();
//        //查询表中的数据
//        Cursor cursor = db.query("user", null, null, null, null, null, "id asc");
//        //获取name列的索引
//        int nameIndex = cursor.getColumnIndex("username");
//
//        for (cursor.moveToFirst();!(cursor.isAfterLast());cursor.moveToNext()) {
//            result = result + cursor.getString(nameIndex)+ "\t\t";
//        }
//        cursor.close();//关闭结果集
//        db.close();//关闭数据库对象
//        return result;
//    }
}
