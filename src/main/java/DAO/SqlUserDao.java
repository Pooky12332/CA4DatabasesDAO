package DAO;

// This class contains all of the DAO objects that the user needs
// These are the methods that are called when were accessing the database

import DAO.SqlUserDao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqlUserDao extends SqlDao{
  // Method to list all expenses
  public void expenseListAll() {
    double totalExpense = 0;
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    try {
      // Get connection from super class
      conn = this.getConnection();

      // Next we create our query
      String query = "SELECT * FROM expense";
      ps = conn.prepareStatement(query);

      // Then we execute that statement
      rs = ps.executeQuery();
      while (rs.next()) {
        int expenseId = rs.getInt("expenseID");
        String title = rs.getString("title");
        String cat = rs.getString("category");
        double amount = rs.getDouble("amount");
        String date = rs.getString("dateIncurred");

        totalExpense += amount;
        System.out.println("[" + expenseId + "] - " + title + ", " + cat + ", " + amount + ", " + date);
      }

      System.out.println("\n[T] - Total: " + totalExpense);
    } catch (SQLException e) {
      System.out.println("[!] SQL query exception - " + e.getMessage() + " @" + e.getStackTrace());
    } finally {
      // Lastly, we close everything
      try {
        if (rs != null) {
          rs.close();
        }
        if (ps != null) {
          ps.close();
        }
        if (conn != null) {
          closeConnection(conn);
        }
      } catch (SQLException e) {
        System.out.println("[!] SQL connection exception - " + e.getMessage() + " @" + e.getStackTrace());
      }
    }
  }

  // Method to list all income
  public void incomeListAll() {
    double totalIncome = 0;
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    try {
      // Get connection
      conn = this.getConnection();

      // Make the query
      String query = "SELECT * FROM income";
      ps = conn.prepareStatement(query);

      // Execute the query onto the datebase
      rs = ps.executeQuery();
      while (rs.next()) {
        int incomeId = rs.getInt("incomeID");
        String title = rs.getString("title");
        double amount = rs.getDouble("amount");
        String date = rs.getString("dateEarned");

        totalIncome += amount;
        System.out.println("[" + incomeId + "] - " + title + ", " + amount + ", " + date);
      }

      System.out.println("\n[T] - Total: " + totalIncome);
    } catch (SQLException e) {
      System.out.println("[!] SQL query exception - " + e.getMessage() + " @" + e.getStackTrace());
    } finally {
      try {
        if (rs != null) {
          rs.close();
        }
        if (ps != null) {
          ps.close();
        }
        if (conn != null) {
          closeConnection(conn);
        }
      } catch (SQLException e) {
        System.out.println("[!] SQL connection exception - " + e.getMessage() + " @" + e.getStackTrace());
      }
    }
  }
}