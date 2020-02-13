package mum.vsebastianvc.personalapp.di.module;

import android.content.Context;
import dagger.Module;
import dagger.Provides;
import mum.vsebastianvc.personalapp.db.DBManager;
import mum.vsebastianvc.personalapp.db.DatabaseHelper;

/**
 * Created by Sebastian on 02/13/2020.
 */
@Module
public class DataBaseModule {

    private final String DBName = "IMAGES_COMMENTS.DB";
    private final int DBVersion = 1;

    @Provides
    DBManager provideDBManager(DatabaseHelper databaseHelper) {
        return new DBManager(databaseHelper);
    }

    @Provides
    DatabaseHelper provideDataBaseHelper(Context context) {
       return new DatabaseHelper(context, DBName, DBVersion);
    }
}
