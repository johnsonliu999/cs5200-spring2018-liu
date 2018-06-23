package edu.northeastern.cs5200.cs5200spring2018liu.models;

abstract public class Widget {
    private int id;
    private String name;
    private int height;
    private int width;
    private String cssClass;
    private String cssStyle;
    private String text;
    private int order;
    private int pageId;

    public Widget(int id, String name, int height, int width, String cssClass, String cssStyle, String text, int order, int pageId) {
        this.id = id;
        this.name = name;
        this.height = height;
        this.width = width;
        this.cssClass = cssClass;
        this.cssStyle = cssStyle;
        this.text = text;
        this.order = order;
        this.pageId = pageId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public String getCssClass() {
        return cssClass;
    }

    public void setCssClass(String cssClass) {
        this.cssClass = cssClass;
    }

    public String getCssStyle() {
        return cssStyle;
    }

    public void setCssStyle(String cssStyle) {
        this.cssStyle = cssStyle;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getPageId() {
        return pageId;
    }

    public void setPageId(int pageId) {
        this.pageId = pageId;
    }

    @Override
    public String toString() {
        return "Widget{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", height=" + height +
                ", width=" + width +
                ", cssClass='" + cssClass + '\'' +
                ", cssStyle='" + cssStyle + '\'' +
                ", text='" + text + '\'' +
                ", order=" + order +
                ", pageId=" + pageId +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Widget)) return false;

        Widget widget = (Widget) o;

        if (id != widget.id) return false;
        if (height != widget.height) return false;
        if (width != widget.width) return false;
        if (order != widget.order) return false;
        if (pageId != widget.pageId) return false;
        if (name != null ? !name.equals(widget.name) : widget.name != null)
            return false;
        if (cssClass != null ? !cssClass.equals(widget.cssClass) : widget.cssClass != null)
            return false;
        if (cssStyle != null ? !cssStyle.equals(widget.cssStyle) : widget.cssStyle != null)
            return false;
        return text != null ? text.equals(widget.text) : widget.text == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + height;
        result = 31 * result + width;
        result = 31 * result + (cssClass != null ? cssClass.hashCode() : 0);
        result = 31 * result + (cssStyle != null ? cssStyle.hashCode() : 0);
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + order;
        result = 31 * result + pageId;
        return result;
    }
}
