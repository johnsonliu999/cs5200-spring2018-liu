package edu.northeastern.cs5200.cs5200spring2018liu.models;

import java.util.Collection;
import java.sql.Date;
import java.util.LinkedList;

public class Page {
    private int id;
    private String title;
    private String description;
    private Date created;
    private Date updated;
    private int views;
    private Collection<Widget> widgets;
    private int websitdId;

    public Page(int id, String title, String description, Date created, Date updated, int views, Collection<Widget> widgets, int websitdId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.created = created;
        this.updated = updated;
        this.views = views;

        if (widgets == null) this.widgets = new LinkedList<>();
        else this.widgets = widgets;

        this.websitdId = websitdId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public Collection<Widget> getWidgets() {
        return widgets;
    }

    public void setWidgets(Collection<Widget> widgets) {
        this.widgets = widgets;
    }

    public int getWebsitdId() {
        return websitdId;
    }

    public void setWebsitdId(int websitdId) {
        this.websitdId = websitdId;
    }

    @Override
    public String toString() {
        return "Page{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", created=" + created +
                ", updated=" + updated +
                ", views=" + views +
                ", widgets=" + widgets +
                ", websitdId=" + websitdId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Page)) return false;

        Page page = (Page) o;

        if (id != page.id) return false;
        if (views != page.views) return false;
        if (websitdId != page.websitdId) return false;
        if (title != null ? !title.equals(page.title) : page.title != null)
            return false;
        if (description != null ? !description.equals(page.description) : page.description != null)
            return false;
        if (created != null ? !created.equals(page.created) : page.created != null)
            return false;
        if (updated != null ? !updated.equals(page.updated) : page.updated != null)
            return false;
        return widgets != null ? widgets.equals(page.widgets) : page.widgets == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (created != null ? created.hashCode() : 0);
        result = 31 * result + (updated != null ? updated.hashCode() : 0);
        result = 31 * result + views;
        result = 31 * result + (widgets != null ? widgets.hashCode() : 0);
        result = 31 * result + websitdId;
        return result;
    }
}
