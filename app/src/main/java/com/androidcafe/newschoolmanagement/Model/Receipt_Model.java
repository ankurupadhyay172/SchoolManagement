package com.androidcafe.newschoolmanagement.Model;

import com.google.firebase.Timestamp;

public class Receipt_Model {

    String term_fees,pay_month,student_name,student_class,student_section,orderid,total_amount,student_medium,term,concession_price,concession_per;
    Timestamp timestamp;
    long pay_year;


    public String getTerm_fees() {
        return term_fees;
    }

    public void setTerm_fees(String term_fees) {
        this.term_fees = term_fees;
    }

    public String getPay_month() {
        return pay_month;
    }

    public void setPay_month(String pay_month) {
        this.pay_month = pay_month;
    }

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
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

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(String total_amount) {
        this.total_amount = total_amount;
    }

    public String getStudent_medium() {
        return student_medium;
    }

    public void setStudent_medium(String student_medium) {
        this.student_medium = student_medium;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getConcession_price() {
        return concession_price;
    }

    public void setConcession_price(String concession_price) {
        this.concession_price = concession_price;
    }

    public String getConcession_per() {
        return concession_per;
    }

    public void setConcession_per(String concession_per) {
        this.concession_per = concession_per;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public long getPay_year() {
        return pay_year;
    }

    public void setPay_year(long pay_year) {
        this.pay_year = pay_year;
    }
}
