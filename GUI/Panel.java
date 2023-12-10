package GUI;

import Books.Book;
import DataStructures.DynamicArray;
import Warehouse.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Panel extends java.awt.Panel {

    JTextField text_field;
    JButton searchButton;
    JButton genreFilter;
    JPopupMenu genreList;
    static JPanel container;
    JScrollPane scrollPane;
    JToggleButton fullName;
    boolean searchSpecific = false;
    String genreName;
    JLabel genreLabel;
    String authorName;
    public static JTabbedPane pane;
    public JCheckBox name, price, popularity, ascending, descending;
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
        catalogue.add(fullName);
        catalogue.add(genreFilter);
        catalogue.add(genreLabel);
        catalogue.add(scrollPane);
        catalogue.add(sortBy);
        catalogue.setBackground(new Color(229, 146, 190));

        this.setBackground(new Color(166, 17, 99));

        catalogue.add(name);
        catalogue.add(price);
        catalogue.add(popularity);
        catalogue.add(ascending);
        catalogue.add(descending);
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
        genreLabel = new JLabel(genreName);
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
        name.setSelected(true);
        name.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                name.setSelected(true);
                price.setSelected(false);
                popularity.setSelected(false);
                performSearch();
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
                price.setSelected(true);
                name.setSelected(false);
                popularity.setSelected(false);
                performSearch();
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
                popularity.setSelected(true);
                name.setSelected(false);
                price.setSelected(false);
                performSearch();
            }
        });

        ascending = new JCheckBox("ascending");
        // popularity.setPreferredSize(new Dimension(75, 20));
        ascending.setBackground(Color.LIGHT_GRAY);
        ascending.setBounds(200, 75, 10, 10);
        ascending.setSelected(true);
        ascending.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                ascending.setSelected(true);
                descending.setSelected(false);
                performSearch();
            }
        });

        descending = new JCheckBox("descending");
        // popularity.setPreferredSize(new Dimension(75, 20));
        descending.setBackground(Color.LIGHT_GRAY);
        descending.setBounds(200, 125, 10, 10);
        descending.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                descending.setSelected(true);
                ascending.setSelected(false);
                performSearch();
            }
        });

        sortBy = new JTextField();
        sortBy.setPreferredSize(new Dimension(50, 20));
        sortBy.setEditable(false);
        sortBy.setText("Sort By: ");
        sortBy.setBackground(Color.lightGray);
        text_field.setFocusable(true);
        // text_field.addKeyListener(new keyReg());


        fullName = new JCheckBox();
        fullName.setText("Specific");
        fullName.setBackground(Color.lightGray);
        fullName.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                searchSpecific = !searchSpecific;
            }
        });
    }

    private void showGenrePopup() {
        genreList = new JPopupMenu("Genres");
        String[] tempgenres = BookStore.getInstance().getGenres();
        String[] genres = new String[tempgenres.length+1];

        for (int i = 0; i < tempgenres.length; i++) {
            genres[i] = tempgenres[i];
        }
        genres[genres.length-1] = "All";

        for (int i = 0; i < genres.length; i++) {
            JMenuItem menuItem = new JMenuItem(genres[i]);
            menuItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Handle the selected genre
                    genreName = menuItem.getText();
                    System.out.println("Selected Genre: " + genreName);
                    genreLabel.setText(genreName);
                }
            });
            genreList.add(menuItem);
        }

        // Show the popup menu below the genreFilter button
        genreList.show(genreFilter, 0, genreFilter.getHeight());
    }

    public void performSearch()
    {
        String bookName = text_field.getText();
        BookStore bookStore = BookStore.getInstance();
        bookStore.updateList(bookName, genreName, searchSpecific);
        // container.removeAll();
//         if (name.isSelected())
//             bookStore.ascSortByName();
//         else if (price.isSelected())
//             bookStore.ascSortByPrice();
//         else if (popularity.isSelected())
//             bookStore.ascSortByPopularity();
//
//         container.removeAll();
//
//        int index = 0;
//        Book temp = bookStore.getCurrentBookList().find(index++);
//        DynamicArray<Book> books = bookStore.getCurrentBookList();
//
//        while (index < books.getSize() && temp != null)
//        {
//            System.out.println(books.getSize() + " " + index);
//            BookContainer bookContainer = new BookContainer(temp);
//            container.add(bookContainer);
//            temp = books.find(index++);
//        }

        if (ascending.isSelected() && !searchSpecific)
            bookStore.setAscending(true);
        else
            bookStore.setAscending(false);

        if (name.isSelected() && !searchSpecific)
               bookStore.ascSortByName();
        else if (price.isSelected() && !searchSpecific)
            bookStore.ascSortByPrice();
        else if (popularity.isSelected() && !searchSpecific)
            bookStore.ascSortByPopularity();
        container.removeAll();

        Object[] temp = bookStore.getCurrentBookList().toArray();
        System.out.println(temp.length);
        for (Object o : temp)
        {
            BookContainer bookContainer = new BookContainer((Book) o);
            container.add(bookContainer);
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