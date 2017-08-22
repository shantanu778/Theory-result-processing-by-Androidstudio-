package com.example.sumon.theoryresultcalculator;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class MyDBFunctions extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Mydb";
    private static final String TABLE_NAME = "Mytab";
    private static final String TB_ID = "id";
    private static final String TB_COURSE ="course";
    private static final String TB_CREDIT ="credit";
    private static final String TB_GRADE ="grade";
    private static final String TB_POINT ="point";
    public MyDBFunctions(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String s ="CREATE TABLE "+TABLE_NAME+" ( "+
                TB_ID+" INTEGER PRIMARY KEY, "+
                TB_COURSE+" TEXT NOT NULL, "+
                TB_CREDIT+" TEXT NOT NULL, "+
                TB_GRADE+" TEXT NOT NULL, "+
                TB_POINT+" TEXT NOT NULL )";
        db.execSQL(s);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST" + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String sid,String course_code,String course_credit,String grade,String point)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TB_ID, sid);
        contentValues.put(TB_COURSE, course_code);
        contentValues.put(TB_CREDIT, course_credit);
        contentValues.put(TB_GRADE, grade);
        contentValues.put(TB_POINT, point);

        long result = db.insert(TABLE_NAME, null, contentValues);
        db.close();

        if (result == -1)
            return false;
        else return true;
    }

    public Cursor getAllData()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME+" ORDER BY "+TB_ID, null);

        return res;
    }

    public Cursor getDataByID(int id)
    {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor rest = db.rawQuery("select * from " + TABLE_NAME + " where "+ TB_ID+" = ? ORDER BY "+TB_ID, new String[]{id+""});


        return rest;
    }


    public boolean updateData(String course_code,String course_credit,String grade,String point)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TB_COURSE, course_code);
        contentValues.put(TB_CREDIT, course_credit);
        contentValues.put(TB_GRADE, grade);
        contentValues.put(TB_POINT, point);


        db.update(TABLE_NAME, contentValues, "course = ?", new String[]{course_code});
        db.close();
        return true;

    }

    public Integer deleteData (String course_code){

        SQLiteDatabase db = this.getWritableDatabase();

        return db.delete(TABLE_NAME, "course  = ?", new String[] {course_code});
    }

    public  Cursor getMypoints() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select sum("+TB_POINT+") from " + TABLE_NAME, null);
        return cursor;
    }
    public Cursor getCredit()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select sum("+TB_CREDIT+") from " + TABLE_NAME, null);
        return cursor;
    }






}
