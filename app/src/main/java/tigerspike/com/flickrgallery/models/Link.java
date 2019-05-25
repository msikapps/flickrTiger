package tigerspike.com.flickrgallery.models;


import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

import java.io.Serializable;

@Root(strict = false)
public class Link implements Serializable {
    public Link() {
    }

    @Attribute(name = "href", required = false)
    private String href;

    @Attribute(name = "rel", required = false)
    private String rel;

    @Attribute(name = "type", required = false)
    private String contentType;

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getRel() {
        return rel;
    }

    public void setRel(String rel) {
        this.rel = rel;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
}