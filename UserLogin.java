package ui;

import model.Event;
import model.EventLog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class UserLogin extends JFrame implements ActionListener, KeyListener {

    private static JTextField userText;
    private static JPasswordField passwordText;

    // Runs user login app
    // EFFECTS: Initializes and displays the user login interface
    public UserLogin() {
        super("User Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = getPanel();

        add(panel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        userText.addKeyListener(this);
        passwordText.addKeyListener(this);
    }

    // EFFECTS: Constructs and returns a panel with username and password fields
    private JPanel getPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        userName(panel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel passwordLabel = new JLabel("Password");
        panel.add(passwordLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        passwordText = new JPasswordField();
        panel.add(passwordText, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        JButton button = new JButton("Login");
        button.addActionListener(this);
        panel.add(button, gbc);
        return panel;
    }

    // EFFECTS: Adds username label and text field to the panel
    private static void userName(JPanel panel, GridBagConstraints gbc) {
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel userLabel = new JLabel("Username");
        panel.add(userLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        userText = new JTextField(20);
        panel.add(userText, gbc);
    }

    // MODIFIES: this
    // EFFECTS: Performs actions upon the 'Login' button press event, validates login credentials,
    //          and either opens the PasswordManagerAppGUI or displays an error message.
    @Override
    public void actionPerformed(ActionEvent e) {
        char[] passwordChars = passwordText.getPassword();
        String password = new String(passwordChars);
        String user = userText.getText();

        if (user.equals("hdalal") && password.equals("Hdalal@1918")) {
            new PasswordManagerAppGUI();
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Invalid login credentials");
        }
    }

    // MODIFIES: this
    // EFFECTS: Performs actions upon the 'Enter' key press event in text fields,
    //          validates login credentials, and either opens the PasswordManagerAppGUI
    //          or displays an error message.
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            char[] passwordChars = passwordText.getPassword();
            String password = new String(passwordChars);
            String user = userText.getText();

            if (user.equals("hdalal") && password.equals("Hdalal@1918")) {
                EventLog.getInstance().logEvent(new Event("User logged in"));
                new PasswordManagerAppGUI();
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid login credentials");
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}

