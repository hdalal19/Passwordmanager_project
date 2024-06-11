package persistence;

import model.Password;
import model.PasswordManager;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonWriterTest extends JsonTest {
    //NOTE TO CPSC 210 STUDENTS: the strategy in designing tests for the JsonWriter is to
    //write data to a file and then use the reader to read it back in and check that we
    //read in a copy of what was written out.

    @Test
    void testWriterInvalidFile() {
        try {
            PasswordManager pm = new PasswordManager();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            PasswordManager pm = new PasswordManager();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyPasswordManager.json");
            writer.open();
            writer.write(pm);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyPasswordManager.json");
            pm = reader.read();
            assertEquals(0, pm.viewPasswords().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            PasswordManager pm = new PasswordManager();
            pm.addPassword("Starbucks", "pbansa", "Bansa@9921");
            pm.addPassword("Walmart", "hdalal", "Hrish@9940");
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralPasswordManager.json");
            writer.open();
            writer.write(pm);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralPasswordManager.json");
            pm = reader.read();
            List<Password> passwords = pm.viewPasswords();
            assertEquals(2, passwords.size());
            checkPassword("Starbucks", "pbansa", "Bansa@9921", passwords.get(0));
            checkPassword("Walmart", "hdalal", "Hrish@9940", passwords.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
