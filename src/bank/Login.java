package bank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*; // <-- FIX: Required for ResultSet

public class Login extends JFrame implements ActionListener {
    JButton login, signup, clear;
    JTextField cardTextField;
    JPasswordField pinTextField;

    Login() {
        setLayout(null);

        // Logo
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/logo.jpg"));
        Image i2 = i1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel label = new JLabel(i3);
        label.setBounds(40, 10, 80, 80);
        add(label);

        getContentPane().setBackground(Color.WHITE);

        // Heading
        JLabel Head = new JLabel("Welcome to ATM");
        Head.setFont(new Font("Garamond", Font.BOLD, 38));
        Head.setBounds(200, 15, 500, 80);
        add(Head);

        // Labels
        JLabel cardNo = new JLabel("Card No. : ");
        cardNo.setFont(new Font("Segoe UI", Font.BOLD, 28));
        cardNo.setBounds(80, 150, 200, 80);
        add(cardNo);

        JLabel pin = new JLabel("PIN : ");
        pin.setFont(new Font("Segoe UI", Font.BOLD, 28));
        pin.setBounds(80, 220, 200, 80);
        add(pin);

        // Input Fields
        cardTextField = new JTextField();
        cardTextField.setBounds(220, 180, 400, 30);
        cardTextField.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        add(cardTextField);

        pinTextField = new JPasswordField();
        pinTextField.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        pinTextField.setBounds(220, 250, 400, 30);
        add(pinTextField);

        // Buttons
        login = new JButton("SIGN IN");
        login.setBounds(220, 300, 150, 50);
        login.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        login.setBackground(Color.black);
        login.setForeground(Color.white);
        login.addActionListener(this);
        add(login);

        clear = new JButton("CLEAR");
        clear.setBounds(470, 300, 150, 50);
        clear.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        clear.setBackground(Color.black);
        clear.setForeground(Color.white);
        clear.addActionListener(this);
        add(clear);

        signup = new JButton("SIGN UP");
        signup.setBounds(220, 380, 400, 50);
        signup.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        signup.setBackground(Color.black);
        signup.setForeground(Color.white);
        signup.addActionListener(this);
        add(signup);

        // Frame Settings
        setSize(800, 600);
        setVisible(true);
        setResizable(false);
        setTitle("Automated Teller Machine");
        setLocation(800, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new Login();
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == clear) {
            cardTextField.setText("");
            pinTextField.setText("");
        } else if (ae.getSource() == login) {
            String cardNumber = cardTextField.getText().trim();
            String pin = new String(pinTextField.getPassword()).trim();

            // --- VALIDATION LOGIC ---
            if (cardNumber.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Card Number is required");
                return;
            }

            if (!cardNumber.matches("\\d{12,16}")) {
                JOptionPane.showMessageDialog(null, "Enter a valid Card Number (12 to 16 digits)");
                return;
            }

            if (pin.isEmpty()) {
                JOptionPane.showMessageDialog(null, "PIN is required");
                return;
            }

            if (!pin.matches("\\d{4}")) {
                JOptionPane.showMessageDialog(null, "PIN must be 4 digits");
                return;
            }

            // --- AUTHENTICATION LOGIC ---
            try {
                Conn c = new Conn();
                String query = "SELECT * FROM login WHERE cardnumber = '" + cardNumber + "' AND pin = '" + pin + "'";
                ResultSet rs = c.s.executeQuery(query);
                if (rs.next()) {
                    setVisible(false);
                    // Assumes Transactions constructor accepts PIN
                    JOptionPane.showMessageDialog(null, "Login Success");
                } else {
                    JOptionPane.showMessageDialog(null, "Incorrect Card Number or PIN");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (ae.getSource() == signup) {
            setVisible(false);
            new SignupOne().setVisible(true);
        }
    }
}
