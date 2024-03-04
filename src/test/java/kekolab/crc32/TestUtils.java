package kekolab.crc32;

public class TestUtils {
    static String toHexString(byte[] result) {
        StringBuilder s = new StringBuilder();
        for (byte b : result) 
            s.append(String.format("%02X", b));
        return s.toString();
    }    
}
