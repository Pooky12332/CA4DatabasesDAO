package BusinessObjects;

import DAO.SqlIncomeDao;
import DAO.SqlExpenseDao;
import DAO.SqlMonthlyDao;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    mainMenu();
  }

  public static void mainMenu() {
    Scanner kb = new Scanner(System.in);
    System.out.print("[1] - Expense\n[2] - Income\n[3] - Monthly\n\n: ");
    int input = kb.nextInt();

    try {
      if (input == 1) {
        expenseMenu();
      } else if (input == 2) {
        incomeMenu();
      } else if (input == 3) {
        monthlyMenu();
      } else {
        System.out.println("[!] Invalid input, check your choice again\n");
        mainMenu();
      }
    } catch (InputMismatchException e) {
      System.out.println("[!] Invalid input, check your choice again\n");
      mainMenu();
    }
  }

  // Menu for dealing with expenses
  public static void expenseMenu() {
    Scanner kb = new Scanner(System.in);
    DAO.SqlExpenseDao expenseDao = new SqlExpenseDao();
    System.out.print("[1] - View entries\n[2] - Add an entry\n[3] - Delete an entry\n[4] - Back\n\n: ");
    int input = kb.nextInt();

    try {
      if (input == 1) {
        expenseDao.expenseListAll();
      } else if (input == 2) {
        try {
          System.out.print("[?] Name of entry?\n: ");
          String entryName = kb.next();
          System.out.print("[?] Category of entry?\n: ");
          String entryCategory = kb.next();
          System.out.print("[?] Amount of entry? (to nearest second decimal)\n: ");
          double entryAmount = kb.nextDouble();

          expenseDao.expenseAdd(entryName, entryCategory, entryAmount);
        } catch (InputMismatchException e) {
          System.out.println("[!] Invalid inputs, check your inputs again\n");
          expenseMenu();
        }
      } else if (input == 3) {
        try {
          System.out.print("[?] ID of entry?\n: ");
          int entryID = kb.nextInt();
          expenseDao.expenseDelete(entryID);
        } catch (InputMismatchException e) {
          System.out.println("[!] Invalid inputs, check your inputs again\n");
          expenseMenu();
        }
      } else if (input == 4) {
        mainMenu();
      }
      expenseMenu();
    } catch (InputMismatchException e) {
      System.out.println("[!] Invalid input, check your choice again\n");
      expenseMenu();
    }
  }

  public static void incomeMenu() {
    Scanner kb = new Scanner(System.in);
    DAO.SqlIncomeDao incomeDao = new SqlIncomeDao();
    System.out.print("[1] - View entries\n[2] - Add an entry\n[3] - Delete an entry\n[4] - Back\n\n: ");
    int input = kb.nextInt();

    try {
      if (input == 1) {
        incomeDao.incomeListAll();
        incomeMenu();
      } else if (input == 2) {
        try {
          System.out.print("[?] Name of entry?\n: ");
          String entryName = kb.next();
          System.out.print("[?] Amount of entry? (to nearest second decimal)\n: ");
          double entryAmount = kb.nextDouble();

          incomeDao.incomeAdd(entryName, entryAmount);
        } catch (InputMismatchException e) {
          System.out.println("[!] Invalid inputs, check your inputs again\n");
          incomeMenu();
        }
      } else if (input == 3) {
        try {
          System.out.print("[?] ID of entry?\n: ");
          int entryID = kb.nextInt();
          incomeDao.incomeDelete(entryID);
        } catch (InputMismatchException e) {
          System.out.println("[!] Invalid inputs, check your inputs again\n");
          incomeMenu();
        }
      } else if (input == 4) {
        mainMenu();
      }
      incomeMenu();
    } catch (InputMismatchException e) {

    }
  }

  // Method for showing the current month
  public static void monthlyMenu() {
    DAO.SqlMonthlyDao monthlyDao = new SqlMonthlyDao();
    Scanner kb = new Scanner(System.in);
    System.out.print("[?] Month to view? (1-12)\n: ");
    int entryMonth = kb.nextInt();
    monthlyDao.monthlyGet(entryMonth);
    mainMenu();
  }
}