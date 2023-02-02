package com.labo.code.ace.oei.vo;

/**
 * Created by Chui Eng on 17/4/2017.
 */

public class AnswerVO {
    private String answer;
    private String gender;
    private Boolean oldInd;

    public AnswerVO(String answer, String gender, Boolean oldInd) {
        this.answer = answer;
        this.gender = gender;
        this.oldInd = oldInd;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Boolean getOldInd() {
        return oldInd;
    }

    public void setOldInd(Boolean oldInd) {
        this.oldInd = oldInd;
    }
}
