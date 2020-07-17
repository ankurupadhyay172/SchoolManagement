package com.androidcafe.newschoolmanagement.Model;

import com.google.firebase.Timestamp;

import java.util.List;

public class SyllabusModel {

    String id,description,pdf_url,selected_class,selected_medium,selected_subject;
    Timestamp timestamp;
    boolean isseen;
    List<String> seenusers;


    public List<String> getSeenusers() {
        return seenusers;
    }

    public void setSeenusers(List<String> seenusers) {
        this.seenusers = seenusers;
    }

    public boolean isIsseen() {
        return isseen;
    }

    public void setIsseen(boolean isseen) {
        this.isseen = isseen;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPdf_url() {
        return pdf_url;
    }

    public void setPdf_url(String pdf_url) {
        this.pdf_url = pdf_url;
    }

    public String getSelected_class() {
        return selected_class;
    }

    public void setSelected_class(String selected_class) {
        this.selected_class = selected_class;
    }

    public String getSelected_medium() {
        return selected_medium;
    }

    public void setSelected_medium(String selected_medium) {
        this.selected_medium = selected_medium;
    }

    public String getSelected_subject() {
        return selected_subject;
    }

    public void setSelected_subject(String selected_subject) {
        this.selected_subject = selected_subject;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

