package DAO;

// This class contains all of the DAO objects for income

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SqlIncomeDao extends SqlDao {
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
        System.out.println("[" + incomeId + ", " + title + ", " + amount + ", " + date + "]");
      }

      System.out.printf("\nTotal: %.2f \n", totalIncome);
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

  // Method to add a entry into the income table
  public void incomeAdd(String title, double amount) {
    Connection conn = null;
    PreparedStatement ps = null;

    // Prepare the query string, we leave ID null for auto increment
    String query = "INSERT INTO income VALUES (null, ?, ?, ?)";

    try {
      // Creating the connection
      conn = this.getConnection();
      ps = conn.prepareStatement(query);

      // Put the parameters into the query
      ps.setString(1, title);
      ps.setDouble(2, amount);
      ps.setDate(3, java.sql.Date.valueOf(LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE)));

      // Then insert the data
      ps.executeUpdate();
      System.out.println("[+] Successfully added entry to income!");
    } catch (SQLException e) {
      System.out.println("[!] SQL connection exception - " + e.getMessage() + " @" + e.getStackTrace());
    } finally {
      try {
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

  // Method for deleting entries from the income table
  public void incomeDelete(int id) {
    Connection conn = null;
    PreparedStatement ps = null;

    String query = "DELETE FROM income WHERE incomeID = ?";

    try {
      conn = this.getConnection();
      ps = conn.prepareStatement(query);

      ps.setInt(1, id);
      ps.executeUpdate();

      System.out.println("[+] Successfully deleted entry from income");
    } catch (SQLException e) {
      System.out.println("[!] SQL delete exception - " + e.getMessage());
    } finally {
      try {
        if (ps != null) {
          ps.close();
        }
        if (conn != null) {
          closeConnection(conn);
        }
      } catch (SQLException e) {
        System.out.println("[!] SQL closing connection exception - " + e.getMessage());
      }
    }
  }
}
