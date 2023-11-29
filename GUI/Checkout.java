package GUI;

import Books.Book;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
                JOptionPane.showInputDialog("Please enter your address");
            }
        });
        this.add(buyButton);
    }

    public void setDetails(Book book){
        String[]  details = book.getDetailsOnly().split("%&");
        String[] labels = {"ISBN:","Name:","Author:","Publisher:", "Genre:", "Price:", "In stock:", "Popularity:"};
        bookPanel.removeAll();
        for (int i = 0; i < details.length; i++) {
            bookPanel.add(getCustomText(labels[i]+" "+details[i].trim()));
        }
    }

    private JTextField getCustomText(String string){
        JTextField item = new JTextField();
        item.setText(string);
        item.setEditable(false);
        item.setPreferredSize(new Dimension(bookPanel.getWidth()-10,50));
        return item;
    }


}
