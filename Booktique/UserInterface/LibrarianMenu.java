import entities.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LibrarianMenu implements IUpdateFrameCommand{

    private JFrame frame;
    private User user;

    public LibrarianMenu(User user)
    {
        this.user = user;
        frame = new JFrame("Librarian Functions");
    }
    public void startLibrarianMenu()
    {
        int width = 1100;
        int height = 600;

        JMenuBar menuBar = new JMenuBar();

        JMenu menuMenu = new JMenu("Menu");
        JMenuItem menuItemHome = new JMenuItem("Home");
        JMenuItem menuItemQuite = new JMenuItem("Quite");
        menuMenu.add(menuItemHome);
        menuMenu.add(menuItemQuite);

        menuItemHome.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                loadFrame("Home", HomePage.home(height, width, user).getComponents());
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
                LoginPage.login();
                frame.dispose();
            }
        });

        JMenu menuManage = new JMenu("Manage");
        JMenuItem menuItemManageBooks = new JMenuItem("Manage Books");
        JMenuItem menuItemManageBorrows = new JMenuItem("Manage Borrows");
        menuManage.add(menuItemManageBooks);
        menuManage.add(menuItemManageBorrows);

        menuItemManageBooks.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ManageBooksPage manageBooksPage = new ManageBooksPage(LibrarianMenu.this::updateFrame, user);
                loadFrame("Manage Books", manageBooksPage.manageBooksPanel().getComponents());

            }
        });

        menuItemManageBorrows.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ManageBorrowsPage manageBorrowsPage = new ManageBorrowsPage(LibrarianMenu.this::updateFrame, user);
                loadFrame("Manage Borrows", manageBorrowsPage.manageBorrowsPanel().getComponents());
            }
        });

        menuBar.add(menuMenu);
        menuBar.add(menuAccount);
        menuBar.add(menuManage);

        frame.setSize(width,height);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);
        frame.setJMenuBar(menuBar);
        loadFrame("Home", HomePage.home(height, width, user).getComponents());
        frame.setTitle("Home");
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
    public void updateFrame(JFrame frame) {
        loadFrame(frame.getTitle(), frame.getComponents());
    }
}