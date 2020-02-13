package mum.vsebastianvc.personalapp.di.module;

import dagger.Module;
import dagger.Provides;
import mum.vsebastianvc.personalapp.api.interactors.DashboardImagesInteractor;
import mum.vsebastianvc.personalapp.presenters.DashboardPresenter;

/**
 * Created by Sebastian on 02/13/2020.
 */
@Module
public class DashboardModule {

    @Provides
    DashboardPresenter provideActivityDashboardPresenter() {
        return new DashboardPresenter(provideActivityDashboardInteractor());
    }

    @Provides
    DashboardImagesInteractor provideActivityDashboardInteractor() {
        return new DashboardImagesInteractor();
    }
}
