package kekolab.crc32;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CRC32SecurityProviderTest {
    private MessageDigest digest;

    @BeforeAll
    public static void addToSecurityProvider() {
        Security.addProvider(new CRC32Provider());
    }

    @BeforeEach
    public void getDigest() throws NoSuchAlgorithmException {
        digest = MessageDigest.getInstance("CRC32");
    }

    @Test
    public void empty() {
        digest.update(new byte[0]);
        byte[] result = digest.digest();
        assertEquals("00000000", TestUtils.toHexString(result));
    }

    @Test
    public void lowercaseQwerty() {
        String qwerty = "qwerty";
        digest.update(qwerty.getBytes());
        byte[] result = digest.digest();
        assertEquals("03498D7D", TestUtils.toHexString(result));
    }

    @Test
    public void reset() {
        String qwerty = "qwerty";
        digest.update(qwerty.getBytes());
        byte[] result = digest.digest();
        assertEquals("03498D7D", TestUtils.toHexString(result));

        digest.update(qwerty.getBytes());
        result = digest.digest();
        assertNotEquals("03498D7D", TestUtils.toHexString(result));
        
        digest.reset();
        digest.update(qwerty.getBytes());
        result = digest.digest();
        assertEquals("03498D7D", TestUtils.toHexString(result));
    }

    @Test
    public void uppercaseQwerty() {
        String qwerty = "QWERTY";
        digest.update(qwerty.getBytes());
        byte[] result = digest.digest();
        assertEquals("F3B14AFB", TestUtils.toHexString(result));
    }
}
