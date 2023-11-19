package GUI;

import Books.Book;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

public class BookContainer extends JTextArea implements MouseListener {
    Book book;
    private BtnState state;
    private Color defaultColor;
    private Color highlight, lightHighlight;
    private List<ActionListener> actionListeners;

    public BookContainer(Book book) {
        this.book = book;
        state = BtnState.NORMAL;
        this.setEditable(false);
        this.setLineWrap(true);
        this.setWrapStyleWord(true);
        this.addMouseListener(this);
        this.defaultColor = Color.WHITE;
        this.setBorder(new EmptyBorder(5, 10, 5, 10));
        this.setPreferredSize(new Dimension(100, 150));
        this.setFont(new Font("book Font", 10, 10));
        this.setText("\""+book.getName()+"\"" + "\n\n" + "Author: " + book.getAuthor()+"\nPrice ($): "+book.getPrice());
        highlight = new Color(122, 138, 153);
        lightHighlight = new Color(184, 207, 229);
        actionListeners = new ArrayList<>();
        this.addActionListener(new BookClickListener());
    }

    @Override
    public Color getSelectionColor() {
        return getBackground();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        setBackground(lightHighlight);
        state = BtnState.CLICKED;
        repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        for (ActionListener l : actionListeners) {
            l.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, this.getText()));
        }
        setBackground(defaultColor);
        state = BtnState.NORMAL;
        repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        state = BtnState.HOVERED;
        setBackground(lightHighlight);
        repaint();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        setBackground(defaultColor);
        state = BtnState.NORMAL;
        repaint();
    }


    public void addActionListener(ActionListener l) {
        actionListeners.add(l);
    }

    public java.util.List<ActionListener> getActionListeners() {
        return actionListeners;
    }

    private class BookClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(null, book);
        }
    }

    private enum BtnState {
        NORMAL,
        HOVERED,
        CLICKED;

    }
}
