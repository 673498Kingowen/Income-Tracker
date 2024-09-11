package Gui;

import impl.ExpenseTracker;
import impl.ExpenseItem;
import impl.IncomeItem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ExpenseMenuGUI extends JFrame {
    private ExpenseTracker tracker;
    private JTextArea outputArea;

    public ExpenseMenuGUI() {
        tracker = new ExpenseTracker();

        // main window
        setTitle("Expense & Income Tracker");
        setSize(900, 500);  // Increase window size
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Output
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);

        // Button Panel with uniform button size
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 1, 20, 20));

        // Button Dimension
        Dimension buttonSize = new Dimension(200, 50);

        // Section for Expense Buttons
        JPanel expensePanel = new JPanel();
        expensePanel.setLayout(new GridLayout(3, 1, 10, 10));
        expensePanel.setBorder(BorderFactory.createTitledBorder("Expense Actions"));

        // Add Expense Button
        JButton addExpenseButton = new JButton("Add Expense");
        addExpenseButton.setPreferredSize(buttonSize);
        addExpenseButton.addActionListener(e -> showAddExpenseDialog());
        expensePanel.add(addExpenseButton);

        // Remove Expense Button
        JButton removeExpenseButton = new JButton("Remove Expense");
        removeExpenseButton.setPreferredSize(buttonSize);
        removeExpenseButton.addActionListener(e -> showRemoveExpenseDialog());
        expensePanel.add(removeExpenseButton);

        // View All Expenses Button
        JButton viewAllExpensesButton = new JButton("View All Expenses");
        viewAllExpensesButton.setPreferredSize(buttonSize);
        viewAllExpensesButton.addActionListener(new ViewAllExpensesListener());
        expensePanel.add(viewAllExpensesButton);

        // Income Buttons
        JPanel incomePanel = new JPanel();
        incomePanel.setLayout(new GridLayout(3, 1, 10, 10));
        incomePanel.setBorder(BorderFactory.createTitledBorder("Income Actions"));

        // Add Income Button
        JButton addIncomeButton = new JButton("Add Income");
        addIncomeButton.setPreferredSize(buttonSize);
        addIncomeButton.addActionListener(e -> showAddIncomeDialog());
        incomePanel.add(addIncomeButton);

        // Remove Income Button
        JButton removeIncomeButton = new JButton("Remove Income");
        removeIncomeButton.setPreferredSize(buttonSize);
        removeIncomeButton.addActionListener(e -> showRemoveIncomeDialog());
        incomePanel.add(removeIncomeButton);

        // View All Income Button
        JButton viewAllIncomeButton = new JButton("View All Income");
        viewAllIncomeButton.setPreferredSize(buttonSize);
        viewAllIncomeButton.addActionListener(new ViewAllIncomeListener());
        incomePanel.add(viewAllIncomeButton);

        // Section for Summary area
        JPanel summaryPanel = new JPanel();
        summaryPanel.setLayout(new GridLayout(2, 1, 10, 10));
        summaryPanel.setBorder(BorderFactory.createTitledBorder("Summary"));

        // View Total Expenses Button
        JButton viewTotalExpensesButton = new JButton("View Total Expenses");
        viewTotalExpensesButton.setPreferredSize(buttonSize);
        viewTotalExpensesButton.addActionListener(new ViewTotalExpensesListener());
        summaryPanel.add(viewTotalExpensesButton);

        // View Total Income Button
        JButton viewTotalIncomeButton = new JButton("View Total Income");
        viewTotalIncomeButton.setPreferredSize(buttonSize);
        viewTotalIncomeButton.addActionListener(new ViewTotalIncomeListener());
        summaryPanel.add(viewTotalIncomeButton);

        // Add all components to the window
        buttonPanel.add(expensePanel);
        buttonPanel.add(incomePanel);
        buttonPanel.add(summaryPanel);

        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.EAST);

        // Make the window visible
        setVisible(true);
    }

    // sub-window for adding an expense
    private void showAddExpenseDialog() {
        JDialog addExpenseDialog = new JDialog(this, "Add Expense", true);
        addExpenseDialog.setLayout(new GridLayout(3, 2, 10, 10));
        addExpenseDialog.setSize(300, 150);

        JLabel descLabel = new JLabel("Description:");
        JTextField descField = new JTextField();
        JLabel amountLabel = new JLabel("Amount:");
        JTextField amountField = new JTextField();
        JButton submitButton = new JButton("Add");

        addExpenseDialog.add(descLabel);
        addExpenseDialog.add(descField);
        addExpenseDialog.add(amountLabel);
        addExpenseDialog.add(amountField);
        addExpenseDialog.add(submitButton);

        submitButton.addActionListener(e -> {
            String description = descField.getText();
            double amount;
            try {
                amount = Double.parseDouble(amountField.getText());
                tracker.addExpense(description, amount);
                outputArea.setText("Expense added: " + description + " - " + amount);
                addExpenseDialog.dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(addExpenseDialog, "Please enter a valid amount.");
            }
        });

        addExpenseDialog.setVisible(true);
    }

    // sub-window for adding an income
    private void showAddIncomeDialog() {
        JDialog addIncomeDialog = new JDialog(this, "Add Income", true);
        addIncomeDialog.setLayout(new GridLayout(3, 2, 10, 10));
        addIncomeDialog.setSize(300, 150);

        JLabel descLabel = new JLabel("Description:");
        JTextField descField = new JTextField();
        JLabel amountLabel = new JLabel("Amount:");
        JTextField amountField = new JTextField();
        JButton submitButton = new JButton("Add");

        addIncomeDialog.add(descLabel);
        addIncomeDialog.add(descField);
        addIncomeDialog.add(amountLabel);
        addIncomeDialog.add(amountField);
        addIncomeDialog.add(submitButton);

        submitButton.addActionListener(e -> {
            String description = descField.getText();
            double amount;
            try {
                amount = Double.parseDouble(amountField.getText());
                tracker.addIncome(description, amount);
                outputArea.setText(STR."Income added: \{description} - \{amount}");
                addIncomeDialog.dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(addIncomeDialog, "Please enter a valid amount.");
            }
        });

        addIncomeDialog.setVisible(true);
    }

    // sub-window to remove expenseID
    private void showRemoveExpenseDialog() {
        JDialog removeExpenseDialog = new JDialog(this, "Remove Expense", true);
        removeExpenseDialog.setLayout(new GridLayout(2, 2, 10, 10));
        removeExpenseDialog.setSize(300, 100);

        JLabel idLabel = new JLabel("Expense ID:");
        JTextField idField = new JTextField();
        JButton removeButton = new JButton("Remove");

        removeExpenseDialog.add(idLabel);
        removeExpenseDialog.add(idField);
        removeExpenseDialog.add(removeButton);

        removeButton.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText());
                if (tracker.getExpenseById(id) != null) {
                    tracker.removeExpense(id);
                    outputArea.setText(STR."Expense removed with ID: \{id}");
                    removeExpenseDialog.dispose();
                } else {
                    JOptionPane.showMessageDialog(removeExpenseDialog, "Expense ID not found.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(removeExpenseDialog, "Please enter a valid ID.");
            }
        });

        removeExpenseDialog.setVisible(true);
    }

    // sub-window for removing an income by ID
    private void showRemoveIncomeDialog() {
        JDialog removeIncomeDialog = new JDialog(this, "Remove Income", true);
        removeIncomeDialog.setLayout(new GridLayout(2, 2, 10, 10));
        removeIncomeDialog.setSize(300, 100);

        JLabel idLabel = new JLabel("Income ID:");
        JTextField idField = new JTextField();
        JButton removeButton = new JButton("Remove");

        removeIncomeDialog.add(idLabel);
        removeIncomeDialog.add(idField);
        removeIncomeDialog.add(removeButton);

        removeButton.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText());
                if (tracker.getIncomeById(id) != null) {
                    tracker.removeIncome(id);
                    outputArea.setText(STR."Income removed with ID: \{id}");
                    removeIncomeDialog.dispose();
                } else {
                    JOptionPane.showMessageDialog(removeIncomeDialog, "Income ID not found.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(removeIncomeDialog, "Please enter a valid ID.");
            }
        });

        removeIncomeDialog.setVisible(true);
    }

    // View All Expenses
    private class ViewAllExpensesListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            List<ExpenseItem> expenses = tracker.getAllExpenses();
            outputArea.setText("All Expenses:\n");
            if (expenses.isEmpty()) {
                outputArea.append("No expenses found.");
            } else {
                for (ExpenseItem expense : expenses) {
                    outputArea.append(expense.toString() + "\n");
                }
            }
        }
    }

    // View All Income
    private class ViewAllIncomeListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            List<IncomeItem> incomeItems = tracker.getAllIncome();
            outputArea.setText("All Income:\n");
            if (incomeItems.isEmpty()) {
                outputArea.append("No income found.");
            } else {
                for (IncomeItem incomeItem : incomeItems) {
                    outputArea.append(incomeItem.toString() + "\n");
                }
            }
        }
    }

    // View Total Expenses
    private class ViewTotalExpensesListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            outputArea.setText(STR."Total Expenses: \{tracker.getTotalExpenses()}");
        }
    }

    // View Total Income
    private class ViewTotalIncomeListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            outputArea.setText(STR."Total Income: \{tracker.getTotalIncome()}");
        }
    }

    public static void main(String[] args) {
        new ExpenseMenuGUI();
    }
}


