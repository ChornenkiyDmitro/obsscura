package com.example.splashscreen;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import static com.example.splashscreen.Constants.KEY_EMAIL;
import static com.example.splashscreen.Constants.KEY_ID;
import static com.example.splashscreen.Constants.KEY_PASSWORD;
import static com.example.splashscreen.Constants.TABLE_NAME;

public class DatabaseHelper extends SQLiteOpenHelper {

    private Context ctx;

    public DatabaseHelper(@Nullable Context context) {
        super(context, Constants.DB_NAME, null, Constants.DB_VERSION);
        this.ctx = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_REGISTER_TABLE = "CREATE TABLE " + Constants.TABLE_NAME + "("
                + Constants.KEY_ID + " INTEGER PRIMARY KEY,"
                + Constants.KEY_USERNAME + " TEXT,"
                + KEY_PASSWORD + " TEXT,"
                + Constants.KEY_EMAIL + " TEXT UNIQUE)";
        Log.d("TableCreated", "done");
        db.execSQL(CREATE_REGISTER_TABLE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_NAME);

        onCreate(db);
    }

    public long addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Constants.KEY_USERNAME, user.getUserName());
        values.put(KEY_PASSWORD, user.getPassword());
        values.put(Constants.KEY_EMAIL, user.getEmail());

        long res = db.insert(Constants.TABLE_NAME, null, values);
        Log.d("Saved!", "saved to DB");
        db.close();
        return res;

    }

    public int updateUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Constants.KEY_USERNAME, user.getUserName());
        values.put(KEY_PASSWORD, user.getPassword());
        values.put(Constants.KEY_EMAIL, user.getEmail());
        return db.update(Constants.TABLE_NAME, values, Constants.KEY_ID + "=?",
                new String[]{String.valueOf(user.getId())});
    }

    public int getUsersCount() {
        String countQuery = "SELECT * FROM " + Constants.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        return cursor.getCount();
    }





    // sign in
    public Boolean checkUser(String email, String password) {
        String[] columns = {Constants.KEY_ID};
        SQLiteDatabase db = getReadableDatabase();
        String selection = Constants.KEY_EMAIL + "=?" + " and " + KEY_PASSWORD + "=?";
        String[] selectionArgs = {email, password};
        Cursor cursor = db.query(Constants.TABLE_NAME, columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        db.close();

        if (count > 0)
            return true;
        else
            return false;
    }
    public String selectOneUserSendUserName(String email, String password) {
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + Constants.TABLE_NAME + " WHERE " + Constants.KEY_EMAIL + " = '"+email.trim()+"'" +" and "+ KEY_PASSWORD + " = '"+password.trim()+"'" , null);
        cursor.moveToFirst();

        int idNumber = 0 ;
        String addUserName = "";
        while (cursor != null) {
            idNumber = Integer.parseInt(cursor.getString(cursor.getColumnIndex(Constants.KEY_ID)));
            addUserName = cursor.getString(cursor.getColumnIndex(Constants.KEY_USERNAME));
            Log.d("tagOneUser", Integer.toString(idNumber) );
            Log.d("tagOneUser", addUserName );
            break;
        }
        cursor.moveToNext();
        return addUserName;
    }
    public int selectOneUserSendId(String email, String password) {
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + Constants.TABLE_NAME + " WHERE " + Constants.KEY_EMAIL + " = '"+email.trim()+"'" +" and "+ KEY_PASSWORD + " = '"+password.trim()+"'" , null);
        cursor.moveToFirst();

        int idNumber = 0 ;
        String addUserName = "";
        while (cursor != null) {
            idNumber = Integer.parseInt(cursor.getString(cursor.getColumnIndex(Constants.KEY_ID)));
            addUserName = cursor.getString(cursor.getColumnIndex(Constants.KEY_USERNAME));
            Log.d("tagOneUser", Integer.toString(idNumber) );
            Log.d("tagOneUser", addUserName );
            break;
        }
        cursor.moveToNext();
        return idNumber;
    }
public void updatePassword(String email, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_PASSWORD, password);
        db.update(TABLE_NAME,values,KEY_EMAIL+" = ?", new String[] {email});
        db.close();
}


    public boolean checkUser1(String email){
        String[] columns = {
                KEY_ID
        };
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = KEY_EMAIL + " = ?";
        String[] selectionArgs = { email };

        Cursor cursor = db.query(TABLE_NAME,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null);
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount > 0){
            return true;
        }
        return false;
    }
}
