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
    private boolean enabled = false;
    private Checkout(){
        initialization();
    }

    public static Checkout getInstance(){
        if (buyScreen == null) {
            buyScreen = new Checkout();
            buyScreen.setPreferredSize(new Dimension(Frame.WIDTH, Frame.HEIGHT-100));
            buyScreen.setBackground(Color.lightGray);
            buyScreen.setFocusable(true);
            buyScreen.setVisible(false);
        };
        return buyScreen;
    }

    public void initialization(){
        bookPanel = new JPanel();
        bookPanel.setPreferredSize(new Dimension(Frame.WIDTH-100,500));
        bookPanel.setFont(new Font("Arial", Font.PLAIN, 14));
        bookPanel.setForeground(Color.BLACK);
        bookPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        bookPanel.setBackground(Color.white);

        this.add(bookPanel);

        buyButton.setText("Buy!");
        buyButton.setPreferredSize(new Dimension(100,50));
        buyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginPanel loginPanel = new LoginPanel();
                JOptionPane.showMessageDialog(Checkout.this,loginPanel);
            }

        });
        buyButton.setVisible(false);
        this.add(buyButton);
    }

    public void setDetails(Book book) {
        if (!enabled)
            enableElements();
        String[]  details = book.getDetailsOnly().split("%&");
        String[] labels = {"ISBN:","Name:","Author:","Publisher:", "Genre:", "Price:", "In stock:", "Popularity:"};
        bookPanel.removeAll();
        for (int i = 0; i < details.length; i++) {
            bookPanel.add(getCustomText(labels[i]+" "+details[i].trim()));
        }
        setVisible(true);
    }

    public void enableElements(){
        buyButton.setVisible(true);
        enabled = true;
    }

    private JTextField getCustomText(String string) {
        JTextField item = new JTextField();
        item.setText(string);
        item.setEditable(false);
        item.setPreferredSize(new Dimension(bookPanel.getWidth()-10,50));
        return item;
    }


}
