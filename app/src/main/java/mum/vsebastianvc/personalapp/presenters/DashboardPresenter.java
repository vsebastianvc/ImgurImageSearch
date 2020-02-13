package mum.vsebastianvc.personalapp.presenters;

import android.util.Log;
import android.util.Pair;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import mum.vsebastianvc.personalapp.api.interactors.DashboardImagesInteractor;
import mum.vsebastianvc.personalapp.api.interactors.IDataAPICall;
import mum.vsebastianvc.personalapp.models.Data;
import mum.vsebastianvc.personalapp.models.ImgurData;
import mum.vsebastianvc.personalapp.views.IDashboardView;

/**
 * Created by Sebastian on 02/04/2020.
 */
public class DashboardPresenter implements IDashboardPresenter {

    private IDashboardView view;


    private DashboardImagesInteractor interactorImages;

    @Inject
    public DashboardPresenter(DashboardImagesInteractor dashboardImagesInteractor) {
        this.interactorImages = dashboardImagesInteractor;
    }


    public void loadImgurDataWithQuery(final String query) {

        interactorImages.loadImagesWithQueryParameter(new IDataAPICall() {
            @Override
            public void onResponse(ImgurData images) {
                List<Pair<String, String>> list = getPairs(images.getData());
                view.onShowListOfImagesWithQuery(list);
            }

            @Override
            public void onFailure() {
            }
        }, query);
    }

    @Override
    public void setView(IDashboardView view) {
        this.view = view;
    }

    private List<Pair<String, String>> getPairs(List<Data> mData) {
        List<Pair<String, String>> listOfPairs = new ArrayList<>();
        for (int i = 0; i < mData.size(); i++) {
            int imageList = mData.get(i).getImages() == null ? 0 : mData.get(i).getImages().size();
            final String title = mData.get(i).getTitle().isEmpty() ?
                    "default" : mData.get(i).getTitle().replaceAll("[^a-zA-Z0-9_-]", " ");
            for (int j = 0; j < imageList; j++) {
                if (mData.get(i).getImages().get(j).getLink() != null) {
                    listOfPairs.add(new Pair<>(title, mData.get(i).getImages().get(j).getLink()));
                }
            }
        }
        Log.d("Links and titles", String.valueOf(listOfPairs.size()));
        return listOfPairs;
    }
}
