package ui;

import model.Event;
import model.EventLog;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class WelcomePanel extends JPanel {

    private JLabel welcomeLabel;
    private BufferedImage image;



    //initialises a panel to welcome the user to the game and has a picture
    public WelcomePanel() {

        setPreferredSize(new Dimension(400, 400));
        setLayout(new BorderLayout());
        welcomeLabel = new JLabel("Welcome to Pokemon Cards Game!");
        welcomeLabel.setFont(new Font("Montserrat", Font.BOLD, 40));
        setForeground(Color.PINK);
        add(welcomeLabel);


        try {
            image = ImageIO.read(new File("pokemon.jpg"));
            // Use the image object for display or manipulation
        } catch (IOException e) {
            System.out.println("Error loading image: " + e.getMessage());
        }
        ImageIcon imageIcon = new ImageIcon(image);
        JLabel imageLabel = new JLabel(imageIcon);


        add(imageLabel);



    }
}
