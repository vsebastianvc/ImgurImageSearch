package mum.vsebastianvc.personalapp.di.component;

import android.app.Application;
import android.content.Context;

import mum.vsebastianvc.personalapp.di.module.ApplicationModule;

/**
 * Created by Sebastian on 02/13/2020.
 */
public final class App extends Application {

    public IApplicationComponent component;

    public static Context context;

    public void onCreate() {
        super.onCreate();

        this.component = DaggerIApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();

        if (context == null) {
            context = getApplicationContext();
        }

    }

    public static IApplicationComponent getApplicationContextReference() {
        return ((App) context.getApplicationContext()).component;
    }
}
