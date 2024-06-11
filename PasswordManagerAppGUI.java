package ui;

import model.EventLog;
import model.Password;
import model.PasswordManager;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import model.Event;

/// GUI for Password Manager
public class PasswordManagerAppGUI extends JFrame implements ActionListener {
    private PasswordManager passwordManager;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private static final String JSON_STORE = "./data/passwords.json";
    private final JPanel panel;
    private final ImageIcon icon;


    // MODIFIES: this
    // EFFECTS: runs the GUI by initializing JFrame
    public PasswordManagerAppGUI() {
        super("Password Manager");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        passwordManager = new PasswordManager();
        panel = new JPanel(new GridLayout(6, 1));

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(14, 14, 0, 14));
        setPreferredSize(new Dimension(500, 300));
        setLayout(new BorderLayout());

        setLocationRelativeTo(null);
        setVisible(true);

        ImageIcon icon1 = new ImageIcon("./data/passwordmanagericon.png");
        Image image = icon1.getImage();
        Image scaledImage = image.getScaledInstance(55, 55, Image.SCALE_SMOOTH);
        icon = new ImageIcon(scaledImage);
        JLabel iconLabel = new JLabel(icon);
        iconLabel.setSize(90, 90);
        add(iconLabel, BorderLayout.CENTER);


        runPasswordManagerGUI();
        last();

        add(panel, BorderLayout.SOUTH);
        pack();
    }


    // MODIFIES: this
    // EFFECTS: displays the main screen
    public void runPasswordManagerGUI() {

        JButton button1 = new JButton("Add Auto Generated Strong Password");
        button1.setActionCommand("Add Auto Generated Strong Password");
        button1.addActionListener(this);
        panel.add(button1);


        createButton("Add Password", "Remove Password");
        createButton("View all Passwords", "View one Password");
        createButton("Load Passwords from file", "Save Passwords to file");

    }

    // EFFECTS: creates a button with an ActionListener
    public void createButton(String input1, String input2) {
        JPanel jp = new JPanel(new GridLayout(1, 2));
        JButton button = new JButton(input1);
        button.setActionCommand(input1);
        button.addActionListener(this);
        jp.add(button);

        JButton button2 = new JButton(input2);
        button2.setActionCommand(input2);
        button2.addActionListener(this);
        jp.add(button2);
        panel.add(jp);
    }

    // Method to generate a random password
    private String generatePassword() {
        String uppercaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowercaseLetters = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";
        String symbols = "!@#$%^&*()-_=+";

        String allCharacters = uppercaseLetters + lowercaseLetters + numbers + symbols;

        Random rand = new Random();
        StringBuilder password = new StringBuilder();
        password.append(numbers.charAt(rand.nextInt(numbers.length())));
        for (int i = 0; i < 11; i++) {
            int randomIndex = rand.nextInt(allCharacters.length());
            password.append(allCharacters.charAt(randomIndex));
        }
        return password.toString();
    }


    // MODIFIES: this
    // EFFECTS: adds the password entered by user to password manager. If password already exits for website,
    // displays an error
    private void addYourPassword() {
        String webSite = JOptionPane.showInputDialog(this, "Enter Website", "");
        if (passExists(webSite)) {
            return;
        }
        String userName = JOptionPane.showInputDialog(this, "Enter Username", "");

        boolean validPassword = false;
        while (!validPassword) {
            String pass = JOptionPane.showInputDialog(this, "Enter Password", "");
            Password tempPassword = new Password(webSite, userName, pass);
            validPassword = tempPassword.isValidPassword();

            if (!validPassword) {
                JOptionPane.showMessageDialog(this,
                        "Password does not meet strength requirements. Please try again.",
                        "Invalid Password!", JOptionPane.ERROR_MESSAGE);
            } else {
                passwordManager.addPassword(webSite, userName, pass);
                JOptionPane.showMessageDialog(this, "Added password successfully for " + webSite);
                break;

            }
        }
    }

    private boolean passExists(String webSite) {
        List<Password> passwordList = passwordManager.getPasswords();
        for (Password p : passwordList) {
            if (p.getWebSite().equals(webSite)) {
                JOptionPane.showMessageDialog(this,
                        "A password for " + webSite + " already exists!!!", "Duplicate Password Entry!",
                        JOptionPane.ERROR_MESSAGE);
                return true;
            }
        }
        return false;
    }

    // MODIFIES: this
    // EFFECTS: adds a strong auto-generated password to password manager. If password already exits for website,
    // displays an error
    private void addAutoGeneratedPassword() {
        String webSite = JOptionPane.showInputDialog(this, "Enter Website", "");

        if (passExists(webSite)) {
            return;
        }

        String userName = JOptionPane.showInputDialog(this, "Enter Username", "");

        String generatedPass = generatePassword();
        int choice = JOptionPane.showConfirmDialog(this, "Set password: " + generatedPass + "?",
                "Confirm Password", JOptionPane.YES_NO_OPTION);

        if (choice == JOptionPane.YES_OPTION) {
            passwordManager.addPassword(webSite, userName, generatedPass);
            JOptionPane.showMessageDialog(this, "Added strong password successfully for " + webSite);
        } else {
            JOptionPane.showMessageDialog(this, "Password not set for " + webSite);
        }

    }


    // EFFECTS: displays the password for the website entered by user
    private void displayOnePass() {
        String website = JOptionPane.showInputDialog(this, "Enter Website for the password to be displayed",
                "");
        List<Password> passwordList = passwordManager.getPasswords();

        for (Password p : passwordList) {
            if (p.getWebSite().equals(website)) {
                passwordManager.viewOnePass(website);
                String pass = showPass(p);
                JOptionPane.showMessageDialog(this, pass);
                return;
            }
        }

        JOptionPane.showMessageDialog(this,
                "No passwords exist for " + website + "!", "No Password Entry found!!",
                JOptionPane.ERROR_MESSAGE);
    }

    // MODIFIES: this
    // EFFECTS: displays all the passwords inputted by the user in a new window
    private void showAllPasswords() {
        List<Password> passwordList = passwordManager.viewPasswords();
        JFrame passwordFrame = new JFrame("User's Passwords");
        passwordFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        passwordFrame.setSize(450, 400);
        JTextArea displayArea = new JTextArea(10, 30);

        displayArea.setText("Passwords:\n");
        for (Password p : passwordList) {
            displayArea.append(showPass(p) + "\n");
            passwordFrame.add(displayArea);
        }

        passwordFrame.setVisible(true);
    }

    // EFFECTS: creates a string of the password with username and website
    private String showPass(Password p) {
        return (" website : " + p.getWebSite() + ", " + "UserName : "
                + p.getUserName() + ", password : " + p.getPass());
    }


    // MODIFIES: this
    // EFFECTS: deletes the password corresponding to the website entered by user
    private void deletePass() {
        List<Password> passwordList = passwordManager.getPasswords();
        String website = JOptionPane.showInputDialog(this,
                "Enter Website of password to be removed", "");
        for (Password p : passwordList) {
            if (p.getWebSite().equals(website)) {
                passwordManager.deletePassword(website);
                JOptionPane.showMessageDialog(this, "Removed password successfully for " + website + "!");
                return;
            }
        }

        JOptionPane.showMessageDialog(this,
                "A password for " + website + " does not exist!", "No Password Entry!!",
                JOptionPane.ERROR_MESSAGE);
    }


    // EFFECTS: saves the passwords to a file
    private void savePass() {
        try {
            jsonWriter.open();
            jsonWriter.write(passwordManager);
            jsonWriter.close();
            JOptionPane.showMessageDialog(this,
                    "Passwords saved to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(this,
                    "Unable to save passwords to " + JSON_STORE);
        }
    }


    // EFFECTS: loads the passwords from the file
    private void loadPass() {
        try {
            passwordManager = jsonReader.read();
            JOptionPane.showMessageDialog(this,
                    "Passwords loaded from " + JSON_STORE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this,
                    "Unable to load Passwords from " + JSON_STORE);
        }
    }

    // EFFECTS: prints all the events that have been logged since the application started
    private void last() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            for (Event e : EventLog.getInstance()) {
                System.out.println(e.toString());
            }
        }));
    }

    // EFFECTS: action listener for all the buttons
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Add Password")) {
            addYourPassword();
        } else if (e.getActionCommand().equals("Add Auto Generated Strong Password")) {
            addAutoGeneratedPassword();
        } else if (e.getActionCommand().equals("View one Password")) {
            displayOnePass();
        } else if (e.getActionCommand().equals("View all Passwords")) {
            showAllPasswords();
        } else if (e.getActionCommand().equals("Remove Password")) {
            deletePass();
        } else if (e.getActionCommand().equals("Save Passwords to file")) {
            savePass();
        } else if (e.getActionCommand().equals("Load Passwords from file")) {
            loadPass();
        }

    }
}

