package tigerspike.com.flickrgallery.services;

import android.util.Log;

import androidx.annotation.NonNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tigerspike.com.flickrgallery.models.Feed;
import tigerspike.com.flickrgallery.network.ApiHelper;
import tigerspike.com.flickrgallery.network.GetDataService;
import tigerspike.com.flickrgallery.network.RetrofitClientInstance;

public class NetworkService implements ApiHelper {

    @Override
    public void getFlickrImages(NetworkServiceCallBack tNetworkServiceCallBack) {
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<Feed> call = service.getFlickrImages();
        call.enqueue(new Callback<Feed>() {
            public void onResponse(@NonNull Call<Feed> call, Response<Feed> response) {
                if (response.isSuccessful()) {
                    tNetworkServiceCallBack.onSuccess(response);
                } else {
                    tNetworkServiceCallBack.onError(response.toString());
                }
            }

            @Override
            public void onFailure(Call<Feed> call, Throwable t) {
                tNetworkServiceCallBack.onError(t.getMessage());
            }
        });
    }

    public interface NetworkServiceCallBack {

        void onError(String error);

        void onSuccess(Response response);

    }
}
