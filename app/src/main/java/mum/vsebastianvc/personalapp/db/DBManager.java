package mum.vsebastianvc.personalapp.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Sebastian on 02/04/2020.
 */
public class DBManager {

    private DatabaseHelper dbHelper;

    private SQLiteDatabase database;


    @Inject
    public DBManager(DatabaseHelper databaseHelper) {
        dbHelper = databaseHelper;
    }

    public DBManager open() throws SQLException {
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public void insert(String title, String link, String comment) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.TITLE, title);
        contentValue.put(DatabaseHelper.LINK, link);
        contentValue.put(DatabaseHelper.COMMENT, comment);
        //contentValue.put(DatabaseHelper.DATE, new Datet);
        Long i = database.insert(DatabaseHelper.TABLE_NAME, null, contentValue);
        Log.d("INSERT", "insert: " + i);
    }

    public List<String> select(String title, String link) {
        List<String> listOfComments = new ArrayList<>();
        //String query = "SELECT "+ DatabaseHelper.COMMENT + " FROM " + DatabaseHelper.TABLE_NAME + " WHERE " +DatabaseHelper.TITLE+ " = '" +title+ " ' and " +DatabaseHelper.LINK+ " =' " +link+ " ';";
        String query = "SELECT " + DatabaseHelper.COMMENT + " FROM " + DatabaseHelper.TABLE_NAME + " WHERE " + DatabaseHelper.TITLE + " = '" + title + "' AND " + DatabaseHelper.LINK + " = '" + link + "';";
        Cursor cursor = database.rawQuery(query, null);
        Log.d("SEARCH", "select: " + cursor.getCount());
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                String row = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COMMENT));
                listOfComments.add(row);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return listOfComments;
    }
}
