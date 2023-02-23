import entities.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserMenu implements IUpdateFrameCommand{

    private JFrame frame;
    private User user;

    public UserMenu(User user)
    {
        this.user = user;
        frame = new JFrame("User Functions");
    }

    public void user_menu()
    {
        final int width = 1100;
        final int height = 600;

        JMenuBar menuBar = new JMenuBar();

        JMenu menuMenu = new JMenu("Menu");
        JMenuItem menuItemHome = new JMenuItem("Home");
        JMenuItem menuItemMyBooks = new JMenuItem("My Books");
        JMenuItem menuItemLibraryBooks = new JMenuItem("Library Books");
        JMenuItem menuItemQuite = new JMenuItem("Quite");
        menuMenu.add(menuItemHome);
        menuMenu.add(menuItemMyBooks);
        menuMenu.add(menuItemLibraryBooks);
        menuMenu.add(menuItemQuite);

        menuItemHome.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                loadFrame("Home", HomePage.home(height, width, user).getComponents());
            }
        });

        menuItemMyBooks.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                MyBooksPage myBooksPage = new MyBooksPage(UserMenu.this::updateFrame, user);
                loadFrame("My Books", myBooksPage.myBooksPanel().getComponents());
            }
        });

        menuItemLibraryBooks.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                LibraryBooksPage libraryBooksPage = new LibraryBooksPage(UserMenu.this::updateFrame, user);
                loadFrame("Library Book Stock", libraryBooksPage.libraryBooksPanel().getComponents());
            }
        });

        menuItemQuite.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
            }
        });

        JMenu menuAccount = new JMenu("Account");
        JMenuItem menuItemEdit =  new JMenuItem("Edit");
        JMenuItem menuItemLogout =  new JMenuItem("Logout");
        menuAccount.add(menuItemEdit);
        menuAccount.add(menuItemLogout);

        menuItemEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                UpdateUserInfo.UpdateUserInfoPage(user);
            }
        });

        menuItemLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                frame.dispose();
                LoginPage.login();
            }
        });

        menuBar.add(menuMenu);
        menuBar.add(menuAccount);

        frame.setSize(width,height);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);
        frame.setJMenuBar(menuBar);
        loadFrame("Home", HomePage.home(height, width, user).getComponents());
        frame.setTitle("Home");
        frame.setResizable(false);
        frame.setVisible(true);
    }

    private void loadFrame(String frameTitle, Component [] cmps)
    {
        frame.getContentPane().removeAll();
        for (Component cmp: cmps) {
            frame.getContentPane().add(cmp);
        }
        frame.setTitle(frameTitle);
        frame.revalidate();
    }

    @Override
    public void updateFrame(JFrame frame)
    {
        loadFrame(frame.getTitle(), frame.getComponents());
    }

}