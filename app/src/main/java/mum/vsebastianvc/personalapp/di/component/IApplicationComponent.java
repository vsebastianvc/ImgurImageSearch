package mum.vsebastianvc.personalapp.di.component;

import javax.inject.Singleton;

import dagger.Component;
import mum.vsebastianvc.personalapp.api.interactors.DashboardImagesInteractor;
import mum.vsebastianvc.personalapp.di.module.ApplicationModule;
import mum.vsebastianvc.personalapp.di.module.DashboardModule;
import mum.vsebastianvc.personalapp.di.module.DataBaseModule;
import mum.vsebastianvc.personalapp.di.module.DetailModule;
import mum.vsebastianvc.personalapp.di.module.RetrofitModule;
import mum.vsebastianvc.personalapp.presenters.DashboardPresenter;
import mum.vsebastianvc.personalapp.presenters.DetailPresenter;
import mum.vsebastianvc.personalapp.views.activities.DashboardActivity;
import mum.vsebastianvc.personalapp.views.activities.DetailActivity;

/**
 * Created by Sebastian on 02/13/2020.
 */
@Singleton
@Component(modules = {ApplicationModule.class, DataBaseModule.class, RetrofitModule.class, DashboardModule.class, DetailModule.class})
public interface IApplicationComponent {

    void inject(final DashboardActivity dashboardActivity);

    void inject(final DetailActivity detailActivity);

    void inject(final DashboardPresenter dashboardPresenter);

    void inject(final DetailPresenter detailPresenter);

    void inject(final DashboardImagesInteractor dashboardImagesInteractor);
}

