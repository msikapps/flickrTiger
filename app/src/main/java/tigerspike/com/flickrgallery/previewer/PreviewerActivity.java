package tigerspike.com.flickrgallery.previewer;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import tigerspike.com.flickrgallery.R;
import tigerspike.com.flickrgallery.services.NetworkService;
import tigerspike.com.flickrgallery.utils.Constant;

public class PreviewerActivity extends AppCompatActivity implements PreviewerContract.View {

    private PreviewerPresenter presenter;
    @BindView(R.id.image)
    ImageView imageView;
    @BindView(R.id.close_btn)
    ImageView closeButton;

    public void setPresenter(PreviewerPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);
        ButterKnife.bind(this);
        presenter = new PreviewerPresenter(this, this, new NetworkService());
    }

    @OnClick(R.id.close_btn)
    public void onClickClose(View view) {
        finish();
    }

    @Override
    public void loadImage() {
        if (getIntent().hasExtra(Constant.PICTURE_URL)) {
            Glide.with(this).load(getIntent().getExtras().getString(Constant.PICTURE_URL)).into(imageView);
        }
    }
}
