package mum.vsebastianvc.personalapp.presenters;

import mum.vsebastianvc.personalapp.views.IDashboardView;

/**
 * Created by Sebastian on 02/04/2020.
 */
public interface IDashboardPresenter {

    void loadImgurDataWithQuery(final String query);
    void setView(IDashboardView view);

}
