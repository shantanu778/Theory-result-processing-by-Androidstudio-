package com.example.sumon.theoryresultcalculator;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

public class ThirdActivity extends AppCompatActivity {

    EditText Search_name;
    Button SearchButton;
    WebView grade_web_view;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        Search_name = (EditText)findViewById(R.id.search_name);
        SearchButton = (Button)findViewById(R.id.searchB);

        grade_web_view = (WebView)findViewById(R.id.gradeWebView);




    }

    public void searchContact(View view){

        int id = Integer.parseInt(Search_name.getText().toString());
        MyDBFunctions myDB = new MyDBFunctions(this);
        Cursor  idcursor =  myDB.getDataByID(id);
        String html;
        if(idcursor.getCount()==0){
            html = "No Data Found";
            grade_web_view.loadData(html,"text/html","UTF-8");
            return ;
        }

        html = "<html>"+
                "<head>" +
                "<style>td{padding:10px;}</style>" +
                "<body style='text-align:center; background-color:;'>" +
                "</div>" +
                "<table border='1' style='width:100%;text-align:left;'>"
                + "<tr style='background:#9a9;'>"
                +   "<td>ID</td>"
                +   "<td>Course Code</td>"
                +   "<td>Credit</td>"
                +   "<td>Grade</td>"
                +   "<td>Point Secured</td>"
                +   "</tr>";

        for(idcursor.moveToFirst(); !idcursor.isAfterLast(); idcursor.moveToNext()){

            html += "<tr>"
                    + "<td>"+idcursor.getInt(0)+"</td>"
                    + "<td>"+idcursor.getString(1)+"</td>"
                    + "<td>"+idcursor.getString(2)+"</td>"
                    + "<td>"+idcursor.getString(3)+"</td>"
                    + "<td>"+idcursor.getString(4)+"</td>"
                    +"</tr>";
        }

        grade_web_view.loadData(html,"text/html","UTF-8");

    }




}
