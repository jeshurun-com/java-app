import javax.swing.*;
import javax.swing.plaf.basic.BasicProgressBarUI;
import java.awt.*;

public class RoundedProgressBarUI extends BasicProgressBarUI {
    @Override
    protected void paintDeterminate(Graphics g, JComponent c) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = progressBar.getWidth();
        int height = progressBar.getHeight();
        int arcSize = height; // Full height arc for rounded ends

        // Paint background
        g2d.setColor(progressBar.getBackground());
        g2d.fillRoundRect(0, 0, width, height, arcSize, arcSize);

        // Paint progress
        int filledWidth = (int) (width * progressBar.getPercentComplete());
        g2d.setColor(progressBar.getForeground());
        g2d.fillRoundRect(0, 0, filledWidth, height, arcSize, arcSize);

        // Paint border
        g2d.setColor(Color.BLACK); // Or any border color you prefer
        g2d.drawRoundRect(0, 0, width - 1, height - 1, arcSize, arcSize);

        // Paint string
        if (progressBar.isStringPainted()) {
            paintString(g2d, 0, 0, width, height, filledWidth, null);
        }
    }
}
