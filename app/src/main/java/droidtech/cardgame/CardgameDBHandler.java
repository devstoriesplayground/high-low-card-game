package droidtech.cardgame;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class CardgameDBHandler extends SQLiteOpenHelper {
    private SQLiteDatabase db;
    private Cursor cursor;
    private static final String DATABASE_NAME = "cardgame.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_CARD_GAME = "tbl_cardgame";
    public static final String COLUMN_ID = "empId";
    public static final String COLUMN_FULLNAME = "fullname";
    public static final String COLUMN_USER_NAME = "username";
    public static final String COLUMN_USER_PASS = "userpass";

    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_CARD_GAME + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_FULLNAME + " TEXT, " +
                    COLUMN_USER_NAME + " TEXT, " +
                    COLUMN_USER_PASS + " TEXT " + ")";

    public CardgameDBHandler(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);

    }

    public void addUser(String fullname, String username, String userpass , SQLiteDatabase db){

        ContentValues contentValue = new ContentValues();
        contentValue.put(COLUMN_FULLNAME,fullname);
        contentValue.put(COLUMN_USER_NAME,username);
        contentValue.put(COLUMN_USER_PASS,userpass);
        db.insert(TABLE_CARD_GAME,null,contentValue);
        Log.e("DATABASE OPERATION", "One row is insert");
    }

    public synchronized boolean isValid(String username, String password){
        String query = "SELECT * FROM " + TABLE_CARD_GAME  + " where username = '" + username + "' and userpass = '" + password + "'";
        db = this.getWritableDatabase();
        cursor = db.rawQuery(query, null);
        try{
            if(cursor.moveToFirst()){
                return true;
            }
        }catch(SQLException e){
            Log.d("SQL ERROR", e.getMessage());
        }finally{
            cursor.close();
        }
        return false;
    }

    public synchronized Cardgame getUserByUsernamePassword(String username, String password){
        Cardgame user = new Cardgame();
        String query = "SELECT * FROM " + TABLE_CARD_GAME + " where username = '" + username + "' and userpass = '" + password + "'";
        db = this.getWritableDatabase();
        cursor = db.rawQuery(query, null);
        try{
            if(cursor.moveToFirst()){
                do{
                    user.setFullname(cursor.getString(0));
                    user.setUsername(cursor.getString(1));
                    user.setUserpass(cursor.getString(2));
                }while(cursor.moveToNext());
            }else{
                return null;
            }
        }catch(SQLException e){
            Log.d("SQL ERROR", e.getMessage());
            return null;
        }finally{
            cursor.close();
        }
        return user;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_CARD_GAME);
        db.execSQL(TABLE_CREATE);
    }
}