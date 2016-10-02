package com.example.ruins7.multiplechoice.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ruins7.multiplechoice.entity.Question;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ruins7 on 2016-09-25.
 */

public class DBDao {

//    DBOpenHelper dbOpenHelper;
//
//    public DBDao(Context context){
//        this.dbOpenHelper=new DBOpenHelper(context, null, null, 0);
//    }
//    /**
//     * 添加一条数据
//     * @param user
//     */
//	/*public void save(User user){
//		SQLiteDatabase db=dbOpenHelper.getWritableDatabase();
//		db.execSQL("insert into user(uname,uaddress) values(?,?)", new Object[]{user.getUname(),user.getUaddress()});
//		db.close();
//	}*/
//    /** * 删除一条数据
//     * @param uid
//     */
//	/*public void delete(Integer uid){
//		SQLiteDatabase db=dbOpenHelper.getWritableDatabase();
//		db.execSQL("delete from user where uid=?", new Object[]{uid});
//		db.close();
//	}*/
//    /**
//     * 更新一条数据
//     * @param user
//     */
//	/*public void update(User user){
//		SQLiteDatabase db=dbOpenHelper.getWritableDatabase();
//		db.execSQL("update user set uname=?,uaddress=? where uid=?", new Object[]{user.getUname(),user.getUaddress(),user.getUid()});
//		db.close();
//	}*/
//    /**
//     * 查找一条数据
//     * @param uid
//     */
//	/*public User find(Integer uid){
//		SQLiteDatabase db=dbOpenHelper.getReadableDatabase();
//		Cursor cursor =db.rawQuery("select * from user where uid=?", new String[]{uid.toString()});
//		if(cursor.moveToFirst()){
//			int uid2=cursor.getInt(cursor.getColumnIndex("uid"));
//			String uname=cursor.getString(cursor.getColumnIndex("uname"));
//			String uaddress=cursor.getString(cursor.getColumnIndex("uaddress"));
//			User user=new User();
//			user.setUid(uid2);
//			user.setUname(uname);
//			user.setUaddress(uaddress);
//			return user;
//		}
//		cursor.close();
//		return null;
//	}*/
//    /**
//     * 分页查找数据
//     * @param offset 跳过多少条数据
//     * @param maxResult 每页多少条数据
//     * @return
//     */
//	/*public List<User> getScrollData(int offset, int maxResult){
//		List<User>users=new ArrayList<User>();
//		SQLiteDatabase db=dbOpenHelper.getReadableDatabase();
//		Cursor cursor =db.rawQuery("select * from user order by uid asc limit ?,?", new String[]{String.valueOf(offset), String.valueOf(maxResult)});
//		while(cursor.moveToNext()){
//			int uid2=cursor.getInt(cursor.getColumnIndex("uid"));
//			String uname=cursor.getString(cursor.getColumnIndex("uname"));
//			String uaddress=cursor.getString(cursor.getColumnIndex("uaddress"));
//			User user=new User();
//			user.setUid(uid2);
//			user.setUname(uname);
//			user.setUaddress(uaddress);
//			users.add(user);
//		}
//		return users;
//	}*/
//    /**
//     * 获取数据总数
//     * @return
//     */
//    public long getCount(){
//
//
//
//        SQLiteDatabase db=dbOpenHelper.getReadableDatabase();
//        Cursor cursor =db.rawQuery("select count(*) from user", null);
//        cursor.moveToFirst();
//        long reslut=cursor.getLong(0);
//        return reslut;
//    }
//    /**
//     * 获取全部数据
//     * @return
//     */
//    public List<Question> getAllQuestion(){
//        List<Question> questions = new ArrayList<Question>();
//        SQLiteDatabase db=dbOpenHelper.getReadableDatabase();
//        Cursor cursor =db.rawQuery("select * from question", null);
//        while(cursor.moveToNext()){
//            int qid = cursor.getInt(cursor.getColumnIndex("qid"));
//            String question = cursor.getString(cursor.getColumnIndex("question"));
//            String answer = cursor.getString(cursor.getColumnIndex("answer"));
//            int time = cursor.getInt(cursor.getColumnIndex("time"));
//            Question q = new Question();
//            q.setQid(qid);
//            q.setQuestion(question);
//            q.setAnswer(answer);
//            q.setTime(time);
//            questions.add(q);
//        }
//        return questions;
//    }
}
