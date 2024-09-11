package impl;

public class ExpenseItem {
    private int expenseId;
    private String description;
    private double amount;

    public ExpenseItem (int expenseId, String description, double amount) {
        this.expenseId = expenseId;
        this.description = description;
        this.amount = amount;
    }

    public int getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(int expenseId) {
        this.expenseId = expenseId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }


    @Override
    public String toString() {
        return String.format("ExpenseID = %d%nDescription= %s%nAmount = %.2f", expenseId, description, amount);
    }
}
