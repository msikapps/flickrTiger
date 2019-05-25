package tigerspike.com.flickrgallery.network;

import tigerspike.com.flickrgallery.services.NetworkService;

public interface ApiHelper {
    void getFlickrImages(NetworkService.NetworkServiceCallBack tNetworkServiceCallBack);
}
