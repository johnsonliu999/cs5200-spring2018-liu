package edu.northeastern.cs5200.cs5200spring2018liu.models;

public class ImageWidget extends Widget {
    private String src;

    public ImageWidget(int id, String name, int height, int width, String cssClass, String cssStyle, String text, int order, int pageId, String src) {
        super(id, name, height, width, cssClass, cssStyle, text, order, pageId);
        this.src = src;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    @Override
    public String toString() {
        return "ImageWidget{" +
                "src='" + src + '\'' +
                "} " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ImageWidget)) return false;
        if (!super.equals(o)) return false;

        ImageWidget that = (ImageWidget) o;

        return src != null ? src.equals(that.src) : that.src == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (src != null ? src.hashCode() : 0);
        return result;
    }
}
