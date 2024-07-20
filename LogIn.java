import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LogIn extends JFrame {

    private JTextField idField;

    public LogIn() {
        setTitle("Login");
        setSize(550, 400);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Font font = new Font("Arial", Font.BOLD, 30);
        JLabel label = new JLabel("LOG IN", JLabel.CENTER);
        label.setFont(font);

        idField = new JTextField(20);

        Dimension textFieldSize = new Dimension(200, 40);
        idField.setPreferredSize(textFieldSize);

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 10, 20, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        add(label, gbc);

        // Student ID
        gbc.gridy++;
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        add(new JLabel("Student ID: "), gbc);
        gbc.gridx = 1;
        add(idField, gbc);

        // Submit Button
        gbc.gridy++;
        gbc.gridx = 1;
        JButton submitButton = new JButton("Login");
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
        String studentId = idField.getText();
        if (!studentId.isEmpty()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "MySQL JDBC Driver not found");
                return;
            }

            try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Quiz", "root", "")) {
                String query = "SELECT * FROM Students WHERE id = ?";
                try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                    pstmt.setString(1, studentId);
                    ResultSet rs = pstmt.executeQuery();
                    if (rs.next()) {
                        JOptionPane.showMessageDialog(this, "Access Granted");
                        new App(studentId).setVisible(true);
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(this, "Invalid Student ID");
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Database Error: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please enter Student ID");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LogIn::new);
    }
}
