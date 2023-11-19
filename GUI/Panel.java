package GUI;

import Books.Book;
import Warehouse.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class Panel extends java.awt.Panel {

    JTextField text_field;
    JButton searchButton;
    JPanel container;
    JScrollPane scrollPane;
    public Panel () {
        Initiation();

        this.setPreferredSize(new Dimension(Frame.WIDTH,Frame.HEIGHT));
        this.setBackground(Color.lightGray);
        this.setVisible(true);
        this.setFocusable(true);
        this.add(text_field);
        this.add(searchButton);
        this.add(scrollPane);

    }

    public void Initiation(){
        container = new JPanel();
        container.setVisible(true);
        container.setFocusable(true);
        container.setLayout(new BoxLayout(container,BoxLayout.Y_AXIS));

        scrollPane = new JScrollPane(container);
        scrollPane.setWheelScrollingEnabled(true);
        scrollPane.setPreferredSize(new Dimension(Frame.WIDTH-100,Frame.HEIGHT-100));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        text_field = new JTextField();
        text_field.setPreferredSize(new Dimension(300,20));
        text_field.setName("Enter Book Name");

        searchButton =new JButton();
        searchButton.setText("Search");
        searchButton.setPreferredSize(new Dimension(100,20));
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performSearch();
            }
        });
    }

    public void performSearch() {
        String bookName = text_field.getText();
        BookStore bookStore = BookStore.getInstance();
        ArrayList<Book> bookArrayList = bookStore.getBooks(bookName);
        container.removeAll();
        for (Book item:
             bookArrayList) {
            BookContainer bookContainer = new BookContainer(item);
            System.out.println(bookContainer.book);
            container.add(bookContainer);
        }

        revalidate();
        repaint();
    }
}