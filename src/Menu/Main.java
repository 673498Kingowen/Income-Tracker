package Menu;
import impl.ExpenseTracker;

public class Main {
    public static void main(String[] args) {
        ExpenseTracker tracker = new ExpenseTracker();
        ExpenseMenu.displayMenu(tracker);
    }
}