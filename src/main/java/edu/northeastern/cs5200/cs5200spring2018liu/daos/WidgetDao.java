package edu.northeastern.cs5200.cs5200spring2018liu.daos;

import edu.northeastern.cs5200.cs5200spring2018liu.models.*;
import edu.northeastern.cs5200.cs5200spring2018liu.utils.ConnectDB;

import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;

public class WidgetDao {

    private static WidgetDao instance = null;

    private WidgetDao () {}

    public static WidgetDao getInstance() {
        if (instance == null) instance = new WidgetDao();
        return instance;
    }

    // type is not included in basic attrs
    private static final String CREATE_WIDGET = "INSERT INTO widget (%s) VALUES (%s)";
    private static final String[] WIDGET = {"page", "id", "name", "width", "height", "cssClass", "cssStyle", "text", "`order`", "type"};
    private static final String[] HEADING = {"size"};
    private static final String[] HTML = {"html"};
    private static final String[] IMAGE = {"src"};
    private static final String[] YOUTUBE = {"url", "shareable", "expandable"};

    /**
     *
     * @param pageId
     * @param widget
     * @return -1 : error, 0 : duplicate
     */
    public int createWidgetForPage(int pageId, Widget widget) {
        widget.setPageId(pageId);

        // if already exists
        if (findWidgetById(widget.getId()) != null) return 0;

        int res = -1;
        try (Connection conn = ConnectDB.getConnection()) {

            PreparedStatement statement = prepareCreateWidget(conn, widget);
            setWidget(statement, widget);

            System.out.println(statement);
            res = statement.executeUpdate();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return res;
    }

    private static final String FIND_ALL_WIDGETS = "SELECT * FROM widget";

    public Collection<Widget> findAllWidgets() {
        Collection<Widget> widgets = new ArrayList<>();
        try (Connection connection = ConnectDB.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery(FIND_ALL_WIDGETS);
            while(results.next()) {
                widgets.add(readWidget(results));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return widgets;
    }

    private static final String FIND_WIDGET_BY_ID = "SELECT * FROM widget WHERE id=?";

    public Widget findWidgetById(int widgetId) {
        Widget widget = null;
        try (Connection connection = ConnectDB.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(FIND_WIDGET_BY_ID);
            statement.setInt(1, widgetId);
            ResultSet results = statement.executeQuery();
            if (results.next()) {
                widget = readWidget(results);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return widget;
    }

    private static final String FIND_WIDGET_FOR_PAGE = "SELECT * FROM widget WHERE page=?";

    public Collection<Widget> findWidgetsForPage(int pageId) {
        Collection<Widget> widgets = new ArrayList<>();
        try (Connection connection = ConnectDB.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(FIND_WIDGET_BY_ID);
            statement.setInt(1, pageId);
            ResultSet results = statement.executeQuery();
            while (results.next()) {
                 widgets.add(readWidget(results));
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return widgets;
    }

    private final static String UPDATE_WIDGET = "UPDATE widget SET %s WHERE id=?";

    public int updateWidget(int widgetId, Widget widget) {

        int res = -1;
        try (Connection conn = ConnectDB.getConnection()) {
            PreparedStatement statement = prepareUpdateWidget(conn, widget);
            int pos = setWidget(statement, widget);
            statement.setInt(pos, widgetId);
            res = statement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return res;
    }

    private static final String DELETE_WIDGET = "DELETE FROM widget WHERE id=?";

    @SuppressWarnings("Duplicates")
    public int deleteWidget(int widgetId) {
        Connection connection = null;
        int res = -1;
        try {
            connection = ConnectDB.getConnection();
            PreparedStatement statement = connection.prepareStatement(DELETE_WIDGET);
            statement.setInt(1, widgetId);
            res = statement.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return res;

    }

    /**
     *
     * @param results
     * @return current result set widget
     * @throws SQLException
     */
    private Widget readWidget(ResultSet results) throws SQLException {
        String type = results.getString("type");
        int id = results.getInt("id");
        String name = results.getString("name");
        int width = results.getInt("width");
        int height = results.getInt("height");
        String cssClass = results.getString("cssClass");
        String cssStyle = results.getString("cssStyle");
        String text = results.getString("text");
        int order = results.getInt("order");
        int pageId = results.getInt("page");

        switch (type) {
            case "heading":
                int size = results.getInt("size");
                return new HeadingWidget(id, name, height, width, cssClass, cssStyle, text, order, pageId, size);
            case "html":
                String html = results.getString("html");
                return new HtmlWidget(id, name, height, width, cssClass, cssStyle, text, order, pageId, html);
            case "image":
                String src = results.getString("src");
                return new ImageWidget(id, name, height, width, cssClass, cssStyle, text, order, pageId, src);
            case "youtube":
                String url = results.getString("url");
                boolean shareable = results.getBoolean("shareable");
                boolean expandable = results.getBoolean("expandable");
                return new YouTubeWidget(id, name, height, width, cssClass, cssStyle, text, order, pageId, url, shareable, expandable);
            default:
                return null;
        }
    }

    private PreparedStatement prepareUpdateWidget(Connection connection, Widget widget) throws SQLException {

        List<String> attrs = new ArrayList<>();
        attrs.addAll(Arrays.asList(WIDGET));
        if (widget instanceof HeadingWidget) {
            attrs.addAll(Arrays.asList(HEADING));
        } else if (widget instanceof HtmlWidget) {
            attrs.addAll(Arrays.asList(HTML));
        } else if (widget instanceof ImageWidget) {
            attrs.addAll(Arrays.asList(IMAGE));
        } else if (widget instanceof YouTubeWidget) {
            attrs.addAll(Arrays.asList(YOUTUBE));
        }

        String placeholder = attrs.stream().map(s -> s + "=?").collect(Collectors.joining(","));
        return connection.prepareStatement(String.format(UPDATE_WIDGET, placeholder));
    }

    /**
     * prepare the sql
     * @param connection
     * @param widget
     * @return
     * @throws SQLException
     */
    private PreparedStatement prepareCreateWidget(Connection connection, Widget widget) throws SQLException {

        String attr = String.join(",", WIDGET) + ",";
        int attrLen = WIDGET.length;
        if (widget instanceof HeadingWidget) {
            attr += String.join(",", HEADING);
            attrLen += HEADING.length;
        } else if (widget instanceof HtmlWidget) {
            attr += String.join(",", HTML);
            attrLen += HTML.length;
        } else if (widget instanceof ImageWidget) {
            attr += String.join(",", IMAGE);
            attrLen += IMAGE.length;
        } else if (widget instanceof YouTubeWidget) {
            attr += String.join(",", YOUTUBE);
            attrLen += YOUTUBE.length;
        }

        String[] ss = new String[attrLen];
        Arrays.fill(ss, "?");
        String placeholder = String.join(",", ss);
        return connection.prepareStatement(String.format(CREATE_WIDGET, attr, placeholder));
    }

    /**
     *
     * @param statement
     * @param widget
     * @return the next set position
     * @throws SQLException
     */
    private int setWidget(PreparedStatement statement, Widget widget) throws SQLException {
        int pos = 0;

        statement.setInt(++pos, widget.getPageId());
        statement.setInt(++pos, widget.getId());
        statement.setString(++pos, widget.getName());
        statement.setInt(++pos, widget.getWidth());
        statement.setInt(++pos, widget.getHeight());
        statement.setString(++pos, widget.getCssClass());
        statement.setString(++pos, widget.getCssStyle());
        statement.setString(++pos, widget.getText());
        statement.setInt(++pos, widget.getOrder());

        // don't forget to set 'type'
        if (widget instanceof HeadingWidget) {
            HeadingWidget headingWidget = (HeadingWidget) widget;

            statement.setString(++pos, "heading");
            statement.setInt(++pos, headingWidget.getSize());
        } else if (widget instanceof HtmlWidget) {
            HtmlWidget htmlWidget = (HtmlWidget) widget;

            statement.setString(++pos, "html");
            statement.setString(++pos, htmlWidget.getHtml());
        } else if (widget instanceof ImageWidget) {
            ImageWidget imageWidget = (ImageWidget) widget;

            statement.setString(++pos, "image");
            statement.setString(++pos, imageWidget.getSrc());
        } else if (widget instanceof YouTubeWidget) {
            YouTubeWidget youTubeWidget = (YouTubeWidget) widget;

            statement.setString(++pos, "youtube");
            statement.setString(++pos, youTubeWidget.getUrl());
            statement.setBoolean(++pos, youTubeWidget.isShareable());
            statement.setBoolean(++pos, youTubeWidget.isExpandable());
        }

        return pos+1;
    }
}
