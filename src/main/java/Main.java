import DAO.SqlUserDao;

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
    SqlUserDao dao = new SqlUserDao();
    dao.expenseListAll();
    System.out.println();
    dao.incomeListAll();
  }
}

