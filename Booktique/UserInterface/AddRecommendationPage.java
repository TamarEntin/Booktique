import entities.Recommendation;
import enums.ResponseStatus;
import serviceHost.ServiceCommand;
import services.requests.RecommendRequest;
import services.responses.CreateOrderResponse;
import services.responses.RecommendResponse;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddRecommendationPage {

    public static void AddRecommendation(String userId, String username, String bookName, String bookId, String authorName) {

        final int[] Y = {100};
        int dY = 35;

        JFrame frame = new JFrame("Add Recommendation");//creating instance of JFrame

        JLabel bookNameLabel;
        JLabel authorNameLabel;
        JLabel rateLabel;
        JLabel recommendationLabel;


        bookNameLabel = new JLabel("Book Name");  //Create label Username
        bookNameLabel.setBounds(35, Y[0] += dY, 100, 30); //x axis, y axis, width, height

        authorNameLabel = new JLabel("Author Name");  //Create label Username
        authorNameLabel.setBounds(35, Y[0] += dY, 100, 30); //x axis, y axis, width, height

        rateLabel = new JLabel("Rate");  //Create label Username
        rateLabel.setBounds(35, Y[0] += dY, 100, 30); //x axis, y axis, width, height

        recommendationLabel = new JLabel("Description");  //Create label Password
        recommendationLabel.setBounds(35, Y[0] += dY, 100, 30);

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

        final int[] rateValue = new int[1];
        final JSpinner[] numberChooser = new JSpinner[1];
        SpinnerNumberModel numberModel = new SpinnerNumberModel(
                new Integer(5), // value
                new Integer(1), // min
                new Integer(5), // max
                new Integer(1) // step
        );
        numberChooser[0] = new JSpinner(numberModel);
        rateValue[0] = Integer.parseInt(numberChooser[0].getValue().toString());
        numberChooser[0].setBounds(115, Y[0] += dY, 200, 30);

        Border border = BorderFactory.createLineBorder(Color.GRAY);

        JTextArea descriptionField = new JTextArea();
        descriptionField.setBounds(115, Y[0] += dY, 200, 150);
        descriptionField.setBorder(BorderFactory.createCompoundBorder(border,
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));

        JButton createOrder_but = new JButton("Recommend!");
        createOrder_but.setBounds(115, Y[0] += 5 * dY, 125, 25);
        createOrder_but.addActionListener(new ActionListener() {  //Perform action

            public void actionPerformed(ActionEvent e) {

                String bookNAme = bookNameField.getText();
                String authorName = authorNameField.getText(); //Store username entered by the user in the variable "username"
                float rate = ((Integer) numberModel.getValue()).floatValue();
                String description = descriptionField.getText();

                Recommendation recommendation = new Recommendation(bookId, userId, username, rate, description);
                RecommendRequest request = new RecommendRequest(recommendation);
                ServiceCommand sc = ServiceCommand.getInstance();
                RecommendResponse response = sc.execute(request);
                if (response.getStatus() != ResponseStatus.OK.errorCode()) {
                    JOptionPane.showMessageDialog(null, response.getErrorMessage()); //Display Message
                } else {
                    JOptionPane.showMessageDialog(null, "Thanks!"); //Display Message

                }
                frame.dispose();

            }

        });

        frame.add(bookNameLabel);
        frame.add(recommendationLabel);
        frame.add(rateLabel);
        frame.add(authorNameLabel);


        frame.add(bookNameField);
        frame.add(numberChooser[0]);
        frame.add(descriptionField);
        frame.add(authorNameField);


        frame.add(createOrder_but);//adding button in JFrame

        frame.setSize(360, 500);//400 width and 500 height
        frame.setLayout(null);//using no layout managers
        frame.setVisible(true);//making the frame visible
        frame.setLocationRelativeTo(null);


    }
}