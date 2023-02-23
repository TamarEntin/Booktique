import entities.Recommendation;
import enums.ResponseStatus;
import serviceHost.ServiceCommand;
import services.requests.RecommendRequest;
import services.requests.RemoveBookRequest;
import services.responses.RecommendResponse;
import services.responses.RemoveBookResponse;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RemoveBookPage {

    public static void RemoveBook(IFinishedCommand finishedCommand, String userId, String bookId,String bookName, int bookQuantity ,String authorName) {

        final int[] Y = {100};
        int dY = 35;

        JFrame frame = new JFrame("Remove Book");//creating instance of JFrame

        JLabel bookNameLabel;
        JLabel authorNameLabel;
        JLabel quantity;
        JLabel recommendationLabel;


        bookNameLabel = new JLabel("Book Name");  //Create label Username
        bookNameLabel.setBounds(35, Y[0] += dY, 100, 30); //x axis, y axis, width, height

        authorNameLabel = new JLabel("Author Name");  //Create label Username
        authorNameLabel.setBounds(35, Y[0] += dY, 100, 30); //x axis, y axis, width, height

        quantity = new JLabel("Quantity to Remove");  //Create label Username
        quantity.setBounds(35, Y[0] += dY, 100, 30); //x axis, y axis, width, height


        Y[0] = 100;
        // add input fields

        JLabel bookNameField = new JLabel(); //Create text field for username
        bookNameField.setEnabled(false);
        bookNameField.setText(bookName);
        bookNameField.setBounds(115, Y[0] += dY, 200, 30);

        JLabel authorNameField = new JLabel(); //Create text field for username
        authorNameField.setEnabled(false);
        authorNameField.setText(authorName);
        authorNameField.setBounds(115, Y[0] += dY, 200, 30);

        //JTextField rateField = new JTextField(); //Create text field for username
        //rateField.setBounds(110, Y+=dY, 200, 30);

        final int[] quantityValue = new int[1];
        final JSpinner[] numberChooser = new JSpinner[1];
        SpinnerNumberModel numberModel = new SpinnerNumberModel(
                new Integer(1), // value
                new Integer(1), // min
                new Integer(bookQuantity), // max
                new Integer(1) // step
        );

        numberChooser[0] = new JSpinner(numberModel);
        quantityValue[0] = Integer.parseInt(numberChooser[0].getValue().toString());
        numberChooser[0].setBounds(115, Y[0] += dY, 200, 30);

        Border border = BorderFactory.createLineBorder(Color.GRAY);

        JButton createOrder_but = new JButton("Remove");
        createOrder_but.setBounds(115, Y[0] += 5 * dY, 125, 25);
        createOrder_but.addActionListener(new ActionListener() {  //Perform action

            public void actionPerformed(ActionEvent e) {

                String bookNAme = bookNameField.getText();
                String authorName = authorNameField.getText(); //Store username entered by the user in the variable "username"
                int removeQuantity = (int)numberModel.getValue();

                RemoveBookRequest request = new RemoveBookRequest(userId, bookId, removeQuantity);
                ServiceCommand sc = ServiceCommand.getInstance();
                RemoveBookResponse response = sc.execute(request);
                if (response.getStatus() != ResponseStatus.OK.errorCode()) {
                    JOptionPane.showMessageDialog(null, response.getErrorMessage()); //Display Message
                } else {
                    JOptionPane.showMessageDialog(null, "Removed!"); //Display Message

                }
                frame.dispose();
                finishedCommand.finishedCommand();
            }

        });

        frame.add(bookNameLabel);
        frame.add(quantity);
        frame.add(authorNameLabel);


        frame.add(bookNameField);
        frame.add(numberChooser[0]);
        frame.add(authorNameField);


        frame.add(createOrder_but);//adding button in JFrame

        frame.setSize(360, 500);//400 width and 500 height
        frame.setLayout(null);//using no layout managers
        frame.setVisible(true);//making the frame visible
        frame.setLocationRelativeTo(null);


    }

}
