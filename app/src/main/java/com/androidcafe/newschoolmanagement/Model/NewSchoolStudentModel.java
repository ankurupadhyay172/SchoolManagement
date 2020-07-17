package com.androidcafe.newschoolmanagement.Model;

public class NewSchoolStudentModel {



    String id,student_name,student_father_name,student_mobile,student_address,student_gender,student_division,student_medium,student_class,student_section,school_name,token_no,profile_pic,school_code,student_dob;
    String concession;
    int a,p,l,student_roll_no;
    boolean isvehicle,isblock;


    public NewSchoolStudentModel(String id, String student_name, String student_father_name, String student_mobile, String student_address, String student_gender, String student_division, String student_medium, String student_class, String student_section, String school_name, String token_no, String profile_pic, String school_code, String student_dob, String concession, int a, int p, int l, int student_roll_no, boolean isvehicle, boolean isblock) {
        this.id = id;
        this.student_name = student_name;
        this.student_father_name = student_father_name;
        this.student_mobile = student_mobile;
        this.student_address = student_address;
        this.student_gender = student_gender;
        this.student_division = student_division;
        this.student_medium = student_medium;
        this.student_class = student_class;
        this.student_section = student_section;
        this.school_name = school_name;
        this.token_no = token_no;
        this.profile_pic = profile_pic;
        this.school_code = school_code;
        this.student_dob = student_dob;
        this.concession = concession;
        this.a = a;
        this.p = p;
        this.l = l;
        this.student_roll_no = student_roll_no;
        this.isvehicle = isvehicle;
        this.isblock = isblock;
    }

    public NewSchoolStudentModel() {
    }

    public String getConcession() {
        return concession;
    }

    public void setConcession(String concession) {
        this.concession = concession;
    }

    public boolean isIsblock() {
        return isblock;
    }

    public void setIsblock(boolean isblock) {
        this.isblock = isblock;
    }

    public boolean isIsvehicle() {
        return isvehicle;
    }

    public void setIsvehicle(boolean isvehicle) {
        this.isvehicle = isvehicle;
    }

    public String getStudent_dob() {
        return student_dob;
    }

    public void setStudent_dob(String student_dob) {
        this.student_dob = student_dob;
    }

    public String getProfile_pic() {
        return profile_pic;
    }

    public void setProfile_pic(String profile_pic) {
        this.profile_pic = profile_pic;
    }

    public String getToken_no() {
        return token_no;
    }

    public void setToken_no(String token_no) {
        this.token_no = token_no;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public String getStudent_father_name() {
        return student_father_name;
    }

    public void setStudent_father_name(String student_father_name) {
        this.student_father_name = student_father_name;
    }

    public String getStudent_mobile() {
        return student_mobile;
    }

    public void setStudent_mobile(String student_mobile) {
        this.student_mobile = student_mobile;
    }

    public String getStudent_address() {
        return student_address;
    }

    public void setStudent_address(String student_address) {
        this.student_address = student_address;
    }

    public String getStudent_gender() {
        return student_gender;
    }

    public void setStudent_gender(String student_gender) {
        this.student_gender = student_gender;
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

    public String getSchool_name() {
        return school_name;
    }

    public void setSchool_name(String school_name) {
        this.school_name = school_name;
    }

    public String getSchool_code() {
        return school_code;
    }

    public void setSchool_code(String school_code) {
        this.school_code = school_code;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getP() {
        return p;
    }

    public void setP(int p) {
        this.p = p;
    }

    public int getL() {
        return l;
    }

    public void setL(int l) {
        this.l = l;
    }

    public int getStudent_roll_no() {
        return student_roll_no;
    }

    public void setStudent_roll_no(int student_roll_no) {
        this.student_roll_no = student_roll_no;
    }
}

