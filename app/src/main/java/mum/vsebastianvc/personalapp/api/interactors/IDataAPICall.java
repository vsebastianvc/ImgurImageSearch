package mum.vsebastianvc.personalapp.api.interactors;

import mum.vsebastianvc.personalapp.models.ImgurData;

/**
 * Created by Sebastian on 02/04/2020.
 */
public interface IDataAPICall {
    void onResponse(ImgurData images);
    void onFailure();
}
