package persistence;

import model.Password;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkPassword(String website, String username, String pass, Password password) {
        assertEquals(website, password.getWebSite());
        assertEquals(username, password.getUserName());
        assertEquals(pass, password.getPass());
    }
}
