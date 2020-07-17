package com.androidcafe.newschoolmanagement.Common;

import android.text.format.DateUtils;
import android.util.Log;
import android.widget.EditText;

import com.androidcafe.newschoolmanagement.Model.Question;
import com.google.firebase.Timestamp;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class common {

    public static String message_type ;
    public static String teacher_name ;

    public static int PDF_REQUEST = 201;
    public static String School_Code ="S_101";
    public static String Branch_code ="S_101";

    public static String News_db = "news_events";




    public static String new_student_db = "new_student_db";
    public static String new_teacher_db = "new_teacher_db";
    public static String chat_room_db = "ChatRoom";
    public static String Notes_db = "School_notes";
    public static String Homework_db = "Home_work";
    public static String timetable = "SchoolTimeTable";


    public static int PERMISSION_REQUEST = 101;

    public static int REQUEST_CODE = 201;


    public static  String term1 = "First Term";
    public static String term2 = "Second Term";
    public static String term3 = "Third Term";
    public static String term4 = "Fourth Term";
    public static String concession="0";

    public static String profile_pic = "na";

    public static String student_medium;
    public static String student_roll_no;
    public static String student_fname;



    public static String getdatetimefromfirebase(Timestamp timestamp)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timestamp.getSeconds()*1000L);

        String date = android.text.format.DateFormat.format("dd/MM/yyyy | hh:mm a",calendar).toString();

        return date;

    }




    //-------------------------------------------------------- dates


    public static String currentdate()
    {
        Date c = Calendar.getInstance().getTime();


        SimpleDateFormat df = new SimpleDateFormat("dd");
        String formatteddate = df.format(c);

        return formatteddate;
    }






    public static String currentMonth()
    {
        DateFormat dateFormat = new SimpleDateFormat("MM");
        Date date = new Date();


        return dateFormat.format(date);
    }





    public static String currentYear()
    {
        DateFormat dateFormat = new SimpleDateFormat("YYYY");
        Date date = new Date();


        return dateFormat.format(date);
    }







    public static String user_type;

    public static String user_id;
    public static String daily_attendance = "Daily_attendance_report";




    public static String mobile_no;
    public static String student_name;
    public static String student_class;
    public static String student_section;
    public static String Fees_Structure = "Fees_Structure";

    public static String school_name;
    public static List<Integer> position = new ArrayList<>();
    public static List<Question> questionlist = new ArrayList<>();




    public static void setQuestionlist(List<Question> questionlist) {
        common.questionlist = questionlist;
    }

    public static List<Question> getQuestionlist() {
        return questionlist;
    }


    //div list


    public static List<String> divlist()
    {
        List<String> list = new ArrayList<>();
        list.add("Select Division");
        list.add("Primary Section (1-5)");
        list.add("Secondry Section (6-8)");
        list.add("Higher Secondary (9-10)");
        list.add("Seniour Secondry (11-12)");
        return list;
    }



    public static List<String> mediumlist()
    {
        List<String> list = new ArrayList<>();
        list.add("Select Medium");
        list.add("CBSE ENGLISH MEDIUM");
        list.add("RBSE ENGLISH MEDIUM");
        list.add("RBSE HINDI MEDIUM");
        return list;
    }




    public static List<String> classlist(int division)
    {
        List<String> list = new ArrayList<>();
        list.add("Select Class");

        if (division==1)
        {

            list.add("Nursery");
            list.add("JK");
            list.add("KG");
            list.add("1st");
            list.add("2nd");
            list.add("3rd");
            list.add("4th");
            list.add("5th");




        }

        if (division==2)

        {
            list.add("6th");
            list.add("7th");
            list.add("8th");
        }

        if (division==3)

        {
            list.add("9th");
            list.add("10th");

        }


        if (division==4)

        {
            list.add("11th");
            list.add("12th");

        }










        return list;
    }





    public static List<String> section()
    {
        List<String> list = new ArrayList<>();
        list.add("Select Class Section");
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");
        list.add("E");
        list.add("F");
        list.add("G");


        return list;

    }



    //notice category spinner
    public static List<String> category()
    {
        List<String> list = new ArrayList<>();
        list.add("All User");
        list.add("Student");
        list.add("Teacher");

        return list;



    }


    public static List<String> subjectlist()
    {

        List<String> list = new ArrayList<>();
        list.add("Select Subject");
        list.add("Hindi");
        list.add("English");
        list.add("Maths");
        list.add("Science");
        list.add("S.S.T");


        return list;



    }



    public static int gettotalsundays(int month,int year)
    {





        int count=0;



        Calendar cal = new GregorianCalendar(year,month,1);
        do {
            int day = cal.get(Calendar.DAY_OF_WEEK);
            if (day== Calendar.SUNDAY)
            {
                Log.e("agetkalu",""+cal.get(Calendar.DAY_OF_MONTH));




                count++;


            }




            cal.add(Calendar.DAY_OF_YEAR,1);

        }
        while (cal.get(Calendar.MONTH)==month);



        return count;

















    }



    public static String news_db = "news_events";
    public static String subscribe_All = "All";
    public static String subscribe_faculty = "Faculty";
    public static String subscribe_student = "Student";
    public static String syllabus_db = "SchoolSyllabus";


    public static boolean isEmpty(EditText etText) {
        if (etText.getText().toString().trim().length() > 0)
            return false;

        return true;
    }

    public static boolean isEmptyString(String text)
    {
        return (text == null ||text.trim().equals("null")||text.trim().length()<0);
    }


    public static String UPLOAD_URL ="https://script.google.com/macros/s/AKfycbwjsZ0SUlvDvdaQcHTc1dPtdWkjFPCXXEdsCFzpBKOOLBTOqNE/exec";


    //------------------------------------------------get time in minutes

    public static CharSequence gethours(long timestamp)
    {
        long time = timestamp*1000L;
        long now = System.currentTimeMillis();

        CharSequence relativetime = DateUtils.getRelativeTimeSpanString(time,now,DateUtils.SECOND_IN_MILLIS,DateUtils.FORMAT_ABBREV_RELATIVE);


        return relativetime;
    }
















}
