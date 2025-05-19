package bank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame implements ActionListener {
    JButton login , signup, clear;
    JTextField cardTextField ;
    JPasswordField pinTextField;
    Login() {
        setLayout(null);
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/logo.jpg"));
        Image i2 = i1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel label = new JLabel(i3);
        label.setBounds(40, 10, 80, 80);
        add(label);

        getContentPane().setBackground(Color.WHITE);

        JLabel Head = new JLabel("Welcome to ATM");
        Head.setFont(new Font("Garamond", Font.BOLD, 38));
        Head.setBounds(200,15,500,80);
        add(Head);

        JLabel cardNo = new JLabel("Card No. : ");
        cardNo.setFont(new Font("Segoe UI", Font.BOLD, 28));
        cardNo.setBounds(80,150,200,80);
        add(cardNo);

        JLabel pin = new JLabel("PIN : ");
        pin.setFont(new Font("Segoe UI", Font.BOLD, 28));
        pin.setBounds(80,220,200,80);
        add(pin);

        cardTextField = new JTextField();
        cardTextField.setBounds(220,180,400,30);
        cardTextField.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        add(cardTextField);

        pinTextField = new JPasswordField();
        pinTextField.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        pinTextField.setBounds(220,250,400,30);
        add(pinTextField);

        login = new JButton("SIGN IN");
        login.setBounds(220,300,150,50);
        login.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        login.setBackground(Color.black);
        login.setForeground(Color.white);
        login.addActionListener(this);
        add(login);

        clear = new JButton("CLEAR");
        clear.setBounds(470,300,150,50);
        clear.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        clear.setBackground(Color.black);
        clear.setForeground(Color.white);
        clear.addActionListener(this);
        add(clear);

        signup = new JButton("SIGN UP");
        signup.setBounds(220,380,400,50);
        signup.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        signup.setBackground(Color.black);
        signup.setForeground(Color.white);
        signup.addActionListener(this);
        add(signup);

        setSize(800, 600);
        setVisible(true);
        setTitle("Automated Teller Machine");
        setLocation(800, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new Login();
    }


    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == clear){
            cardTextField.setText("");
            pinTextField.setText("");
        } else if (ae.getSource() == login) {

        } else if(ae.getSource() == signup){
                setVisible(false);
                new SignupOne().setVisible(true);
            }
    }
}
