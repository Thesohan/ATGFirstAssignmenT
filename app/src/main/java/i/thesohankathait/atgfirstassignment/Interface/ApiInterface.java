package i.thesohankathait.atgfirstassignment.Interface;

import java.util.List;

import i.thesohankathait.atgfirstassignment.Model.MyModel;
import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface{
//    https://api.flickr.com/services/rest/
    @GET("?method=flickr.photos.getRecent&\n" +
            "api_key=6f102c62f41998d151e5a1b48713cf13&format=json&nojsoncallback=1&extras=url_s\n")
    Call<MyModel> getMyModel();

    @GET("?method=flickr.photos.search&\n" +
            "api_key=6f102c62f41998d151e5a1b48713cf13&format=json&nojsoncallback=1&extras=url_s")
    Call<MyModel> getSearchResult(@Query("tags") String animal);

}

