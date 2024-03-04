package kekolab.crc32;

import java.security.MessageDigestSpi;
import java.util.zip.CRC32;

public class CRC32MessageDigestSpi extends MessageDigestSpi {
    private CRC32 crc32;

    public CRC32MessageDigestSpi() {
        this.crc32 = new CRC32();
    }

    @Override
    protected byte[] engineDigest() {
        long value = crc32.getValue();
        return new byte[] { (byte) ((value & 0xFF000000) >> 24), (byte) ((value & 0x00FF0000) >> 16),
                (byte) ((value & 0x0000FF00) >> 8), (byte) (value & 0x000000FF) };
    }

    @Override
    protected void engineReset() {
        crc32.reset();
    }

    @Override
    protected void engineUpdate(byte b) {
        crc32.update(b);
    }

    @Override
    protected void engineUpdate(byte[] b, int off, int len) {
        crc32.update(b, off, len);
    }
}
