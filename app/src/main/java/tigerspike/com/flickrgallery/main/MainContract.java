package tigerspike.com.flickrgallery.main;

import java.util.List;

import tigerspike.com.flickrgallery.models.Entry;

public interface MainContract {

    interface View {
        void populateGallery(List<Entry> pictureList);
        void leaveScreen();
    }

    interface Presenter {
        void fetchPictures();
    }
}
