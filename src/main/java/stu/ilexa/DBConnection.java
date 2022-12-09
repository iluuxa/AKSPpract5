package stu.ilexa;

import java.sql.*;
import java.util.ArrayList;

public class DBConnection {
    private static final String url = "jdbc:sqlite:D:/trash/pr5db.s3db";
    private static Connection connection;

    public static void connect() {
        try {

            // create a connection to the database
            connection = DriverManager.getConnection(url);

            System.out.println("Connection to SQLite has been established.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } /*finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }*/
    }

    public DBConnection() {
        connect();
    }

    public ArrayList<OutputMessage> selectAll() {
        try {
            if(connection.isClosed()){connect();}
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        String sql = "SELECT id, author, message, time FROM messages";
        ArrayList<OutputMessage> result = new ArrayList<>();
        try (Connection conn = connection;
             Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            // loop through the result set
            while (resultSet.next()) {
                result.add(new OutputMessage(resultSet.getInt("id"),
                        resultSet.getString("time"),
                        resultSet.getString("author"),
                        resultSet.getString("message")));
            }
            return result;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void insert(OutputMessage outputMessage) {
        String sql = "INSERT INTO messages(author,message,time) VALUES(?,?,?)";
        try {
            if(connection.isClosed()){connect();}
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try (Connection conn = connection;
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setString(1, outputMessage.getFrom());
            preparedStatement.setString(2, outputMessage.getText());
            preparedStatement.setString(3, outputMessage.getTime());
            preparedStatement.executeUpdate();
            System.out.println("Insertion complete");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
