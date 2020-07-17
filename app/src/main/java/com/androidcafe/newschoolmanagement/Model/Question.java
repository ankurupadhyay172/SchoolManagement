package com.androidcafe.newschoolmanagement.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class Question implements Parcelable {


    String question,opt1,opt2,opt3,opt4,ans,selected,img;


    public Question(String question, String opt1, String opt2, String opt3, String opt4, String ans, String img) {
        this.question = question;
        this.opt1 = opt1;
        this.opt2 = opt2;
        this.opt3 = opt3;
        this.opt4 = opt4;
        this.ans = ans;
        this.img = img;
    }

    public Question(String question, String opt1, String opt2, String opt3, String opt4, String ans) {
        this.question = question;
        this.opt1 = opt1;
        this.opt2 = opt2;
        this.opt3 = opt3;
        this.opt4 = opt4;
        this.ans = ans;
    }




    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }


    public String getSelected() {
        return selected;
    }

    public void setSelected(String selected) {
        this.selected = selected;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOpt1() {
        return opt1;
    }

    public void setOpt1(String opt1) {
        this.opt1 = opt1;
    }

    public String getOpt2() {
        return opt2;
    }

    public void setOpt2(String opt2) {
        this.opt2 = opt2;
    }

    public String getOpt3() {
        return opt3;
    }

    public void setOpt3(String opt3) {
        this.opt3 = opt3;
    }

    public String getOpt4() {
        return opt4;
    }

    public void setOpt4(String opt4) {
        this.opt4 = opt4;
    }

    public String getAns() {
        return ans;
    }

    public void setAns(String ans) {
        this.ans = ans;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.question);
        dest.writeString(this.opt1);
        dest.writeString(this.opt2);
        dest.writeString(this.opt3);
        dest.writeString(this.opt4);
        dest.writeString(this.ans);
    }

    protected Question(Parcel in) {
        this.question = in.readString();
        this.opt1 = in.readString();
        this.opt2 = in.readString();
        this.opt3 = in.readString();
        this.opt4 = in.readString();
        this.ans = in.readString();
    }

    public static final Parcelable.Creator<Question> CREATOR = new Parcelable.Creator<Question>() {
        @Override
        public Question createFromParcel(Parcel source) {
            return new Question(source);
        }

        @Override
        public Question[] newArray(int size) {
            return new Question[size];
        }
    };
}

