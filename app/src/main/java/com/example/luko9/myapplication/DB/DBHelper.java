package com.example.luko9.myapplication.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.luko9.myapplication.Entity.TvShows;

import java.util.ArrayList;
import java.util.HashMap;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "TvShows.db";
    public static final String SHOWS_TABLE_NAME = "shows";
    public static final String SHOWS_COLUMN_ID = "id";
    public static final String SHOWS_COLUMN_NAME = "name";
    public static final String SHOWS_COLUMN_SUMMARY = "summary";
    public static final String SHOWS_COLUMN_IMAGE = "image";
    public static final String SHOWS_COLUMN_BIGIMAGE = "bigImage";
    public static final String SHOWS_COLUMN_DAYS = "days";
    public static final String SHOWS_COLUMN_STATUS = "status";
    public static final String SHOWS_COLUMN_GENRES = "genres";
    public static final String SHOWS_COLUMN_RUNTIME = "runtime";
    public static final String SHOWS_COLUMN_WEIGHT = "weight";
    public static final String SHOWS_COLUMN_RATING = "rating";
    public static final String SHOWS_COLUMN_ISFAVOURITE = "favourite";

    private HashMap hp;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 3);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "create table " + SHOWS_TABLE_NAME +
                        "(" +
                        SHOWS_COLUMN_ID + " integer primary key," +
                        SHOWS_COLUMN_NAME + " text," +
                        SHOWS_COLUMN_SUMMARY + " text," +
                        SHOWS_COLUMN_IMAGE + " text," +
                        SHOWS_COLUMN_BIGIMAGE + " text," +
                        SHOWS_COLUMN_DAYS + " text," +
                        SHOWS_COLUMN_STATUS + " text," +
                        SHOWS_COLUMN_GENRES + " text," +
                        SHOWS_COLUMN_RUNTIME + " text," +
                        SHOWS_COLUMN_WEIGHT + " text," +
                        SHOWS_COLUMN_ISFAVOURITE + " text," +
                        SHOWS_COLUMN_RATING + " text)"

        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS contacts");
        onCreate(db);
    }

    public boolean insertShow(TvShows show) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", show.getId());
        contentValues.put("name", show.getName());
        contentValues.put("summary", show.getSummary());
        contentValues.put("image", show.getImage());
        contentValues.put("bigimage", show.getBigImage());
        contentValues.put("days", show.getDays());
        contentValues.put("runtime", show.getRuntime());
        contentValues.put("weight", show.getWeight());
        contentValues.put("status", show.getStatus());
        contentValues.put("genres", show.getGenres());
        contentValues.put("rating", show.getRating());
        contentValues.put("favourite", show.getIsFavourite());
        db.insert("shows", null, contentValues);
        return true;
    }

    public TvShows getData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from shows where id=" + id + "", null);

        try {
            res.moveToFirst();
            TvShows show = new TvShows();
            show.setId(res.getInt(res.getColumnIndex(SHOWS_COLUMN_ID)));
            show.setName(res.getString(res.getColumnIndex(SHOWS_COLUMN_NAME)));
            show.setImage(res.getString(res.getColumnIndex(SHOWS_COLUMN_IMAGE)));
            show.setBigImage(res.getString(res.getColumnIndex(SHOWS_COLUMN_BIGIMAGE)));
            show.setSummary(res.getString(res.getColumnIndex(SHOWS_COLUMN_SUMMARY)));
            show.setDays(res.getString(res.getColumnIndex(SHOWS_COLUMN_DAYS)));
            show.setRuntime(res.getString(res.getColumnIndex(SHOWS_COLUMN_RUNTIME)));
            show.setGenres(res.getString(res.getColumnIndex(SHOWS_COLUMN_GENRES)));
            show.setWeight(res.getString(res.getColumnIndex(SHOWS_COLUMN_WEIGHT)));
            show.setRating(res.getString(res.getColumnIndex(SHOWS_COLUMN_RATING)));
            show.setStatus(res.getString(res.getColumnIndex(SHOWS_COLUMN_STATUS)));
            show.setIsFavourite(res.getString(res.getColumnIndex(SHOWS_COLUMN_ISFAVOURITE)));

            return show;
        } finally {
            res.close();
            db.close();
        }
    }

    public ArrayList<TvShows> getShowsByName(String name) {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<TvShows> shows = new ArrayList<>();
        Cursor res = db.rawQuery("select * from shows where name like '%" + name + "%'", null);
        try {
            res.moveToFirst();
            while (res.isAfterLast() == false) {
                TvShows show = new TvShows();
                show.setId(res.getInt(res.getColumnIndex(SHOWS_COLUMN_ID)));
                show.setName(res.getString(res.getColumnIndex(SHOWS_COLUMN_NAME)));
                show.setImage(res.getString(res.getColumnIndex(SHOWS_COLUMN_IMAGE)));
                show.setBigImage(res.getString(res.getColumnIndex(SHOWS_COLUMN_BIGIMAGE)));
                show.setSummary(res.getString(res.getColumnIndex(SHOWS_COLUMN_SUMMARY)));
                show.setDays(res.getString(res.getColumnIndex(SHOWS_COLUMN_DAYS)));
                show.setRuntime(res.getString(res.getColumnIndex(SHOWS_COLUMN_RUNTIME)));
                show.setGenres(res.getString(res.getColumnIndex(SHOWS_COLUMN_GENRES)));
                show.setWeight(res.getString(res.getColumnIndex(SHOWS_COLUMN_WEIGHT)));
                show.setRating(res.getString(res.getColumnIndex(SHOWS_COLUMN_RATING)));
                show.setStatus(res.getString(res.getColumnIndex(SHOWS_COLUMN_STATUS)));
                show.setIsFavourite(res.getString(res.getColumnIndex(SHOWS_COLUMN_ISFAVOURITE)));

                shows.add(show);
                res.moveToNext();
            }
            return shows;
        } finally {
            res.close();
            db.close();
        }
    }


    public int numberOfRows() {
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, SHOWS_TABLE_NAME);
        return numRows;
    }

    public boolean updateShow(TvShows show) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", show.getId());
        contentValues.put("name", show.getName());
        contentValues.put("summary", show.getSummary());
        contentValues.put("image", show.getImage());
        contentValues.put("bigimage", show.getBigImage());
        contentValues.put("days", show.getDays());
        contentValues.put("runtime", show.getRuntime());
        contentValues.put("weight", show.getWeight());
        contentValues.put("status", show.getStatus());
        contentValues.put("genres", show.getGenres());
        contentValues.put("rating", show.getRating());
        contentValues.put("favourite", show.getIsFavourite());
        db.update("shows", contentValues, "id = ? ", new String[]{Integer.toString(show.getId())});
        return true;
    }

    public Integer deleteShow(Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("shows",
                "id = ? ",
                new String[]{Integer.toString(id)});
    }

    public ArrayList<TvShows> getAllShows() {
        ArrayList<TvShows> array_list = new ArrayList<>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + SHOWS_TABLE_NAME, null);
        res.moveToFirst();
        try {
            while (res.isAfterLast() == false) {
                TvShows show = new TvShows();

                show.setId(res.getInt(res.getColumnIndex(SHOWS_COLUMN_ID)));
                show.setName(res.getString(res.getColumnIndex(SHOWS_COLUMN_NAME)));
                show.setImage(res.getString(res.getColumnIndex(SHOWS_COLUMN_IMAGE)));
                show.setBigImage(res.getString(res.getColumnIndex(SHOWS_COLUMN_BIGIMAGE)));
                show.setSummary(res.getString(res.getColumnIndex(SHOWS_COLUMN_SUMMARY)));
                show.setDays(res.getString(res.getColumnIndex(SHOWS_COLUMN_DAYS)));
                show.setRuntime(res.getString(res.getColumnIndex(SHOWS_COLUMN_RUNTIME)));
                show.setGenres(res.getString(res.getColumnIndex(SHOWS_COLUMN_GENRES)));
                show.setWeight(res.getString(res.getColumnIndex(SHOWS_COLUMN_WEIGHT)));
                show.setStatus(res.getString(res.getColumnIndex(SHOWS_COLUMN_STATUS)));
                show.setRating(res.getString(res.getColumnIndex(SHOWS_COLUMN_RATING)));
                show.setIsFavourite(res.getString(res.getColumnIndex(SHOWS_COLUMN_ISFAVOURITE)));

                array_list.add(show);
                res.moveToNext();
            }
            return array_list;
        } finally {
            res.close();
            db.close();
        }
    }

    public ArrayList<TvShows> getAllFavouriteShows() {
        ArrayList<TvShows> array_list = new ArrayList<>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + SHOWS_TABLE_NAME + " where favourite = 'true'", null);
        res.moveToFirst();
        try {
            while (res.isAfterLast() == false) {
                TvShows show = new TvShows();

                show.setId(res.getInt(res.getColumnIndex(SHOWS_COLUMN_ID)));
                show.setName(res.getString(res.getColumnIndex(SHOWS_COLUMN_NAME)));
                show.setImage(res.getString(res.getColumnIndex(SHOWS_COLUMN_IMAGE)));
                show.setBigImage(res.getString(res.getColumnIndex(SHOWS_COLUMN_BIGIMAGE)));
                show.setSummary(res.getString(res.getColumnIndex(SHOWS_COLUMN_SUMMARY)));
                show.setDays(res.getString(res.getColumnIndex(SHOWS_COLUMN_DAYS)));
                show.setRuntime(res.getString(res.getColumnIndex(SHOWS_COLUMN_RUNTIME)));
                show.setGenres(res.getString(res.getColumnIndex(SHOWS_COLUMN_GENRES)));
                show.setWeight(res.getString(res.getColumnIndex(SHOWS_COLUMN_WEIGHT)));
                show.setStatus(res.getString(res.getColumnIndex(SHOWS_COLUMN_STATUS)));
                show.setRating(res.getString(res.getColumnIndex(SHOWS_COLUMN_RATING)));
                show.setIsFavourite(res.getString(res.getColumnIndex(SHOWS_COLUMN_ISFAVOURITE)));

                array_list.add(show);
                res.moveToNext();
            }
            return array_list;
        } finally {
            res.close();
            db.close();
        }
    }
}
