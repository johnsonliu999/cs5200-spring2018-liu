package edu.northeastern.cs5200.cs5200spring2018liu.daos;

import edu.northeastern.cs5200.cs5200spring2018liu.models.*;
import edu.northeastern.cs5200.cs5200spring2018liu.utils.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;

public class WidgetDao {

    // type is not included in basic attrs
    private static String CREATE_WIDGET = "INSERT INTO widget (%s) VALUES (%s)";
    private static final String[] WIDGET = {"id", "width", "height", "cssClass", "cssStyle", "text", "order", "type"};
    private static final String[] HEADING = {"sie"};
    private static final String[] HTML = {"html"};
    private static final String[] IMAGE = {"src"};
    private static final String[] YOUTUBE = {"url", "shareable", "expandable"};

    int createWidgetForPage(int pageId, Widget widget) {
        Connection connection = null;

        int res = -1;
        try {
            connection = ConnectDB.getConnection();

            PreparedStatement statement = prepareWidget(connection, widget);
            setWidget(statement, widget);
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
     * prepare the sql
     * @param connection
     * @param widget
     * @return
     * @throws SQLException
     */
    private PreparedStatement prepareWidget(Connection connection, Widget widget) throws SQLException {

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

    private void setWidget(PreparedStatement statement, Widget widget) throws SQLException {
        int pos = 0;
        statement.setInt(++pos, widget.getId());
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
    }
}
