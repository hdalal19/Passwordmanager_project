package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

// Represents a password manager with a list of passwords
public class PasswordManager implements Writable {
    private final List<Password> passwords;


    // EFFECTS: constructs an empty list of passwords
    public PasswordManager() {
        this.passwords = new ArrayList<>();
    }

    // EFFECTS: gets the list of passwords in manager
    public List<Password> getPasswords() {
        return passwords;
    }

    // REQUIRES: password must be valid (Password must have at least 5 characters
    // including one uppercase letter, one lowercase letter, and one digit.")
    // MODIFIES: this
    // EFFECTS: adds a password object with a website, username and password, to a list of passwords in password manager
    public void addPassword(String website, String username, String password) {
        Password newPassword = new Password(website, username, password);
        this.passwords.add(newPassword);
        EventLog.getInstance().logEvent(new Event("Added password for Website: " + website + " & Username: "
                + username));
    }


    // REQUIRES: password must be valid (Password must have at least 5 characters
    // including one uppercase letter, one lowercase letter, and one digit.")
    // MODIFIES: this
    // EFFECTS: changes the password for a website to the new password
    public void updatePassword(String website, String newPass) {
        for (Password password : passwords) {
            if (password.getWebSite().equals(website)) {
                password.setPassword(newPass);
                EventLog.getInstance().logEvent(new Event("Updated password"));

            }
        }
    }


    // EFFECTS: shows the list of all passwords in the password manager
    public List<Password> viewPasswords() {
        EventLog.getInstance().logEvent(new Event("Viewed " + passwords.size()
                + " Passwords in Password Manager"));
        return passwords;
    }

    // EFFECTS: gets a password from the list of passwords
    public String viewOnePass(String web) {
        for (Password password : passwords) {
            if (web.equals(password.getWebSite())) {
                EventLog.getInstance().logEvent(new Event("Viewed password for : " + password.getWebSite()));
                return password.getPass();
            }
        }
        return null;
    }

    // MODIFIES: this
    // EFFECTS: deletes a password from the list of passwords in the Password Manager
    public void deletePassword(String website) {
        for (int index = 0; index < passwords.size(); index++) {
            if (passwords.get(index).getWebSite().equals(website)) {
                EventLog.getInstance().logEvent(new Event("Deleted password for website: " + website));
                passwords.remove(index);
                return;
            }
        }
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("passwords", passwordsToJson());
        return json;
    }

    // EFFECTS: returns passwords in the password manager as a JSON array
    private JSONArray passwordsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Password p : passwords) {
            jsonArray.put(p.toJson());
        }

        return jsonArray;
    }

}
