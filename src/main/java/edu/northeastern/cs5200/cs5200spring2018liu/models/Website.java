package edu.northeastern.cs5200.cs5200spring2018liu.models;

import java.util.Collection;
import java.sql.Date;

public class Website {
    private int id;
    private String name;
    private String description;
    private Date created;
    private Date updated;
    private int visits;
    private Collection<Page> pages;
    private int developerId;

    public Website(int id, String name, String description, Date created, Date updated, int visits, Collection<Page> pages, int developerId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.created = created;
        this.updated = updated;
        this.visits = visits;
        this.pages = pages;
        this.developerId = developerId;
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

    public int getVisits() {
        return visits;
    }

    public void setVisits(int visits) {
        this.visits = visits;
    }

    public Collection<Page> getPages() {
        return pages;
    }

    public void setPages(Collection<Page> pages) {
        this.pages = pages;
    }

    public int getDeveloperId() {
        return developerId;
    }

    public void setDeveloperId(int developerId) {
        this.developerId = developerId;
    }

    @Override
    public String toString() {
        return "Website{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", created=" + created +
                ", updated=" + updated +
                ", visits=" + visits +
                ", pages=" + pages +
                ", developerId=" + developerId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Website)) return false;

        Website website = (Website) o;

        if (id != website.id) return false;
        if (visits != website.visits) return false;
        if (developerId != website.developerId) return false;
        if (name != null ? !name.equals(website.name) : website.name != null)
            return false;
        if (description != null ? !description.equals(website.description) : website.description != null)
            return false;
        if (created != null ? !created.equals(website.created) : website.created != null)
            return false;
        if (updated != null ? !updated.equals(website.updated) : website.updated != null)
            return false;
        return pages != null ? pages.equals(website.pages) : website.pages == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (created != null ? created.hashCode() : 0);
        result = 31 * result + (updated != null ? updated.hashCode() : 0);
        result = 31 * result + visits;
        result = 31 * result + (pages != null ? pages.hashCode() : 0);
        result = 31 * result + developerId;
        return result;
    }
}
