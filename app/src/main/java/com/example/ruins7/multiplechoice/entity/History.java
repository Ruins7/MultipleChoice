package com.example.ruins7.multiplechoice.entity;

/**
 * Created by ruins7 on 2016-09-29.
 */

public class History {

    private int hid;
    private int uid;
    private int qid;
    private String gavenAnswer;
    private String result;
    private int spendTime;

    public int getHid() {
        return hid;
    }

    public void setHid(int hid) {
        this.hid = hid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getQid() {
        return qid;
    }

    public void setQid(int qid) {
        this.qid = qid;
    }

    public String getGavenAnswer() {
        return gavenAnswer;
    }

    public void setGavenAnswer(String gavenAnswer) {
        this.gavenAnswer = gavenAnswer;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getSpendTime() {
        return spendTime;
    }

    public void setSpendTime(int spendTime) {
        this.spendTime = spendTime;
    }
}
