package mum.vsebastianvc.personalapp.presenters;

import mum.vsebastianvc.personalapp.views.IDetailView;

/**
 * Created by Sebastian on 02/04/2020.
 */
public interface IDetailPresenter {


    void searchComments(String title, String link);
    void insertCommentOnImage(final String title, final String link, final String comment);
    void setView(IDetailView view);
}
