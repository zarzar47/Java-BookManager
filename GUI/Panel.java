package GUI;

import Books.Book;
import DataStructures.LinkedList;
import DataStructures.Node;
import Warehouse.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Panel extends java.awt.Panel {

    JTextField text_field;
    JButton searchButton;
    JButton genreFilter;
    JPopupMenu genreList;
    JButton authorFilter;
    static JPanel container;
    JPanel buyPage;
    JScrollPane scrollPane;
    boolean filterGenre;
    String genreName;
    boolean filterAuthor;
    String authorName;
    public static JTabbedPane pane;
    public Panel() {
        Initiation();
        pane = new JTabbedPane();
        JPanel catalogue = new JPanel();
        catalogue.setPreferredSize(new Dimension(Frame.WIDTH, Frame.HEIGHT));
        catalogue.setBackground(Color.lightGray);
        catalogue.setVisible(true);
        catalogue.setFocusable(true);
        catalogue.add(text_field);
        catalogue.add(searchButton);
        catalogue.add(genreFilter);
        catalogue.addKeyListener(new keyReg());
       // catalogue.add(authorFilter);
        catalogue.add(scrollPane);

        pane.addTab("Catalogue",catalogue);
        pane.addTab("Checkout", Checkout.getInstance());

        this.add(pane);
    }

    public void Initiation() {
        genreName = "All";
        authorName = "";
        container = new JPanel();
        container.setVisible(true);
        container.setFocusable(true);
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        scrollPane = new JScrollPane(container);
        scrollPane.setWheelScrollingEnabled(true);
        scrollPane.setPreferredSize(new Dimension(Frame.WIDTH - 100, Frame.HEIGHT - 100));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        text_field = new JTextField();
        text_field.setPreferredSize(new Dimension(300, 20));
        text_field.setName("Enter Book Name");

        searchButton = new JButton();
        searchButton.setText("Search");
        searchButton.setPreferredSize(new Dimension(100, 20));


        genreFilter = new JButton();
        genreFilter.setText("Genre");
        genreFilter.setPreferredSize(new Dimension(100, 20));

        genreFilter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Create and show the popup menu
                showGenrePopup();
            }
        });

        authorFilter = new JButton();
        authorFilter.setText("Author");
        authorFilter.setPreferredSize(new Dimension(100, 20));
        authorFilter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                authorName = text_field.getText();
            }
        });
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performSearch();
            }
        });

        buyPage = new JPanel();
        buyPage.setVisible(true);
        buyPage.setFocusable(true);

    }

    private void showGenrePopup() {
        genreList = new JPopupMenu("Genres");
        genreList.add(new JMenuItem("All"));
        String[] genres = BookStore.getInstance().getGenres();
        for (int i = 0; i < genres.length; i++) {
            JMenuItem menuItem = new JMenuItem(genres[i]);
            menuItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Handle the selected genre
                    genreName = menuItem.getText();
                    // Optionally, you can perform some action based on the selected genre
                    System.out.println("Selected Genre: " + genreName);
                }
            });
            genreList.add(menuItem);
        }

        // Show the popup menu below the genreFilter button
        genreList.show(genreFilter, 0, genreFilter.getHeight());
    }

    public void performSearch() {
        String bookName = text_field.getText();
        BookStore bookStore = BookStore.getInstance();
        bookStore.updateList(bookName,genreName);
        container.removeAll();
        Node temp = bookStore.getCurrentBookList().getHead();

        while (temp != null) {
            BookContainer bookContainer = new BookContainer(temp.getPointer());
            container.add(bookContainer);
            temp = temp.getNext();
        }

        scrollPane.getVerticalScrollBar().setValue(50);

        revalidate();
        repaint();
    }

    private class keyReg implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER)
                searchButton.doClick();
        }

        @Override
        public void keyReleased(KeyEvent e) {

        }
    }
}