package edu.northeastern.cs5200.cs5200spring2018liu.models;

public class HtmlWidget extends Widget {
    private String html;

    public HtmlWidget(int id, String name, int height, int width, String cssClass, String cssStyle, String text, int order, int pageId, String html) {
        super(id, name, height, width, cssClass, cssStyle, text, order, pageId);
        this.html = html;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    @Override
    public String toString() {
        return "HtmlWidget{" +
                "html='" + html + '\'' +
                "} " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HtmlWidget)) return false;
        if (!super.equals(o)) return false;

        HtmlWidget that = (HtmlWidget) o;

        return html != null ? html.equals(that.html) : that.html == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (html != null ? html.hashCode() : 0);
        return result;
    }
}
