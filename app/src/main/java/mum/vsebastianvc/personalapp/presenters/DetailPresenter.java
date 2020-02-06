package mum.vsebastianvc.personalapp.presenters;

import android.content.Context;

import java.util.List;

import mum.vsebastianvc.personalapp.db.DBManager;
import mum.vsebastianvc.personalapp.views.IDetailView;

/**
 * Created by Sebastian on 02/04/2020.
 */
public class DetailPresenter implements IDetailPresenter {

    private IDetailView view;
    private DBManager dbManager;

    public DetailPresenter(IDetailView view) {
        this.view = view;
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

    public void instanceDB(Context context) {
        dbManager = new DBManager(context);
    }
}
