import enums.ResponseStatus;
import serviceHost.ServiceCommand;
import services.requests.LoginRequest;
import services.responses.LoginResponse;
import services.responses.ResponseBase;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.TextAttribute;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Map;

public class LoginPage {

    /**
     * @wbp.parser.entryPoint
     */
    public static void login() {
        int Y = 200;
        int dY = 35;
        int X = 65;
        try
        {
            JFrame loginFrame = new JFrame("Login");//creating instance of JFrame
            loginFrame.getContentPane().setForeground(Color.BLACK);

            BufferedImage img = null;
            try {
                img = ImageIO.read(new File("C:\\Users\\USER\\eclipse-workspace\\Booktique\\icon.jpeg"));
            } catch (IOException e) {
                e.printStackTrace();
            }

            Image dimg = img.getScaledInstance(280, 280,
                    Image.SCALE_SMOOTH);

            ImageIcon imageIcon = new ImageIcon(dimg);

            JLabel LogoLabel = new JLabel(imageIcon);
            LogoLabel.setBounds(26, 11, 280, 280); //x axis, y axis, width, height

            X = 15;

            JLabel usernameLbl = new JLabel("Username:");  //Create label Username
            usernameLbl.setBounds(15, 310, 100, 30); //x axis, y axis, width, height

            JLabel passwordLbl = new JLabel("Password:");  //Create label Password
            passwordLbl.setBounds(15, 351, 100, 30);

            Y = 200;
            X += 90;

            JTextField usernameField = new JTextField(); //Create text field for username
            usernameField.setBounds(90, 310, 200, 30);

            JPasswordField passwordField = new JPasswordField(); //Create text field for password
            passwordField.setBounds(90, 351, 200, 30);

            X += 40;

            JButton loginBtn = new JButton("Login");//creating instance of JButton for Login Button
            loginBtn.setBounds(146, 392, 80, 25);//Dimensions for button
            //loginBtn.setBackground(Color.BLUE);
            X -= 100;

            JButton registrationBtn = new JButton("Doesn't have an account?");
            registrationBtn.setBounds(26, 425, 189, 25);
            registrationBtn.setHorizontalAlignment(SwingConstants.LEFT);
            registrationBtn.setBorderPainted(false);
            registrationBtn.setOpaque(false);
            registrationBtn.setBackground(Color.WHITE);
            registrationBtn.setToolTipText("Booktique is the best");
            registrationBtn.getModel().addChangeListener(evt -> {
                ButtonModel model = (ButtonModel) evt.getSource();
                Font btnFont = registrationBtn.getFont();
                Map attributes = btnFont.getAttributes();

                if (model.isRollover()) {
                    attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
                } else {
                    attributes.put(TextAttribute.UNDERLINE, null);
                }
                btnFont = btnFont.deriveFont(attributes);
                registrationBtn.setFont(btnFont);
            });

            loginBtn.addActionListener(new ActionListener() {  //Perform action
                public void actionPerformed(ActionEvent e) {

                    String username = usernameField.getText(); //Store username entered by the user in the variable "username"
                    String password = passwordField.getText(); //Store password entered by the user in the variable "password"

                    if (username.equals("")) //If username is null
                    {
                        JOptionPane.showMessageDialog(null, "Please enter username"); //Display dialog box with the message
                    } else if (password.equals("")) //If password is null
                    {
                        JOptionPane.showMessageDialog(null, "Please enter password"); //Display dialog box with the message
                    } else {
                        LoginRequest request = new LoginRequest(username, password);
                        ServiceCommand sc = ServiceCommand.getInstance();
                        LoginResponse response = sc.execute(request);
                        if (response.getStatus() != ResponseStatus.OK.errorCode()) {
                            JOptionPane.showMessageDialog(null, response.getErrorMessage()); //Display Message
                        } else {
                            switch (response.getUser().getUserStatus()) {
                                case 1:
                                    UserMenu um = new UserMenu(response.getUser());
                                    um.user_menu();
                                    break;
                                case 2:
                                    LibrarianMenu librarianMenu = new LibrarianMenu(response.getUser());
                                    librarianMenu.startLibrarianMenu();
                                    break;
                                case 3:
                                    AdminMenu adminMenu = new AdminMenu(response.getUser());
                                    adminMenu.startAdminMenu();
                                    break;
                                default:
                                    break;
                            }
                            loginFrame.dispose();
                        }
                    }
                }
            });

            registrationBtn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    RegisterPage.Register();
                    loginFrame.dispose();
                }
            });

            loginFrame.getContentPane().add(LogoLabel);
            loginFrame.getContentPane().add(passwordField); //add password
            loginFrame.getContentPane().add(usernameField);  //add user
            loginFrame.getContentPane().add(usernameLbl);  // add label1 i.e. for username
            loginFrame.getContentPane().add(passwordLbl); // add label2 i.e. for password

            loginFrame.getContentPane().add(loginBtn);
            loginFrame.getContentPane().add(registrationBtn);

            loginFrame.setSize(360, 500);
            loginFrame.getContentPane().setLayout(null);//using no layout managers
            loginFrame.setVisible(true);//making the frame visible
            loginFrame.setLocationRelativeTo(null);

        } catch (Exception e) {

        }
    }

}
