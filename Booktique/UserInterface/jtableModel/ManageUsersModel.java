package jtableModel;

import entities.User;
import entities.UserLending;

import javax.swing.table.AbstractTableModel;
import java.util.Arrays;
import java.util.Date;
import java.util.Vector;

public class ManageUsersModel extends AbstractTableModel implements IJTableModel{

    private final static String[] columnNames = {"User ID", "User Name", "FirstName", "Last Name", "User Status", "Register Date", "Address", "Email", "Phone", "Is Active"};
    private Vector<User> users;

    public ManageUsersModel(Vector<User> data) {
        users = data;
        fireTableRowsInserted(users.size() - 1, users.size() - 1);
    }

    @Override
    public String[] getColumnNames() {
        return columnNames;
    }

    private ManageUsersModel() {
        users = new Vector<User>();
    }

    public Vector<String> getColumns() {
        return new Vector<String>(Arrays.asList(columnNames));
    }

    public Vector<User> getUsers() {
        return this.users;
    }

    public void addElement(User e) {
        // Adds the element in the last position in the list
        users.add(e);
        fireTableRowsInserted(users.size() - 1, users.size() - 1);
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public int getRowCount() {
        return users.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return users.get(rowIndex).getId();
            case 1:
                return users.get(rowIndex).getUserName();
            case 2:
                return users.get(rowIndex).getFirstName();
            case 3:
                return users.get(rowIndex).getLastName();
            case 4:
                return users.get(rowIndex).getPassword();
            case 5:
                return users.get(rowIndex).getUserStatus();
            case 6:
                return users.get(rowIndex).getCreated();
            case 7:
                return users.get(rowIndex).getAddress();
            case 8:
                return users.get(rowIndex).getEmail();
            case 9:
                return users.get(rowIndex).getPhone();
            case 10:
                return users.get(rowIndex).isActive();
        }
        return null;
    }

}
