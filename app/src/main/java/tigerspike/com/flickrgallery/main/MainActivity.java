package tigerspike.com.flickrgallery.main;

import android.os.Bundle;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import tigerspike.com.flickrgallery.R;
import tigerspike.com.flickrgallery.adapters.PictureAdapter;
import tigerspike.com.flickrgallery.models.Entry;
import tigerspike.com.flickrgallery.services.NetworkService;

public class MainActivity extends AppCompatActivity implements MainContract.View {


    private MainPresenter presenter;
    @BindView(R.id.gallery)
    RecyclerView imageGallery;

    public void setPresenter(MainPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        presenter = new MainPresenter(this, this, new NetworkService());
        presenter.fetchPictures();
    }

    @Override
    public void populateGallery(List<Entry> pictureList) {
        imageGallery.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        PictureAdapter adapter = new PictureAdapter(this, this, pictureList);
        imageGallery.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void leaveScreen() {
        finish();
    }
}
