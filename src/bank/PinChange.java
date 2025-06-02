package bank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class PinChange extends JFrame implements ActionListener {

    JPasswordField oldPinField, newPinField, confirmPinField;
    JButton changeButton, backButton;
    String cardNumber,pinNumber;

    PinChange(String pinNumber , String cardNumber) {
        this.cardNumber = cardNumber;
        this.pinNumber = pinNumber;
        setLayout(null);
        setTitle("Change PIN");

        JLabel heading = new JLabel("Change Your ATM PIN");
        heading.setFont(new Font("System", Font.BOLD, 20));
        heading.setBounds(250, 180, 300, 30);
        heading.setForeground(Color.white);
        add(heading);

        JLabel oldPinLabel = new JLabel("Enter Old PIN:");
        oldPinLabel.setForeground(Color.white);
        oldPinLabel.setBounds(200, 240, 150, 30);
        add(oldPinLabel);

        oldPinField = new JPasswordField();
        oldPinField.setBounds(350, 240, 150, 30);
        add(oldPinField);

        JLabel newPinLabel = new JLabel("Enter New PIN:");
        newPinLabel.setForeground(Color.white);
        newPinLabel.setBounds(200, 280, 150, 30);
        add(newPinLabel);

        newPinField = new JPasswordField();
        newPinField.setBounds(350, 280, 150, 30);
        add(newPinField);

        JLabel confirmPinLabel = new JLabel("Confirm New PIN:");
        confirmPinLabel.setForeground(Color.white);
        confirmPinLabel.setBounds(200, 320, 150, 30);
        add(confirmPinLabel);

        confirmPinField = new JPasswordField();
        confirmPinField.setBounds(350, 320, 150, 30);
        add(confirmPinField);

        changeButton = new JButton("Change");
        changeButton.setBounds(160, 400, 120, 30);
        changeButton.addActionListener(this);
        add(changeButton);

        backButton = new JButton("Back");
        backButton.setBounds(390, 400, 120, 30);
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
        setLocation(400, 50);
        setUndecorated(true);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == backButton) {
            setVisible(false);
            new Transactions("", cardNumber).setVisible(true);
        } else if (ae.getSource() == changeButton) {
            String oldPin = new String(oldPinField.getPassword());
            String newPin = new String(newPinField.getPassword());
            String confirmPin = new String(confirmPinField.getPassword());

            if (oldPin.equals("") || newPin.equals("") || confirmPin.equals("")) {
                JOptionPane.showMessageDialog(this, "All fields are required.");
                return;
            }

            if (!newPin.equals(confirmPin)) {
                JOptionPane.showMessageDialog(this, "New PIN and Confirm PIN do not match.");
                return;
            }

            if (!newPin.matches("\\d{4}")) {
                JOptionPane.showMessageDialog(this, "PIN must be 4 digits only.");
                return;
            }

            try {
                Conn c = new Conn();
                ResultSet rs = c.s.executeQuery("SELECT pin FROM login WHERE cardnumber = '" + cardNumber + "'");

                if (rs.next()) {
                    String currentPin = rs.getString("pin");
                    if (!currentPin.equals(oldPin)) {
                        JOptionPane.showMessageDialog(this, "Old PIN is incorrect.");
                        return;
                    }

                    // Update PIN
                    c.s.executeUpdate("UPDATE login SET pin = '" + newPin + "' WHERE cardnumber = '" + cardNumber + "'");
                    JOptionPane.showMessageDialog(this, "PIN changed successfully!");
                    setVisible(false);
                    new Transactions(newPin, cardNumber).setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(this, "Card not found.");
                }

            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error updating PIN.");
            }
        }
    }

    public static void main(String[] args) {
        new PinChange("","");
    }
}
