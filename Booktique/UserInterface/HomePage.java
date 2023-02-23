import entities.User;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class HomePage {

    /**
     * @wbp.parser.entryPoint
     */
    public static JFrame home(int height, int width, User user)
    {
        JFrame homeFrame = new JFrame("Login");//creating instance of JFrame
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("C:\\Users\\USER\\eclipse-workspace\\Booktique\\icon.jpeg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image dimg = img.getScaledInstance(300, 300,
                Image.SCALE_SMOOTH);
        ImageIcon imageIcon = new ImageIcon(dimg);
        JLabel LogoLabel = new JLabel(imageIcon);
        LogoLabel.setBounds(390, 0, 300, 300); //x axis, y axis, width, height

        String userWelcomeTitle = "<html><h1>Welcome, " + user.getUserName() + "</h1></html>";
        JLabel helloL = new JLabel("<html><h1>Welcome to Bootique system, <dynamic></h1>\r\n<h1>Enjoy from your reading <dynamic></h1>\r\n\r\n</html>");
        helloL.setHorizontalAlignment(SwingConstants.CENTER);

        String niceReadLTitle = "<html><br><h2>Have A Nice Read!</h2></html>";

        homeFrame.getContentPane().add(LogoLabel, BorderLayout.NORTH);
        homeFrame.getContentPane().add(helloL, BorderLayout.CENTER);

        return homeFrame;
    }

}
