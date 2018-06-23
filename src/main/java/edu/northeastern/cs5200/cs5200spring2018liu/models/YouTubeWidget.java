package edu.northeastern.cs5200.cs5200spring2018liu.models;

public class YouTubeWidget extends Widget {
    private String url;
    private boolean shareable;
    private boolean expandable;

    public YouTubeWidget(int id, String name, int height, int width, String cssClass, String cssStyle, String text, int order, int pageId, String url, boolean shareable, boolean expandable) {
        super(id, name, height, width, cssClass, cssStyle, text, order, pageId);
        this.url = url;
        this.shareable = shareable;
        this.expandable = expandable;
    }

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

    @Override
    public String toString() {
        return "YouTubeWidget{" +
                "url='" + url + '\'' +
                ", shareable=" + shareable +
                ", expandable=" + expandable +
                "} " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof YouTubeWidget)) return false;
        if (!super.equals(o)) return false;

        YouTubeWidget that = (YouTubeWidget) o;

        if (shareable != that.shareable) return false;
        if (expandable != that.expandable) return false;
        return url != null ? url.equals(that.url) : that.url == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (shareable ? 1 : 0);
        result = 31 * result + (expandable ? 1 : 0);
        return result;
    }
}
