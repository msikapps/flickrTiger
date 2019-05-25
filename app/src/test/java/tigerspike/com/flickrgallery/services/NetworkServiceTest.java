package tigerspike.com.flickrgallery.services;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import retrofit2.Call;
import retrofit2.Response;
import tigerspike.com.flickrgallery.models.Feed;
import tigerspike.com.flickrgallery.network.GetDataService;
import tigerspike.com.flickrgallery.network.RetrofitClientInstance;
import tigerspike.com.flickrgallery.utils.Constant;

import static org.junit.Assert.assertEquals;
@RunWith(RobolectricTestRunner.class)
public class NetworkServiceTest {

    private MockWebServer mockWebServer;
    private NetworkService.NetworkServiceCallBack networkServiceCallBack;
    private String testXml = "<?xml version=\"1.0\" encoding=\"utf-8\" standalone=\"yes\"?>\n" +
            "<feed xmlns=\"http://www.w3.org/2005/Atom\"\n" +
            "      xmlns:dc=\"http://purl.org/dc/elements/1.1/\"\n" +
            "      xmlns:flickr=\"urn:flickr:user\"\n" +
            "      xmlns:media=\"http://search.yahoo.com/mrss/\">\n" +
            "\n" +
            "  <title>Uploads from everyone</title>\n" +
            "  <link rel=\"self\" href=\"http://www.flickr.com/services/feeds/photos_public.gne\" />\n" +
            "  <link rel=\"alternate\" type=\"text/html\" href=\"https://www.flickr.com/photos/\"/>\n" +
            "  <id>tag:flickr.com,2005:/photos/public</id>\n" +
            "  <icon>https://combo.staticflickr.com/pw/images/buddyicon.gif</icon>\n" +
            "  <subtitle></subtitle>\n" +
            "  <updated>2019-05-24T14:33:16Z</updated>\n" +
            "  <generator uri=\"https://www.flickr.com/\">Flickr</generator>\n" +
            "\n" +
            "<entry>\n" +
            "    <title>Ministro de Vivienda Miguel Estrada participa en reunión de trabajo en Epsel Chiclayo</title>\n" +
            "    <link rel=\"alternate\" type=\"text/html\" href=\"https://www.flickr.com/photos/viviendaperu/47924404582/\"/>\n" +
            "    <id>tag:flickr.com,2005:/photo/47924404582</id>\n" +
            "    <published>2019-05-24T14:33:16Z</published>\n" +
            "    <updated>2019-05-24T14:33:16Z</updated>\n" +
            "    <flickr:date_taken>2019-05-24T09:03:59-08:00</flickr:date_taken>\n" +
            "    <dc:date.Taken>2019-05-24T09:03:59-08:00</dc:date.Taken>\n" +
            "    <content type=\"html\">\t\t\t&lt;p&gt;&lt;a href=&quot;https://www.flickr.com/people/viviendaperu/&quot;&gt;Ministerio de Vivienda Perú&lt;/a&gt; posted a photo:&lt;/p&gt;\n" +
            "\t\n" +
            "&lt;p&gt;&lt;a href=&quot;https://www.flickr.com/photos/viviendaperu/47924404582/&quot; title=&quot;Ministro de Vivienda Miguel Estrada participa en reunión de trabajo en Epsel Chiclayo&quot;&gt;&lt;img src=&quot;https://live.staticflickr.com/65535/47924404582_1285745858_m.jpg&quot; width=&quot;240&quot; height=&quot;161&quot; alt=&quot;Ministro de Vivienda Miguel Estrada participa en reunión de trabajo en Epsel Chiclayo&quot; /&gt;&lt;/a&gt;&lt;/p&gt;\n" +
            "\n" +
            "</content>\n" +
            "    <author>\n" +
            "      <name>Ministerio de Vivienda Perú</name>\n" +
            "      <uri>https://www.flickr.com/people/viviendaperu/</uri>\n" +
            "      <flickr:nsid>120957107@N06</flickr:nsid>\n" +
            "      <flickr:buddyicon>https://farm9.staticflickr.com/8057/buddyicons/120957107@N06.jpg?1472763787#120957107@N06</flickr:buddyicon>\n" +
            "    </author>\n" +
            "    <link rel=\"enclosure\" type=\"image/jpeg\" href=\"https://live.staticflickr.com/65535/47924404582_1285745858_b.jpg\" />\n" +
            "    <category term=\"\" scheme=\"https://www.flickr.com/photos/tags/\" />\n" +
            "    <displaycategories>\n" +
            "            </displaycategories>\n" +
            "    </entry></feed>";

    @Before
    public void setUp() throws IOException {
        MockitoAnnotations.initMocks(this);
        mockWebServer = new MockWebServer();
        HttpUrl url = mockWebServer.url("/");
        Constant.BASE_URL = url.toString();
    }

    @Test
    public void getImagesShouldReturnData() throws IOException {
        mockWebServer.enqueue(new MockResponse().setBody(testXml).setResponseCode(200));
        GetDataService appService = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<Feed> call = appService.getFlickrImages();
        Response<Feed> response = call.execute();
        assertEquals(200, response.code());
        assertEquals(1, response.body().getPictureList().size());
        assertEquals("https://www.flickr.com/photos/viviendaperu/47924404582/", response.body().getPictureList().get(0).getLinks().get(0).getHref());
    }

    @After
    public void tearDown() throws Exception {
        mockWebServer.close();
    }
}