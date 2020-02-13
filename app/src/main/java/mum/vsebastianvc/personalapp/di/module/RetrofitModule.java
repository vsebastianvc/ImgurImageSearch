package mum.vsebastianvc.personalapp.di.module;

import dagger.Module;
import dagger.Provides;
import mum.vsebastianvc.personalapp.api.services.IImgurImages;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Sebastian on 02/13/2020.
 */
@Module
public class RetrofitModule {

    @Provides
    Retrofit provideRetrofit() {
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.imgur.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }

    @Provides
    IImgurImages provideImgurImages() {
        return provideRetrofit().create(IImgurImages.class);
    }
}
