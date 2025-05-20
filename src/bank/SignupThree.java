package bank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class SignupThree extends JFrame implements ActionListener {

    JRadioButton r1, r2, r3, r4;
    JCheckBox c1, c2, c3, c4, c5, c6, c7;
    JButton submit, cancel;
    String formno;

    SignupThree(String formno) {
        this.formno = formno;
        setLayout(null);

        JLabel accountDetails = new JLabel("Page 3: Account Details");
        accountDetails.setFont(new Font("Garamond", Font.BOLD, 35));
        accountDetails.setBounds(280, 5, 500, 80);
        add(accountDetails);

        JLabel accountType = new JLabel("Account Type : ");
        accountType.setFont(new Font("Garamond", Font.BOLD, 26));
        accountType.setBounds(80, 80, 200, 80);
        add(accountType);

        r1 = new JRadioButton("Savings Account");
        r1.setBounds(180, 150, 200, 30);
        r1.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        r1.setBackground(Color.white);
        add(r1);

        r2 = new JRadioButton("Fixed Deposit Account");
        r2.setBounds(400, 150, 400, 30);
        r2.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        r2.setBackground(Color.white);
        add(r2);

        r3 = new JRadioButton("Current Account");
        r3.setBounds(180, 190, 200, 30);
        r3.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        r3.setBackground(Color.white);
        add(r3);

        r4 = new JRadioButton("Recurring Deposit Account");
        r4.setBounds(400, 190, 400, 30);
        r4.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        r4.setBackground(Color.white);
        add(r4);

        ButtonGroup groupAccount = new ButtonGroup();
        groupAccount.add(r1);
        groupAccount.add(r2);
        groupAccount.add(r3);
        groupAccount.add(r4);

        JLabel card = new JLabel("Card Number : ");
        card.setFont(new Font("Garamond", Font.BOLD, 26));
        card.setBounds(80, 210, 200, 80);
        add(card);

        JLabel cardDetails = new JLabel("Your 16 Digit Card Number");
        cardDetails.setFont(new Font("Garamond", Font.BOLD, 12));
        cardDetails.setBounds(80, 230, 200, 80);
        add(cardDetails);

        JLabel number = new JLabel("XXXX-XXXX-XXXX-");
        number.setFont(new Font("Garamond", Font.PLAIN, 24));
        number.setBounds(280, 213, 500, 80);
        add(number);

        JLabel lastdigit = new JLabel("4141");
        lastdigit.setFont(new Font("Garamond", Font.BOLD, 24));
        lastdigit.setBounds(510, 213, 500, 80);
        add(lastdigit);

        JLabel pin = new JLabel("PIN Number : ");
        pin.setFont(new Font("Garamond", Font.BOLD, 26));
        pin.setBounds(80, 270, 200, 80);
        add(pin);

        JLabel pinDetaild = new JLabel("Your secure 4 digit PIN");
        pinDetaild.setFont(new Font("Garamond", Font.BOLD, 12));
        pinDetaild.setBounds(80, 290, 200, 80);
        add(pinDetaild);

        JLabel pnumber = new JLabel("XXXX");
        pnumber.setFont(new Font("Garamond", Font.PLAIN, 24));
        pnumber.setBounds(280, 273, 500, 80);
        add(pnumber);

        JLabel servicesRequired = new JLabel("Services Required : ");
        servicesRequired.setFont(new Font("Garamond", Font.BOLD, 26));
        servicesRequired.setBounds(80, 330, 300, 80);
        add(servicesRequired);

        c1 = new JCheckBox("ATM Card");
        c1.setBackground(Color.WHITE);
        c1.setFont(new Font("Garamond", Font.PLAIN, 20));
        c1.setBounds(120, 390, 140, 30);
        add(c1);

        c2 = new JCheckBox("Internet Banking");
        c2.setBackground(Color.WHITE);
        c2.setFont(new Font("Garamond", Font.PLAIN, 20));
        c2.setBounds(350, 390, 200, 30);
        add(c2);

        c3 = new JCheckBox("Mobile Banking");
        c3.setBackground(Color.WHITE);
        c3.setFont(new Font("Garamond", Font.PLAIN, 20));
        c3.setBounds(120, 420, 200, 30);
        add(c3);

        c4 = new JCheckBox("E-mail & SMS Alerts");
        c4.setBackground(Color.WHITE);
        c4.setFont(new Font("Garamond", Font.PLAIN, 20));
        c4.setBounds(350, 420, 250, 30);
        add(c4);

        c5 = new JCheckBox("Cheque Book");
        c5.setBackground(Color.WHITE);
        c5.setFont(new Font("Garamond", Font.PLAIN, 20));
        c5.setBounds(120, 450, 200, 30);
        add(c5);

        c6 = new JCheckBox("E-Statement");
        c6.setBackground(Color.WHITE);
        c6.setFont(new Font("Garamond", Font.PLAIN, 20));
        c6.setBounds(350, 450, 250, 30);
        add(c6);

        c7 = new JCheckBox("I Hereby declare that the provided details are correct to the best of my knowledge ");
        c7.setBackground(Color.WHITE);
        c7.setFont(new Font("Garamond", Font.BOLD, 16));
        c7.setBounds(80, 500, 600, 30);
        add(c7);

        submit = new JButton("Submit");
        submit.setBounds(210, 560, 150, 40);
        submit.setFont(new Font("Segoe UI", Font.BOLD, 20));
        submit.setBackground(Color.black);
        submit.setForeground(Color.white);
        submit.addActionListener(this);
        add(submit);

        cancel = new JButton("Cancel");
        cancel.setBounds(450, 560, 150, 40);
        cancel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        cancel.setBackground(Color.black);
        cancel.setForeground(Color.white);
        cancel.addActionListener(this);
        add(cancel);

        setSize(900, 700);
        getContentPane().setBackground(Color.WHITE);
        setResizable(false);
        setVisible(true);
        setTitle("Automated Teller Machine");
        setLocation(500, 50);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == submit) {
            String accountType = null;
            if (r1.isSelected()) {
                accountType = "Savings Account";
            } else if (r2.isSelected()) {
                accountType = "Fixed Deposit Account";
            } else if (r3.isSelected()) {
                accountType = "Current Account";
            } else if (r4.isSelected()) {
                accountType = "Recurring Deposit Account";
            }

            if (!c7.isSelected()) {
                JOptionPane.showMessageDialog(null, "Please confirm the declaration to proceed.");
                return;
            }

            Random random = new Random();
            String cardNumber = "" + Math.abs((random.nextLong() % 90000000L) + 5040936000000000L);
            String pinNumber = "" + Math.abs((random.nextLong() % 9000L) + 1000L);

            String facility = "";
            if (c1.isSelected()) facility += " ATM Card";
            if (c2.isSelected()) facility += " Internet Banking";
            if (c3.isSelected()) facility += " Mobile Banking";
            if (c4.isSelected()) facility += " EMAIL Alerts";
            if (c5.isSelected()) facility += " Cheque Book";
            if (c6.isSelected()) facility += " E-Statement";

            try {
                if (accountType == null) {
                    JOptionPane.showMessageDialog(null, "Account type is required");
                } else {
                    Conn c = new Conn();
                    String q1 = "insert into signupthree values('" + formno + "','" + accountType + "','" + cardNumber + "','" + pinNumber + "','" + facility + "')";
                    String q2 = "insert into login values('" + formno + "','" + cardNumber + "','" + pinNumber + "')";
                    c.s.executeUpdate(q1);
                    c.s.executeUpdate(q2);
                    JOptionPane.showMessageDialog(null, "Card Number: " + cardNumber + "\nPin: " + pinNumber);
                }
            } catch (Exception e) {
                System.out.println(e);
            }

        } else if (ae.getSource() == cancel) {
            // You can add cancel functionality here if needed
        }
    }


    public static void main(String[] args) {
        new SignupThree("");
    }


}
