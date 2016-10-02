package com.example.ruins7.multiplechoice.entity;

/**
 * Created by ruins7 on 2016-09-29.
 */

public class History {

    private int hid;
    private int uid;
    private int qid;
    private String gavenAnswer;
    private Long result;
    private int time;

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

    public Long getResult() {
        return result;
    }

    public void setResult(Long result) {
        this.result = result;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
