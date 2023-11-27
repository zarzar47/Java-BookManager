package GUI;

import Books.Book;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Checkout extends JPanel {
    private final JButton buyButton = new JButton();
    private static Checkout buyScreen;
    JTextArea bookInfo;
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

    public void setBook(Book book){
        bookInfo.setText(book.toString());
    }

    public void initialization(){
        bookInfo = new JTextArea("Book details");
        bookInfo.setPreferredSize(new Dimension(400,500));
        bookInfo.setVisible(true);
        bookInfo.setEditable(false);
        bookInfo.setLineWrap(true);
        bookInfo.setWrapStyleWord(true);
        bookInfo.setFont(new Font("Arial", Font.PLAIN, 14));
        bookInfo.setForeground(Color.BLACK);
        bookInfo.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        bookInfo.setBackground(Color.WHITE);

        this.add(bookInfo);

        buyButton.setText("Buy!");
        buyButton.setPreferredSize(new Dimension(100,50));
        buyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showInputDialog("Please enter your address");
            }
        });
        this.add(buyButton);
    }


}
