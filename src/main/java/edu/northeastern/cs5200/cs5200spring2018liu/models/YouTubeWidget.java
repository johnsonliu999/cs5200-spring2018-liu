package edu.northeastern.cs5200.cs5200spring2018liu.models;

public class YouTubeWidget extends Widget {
    private String url;
    private boolean shareable;
    private boolean expandable;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isShareable() {
        return shareable;
    }

    public void setShareable(boolean shareable) {
        this.shareable = shareable;
    }

    public boolean isExpandable() {
        return expandable;
    }

    public void setExpandable(boolean expandable) {
        this.expandable = expandable;
    }
}
