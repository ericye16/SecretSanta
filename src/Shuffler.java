import java.io.IOException;

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

        int numPeople = SendMailSSL.getNumNames();
        int[] recipients = ShuffleAlgs.tanShuffle(numPeople, seed);

        //To be implemented by Andrew:
        // SendMailSSL.sendEmails(recipients);

    }

}
