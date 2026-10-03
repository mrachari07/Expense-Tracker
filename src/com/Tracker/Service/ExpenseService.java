package com.Tracker.Service;

import com.Tracker.model.Expense;

public interface ExpenseService {
    boolean addExpense(Expense expense);

    boolean deleteExpense(int expenseId);

    Expense[] getAllExpenses();

    double calculateTotalExpense();

    Expense findHighestExpense();

    double[] calculateCategoryWiseExpenses();
}
