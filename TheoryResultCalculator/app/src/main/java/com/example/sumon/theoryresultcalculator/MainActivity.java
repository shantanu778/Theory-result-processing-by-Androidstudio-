package com.example.sumon.theoryresultcalculator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
    EditText id,course,credit,ct,at,pa,pb;
    Button storeButton,showButton;
    MyDBFunctions myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        course =(EditText)findViewById(R.id.code_editText);
        credit =(EditText)findViewById(R.id.credit_editText);
        ct =(EditText)findViewById(R.id.ctmark_editText);
        at =(EditText)findViewById(R.id.atndnc_mark_editText);
        pa =(EditText)findViewById(R.id.parta_editText);
        pb =(EditText)findViewById(R.id.partB_editText);
        id = (EditText)findViewById(R.id.editText7);

        storeButton= (Button) findViewById(R.id.add_button);
        showButton= (Button) findViewById(R.id.button2);

        myDB = new MyDBFunctions(this);

    }

    public void store(View v)
    {
        //taking input from user

        String course_code = course.getText().toString();
        String course_credit = credit.getText().toString();
        String ct_mark = ct.getText().toString();
        String attandence_mark = at.getText().toString();
        String part_a_mark = pa.getText().toString();
        String part_b_mark = pb.getText().toString();
        String sid = id.getText().toString();

        if(!course_code.equals("") && !course_credit.equals("") && !ct_mark.equals("") && !attandence_mark.equals("")&&! part_a_mark.equals("") && !part_b_mark.equals("") && !sid.equals("") ) {
            //converting string to double and int
            int Crdt = Integer.parseInt(course_credit);
            int Id = Integer.parseInt(sid);
            double ClassTestMark = Double.parseDouble(ct_mark);
            double AttendanceMark = Double.parseDouble(attandence_mark);
            double PartAMark = Double.parseDouble(part_a_mark);
            double PartBMark = Double.parseDouble(part_b_mark);

            if (Id > 0 && Crdt == 3) {
                if (ClassTestMark >= 0 && ClassTestMark < 16) {
                    if (AttendanceMark >= 0 && AttendanceMark < 8) {
                        if ((PartAMark >= 0 && PartAMark < 27) && (PartBMark >= 0 && PartBMark < 27)) {
                            //Calculating Letter Grade
                            double number = (ClassTestMark + AttendanceMark + PartAMark + PartBMark) / (Crdt * 25);
                            String LetterGrade = cal_grade(number);

                            double point = cal_point(LetterGrade) * Crdt;

                            String pointSecured = String.valueOf(point);


                            // check if data is inserted correctly
                            boolean isInserted = myDB.insertData(sid, course_code, course_credit, LetterGrade, pointSecured);


                            if (isInserted) {
                                Toast.makeText(this, "Data inserted successfully", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(this, "Data insertion failed !!", Toast.LENGTH_LONG).show();
                            }

                            //clearing the text field
                            course.setText("");
                            credit.setText("");
                            ct.setText("");
                            at.setText("");
                            pa.setText("");
                            pb.setText("");
                            id.setText("");
                        }
                    }
                }
            } else if (Id > 0 && Crdt == 2) {
                if (ClassTestMark >= 0 && ClassTestMark < 10) {
                    if (AttendanceMark >= 0 && AttendanceMark < 6) {
                        if ((PartAMark >= 0 && PartAMark < 18) && (PartBMark >= 0 && PartBMark < 18)) {
                            //Calculating Letter Grade
                            double number = (ClassTestMark + AttendanceMark + PartAMark + PartBMark) / (Crdt * 25);
                            String LetterGrade = cal_grade(number);

                            double point = cal_point(LetterGrade) * Crdt;

                            String pointSecured = String.valueOf(point);


                            // check if data is inserted correctly
                            boolean isInserted = myDB.insertData(sid, course_code, course_credit, LetterGrade, pointSecured);


                            if (isInserted) {
                                Toast.makeText(this, "Data inserted successfully", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(this, "Data insertion failed !!", Toast.LENGTH_LONG).show();
                            }

                            //clearing the text field
                            course.setText("");
                            credit.setText("");
                            ct.setText("");
                            at.setText("");
                            pa.setText("");
                            pb.setText("");
                            id.setText("");
                        }
                    }
                }
            } else if (Id > 0 && Crdt == 1) {
                if (ClassTestMark >= 0 && ClassTestMark < 8) {
                    if (AttendanceMark >= 0 && AttendanceMark < 3) {
                        if ((PartAMark >= 0 && PartAMark < 8) && (PartBMark >= 0 && PartBMark < 8)) {
                            //Calculating Letter Grade
                            double number = (ClassTestMark + AttendanceMark + PartAMark + PartBMark) / (Crdt * 25);
                            String LetterGrade = cal_grade(number);

                            double point = cal_point(LetterGrade) * Crdt;

                            String pointSecured = String.valueOf(point);


                            // check if data is inserted correctly
                            boolean isInserted = myDB.insertData(sid, course_code, course_credit, LetterGrade, pointSecured);


                            if (isInserted) {
                                Toast.makeText(this, "Data inserted successfully", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(this, "Data insertion failed !!", Toast.LENGTH_LONG).show();
                            }

                            //clearing the text field
                            course.setText("");
                            credit.setText("");
                            ct.setText("");
                            at.setText("");
                            pa.setText("");
                            pb.setText("");
                            id.setText("");
                        }
                    }
                }
            } else {
                Toast.makeText(this, "please Enter Valid value", Toast.LENGTH_LONG).show();
            }
        }

        else
        {
            Toast.makeText(this,"Please Enter all Field",Toast.LENGTH_LONG).show();
        }

    }

    public void show(View v)
    {
        Intent i = new Intent(this,showActivity.class);
        startActivity(i);

    }

    public void UpdateSub(View v)
    {
        String course_code = course.getText().toString();
        String course_credit = credit.getText().toString();
        String ct_mark = ct.getText().toString();
        String attandence_mark = at.getText().toString();
        String part_a_mark = pa.getText().toString();
        String part_b_mark = pb.getText().toString();
        String sid=id.getText().toString();

        if(!course_code.equals("")) {
            int Crdt = Integer.parseInt(course_credit);
            double ClassTestMark = Double.parseDouble(ct_mark);
            double AttendanceMark = Double.parseDouble(attandence_mark);
            double PartAMark = Double.parseDouble(part_a_mark);
            double PartBMark = Double.parseDouble(part_b_mark);

            double number = (ClassTestMark + AttendanceMark + PartAMark + PartBMark) / (Crdt * 25);
            String LetterGrade = cal_grade(number);

            double point = cal_point(LetterGrade) * Crdt;

            String pointSecured = Double.toString(point);


            //check if update successfull or not
            boolean isUpdated = myDB.updateData(course_code, course_credit, LetterGrade, pointSecured);

            if (isUpdated) {
                Toast.makeText(this, "Data Updated successfully", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Update failed !!", Toast.LENGTH_LONG).show();
            }

            course.setText("");
            credit.setText("");
            ct.setText("");
            at.setText("");
            pa.setText("");
            pb.setText("");
            id.setText("");
        }
        else {
            Toast.makeText(this, "Please Enter Course Code To Update Course", Toast.LENGTH_LONG).show();
        }


    }

    public void DeleteSub(View v)
    {
        String delete_course = course.getText().toString();
        if(!delete_course.equals("")) {
            Integer isDeleted = myDB.deleteData(delete_course);

            if (isDeleted > 0)
                Toast.makeText(MainActivity.this, "Subjet Deleted from Database", Toast.LENGTH_LONG).show();
            else
                Toast.makeText(MainActivity.this, "Not deleted", Toast.LENGTH_LONG).show();

            course.setText("");
        }
       else {
            Toast.makeText(MainActivity.this, "Please Enter Course Code To Delete Course From Database", Toast.LENGTH_LONG).show();
        }

    }

    public void SeeResult(View v)
    {
        startActivity(new Intent(this,ThirdActivity.class));
    }

    //Letter Grade calculation
    public String cal_grade(double marks)
    {
        double numberObtained = marks*100;
        String grade;

       if(numberObtained >= 80)
       {
            grade= "A+";
       }
        else if(numberObtained >=75 && numberObtained <80)
       {
           grade= "A";
       }
        else if(numberObtained >=70 && numberObtained <75)
       {
            grade= "A-";
       }
       else if(numberObtained >=65 && numberObtained <70)
       {
           grade= "B+";
       }
       else if(numberObtained >=60 && numberObtained <65)
       {
            grade= "B";
       }
       else if(numberObtained >=55 && numberObtained <60)
       {
           grade= "B-";
       }
       else if(numberObtained >=50 && numberObtained <55)
       {
            grade= "C+";
       }
       else if(numberObtained >=45 && numberObtained <50)
       {
            grade= "C";
       }
       else if(numberObtained >=40 && numberObtained <45)
       {
            grade= "D";
       }
       else
       {
          grade = "F";
       }

        return grade;

    }

    // Grade point calculation

    public double cal_point(String grade)
    {
        double pointSecured;

        if(grade.equals("A+"))
        {
            pointSecured = 4.00;
        }
        else if(grade.equals("A"))
        {
            pointSecured = 3.75;
        }
        else if(grade.equals("A-"))
        {
            pointSecured = 3.50;
        }
        else if(grade.equals("B+"))
        {
            pointSecured = 3.25;
        }
        else if(grade.equals("B"))
        {
            pointSecured = 3.00;
        }
        else if(grade.equals("B-"))
        {
            pointSecured = 2.75;
        }
        else if(grade.equals("C+"))
        {
            pointSecured = 2.50;
        }
        else if(grade.equals("C"))
        {
            pointSecured = 2.25;
        }
        else if(grade.equals("D"))
        {
            pointSecured = 2.00;
        }
        else
        {
            pointSecured = 0.00;
        }


        return pointSecured;

    }



}
