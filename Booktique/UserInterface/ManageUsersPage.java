import entities.User;
import enums.ResponseStatus;
import jtableModel.ManageUsersModel;
import serviceHost.ServiceCommand;
import services.requests.GetUsersRequest;
import services.requests.UpdateUsersRequest;
import services.responses.GetUsersResponse;
import services.responses.UpdateUsersResponse;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Vector;

public class ManageUsersPage implements IFinishedCommand{
    private User user;
    private ServiceCommand sc;
    private Vector<User> usersToUpdate;
    private IUpdateFrameCommand menuCommand;
    private JFrame frame;
    private ManageUsersModel manageUsersModel;

    public ManageUsersPage(IUpdateFrameCommand command, User user)
    {
        this.user = user;
        sc = ServiceCommand.getInstance();
        usersToUpdate = new Vector<>();
        this.menuCommand = command;
    }

    private JTable manageUsersTable() {

        GetUsersRequest request = new GetUsersRequest(user.getId());
        GetUsersResponse response = sc.execute(request);

        if (response.getStatus() != ResponseStatus.OK.errorCode()) {
            JOptionPane.showMessageDialog(null, response.getErrorMessage()); //Display Message
        } else {
            manageUsersModel = new ManageUsersModel(response.getUsers());
            JTable usersTable = new JTable(convert(manageUsersModel.getUsers()), manageUsersModel.getColumns().toArray()) {
                @Override
                public boolean isCellEditable(int row, int col) {
                    if( col == 0 || col == 5 )
                        return false;
                    return true;
                }
            };

            usersTable.getModel().addTableModelListener (new TableModelListener() {
                public void tableChanged(TableModelEvent event) {
                    int row = usersTable.getSelectedRow();
                    User newUser = new User();
                    newUser.setId((String) usersTable.getModel().getValueAt(row, 0));
                    newUser.setUserName((String) usersTable.getModel().getValueAt(row, 1));
                    newUser.setFirstName((String) usersTable.getModel().getValueAt(row, 2));
                    newUser.setLastName((String) usersTable.getModel().getValueAt(row, 3));
                    newUser.setUserStatus(Integer.parseInt((String) usersTable.getModel().getValueAt(row, 4)));
                    SimpleDateFormat formatter=new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
                    try {
                        newUser.setCreated(formatter.parse((String) usersTable.getModel().getValueAt(row, 5)));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    newUser.setAddress((String) usersTable.getModel().getValueAt(row, 6));
                    newUser.setEmail((String) usersTable.getModel().getValueAt(row, 7));
                    newUser.setPhone((String) usersTable.getModel().getValueAt(row, 8));
                    newUser.setActive(Boolean.parseBoolean((String) usersTable.getModel().getValueAt(row, 9)));
                    userUpdated(newUser);
                }
            });
            return usersTable;
        }
        return new JTable();
    }

    public JFrame manageUsersPanel()
    {
        frame = new JFrame();


        final JTable table = manageUsersTable();
        JPanel btnPnl = new JPanel(new BorderLayout());
        JPanel bottombtnPnl = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JButton exportBtn = new JButton("Export");
        //bottombtnPnl.add(exportBtn);

        exportBtn.addActionListener(new ActionListener() {  //Perform action
            public void actionPerformed(ActionEvent e) {
                boolean result = ExportToFile.exportToTextFile(table, manageUsersModel, this.getClass().getName());
                if (result)
                    JOptionPane.showMessageDialog(null,"Exported Successfully!"); //Display Message
                else
                    JOptionPane.showMessageDialog(null, "Something went wrong"); //Display Message

            }
        });

        JButton updateUsers = new JButton("Save");
        updateUsers.setEnabled(true);

        bottombtnPnl.add(updateUsers);

        updateUsers.addActionListener(new ActionListener() {  //Perform action
            public void actionPerformed(ActionEvent e) {

                UpdateUsersRequest request = new UpdateUsersRequest(user.getId(), usersToUpdate);
                UpdateUsersResponse response = sc.execute(request);
                if(response.getStatus() != ResponseStatus.OK.errorCode())
                {
                    JOptionPane.showMessageDialog(null,response.getErrorMessage()); //Display Message
                }
                else {
                    JOptionPane.showMessageDialog(null,"Updated Users Successfully"); //Display Message
                    //refreshTable();
                    finishedCommand();
                }
            }
        });

        btnPnl.add(bottombtnPnl, BorderLayout.CENTER);

        table.getTableHeader().setReorderingAllowed(false);

        JScrollPane scrollPane = new JScrollPane(table);
        // Force the scrollbars to always be displayed
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        JPanel upPnl = new JPanel(new BorderLayout());
        final JLabel titleLabel = new JLabel("<html><h2>Mange Users</h2></html>");
        upPnl.add(titleLabel);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        frame.add(upPnl, BorderLayout.NORTH);
        frame.add(table.getTableHeader(), BorderLayout.CENTER);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(btnPnl, BorderLayout.SOUTH);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(false);
        return frame;
    }

    private void userUpdated(User userToUpdate)
    {
        if (userToUpdate == null) return;
        boolean isInserted = false;
        Vector<User> usersToRemove = new Vector<User>();
        Vector<User> usersToAdd = new Vector<User>();
        for (User usr: usersToUpdate) {
            if (usr.getId().equalsIgnoreCase(userToUpdate.getId()))
            {
                usersToRemove.add(usr);
                usersToAdd.add(userToUpdate);
                isInserted = true;
            }
        }

        if (isInserted)
        {
            usersToUpdate.removeAll(usersToRemove);
            usersToUpdate.addAll(usersToAdd);
        }
        else
        {
            usersToUpdate.add(userToUpdate);
        }
    }

    private String [] [] convert(Vector<User> users)
    {
        String [][] stringM = new String[users.size()][10];

        for (int i = 0; i < users.size(); i ++){
            User user = users.get(i);
            String [] usersArray = new String[10];
            usersArray[0] = user.getId();
            usersArray[1] = user.getUserName();
            usersArray[2] = user.getFirstName();
            usersArray[3] = user.getLastName();
            usersArray[4] = String.valueOf(user.getUserStatus());
            usersArray[5] = String.valueOf(user.getCreated());
            usersArray[6] = user.getAddress();
            usersArray[7] = user.getEmail();
            usersArray[8] = user.getPhone();
            usersArray[9] = String.valueOf(user.isActive());

            stringM[i] = usersArray;
        }
        return  stringM;
    }

    @Override
    public void finishedCommand() {
        manageUsersPanel();
        this.menuCommand.updateFrame(frame);
    }
}
