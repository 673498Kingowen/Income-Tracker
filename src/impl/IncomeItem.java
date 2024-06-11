package impl;

public class IncomeItem {
    private int incomeId;
    private String description;
    private double amount;

    public IncomeItem(int incomeId, String description, double amount) {
        this.incomeId = incomeId;
        this.description = description;
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setIncomeId(int incomeId) {
        this.incomeId = incomeId;
    }

    public int getIncomeId() {
        return incomeId;
    }

    @Override
    public String toString() {
        return String.format("Income: ID= %d%nDescription= %s%nAmount= %.2f", incomeId, description, amount);
    }
}
