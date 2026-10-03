package com.Tracker.Service;

import com.Tracker.dao.ExpenseDao;
import com.Tracker.model.Expense;
import com.Tracker.model.ExpenseCategory;

public class ExpenseServiceImpl implements ExpenseService {
	    private final ExpenseDao expenseDao;

	    public ExpenseServiceImpl(ExpenseDao expenseDao) {
	        this.expenseDao = expenseDao;
	    }

	    @Override
	    public boolean addExpense(Expense expense) {
	        if (expense == null || expense.getExpenseId() <= 0 || expense.getAmount() <= 0) {
	            return false;
	        }
	        if (expenseDao.getExpenseById(expense.getExpenseId()) != null) {
	            return false;
	        }
	        return expenseDao.addExpense(expense);
	    }

	    @Override
	    public boolean deleteExpense(int expenseId) {
	        return expenseDao.deleteExpenseById(expenseId);
	    }

	    @Override
	    public Expense[] getAllExpenses() {
	        return expenseDao.getAllExpenses();
	    }

	    @Override
	    public double calculateTotalExpense() {
	        Expense[] expenses = expenseDao.getAllExpenses();
	        double total = 0;

	        for (int index = 0; index < expenses.length; index++) {
	            total = total + expenses[index].getAmount();
	        }
	        return total;
	    }

	    @Override
	    public Expense findHighestExpense() {
	        Expense[] expenses = expenseDao.getAllExpenses();
	        if (expenses.length == 0) {
	            return null;
	        }

	        Expense highestExpense = expenses[0];
	        for (int index = 1; index < expenses.length; index++) {
	            if (expenses[index].getAmount() > highestExpense.getAmount()) {
	                highestExpense = expenses[index];
	            }
	        }
	        return highestExpense;
	    }

	    @Override
	    public double[] calculateCategoryWiseExpenses() {
	        Expense[] expenses = expenseDao.getAllExpenses();
	        double[] categoryTotals = new double[ExpenseCategory.values().length];

	        for (int index = 0; index < expenses.length; index++) {
	            int categoryIndex = expenses[index].getCategory().ordinal();
	            categoryTotals[categoryIndex] = categoryTotals[categoryIndex] + expenses[index].getAmount();
	        }
	        return categoryTotals;
	    }
	}
