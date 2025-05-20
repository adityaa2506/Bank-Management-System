package bank;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import com.toedter.calendar.JDateChooser;

public class SignupOne extends JFrame implements ActionListener {
    int random;
    JTextField nameTextField, fnameTextField, emailAddressTextField, addressTextField, cityTextField, stateTextField, pincodeTextField;
    JButton next;
    JRadioButton male;
    JRadioButton female;
    JRadioButton other;
    JRadioButton married;
    JRadioButton unmarried;
    JDateChooser dateChooser;

    SignupOne() {
        Random rand = new Random();
        getContentPane().setBackground(Color.white);
        random = 1000 + rand.nextInt(9000);

        JLabel formNo = new JLabel("APPLICATION FORM NO. " + random);
        formNo.setFont(new Font("Garamond", Font.BOLD, 34));
        formNo.setBounds(260, 15, 500, 80);
        add(formNo);

        JLabel personalDetails = new JLabel("Page 1: Personal Details");
        personalDetails.setFont(new Font("Garamond", Font.PLAIN, 28));
        personalDetails.setBounds(370, 60, 500, 80);
        add(personalDetails);

        JLabel name = new JLabel("Name : ");
        name.setFont(new Font("Garamond", Font.BOLD, 24));
        name.setBounds(80, 130, 500, 80);
        add(name);

        nameTextField = new JTextField();
        nameTextField.setBounds(280, 155, 600, 30);
        nameTextField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        add(nameTextField);

        JLabel fname = new JLabel("Fathers Name : ");
        fname.setFont(new Font("Garamond", Font.BOLD, 24));
        fname.setBounds(80, 170, 500, 80);
        add(fname);

        fnameTextField = new JTextField();
        fnameTextField.setBounds(280, 195, 600, 30);
        fnameTextField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        add(fnameTextField);

        JLabel dob = new JLabel("Date of Birth : ");
        dob.setFont(new Font("Garamond", Font.BOLD, 24));
        dob.setBounds(80, 210, 500, 80);
        add(dob);

        dateChooser = new JDateChooser();
        dateChooser.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        dateChooser.setBounds(280, 235, 600, 30);
        add(dateChooser);

        JLabel gender = new JLabel("Gender : ");
        gender.setFont(new Font("Garamond", Font.BOLD, 24));
        gender.setBounds(80, 250, 500, 80);
        add(gender);

        male = new JRadioButton("Male");
        male.setBackground(Color.white);
        male.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        male.setBounds(400, 275, 100, 30);
        add(male);

        female = new JRadioButton("Female");
        female.setBackground(Color.white);
        female.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        female.setBounds(600, 275, 600, 30);
        add(female);

        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(male);
        genderGroup.add(female);

        JLabel emailAddress = new JLabel("Email Address : ");
        emailAddress.setFont(new Font("Garamond", Font.BOLD, 24));
        emailAddress.setBounds(80, 290, 500, 80);
        add(emailAddress);

        emailAddressTextField = new JTextField();
        emailAddressTextField.setBounds(280, 315, 600, 30);
        emailAddressTextField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        add(emailAddressTextField);

        JLabel marital = new JLabel("Marital Status : ");
        marital.setFont(new Font("Garamond", Font.BOLD, 24));
        marital.setBounds(80, 330, 500, 80);
        add(marital);

        married = new JRadioButton("Married");
        married.setBackground(Color.white);
        married.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        married.setBounds(400, 355, 100, 30);
        add(married);

        unmarried = new JRadioButton("Unmarried");
        unmarried.setBackground(Color.white);
        unmarried.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        unmarried.setBounds(520, 355, 120, 30);
        add(unmarried);

        other = new JRadioButton("Others");
        other.setBackground(Color.white);
        other.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        other.setBounds(660, 355, 100, 30);
        add(other);

        ButtonGroup maritalGroup = new ButtonGroup();
        maritalGroup.add(married);
        maritalGroup.add(unmarried);
        maritalGroup.add(other);

        JLabel address = new JLabel("Address : ");
        address.setFont(new Font("Garamond", Font.BOLD, 24));
        address.setBounds(80, 370, 500, 80);
        add(address);

        addressTextField = new JTextField();
        addressTextField.setBounds(280, 395, 600, 30);
        addressTextField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        add(addressTextField);

        JLabel city = new JLabel("City : ");
        city.setFont(new Font("Garamond", Font.BOLD, 24));
        city.setBounds(80, 410, 500, 80);
        add(city);

        cityTextField = new JTextField();
        cityTextField.setBounds(280, 435, 600, 30);
        cityTextField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        add(cityTextField);

        JLabel state = new JLabel("State : ");
        state.setFont(new Font("Garamond", Font.BOLD, 24));
        state.setBounds(80, 450, 500, 80);
        add(state);

        stateTextField = new JTextField();
        stateTextField.setBounds(280, 475, 600, 30);
        stateTextField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        add(stateTextField);

        JLabel pincode = new JLabel("Pincode : ");
        pincode.setFont(new Font("Garamond", Font.BOLD, 24));
        pincode.setBounds(80, 490, 500, 80);
        add(pincode);

        pincodeTextField = new JTextField();
        pincodeTextField.setBounds(280, 515, 600, 30);
        pincodeTextField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        add(pincodeTextField);

        next = new JButton("Next");
        next.setBounds(630, 560, 250, 40);
        next.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        next.setBackground(Color.black);
        next.setForeground(Color.white);
        next.addActionListener(this);
        add(next);


        setLayout(null);
        setSize(1000, 700);
        setVisible(true);
        setTitle("Automated Teller Machine");
        setLocation(500, 50);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new SignupOne();
    }


    public void actionPerformed(ActionEvent ae) {
        String formno = "" + random;
        String name = nameTextField.getText();
        String fname = fnameTextField.getText();
        String dob = ((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
        String gender = null;
        if (male.isSelected()) {
            gender = "Male";
        } else if (female.isSelected()) {
            gender = "Female";
        }
        String email = emailAddressTextField.getText();
        String marital = null;
        if (married.isSelected()){
            marital = "Married";
        } else if (unmarried.isSelected()) {
            marital = "Unmarried";
        } else if (other.isSelected()) {
            marital = "Other";
        }
        String address = addressTextField.getText();
        String city = cityTextField.getText();
        String state = stateTextField.getText();
        String pincode = pincodeTextField.getText();

        try{
            if (name.equals("") || fname.equals("") || dob.equals("") || email.equals("") ){
                JOptionPane.showMessageDialog(null,"All fields are Required");
            }
            else {
               Conn c = new Conn();
               String query = "insert into signup values('"+formno+"','"+name+"','"+fname+"','"+dob+"','"+gender+"','"+email+"','"+marital+"','"+address+"','"+city+"','"+pincode+"','"+state+"')";
               c.s.executeUpdate(query);
               setVisible(false);
               new SignupTwo(formno).setVisible(true);
            }
        }catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
