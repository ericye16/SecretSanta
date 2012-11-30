import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.security.SecureRandom;

/**
 * User: Eric
 * Date: 29/11/12
 * Time: 12:29 PM
 */
public class keypkg {
    public static long readKey() throws IOException {
        FileInputStream f1 = null;
        FileInputStream f2 = null;
        long seed;

        byte[] key1Bytes = new byte[16];
        byte[] key2Bytes = new byte[16];
        byte[] seedBytes = new byte[16];

        try {
            f1 = new FileInputStream("key.secret");
            f2 = new FileInputStream("key2.secret");

            f1.read(key1Bytes);
            f2.read(key2Bytes);

            for (int i = 0; i < seedBytes.length; i++) {
                seedBytes[i] = (byte) (key1Bytes[i] ^ key2Bytes[i]);
            }
            ByteBuffer br = ByteBuffer.wrap(seedBytes);
            seed = br.getLong();
        }
        finally {
            if (f1 != null) {
                f1.close();
            }
            if (f2 != null) {
                f2.close();
            }
        }

        return seed;
    }
    public static void genKey() throws IOException {
        byte[] key = new byte[16];
        FileOutputStream out = null;
        try {
            out = new FileOutputStream("key.secret");
            SecureRandom r = new SecureRandom();
            r.nextBytes(key);
            out.write(key);
        }
        finally {
            out.close();
        }
    }

    public static void main(String[] args) {
        try {
            genKey();
        } catch (IOException e) {
            System.err.println("Key generation failed.");
            e.printStackTrace();
            return;
        }
        System.err.println("Secret key generated at key.secret");
    }
}
