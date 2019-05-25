package tigerspike.com.flickrgallery.network;

import retrofit2.Call;
import retrofit2.http.GET;
import tigerspike.com.flickrgallery.models.Feed;

public interface GetDataService {
    @GET("feeds/photos_public.gne")
    Call<Feed> getFlickrImages();
}