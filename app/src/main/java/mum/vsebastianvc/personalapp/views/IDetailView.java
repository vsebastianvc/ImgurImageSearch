package mum.vsebastianvc.personalapp.views;

import java.util.List;

/**
 * Created by Sebastian on 02/04/2020.
 */
public interface IDetailView {

    void populateListOfComments(List<String> comments);
    void setComments();
}
