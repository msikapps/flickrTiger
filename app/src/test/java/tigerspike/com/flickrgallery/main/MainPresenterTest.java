package tigerspike.com.flickrgallery.main;

import android.content.Context;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;

import java.util.ArrayList;
import java.util.List;

import androidx.test.core.app.ApplicationProvider;
import retrofit2.Response;
import tigerspike.com.flickrgallery.models.Entry;
import tigerspike.com.flickrgallery.models.Feed;
import tigerspike.com.flickrgallery.models.Link;
import tigerspike.com.flickrgallery.services.NetworkService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(RobolectricTestRunner.class)
public class MainPresenterTest {

    private MainPresenter presenter;
    @Mock
    private NetworkService service;
    @Mock
    private MainActivity view;
    Context context;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        context = ApplicationProvider.getApplicationContext();
        presenter = new MainPresenter(context, view, service);
    }

    @Test
    public void successfulFetchPicturesShouldPopulateGallery() {
        Feed response = new Feed();
        Entry entry = new Entry();
        List<Entry> entryList = new ArrayList<>();
        entryList.add(entry);
        response.setPictureList(entryList);
        doAnswer(invocation -> {
            NetworkService.NetworkServiceCallBack callback = (NetworkService.NetworkServiceCallBack) invocation.getArguments()[0];
            callback.onSuccess(Response.success(response));
            return null;
        }).when(service).getFlickrImages(any(NetworkService.NetworkServiceCallBack.class));
        presenter.fetchPictures();
        verify(view, times(1)).populateGallery(entryList);
    }

    @Test
    public void erroneousFetchPicturesShouldLeave() {
        doAnswer(invocation -> {
            NetworkService.NetworkServiceCallBack callback = (NetworkService.NetworkServiceCallBack) invocation.getArguments()[0];
            callback.onSuccess(Response.success(null));
            return null;
        }).when(service).getFlickrImages(any(NetworkService.NetworkServiceCallBack.class));
        presenter.fetchPictures();
        verify(view, times(1)).leaveScreen();
    }

    @Test
    public void erroneousRequestFetchPicturesShouldLeave() {
        doAnswer(invocation -> {
            NetworkService.NetworkServiceCallBack callback = (NetworkService.NetworkServiceCallBack) invocation.getArguments()[0];
            callback.onError(null);
            return null;
        }).when(service).getFlickrImages(any(NetworkService.NetworkServiceCallBack.class));
        presenter.fetchPictures();
        verify(view, times(1)).leaveScreen();
    }

}