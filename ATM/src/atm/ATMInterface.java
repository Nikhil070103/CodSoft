package atm;
import javax.swing.*;
import java.awt.*;

public class ATMInterface extends JFrame {

    private BankAccount account;
    private JLabel messageLabel;

    public ATMInterface(BankAccount account) {
        this.account = account;

        setTitle("ATM Machine");
        setSize(600, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Heading
        JLabel titleLabel = new JLabel("Welcome to ATM", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        add(titleLabel, BorderLayout.NORTH);

        // Buttons
        JPanel buttonPanel = new JPanel(new GridLayout(2, 2, 15, 15));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        JButton withdrawButton = new JButton("Withdraw");
        JButton depositButton = new JButton("Deposit");
        JButton checkBalanceButton = new JButton("Check Balance");
        JButton exitButton = new JButton("Exit");

        Font btnFont = new Font("Tahoma", Font.PLAIN, 16);
        withdrawButton.setFont(btnFont);
        depositButton.setFont(btnFont);
        checkBalanceButton.setFont(btnFont);
        exitButton.setFont(btnFont);

        buttonPanel.add(withdrawButton);
        buttonPanel.add(depositButton);
        buttonPanel.add(checkBalanceButton);
        buttonPanel.add(exitButton);
        add(buttonPanel, BorderLayout.CENTER);

        // Message
        messageLabel = new JLabel("Please choose an option.", SwingConstants.CENTER);
        messageLabel.setFont(new Font("Verdana", Font.ITALIC, 14));
        add(messageLabel, BorderLayout.SOUTH);

        // Button Actions
        withdrawButton.addActionListener(e -> withdrawAction());
        depositButton.addActionListener(e -> depositAction());
        checkBalanceButton.addActionListener(e -> showBalance());
        exitButton.addActionListener(e -> System.exit(0));
    }

    private void withdrawAction() {
        String input = JOptionPane.showInputDialog(this, "Enter amount to withdraw:");
        if (input != null && !input.isEmpty()) {
            try {
                double amount = Double.parseDouble(input);
                if (account.withdraw(amount)) {
                    messageLabel.setText("Withdrawn ₹" + amount + ". Balance: ₹" + account.getBalance());
                } else {
                    messageLabel.setText("Invalid or insufficient amount.");
                }
            } catch (NumberFormatException e) {
                messageLabel.setText("Enter a valid number.");
            }
        }
    }

    private void depositAction() {
        String input = JOptionPane.showInputDialog(this, "Enter amount to deposit:");
        if (input != null && !input.isEmpty()) {
            try {
                double amount = Double.parseDouble(input);
                if (account.deposit(amount)) {
                    messageLabel.setText("Deposited ₹" + amount + ". Balance: ₹" + account.getBalance());
                } else {
                    messageLabel.setText("Enter a positive amount.");
                }
            } catch (NumberFormatException e) {
                messageLabel.setText("Enter a valid number.");
            }
        }
    }

    private void showBalance() {
        messageLabel.setText("Current Balance: ₹" + account.getBalance());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            BankAccount userAccount = new BankAccount(1000.0); // Initial balance
            new ATMInterface(userAccount).setVisible(true);
        });
    }
}
