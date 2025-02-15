import java.sql.*;
// 1.	List all expenses incurred and calculate the total spend
// 2.	Add a new expense
// 3.	Delete an expense (by id)
// 4.	List all income earned and calculate total income
// 5.	Add a new income
// 6.	Delete an income (by id)
// 7.	List all income and expenses for a particular month and display the total income,
//    expenditure, and how much money they should have left over.

public class Main {
  public static void main(String[] args) {
    Main app = new Main();
    app.sqlStart();
  }

  // Start the connection to the SQL server
  public void sqlStart() {
    String url = "jdbc:mysql://localhost:3306/";
    String dbName = "oopca4db";
    String userName = "root";
    String password = "";

    System.out.println("[-] Connecting to database...");

    // Atempt to connect to the database
    try (Connection conn = DriverManager.getConnection(url + dbName, userName, password)) {
      System.out.println("[+] Connected to database");
    } catch (SQLException e) {
      System.out.println("[!] An error ocurred - " + e.getMessage());
    }
  }
}

