import java.io.IOException;
import java.util.HashMap;

/**
 * Eric
 * Date: 28/11/12
 * Time: 5:28 AM
 */
public class Shuffler {

    public static void main(String[] args) throws IOException {
        long seed;
        try {
            seed = keypkg.readKey();
        } catch (IOException e) {
            System.err.println("Could not read/get key files.");
            e.printStackTrace();
            return;
        }
        System.err.println("Seed used is: " + seed);

        HashMap<Integer, String[]> namesAndEmails = nameListParser.getNamesAndEmails();
        int numPeople = namesAndEmails.size();
        int[] recipients = ShuffleAlgs.tanShuffle(numPeople, seed);

        //To be implemented by Andrew:
        SendMailSSL.sendEmails(recipients, namesAndEmails);

    }

}
