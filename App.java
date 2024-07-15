import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.EmptyBorder;

public class App extends JFrame {
    private static final long serialVersionUID = 1L;
    private JLabel questionLabel;
    private JRadioButton[] options;
    private JButton nextButton, previousButton, submitButton;
    private ButtonGroup buttonGroup;
    private int currentQuestionIndex = 0;
    private String[] questions;
    private String[][] choices;
    private String[] correctAnswers;
    private String[] userAnswers;

    public App() {
        setTitle("Quiz App");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        // Initialize data
        questions = new String[]{
                "What is the capital of France?",
                "What is the capital of Germany?",
                "What is the capital of Spain?"
                // Add more questions as needed
        };
        choices = new String[][]{
                {"Berlin", "Madrid", "Paris", "Rome"},
                {"Berlin", "Madrid", "Paris", "Rome"},
                {"Berlin", "Madrid", "Paris", "Rome"}
                // Add choices for more questions
        };
        correctAnswers = new String[]{
                "Paris",
                "Berlin",
                "Madrid"
                // Add correct answers for more questions
        };
        userAnswers = new String[questions.length];

        // Initialize fonts
        Font generalFont = new Font("Arial", Font.PLAIN, 16);
        Font questionFont = new Font("Arial", Font.BOLD, 20);

        // Initialize components
        questionLabel = new JLabel();
        questionLabel.setFont(questionFont);
        questionLabel.setBorder(new EmptyBorder(0, 20, 0, 0));

        options = new JRadioButton[4];
        for (int i = 0; i < options.length; i++) {
            options[i] = new JRadioButton();
            options[i].setFont(generalFont);
            options[i].setBorder(new EmptyBorder(0, 20, 0, 0));
        }

        // Group the radio buttons
        buttonGroup = new ButtonGroup();
        for (JRadioButton option : options) {
            buttonGroup.add(option);
        }

        nextButton = new JButton("Next");
        nextButton.setFont(generalFont);
        nextButton.addActionListener(new NextButtonListener());

        previousButton = new JButton("Previous");
        previousButton.setFont(generalFont);
        previousButton.addActionListener(new PreviousButtonListener());

        submitButton = new JButton("Submit");
        submitButton.setFont(generalFont);
        submitButton.addActionListener(new SubmitButtonListener());

        // Layout setup
        setLayout(new BorderLayout());
        JPanel centerPanel = new JPanel(new GridLayout(5, 1));
        centerPanel.add(questionLabel);
        for (JRadioButton option : options) {
            centerPanel.add(option);
        }

        add(centerPanel, BorderLayout.CENTER);

        JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        southPanel.add(previousButton);
        southPanel.add(nextButton);
        southPanel.add(submitButton);
        add(southPanel, BorderLayout.SOUTH);

        loadQuestion(currentQuestionIndex);
    }

    private void loadQuestion(int index) {
        questionLabel.setText(questions[index]);
        String[] currentChoices = choices[index];
        buttonGroup.clearSelection();
        for (int i = 0; i < options.length; i++) {
            options[i].setText(currentChoices[i]);
            options[i].setSelected(userAnswers[index] != null && userAnswers[index].equals(currentChoices[i]));
        }

        nextButton.setVisible(index < questions.length - 1);
        submitButton.setVisible(index == questions.length - 1);
        previousButton.setVisible(index > 0);

        revalidate();
        repaint();
    }

    private class NextButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (validateSelection()) {
                saveAnswer();
                currentQuestionIndex++;
                loadQuestion(currentQuestionIndex);
            } else {
                JOptionPane.showMessageDialog(null, "Please select an answer.");
            }
        }
    }

    private class PreviousButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            saveAnswer();
            currentQuestionIndex--;
            loadQuestion(currentQuestionIndex);
        }
    }

    private class SubmitButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (validateSelection()) {
                saveAnswer();
                showResults();
            } else {
                JOptionPane.showMessageDialog(null, "Please select an answer.");
            }
        }
    }

    private boolean validateSelection() {
        for (JRadioButton option : options) {
            if (option.isSelected()) {
                return true;
            }
        }
        return false;
    }

    private void saveAnswer() {
        for (JRadioButton option : options) {
            if (option.isSelected()) {
                userAnswers[currentQuestionIndex] = option.getText();
                return;
            }
        }
        userAnswers[currentQuestionIndex] = null; // No answer selected
    }

    private void showResults() {
        int correctCount = 0;
        for (int i = 0; i < questions.length; i++) {
            if (userAnswers[i] != null && userAnswers[i].equals(correctAnswers[i])) {
                correctCount++;
            }
        }
        JOptionPane.showMessageDialog(null, "You got " + correctCount + " out of " + questions.length + " correct!");
        dispose();
    }

//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
//                new App().setVisible(true);
//            }
//        });
//    }
}
