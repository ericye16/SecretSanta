import java.io.IOException;
import java.util.HashMap;

/**
 * Eric
 * Date: 28/11/12
 * Time: 5:28 AM
 */
public class Shuffler {

    public static void main(String[] args)throws IOException {
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
        System.out.println("(e)mail or simply (v)iew?");
        switch (System.in.read()) {
            case 'e':
                SendMailSSL.sendEmails(recipients, namesAndEmails);
                break;
            case 'v':
                showGiversAndRecipients(recipients, namesAndEmails);
                break;
            default:
                System.err.println("Invalid choice; exiting.");
        }
    }

    private static void showGiversAndRecipients(int[] recipients, HashMap<Integer, String[]> namesAndEmails) {
        for (int i = 0; i < recipients.length; i++) {
            System.out.format("%s is giving a gift to %s.\n",
                    namesAndEmails.get(i)[0], namesAndEmails.get(recipients[i])[0]);
        }
    }

}
