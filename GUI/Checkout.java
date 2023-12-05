package GUI;

import Books.Book;

import javax.management.relation.RelationNotification;
import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.Random;

public class Checkout extends JPanel {
    private final JButton buyButton = new JButton();
    private static Checkout buyScreen;
    private JPanel bookPanel;
    private Checkout(){
        initialization();
    }

    public static Checkout getInstance(){
        if (buyScreen == null) {
            buyScreen = new Checkout();
            buyScreen.setPreferredSize(new Dimension(Frame.WIDTH, Frame.HEIGHT-100));
            buyScreen.setBackground(Color.lightGray);
            buyScreen.setVisible(true);
            buyScreen.setFocusable(true);
        };
        return buyScreen;
    }


    public void initialization(){
        bookPanel = new JPanel();
        bookPanel.setPreferredSize(new Dimension(400,500));
        bookPanel.setVisible(true);
//        bookInfo.setEditable(false);
//        bookInfo.setLineWrap(true);
//        bookInfo.setWrapStyleWord(true);
        bookPanel.setFont(new Font("Arial", Font.PLAIN, 14));
        bookPanel.setForeground(Color.BLACK);
        bookPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        bookPanel.setBackground(Color.WHITE);

        this.add(bookPanel);

        buyButton.setText("Buy!");
        buyButton.setPreferredSize(new Dimension(100,50));
        buyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NumberFormat longFormat = NumberFormat.getIntegerInstance();

                NumberFormatter numberFormatter = new NumberFormatter(longFormat);
                numberFormatter.setValueClass(Long.class); //optional, ensures you will always get a long value
                numberFormatter.setAllowsInvalid(false); //this is the key!!
                numberFormatter.setMinimum(0l); //Optional

                JFormattedTextField xField = new JFormattedTextField(numberFormatter);
                JTextField yField = new JTextField(10);

                JPanel myPanel = new JPanel();
                myPanel.setLayout(new GridLayout(5, 0));
                myPanel.add(new JLabel("ID:"));
                myPanel.add(xField);
                myPanel.add(Box.createHorizontalStrut(15)); // a spacer
                myPanel.add(new JLabel("Password:"));
                myPanel.add(yField);
                Object[] options = {"Enter","Cancel","New User"};
                int result = JOptionPane.showOptionDialog(null, myPanel, "Enter a Number",
                        JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE,
                        null, options, null);
                if (result == JOptionPane.OK_OPTION) {
                    System.out.println("ID: " + xField.getText());
                    System.out.println("Password: " + yField.getText());
                } else if (result == 2) {
                    JPanel newUserPanel = new JPanel();
                    JFormattedTextField idField = new JFormattedTextField(numberFormatter);
                    idField.setText(""+(Math.random()*1000));
                    idField.setEditable(false);
                    newUserPanel.setLayout(new GridLayout(5, 0));
                    newUserPanel.add(new JLabel("ID:"));;
                    newUserPanel.add(idField);
                    String pass = JOptionPane.showInputDialog(null,newUserPanel,"Enter your password",JOptionPane.OK_CANCEL_OPTION);
                    System.out.println("The new password is "+pass);
                }
                //String id = JOptionPane.showInputDialog("Please enter your ID");
                //String pass = JOptionPane.showInputDialog("Please enter your password");
                //System.out.println(id + " " + pass);
            }

        });
        this.add(buyButton);
    }

    public void setDetails(Book book) {
        String[]  details = book.getDetailsOnly().split("%&");
        String[] labels = {"ISBN:","Name:","Author:","Publisher:", "Genre:", "Price:", "In stock:", "Popularity:"};
        bookPanel.removeAll();
        for (int i = 0; i < details.length; i++) {
            bookPanel.add(getCustomText(labels[i]+" "+details[i].trim()));
        }
    }

    private JTextField getCustomText(String string) {
        JTextField item = new JTextField();
        item.setText(string);
        item.setEditable(false);
        item.setPreferredSize(new Dimension(bookPanel.getWidth()-10,50));
        return item;
    }


}
