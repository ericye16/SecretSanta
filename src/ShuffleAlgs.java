import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Eric
 * Date: 28/11/12
 * Time: 1:01 PM
 */
public class ShuffleAlgs {
    public static Random random;

    /**
     * Shuffle algorithm as suggested by Andrew
     * Pick pairs in (giver, reciever) format.
     * Keep going until valid.
     *
     * @param names number of names
     * @return recievers for each giver
     */
    public static int[] tanShuffle(int names) {

        int[] recievers = new int[names];

        HashSet<Integer> lSide;
        HashSet<Integer> rSide;

        boolean restart;
        do {
            rSide = new HashSet<Integer>();
            lSide = new HashSet<Integer>();
            restart = false;
            int lNext;
            int rNext;
            for (int i = 0; i < names; i++) {
                do {
                    lNext = random.nextInt(names);
                } while (lSide.contains(lNext));
                lSide.add(lNext);
                if (i == names - 1 && !rSide.contains(lNext)) {
                    restart = true;
                    break;
                }
                do {
                    rNext = random.nextInt(names);
                } while (rSide.contains(rNext) || rNext == lNext);
                rSide.add(rNext);

                recievers[lNext] = rNext;
            }
        } while(restart);
        return recievers;
    }

    private static int[] addPerson(int[] prevReci, int numPeople,
                                   AtomicInteger oldChang, AtomicInteger newRec) {
        //Our examples: originally, Nancy gifts to Leslie, but with the addition of Kerui, it becomes
        //Nancy gifts to Kerui gifts to Leslie.

        int[] toBeReturned = new int[numPeople + 1];
        //In our example, this would be Nancy.
        int oldChanged = random.nextInt(numPeople);
        //in our example, this would be Leslie.
        int newGiver = prevReci[oldChanged];
        prevReci[oldChanged] = numPeople;

        for (int i = 0; i < numPeople; i++) {
            toBeReturned[i] = prevReci[i];
        }
        toBeReturned[numPeople] = newGiver;
        newRec.set(newGiver);
        oldChang.set(oldChanged);

        return toBeReturned;
    }

    public static int[] addPersonAndEmail(HashMap<Integer, String[]> namesAndEmails,
                                          String name, String email, int[] prevReci, AtomicInteger oldChan,
                                          AtomicInteger newRec) {
        int numPeopleFirst = namesAndEmails.size();
        namesAndEmails.put(numPeopleFirst, new String[] {name, email});
        return addPerson(prevReci, prevReci.length, oldChan, newRec);
    }

}