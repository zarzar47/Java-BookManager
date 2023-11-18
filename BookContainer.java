import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BookContainer extends JButton {
    Book book;
    public BookContainer(Book book){
        this.book=book;
        this.setPreferredSize(new Dimension(400,100));
        this.setText("<html><center>" + book.getName() + "<br>" + book.getAuthor() + "</center></html>");
        this.addActionListener(new BookClickListener());
    }
    private class BookClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(null, "Clicked on " + book.getName());
        }
    }
}
