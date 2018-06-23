package edu.northeastern.cs5200.cs5200spring2018liu.models;

public class HeadingWidget extends Widget {

    private int size;

    public HeadingWidget(int id, String name, int height, int width, String cssClass, String cssStyle, String text, int order, int pageId, int size) {
        super(id, name, height, width, cssClass, cssStyle, text, order, pageId);
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "HeadingWidget{" +
                "size=" + size +
                "} " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HeadingWidget)) return false;
        if (!super.equals(o)) return false;

        HeadingWidget that = (HeadingWidget) o;

        return size == that.size;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + size;
        return result;
    }
}
