package components;

import java.awt.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Image extends JPanel {
    private BufferedImage image;

    private int width;
    private int height;

    public Image(String imagePath, int width, int height) {
        this.setPreferredSize(new Dimension(width, height));
        this.setMinimumSize(new Dimension(width, height));
        this.setMaximumSize(new Dimension(width, height));

        this.setOpaque(false);
        this.setLayout(null);

        try {
            this.image = ImageIO.read(new File(imagePath));
            this.width = width;
            this.height = height;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            g.drawImage(image, 0, 0, width, height, this);
        }
    }
}
