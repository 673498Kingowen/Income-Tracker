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
        double totalExpense = 0;
        for (ExpenseItem expense : expenses.values()) {
            totalExpense += expense.getAmount();
        }
        return  totalExpense;
    }

    public List<ExpenseItem> getAllExpenses() {
        return new ArrayList<>(expenses.values());
    }

    public void addIncome(String description, double amount) {
        IncomeItem newIncome = new IncomeItem(nextIncomeId++, description, amount);
        income.put(newIncome.getIncomeId(), newIncome);
    }

    public void removeIncome(int incomeId) {
        income.remove(incomeId);
    }

    public double getTotalIncome() {
        double totalIncome = 0;
        for (IncomeItem Income : income.values()) {
            totalIncome += Income.getAmount();
        }
        return  totalIncome;
    }

    public List<IncomeItem> getAllIncome() {
       return new ArrayList<>(income.values());
    }

    public void sortExpensesByAmount() {
        // must fix
        for (int i = 0; i < expenses.size(); i++) {
            ExpenseItem current = expenses.get(i);
            int j = i - 1;
            while (j >= 0 && expenses.get(i).getAmount() < expenses.get(j).getAmount()) {
                expenses.set(j + 1, expenses.get(j));
                j--;
            }
            expenses.set(j + 1, current);
        }
    }

    public void sortExpensesByDescription() {

    }
    public void sortIncomeByAmount(){

    }
    public void sortIncomeByDescription(){

    }

    public double getRemainingBalance(){
        return;
    }
}
