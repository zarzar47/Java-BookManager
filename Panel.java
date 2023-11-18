
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class Panel extends java.awt.Panel {

    JTextField text_field;
    JButton searchButton;
    public Panel () {
        Initiation();
        this.setPreferredSize(new Dimension(Frame.WIDTH,Frame.HEIGHT));
        this.setBackground(Color.lightGray);
        this.setVisible(true);
        this.setFocusable(true);
        this.add(text_field);
        this.add(searchButton);
    }

    public void Initiation(){
        text_field = new JTextField();
        text_field.setPreferredSize(new Dimension(300,20));
        text_field.setName("Enter Book Name");
        text_field.setText("PLEADING GUILTY");
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

    public void performSearch(){
        String bookName = text_field.getText();
        BookStore bookStore = BookStore.getInstance();
        BookContainer bookContainer = new BookContainer(bookStore.getBookByName(bookName));

        this.add(bookContainer);

        revalidate();
        repaint();
    }
}