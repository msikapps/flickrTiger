package tigerspike.com.flickrgallery.adapters;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.Shadows;
import org.robolectric.shadows.ShadowActivity;

import java.util.ArrayList;
import java.util.List;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.test.core.app.ApplicationProvider;
import tigerspike.com.flickrgallery.R;
import tigerspike.com.flickrgallery.main.MainActivity;
import tigerspike.com.flickrgallery.models.Author;
import tigerspike.com.flickrgallery.models.Entry;
import tigerspike.com.flickrgallery.models.Link;
import tigerspike.com.flickrgallery.previewer.PreviewerActivity;

import static org.junit.Assert.assertEquals;

@RunWith(RobolectricTestRunner.class)
public class PictureAdapterTest {

    private PictureAdapter adapter;
    private Context context;
    private List<Entry> list;
    private List<Link> links;
    private Link link;
    private Author author;
    private Entry entry;
    private MainActivity view;
    private ShadowActivity shadow;

    @Before
    public void setUp() {
        context = ApplicationProvider.getApplicationContext();
        view = new MainActivity();
        shadow = Shadows.shadowOf(view);
        author = new Author();
        list = new ArrayList<>();
        links = new ArrayList<>();
        entry = new Entry();
        link = new Link();
        author.setName("Bakr");
        link.setHref("link");
        link.setRel("enclosure");
        links.add(link);
        entry.setAuthor(author);
        entry.setLinks(links);
        entry.setTitle("title");
        entry.setPublishedOn("2019-05-24T14:33:56Z");
        list.add(entry);
    }

    @Test
    public void itemCount() {
        adapter = new PictureAdapter(context, view, list);
        assertEquals(1, adapter.getItemCount());
    }

    @Test
    public void getItemAtPosition() {
        adapter = new PictureAdapter(context, view, list);
        assertEquals(adapter.getArrayList().get(0).getTitle(), entry.getTitle());
    }

    @Test
    public void onBindViewHolderShouldAddPictureElement() {
        adapter = new PictureAdapter(context, view, list);
        LayoutInflater inflater = (LayoutInflater) ApplicationProvider.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View listItemView = inflater.inflate(R.layout.picture_item, null, false);
        PictureViewHolder holder = new PictureViewHolder(listItemView);
        adapter.onBindViewHolder(holder, 0);
        assertEquals(holder.pictureTitle.getText().toString(), entry.getTitle());
        assertEquals(holder.publisherText.getText().toString(), entry.getAuthor().getName());
        holder.itemView.performClick();
        Intent intent = shadow.peekNextStartedActivity();
        assertEquals(intent.getComponent(),new ComponentName(context,PreviewerActivity.class));
    }


    @Test
    public void onCreateViewHolderShouldInflate() {
        adapter = new PictureAdapter(context, view, list);
        LayoutInflater inflater = (LayoutInflater) ApplicationProvider.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ViewGroup group = (ViewGroup) inflater.inflate(R.layout.picture_item, null, false);
        RecyclerView.ViewHolder holder = adapter.onCreateViewHolder(group, 1);
        assertEquals(holder.itemView.getClass(), CardView.class);
    }

}