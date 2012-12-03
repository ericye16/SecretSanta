import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * User: Eric
 * Date: 03/12/12
 * Time: 2:37 PM
 */
public class nameListParser {
    public static int getNumNames() throws IOException {
        int numNames = 0;
        BufferedReader i = null;
        try {
            i = new BufferedReader(new FileReader("secretsanta.secret"));
        }
        finally {
            i.close();
        }

        while (i.readLine() != null) {
            numNames++;
        }

        return numNames;
    }
}
