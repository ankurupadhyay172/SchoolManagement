package com.androidcafe.newschoolmanagement.Model;

import com.google.firebase.Timestamp;

public class Fees_Model {

    String id,selected_class,selected_medium,selected_divison,vehicle_fees,exam_fees,first_term_fees,second_term_fees,third_term_fees,fourth_term_fees;
    Timestamp  timestamp;

    public String getFirst_term_fees() {
        return first_term_fees;
    }

    public void setFirst_term_fees(String first_term_fees) {
        this.first_term_fees = first_term_fees;
    }

    public String getSecond_term_fees() {
        return second_term_fees;
    }

    public void setSecond_term_fees(String second_term_fees) {
        this.second_term_fees = second_term_fees;
    }

    public String getThird_term_fees() {
        return third_term_fees;
    }

    public void setThird_term_fees(String third_term_fees) {
        this.third_term_fees = third_term_fees;
    }

    public String getFourth_term_fees() {
        return fourth_term_fees;
    }

    public void setFourth_term_fees(String fourth_term_fees) {
        this.fourth_term_fees = fourth_term_fees;
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

    public String getSelected_divison() {
        return selected_divison;
    }

    public void setSelected_divison(String selected_divison) {
        this.selected_divison = selected_divison;
    }

    public String getVehicle_fees() {
        return vehicle_fees;
    }

    public void setVehicle_fees(String vehicle_fees) {
        this.vehicle_fees = vehicle_fees;
    }

    public String getExam_fees() {
        return exam_fees;
    }

    public void setExam_fees(String exam_fees) {
        this.exam_fees = exam_fees;
    }
}
