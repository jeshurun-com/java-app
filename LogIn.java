import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class LogIn extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;

    public LogIn() {
        setTitle("Login");
        setSize(550, 400);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Font font = new Font("Arial", Font.BOLD, 30);
        JLabel label = new JLabel("LOG IN", JLabel.CENTER);
        label.setFont(font);

        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);

        Dimension textFieldSize = new Dimension(200, 40);
        usernameField.setPreferredSize(textFieldSize);
        passwordField.setPreferredSize(textFieldSize);

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 10, 20, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        add(label, gbc);

        // Username
        gbc.gridy++;
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        add(new JLabel("Username: "), gbc);
        gbc.gridx = 1;
        add(usernameField, gbc);

        // Password
        gbc.gridy++;
        gbc.gridx = 0;
        add(new JLabel("Password: "), gbc);
        gbc.gridx = 1;
        add(passwordField, gbc);

        // Submit Button
        gbc.gridy++;
        gbc.gridx = 1;
        JButton submitButton = new JButton("GET IN");
        Dimension buttonSize = new Dimension(100, 40);
        submitButton.setPreferredSize(buttonSize);
        add(submitButton, gbc);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    submitForm();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });

        setVisible(true);
    }

    private void submitForm() throws IOException {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        if("123".equals(username) && "123".equals(password)) {
            JOptionPane.showMessageDialog(this, "Access Granted");
            new App();
                dispose();
        }
        else {
            JOptionPane.showMessageDialog(this, "Failed");
        }
    }

    public static void main(String[] args) {
       SwingUtilities.invokeLater(LogIn::new);
    }
}
