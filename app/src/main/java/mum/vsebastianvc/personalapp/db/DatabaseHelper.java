package mum.vsebastianvc.personalapp.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Sebastian on 02/04/2020.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    // Table Name
    public static final String TABLE_NAME = "COMMENTS";

    // Table columns
    public static final String _ID = "_id";
    public static final String TITLE = "title";
    public static final String LINK = "link";
    public static final String COMMENT = "comment";
    //public static final String DATE = "date";

    // Database Information
    static final String DB_NAME = "IMAGES_COMMENTS.DB";

    // database version
    static final int DB_VERSION = 1;

    // Creating table query
    private static final String CREATE_TABLE = "create table " + TABLE_NAME + "(" + _ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + TITLE
            + " TEXT NOT NULL, " + LINK + " TEXT NOT NULL, " + COMMENT + " TEXT NOT NULL);";
/**
    private static final String CREATE_TABLE = "create table " + TABLE_NAME + "(" + TITLE
            + " TEXT NOT NULL, " + LINK + " TEXT NOT NULL, " + COMMENT + " TEXT NOT NULL,"+ DATE
            + " TEXT NOT NULL, " + "PRIMARY KEY ("+TITLE+","+ LINK+"));";
 **/

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
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
