package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents a password object with a website, username and password
public class Password implements Writable {
    private final String webSite;     // Website
    private final String userName;    // Username
    private String pass;             //  Password
    private boolean hasUpperCase;    //   At least one uppercase letter
    private boolean hasLowerCase;    //   At least one lowercase letter
    private boolean hasDigit;        //   At least one digit


    //EFFECTS: Constructs a password object with a website, username and password
    public Password(String webSite, String userName, String pass) {
        this.webSite = webSite;
        this.userName = userName;
        this.pass = pass;
    }

    public String getUserName() {
        return userName;
    }

    public String getWebSite() {
        return webSite;
    }

    public String getPass() {
        return pass;
    }

    // MODIFIES: this
    // EFFECTS: sets the password to the new password
    public void setPassword(String newPassword) {
        this.pass = newPassword;
    }

    // EFFECTS: Checks if the password meets the criteria for being valid (Password must have at least 5 characters
    // including one uppercase letter, one lowercase letter, and one digit.")
    public boolean isValidPassword() {

        if (pass.length() < 5) {    // Checks if the password has at least 5 characters
            return false;
        }
        for (char ch : pass.toCharArray()) {
            if (Character.isUpperCase(ch)) {         // Checks if the password has at least one uppercase letter
                hasUpperCase = true;
            } else if (Character.isLowerCase(ch)) {  // Checks if the password has at least one lowercase letter
                hasLowerCase = true;
            } else if (Character.isDigit(ch)) {      // Checks if the password has at least one digit
                hasDigit = true;
            }
        }
        return hasUpperCase && hasLowerCase && hasDigit;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("website", webSite);
        json.put("username", userName);
        json.put("password", pass);
        return json;
    }
}


