package ui;

import model.Password;
import model.PasswordManager;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Password Manager Application
public class PasswordManagerApp {
    private PasswordManager passwordManager;
    private final Scanner input;
    private static final String JSON_STORE = "./data/passwords.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: initializes password manager and runs the password manager application
    public PasswordManagerApp() throws FileNotFoundException {
        passwordManager = new PasswordManager();
        input = new Scanner(System.in);
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runPasswordManager();
    }


    // EFFECTS: runs the password manager and determines whether to continue processing or exit
    private void runPasswordManager() {
        boolean keepGoing = true;
        while (keepGoing) {
            keepGoing = isKeepGoing();
        }
        System.out.println("Exiting Password Manager. Goodbye!");
    }

    // EFFECTS: displays a menu of options to user, processes user input and command
    private boolean isKeepGoing() {
        String command;
        displayMenu();
        command = input.next().toLowerCase();
        if (command.equals("1")) {
            addPassword();
        } else if (command.equals("2")) {
            updatePassword();
        } else if (command.equals("3")) {
            viewPasswords();
        } else if (command.equals("4")) {
            deletePassword();
        } else if (command.equals("5")) {
            return false;
        } else if (command.equals("6")) {
            savePasswords();
        } else if (command.equals("7")) {
            loadPasswords();
        } else {
            System.out.println("Invalid option. Please try again.");
        }
        return true;
    }


    // EFFECTS: displays menu of options to the user
    private void displayMenu() {
        System.out.println("\nSelect an option:");
        System.out.println("\t1 -> Add Password");
        System.out.println("\t2 -> Update Password");
        System.out.println("\t3 -> View All Passwords");
        System.out.println("\t4 -> Delete Password");
        System.out.println("\t5 -> Quit");
        System.out.println("\t6 -> Save passwords to file");
        System.out.println("\t7 -> Load passwords from file");
        System.out.print("Enter your choice: ");
    }


    // MODIFIES: this
    // EFFECTS: adds a password to the password manager if it is valid
    private void addPassword() {
        System.out.print("Enter Website: ");
        String website = input.next();
        System.out.print("Enter Username: ");
        String username = input.next();
        String password;
        boolean validPassword = false;
        while (!validPassword) {
            System.out.print("Enter Password: ");
            password = input.next();

            Password tempPassword = new Password(website, username, password);
            validPassword = tempPassword.isValidPassword();

            if (!validPassword) {
                System.out.println("Invalid password.");
            } else {
                passwordManager.addPassword(website, username, password);
                System.out.println("Password added successfully!!");
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: changes the password for a website to the new valid password
    @SuppressWarnings("methodlength") // Signed by Nanjou
    private void updatePassword() {
        if (passwordManager.viewPasswords().isEmpty()) {
            System.out.println("Password manager is Empty!");
        } else {
            System.out.print("Website to update: ");
            String websiteToUpdate = input.next();

            for (int index = 0; index < passwordManager.viewPasswords().size(); index++) {
                if (passwordManager.viewPasswords().get(index).getWebSite().equals(websiteToUpdate)) {
                    String newPass;
                    boolean validPassword = false;
                    while (!validPassword) {
                        System.out.print("Enter new password: ");
                        newPass = input.next();

                        Password tempPassword = new Password(websiteToUpdate, "", newPass);
                        validPassword = tempPassword.isValidPassword();

                        if (!validPassword) {
                            System.out.println("Invalid password.");
                        } else {
                            passwordManager.updatePassword(websiteToUpdate, newPass);
                            System.out.println("Password updated successfully!");
                            return;
                        }
                    }
                }
            }
            System.out.println("Website not found.");
        }
    }


    // EFFECTS: displays the list of all the passwords in the password manager
    private void viewPasswords() {
        List<Password> passwordList = passwordManager.viewPasswords();
        if (passwordList.isEmpty()) {
            System.out.println("Password manager is Empty!");
        } else {
            System.out.println("Your Passwords:");
            for (Password password : passwordList) {
                System.out.println("Website: " + password.getWebSite() + ", Username: "
                        + password.getUserName() + ", Password: " + password.getPass());
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: deletes a password from the password manager
    private void deletePassword() {
        List<Password> passwordList = passwordManager.viewPasswords();
        if (passwordList.isEmpty()) {
            System.out.println("There are no passwords to delete!");
        } else {
            System.out.print("Enter website name to delete password: ");
            String websiteToDelete = input.next();

            for (int index = 0; index < passwordList.size(); index++) {
                if (passwordList.get(index).getWebSite().equals(websiteToDelete)) {
                    passwordManager.deletePassword(websiteToDelete);
                    System.out.println("Password deleted successfully!");
                    return;

                }
            }
            System.out.println("Website not found!!");
        }
    }

    // EFFECTS: saves the password to file
    private void savePasswords() {
        try {
            jsonWriter.open();
            jsonWriter.write(passwordManager);
            jsonWriter.close();
            System.out.println("Saved " + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads password from file
    private void loadPasswords() {
        try {
            passwordManager = jsonReader.read();
            System.out.println("Loaded " + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}

