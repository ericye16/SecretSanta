import java.util.HashSet;
import java.util.Random;

/**
 * Eric
 * Date: 28/11/12
 * Time: 1:01 PM
 */
public class ShuffleAlgs {

    /**
     * Shuffle algorithm as suggested by Andrew
     * Pick pairs in (giver, reciever) format.
     * Keep going until valid.
     * @param names number of names
     * @param seed seed for the PRNG
     * @return recievers for each giver
     */
    public static int[] tanShuffle(int names, long seed) {
        Random r = new Random(seed);

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
                    lNext = r.nextInt(names);
                } while (lSide.contains(lNext));
                lSide.add(lNext);
                if (i == names - 1 && !rSide.contains(lNext)) {
                    restart = true;
                    break;
                }
                do {
                    rNext = r.nextInt(names);
                } while (rSide.contains(rNext) || rNext == lNext);
                rSide.add(rNext);

                recievers[lNext] = rNext;
            }
        } while(restart);
        return recievers;
    }
}