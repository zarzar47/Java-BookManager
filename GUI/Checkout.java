package GUI;

import Books.Book;

import javax.management.relation.RelationNotification;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.Random;

public class Checkout extends JPanel {
    private final JButton buyButton = new JButton();
    private static Checkout buyScreen;
    private JScrollPane scrollPane;
    private JPanel bookPanel;
    private boolean enabled = false;
    private Book currentBook;
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

    public void setCurrentBook(Book book){
        this.currentBook = book;
    }

    public void initialization(){
        bookPanel = new JPanel();
        bookPanel.setPreferredSize(new Dimension(Frame.WIDTH-100,500));
        bookPanel.setFont(new Font("Arial", Font.PLAIN, 14));
        bookPanel.setForeground(Color.BLACK);
        bookPanel.setLayout(new BoxLayout(bookPanel,BoxLayout.Y_AXIS));
        bookPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        bookPanel.setBackground(Color.white);

        scrollPane = new JScrollPane(bookPanel);
        scrollPane.setWheelScrollingEnabled(true);
        scrollPane.setPreferredSize(new Dimension(Frame.WIDTH - 50, 500));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(25,0));
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.add(scrollPane);

        buyButton.setText("Buy!");
        buyButton.setPreferredSize(new Dimension(100,50));
        buyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginPanel loginPanel = new LoginPanel(currentBook);
                JOptionPane.showOptionDialog(Checkout.this,loginPanel,"Login",JOptionPane.DEFAULT_OPTION,JOptionPane.PLAIN_MESSAGE,null,new Object[]{},null);
            }

        });
        buyButton.setVisible(false);
        this.add(buyButton);
    }

    public void setDetails(Book book) {
        this.currentBook = book;
        if (!enabled)
            enableElements();
        String[]  details = book.getDetailsOnly().split("%&");
        String[] labels = {"ISBN:","Name:","Author:","Publisher:", "Genre:", "Price:", "In stock:", "Popularity:"};
        bookPanel.removeAll();

        bookPanel.add(new JLabel("Book Details"));
        for (int i = 0; i < details.length; i++) {
            bookPanel.add(getCustomText(labels[i]+" "+details[i].trim()));
        }
        bookPanel.add(new JLabel("Reviews"));
        Object[] reviews = book.getReviewList().toArray();
        for (int i = 0; i < reviews.length; i++) {
            bookPanel.add(getCustomText(reviews[i].toString()));
        }
        setVisible(true);
    }

    public void enableElements(){
        buyButton.setVisible(true);
        enabled = true;
    }

    private JTextArea getCustomText(String string) {
        JTextArea item = new JTextArea();
        item.setText(string);
        item.setLineWrap(true);
        item.setWrapStyleWord(true);
        item.setEditable(false);
        item.setBorder(BorderFactory.createEmptyBorder(10,40,10,40));
        item.setFont(new Font("Bonk",Font.BOLD,12));
        return item;
    }


}
