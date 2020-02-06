package mum.vsebastianvc.personalapp.api.services;

import mum.vsebastianvc.personalapp.models.ImgurData;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * Created by Sebastian on 02/04/2020.
 */
public interface IImgurImages {

    @Headers("Authorization: Client-ID 137cda6b5008a7c")
    @GET("3/gallery/search/1")
    Call<ImgurData> getDataWithQuery(@Query("q") String q);
}
