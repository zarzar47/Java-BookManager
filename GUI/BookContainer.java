package GUI;

import Books.Book;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.ImageConsumer;
import java.awt.image.ImageObserver;
import java.awt.image.VolatileImage;
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
        actionListeners = new ArrayList<>();
        state = BtnState.NORMAL;
        this.book = book;
        this.setEditable(false);
        this.setLineWrap(true);
        this.setWrapStyleWord(true);
        this.setBorder(new LineBorder(new Color(76, 107, 124)));
        this.setPreferredSize(new Dimension(100, 100));
        this.setFont(new Font("book Font", Font.BOLD, 13));
        this.setText("  \""+book.getName()+"\"" + "\n  " + "Author: " + book.getAuthor()+"\n  Price ($): "+book.getPrice() + "\n Popularity: " + book.getPopularity());

        this.addMouseListener(this);
        this.addActionListener(new BookClickListener());

        defaultColor = new Color(200, 210, 250);
        this.setBackground(new Color(200, 210, 250));
        highlight = new Color(200, 210, 250);
        lightHighlight = new Color(250, 221, 121);
    }

    @Override
    public Color getSelectionColor() {
        return getBackground();
    }

    public Color getHighlight()
    {
        return highlight;
    }

    public void setHighlight(Color highlight)
    {
        this.highlight = highlight;
    }

    public Color getLightHighlight()
    {
        return lightHighlight;
    }

    public void setLightHighlight(Color lightHighlight)
    {
        this.lightHighlight = lightHighlight;
    }

    public Color getDefaultColor()
    {
        return defaultColor;
    }

    public void setDefaultColor(Color defaultColor)
    {
        this.defaultColor = defaultColor;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        setBackground(highlight);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        setBackground(highlight);
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
            Panel.pane.setSelectedIndex(Panel.pane.getTabCount()-1);
        }
    }

    private enum BtnState {
        NORMAL,
        HOVERED,
        CLICKED;
    }
}
