package edu.northeastern.cs5200.cs5200spring2018liu.daos;

import edu.northeastern.cs5200.cs5200spring2018liu.models.HeadingWidget;
import edu.northeastern.cs5200.cs5200spring2018liu.models.Widget;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.Collection;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class WidgetDaoTest {
    private WidgetDao widgetDao;

    Widget headingWidget = new HeadingWidget(6, "headingWidget",
            12, 32, "btn", "color: yellow",
            "headline", 3, 123, 5);

    @Before
    public void setUp() throws Exception {
        widgetDao = WidgetDao.getInstance();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void S1_createWidgetForPage() throws Exception {
        int res = widgetDao.createWidgetForPage(123, headingWidget);
        assertNotEquals(-1, res);

        res = widgetDao.createWidgetForPage(123, headingWidget);
        System.out.println(res);
    }

    @Test
    public void S2_findAllWidgets() throws Exception {
        Collection<Widget> widgets = widgetDao.findAllWidgets();
        assertNotNull(widgets);
        assertNotEquals(0, widgets.size());
        System.out.println(widgets);
    }

    @Test
    public void S3_updateWidget() throws Exception {
        headingWidget.setHeight(1000);
        int res = widgetDao.updateWidget(headingWidget.getId(), headingWidget);
        HeadingWidget widget = (HeadingWidget) widgetDao.findWidgetById(headingWidget.getId());
        assertNotNull(widget);
        assertNotEquals(-1, res);
        assertEquals(1000, widget.getHeight());
    }

    @Test
    public void S4_deleteWidget() throws Exception {
        int res = widgetDao.deleteWidget(headingWidget.getId());
        assertNotEquals(-1, res);
    }
}