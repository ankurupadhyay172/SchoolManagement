package com.androidcafe.newschoolmanagement.Model;

import com.google.firebase.Timestamp;

import java.util.List;

public class NotesModel {

    String id,notes_file,notes_message,notes_title,student_class,student_division,student_medium,subject_name,teacher_name;
    Timestamp timestamp;

    List<String> seenusers;

    public List<String> getSeenusers() {
        return seenusers;
    }

    public void setSeenusers(List<String> seenusers) {
        this.seenusers = seenusers;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNotes_file() {
        return notes_file;
    }

    public void setNotes_file(String notes_file) {
        this.notes_file = notes_file;
    }

    public String getNotes_message() {
        return notes_message;
    }

    public void setNotes_message(String notes_message) {
        this.notes_message = notes_message;
    }

    public String getNotes_title() {
        return notes_title;
    }

    public void setNotes_title(String notes_title) {
        this.notes_title = notes_title;
    }

    public String getStudent_class() {
        return student_class;
    }

    public void setStudent_class(String student_class) {
        this.student_class = student_class;
    }

    public String getStudent_division() {
        return student_division;
    }

    public void setStudent_division(String student_division) {
        this.student_division = student_division;
    }

    public String getStudent_medium() {
        return student_medium;
    }

    public void setStudent_medium(String student_medium) {
        this.student_medium = student_medium;
    }

    public String getSubject_name() {
        return subject_name;
    }

    public void setSubject_name(String subject_name) {
        this.subject_name = subject_name;
    }

    public String getTeacher_name() {
        return teacher_name;
    }

    public void setTeacher_name(String teacher_name) {
        this.teacher_name = teacher_name;
    }
}

