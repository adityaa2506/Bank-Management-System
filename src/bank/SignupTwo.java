package bank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignupTwo extends JFrame implements ActionListener {
    JTextField pan, adhaar;
    JButton next;
    JComboBox religion, category, income, educationalQualification, occupation;
    JRadioButton sYes, sNo, eNo, eYes;
    String formno;

    SignupTwo(String formno) {
        this.formno = formno;
        getContentPane().setBackground(Color.white);
        setLayout(null);

        JLabel additionalDetails = new JLabel("Page 2: Additional Details");
        additionalDetails.setFont(new Font("Garamond", Font.PLAIN, 28));
        additionalDetails.setBounds(370, 30, 500, 80);
        add(additionalDetails);

        JLabel name = new JLabel("Religion : ");
        name.setFont(new Font("Garamond", Font.BOLD, 24));
        name.setBounds(80, 130, 500, 80);
        add(name);

        String valReligion[] = {"Hindu", "Muslim", "Sikh", "Christian", "Other"};
        religion = new JComboBox(valReligion);
        religion.setBounds(280, 155, 600, 30);
        religion.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        religion.setBackground(Color.WHITE);
        add(religion);

        JLabel fname = new JLabel("Category : ");
        fname.setFont(new Font("Garamond", Font.BOLD, 24));
        fname.setBounds(80, 170, 500, 80);
        add(fname);

        String valCategory[] = {"General", "ST", "SC", "OBC", "Other"};
        category = new JComboBox(valCategory);
        category.setBounds(280, 195, 600, 30);
        category.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        category.setBackground(Color.WHITE);
        add(category);

        JLabel dob = new JLabel("Income : ");
        dob.setFont(new Font("Garamond", Font.BOLD, 24));
        dob.setBounds(80, 210, 500, 80);
        add(dob);

        String valIncome[] = {"Null", "< 1,50,000", "< 2,50,000", "< 5,00,000", "Upto 10,00,000"};
        income = new JComboBox(valIncome);
        income.setBounds(280, 235, 600, 30);
        income.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        income.setBackground(Color.WHITE);
        add(income);

        JLabel educationLabel = new JLabel("Educational : ");
        educationLabel.setFont(new Font("Garamond", Font.BOLD, 19));
        educationLabel.setBounds(80, 250, 500, 80);
        add(educationLabel);

        JLabel qualificationLabel = new JLabel("Qualification ");
        qualificationLabel.setFont(new Font("Garamond", Font.BOLD, 19));
        qualificationLabel.setBounds(75, 270, 500, 80);
        add(qualificationLabel);

        String valEducation[] = {"Non Graduation", "Graduate", "Post Graduate", "Doctrate", "Others"};
        educationalQualification = new JComboBox(valEducation);
        educationalQualification.setBounds(280, 285, 600, 30);
        educationalQualification.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        educationalQualification.setBackground(Color.WHITE);
        add(educationalQualification);

        JLabel occupationLabel = new JLabel("Occupation : ");
        occupationLabel.setFont(new Font("Garamond", Font.BOLD, 24));
        occupationLabel.setBounds(80, 305, 500, 80);
        add(occupationLabel);

        String valOccupation[] = {"Salaried", "Self-Employed", "Business", "Retired", "Student", "Others"};
        occupation = new JComboBox(valOccupation);
        occupation.setBounds(280, 330, 600, 30);
        occupation.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        occupation.setBackground(Color.WHITE);
        add(occupation);

        JLabel panLabel = new JLabel("PAN no. : ");
        panLabel.setFont(new Font("Garamond", Font.BOLD, 24));
        panLabel.setBounds(80, 350, 500, 80);
        add(panLabel);

        pan = new JTextField();
        pan.setBounds(280, 375, 600, 30);
        pan.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        add(pan);

        JLabel adhaarLabel = new JLabel("Adhaar No. : ");
        adhaarLabel.setFont(new Font("Garamond", Font.BOLD, 24));
        adhaarLabel.setBounds(80, 390, 500, 80);
        add(adhaarLabel);

        adhaar = new JTextField();
        adhaar.setBounds(280, 415, 600, 30);
        adhaar.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        add(adhaar);

        JLabel seniorCitizenLabel = new JLabel("Senior Citizen :  ");
        seniorCitizenLabel.setFont(new Font("Garamond", Font.BOLD, 24));
        seniorCitizenLabel.setBounds(80, 430, 500, 80);
        add(seniorCitizenLabel);

        sYes = new JRadioButton("Yes");
        sYes.setBounds(280, 455, 100, 30);
        sYes.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        sYes.setBackground(Color.white);
        add(sYes);

        sNo = new JRadioButton("No");
        sNo.setBounds(400, 455, 100, 30);
        sNo.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        sNo.setBackground(Color.white);
        add(sNo);

        ButtonGroup seniorGroup = new ButtonGroup();
        seniorGroup.add(sYes);
        seniorGroup.add(sNo);

        JLabel accountLabel = new JLabel("Existing Account : ");
        accountLabel.setFont(new Font("Garamond", Font.BOLD, 24));
        accountLabel.setBounds(80, 465, 500, 80);
        add(accountLabel);

        eYes = new JRadioButton("Yes");
        eYes.setBounds(280, 490, 100, 30);
        eYes.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        eYes.setBackground(Color.white);
        add(eYes);

        eNo = new JRadioButton("No");
        eNo.setBounds(400, 490, 100, 30);
        eNo.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        eNo.setBackground(Color.white);
        add(eNo);

        ButtonGroup existingAccountGroup = new ButtonGroup();
        existingAccountGroup.add(eYes);
        existingAccountGroup.add(eNo);

        next = new JButton("Next");
        next.setBounds(630, 560, 250, 40);
        next.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        next.setBackground(Color.black);
        next.setForeground(Color.white);
        next.addActionListener(this); // ✅ FIXED: next button action listener
        add(next);

        setSize(1000, 700);
        setVisible(true);
        setResizable(false);
        setTitle("Automated Teller Machine");
        setLocation(500, 50);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new SignupTwo("");
    }

    public void actionPerformed(ActionEvent ae) {
        String sreligion = (String) religion.getSelectedItem();
        String scategory = (String) category.getSelectedItem();
        String sincome = (String) income.getSelectedItem();
        String seducation = (String) educationalQualification.getSelectedItem();
        String soccupation = (String) occupation.getSelectedItem();
        String span = pan.getText().trim();
        String sadhaar = adhaar.getText().trim();
        String seniorcitizen = sYes.isSelected() ? "Yes" : (sNo.isSelected() ? "No" : "");
        String existingaccount = eYes.isSelected() ? "Yes" : (eNo.isSelected() ? "No" : "");

        // --- VALIDATIONS START HERE ---

        if (span.isEmpty()) {
            JOptionPane.showMessageDialog(null, "PAN number is required");
            return;
        }

        if (!span.matches("[A-Z]{5}[0-9]{4}[A-Z]{1}")) {
            JOptionPane.showMessageDialog(null, "Enter a valid PAN number (e.g., ABCDE1234F)");
            return;
        }

        if (sadhaar.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Aadhaar number is required");
            return;
        }

        if (!sadhaar.matches("\\d{12}")) {
            JOptionPane.showMessageDialog(null, "Enter a valid 12-digit Aadhaar number");
            return;
        }

        if (seniorcitizen.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please select Senior Citizen option");
            return;
        }

        if (existingaccount.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please select Existing Account option");
            return;
        }

        // --- DATABASE LOGIC ---
        try {
            Conn c = new Conn();
            String query = "insert into signuptwo values('" + formno + "','" + sreligion + "','" + scategory + "','" + sincome + "','" + seducation + "','" + soccupation + "','" + span + "','" + sadhaar + "','" + seniorcitizen + "','" + existingaccount + "')";
            c.s.executeUpdate(query);
            setVisible(false);
            new SignupThree(formno).setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
