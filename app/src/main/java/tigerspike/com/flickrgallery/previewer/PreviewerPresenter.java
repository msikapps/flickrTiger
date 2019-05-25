package tigerspike.com.flickrgallery.previewer;

import android.content.Context;

import tigerspike.com.flickrgallery.services.NetworkService;

public class PreviewerPresenter implements PreviewerContract.Presenter {
    private NetworkService service;
    private Context context;
    private PreviewerActivity view;

    public PreviewerPresenter(Context context, PreviewerActivity view, NetworkService service) {
        this.view = view;
        this.context = context;
        this.service = service;
        view.loadImage();
    }
}
