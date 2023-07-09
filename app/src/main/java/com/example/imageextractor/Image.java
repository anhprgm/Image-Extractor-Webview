package com.example.imageextractor;
import java.util.List;

public class Image {
    private String url;
    private List<String> attributes;

    public Image(String url, List<String> attributes) {
        this.url = url;
        this.attributes = attributes;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<String> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<String> attributes) {
        this.attributes = attributes;
    }
}
