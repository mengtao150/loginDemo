package com.example.loginandsqldemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MedicineDBHelper extends SQLiteOpenHelper {
    public MedicineDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String sql="create table tb_medicine(medicine varchar(20) primary key, othername varchar(20),frequency integer)";//创建药物名称，别名，频度
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
