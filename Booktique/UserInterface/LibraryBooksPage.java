import entities.BookStock;
import entities.User;
import enums.BooksFilter;
import enums.ResponseStatus;
import jtableModel.ManageBooksModel;
import serviceHost.ServiceCommand;
import services.requests.BookLendingRequest;
import services.requests.GetBookRecommendationRequest;
import services.requests.GetBooksRequest;
import services.responses.BookLendingResponse;
import services.responses.GetBookRecommendationResponse;
import services.responses.GetBooksResponse;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class LibraryBooksPage implements IFinishedCommand
{
    private User user;
    private JButton borrowBookBtn;
    private JButton exportBtn;
    private JButton viewRecommendations;
    private ServiceCommand sc;
    private String chosenBookId;
    private IUpdateFrameCommand menuCommand;
    private JFrame frame;
    private ManageBooksModel manageBooksModel;

    public LibraryBooksPage(IUpdateFrameCommand menuCommand, User user) {
        this.user = user;
        sc = ServiceCommand.getInstance();
        chosenBookId = "";
        this.menuCommand = menuCommand;
    }

    private JTable libraryBooksTable()
    {
        GetBooksRequest request = new GetBooksRequest(BooksFilter.AvailableOnly, true);
        GetBooksResponse response = sc.execute(request);

        if (response.getStatus() != ResponseStatus.OK.errorCode()) {
            JOptionPane.showMessageDialog(null, response.getErrorMessage()); //Display Message
        } else {
            Vector<BookStock> books = new Vector<>();
            if(response.getBooks() != null)
            {
                books.addAll(response.getBooks());
            }
            manageBooksModel = new ManageBooksModel(books);
            JTable booksTable = new JTable(convert(manageBooksModel.getBooks()), manageBooksModel.getColumns().toArray()) {
                @Override
                public boolean isCellEditable(int row, int col) {
                    return false;
                }
            };

            booksTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                public void valueChanged(ListSelectionEvent event) {
                    String bookId = booksTable.getValueAt(booksTable.getSelectedRow(), 0).toString();
                    userChoseAvailableBook(bookId);
                }
            });

            return booksTable;
        }
        return new JTable();
    }

    /**
     * @wbp.parser.entryPoint
     */
    public JFrame libraryBooksPanel() {

        frame = new JFrame();

        final JTable table = libraryBooksTable();
        JPanel btnPnl = new JPanel(new BorderLayout());
        JPanel bottombtnPnl = new JPanel(new FlowLayout(FlowLayout.CENTER));

        exportBtn = new JButton("Export");
        bottombtnPnl.add(exportBtn);

        exportBtn.addActionListener(new ActionListener() {  //Perform action
            public void actionPerformed(ActionEvent e) {
                boolean result = ExportToFile.exportToTextFile(table, manageBooksModel, this.getClass().getName());
                if (result)
                    JOptionPane.showMessageDialog(null,"Exported Successfully!"); //Display Message
                else
                    JOptionPane.showMessageDialog(null, "Something went wrong"); //Display Message

            }
        });


        borrowBookBtn = new JButton("Borrow");
        borrowBookBtn.setEnabled(false);

        viewRecommendations = new JButton("View Recommendations");
        viewRecommendations.setEnabled(false);

        bottombtnPnl.add(borrowBookBtn);
        bottombtnPnl.add(viewRecommendations);

        borrowBookBtn.addActionListener(new ActionListener() {  //Perform action
            public void actionPerformed(ActionEvent e) {
                BookLendingRequest request = new BookLendingRequest(user.getId(), chosenBookId);
                BookLendingResponse response = sc.execute(request);
                if(response.getStatus() != ResponseStatus.OK.errorCode())
                {
                    JOptionPane.showMessageDialog(null,response.getErrorMessage()); //Display Message
                }
                else {
                    JOptionPane.showMessageDialog(null,"Borrowed Successfully"); //Display Message
                    finishedCommand();
                }
            }
        });

        viewRecommendations.addActionListener(new ActionListener() {  //Perform action
            public void actionPerformed(ActionEvent e) {
                GetBookRecommendationRequest request = new GetBookRecommendationRequest(chosenBookId);
                GetBookRecommendationResponse response = sc.execute(request);
                if(response.getStatus() != ResponseStatus.OK.errorCode())
                {
                    JOptionPane.showMessageDialog(null, "No Recommendations to view"); //Display Message
                }
                else {
                        WatchBookRecommendations bookRecommendations = new WatchBookRecommendations(response.getBooksRecommendation());
                        bookRecommendations.watchBookRecommendations();
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
        final JLabel titleLabel = new JLabel("<html><h2>Library Books</h2></html>");
        upPnl.add(titleLabel);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        frame.getContentPane().add(upPnl, BorderLayout.NORTH);
        frame.getContentPane().add(table.getTableHeader(), BorderLayout.CENTER);
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
        frame.getContentPane().add(btnPnl, BorderLayout.SOUTH);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(false);
        return frame;
    }

    private String [] [] convert(Vector<BookStock> books)
    {
        String [][] stringM = new String[books.size()][5];
        for (int i = 0; i < books.size(); i ++){
            BookStock book = books.get(i);
            String [] booksArray = new String[7];
            booksArray[0] = book.getId();
            booksArray[1] = book.getBookName();
            booksArray[2] = book.getAuthorName();
            booksArray[3] = String.valueOf(book.getQuantity());
            booksArray[4] = book.getCategory();
            stringM[i] = booksArray;
        }
        return  stringM;
    }

    public void userChoseAvailableBook(String bookId) {
        if (bookId.equals("")) return;

        this.chosenBookId = bookId;

        if (borrowBookBtn != null)
            this.borrowBookBtn.setEnabled(true);

        if(viewRecommendations != null)
            this.viewRecommendations.setEnabled(true);
    }

    @Override
    public void finishedCommand() {
        libraryBooksPanel();
        this.menuCommand.updateFrame(frame);
    }
}
