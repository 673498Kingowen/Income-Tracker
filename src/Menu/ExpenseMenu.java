package Menu;

import impl.ExpenseItem;
import impl.ExpenseTracker;
import impl.IncomeItem;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ExpenseMenu {
    public static void displayMenu(ExpenseTracker tracker) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("Expense Tracker Menu:");
            System.out.println("1. Add Expense");
            System.out.println("2. Remove Expense");
            System.out.println("3. View All Expenses");
            System.out.println("4. View Total Expenses");
            System.out.println("5. Add Income");
            System.out.println("6. Remove Income");
            System.out.println("7. View All Income");
            System.out.println("8. View Total Income");
            System.out.println("9. View Remaining Balance");
            System.out.println("10. Sort Expenses by Amount");
            System.out.println("11. Sort Expenses by Description");
            System.out.println("12. Sort Income by Amount");
            System.out.println("13. Sort Income by Description");
            System.out.println("14. Search Expense by ID");
            System.out.println("15. Search Expense by Description");
            System.out.println("16. Exit");

            int option = -1;
            boolean validOption = false;

            while (!validOption) {
                System.out.print("Choose an option: ");
                try {
                    option = scanner.nextInt();

                    if (option >= 1 && option <= 16) {
                        validOption = true;
                    } else {
                        System.out.println("Invalid option. Please enter a number between 1 and 14.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input! Please enter a number between 1 and 14.");
                    scanner.nextLine();
                }
            }
            scanner.nextLine();

            switch (option) {
                case 1:
                    System.out.print("Enter expense description: ");
                    String expenseDescription = scanner.nextLine().toUpperCase();

                    double expenseAmount = 0;
                    boolean validInput = false;

                    while (!validInput) {
                        System.out.print("Enter expense amount: ");
                        try {
                            expenseAmount = scanner.nextDouble();
                            validInput = true;
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input! Please enter a valid number for the expense amount.");
                            scanner.nextLine();
                        }
                    }

                    scanner.nextLine();
                    tracker.addExpense(expenseDescription, expenseAmount);
                    System.out.println("Expense added.");
                    System.out.println();
                    break;

                case 2:
                    int expenseId = 0;
                    boolean validIdInput = false;

                    while (!validIdInput) {
                        System.out.print("Enter expense ID to remove: ");
                        try {
                            expenseId = scanner.nextInt();
                            if (tracker.getExpenseById(expenseId) == null) {
                                System.out.println("Expense ID not found. Please enter a valid expense ID.");
                            } else {
                                validIdInput = true;
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input! Please enter a valid number for the expense ID.");
                            scanner.nextLine();
                        }
                    }

                    scanner.nextLine();
                    tracker.removeExpense(expenseId);
                    System.out.println("Expense removed.");
                    System.out.println();
                    break;

                case 3:
                    if (!tracker.getAllExpenses().isEmpty()) {
                        System.out.println("All Expenses:");
                        for (ExpenseItem expense : tracker.getAllExpenses()) {
                            System.out.println(expense);
                            System.out.println();
                        }
                    } else {
                        System.out.println("No expense found.");
                    }

                    System.out.println();
                    break;

                case 4:
                    System.out.println(STR."Total Expenses: \{tracker.getTotalExpenses()}");
                    System.out.println();
                    break;

                case 5:
                    System.out.print("Enter income description: ");
                    String incomeDescription = scanner.nextLine().toUpperCase();

                    double incomeAmount = 0;
                    boolean validIncome = false;

                    while (!validIncome) {
                        System.out.print("Enter income amount: ");
                        try {
                            incomeAmount = scanner.nextDouble();
                            validIncome = true;
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input! Please enter a valid number for the income amount.");
                            scanner.nextLine();
                        }
                    }

                    scanner.nextLine();
                    tracker.addIncome(incomeDescription, incomeAmount);
                    System.out.println("Income added.");
                    System.out.println();
                    break;

                case 6:
                    int incomeId = 0;
                    boolean validIdIncome = false;

                    while (!validIdIncome) {
                        System.out.print("Enter income ID to remove: ");
                        try {
                            incomeId = scanner.nextInt();
                            if (tracker.getIncomeById(incomeId) == null) {
                                System.out.println("Expense ID not found. Please enter a valid income ID.");
                            } else {
                                validIdIncome = true;
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input! Please enter a valid number for the income ID.");
                            scanner.nextLine();
                        }
                    }

                    scanner.nextLine();
                    tracker.removeIncome(incomeId);
                    System.out.println("Income removed.");
                    System.out.println();
                    break;

                case 7:
                    if (!tracker.getAllIncome().isEmpty()) {
                        System.out.println("All Income:");
                        for (IncomeItem incomeItem : tracker.getAllIncome()) {
                            System.out.println(incomeItem);
                            System.out.println();
                        }
                    } else {
                        System.out.println("No Income found.");
                    }
                    System.out.println();
                    break;

                case 8:
                    System.out.println(STR."Total Income: \{tracker.getTotalIncome()}");
                    System.out.println();
                    break;

                case 9:
                    System.out.println(STR."Remaining Balance: \{tracker.getRemainingBalance()}");
                    System.out.println();
                    break;

                case 10:
                    tracker.sortExpensesByAmount();
                    if (!tracker.getAllExpenses().isEmpty()) {
                        System.out.println("Expenses sorted by amount.");
                        tracker.getAllExpenses().forEach(expense -> {
                            System.out.println(expense);
                            System.out.println();
                        });
                    } else {
                        System.out.println("No expenses found.");
                    }

                    System.out.println();
                    break;

                case 11:
                    tracker.sortExpensesByDescription();
                    if (!tracker.getAllExpenses().isEmpty()) {
                        System.out.println("Expenses sorted by Description.");
                        tracker.getAllExpenses().forEach(expense -> {
                            System.out.println(expense);
                            System.out.println();
                        });
                    } else {
                        System.out.println("No expenses found.");
                    }

                    System.out.println();
                    break;

                case 12:
                    tracker.sortIncomeByAmount();
                    if (!tracker.getAllIncome().isEmpty()) {
                        System.out.println("Income sorted by amount.");
                        tracker.getAllIncome().forEach(income -> {
                            System.out.println(income);
                            System.out.println();
                        });
                    } else {
                        System.out.println("No expenses found.");
                    }

                    System.out.println();
                    break;

                case 13:
                    tracker.sortIncomeByDescription();
                    if (!tracker.getAllIncome().isEmpty()) {
                        System.out.println("Income sorted by description.");
                        tracker.getAllIncome().forEach(income -> {
                            System.out.println(income);
                            System.out.println();
                        });
                    } else {
                        System.out.println("No expenses found.");
                    }

                    System.out.println();
                    break;

                case 14:
                    System.out.print("Enter expense ID to search: ");
                    int expenseSearch = scanner.nextInt();
                    scanner.nextLine();

                    ExpenseItem foundExpense = tracker.searchById(expenseSearch);
                    if (foundExpense != null) {
                        System.out.println(STR."Expense found: \{foundExpense}");
                    } else {
                        System.out.println(STR."No expense found with ID: \{expenseSearch}");
                    }
                    System.out.println();
                    break;

                case 15:
                    System.out.print("Enter description to search: ");
                    String searchDescription = scanner.nextLine();

                    List<ExpenseItem> matchingExpenses = tracker.searchByDescription(searchDescription);
                    if (!matchingExpenses.isEmpty()) {
                        System.out.println("Matching Expenses:");
                        matchingExpenses.forEach(System.out::println);
                    } else {
                        System.out.println(STR."No expenses found with description: \{searchDescription}");
                    }
                    System.out.println();
                    break;

                case 16:
                    running = false;
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
                    System.out.println();
            }
        }

        scanner.close();
    }
}
