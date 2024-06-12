package Menu;
import Interface.ExpenseTrackerInterface;
import impl.ExpenseItem;
import impl.IncomeItem;
import java.util.*;

public abstract class ExpenseTracker implements ExpenseTrackerInterface{
    private Map<Integer, ExpenseItem> expenses;
    private Map<Integer, IncomeItem> income;
    private int nextExpenseId;
    private int nextIncomeId;

    public ExpenseTracker () {
        this.expenses = new LinkedHashMap<>();
        this.income = new LinkedHashMap<>();
        this.nextExpenseId = 1;
        this.nextIncomeId = 1;
    }

    public void addExpense(String description, double amount) {
        ExpenseItem newExpense = new ExpenseItem(nextExpenseId++, description, amount);
        expenses.put(newExpense.getExpenseId(), newExpense);
    }

    public void removeExpense(int expenseId) {
        expenses.remove(expenseId);
    }

    public double getTotalExpenses() {
        double total = 0;
        for (ExpenseItem expense : expenses.values()) {
            total += expense.getAmount();
        }
        return  total;
    }

    public List<ExpenseItem> getAllExpenses() {
        return new ArrayList<>(expenses.values());
    }

    
}
