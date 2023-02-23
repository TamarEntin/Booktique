import entities.Recommendation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class WatchBookRecommendations
{
    private int current;
    private JLabel rateNameLable;
    private JLabel descriptionNameLable;
    private JLabel userNameNameLable;
    private JLabel rateLable;
    private JLabel descriptionLable;
    private JLabel userNameLable;
    private JButton prevButton;
    private JButton nextButton;
    private JButton firstButton;
    private JButton lastButton;
    private Vector<Recommendation> recommendations;

    public WatchBookRecommendations(Vector<Recommendation> recommendations)
    {
        this.recommendations = recommendations;
    }

    public void watchBookRecommendations()
    {
        int Y = 50;
        int dY = 35;
        int X = 30;
        int dX = 60;
        int dX2 = 105;

        JFrame f = new JFrame("Watch Book Recommendation");

        rateNameLable = new JLabel("<html><h3>Rate</h3></html>");
        rateNameLable.setBounds(X, Y, 100, 30);

        descriptionNameLable = new JLabel("<html><h3>Description</h3></html>");
        descriptionNameLable.setBounds(X, Y+=dY, 100, 95);

        userNameNameLable = new JLabel("<html><h3>Username</h3></html>");
        userNameNameLable.setBounds(X, Y+=100, 100, 50);

        Y = 50;
        X = 30;

        rateLable = new JLabel();
        rateLable.setBounds(X+dX2, Y, 100, 30);

        descriptionLable = new JLabel();
        descriptionLable.setBounds(X+dX2, Y+=dY, 250, 95);
        //descriptionLable.setFont(new Font("Serif", Font.ITALIC, 16));

        userNameLable  = new JLabel();
        userNameLable.setBounds(X+dX2, Y+=100, 100, 50);

        Y = Y+=100;
        X = 72;

        prevButton = new JButton("<");
        prevButton.setBounds(X, Y+=dY, 60, 30);

        nextButton = new JButton(">");
        nextButton.setBounds(X+=dX, Y, 60, 30);

        firstButton = new JButton("First");
        firstButton.setBounds(X+=dX, Y, 60, 30);

        lastButton = new JButton("Last");
        lastButton.setBounds(X+=dX, Y, 60, 30);

        current = 0;
        validateButtons(current);
        updateTexts(recommendations, current);

        prevButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                current--;
                validateButtons(current);
                updateTexts(recommendations, current);
            }
        });

        nextButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                current++;
                validateButtons(current);
                updateTexts(recommendations, current);
            }
        });

        firstButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                current = 0;
                validateButtons(current);
                updateTexts(recommendations, current);
            }
        });

        lastButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                current = recommendations.size() - 1;
                validateButtons(current);
                updateTexts(recommendations, current);
            }
        });

        f.add(rateNameLable);
        f.add(descriptionNameLable);
        f.add(userNameNameLable);

        f.add(rateLable);
        f.add(descriptionLable);
        f.add(userNameLable);

        f.add(prevButton);
        f.add(nextButton);
        f.add(firstButton);
        f.add(lastButton);

        f.setSize(400, 450);//400 width and 500 height
        f.setLayout(null);//using no layout managers
        f.setVisible(true);//making the frame visible
        f.setLocationRelativeTo(null);
    }

    public void updateTexts(Vector<Recommendation> recommendations, int i)
    {
        if (recommendations != null) {
            rateLable.setText(String.valueOf(recommendations.get(i).getRate()));
            descriptionLable.setText("<html>" + recommendations.get(i).getRecommendDescription() + "</html>");
            userNameLable.setText(recommendations.get(i).getUserName());
        }
    }

    public void validateButtons(int i)
    {
        if(current > 0) {
            prevButton.setEnabled(true);
        }
        else {
            prevButton.setEnabled(false);
        }
        if(current < recommendations.size() - 1) {
            nextButton.setEnabled(true);
        }
        else {
            nextButton.setEnabled(false);
        }
    }

}
