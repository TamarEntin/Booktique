import entities.BorrowedBook;
import entities.User;
import entities.UserLending;
import enums.ResponseStatus;
import jtableModel.IJTableModel;
import jtableModel.UserLendingsModel;
import serviceHost.ServiceCommand;
import services.requests.AllBooksLendingsInformationRequest;
import services.requests.ExtendLendingRequest;
import services.requests.ReturnBookRequest;
import services.responses.AllBooksLendingsInformationResponse;
import services.responses.ExtendLendingResponse;
import services.responses.ReturnBookResponse;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

public class MyBooksPage implements IFinishedCommand{

    private JButton returnBookButton;
    private JButton extendBookBorrowButton;
    private ServiceCommand sc;
    private String borrowId;
    private User user;
    private String bookName;
    private String authorName;
    private JScrollPane sp;
    private IUpdateFrameCommand menuCommand;
    private JFrame frame;
    private UserLendingsModel landingsModel;

    public MyBooksPage(IUpdateFrameCommand menuCommand, User user)
    {
        this.user = user;
        sc = ServiceCommand.getInstance();
        borrowId = "";
        bookName = "";
        authorName = "";
        sp = new JScrollPane();
        this.menuCommand = menuCommand;
    }

    private JTable myBooksTable() {

        AllBooksLendingsInformationRequest request = new AllBooksLendingsInformationRequest(user.getId());
        AllBooksLendingsInformationResponse response = sc.execute(request);

        if (response.getStatus() != ResponseStatus.OK.errorCode()) {
            JOptionPane.showMessageDialog(null, response.getErrorMessage()); //Display Message
        } else {
            landingsModel = new UserLendingsModel(response.getBorrowedBook());
            JTable lendingsTable = new JTable(convert(landingsModel.getUserLending()), landingsModel.getColumns().toArray()) {
               @Override
                public boolean isCellEditable(int row, int col) {
                    return false;
                }
            };
            lendingsTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                public void valueChanged(ListSelectionEvent event) {
                    String borrowId = lendingsTable.getValueAt(lendingsTable.getSelectedRow(), 0).toString();
                    String bookName = lendingsTable.getValueAt(lendingsTable.getSelectedRow(), 1).toString();
                    String authorName = lendingsTable.getValueAt(lendingsTable.getSelectedRow(), 2).toString();
                    userChoseAvailableBook(borrowId, bookName, authorName);
                }
            });
            return lendingsTable;
        }
        return new JTable();
    }

    public JFrame myBooksPanel()
    {
        frame = new JFrame();

        final JTable table = myBooksTable();
        JPanel btnPnl = new JPanel(new BorderLayout());
        JPanel bottombtnPnl = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JButton exportBtn = new JButton("Export");
        //bottombtnPnl.add(exportBtn);

        exportBtn.addActionListener(new ActionListener() {  //Perform action
            public void actionPerformed(ActionEvent e) {
                boolean result = ExportToFile.exportToTextFile(table, landingsModel, this.getClass().getName());
                if (result)
                    JOptionPane.showMessageDialog(null,"Exported Successfully!"); //Display Message
                else
                    JOptionPane.showMessageDialog(null, "Something went wrong"); //Display Message

            }
        });



        returnBookButton = new JButton("Return Book");
        returnBookButton.setEnabled(false);

        extendBookBorrowButton = new JButton("Extend My Borrow");
        extendBookBorrowButton.setEnabled(false);

        bottombtnPnl.add(returnBookButton);
        bottombtnPnl.add(extendBookBorrowButton);

        returnBookButton.addActionListener(new ActionListener() {  //Perform action
            public void actionPerformed(ActionEvent e) {
                ReturnBookRequest request = new ReturnBookRequest(user.getId(), borrowId);
                ReturnBookResponse response = sc.execute(request);
                if(response.getStatus() != ResponseStatus.OK.errorCode())
                {
                    JOptionPane.showMessageDialog(null,response.getErrorMessage()); //Display Message
                }
                else {
                    JOptionPane.showMessageDialog(null,"Returned Book Successfully"); //Display Message
                    BorrowedBook borrowedBook = response.getBorrowedBook();
                    AddRecommendationPage.AddRecommendation(user.getId(), user.getUserName(), bookName,borrowedBook.getBookID(), authorName);
                    finishedCommand();
                }
            }
        });

        extendBookBorrowButton.addActionListener(new ActionListener() {  //Perform action
            public void actionPerformed(ActionEvent e) {
                ExtendLendingRequest request = new ExtendLendingRequest(user.getId(), borrowId);
                ExtendLendingResponse response = sc.execute(request);
                if(response.getStatus() != ResponseStatus.OK.errorCode())
                {
                    JOptionPane.showMessageDialog(null,response.getErrorMessage()); //Display Message
                }
                else {
                    JOptionPane.showMessageDialog(null,"Extended Successfully"); //Display Message
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
        final JLabel titleLabel = new JLabel("<html><h2>My Books</h2></html>");
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

    public static boolean exportToTextFile(JTable table, IJTableModel model, String fileName)
    {
        String _FileLocation = "C:\\Users\\USER\\eclipse-workspace\\Booktique\\Output";
        String _FileStorgeName = fileName + ".txt";
        String filepath = _FileLocation + "\\"+ _FileStorgeName;

        if (!setUpRepository(_FileLocation,_FileStorgeName))
            return false;

        File file = new File(filepath);
        try{
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);

            for(int i = 0; i < table.getColumnCount(); i++)
            {
                bw.write(model.getColumnNames()[i] + "\t");
            }
            bw.newLine();

            for (int i = 0; i < table.getRowCount(); i++)
            {
                for (int j = 0; j< table.getColumnCount(); j++)
                {
                    bw.write(table.getValueAt(i,j).toString() + "\t");
                }
                bw.newLine();
            }
            bw.close();
            fw.close();
        }
        catch(Exception e)
        {
            return  false;
        }
        return true;
    }

    private static boolean setUpRepository(String _FileLocation, String _FileStorgeName)
    {
        File files = new File(_FileLocation);

        if (!files.exists()) {
            if (!files.mkdirs()) {
                return false;
            }
        }

        File repositoryFullPath = new File(_FileLocation + "\\"+ _FileStorgeName);
        try {
            if (repositoryFullPath.createNewFile())
                return true;
        }
        catch (IOException e) {
            return false;
        }

        return true;
    }


    private void refreshTable()
    {
        myBooksTable();
    }

    private void userChoseAvailableBook(String borrowId, String bookName, String authorName) {
        if (borrowId.equals("")) return;

        this.borrowId = borrowId;
        this.bookName = bookName;
        this.authorName = authorName;

        if (returnBookButton != null)
            this.returnBookButton.setEnabled(true);

        if(extendBookBorrowButton != null)
            this.extendBookBorrowButton.setEnabled(true);
    }


    private String [] [] convert(Vector<UserLending> lends)
    {
        String [][] stringM = new String[lends.size()][8];

        for (int i = 0; i < lends.size(); i ++){
            UserLending lend = lends.get(i);
            String [] lendsArray = new String[8];
            lendsArray[0] = lend.getBorrowID();
            lendsArray[1] = lend.getBookName();
            lendsArray[2] = lend.getAuthorName();
            lendsArray[3] = lend.getCategory();
            lendsArray[4] = String.valueOf(lend.isExtended());
            lendsArray[5] = String.valueOf(lend.getStartBorrowRequest());
            lendsArray[6] = String.valueOf(lend.getFinalBorrowDate());
            lendsArray[7] = convertStatus(lend.getStatus());

            stringM[i] = lendsArray;
        }
        return  stringM;
    }

    private String convertStatus(int statusValue)
    {
        switch(statusValue)
        {
            case 1: return "Borrowed";
            case 2: return "Waiting for Approval";
            case 3: return "Approved";
            default: return "Unknown";
        }
    }

    @Override
    public void finishedCommand() {
        myBooksPanel();
        this.menuCommand.updateFrame(frame);
    }
}