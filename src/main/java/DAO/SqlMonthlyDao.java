package DAO;

// This class contains all of the DAO objects for the monthly charts

import java.sql.*;

public class SqlMonthlyDao extends SqlDao {
  // Method to show the current P&L of the month
  public void monthlyGet(int month) {
    double totalMonthly = 0;
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    String query = "SELECT 'expense' AS type, expenseID AS id, title, amount, dateIncurred AS date FROM `expense` WHERE MONTH(dateIncurred) = ? UNION ALL SELECT 'income' AS type, incomeID AS id, title, amount, dateEarned AS date FROM income WHERE MONTH(dateEarned) = ? ORDER BY `date` DESC;";

    try {
      // Make the connection
      conn = this.getConnection();

      // Prepare our query
      ps = conn.prepareStatement(query);
      ps.setInt(1, month);
      ps.setInt(2, month);
      rs = ps.executeQuery();

      while (rs.next()) {
        String type = rs.getString("type");
        String title = rs.getString("title");
        double amount = rs.getDouble("amount");
        String date = rs.getString("date");

        if (type.equals("expense")) {
          totalMonthly -= amount;
          System.out.println("[-" + amount + ", " + title + ", " + date + "]");
        } else if (type.equals("income")) {
          totalMonthly += amount;
          System.out.println("[+" + amount + ", " + title + ", " + date + "]");
        }
      }
      System.out.printf("\nTotal: %.2f \n", totalMonthly);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
}
