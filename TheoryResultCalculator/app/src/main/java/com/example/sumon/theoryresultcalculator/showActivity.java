package com.example.sumon.theoryresultcalculator;

import android.app.Activity;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

public class showActivity extends Activity {

    String htmlString;
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        webView = (WebView) findViewById(R.id.showDataWebView);

        htmlString = "<html>"+
                            "<head>" +
                                    "<style>td{padding:10px;}</style>" +
                            "<body style='text-align:center;'>" +
                                "</div>" +
                                    "<table border='1' style='width:100%;text-align:left;'>"
                                        + "<tr style='background:#9a9;'>"
                                        +   "<td>student id</td>"
                                        +   "<td>Course Code</td>"
                                        +   "<td>Credit</td>"
                                        +   "<td>Grade</td>"
                                        +   "<td>Point Secured</td>"
                                        +   "</tr>";

        MyDBFunctions myDB = new MyDBFunctions(this);


        Cursor res = myDB.getAllData();

        if (res.getCount() == 0) {
            Toast.makeText(this, "No data found", Toast.LENGTH_LONG).show();
            return;
        }
        else {
            for (res.moveToFirst(); !res.isAfterLast(); res.moveToNext()){

                htmlString += "<tr>"+
                                "<td>"+res.getInt(0)+"</td>"+
                                "<td>"+res.getInt(1)+"</td>"+
                                "<td>"+res.getString(2)+"</td>"+
                                "<td>"+res.getString(3)+"</td>"+
                                "<td>"+res.getString(4)+"</td>"+
                            "</tr>";

            }
        }

        htmlString += "</table></body></html>";

        webView.loadData(htmlString,"text/html","UTF-8");
    }
}
