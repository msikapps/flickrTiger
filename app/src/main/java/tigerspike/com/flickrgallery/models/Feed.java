package tigerspike.com.flickrgallery.models;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.NamespaceList;
import org.simpleframework.xml.Root;

import java.io.Serializable;
import java.util.List;

@Root(name = "feed", strict = false)
public class Feed implements Serializable {

    @ElementList(entry = "entry", inline = true)
    private List<Entry> pictureList;

    public List<Entry> getPictureList() {
        return pictureList;
    }

    public void setPictureList(List<Entry> pictureList) {
        this.pictureList = pictureList;
    }

    public Feed() {

    }

}
