package com.example.ruins7.multiplechoice.entity;

/**
 * Created by ruins7 on 2016-09-25.
 */

public class Question {
    private int qid;
    private String questionContent;
    private String correntAnswer;
    private String incorrentAnswer1;
    private String incorrentAnswer2;
    private String incorrentAnswer3;
    private int time;

    public int getQid() {
        return qid;
    }

    public void setQid(int qid) {
        this.qid = qid;
    }

    public String getQuestionContent() {
        return questionContent;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }

    public String getCorrentAnswer() {
        return correntAnswer;
    }

    public void setCorrentAnswer(String correntAnswer) {
        this.correntAnswer = correntAnswer;
    }

    public String getIncorrentAnswer1() {
        return incorrentAnswer1;
    }

    public void setIncorrentAnswer1(String incorrentAnswer1) {
        this.incorrentAnswer1 = incorrentAnswer1;
    }

    public String getIncorrentAnswer2() {
        return incorrentAnswer2;
    }

    public void setIncorrentAnswer2(String incorrentAnswer2) {
        this.incorrentAnswer2 = incorrentAnswer2;
    }

    public String getIncorrentAnswer3() {
        return incorrentAnswer3;
    }

    public void setIncorrentAnswer3(String incorrentAnswer3) {
        this.incorrentAnswer3 = incorrentAnswer3;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
