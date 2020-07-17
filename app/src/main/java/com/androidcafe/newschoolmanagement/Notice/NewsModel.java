package com.androidcafe.newschoolmanagement.Notice;

import com.google.firebase.Timestamp;

public class NewsModel {

    String id,Message,Title,category,file_url;
    Timestamp TimeStamp;
    boolean isseen;


    public String getFile_url() {
        return file_url;
    }

    public void setFile_url(String file_url) {
        this.file_url = file_url;
    }

    public boolean isIsseen() {
        return isseen;
    }

    public void setIsseen(boolean isseen) {
        this.isseen = isseen;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }



    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Timestamp getTimeStamp() {
        return TimeStamp;
    }

    public void setTimeStamp(Timestamp timeStamp) {
        TimeStamp = timeStamp;
    }
}
