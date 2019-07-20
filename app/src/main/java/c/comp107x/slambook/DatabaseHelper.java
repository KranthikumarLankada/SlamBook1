package c.comp107x.slambook;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME="slambook.db";
    public static final String TABLE_NAME="slambook_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "NICKNAME";
    public static final String COL_4 = "DATEOFBIRTH";
    public static final String COL_5 = "EMAIL";
    public static final String COL_6 = "MOBILE";
    public static final String COL_7 = "ACHIVEMENT";
    public static final String COL_8 = "PLACE";
    public static final String COL_9 = "FOOD";
    public static final String COL_10 = "ADDRESS";
 //   public static final String COL_11 = "HOOBIES";
 //   public static final String COL_12 = "ADDRESS";


    public DatabaseHelper(Context context){
        super(context,DATABASE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+TABLE_NAME+"(ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,NICKNAME TEXT,DATEOFBIRTH TEXT,EMAIL TEXT,MOBILE TEXT,ACHIVEMENT TEXT,PLACE TEXT,FOOD TEXT,ADDRESS TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String name,String nickname,String dateofbirth,String email,String mobile,String achivement,String place,String food,String address){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,nickname);
        contentValues.put(COL_4,dateofbirth);
        contentValues.put(COL_5,email);
        contentValues.put(COL_6,mobile);
        contentValues.put(COL_7,achivement);
        contentValues.put(COL_8,place);
        contentValues.put(COL_9,food);
        contentValues.put(COL_10,address);
//        contentValues.put(COL_11,hobbies);
//        contentValues.put(COL_12,address);
        long result=db.insert(TABLE_NAME,null,contentValues);
        if (result==-1)
            return false;
        else
            return true;
    }

    public Cursor getAllData(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }

    public boolean updateData(String id,String name,String nickname,String dateofbirth,String email,String mobile,String achivement,String place,String food,String address){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,nickname);
        contentValues.put(COL_4,dateofbirth);
        contentValues.put(COL_5,email);
        contentValues.put(COL_6,mobile);
        contentValues.put(COL_7,achivement);
        contentValues.put(COL_8,place);
        contentValues.put(COL_9,food);
        contentValues.put(COL_10,address);
     //   contentValues.put(COL_11,hobbies);
     //   contentValues.put(COL_12,address);

        db.update(TABLE_NAME,contentValues,"ID=?",new String[]{id});
        return true;
    }

    public Integer deleteData(String id){
        SQLiteDatabase db=this.getWritableDatabase();
        return db.delete(TABLE_NAME,"ID=?",new String[]{id});
    }
  /*  public Cursor viewdata(String id,String name,String nickname,String dateofbirth,String email,String mobile,String achivement,String place,String food,String color,String address){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor c=db.rawQuery("select name from "+TABLE_NAME,null);
        return c;
    }*/
}
