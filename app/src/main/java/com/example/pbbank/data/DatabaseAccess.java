package com.example.pbbank.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DatabaseAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase db;
    private static DatabaseAccess instance;
    Cursor c = null;
    Cursor c1 = null;
    Cursor c2 = null;
    Cursor c3 = null;
    Cursor c4 = null;
    Cursor c5 = null;

    private DatabaseAccess(Context context){
        this.openHelper = new CustomerDbHelper(context);
    }

    public static DatabaseAccess getInstance(Context context){
        if(instance == null){
            instance = new DatabaseAccess(context);
        }
        return instance;
    }

    public void open(){
        this.db = openHelper.getWritableDatabase();
    }

    public void close(){
        if(db!=null){
            this.db.close();
        }
    }

    public String[] getdatadb(int id){
        String s=Integer.toString(id);
        c = db.rawQuery("SELECT * from user where _id='"+id+"'",null);
        c1 = db.rawQuery("SELECT * from Tranfers where _id='"+id+"'",null);
        c.moveToFirst();
        c1.moveToFirst();
        String name = c.getString(1);
        String email = c.getString(2);
        String add = c.getString(3);
        String ph = c.getString(4);
        float bal = c1.getFloat(1);
        String balance = Float.toString(bal);
        String data[] = {name,email,add,ph,balance};
        c.close();
        c1.close();
        return data;

    }

    public int addamount(int sid,int rid,float amount){
        String s=Integer.toString(sid);
        String r=Integer.toString(rid);

         c2 = db.rawQuery("SELECT * from Tranfers where _id='"+sid+"'",null);
         c2.moveToFirst();
         float smoney = c2.getFloat(1);
         smoney = smoney - amount;

        c3 = db.rawQuery("SELECT * from Tranfers WHERE _id='"+rid+"'",null);
        c3.moveToFirst();
        float rmoney = c3.getFloat(1);
        rmoney = rmoney + amount;
        System.out.print("++Function_data:=\t"+s+"++"+r);
        System.out.println("++Function_smoney:=\t"+rmoney+"++"+amount);


        ContentValues cv = new ContentValues();
        cv.put("current_balance",smoney);
        long sender = db.update("Tranfers",cv,"_id=?",new String[]{s});
        cv.put("current_balance",rmoney);
        long reciever = db.update("Tranfers",cv,"_id=?",new String[]{r});
        if(sender==-1 || reciever==-1){
            return -1;
        }
        else
            return 1;


//
//        c2.close();
//        c3.close();

    }
}
