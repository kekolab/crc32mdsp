package kekolab.crc32;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CRC32MessageDigestTest {
    private CRC32MessageDigestSpi digest;

    @BeforeEach
    public void initDigest() {
        digest = new CRC32MessageDigestSpi();
    }

    @Test
    public void empty() {
        digest.engineUpdate(new byte[0], 0, 0);
        byte[] result = digest.engineDigest();
        assertEquals("00000000", TestUtils.toHexString(result));
    }

    @Test
    public void lowercaseQwerty() {
        String qwerty = "qwerty";
        digest.engineUpdate(qwerty.getBytes(), 0, qwerty.length());
        byte[] result = digest.engineDigest();
        assertEquals("03498D7D", TestUtils.toHexString(result));
    }

    @Test
    public void reset() {
        String qwerty = "qwerty";
        digest.engineUpdate(qwerty.getBytes(), 0, qwerty.length());
        byte[] result = digest.engineDigest();
        assertEquals("03498D7D", TestUtils.toHexString(result));

        digest.engineUpdate(qwerty.getBytes(), 0, qwerty.length());
        result = digest.engineDigest();
        assertNotEquals("03498D7D", TestUtils.toHexString(result));
        
        digest.engineReset();
        digest.engineUpdate(qwerty.getBytes(), 0, qwerty.length());
        result = digest.engineDigest();
        assertEquals("03498D7D", TestUtils.toHexString(result));
    }

    @Test
    public void uppercaseQwerty() {
        String qwerty = "QWERTY";
        digest.engineUpdate(qwerty.getBytes(), 0, qwerty.length());
        byte[] result = digest.engineDigest();
        assertEquals("F3B14AFB", TestUtils.toHexString(result));
    }
}
