package tigerspike.com.flickrgallery.main;

import android.content.Context;
import android.util.Log;

import retrofit2.Response;
import tigerspike.com.flickrgallery.models.Feed;
import tigerspike.com.flickrgallery.services.NetworkService;

public class MainPresenter implements MainContract.Presenter {

    private NetworkService service;
    private Context context;
    private MainActivity view;

    public MainPresenter(Context context, MainActivity view, NetworkService service) {
        this.view = view;
        this.context = context;
        this.service = service;
    }

    @Override
    public void fetchPictures() {
        service.getFlickrImages(new NetworkService.NetworkServiceCallBack() {
            @Override
            public void onError(String error) {
                view.leaveScreen();
            }

            @Override
            public void onSuccess(Response response) {
                if (response != null && response.body() != null) {
                    Feed galleryResponse = (Feed) response.body();
                    view.populateGallery(galleryResponse.getPictureList());
                } else
                    view.leaveScreen();
            }
        });
    }
}
