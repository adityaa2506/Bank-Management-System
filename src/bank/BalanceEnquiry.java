package bank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class BalanceEnquiry extends JFrame implements ActionListener {
    JTextField depositAmount;
    JButton depositButton, backButton;
    String pinNumber, cardNumber;

    BalanceEnquiry(String pinNumber, String cardNumber) {
        this.pinNumber = pinNumber;
        this.cardNumber = cardNumber;
        JLabel text = new JLabel("Enter the amount you want to Deposit");
        text.setFont(new Font("System", Font.BOLD, 16));
        text.setForeground(Color.white);
        text.setBounds(190, 160, 500, 80);
        add(text);

        Conn c = new Conn();
        String cNumber = cardNumber;
        double totalDeposit = 0;
        double totalWithdraw = 0, totalFcash = 0;


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
        String bal = String.valueOf(balance);
        JLabel amount = new JLabel("Your current Balance is ₹"+bal);
        amount.setFont(new Font("System", Font.BOLD, 16));
        amount.setForeground(Color.white);
        amount.setBounds(190, 260, 500, 80);
        add(amount);


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
        new BalanceEnquiry("", "");
    }


    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == backButton) {
            setVisible(false);
            new Transactions(pinNumber, cardNumber).setVisible(true);
        }
    }
}

