import entities.User;
import entities.UserLending;
import enums.ResponseStatus;
import jtableModel.ManageBorrowsModel;
import serviceHost.ServiceCommand;
import services.requests.AllUserAwaitingForApprovalBorrowingRequest;
import services.responses.AllUserAwaitingForApprovalBorrowingResponse;

import javax.swing.*;
import java.util.Vector;

public class BorrowedBooksPage {

    /**
     * @wbp.parser.entryPoint
     */
    public static JScrollPane borrowedBooks(User user) {

        AllUserAwaitingForApprovalBorrowingRequest request = new AllUserAwaitingForApprovalBorrowingRequest(user.getId());
        ServiceCommand sc = ServiceCommand.getInstance();
        AllUserAwaitingForApprovalBorrowingResponse response = sc.execute(request);

        if (response.getStatus() != ResponseStatus.OK.errorCode()) {
            JOptionPane.showMessageDialog(null, response.getErrorMessage()); //Display Message
        } else {
            ManageBorrowsModel manageBooksModel = new ManageBorrowsModel(response.getUserLending());
            JTable manageBooksTable = new JTable(convert(manageBooksModel.getUserLending()), manageBooksModel.getColumns().toArray()) {
                @Override
                public boolean isCellEditable(int row, int col) {
                    return false;
                }
            };
            JScrollPane sp = new JScrollPane(manageBooksTable);
            return sp;
        }
        return new JScrollPane();
    }

    private static String [] [] convert(Vector<UserLending> lends)
    {
        String [][] stringM = new String[lends.size()][7];

        for (int i = 0; i < lends.size(); i ++){
            UserLending lend = lends.get(i);
            String [] lendsArray = new String[7];
            lendsArray[0] = lend.getBookName();
            lendsArray[1] = lend.getAuthorName();
            lendsArray[2] = lend.getCategory();
            lendsArray[3] = String.valueOf(lend.isExtended());
            lendsArray[4] = String.valueOf(lend.getStartBorrowRequest());
            lendsArray[5] = String.valueOf(lend.getFinalBorrowDate());
            lendsArray[6] = String.valueOf(lend.getStatus());

            stringM[i] = lendsArray;
        }
        return  stringM;
    }

}
