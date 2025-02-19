package DAO;

// This class contains all of the DAO objects for expenses

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SqlExpenseDao extends SqlDao{
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
        System.out.println("[" + expenseId + ", " + title + ", " + cat + ", " + amount + ", " + date + "]");
      }

      System.out.printf("\nTotal: %.2f \n", totalExpense);
    } catch (SQLException e) {
      System.out.println("[!] SQL query exception - " + e.getMessage());
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
        System.out.println("[!] SQL closing connection exception - " + e.getMessage());
      }
    }
  }

  // Method to add a entry into the expense table
  public void expenseAdd(String title, String cat, double amount) {
    Connection conn = null;
    PreparedStatement ps = null;

    // Prepare the query string, we leave ID null for auto increment
    String query = "INSERT INTO expense VALUES (null, ?, ?, ?, ?)";

    try {
      // Creating the connection
      conn = this.getConnection();
      ps = conn.prepareStatement(query);

      // Put the parameters into the query
      ps.setString(1, title);
      ps.setString(2, cat);
      ps.setDouble(3, amount);
      ps.setDate(4, java.sql.Date.valueOf(LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE)));

      // Then insert the data
      ps.executeUpdate();
      System.out.println("[+] Successfully added entry to expense!");
    } catch (SQLException e) {
      System.out.println("[!] SQL insert exception - " + e.getMessage());
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

  // Method for deleting entries from the expense table
  public void expenseDelete(int id) {
    Connection conn = null;
    PreparedStatement ps = null;

    String query = "DELETE FROM expense WHERE expenseID = ?";

    try {
      conn = this.getConnection();
      ps = conn.prepareStatement(query);

      ps.setInt(1, id);
      ps.executeUpdate();

      System.out.println("[+] Successfully deleted entry from expense");
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