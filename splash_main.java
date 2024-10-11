import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.Timer;

public class splash_main extends JFrame {

    public splash_main() {
        setTitle("Splash");
        setResizable(false);
        setSize(700, 404);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);

        //panel with no padding or margins
        JPanel panel = new JPanel(new BorderLayout(0, 0));

        // Load and resize the image
        ImageIcon originalIcon = new ImageIcon("anon.jpg");
        Image originalImage = originalIcon.getImage();
        Image resizedImage = originalImage.getScaledInstance(700, 410, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);

        //label to display the image
        JLabel imageLabel = new JLabel(resizedIcon);

        // Add the label to the panel
        panel.add(imageLabel, BorderLayout.CENTER);

        // Add the panel to the frame
        add(panel, BorderLayout.CENTER);

        // Set a timer to close the splash screen and open the main application after 7 seconds
        Timer timer = new Timer(15000, e -> {
            new LogIn();  // Open the main application
            dispose();    // Close the splash screen
        });
        timer.setRepeats(false);
        timer.start();

        // Center the frame on the screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((screenSize.width - getWidth()) / 2, (screenSize.height - getHeight()) / 2);
    }

    public static void main(String[] args) {
        splash_main newSplash = new splash_main();
        newSplash.setVisible(true);
    }
}
