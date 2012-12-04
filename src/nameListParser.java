import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

/**
 * User: Eric
 * Date: 03/12/12
 * Time: 2:37 PM
 */

/**
 * Reads the csv into a hashmap between an integer, the person ID, and an array of length 2 which will contain
 * name, email address
 */
public class nameListParser {
    public static HashMap<Integer, String[]> getNamesAndEmails() throws IOException {
        HashMap<Integer, String[]> namesAndEmails = new HashMap<Integer, String[]>();
        int lineNum = 0;
        String name;
        String email;
        String line;
        int commaPlace;
        BufferedReader i = null;
        try {
            i = new BufferedReader(new FileReader("secretsanta.csv"));

            while ((line = i.readLine()) != null) {
                commaPlace = line.indexOf(",");
                name = line.substring(0, commaPlace).trim();
                email = line.substring(commaPlace + 1).trim();
                namesAndEmails.put(lineNum, new String[] {name, email});
                lineNum++;
            }
        }
        finally {
            if (i != null) {
            i.close();
            }
        }

        return namesAndEmails;
    }
}
