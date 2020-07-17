package com.androidcafe.newschoolmanagement.Common;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.androidcafe.newschoolmanagement.R;

public class LoadingClass {

    //---------------------------------------------------------show animation


    public Dialog showLoginDialog(Context context) {



        Dialog test = new Dialog(context);

        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        View view = LayoutInflater.from(context).inflate(R.layout.loading_layout,linearLayout);
        test.setContentView(view);
        test.show();

        return test;


    }
}
