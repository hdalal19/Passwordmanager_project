package persistence;

import model.Event;
import model.EventLog;
import model.PasswordManager;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads password manager from JSON data stored in file
public class JsonReader {
    private final String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads password manager from file and returns it;
    // throws IOException if an error occurs reading data from file
    public PasswordManager read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        EventLog.getInstance().logEvent(new Event("Loaded passwords"));
        return parsePasswordManager(jsonObject);

    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses password manager from JSON object and returns it
    private PasswordManager parsePasswordManager(JSONObject jsonObject) {
        PasswordManager passwordManager = new PasswordManager();
        addPasswords(passwordManager, jsonObject);
        return passwordManager;
    }

    // MODIFIES: passwordManager
    // EFFECTS: parses passwords from JSON object and adds them to password manager
    private void addPasswords(PasswordManager passwordManager, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("passwords");
        for (Object json : jsonArray) {
            JSONObject nextPassword = (JSONObject) json;
            addAPassword(passwordManager, nextPassword);
        }
    }

    // MODIFIES: pm
    // EFFECTS: parses a password from JSON object and adds it to password manager
    private void addAPassword(PasswordManager pm, JSONObject jsonObject) {
        String webSite = jsonObject.getString("website");
        String userName = jsonObject.getString("username");
        String pass = jsonObject.getString("password");
        pm.addPassword(webSite, userName, pass);
    }
}
