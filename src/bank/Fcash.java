package bank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.time.LocalDate;

public class Fcash extends JFrame implements ActionListener {
    JButton backButton;
    JButton[] cashButtons;
    String pinNumber, cardNumber;

    final int[] amounts = {200, 500, 1000, 2000, 5000};

    Fcash(String pinNumber, String cardNumber) {
        this.pinNumber = pinNumber;
        this.cardNumber = cardNumber;

        JLabel text = new JLabel("Select Amount for Fast Withdraw");
        text.setFont(new Font("System", Font.BOLD, 18));
        text.setForeground(Color.white);
        text.setBounds(190, 160, 500, 80);
        add(text);

        // Create buttons for fast cash
        cashButtons = new JButton[amounts.length];
        int x = 160, y = 355;
        for (int i = 0; i < amounts.length; i++) {
            cashButtons[i] = new JButton("₹" + amounts[i]);
            cashButtons[i].setBounds(x, y, 120, 33);
            cashButtons[i].addActionListener(this);
            add(cashButtons[i]);
            x += 230;
            if (x > 390) {
                x = 160;
                y += 45;
            }
        }

        // Back Button
        backButton = new JButton("Back");
        backButton.setBounds(390, y + 45, 120, 35);
        backButton.addActionListener(this);
        add(backButton);

        // Background
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel label = new JLabel(i3);
        label.setBounds(0, 0, 900, 900);
        add(label);

        setSize(900, 900);
        setResizable(false);
        setTitle("Fast Cash - ATM");
        setLocation(450, 50);
        setUndecorated(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Fcash("", "");
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == backButton) {
            setVisible(false);
            new Transactions(pinNumber, cardNumber).setVisible(true);
        } else {
            for (int i = 0; i < cashButtons.length; i++) {
                if (ae.getSource() == cashButtons[i]) {
                    handleWithdrawal(amounts[i]);
                    break;
                }
            }
        }
    }

    private void handleWithdrawal(double amount) {
        LocalDate today = LocalDate.now();
        String cNumber = cardNumber;

        if (amount > 50000) {
            JOptionPane.showMessageDialog(this, "Fast Cash limit exceeded. Max ₹50,000", "Limit Exceeded", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Conn c = new Conn();
        double totalDeposit = 0, totalWithdraw = 0 , totalFcash = 0;

        try {
            var rs = c.s.executeQuery("SELECT SUM(amount) FROM bank WHERE card_number = '" + cNumber + "' AND type = 'deposit'");
            if (rs.next()) totalDeposit = rs.getDouble(1);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error fetching deposit data.");
        }

        try {
            var rs = c.s.executeQuery("SELECT SUM(amount) FROM bank WHERE card_number = '" + cNumber + "' AND type = 'withdraw'");
            if (rs.next()) totalWithdraw = rs.getDouble(1);
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
            JOptionPane.showMessageDialog(this, "Insufficient balance. Available ₹" + balance, "Transaction Denied", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Success message
        JOptionPane.showMessageDialog(this, "₹" + amount + " withdrawn successfully!", "Withdraw Success", JOptionPane.INFORMATION_MESSAGE);

        if (amount < 1000) {
            JOptionPane.showMessageDialog(this, "That’s a small withdrawal. Use wisely!", "Note", JOptionPane.INFORMATION_MESSAGE);
        } else if (amount <= 10000) {
            JOptionPane.showMessageDialog(this, "That's a reasonable withdrawal. Stay budget-conscious!", "Note", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "That's a large withdrawal. Spend smartly!", "Note", JOptionPane.INFORMATION_MESSAGE);
        }

        String query = "INSERT INTO bank VALUES('" + cNumber + "','" + today + "','fast cash','" + amount + "' )";
        try {
            c.s.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
