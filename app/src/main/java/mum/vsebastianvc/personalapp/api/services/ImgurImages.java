package mum.vsebastianvc.personalapp.api.services;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Sebastian on 02/04/2020.
 */
public class ImgurImages {

    private static ImgurImages instance;

    private final Retrofit retrofit;


    public static ImgurImages getInstance() {
        if(instance == null) {
            instance = new ImgurImages();
        }

        return instance;
    }

    private ImgurImages() {
        retrofit = new Retrofit.Builder()
                .baseUrl("https://api.imgur.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public IImgurImages getDataAPI() {
        return retrofit.create(IImgurImages.class);
    }
}
