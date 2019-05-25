package tigerspike.com.flickrgallery.main;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import java.util.ArrayList;
import java.util.List;

import tigerspike.com.flickrgallery.models.Author;
import tigerspike.com.flickrgallery.models.Entry;
import tigerspike.com.flickrgallery.models.Link;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.spy;

@RunWith(RobolectricTestRunner.class)
public class MainActivityTest {

    @Mock
    private MainPresenter presenter;

    private MainActivity view;

    private List<Entry> entryList;
    private Link link;
    private List<Link> linkList;
    private Author author;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        view = Robolectric.setupActivity(MainActivity.class);
        view.setPresenter(presenter);
        Entry entry = new Entry();
        entryList = new ArrayList<>();
        link = new Link();
        linkList = new ArrayList<>();
        author = new Author();
        author.setName("Bakr");
        link.setHref("blank");
        link.setRel("alternate");
        linkList.add(link);
        entry.setTitle("Title");
        entry.setPublishedOn("2019-05-24T14:33:56Z");
        entry.setAuthor(author);
        entry.setLinks(linkList);
        entryList.add(entry);
        entryList.add(entry);
    }

    @Test
    public void populateGalleryShouldFillList() {
        view = spy(view);
        view.populateGallery(entryList);
        assertTrue(view.imageGallery.getAdapter().getItemCount() > 0);
    }

    @Test
    public void leaveScreenShouldCloseApplication() {
        view.leaveScreen();
        assertTrue(view.isFinishing());
    }
}