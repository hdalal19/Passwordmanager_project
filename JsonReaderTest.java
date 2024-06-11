package persistence;

import model.Password;
import model.PasswordManager;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            PasswordManager pm = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }
    

    @Test
    void testReaderEmptyPasswordManager() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyPasswordManager.json");
        try {
            PasswordManager pm = reader.read();
            assertEquals(0, pm.viewPasswords().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralPasswordManager() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralPasswordManager.json");
        try {
            PasswordManager pm = reader.read();
            List<Password> passwords = pm.viewPasswords();
            assertEquals(2, passwords.size());
            checkPassword("Starbucks", "pbansa", "Bansa@9921", passwords.get(0));
            checkPassword("Walmart", "hdalal", "Hrish@9940", passwords.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}