import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

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
        ShuffleAlgs.random = new Random(seed);
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        Boolean exit = false;
        HashMap<Integer, Integer> numSubTours;

        int[] recipients = ShuffleAlgs.tanShuffle(numPeople);
        do {
            System.out.println("(c)hange, (q)uit, check (s)ubgraphs, (e)mail or simply (v)iew? ");
            switch (in.readLine().charAt(0)) {
                case 'e':
                    SendMailSSL.sendEmails(recipients, namesAndEmails);
                    exit = true;
                    break;
                case 'v':
                    showGiversAndRecipients(recipients, namesAndEmails);
                    exit = false;
                    break;
                case 'q':
                    exit = true;
                    break;
                case 'c':
                    AtomicInteger oldChang = new AtomicInteger();
                    AtomicInteger newRec = new AtomicInteger();
                    String[] newNameAndEmail = askForNewNameAndEmail();
                    recipients = ShuffleAlgs.addPersonAndEmail(namesAndEmails,
                            newNameAndEmail[0], newNameAndEmail[1], recipients, oldChang, newRec);
                    System.out.println("(e)mail or (n)ot? ");
                    if (in.readLine().charAt(0) == 'e') {
                        SendMailSSL.sendChangeEmail(namesAndEmails, oldChang, newRec);
                        numPeople = recipients.length;
                    }
                    exit = false;
                    break;
                case 's':
                    numSubTours = Graphing.getSubGraphs(recipients);
                    for (Map.Entry<Integer, Integer> entry : numSubTours.entrySet()) {
                         System.out.println(entry.getKey() + ": " + entry.getValue());
                    }
                    exit = false;
                    break;
                default:
                    System.err.println("Invalid choice.");
                    exit = false;
                    break;

            }
        } while (!exit);
    }

    private static void showGiversAndRecipients(int[] recipients, HashMap<Integer, String[]> namesAndEmails) {
        for (int i = 0; i < recipients.length; i++) {
            System.out.format("%s is giving a gift to %s.\n",
                    namesAndEmails.get(i)[0], namesAndEmails.get(recipients[i])[0]);
        }
    }

    private static String[] askForNewNameAndEmail() {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] toBeReturned = new String[2];
        try {
            System.out.println("Enter their name");
            toBeReturned[0] = in.readLine();
            System.out.println("Enter their email address");
            toBeReturned[1] = in.readLine();
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return toBeReturned;

    }

}
