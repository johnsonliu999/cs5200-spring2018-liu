package edu.northeastern.cs5200.cs5200spring2018liu.daos;

import edu.northeastern.cs5200.cs5200spring2018liu.models.*;
import edu.northeastern.cs5200.cs5200spring2018liu.utils.ConnectDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

public class WidgetDao {

    private static WidgetDao instance = null;

    private WidgetDao () {}

    public static WidgetDao getInstance() {
        if (instance == null) instance = new WidgetDao();
        return instance;
    }

    // type is not included in basic attrs
    private static final String[] WIDGET_ATTRS = {"id", "name", "width", "height", "cssClass", "cssStyle", "text", "`order`", "page", "type"};
    private static final String[] HEADING_ATTRS = {"size"};
    private static final String[] HTML_ATTRS = {"html"};
    private static final String[] IMAGE_ATTRS = {"src"};
    private static final String[] YOUTUBE_ATTRS = {"url", "shareable", "expandable"};

    private static final String CREATE_WIDGET = "INSERT INTO widget (%s) VALUES (%s)";
    private static final String CREATE_HEADING = "INSERT INTO heading (id, size) VALUES (?, ?)";
    private static final String CREATE_HTML = "INSERT INTO html (id, html) VALUES (?, ?)";
    private static final String CREATE_IMAGE = "INSERT INTO image (id, src) VALUES (?, ?)";
    private static final String CREATE_YOUTUBE = "INSERT INTO youtube (id, url, shareable, expandable) VALUES (?, ?, ?)";

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

            PreparedStatement statement = prepareCreateWidget(conn);
            PreparedStatement specStatement = prepareCreateSpecWidget(conn, widget);
            setWidget(statement, specStatement, widget);

            res = statement.executeUpdate();
            res = specStatement.executeUpdate();

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
    private final static String UPDATE_HEADING = "UPDATE heading SET id=?, size=? WHERE id=?";
    private final static String UPDATE_HTML = "UPDATE html SET id=?, html=? WHERE id=?";
    private final static String UPDATE_IMAGE = "UPDATE heading SET id=?, src=? WHERE id=?";
    private final static String UPDATE_YOUTUBE = "UPDATE youtube SET id=?, url=?, shareable=?, expandable=? WHERE id=?";


    public int updateWidget(int widgetId, Widget widget) {

        int res = -1;
        try (Connection conn = ConnectDB.getConnection()) {
            PreparedStatement statement = prepareUpdateWidget(conn);
            PreparedStatement specStatement = preparedUpdateSpecWidget(conn, widget);
            setWidget(statement, specStatement, widget);
            statement.setInt(WIDGET_ATTRS.length+1, widgetId);
            specStatement.setInt(specStatement.getParameterMetaData().getParameterCount(), widgetId);

            res = statement.executeUpdate();
            res = specStatement.executeUpdate();
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

    private static final String FIND_ALL_SPEC = "SELECT * FROM %s";
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

        try (Connection conn = ConnectDB.getConnection()) {
            PreparedStatement stmt =  conn.prepareStatement(String.format(FIND_ALL_SPEC, type));
            ResultSet specResults =  stmt.executeQuery();
            if (specResults.next())
                switch (type) {
                    case "heading":
                        int size = specResults.getInt("size");
                        return new HeadingWidget(id, name, height, width, cssClass, cssStyle, text, order, pageId, size);
                    case "html":
                        String html = specResults.getString("html");
                        return new HtmlWidget(id, name, height, width, cssClass, cssStyle, text, order, pageId, html);
                    case "image":
                        String src = specResults.getString("src");
                        return new ImageWidget(id, name, height, width, cssClass, cssStyle, text, order, pageId, src);
                    case "youtube":
                        String url = specResults.getString("url");
                        boolean shareable = specResults.getBoolean("shareable");
                        boolean expandable = specResults.getBoolean("expandable");
                        return new YouTubeWidget(id, name, height, width, cssClass, cssStyle, text, order, pageId, url, shareable, expandable);
                    default:
                        return null;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    private PreparedStatement prepareUpdateWidget(Connection connection) throws SQLException {
        String placeholder = Arrays.stream(WIDGET_ATTRS).map(s -> s + "=?").collect(Collectors.joining(","));
        return connection.prepareStatement(String.format(UPDATE_WIDGET, placeholder));
    }

    @SuppressWarnings("Duplicates")
    private PreparedStatement preparedUpdateSpecWidget(Connection connection, Widget widget) throws SQLException {
        if (widget instanceof HeadingWidget) return connection.prepareStatement(UPDATE_HEADING);
        if (widget instanceof HtmlWidget) return connection.prepareStatement(UPDATE_HTML);
        if (widget instanceof ImageWidget) return connection.prepareStatement(UPDATE_IMAGE);
        if (widget instanceof YouTubeWidget) return connection.prepareStatement(UPDATE_YOUTUBE);

        return null;
    }

    /**
     * prepare the sql
     * @param connection
     * @return
     * @throws SQLException
     */
    private PreparedStatement prepareCreateWidget(Connection connection) throws SQLException {

        String attr = String.join(",", WIDGET_ATTRS);
        String[] ss = new String[WIDGET_ATTRS.length];
        Arrays.fill(ss, "?");
        String placeholder = String.join(",", ss);
        return connection.prepareStatement(String.format(CREATE_WIDGET, attr, placeholder));
    }

    @SuppressWarnings("Duplicates")
    private PreparedStatement prepareCreateSpecWidget(Connection connection, Widget widget) throws SQLException {
        if (widget instanceof HeadingWidget) return connection.prepareStatement(CREATE_HEADING);
        if (widget instanceof HtmlWidget) return connection.prepareStatement(CREATE_HTML);
        if (widget instanceof ImageWidget) return connection.prepareStatement(CREATE_IMAGE);
        if (widget instanceof YouTubeWidget) return connection.prepareStatement(CREATE_YOUTUBE);

        return null;
    }

    /**
     *
     * @param statement
     * @param widget
     * @throws SQLException
     */
    private void setWidget(PreparedStatement statement, PreparedStatement specStatement, Widget widget) throws SQLException {
        int pos = 0;

        if (widget.getId() == 0)
            statement.setNull(++pos, Types.INTEGER);
        else
            statement.setInt(++pos, widget.getId());

        statement.setString(++pos, widget.getName());
        statement.setInt(++pos, widget.getWidth());
        statement.setInt(++pos, widget.getHeight());
        statement.setString(++pos, widget.getCssClass());
        statement.setString(++pos, widget.getCssStyle());
        statement.setString(++pos, widget.getText());
        statement.setInt(++pos, widget.getOrder());
        statement.setInt(++pos, widget.getPageId());

        // don't forget to set 'type'
        if (widget instanceof HeadingWidget) {
            HeadingWidget headingWidget = (HeadingWidget) widget;
            statement.setString(++pos, "heading");

            pos = 0;
            specStatement.setInt(++pos, widget.getId());
            if (headingWidget.getSize() != 0)
                specStatement.setInt(++pos, headingWidget.getSize());
            else
                specStatement.setInt(++pos, 2);
        } else if (widget instanceof HtmlWidget) {
            HtmlWidget htmlWidget = (HtmlWidget) widget;
            statement.setString(++pos, "html");

            pos = 0;
            specStatement.setInt(++pos, widget.getId());
            specStatement.setString(++pos, htmlWidget.getHtml());
        } else if (widget instanceof ImageWidget) {
            ImageWidget imageWidget = (ImageWidget) widget;
            statement.setString(++pos, "image");

            pos = 0;
            specStatement.setInt(++pos, widget.getId());
            specStatement.setString(++pos, imageWidget.getSrc());
        } else if (widget instanceof YouTubeWidget) {
            YouTubeWidget youTubeWidget = (YouTubeWidget) widget;
            statement.setString(++pos, "youtube");

            pos = 0;
            specStatement.setInt(++pos, widget.getId());
            specStatement.setString(++pos, youTubeWidget.getUrl());
            specStatement.setBoolean(++pos, youTubeWidget.isShareable());
            specStatement.setBoolean(++pos, youTubeWidget.isExpandable());
        }
    }
}
