package com.example.shehani.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Shehani on 25/04/2017.
 */
public class Database extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "student.db";
    public static final String TABLE_NAME = "student_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "AGE";
    public static final String COL_4 = "NIC";
    public static final String COL_5 = "ADDRESS";
    public static final String COL_6 = "COURSE";


    public Database(Context context) {
        //creates an object for creating, opening and managing the database
        super(context, DATABASE_NAME, null, 1);
        // 2nd parameter - Database name  4th parameter - db version

    }


    @Override
    public void onCreate(SQLiteDatabase db) { //called only once when database is created for the first time.
        db.execSQL("create table " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT , NAME TEXT , AGE INTEGER , NIC TEXT , ADDRESS TEXT ,COURSE TEXT )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) { //called when database needs to be upgraded.
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME);
        onCreate(db);
    }



    public boolean insertData(String name, String age , String nic , String address ,String course ) {

        SQLiteDatabase db = this.getWritableDatabase();




        Cursor cs = db.rawQuery("select * from student_table where NAME = ? and NIC = ? ", new String[]{name, nic});


        if (cs.getCount() == 0) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(COL_2, name);
            Log.d("name", name);
            contentValues.put(COL_3, age);
            Log.d("age", age);
            contentValues.put(COL_4, nic);
            Log.d("age", nic);
            contentValues.put(COL_5, address);
            Log.d("age", address);
            contentValues.put(COL_6, course);
            Log.d("age", course);


            long result = db.insert(TABLE_NAME, null, contentValues);
            return true;
        }


        return false;
    }


    public Cursor getInformation() {

        SQLiteDatabase sq = this.getWritableDatabase();

        Cursor res = sq.rawQuery("select * from " + TABLE_NAME, null);
        return res;

    }
}
