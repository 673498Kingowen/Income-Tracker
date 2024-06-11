package Interface;
import java.util.List;

import impl.ExpenseItem;
import impl.IncomeItem;

public interface ExpenseTrackerInterface {
    void addExpense(String description, double amount);
    void removeExpense(int expenseId);
    double getTotalExpenses();
    List<ExpenseItem> getAllExpenses();

    void addIncome(String description, double amount);
    void removeIncome(int incomeId);
    double getTotalIncome();
    List<IncomeItem> getAllIncome();

    void sortExpensesByAmount();
    void sortExpensesByDescription();
    void sortIncomeByAmount();
    void sortIncomeByDescription();

    double getRemainingBalance();
}
