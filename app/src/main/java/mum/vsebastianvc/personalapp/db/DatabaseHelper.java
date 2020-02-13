package mum.vsebastianvc.personalapp.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Sebastian on 02/04/2020.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    // Table Name
    static final String TABLE_NAME = "COMMENTS";

    // Table columns
    private static final String _ID = "_id";
    static final String TITLE = "title";
    static final String LINK = "link";
    static final String COMMENT = "comment";
    //public static final String DATE = "date";



    // Creating table query
    private static final String CREATE_TABLE = "create table " + TABLE_NAME + "(" + _ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + TITLE
            + " TEXT NOT NULL, " + LINK + " TEXT NOT NULL, " + COMMENT + " TEXT NOT NULL);";

    public DatabaseHelper(Context context, String dbName, int dbVersion) {
        super(context, dbName, null, dbVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
