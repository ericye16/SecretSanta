import java.io.IOException;

/**
 * Eric
 * Date: 28/11/12
 * Time: 5:28 AM
 */
public class Shuffler {
    public static String[] names = {
            "Alice",
            "Bob",
            "Carla",
            "Daniel",
            "Elaine",
            "Frieda"
    };
    public static void main(String[] args) {
        long seed;
        try {
            seed = keypkg.readKey();
        } catch (IOException e) {
            System.err.println("Could not read/get key files.");
            e.printStackTrace();
            return;
        }

        int numPeople = names.length;
        int[] recipients = ShuffleAlgs.tanShuffle(numPeople, seed);

        for (int i = 0; i < numPeople; i++) {
            System.out.println(names[i] + " will give a gift to " + names[recipients[i]]);
        }
    }


}
