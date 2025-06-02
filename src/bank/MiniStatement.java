package bank;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class MiniStatement extends JFrame {


    String cardNumber , pinNumber;
    JTextArea miniStatementArea;
    JButton backButton;

    MiniStatement(String pinNumber , String cardNumber) {
        this.cardNumber = cardNumber;
        this.pinNumber = pinNumber;
        setTitle("Mini Statement");
        setLayout(new BorderLayout());

        JLabel heading = new JLabel("Mini Statement for Card: " + cardNumber);
        heading.setFont(new Font("System", Font.BOLD, 18));
        heading.setHorizontalAlignment(JLabel.CENTER);
        add(heading, BorderLayout.NORTH);

        miniStatementArea = new JTextArea();
        miniStatementArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        miniStatementArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(miniStatementArea);
        add(scrollPane, BorderLayout.CENTER);

        backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            setVisible(false);
            new Transactions(pinNumber,cardNumber).setVisible(true);
        });
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(backButton);
        add(buttonPanel, BorderLayout.SOUTH);

        fetchMiniStatement();

        setSize(600, 400);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void fetchMiniStatement() {
        try {
            Conn c = new Conn();

            // Query last 10 transactions for this card, ordered by date desc
            String query = "SELECT date, type, amount FROM bank WHERE card_number = '" + cardNumber + "' ORDER BY date DESC LIMIT 30";

            ResultSet rs = c.s.executeQuery(query);

            StringBuilder sb = new StringBuilder();
            sb.append(String.format("%-15s %-15s %-10s\n", "Date", "Type", "Amount"));
            sb.append("------------------------------------------------\n");

            while (rs.next()) {
                String date = rs.getString("date");
                String type = rs.getString("type");
                String amount = rs.getString("amount");

                sb.append(String.format("%-15s %-15s ₹%-10s\n", date, type, amount));
            }

            miniStatementArea.setText(sb.toString());


        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error fetching mini statement.");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new MiniStatement("","" );
    }
}
