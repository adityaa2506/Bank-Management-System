package bank;

import javax.swing.*;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Transactions extends JFrame implements ActionListener {
    JButton deposit, withdrawl, fcash, mstatement, pinChange, balanceEnquiry, exit;
    String pinNumber;

    Transactions(String pin, String card) {
        this.pinNumber = pin;
        JLabel text = new JLabel("Please select your Transaction");
        text.setFont(new Font("System", Font.PLAIN, 20));
        text.setForeground(Color.white);
        text.setBounds(200, 160, 500, 80);
        add(text);

        deposit = new JButton("Deposit");
        deposit.setBounds(160, 355, 120, 33);
        deposit.addActionListener(this);
        add(deposit);

        withdrawl = new JButton("Cash Withdral");
        withdrawl.setBounds(390, 355, 120, 33);
        withdrawl.addActionListener(this);
        add(withdrawl);

        fcash = new JButton("Fast cash");
        fcash.setBounds(160, 400, 120, 33);
        fcash.addActionListener(this);
        add(fcash);

        mstatement = new JButton("Mini Statement");
        mstatement.setBounds(390, 400, 120, 33);
        mstatement.addActionListener(this);
        add(mstatement);

        pinChange = new JButton("Pin Change");
        pinChange.setBounds(160, 445, 120, 33);
        pinChange.addActionListener(this);
        add(pinChange);

        balanceEnquiry = new JButton("Balance Enquiry");
        balanceEnquiry.setBounds(390, 445, 120, 33);
        balanceEnquiry.addActionListener(this);
        add(balanceEnquiry);

        exit = new JButton("Exit");
        exit.setBounds(390, 490, 120, 33);
        exit.addActionListener(this);
        add(exit);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel label = new JLabel(i3);
        label.setBounds(0, 0, 900, 900);
        add(label);

//        JLabel text = new JLabel("Please select your Transaction");
//        text.setFont(new Font("Garamond", Font.PLAIN, 28));
//        text.setBounds(370, 30, 500, 80);
//        add(text);


        setSize(900, 900);
        setResizable(false);
        setTitle("Automated Teller Machine");
        setLocation(450, 50);
        setUndecorated(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }


    public static void main(String[] args) {
        new Transactions("", "");
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == exit) {
            System.exit(0);
        }
    }
}
