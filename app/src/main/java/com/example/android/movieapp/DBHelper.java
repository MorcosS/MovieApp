package com.example.android.movieapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by MorcosS on 4/9/16.
 */
public class DBHelper extends SQLiteOpenHelper {
    private static final String CREATE_TABLE_Movies = "CREATE TABLE "
            + "Movies" + "(" + "Movie_ID" + " INTEGER PRIMARY KEY," + "Movie_Name"
            + " TEXT , ImageURL TEXT, MovieOverview Text, MovieRate DOUBLE,ReleaseDate TEXT, id INT)";

    public DBHelper(Context context) {
        super(context, "Movies", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_Movies);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public boolean addMovie (int Movie_ID, String MovieName, String ImageURL, String MovieOverView , Double MovieRate, String ReleaseDate,int id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Movie_ID",Movie_ID);
        values.put("Movie_Name", MovieName);
        values.put("ImageURL", ImageURL);
        values.put("MovieOverView", MovieOverView);
        values.put("MovieRate", MovieRate);
        values.put("ReleaseDate", ReleaseDate);
        values.put("id",id);
// Inserting Row
        long movie_row = db.insert("Movies", null, values);
        db.close(); // Closing database connection
        if (movie_row==-1){
            return false;
        }else{
            return true;
        }
    }

    public Cursor getMovie() {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  *"  + " FROM Movies ";
        Cursor c = db.rawQuery(selectQuery, null);

        if (c == null || ! c.moveToFirst()) return null;
        return c;

    }

    public void deleteMovie(int id){
            SQLiteDatabase db = this.getReadableDatabase();
            db.delete("Movies","Movie_ID = "+id,null);
    }
}
