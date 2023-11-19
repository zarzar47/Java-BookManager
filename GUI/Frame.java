package GUI;

import Books.FileReading;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {
    public static int WIDTH = 500;
    public static int HEIGHT = 600;
    public static void main(String[] args) {
        new FileReading();
        JFrame frame = new JFrame();
        frame.setTitle("Book Store");
        frame.add(new Panel());
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setFocusable(true);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setSize(WIDTH, HEIGHT);
        frame.setBackground(Color.lightGray);
    }
}
