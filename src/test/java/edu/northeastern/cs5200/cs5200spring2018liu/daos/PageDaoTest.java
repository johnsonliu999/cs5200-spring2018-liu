package edu.northeastern.cs5200.cs5200spring2018liu.daos;

import edu.northeastern.cs5200.cs5200spring2018liu.models.HeadingWidget;
import edu.northeastern.cs5200.cs5200spring2018liu.models.Page;
import edu.northeastern.cs5200.cs5200spring2018liu.models.Widget;
import org.junit.After;
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
public class PageDaoTest {

    private PageDao pageDao = PageDao.getInstance();
    private WidgetDao widgetDao = WidgetDao.getInstance();

    private Widget headingWidget = new HeadingWidget(6, "headingWidget",
            12, 32, "btn", "color: yellow",
            "headline", 3, 123, 5);
    private Page indexPage = new Page(100, "Index", "index page",
            new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()),
            1000, Collections.singletonList(headingWidget), 123);

    @Before
    public void setup() {
    }

    @After
    public void tearDown() {
        
    }

    @Test
    public void S1_createPageForWebsite() throws Exception {
        int res = pageDao.createPageForWebsite(indexPage.getWebsitdId(), indexPage);
        assertEquals(1, res);
        assertNotNull(widgetDao.findWidgetById(headingWidget.getId()));
    }

    @Test
    public void S2_findAllPages() throws Exception {
        Collection<Page> pages = pageDao.findAllPages();
        assertNotEquals(0, pages.size());
        System.out.println(pages);
    }

    @Test
    public void S3_findPageById() throws Exception {
        Page page = pageDao.findPageById(indexPage.getId());
        assertNotNull(page);
    }

    @Test
    public void S4_findPagesForWebsite() throws Exception {
        Collection<Page> pages = pageDao.findPagesForWebsite(indexPage.getWebsitdId());
        assertNotEquals(0, pages.size());
    }

    @Test
    public void S5_updatePage() throws Exception {
        indexPage.setTitle("test");
        int res = pageDao.updatePage(indexPage.getId(), indexPage);
        assertNotEquals(-1, res);

        Page page = pageDao.findPageById(indexPage.getId());
        assertNotNull(page);
        assertEquals("test", page.getTitle());
    }

    @Test
    public void S6_deletePage() throws Exception {
        int res = pageDao.deletePage(indexPage.getId());
        assertEquals(1, res);
        assertNull(widgetDao.findWidgetById(headingWidget.getId()));

    }

}