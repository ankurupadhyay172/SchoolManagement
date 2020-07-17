package com.androidcafe.newschoolmanagement.Student;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.androidcafe.newschoolmanagement.Common.common;
import com.androidcafe.newschoolmanagement.Model.AttendanceModel;
import com.androidcafe.newschoolmanagement.Model.HolidayModel;
import com.androidcafe.newschoolmanagement.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.List;

public class ViewAttendanceActivity extends AppCompatActivity {


    MaterialCalendarView calendarView;


    Collection<CalendarDay> sunday;
    Collection<CalendarDay> holiday = new ArrayList<>();
    Collection<CalendarDay> present ;
    Collection<CalendarDay> absent ;
    Collection<CalendarDay> leave ;

    FirebaseFirestore db;



    private static String TAG = "Piechart";
    private Integer[] yData;
    int counter ;

    String charttag;
    private String[] xData = {"Holiday","Present day","Absent day","On Leave"};
    PieChart pieChart;


    List<HolidayModel> holilist;
    TextView msg;

    int month,year;

    Dialog test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_attendance);

        calendarView = (MaterialCalendarView)findViewById(R.id.reccalendar);
        db = FirebaseFirestore.getInstance();

        msg = (TextView)findViewById(R.id.msg);


        sunday = new ArrayList<>();


        yData = new Integer[4];



        for (int i=0;i<12;i++) {

            getallsundays(i);
        }







        //Get All Attendance


        //get sunday in this month

        Calendar calendar = Calendar.getInstance();

        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        getAllAttendance(month);





        getAllHolidays(Calendar.getInstance().get(Calendar.MONTH));



        //Implement the month change listener

        calendarView.setOnMonthChangedListener(new OnMonthChangedListener() {
            @Override
            public void onMonthChanged(MaterialCalendarView widget, CalendarDay date) {
                // Toast.makeText(newReciveAttendance.this, "month "+date.getMonth(), Toast.LENGTH_SHORT).show();

                month = date.getMonth();
                year = date.getYear();



                getAllHolidays(date.getMonth());

                getAllAttendance(date.getMonth());
            }
        });




















        // calendarView.addDecorator(new CircleDecorator(this,R.drawable.sundaybgshape,sunday));

        calendarView.addDecorator(new CircleDecorator(this,R.drawable.holydaybgshape,sunday));







        //implement chart


        pieChart = (PieChart)findViewById(R.id.idpiechat);



        pieChart.setDescription("Powered By School Management");
        pieChart.setRotationEnabled(true);
        pieChart.setHoleRadius(25f);

        pieChart.setTransparentCircleAlpha(0);
        pieChart.setCenterText("Attendance Chart");
        pieChart.setCenterTextSize(10);
        pieChart.setDrawEntryLabels(true);







        yData[0]= common.gettotalsundays(month,year);

        yData[1]= 0;
        yData[2]= 0;
        yData[3]= 0;





        pieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                Log.e("Kabiraman",":Value selected from chart");
                Log.e("Kabiraman",":Value selected "+e.toString());
                Log.e("Kabiraman",":Value selected "+h.toString());



            }

            @Override
            public void onNothingSelected() {

            }
        });


    }

    private void getAllHolidays(final int month)
    {
        showLoginDialog();
        db.collection(common.School_Code).document(common.Branch_code).collection("Holidaylist").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete( Task<QuerySnapshot> task) {

                if (task.isSuccessful())
                {
                    test.dismiss();

                    holilist = new ArrayList<>();
                    for (QueryDocumentSnapshot document:task.getResult())
                    {


                        try {

                            final HolidayModel model = document.toObject(HolidayModel.class);



                            if (model.getMonth()==month)
                            {
                                test.dismiss();
                                Calendar cal = new GregorianCalendar(model.getYear(),model.getMonth(),model.getDay());

                                holilist.add(model);

                                CalendarDay holi = CalendarDay.from(cal);
                                holiday.add(holi);

                                calendarView.addDecorator(new CircleDecorator(ViewAttendanceActivity.this,R.drawable.holydaybgshape,holiday));

                                handletheclick(holilist);

                            }


                        }catch (NullPointerException e)
                        {
                            Toast.makeText(ViewAttendanceActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }



                    }
                    yData[0] = holilist.size()+common.gettotalsundays(month,year);

                    addDataSet();




                }


            }
        });


    }



    private void getallsundays(int i) {




        for (int year=2018;year<=2022;year++)
        {




            Calendar cal = new GregorianCalendar(year,i,1);
            do {
                int day = cal.get(Calendar.DAY_OF_WEEK);
                if (day== Calendar.SUNDAY)
                {
                    Log.e("agetkalu",""+cal.get(Calendar.DAY_OF_MONTH));




                    CalendarDay absentcalendar = CalendarDay.from(cal);
                    sunday.add(absentcalendar);


                }




                cal.add(Calendar.DAY_OF_YEAR,1);

            }
            while (cal.get(Calendar.MONTH)==i);








        }

    }




    private void getAllAttendance(final int month1) {


        db.collection(common.School_Code).document(common.Branch_code).collection(common.new_student_db).document(common.user_id).collection(common.daily_attendance).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(Task<QuerySnapshot> task) {

                if (task.isSuccessful())
                {






                    if (task.getResult().isEmpty())
                    {


                    }


                    else
                    {

                        absent = new ArrayList<>();
                        leave = new ArrayList<>();

                        present = new ArrayList<>();

                        for (QueryDocumentSnapshot doument:task.getResult())
                        {


                            AttendanceModel model = doument.toObject(AttendanceModel.class);

                            if (Integer.parseInt(model.getMonth())-1==month1)
                            {

                                if (model.getP()==1)
                                {


                                    Log.e("innerroll",""+model.getDay());




                                    Calendar cal = new GregorianCalendar(Integer.parseInt(model.getYear()),Integer.parseInt(model.getMonth())-1,Integer.parseInt(model.getDay()));
                                    CalendarDay absentcalendar = CalendarDay.from(cal);
                                    present.add(absentcalendar);
                                    calendarView.addDecorator(new CircleDecorator(ViewAttendanceActivity.this,R.drawable.presentdaybgshape,present));


                                }


                                if (model.getA()==1)
                                {
                                    Log.e("innerroll",""+model.getDay());




                                    Calendar cal = new GregorianCalendar(Integer.parseInt(model.getYear()),Integer.parseInt(model.getMonth())-1,Integer.parseInt(model.getDay()));
                                    CalendarDay absentcalendar = CalendarDay.from(cal);
                                    absent.add(absentcalendar);
                                    calendarView.addDecorator(new CircleDecorator(ViewAttendanceActivity.this,R.drawable.absentbgshape,absent));




                                }

                                if (model.getL()==1)
                                {
                                    Log.e("innerroll",""+model.getDay());




                                    Calendar cal = new GregorianCalendar(Integer.parseInt(model.getYear()),Integer.parseInt(model.getMonth())-1,Integer.parseInt(model.getDay()));
                                    CalendarDay absentcalendar = CalendarDay.from(cal);
                                    leave.add(absentcalendar);
                                    calendarView.addDecorator(new CircleDecorator(ViewAttendanceActivity.this,R.drawable.leaveshape,leave));



                                }



                            }



















                        }


                        yData[1]= present.size();
                        yData[2]= absent.size();
                        yData[3]= leave.size();




                        if (leave.size()==0)
                        {
                            xData[3]="";

                        }
                        else
                        {
                            xData[3]="On Leave";
                        }

                        if (absent.size()==0)
                        {
                            xData[2]="";
                        }
                        else
                        {
                            xData[2]="Absent";
                        }

                        if (present.size()==0)
                        {
                            xData[1]="";

                        }
                        else
                        {
                            xData[1]="Present";
                        }


                        addDataSet();






                    }

                }



            }
        });





    }


//--------------------------------------------------------------------show pie chart


    private void addDataSet() {






        ArrayList<PieEntry> yEntry = new ArrayList<>();
        final ArrayList<String> xEntry = new ArrayList<>();

        for (int i=0;i<yData.length;i++)
        {
            yEntry.add(new PieEntry(yData[i],i));

        }

        for (int i=0;i<xData.length;i++)
        {
            xEntry.add(xData[i]);
        }








        //create the data set
        PieDataSet pieDataSet = new PieDataSet(yEntry,"Student Attendance");


        counter=-1;

        pieDataSet.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {

                counter = counter+1;




                try {





                    charttag = xEntry.get(counter);
                    String temp = String.valueOf((int)value+" "+charttag);
                    if (value==0)
                        temp="";

                    return temp;

                }catch (IndexOutOfBoundsException e)
                {
                    counter=0;
                    charttag= xEntry.get(counter);
                }
                catch (NullPointerException e)
                {

                }





                // Toast.makeText(newReciveAttendance.this, ""+xEntry.get(counter), Toast.LENGTH_SHORT).show();

                return String.valueOf((int)value+" "+charttag);

            }
        });








        pieDataSet.setSliceSpace(2);
        pieDataSet.setValueTextSize(8);


        //add colors to dataset
        ArrayList<Integer> colors = new ArrayList<>();


        //holiday
        colors.add(Color.parseColor("#ffffbb33"));
        //present
        colors.add(Color.parseColor("#ff669900"));
        //absent
        colors.add(Color.parseColor("#ffcc0000"));

        //leave
        colors.add(Color.parseColor("#ff33b5e5"));


        pieDataSet.setColors(colors);


        //add legend to chart
        Legend legend = pieChart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setPosition(Legend.LegendPosition.LEFT_OF_CHART);


        //create pie data object
        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.invalidate();


    }



    //--------------------------------------------- Handle the date click
    private void handletheclick(final List<HolidayModel> holilist)
    {
        calendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected( MaterialCalendarView widget, CalendarDay date, boolean selected) {

                msg.setVisibility(View.GONE);
                for (HolidayModel i:holilist)
                {
                    if (i.getDay()==date.getDate().getDate()&&i.getMonth()==date.getMonth())
                    {
                        msg.setText(i.getDesc());
                        msg.setVisibility(View.VISIBLE);

                    }





                }



            }
        });



    }


    //handle the dialoge


    private void showLoginDialog() {



        test = new Dialog(this);
        test.setTitle("test ");
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        View view = LayoutInflater.from(this).inflate(R.layout.loading_layout,linearLayout);
        test.setContentView(view);
        test.show();



    }

}
