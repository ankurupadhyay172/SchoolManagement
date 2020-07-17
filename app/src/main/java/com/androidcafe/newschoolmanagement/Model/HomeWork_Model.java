package com.androidcafe.newschoolmanagement.Model;

import com.google.firebase.Timestamp;

public class HomeWork_Model {
    String id,selected_class,selected_section,selected_subject,homework;
    Timestamp timestamp;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSelected_class() {
        return selected_class;
    }

    public void setSelected_class(String selected_class) {
        this.selected_class = selected_class;
    }

    public String getSelected_section() {
        return selected_section;
    }

    public void setSelected_section(String selected_section) {
        this.selected_section = selected_section;
    }

    public String getSelected_subject() {
        return selected_subject;
    }

    public void setSelected_subject(String selected_subject) {
        this.selected_subject = selected_subject;
    }

    public String getHomework() {
        return homework;
    }

    public void setHomework(String homework) {
        this.homework = homework;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }


}
