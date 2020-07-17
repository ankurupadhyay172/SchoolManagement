package com.androidcafe.newschoolmanagement.Model;

import java.util.List;

public class AllModel {

    String id,student_division,student_medium,student_class,student_section,student_subject,start_time,end_time,teacher_name,selected_period;

    List<String> selected_days;
    int start_hours;


    public int getStart_hours() {
        return start_hours;
    }

    public void setStart_hours(int start_hours) {
        this.start_hours = start_hours;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSelected_period() {
        return selected_period;
    }

    public void setSelected_period(String selected_period) {
        this.selected_period = selected_period;
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

    public String getStudent_class() {
        return student_class;
    }

    public void setStudent_class(String student_class) {
        this.student_class = student_class;
    }

    public String getStudent_section() {
        return student_section;
    }

    public void setStudent_section(String student_section) {
        this.student_section = student_section;
    }

    public String getStudent_subject() {
        return student_subject;
    }

    public void setStudent_subject(String student_subject) {
        this.student_subject = student_subject;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getTeacher_name() {
        return teacher_name;
    }

    public void setTeacher_name(String teacher_name) {
        this.teacher_name = teacher_name;
    }

    public List<String> getSelected_days() {
        return selected_days;
    }

    public void setSelected_days(List<String> selected_days) {
        this.selected_days = selected_days;
    }
}

