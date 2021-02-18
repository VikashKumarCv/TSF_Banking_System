package com.tsf.vikash;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private String TABLE_NAME = "user_table";
    private String TABLE_NAME1 = "transfers_table";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "User.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (PHONENUMBER INTEGER PRIMARY KEY ,NAME TEXT,BALANCE DECIMAL,EMAIL VARCHAR,ACCOUNT_NO VARCHAR,IFSC_CODE VARCHAR)");
        db.execSQL("create table " + TABLE_NAME1 +" (TRANSACTIONID INTEGER PRIMARY KEY AUTOINCREMENT,DATE TEXT,FROMNAME TEXT,TONAME TEXT,AMOUNT DECIMAL,STATUS TEXT)");
        db.execSQL("insert into user_table values(1204567890,'Varsha',8472.00,'varsha@gmail.com','XXXXXXXXXXXX0031','DBC09876540')");
        db.execSQL("insert into user_table values(1025678901,'Moin',182.67,'moin@gmail.com','XXXXXXXXXXXX0042','MCA98765430')");
        db.execSQL("insert into user_table values(0126789012,'Abhay',1000.56,'abhay@gmail.com','XXXXXXXXXXXX0017','CAB87654320')");
        db.execSQL("insert into user_table values(1107890123,'Vikku',15500.01,'Vikku.Vikash@gmail.com','XXXXXXXXXXXX9024','FBC77543231')");
        db.execSQL("insert into user_table values(0018901234,'Shubhansu',298.48,'shubh@gmail.com','XXXXXXXXXXXX0942','BCC65432109')");
        db.execSQL("insert into user_table values(0009012345,'Deepak',975.16,'deepu@gmail.com','XXXXXXXXXXXX4359','NAB54321099')");
        db.execSQL("insert into user_table values(1110123456,'Priya',6936.00,'priyaqueen@gmail.com','XXXXXXXXXXXX0525','ZBC43210984')");
        db.execSQL("insert into user_table values(0951234567,'Vicky',757.22,'vicky@gmail.com','XXXXXXXXXXXX2532','XCA32109879')");
        db.execSQL("insert into user_table values(9992345678,'Omprakash',2398.46,'om@gmail.com','XXXXXXXXXXXX4579','LAB21098766')");
        db.execSQL("insert into user_table values(1324567809,'Himanshu',2573.90,'Himansh44@gmail.com','XXXXXXXXXXXX9000','SBC10987659')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME1);
        onCreate(db);
    }

    public Cursor readalldata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table", null);
        return cursor;
    }

    public Cursor readparticulardata(String phonenumber){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table where phonenumber = " +phonenumber, null);
        return cursor;
    }

    public Cursor readselectuserdata(String phonenumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table except select * from user_table where phonenumber = " +phonenumber, null);
        return cursor;
    }

    public void updateAmount(String phonenumber, String amount){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("update user_table set balance = " + amount + " where phonenumber = " +phonenumber);
    }

    public Cursor readtransferdata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from transfers_table", null);
        return cursor;
    }

    public boolean insertTransferData(String date,String from_name, String to_name, String amount, String status){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("DATE", date);
        contentValues.put("FROMNAME", from_name);
        contentValues.put("TONAME", to_name);
        contentValues.put("AMOUNT", amount);
        contentValues.put("STATUS", status);
        Long result = db.insert(TABLE_NAME1, null, contentValues);
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }
}
