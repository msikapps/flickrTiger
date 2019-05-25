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

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.spy;

@RunWith(RobolectricTestRunner.class)
public class MainActivityTest {

    @Mock
    MainPresenter presenter;

    MainActivity view;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        view = Robolectric.setupActivity(MainActivity.class);
        view.setPresenter(presenter);
    }

    @Test
    public void populateGalleryShouldFillList() {
        view = spy(view);
        Entry entry = new Entry();
        List<Entry> entryList = new ArrayList<>();
        Link link = new Link();
        List<Link> linkList = new ArrayList<>();
        Author author = new Author();
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
        view.populateGallery(entryList);
        assertTrue(view.imageGallery.getAdapter().getItemCount()> 0);
    }

    @Test
    public void leaveScreenShouldCloseApplication() {
        view.leaveScreen();
        assertTrue(view.isFinishing());
    }
}