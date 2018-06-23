package edu.northeastern.cs5200.cs5200spring2018liu.daos;

import edu.northeastern.cs5200.cs5200spring2018liu.models.HeadingWidget;
import edu.northeastern.cs5200.cs5200spring2018liu.models.Page;
import edu.northeastern.cs5200.cs5200spring2018liu.models.Website;
import edu.northeastern.cs5200.cs5200spring2018liu.models.Widget;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.sql.Date;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class WebsiteDaoTest {

    private WebsiteDao websiteDao = WebsiteDao.getInstance();
    private PageDao pageDao = PageDao.getInstance();

    private Widget headingWidget = new HeadingWidget(6, "headingWidget",
            12, 32, "btn", "color: yellow",
            "headline", 3, 123, 5);
    private Page indexPage = new Page(100, "Index", "index page",
            new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()),
            1000, Collections.singletonList(headingWidget), 123);

    private Website heartWebsite = new Website(521, "heart", "for heart",
            new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()), 360,
            Collections.singletonList(indexPage), 0);

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void S1_createWebsiteForDeveloper() throws Exception {
        int res = websiteDao.createWebsiteForDeveloper(34, heartWebsite);
        assertEquals(1, res);
    }

    @Test
    public void S2_findAllWebsites() throws Exception {
        Collection<Website> websites = websiteDao.findAllWebsites();
        assertNotEquals(0, websites.size());
    }

    @Test
    public void S3_findWebsitesForDeveloper() throws Exception {
        Collection<Website> websites = websiteDao.findWebsitesForDeveloper(34);
        assertNotEquals(0, websites.size());
    }

    @Test
    public void S4_findWebsiteById() throws Exception {
        Website website = websiteDao.findWebsiteById(heartWebsite.getId());
        assertNotNull(website);
    }

    @Test
    public void S5_updateWebsite() throws Exception {
        heartWebsite.setDeveloperId(34);
        heartWebsite.setName("test");

        int res = websiteDao.updateWebsite(heartWebsite.getId(), heartWebsite);
        Page page = pageDao.findPageById(indexPage.getId());

        assertEquals(1, res);
        Website website = websiteDao.findWebsiteById(heartWebsite.getId());

        assertNotNull(website);
        assertEquals(heartWebsite.getName(), website.getName());
        assertNotNull(page);
    }

    @Test
    public void S6_deleteWebsite() throws Exception {
        int res = websiteDao.deleteWebsite(heartWebsite.getId());
        Website website = websiteDao.findWebsiteById(heartWebsite.getId());
        Page page = pageDao.findPageById(indexPage.getId());

        assertEquals(1, res);
        assertNull(website);
        assertNull(page);
    }

}