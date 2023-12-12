package GUI;
import DataStructures.UserHash;;
import User.User;
import DataStructures.intHashMap;
import Warehouse.BookStore;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.InputMismatchException;
import Books.Book;

public class LoginPanel extends JPanel {
    private Book currentBook;

    private final intHashMap<User> userCredentials;

    public LoginPanel(Book book) {
        userCredentials = UserHash.getUsers();
        currentBook = book;
        initializeUI();
    }

    private void initializeUI() {
        setLayout(new GridBagLayout());

        JPanel loginPanel = createLoginPanel();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10); // Add some padding

        add(loginPanel, gbc);
    }

    private JPanel createLoginPanel() {
        JPanel loginPanel = new JPanel(new GridLayout(4, 2));

        JLabel userLabel = new JLabel("Username:");
        JTextField userField = new JTextField();

        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField();

        JButton loginButton = new JButton("Login");
        loginButton.setBorder(new RoundedButton(10));
        JButton newUserButton = new JButton("New User");
        newUserButton.setBorder(new RoundedButton(10));

        loginPanel.add(userLabel);
        loginPanel.add(userField);
        loginPanel.add(passwordLabel);
        loginPanel.add(passwordField);
        loginPanel.add(loginButton);
        loginPanel.add(newUserButton);



        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int username = 0;
                try {
                    username = Integer.parseInt(userField.getText());
                } catch (InputMismatchException | NumberFormatException er) {
                    JOptionPane.showMessageDialog(LoginPanel.this,"Sorry only numbers allowed in username.");
                    return;
                }
                String password = new String(passwordField.getPassword());

                if (userCredentials.size() == 0){
                    JOptionPane.showMessageDialog(LoginPanel.this, "No users exist, please make an account first.");
                }else if (userCredentials.containsKey(username) && userCredentials.get(username).getPassword().equals(password) && userCredentials.get(username).hasBook(currentBook)) {
                    int owned = JOptionPane.showOptionDialog(LoginPanel.this, "User already has this book. Would you like to return this book?", "Book is Owned",
                            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
                    if (owned == 0)
                    {
                        String s = BookStore.getInstance().removeBook(username, currentBook.getISBN());
                        userCredentials.get(username).removeBook(currentBook);
                        JOptionPane.showMessageDialog(LoginPanel.this, s);
                    }
                } else if (userCredentials.containsKey(username) && userCredentials.get(username).getPassword().equals(password) && !userCredentials.get(username).hasBook(currentBook)) {
                    String s = BookStore.getInstance().buyBook(currentBook.getISBN(), username);
                    userCredentials.get(username).addBook(currentBook);
                    JOptionPane.showMessageDialog(LoginPanel.this, s);
                    loginButton.setEnabled(false);
                }  else {
                    JOptionPane.showMessageDialog(LoginPanel.this, "Invalid username or password. Try again.");
                }
            }
        });

        newUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = userCredentials.size();
                JLabel idLabel = new JLabel("Your ID: "+id);
                JLabel passLabel = new JLabel("Enter Password:");
                JTextField passField = new JTextField();

                JPanel detailPanel =new JPanel();
                detailPanel.setLayout(new BoxLayout(detailPanel,BoxLayout.Y_AXIS));
                detailPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
                detailPanel.add(idLabel);
                detailPanel.add(passLabel);
                detailPanel.add(passField);
                JButton done = new JButton("Confirm");
                final String[] password = {""};
                done.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (passwordStrong(passField.getText())) {
                            JOptionPane.showMessageDialog(null, "Your account has been made! Please login now");
                            password[0] = passField.getText();
                            done.setEnabled(false);
                        }
                        else JOptionPane.showMessageDialog(LoginPanel.this, "Please enter a strong password with at least one numeric, one lowercase and one upparcase character with minimum length 8");
                    }
                });

                detailPanel.add(done);
                JOptionPane.showOptionDialog(null,detailPanel,"Details",JOptionPane.DEFAULT_OPTION,JOptionPane.PLAIN_MESSAGE,null,new Object[]{},null);
                if (password[0] != null && passwordStrong(password[0])) {
                    userCredentials.put(id, new User(id, password[0]));
                    UserHash.getInstance().saveNewData();
                    BookStore.getInstance().addUser(new User(id, password[0]));
                }
            }
        });
        return loginPanel;
    }

    public boolean passwordStrong(String password){
        if (password.length() < 8 || password.length() > 24) return false;
        int lowercase = 0;
        int uppercase = 0;
        int number = 0;
        for (int i = 0; i < password.length(); i++) {
            if (password.charAt(i) >= 'A' && password.charAt(i) <= 'Z') uppercase++;
            else if (password.charAt(i) >= 'a' && password.charAt(i) <= 'z') lowercase++;
            else if (password.charAt(i) >= '0' && password.charAt(i) <= '9') number++;
        }
        if (lowercase > 0 && uppercase > 0 && number > 0) return true;
        return false;
    }
}
