package mum.vsebastianvc.personalapp.presenters;

import java.util.List;

import javax.inject.Inject;

import mum.vsebastianvc.personalapp.db.DBManager;
import mum.vsebastianvc.personalapp.di.component.App;
import mum.vsebastianvc.personalapp.views.IDetailView;

/**
 * Created by Sebastian on 02/04/2020.
 */
public class DetailPresenter implements IDetailPresenter {

    private IDetailView view;
    @Inject
    DBManager dbManager;

    public DetailPresenter() {
        App.getApplicationContextReference().inject(this);
    }


    @Override
    public void searchComments(String title, String link) {
        dbManager.open();
        List<String> listOfComments = dbManager.select(title, link);
        dbManager.close();
        view.populateListOfComments(listOfComments);
    }

    @Override
    public void insertCommentOnImage(final String title, final String link, final String comment) {
        dbManager.open();
        dbManager.insert(title, link, comment);
        dbManager.close();
    }

    @Override
    public void setView(IDetailView view) {
        this.view = view;
    }
}
