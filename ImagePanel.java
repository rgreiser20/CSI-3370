import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

// this class is used to facilitate loading our images for editing, can be used in Home and Room designers
public class ImagePanel extends JPanel {
    private BufferedImage image;

    public ImagePanel() {
        super();
    }

    // pass in the image to load and set it to the current image
    public void setImage(BufferedImage image) {
        this.image = image;
        repaint();
    }

    // display image in the graphics
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            g.drawImage(image, 0,0, this.getWidth(), this.getHeight(), this);
        }
    }
}
