package com.Tracker.App;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import com.Tracker.Service.ExpenseService;
import com.Tracker.Service.ExpenseServiceImpl;
import com.Tracker.daoImplementation.ExpenseDaoImpl;
import com.Tracker.model.Expense;
import com.Tracker.model.ExpenseCategory;

public class ExpenseTrackerApplication {
	
	    private static final Scanner SCANNER = new Scanner(System.in);
	    private static final ExpenseService EXPENSE_SERVICE = new ExpenseServiceImpl(new ExpenseDaoImpl());

	    public static void main(String[] args) {
	        boolean running = true;

	        while (running) {
	            printMenu();
	            int choice = readInt("Choose an option: ");

	            switch (choice) {
	                case 1:
	                    addExpense();
	                    break;
	                case 2:
	                    deleteExpense();
	                    break;
	                case 3:
	                    displayAllExpenses();
	                    break;
	                case 4:
	                    displayTotalExpense();
	                    break;
	                case 5:
	                    displayHighestExpense();
	                    break;
	                case 6:
	                    displayCategoryWiseExpenses();
	                    break;
	                case 0:
	                    running = false;
	                    System.out.println("Expense Tracker closed.");
	                    break;
	                default:
	                    System.out.println("Please choose a valid option.");
	            }
	        }
	        SCANNER.close();
	    }

	    private static void printMenu() {
	        System.out.println("\n--- Personal Expense Tracker ---");
	        System.out.println("1. Add expense");
	        System.out.println("2. Delete expense");
	        System.out.println("3. Display all expenses");
	        System.out.println("4. Calculate total expense");
	        System.out.println("5. Find highest expense");
	        System.out.println("6. Display category-wise expenses");
	        System.out.println("0. Exit");
	    }

	    private static void addExpense() {
	        int expenseId = readInt("Expense ID: ");
	        System.out.print("Title: ");
	        String title = SCANNER.nextLine();
	        double amount = readDouble("Amount: ");
	        ExpenseCategory category = readCategory();
	        LocalDate expenseDate = readDate();
	        System.out.print("Notes: ");
	        String notes = SCANNER.nextLine();

	        Expense expense = new Expense(expenseId, title, amount, category, expenseDate, notes);
	        if (EXPENSE_SERVICE.addExpense(expense)) {
	            System.out.println("Expense added successfully.");
	        } else {
	            System.out.println("Expense was not added. Use a unique positive ID and a positive amount.");
	        }
	    }

	    private static void deleteExpense() {
	        int expenseId = readInt("Expense ID to delete: ");
	        if (EXPENSE_SERVICE.deleteExpense(expenseId)) {
	            System.out.println("Expense deleted successfully.");
	        } else {
	            System.out.println("No expense was found with that ID.");
	        }
	    }

	    private static void displayAllExpenses() {
	        Expense[] expenses = EXPENSE_SERVICE.getAllExpenses();
	        if (expenses.length == 0) {
	            System.out.println("No expenses recorded yet.");
	            return;
	        }

	        System.out.println("\nID | Title | Amount | Category | Date | Notes");
	        for (int index = 0; index < expenses.length; index++) {
	            Expense expense = expenses[index];
	            System.out.println(expense.getExpenseId() + " | " + expense.getTitle() + " | "
	                    + expense.getAmount() + " | " + expense.getCategory() + " | "
	                    + expense.getExpenseDate() + " | " + expense.getNotes());
	        }
	    }

	    private static void displayTotalExpense() {
	        System.out.printf("Total expense: %.2f%n", EXPENSE_SERVICE.calculateTotalExpense());
	    }

	    private static void displayHighestExpense() {
	        Expense highestExpense = EXPENSE_SERVICE.findHighestExpense();
	        if (highestExpense == null) {
	            System.out.println("No expenses recorded yet.");
	            return;
	        }
	        System.out.println("Highest expense: " + highestExpense);
	    }

	    private static void displayCategoryWiseExpenses() {
	        double[] categoryTotals = EXPENSE_SERVICE.calculateCategoryWiseExpenses();
	        ExpenseCategory[] categories = ExpenseCategory.values();

	        System.out.println("\nCategory-wise expenses:");
	        for (int index = 0; index < categories.length; index++) {
	            System.out.printf("%s: %.2f%n", categories[index], categoryTotals[index]);
	        }
	    }

	    private static int readInt(String prompt) {
	        while (true) {
	            System.out.print(prompt);
	            String input = SCANNER.nextLine();
	            try {
	                return Integer.parseInt(input);
	            } catch (NumberFormatException exception) {
	                System.out.println("Enter a whole number.");
	            }
	        }
	    }

	    private static double readDouble(String prompt) {
	        while (true) {
	            System.out.print(prompt);
	            String input = SCANNER.nextLine();
	            try {
	                return Double.parseDouble(input);
	            } catch (NumberFormatException exception) {
	                System.out.println("Enter a valid amount.");
	            }
	        }
	    }

	    private static ExpenseCategory readCategory() {
	        ExpenseCategory[] categories = ExpenseCategory.values();
	        System.out.println("Categories:");
	        for (int index = 0; index < categories.length; index++) {
	            System.out.println((index + 1) + ". " + categories[index]);
	        }

	        while (true) {
	            int categoryChoice = readInt("Category number: ");
	            if (categoryChoice >= 1 && categoryChoice <= categories.length) {
	                return categories[categoryChoice - 1];
	            }
	            System.out.println("Choose a category from the displayed list.");
	        }
	    }

	    private static LocalDate readDate() {
	        while (true) {
	            System.out.print("Date (YYYY-MM-DD): ");
	            try {
	                return LocalDate.parse(SCANNER.nextLine());
	            } catch (DateTimeParseException exception) {
	                System.out.println("Enter the date in YYYY-MM-DD format.");
	            }
	        }
	    }
	}
