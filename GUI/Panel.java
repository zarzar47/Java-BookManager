package GUI;

import Books.Book;
import DataStructures.LinkedList;
import DataStructures.Node;
import Warehouse.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

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
    public JCheckBox name, price, popularity;
    public JTextField sortBy;

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
       // catalogue.add(authorFilter);
        catalogue.add(scrollPane);
        catalogue.add(sortBy);

        catalogue.add(name);
        catalogue.add(price);
        catalogue.add(popularity);
        name.setLayout(new BoxLayout(name, BoxLayout.Y_AXIS));
        price.setLayout(new BoxLayout(price, BoxLayout.Y_AXIS));
        popularity.setLayout(new BoxLayout(popularity, BoxLayout.Y_AXIS));

        //Controlling the clickable Panes
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
        // scrollPane.setBounds(10, 500, Frame.WIDTH - 50, Frame.HEIGHT - 100);
        scrollPane.setPreferredSize(new Dimension(Frame.WIDTH - 50, Frame.HEIGHT - 100));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(25,0));
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);


        text_field = new JTextField();
        text_field.setPreferredSize(new Dimension(300, 20));
        text_field.setName("bookName");
        text_field.setText("Enter Book Name");
        text_field.setFocusable(true);
        text_field.addKeyListener(new keyReg());
        text_field.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (text_field.getText().equalsIgnoreCase("Enter Book Name"))
                text_field.setText(null);
            }

            @Override
            public void focusLost(FocusEvent e) {
            }
        });


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

        name = new JCheckBox("Name");
        //name.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        // name.setPreferredSize(new Dimension(60, 20));
        name.setBackground(Color.LIGHT_GRAY);
        name.setBounds(100, 50, 10, 10);
        name.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                price.setSelected(false);
                popularity.setSelected(false);
            }
        });

        price = new JCheckBox("Price");
        // price.setPreferredSize(new Dimension(60, 20));
        price.setBackground(Color.LIGHT_GRAY);
        price.setBounds(100, 100, 10, 10);
        price.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                name.setSelected(false);
                popularity.setSelected(false);
            }
        });

        popularity = new JCheckBox("Popularity");
        // popularity.setPreferredSize(new Dimension(75, 20));
        popularity.setBackground(Color.LIGHT_GRAY);
        popularity.setBounds(100, 150, 10, 10);
        popularity.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                name.setSelected(false);
                price.setSelected(false);
            }
        });

        sortBy = new JTextField();
        sortBy.setPreferredSize(new Dimension(50, 20));
        sortBy.setEditable(false);
        sortBy.setText("Sort By: ");
        sortBy.setBackground(Color.lightGray);
        text_field.setFocusable(true);
        // text_field.addKeyListener(new keyReg());



//        buyPage = new JPanel();
//        buyPage.setVisible(true);
//        buyPage.setFocusable(true);
//        pane.add(name);
//        pane.add(popularity);
//        pane.add(price);
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

        int index = 0;
        Book temp = bookStore.getCurrentBookList().find(index++);

        while (temp != null) {
            BookContainer bookContainer = new BookContainer(temp);
            container.add(bookContainer);
            temp = bookStore.getCurrentBookList().find(index++);
        }
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