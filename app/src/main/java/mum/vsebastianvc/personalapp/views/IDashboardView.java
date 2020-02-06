package mum.vsebastianvc.personalapp.views;

import android.util.Pair;

import java.util.List;

/**
 * Created by Sebastian on 02/04/2020.
 */
public interface IDashboardView {

    void onShowListOfImagesWithQuery(List<Pair<String,String>> data);

}
