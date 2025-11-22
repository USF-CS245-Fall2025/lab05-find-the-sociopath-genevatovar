import java.util.List;

public class Sociopath {
    /**
     * @param groupSize - the number of people in the group 1, 2, 3, ... n (0 is not a person)!
     * @param likeList - represents people liking each other i.e. [1, 7] means person 1 likes person 7 but it is not reciprocal
     * @return the number of the sociopath. if the sociopath is not found, return -1. (everyone likes the sociopath, but the sociopath likes no-one.)
     */
    public int findTheSociopath (int groupSize, List<int []> likeList) {
        // Use 2 arrays, one incoming, one outgoing
        int[] incomingLikes = new int[groupSize + 1];
        int[] outgoingLikes = new int[groupSize + 1];

        // Treat the likeList elements as a set of pairs to process relationships
        for (int i = 0; i < likeList.size(); i++) {
            int[] pair = likeList.get(i);
            int liker = pair[0];
            int liked = pair[1];

            outgoingLikes[liker]++; // increment outgoing for the liker
            incomingLikes[liked]++; // increment incoming for the liked
        }

        // Find sociopath (find person with groupsize - 1 incoming likes and 0 outgoing likes)
        for (int person = 1; person <= groupSize; person++) {
            if (incomingLikes[person] == groupSize - 1 && outgoingLikes[person] == 0) {
                return person;
            }
        }
        return -1; // sociopath not found, doesnt exist
    }

    // Test method
    public static void main(String[] args) {
        Sociopath finder = new Sociopath();

        // Test 1 -- Person 3 is the sociopath
        // Everyone (1, 2, 4) likes person 3, but person 3 likes no one
        List<int[]> test1 = java.util.Arrays.asList(
                new int[]{1, 3},
                new int[]{2, 3},
                new int[]{4, 3},
                new int[]{1, 2},
                new int[]{2, 4}
        );
        System.out.println("Test 1 -- Expected: 3, Got: " + finder.findTheSociopath(4, test1));
        // Test 2 -- No sociopath
        List<int[]> test2 = java.util.Arrays.asList(
                new int[]{1, 2},
                new int[]{2, 3},
                new int[]{3, 1}
        );
        System.out.println("\nTest 2 -- Expected: -1, Got: " + finder.findTheSociopath(3, test2));
        // Test 3 -- Person 2 is the sociopath in a group of 2
        List<int[]> test3 = java.util.Arrays.asList(
                new int[]{1, 2}
        );
        System.out.println("\nTest 3 -- Expected: 2, Got: " + finder.findTheSociopath(2, test3));
    }
}