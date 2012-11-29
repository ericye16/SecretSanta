
/**
 * Created with IntelliJ IDEA.
 * User: Eric
 * Date: 28/11/12
 * Time: 5:28 AM
 * To change this template use File | Settings | File Templates.
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
        long seed = 0; // Found through external means--keep secret!
                        //to keep it simple, it's 32-signed-bits long

        int numPeople = names.length;
        int[] recipients = ShuffleAlgs.tanShuffle(numPeople, seed);

        for (int i = 0; i < numPeople; i++) {
            System.out.println(names[i] + " will give a gift to " + names[recipients[i]]);
        }
    }


}
