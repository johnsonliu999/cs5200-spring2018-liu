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
}
