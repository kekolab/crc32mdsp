package kekolab.crc32;

import java.security.Provider;

public class CRC32Provider extends Provider {
    private static final long serialVersionUID = 4421238143582224193L;

    private static final String PROVIDER_NAME = "CRC32 Provider";
    private static final String PROVIDER_VERSION = "1.0";
    private static final String PROVIDER_INFO = "Implementation of CRC32 provider based on java.util.zip.CRC32";
    private static final String PROVIDER_KEY = "MessageDigest.CRC32";
    private static final String PROVIDER_VALUE = CRC32MessageDigestSpi.class.getName();    

    public CRC32Provider() {
        super(PROVIDER_NAME, PROVIDER_VERSION, PROVIDER_INFO);
        put(PROVIDER_KEY, PROVIDER_VALUE);
    }
}
