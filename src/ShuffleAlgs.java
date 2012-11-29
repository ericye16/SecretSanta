import java.util.HashSet;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: Eric
 * Date: 28/11/12
 * Time: 1:01 PM
 * To change this template use File | Settings | File Templates.
 */
public class ShuffleAlgs {
    /**
     * Shuffle algorithm as suggested by Mike Li and Andrew Tan
     * @param names the number of names
     * @return a list of indices for
     * which the ith person is the
     * giver of the person at position i
     */
    public static int[] liTanShuffle(int names, long seed) {
        Random r = new Random(seed);

        //A mapping so the ith person actually is the givers[i]th person
        int[] givers = new int[names];
        int[] invGivers = new int[names];
        int lastNum = 0;
        HashSet<Integer> used = new HashSet<Integer>();
        for (int i = 0; i < names; i++) {
            int next;
            next = r.nextInt(names);
            while (used.contains(next)) {
                next = r.nextInt(names);
            }
            if (next == names - 1) {
                lastNum = i;
            }
            invGivers[next] = i;
            used.add(next);
            givers[i] = next;
        }
        used.clear();

        //recipients of the gifts for the above
        boolean restartRecievers;
        int[] recievers;
        do {
            restartRecievers = false;
            recievers = new int[names];
            for (int i = 0; i < names; i++) {
                int next;
                next = r.nextInt(names);
                while (used.contains(next) || givers[i] == next) {
                    next = r.nextInt(names);
                    if (i == names - 1 && !used.contains(lastNum)) {
                        restartRecievers = true;
                        break;
                    }
                }
                used.add(next);
                recievers[i] = next;
            }
            used.clear();
        } while (restartRecievers);

        //finally, let's put them together so we get original -> recipients
        int[] result = new int[names];
        for (int i = 0; i < names; i++) {
            int next;
            next = r.nextInt(names);
            while (used.contains(next)) {
                next = r.nextInt(names);
            }
            used.add(next);
            result[i] = recievers[invGivers[i]];
        }
        used.clear();

        return result;
    }

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
            int lNext = 0;
            int rNext = 0;
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
