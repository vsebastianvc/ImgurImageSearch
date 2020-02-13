package mum.vsebastianvc.personalapp.di.module;

import android.content.Context;
import android.support.annotation.NonNull;

import dagger.Module;
import dagger.Provides;
import mum.vsebastianvc.personalapp.di.component.App;

/**
 * Created by Sebastian on 02/13/2020.
 */
@Module
public final class ApplicationModule {
    private final App application;

    public  ApplicationModule(@NonNull App application) {
        this.application = application;
    }

    @Provides
    public final Context provideContext() {
        return this.application;
    }
}
