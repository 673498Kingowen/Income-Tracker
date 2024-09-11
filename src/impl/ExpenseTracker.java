package impl;
import Interface.ExpenseTrackerInterface;
import java.util.*;
import java.util.stream.Collectors;

public class ExpenseTracker implements ExpenseTrackerInterface{
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

    public ExpenseItem getExpenseById(int expenseId) {
        return expenses.get(expenseId);
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

    @Override
    public IncomeItem getIncomeById(int incomeId) {
        return income.get(incomeId);
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
        List<ExpenseItem> expenseList = new ArrayList<>(expenses.values());
        expenseList.sort(Comparator.comparingDouble(ExpenseItem::getAmount));
        expenses.clear();
        for (ExpenseItem expense : expenseList) {
            expenses.put(expense.getExpenseId(), expense);
        }
    }

    public void sortExpensesByDescription() {
        List<ExpenseItem> expenseList = new ArrayList<>(expenses.values());
        expenseList.sort(Comparator.comparing(ExpenseItem::getDescription));
        expenses.clear();
        for (ExpenseItem expense : expenseList) {
            expenses.put(expense.getExpenseId(), expense);
        }
    }
    public void sortIncomeByAmount(){
        List<IncomeItem> incomeList = new ArrayList<>(income.values());
        incomeList.sort(Comparator.comparingDouble(IncomeItem::getAmount));
        income.clear();
        for (IncomeItem incomeItem : incomeList) {
            income.put(incomeItem.getIncomeId(), incomeItem);
        }
    }
    public void sortIncomeByDescription(){
        List<IncomeItem> incomeList = new ArrayList<>(income.values());
        incomeList.sort(Comparator.comparing(IncomeItem::getDescription));
        income.clear();
        for (IncomeItem incomeItem : incomeList) {
            income.put(incomeItem.getIncomeId(), incomeItem);
        }
    }

    public double getRemainingBalance(){
        return getTotalIncome() - getTotalExpenses();
    }

    public ExpenseItem searchById(int expenseId) {
        return expenses.get(expenseId);  // Return the expense if found, otherwise null
    }

    // Search by description (partial match)
    public List<ExpenseItem> searchByDescription(String description) {
        return expenses.values().stream()
                .filter(expense -> expense.getDescription().toLowerCase().contains(description.toLowerCase()))
                .collect(Collectors.toList());  // Return a list of matching expenses
    }
}
