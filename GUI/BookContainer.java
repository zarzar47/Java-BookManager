package GUI;

import Books.Book;

import javax.swing.*;
import javax.swing.border.LineBorder;
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

    public BookContainer(Book book)
    {
        this.book = book;
        state = BtnState.NORMAL;
        this.setEditable(false);
        this.setLineWrap(true);
        this.setWrapStyleWord(true);
        this.setBackground(defaultColor);
        this.setBorder(new LineBorder(new Color(76, 107, 124)));
        this.setPreferredSize(new Dimension(100, 100));
        this.setFont(new Font("book Font", Font.BOLD, 13));
        this.setText("  \""+book.getName()+"\"" + "\n  " + "Author: " + book.getAuthor()+"\n  Price ($): "+book.getPrice());
        this.addMouseListener(this);
        this.defaultColor = new Color(232, 241, 241);
        highlight = new Color(125, 129, 130);
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
        setBackground(highlight);
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
            Checkout.getInstance().setDetails(book);
            Panel.pane.setSelectedIndex(1);
        }
    }

    private enum BtnState {
        NORMAL,
        HOVERED,
        CLICKED;
    }
}
