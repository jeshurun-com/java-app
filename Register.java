import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Register extends JFrame {

    private JTextField id_field;
    private JTextField first_name_field;
    private JTextField last_name_field;

    public Register() {
        setTitle("Register");
        setSize(550, 400);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Font font = new Font("Arial", Font.BOLD, 30);
        JLabel label = new JLabel("Register", JLabel.CENTER);
        label.setFont(font);

        id_field = new JTextField(20);
        first_name_field = new JTextField(20);
        last_name_field = new JTextField(20);

        Dimension textFieldSize = new Dimension(200, 40);
        id_field.setPreferredSize(textFieldSize);
        first_name_field.setPreferredSize(textFieldSize);
        last_name_field.setPreferredSize(textFieldSize);

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 10, 20, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        add(label, gbc);

        // ID
        gbc.gridy++;
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        add(new JLabel("ID: "), gbc);
        gbc.gridx = 1;
        add(id_field, gbc);

        // First Name
        gbc.gridy++;
        gbc.gridx = 0;
        add(new JLabel("First Name: "), gbc);
        gbc.gridx = 1;
        add(first_name_field, gbc);

        // Last Name
        gbc.gridy++;
        gbc.gridx = 0;
        add(new JLabel("Last Name: "), gbc);
        gbc.gridx = 1;
        add(last_name_field, gbc);

        // Submit Button
        gbc.gridy++;
        gbc.gridx = 1;
        JButton submitButton = new JButton("Register");
        Dimension buttonSize = new Dimension(100, 40);
        submitButton.setPreferredSize(buttonSize);
        add(submitButton, gbc);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                submitForm();
            }
        });

        setVisible(true);
    }

    private void submitForm() {
        String id = id_field.getText();
        String firstName = first_name_field.getText();
        String lastName = last_name_field.getText();
        // Example of a simple validation; you can expand this as needed
        if (!id.isEmpty() && !firstName.isEmpty() && !lastName.isEmpty()) {
            // Load MySQL JDBC Driver
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "MySQL JDBC Driver not found");
                return;
            }

            // JDBC code to insert data into the MySQL database
            try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Quiz", "root@localhost", "")) {
                String query = "INSERT INTO Students (id, first_name, last_name) VALUES (?, ?, ?)";
                try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                    pstmt.setString(1, id);
                    pstmt.setString(2, firstName);
                    pstmt.setString(3, lastName);
                    pstmt.executeUpdate();
                    JOptionPane.showMessageDialog(this, "Registration Successfull");
                    // clear the fields after successful registration
                    id_field.setText("");
                    first_name_field.setText("");
                    last_name_field.setText("");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Database Error: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please fill all fields");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Register::new);
    }
}
