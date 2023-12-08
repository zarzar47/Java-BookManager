package GUI;
import DataStructures.UserHash;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;
import java.util.InputMismatchException;
import java.util.Random;

public class LoginPanel extends JPanel {


    private final Hashtable<Integer, String> userCredentials;

    public LoginPanel() {
        userCredentials = UserHash.getInstance().getUserCredentials();
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
        JButton newUserButton = new JButton("New User");

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

                if (userCredentials.containsKey(username) && userCredentials.get(username).equals(password)) {
                    JOptionPane.showMessageDialog(LoginPanel.this, "Login successful!");
                } else {
                    JOptionPane.showMessageDialog(LoginPanel.this, "Invalid username or password. Try again.");
                }
            }
        });

        newUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String password = JOptionPane.showInputDialog(LoginPanel.this, "Enter new password:");

                if (password != null) {
                    int id = userCredentials.size();
                    userCredentials.put(id, password);
                    JOptionPane.showMessageDialog(LoginPanel.this, "New user created successfully! Your ID is:"+id);
                    UserHash.getInstance().saveNewData(id,password);
                }
            }
        });

        return loginPanel;
    }
}
