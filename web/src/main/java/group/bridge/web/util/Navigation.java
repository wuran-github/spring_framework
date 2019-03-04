package group.bridge.web.util;

import com.fasterxml.jackson.dataformat.xml.annotation.*;

import java.util.List;

@JacksonXmlRootElement(localName = "Nav")
public class Navigation {
    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "Url")
    List<Url> urls;

    public List<Url> getUrls() {
        return urls;
    }

    public void setUrls(List<Url> urls) {
        this.urls = urls;
    }
}
