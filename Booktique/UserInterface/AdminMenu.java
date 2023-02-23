import entities.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminMenu implements IUpdateFrameCommand{

    private JFrame frame;
    private User user;
    public AdminMenu(User user)
    {
        this.user = user;
        frame = new JFrame("Admin Functions");
    }
    public void startAdminMenu()
    {
        int width = 1100;
        int height = 600;

        JMenuBar menuBar = new JMenuBar();

        JMenuItem menuItemHome = new JMenuItem("Home");
        JMenuItem menuItemQuite = new JMenuItem("Quite");

        menuItemHome.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                loadFrame("Home", HomePage.home(height, width, user).getComponents());
            }
        });

        menuItemQuite.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
            }
        });

        JMenuItem menuItemEdit =  new JMenuItem("Edit Account");
        JMenuItem menuItemLogout =  new JMenuItem("Logout");

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

        JMenuItem menuItemManageConfigurations = new JMenuItem("Manage Configurations");
        JMenuItem menuItemManageUsers = new JMenuItem("Manage Users");
        JMenuItem menuItemManageBooks = new JMenuItem("Manage Books");
        JMenuItem menuItemManageBorrows = new JMenuItem("Manage Borrows");

        menuItemManageConfigurations.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ConfigurationPage manageConfigurationsPage = new ConfigurationPage(AdminMenu.this::updateFrame, user);
                Component [] cmps =  manageConfigurationsPage.configurationFrame().getComponents();
                loadFrame("Manage Configurations", cmps);
            }
        });

        menuItemManageUsers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ManageUsersPage manageUsersPage = new ManageUsersPage(AdminMenu.this::updateFrame, user);
                Component [] cmps =  manageUsersPage.manageUsersPanel().getComponents();
                loadFrame("Manage Users", cmps);
            }
        });

        menuItemManageBooks.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ManageBooksPage manageBooksPage = new ManageBooksPage(AdminMenu.this::updateFrame, user);
                loadFrame("Manage Books", manageBooksPage.manageBooksPanel().getComponents());

            }
        });

        menuItemManageBorrows.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ManageBorrowsPage manageBorrowsPage = new ManageBorrowsPage(AdminMenu.this::updateFrame, user);
                loadFrame("Manage Borrows", manageBorrowsPage.manageBorrowsPanel().getComponents());
            }
        });

        JMenu menuMenu = new JMenu("Menu");
        JMenu menuAccount = new JMenu("Account");
        JMenu menuManage = new JMenu("Manage");

        menuMenu.add(menuItemHome);
        menuMenu.add(menuItemQuite);
        menuAccount.add(menuItemEdit);
        menuAccount.add(menuItemLogout);
        menuManage.add(menuItemManageConfigurations);
        menuManage.add(menuItemManageUsers);
        menuManage.add(menuItemManageBooks);
        menuManage.add(menuItemManageBorrows);

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

    void loadFrame(String frameTitle, Component[] cmps)
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