package com.example.ruins7.multiplechoice.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ruins7.multiplechoice.entity.History;
import com.example.ruins7.multiplechoice.entity.Question;
import com.example.ruins7.multiplechoice.entity.User;

import java.util.ArrayList;

/**
 * Created by ruins7 on 2016-10-01.
 */

public class QuizDB {

    private DBOpenHelper dbOpenHelper;

    public QuizDB(Context context) {
        dbOpenHelper = new DBOpenHelper(context);
    }

    //查询用户
    public ArrayList<User> selectUser(User user) {
        SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
        ArrayList<User> userlist = new ArrayList<User>();
        Cursor cursor = db.rawQuery("select * from user where username = ? and password = ?", new String[]{user.getUsername(), user.getPassword()});
        while (cursor.moveToNext()) {
            user.setUid(cursor.getInt(0));
            user.setUsername(cursor.getString(2));
            user.setPassword(cursor.getString(1));
            user.setUtype(cursor.getInt(3));
            userlist.add(user);
        }
        cursor.close();
        db.close();
        return userlist;
    }

    //查询用户 by username
    public ArrayList<User> selectUser(String username) {
        SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
        ArrayList<User> userlist = new ArrayList<User>();
        Cursor cursor = db.rawQuery("select * from user where username = ? order by uid asc", new String[]{username});
        while (cursor.moveToNext()) {
            User user = new User();
            user.setUid(cursor.getInt(0));
            user.setUsername(cursor.getString(2));
            user.setPassword(cursor.getString(1));
            user.setUtype(cursor.getInt(3));
            userlist.add(user);
        }
        cursor.close();
        db.close();
        return userlist;
    }

    //分页查询question
    public Question selectQuestion() {
        SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
        Question question = new Question();
        Cursor cursor = db.rawQuery("select * from question order by RANDOM () limit 1", null);
        while (cursor.moveToNext()) {
            question.setQid(cursor.getInt(0));
            question.setQuestionContent(cursor.getString(1));
            question.setCorrentAnswer(cursor.getString(2));
            question.setIncorrectAnswer1(cursor.getString(3));
            question.setIncorrectAnswer2(cursor.getString(4));
            question.setIncorrectAnswer3(cursor.getString(5));
            question.setTime(cursor.getInt(6));
        }
        cursor.close();
        db.close();
        return question;
    }

    //查询history
    public ArrayList<History> selectHistory(int uid) {
        SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
        ArrayList<History> historylist = new ArrayList<History>();
        Cursor cursor = db.rawQuery("select * from history where uid = ?", new String[]{String.valueOf(uid)});
        while (cursor.moveToNext()) {
            History history = new History();
            history.setHid(cursor.getInt(0));
            history.setUid(cursor.getInt(1));
            history.setQid(cursor.getInt(2));
            history.setGavenAnswer(cursor.getString(3));
            history.setResult(cursor.getString(4));
            history.setSpendTime(cursor.getInt(5));
            historylist.add(history);
        }
        cursor.close();
        db.close();
        return historylist;
    }

    //查询用户答对的题的history
    public ArrayList<History> selectCorrectHistory(int uid) {
        SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
        ArrayList<History> historylist = new ArrayList<History>();
        Cursor cursor = db.rawQuery("select * from history where result = 'correct' and uid = ?", new String[]{String.valueOf(uid)});
        while (cursor.moveToNext()) {
            History history = new History();
            history.setHid(cursor.getInt(0));
            history.setUid(cursor.getInt(1));
            history.setQid(cursor.getInt(2));
            history.setGavenAnswer(cursor.getString(3));
            history.setResult(cursor.getString(4));
            history.setSpendTime(cursor.getInt(5));
            historylist.add(history);
        }
        cursor.close();
        db.close();
        return historylist;
    }

    //增加user操作
    public long insertUser(User user) {
        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
        /* ContentValues */
        ContentValues cv = new ContentValues();

        cv.put("password", user.getPassword());
        cv.put("username", user.getUsername());
        cv.put("utype", user.getUtype());

        long row = db.insert("user", null, cv);
        return row;
    }

    //增加question操作
    public long insertQuestion(Question question) {
        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
        /* ContentValues */
        ContentValues cv = new ContentValues();

        cv.put("questioncontent", question.getQuestionContent());
        cv.put("correctanswer", question.getCorrentAnswer());
        cv.put("incorrect_answer1", question.getIncorrectAnswer1());
        cv.put("incorrect_answer2", question.getIncorrectAnswer2());
        cv.put("incorrect_answer3", question.getIncorrectAnswer3());
        cv.put("time", question.getTime());

        long row = db.insert("question", null, cv);
        return row;
    }

    //增加history操作
    public long insertHistory(History history) {
        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
        /* ContentValues */
        ContentValues cv = new ContentValues();

        cv.put("uid", history.getUid());
        cv.put("qid", history.getQid());
        cv.put("gaven_answer", history.getGavenAnswer());
        cv.put("result", history.getResult());
        cv.put("spend_time", history.getSpendTime());

        long row = db.insert("history", null, cv);
        return row;
    }

    //删除histroy
    public int deleteHistory(int uid)
    {
        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
        int result = db.delete("history", "uid = ?", new String[]{String.valueOf(uid)});
        return result;
    }
}
