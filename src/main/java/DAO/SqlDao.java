package DAO;

// This class creates the basic SQL methods that we need for our other DAO's
// If we didnt have these classes, we would have to write them over and over
// Making this super class means we can avoid that

import java.sql.*;

public class SqlDao {
  // Method to open connections
  public Connection getConnection() throws SQLException{
    // Creating the variable needed to create a connection
    String driver = "com.mysql.cj.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/oopca4db";
    String user = "root";
    String password = "";
    Connection conn = null;

    // We take our driver and set our null connection to that driver connection with our given variables
    try {
      Class.forName(driver);
      conn = DriverManager.getConnection(url, user, password);
    } catch (ClassNotFoundException e) {
      System.out.println("[!] Driver class was not found - " + e.getMessage() + " @" + e.getStackTrace());
    } catch (SQLException e) {
      System.out.println("[!] SQL connection exception - " + e.getMessage() + " @" + e.getStackTrace());
    }

    // If the connection is successful, we can return the connection object
    return conn;
  }

  // Method to close the current connection
  public void closeConnection(Connection conn) throws SQLException {
    if (conn != null) {
      conn.close();
      conn = null;
    }
  }
}