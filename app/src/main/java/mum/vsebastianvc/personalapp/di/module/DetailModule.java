package mum.vsebastianvc.personalapp.di.module;

import dagger.Module;
import dagger.Provides;
import mum.vsebastianvc.personalapp.presenters.DetailPresenter;

/**
 * Created by Sebastian on 02/13/2020.
 */
@Module
public class DetailModule {

    @Provides
    DetailPresenter provideActivityDetailPresenter() {
        return new DetailPresenter();
    }
}
