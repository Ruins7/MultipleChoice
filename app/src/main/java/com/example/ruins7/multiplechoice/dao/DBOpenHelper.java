package com.example.ruins7.multiplechoice.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by ruins7 on 2016-09-25.
 */

public class DBOpenHelper extends SQLiteOpenHelper{

    private final static String DATABASE_NAME = "quizapp.db";
    private final static int DATABASE_VERSION = 1;
    private final static String TABLE_NAME = "";


    public DBOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //创建数据库
    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建数据库sql语句
        String user = "CREATE TABLE `user` (\n" +
                "\t`uid`\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,\n" +
                "\t`password`\tTEXT NOT NULL,\n" +
                "\t`username`\tTEXT NOT NULL,\n" +
                "\t`utype`\tINTEGER NOT NULL\n" +
                ");";
        String question = "CREATE TABLE `question` (\n" +
                "\t`qid`\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,\n" +
                "\t`questioncontent`\tTEXT NOT NULL,\n" +
                "\t`correctanswer`\tTEXT NOT NULL,\n" +
                "\t`incorrect_answer1`\tTEXT NOT NULL,\n" +
                "\t`incorrect_answer2`\tTEXT NOT NULL,\n" +
                "\t`incorrect_answer3`\tTEXT NOT NULL,\n" +
                "\t`time`\tINTEGER NOT NULL\n" +
                ");";
        String history = "CREATE TABLE `history` (\n" +
                "\t`hid`\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,\n" +
                "\t`uid`\tINTEGER NOT NULL,\n" +
                "\t`qid`\tINTEGER NOT NULL,\n" +
                "\t`gaven_answer`\tTEXT ,\n" +
                "\t`result`\tTEXT NOT NULL,\n" +
                "\t`spend_time`\tINTEGER NOT NULL\n" +
                ");";
        //执行创建数据库操作
        db.execSQL(user);
        db.execSQL(question);
        db.execSQL(history);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //创建成功，日志输出提示
        Log.i("tag", "update a Database");
    }



}
