package bank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDate;

public class Withdraw extends JFrame implements ActionListener {
    JTextField withdrawAmount;
    JButton withdrawButton, backButton;
    String pinNumber, cardNumber;

    Withdraw(String pinNumber, String cardNumber) {
        this.pinNumber = pinNumber;
        this.cardNumber = cardNumber;
        JLabel text = new JLabel("Enter the amount you want to Withdraw");
        text.setFont(new Font("System", Font.BOLD, 16));
        text.setForeground(Color.white);
        text.setBounds(190, 160, 500, 80);
        add(text);

        withdrawAmount = new JTextField();
        withdrawAmount.setFont(new Font("System", Font.BOLD, 16));
        withdrawAmount.setHorizontalAlignment(JTextField.CENTER);
        withdrawAmount.setForeground(Color.black);
        withdrawAmount.setBounds(200, 230, 270, 30);
        add(withdrawAmount);

        withdrawButton = new JButton("Withdraw");
        withdrawButton.setBounds(160, 355, 120, 33);
        withdrawButton.addActionListener(this);
        add(withdrawButton);

        backButton = new JButton("Back");
        backButton.setBounds(390, 355, 120, 33);
        backButton.addActionListener(this);
        add(backButton);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel label = new JLabel(i3);
        label.setBounds(0, 0, 900, 900);
        add(label);

        setSize(900, 900);
        setResizable(false);
        setTitle("Automated Teller Machine");
        setLocation(450, 50);
        setUndecorated(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Withdraw("", "");
    }


    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == backButton) {
            setVisible(false);
            new Transactions(pinNumber, cardNumber).setVisible(true);
        } else if (ae.getSource() == withdrawButton) {
            String dAmount = withdrawAmount.getText().trim();
            LocalDate today = LocalDate.now();
            String cNumber = cardNumber;

            if (dAmount.isEmpty()) {
                JOptionPane.showMessageDialog(
                        this,
                        "Please enter an amount to withdraw.",
                        "Input Error",
                        JOptionPane.ERROR_MESSAGE
                );
            } else {
                try {
                    double amount = Double.parseDouble(dAmount);

                    if (amount <= 0) {
                        JOptionPane.showMessageDialog(
                                this,
                                "Please enter an amount greater than ₹0.",
                                "Invalid Amount",
                                JOptionPane.ERROR_MESSAGE
                        );
                    } else if (amount > 50000) {
                        JOptionPane.showMessageDialog(
                                this,
                                "Withdraw limit exceeded. Maximum allowed is ₹50,000.",
                                "Limit Exceeded",
                                JOptionPane.WARNING_MESSAGE
                        );
                    } else {
                        Conn c = new Conn();

                        double totalDeposit = 0;
                        double totalWithdraw = 0 , totalFcash = 0;


                        // Fetch deposit total
                        try {
                            var rs = c.s.executeQuery("SELECT SUM(amount) FROM bank WHERE card_number = '" + cNumber + "' AND type = 'deposit'");
                            if (rs.next()) {
                                totalDeposit = rs.getDouble(1);
                            }
                        } catch (SQLException e) {
                            JOptionPane.showMessageDialog(this, "Error fetching deposit data.");
                        }

                        // Fetch withdraw total
                        try {
                            var rs = c.s.executeQuery("SELECT SUM(amount) FROM bank WHERE card_number = '" + cNumber + "' AND type = 'withdraw'");
                            if (rs.next()) {
                                totalWithdraw = rs.getDouble(1);
                            }
                        } catch (SQLException e) {
                            JOptionPane.showMessageDialog(this, "Error fetching withdrawal data.");
                        }

                        try {
                            var rs = c.s.executeQuery("SELECT SUM(amount) FROM bank WHERE card_number = '" + cNumber + "' AND type = 'fast cash'");
                            if (rs.next()) totalFcash = rs.getDouble(1);
                        } catch (SQLException e) {
                            JOptionPane.showMessageDialog(this, "Error fetching withdrawal data.");
                        }

                        double balance = totalDeposit - (totalWithdraw + totalFcash);

                        if (amount > balance) {
                            JOptionPane.showMessageDialog(
                                    this,
                                    "Insufficient balance. Available balance is ₹" + balance,
                                    "Transaction Denied",
                                    JOptionPane.ERROR_MESSAGE
                            );
                            return;
                        }

                        // All checks passed – perform withdrawal
                        JOptionPane.showMessageDialog(
                                this,
                                "₹" + amount + " withdrawn successfully!",
                                "Withdraw Success",
                                JOptionPane.INFORMATION_MESSAGE
                        );

                        if (amount < 1000) {
                            JOptionPane.showMessageDialog(this, "That’s a small withdrawal. Use wisely!", "Note", JOptionPane.INFORMATION_MESSAGE);
                        } else if (amount <= 10000) {
                            JOptionPane.showMessageDialog(this, "That's a reasonable withdrawal. Stay budget-conscious!", "Note", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(this, "That's a large withdrawal. Spend smartly!", "Note", JOptionPane.INFORMATION_MESSAGE);
                        }

                        String query = "INSERT INTO bank VALUES('" + cNumber + "','" + today + "','withdraw','" + dAmount + "' )";
                        try {
                            c.s.executeUpdate(query);
                            withdrawAmount.setText("");
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(
                            this,
                            "Please enter a valid numeric amount.",
                            "Invalid Input",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        }
    }



}
