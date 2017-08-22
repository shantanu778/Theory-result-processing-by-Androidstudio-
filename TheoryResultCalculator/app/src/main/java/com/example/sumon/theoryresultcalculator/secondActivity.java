package com.example.sumon.theoryresultcalculator;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.text.DecimalFormat;

public class secondActivity extends AppCompatActivity {
    TextView show;
    double point_acheived,result,total_credit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        show = (TextView) findViewById(R.id.newtxtview);
        MyDBFunctions myDB = new MyDBFunctions(this);
        Cursor c= myDB.getMypoints();
        Cursor c2 =myDB.getCredit();

        if (c.moveToFirst()) {
            point_acheived = c.getDouble(0);
        }
        if(c2.moveToFirst())
        {
            total_credit = c2.getDouble(0);
        }
        if(point_acheived>0 && total_credit >0)
        {
            result = point_acheived/total_credit;
        }
        DecimalFormat precision = new DecimalFormat("0.000");
        show.setText(" Total Credit: "+total_credit+"\n"+"  Your CGPA:  "+(precision.format(result)));

    }

    public void showGreetings(View view)
    {
        String button_text;
        button_text = ((Button) view).getText().toString();
        if(button_text.equals("Open Third Activity"))
        {
            Intent intent = new Intent(this,ThirdActivity.class);
            startActivity(intent);
        }
    }


}
