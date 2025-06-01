package bank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDate;

public class Deposit extends JFrame implements ActionListener {
    JTextField depositAmount;
    JButton depositButton, backButton;
    String pinNumber, cardNumber;

    Deposit(String pinNumber, String cardNumber) {
        this.pinNumber = pinNumber;
        this.cardNumber = cardNumber;
        JLabel text = new JLabel("Enter the amount you want to Deposit");
        text.setFont(new Font("System", Font.BOLD, 16));
        text.setForeground(Color.white);
        text.setBounds(190, 160, 500, 80);
        add(text);

        depositAmount = new JTextField();
        depositAmount.setFont(new Font("System", Font.BOLD, 16));
        depositAmount.setHorizontalAlignment(JTextField.CENTER);
        depositAmount.setForeground(Color.black);
        depositAmount.setBounds(200, 230, 270, 30);
        add(depositAmount);

        depositButton = new JButton("Deposit");
        depositButton.setBounds(160, 355, 120, 33);
        depositButton.addActionListener(this);
        add(depositButton);

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
        new Deposit("", "");
    }


    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == backButton) {
            setVisible(false);
            new Transactions(pinNumber, cardNumber).setVisible(true);
        } else if (ae.getSource() == depositButton) {
            String dAmount = depositAmount.getText().trim();
            LocalDate today = LocalDate.now();
            String cNumber = cardNumber;
            if (dAmount.isEmpty()) {
                JOptionPane.showMessageDialog(
                        this,
                        "Please enter an amount to deposit.",
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
                                "Deposit limit exceeded. Maximum allowed is ₹50,000.",
                                "Limit Exceeded",
                                JOptionPane.WARNING_MESSAGE
                        );
                    } else {
                        // Show success message
                        JOptionPane.showMessageDialog(
                                this,
                                "₹" + amount + " deposited successfully!",
                                "Deposit Success",
                                JOptionPane.INFORMATION_MESSAGE
                        );

                        // Show custom message based on amount
                        if (amount < 1000) {
                            JOptionPane.showMessageDialog(
                                    this,
                                    "That’s a small deposit. Every rupee counts!",
                                    "Note",
                                    JOptionPane.INFORMATION_MESSAGE
                            );
                        } else if (amount <= 10000) {
                            JOptionPane.showMessageDialog(
                                    this,
                                    "That's a good deposit. Keep saving!",
                                    "Note",
                                    JOptionPane.INFORMATION_MESSAGE
                            );
                        } else {
                            JOptionPane.showMessageDialog(
                                    this,
                                    "That's a high-value deposit. Great job!",
                                    "Note",
                                    JOptionPane.INFORMATION_MESSAGE
                            );
                        }
                        Conn c = new Conn();
                        String query = "insert into bank values('" + cNumber + "','" + today + "','deposit','" + dAmount + "' )";
                        try {
                            c.s.executeUpdate(query);
                            depositAmount.setText("");
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }


                        // TODO: Add actual deposit-to-database logic here


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
