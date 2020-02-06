package mum.vsebastianvc.personalapp.api.interactors;

import mum.vsebastianvc.personalapp.models.ImgurData;
import mum.vsebastianvc.personalapp.api.services.IImgurImages;
import mum.vsebastianvc.personalapp.api.services.ImgurImages;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Sebastian on 02/04/2020.
 */
public class DashboardImagesInteractor {

    public void loadImagesWithQueryParameter(final IDataAPICall callback, final String query) {
        IImgurImages api = ImgurImages.getInstance().getDataAPI();
        Call<ImgurData> call = api.getDataWithQuery(query);
        call.enqueue(new Callback<ImgurData>() {
            @Override
            public void onResponse(Call<ImgurData> call, Response<ImgurData> response) {
                if(response.isSuccessful()) {
                    callback.onResponse(response.body());
                } else {
                    callback.onFailure();
                }
            }

            @Override
            public void onFailure(Call<ImgurData> call, Throwable t) {
                callback.onFailure();
            }
        });
    }
}
