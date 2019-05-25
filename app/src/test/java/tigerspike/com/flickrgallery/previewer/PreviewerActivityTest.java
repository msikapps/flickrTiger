package tigerspike.com.flickrgallery.previewer;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.Shadows;
import org.robolectric.shadows.ShadowDrawable;

import androidx.annotation.Nullable;
import androidx.test.core.app.ApplicationProvider;
import tigerspike.com.flickrgallery.utils.Constant;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(RobolectricTestRunner.class)
public class PreviewerActivityTest {

    @Mock
    private PreviewerPresenter presenter;
    private Context context;
    private PreviewerActivity view;
    private Drawable expectedDrawable;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        context = ApplicationProvider.getApplicationContext();
        Intent intent = new Intent(context, PreviewerActivity.class);
        intent.putExtra(Constant.PICTURE_URL, "https://live.staticflickr.com/65535/47924412076_734cb8b634_b.jpg");
        view = Robolectric.buildActivity(PreviewerActivity.class, intent).create().get();
        view.setPresenter(presenter);
    }

    @Test
    public void loadImageShouldDownloadIt() {
        view.loadImage();
        Glide.with(context).asDrawable().load("https://live.staticflickr.com/65535/47924412076_734cb8b634_b.jpg").into(new SimpleTarget<Drawable>() {
            @Override
            public void onLoadStarted(@Nullable Drawable placeholder) {
                super.onLoadStarted(placeholder);
            }

            @Override
            public void onLoadFailed(@Nullable Drawable errorDrawable) {
                super.onLoadFailed(errorDrawable);
            }

            @Override
            public void onResourceReady(Drawable drawable, Transition glideAnimation) {
                expectedDrawable = drawable;
                ShadowDrawable shadowDrawable = Shadows.shadowOf(view.imageView.getDrawable());
                assertEquals(expectedDrawable, shadowDrawable.getCreatedFromResId());
            }
        });
    }

    @Test
    public void onClickCloseShouldFinish() {
        view.closeButton.performClick();
        assertTrue(view.isFinishing());
    }
}