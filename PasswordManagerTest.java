package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PasswordManagerTest {
    private PasswordManager passwordList;
    private Password testPassword1;
    private Password testPassword2;
    private Password testPassword3;
    private Password testPassword4;
    private Password testPassword5;
    private Password testPassword6;
    private Password testPassword7;
    private Password testPassword8;
    private Password testPassword9;
    private Password testPassword10;

    @BeforeEach
    void runBefore() {
        this.testPassword1 = new Password("Nike", "hrishd", "Jameson123@");
        this.testPassword2 = new Password("Gymshark", "hrishmdd", "J123@");
        this.testPassword3 = new Password("Youtube", "hrisehd", "Jame1");
        this.testPassword4 = new Password("Prime", "hrisrhd", "ja123");
        this.testPassword5 = new Password("Finance", "hrisrfrhd", "JAMES");
        this.testPassword6 = new Password("Fin", "hrifrf4srfrhd", "Jam2");
        this.testPassword7 = new Password("Fintech", "hrr4fisrfrr5hd", "@@@@#");
        this.testPassword8 = new Password("Fiance", "hrisrfrhd", "James");
        this.testPassword9 = new Password("Ptech", "hrr4fisrffrr5hd", "abcde");
        this.testPassword10 = new Password("Instacart", "hrisrfrddfhd", "12345");

    }

    @Test
    void testConstructor() {
        assertEquals("Nike", testPassword1.getWebSite());
        assertEquals("hrishd", testPassword1.getUserName());
        assertEquals("Jameson123@", testPassword1.getPass());
    }

    @Test
    void testIsValidPassword() {
        assertTrue(testPassword1.isValidPassword());
        assertFalse(testPassword2.isValidPassword());
        assertTrue(testPassword3.isValidPassword());
        assertFalse(testPassword4.isValidPassword());
        assertFalse(testPassword5.isValidPassword());
        assertFalse(testPassword6.isValidPassword());
        assertFalse(testPassword7.isValidPassword());
        assertFalse(testPassword8.isValidPassword());
        assertFalse(testPassword9.isValidPassword());
        assertFalse(testPassword10.isValidPassword());
    }

    @Test
    void testAddPassword() {
        this.passwordList = new PasswordManager();
        passwordList.addPassword("Adidas", "abcdef", "Far789#");
        assertEquals("Adidas", passwordList.viewPasswords().get(0).getWebSite());
        assertEquals("abcdef", passwordList.viewPasswords().get(0).getUserName());
        assertEquals("Far789#", passwordList.viewPasswords().get(0).getPass());

    }

    @Test
    void testUpdatePassword() {
        this.passwordList = new PasswordManager();
        passwordList.addPassword("Adidas", "abcdef", "Far789#");
        passwordList.addPassword("Amazon", "mello", "Ayne7890");
        passwordList.updatePassword("Amazon", "Abc123456789");
        assertEquals("Amazon", passwordList.viewPasswords().get(1).getWebSite());
        assertEquals("mello", passwordList.viewPasswords().get(1).getUserName());
        assertEquals("Abc123456789", passwordList.viewPasswords().get(1).getPass());


    }

    @Test
    void testViewPasswords() {
        this.passwordList = new PasswordManager();
        assertEquals(0, passwordList.viewPasswords().size());
        assertNull(passwordList.viewOnePass("abcd"));

        passwordList.addPassword("Adidas", "abcdef", "Far789#");
        passwordList.addPassword("Amazon", "melon", "Ayne7890");
        passwordList.addPassword("Flipkart", "defg", "vEdi8796");
        passwordList.addPassword("Sportchek", "meow", "Qwt09877890");
        assertNull(passwordList.viewOnePass("abcd"));
        assertEquals("Far789#", passwordList.viewOnePass("Adidas"));

        assertEquals(4, passwordList.viewPasswords().size());

        assertEquals("Sportchek", passwordList.viewPasswords().get(3).getWebSite());
        assertEquals("meow", passwordList.viewPasswords().get(3).getUserName());
        assertEquals("Qwt09877890", passwordList.viewPasswords().get(3).getPass());


    }

    @Test
    void testDeletePassword() {
        this.passwordList = new PasswordManager();
        passwordList.deletePassword("US Polo Assn");
        assertTrue(passwordList.viewPasswords().isEmpty());

        passwordList.addPassword("Adidas", "abcdef", "Far789#");

        passwordList.deletePassword("deal");
        assertEquals(1, passwordList.viewPasswords().size());
        assertEquals("Adidas", passwordList.viewPasswords().get(0).getWebSite());
        assertEquals("abcdef", passwordList.viewPasswords().get(0).getUserName());
        assertEquals("Far789#", passwordList.viewPasswords().get(0).getPass());

        passwordList.deletePassword("Adidas");
        assertEquals(0, passwordList.getPasswords().size());


        passwordList.addPassword("Adidas", "abcdef", "Far789#");
        passwordList.addPassword("Amazon", "mello", "Ayne7890");
        passwordList.addPassword("Flipkart", "ddme", "partB8796");
        passwordList.addPassword("Sportchek", "meow", "Def09877890");
        passwordList.deletePassword("Flipkart");
        passwordList.deletePassword("Adidas");

        assertEquals(2, passwordList.getPasswords().size());

        assertEquals("Amazon", passwordList.viewPasswords().get(0).getWebSite());
        assertEquals("mello", passwordList.viewPasswords().get(0).getUserName());
        assertEquals("Ayne7890", passwordList.viewPasswords().get(0).getPass());

        assertEquals("Sportchek", passwordList.viewPasswords().get(1).getWebSite());
        assertEquals("meow", passwordList.viewPasswords().get(1).getUserName());
        assertEquals("Def09877890", passwordList.viewPasswords().get(1).getPass());


    }


}