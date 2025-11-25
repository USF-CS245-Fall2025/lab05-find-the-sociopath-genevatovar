import java.util.List;

public class Sociopath {
    private boolean sociopathFound; // Track if sociopath was found
    /**
     * @param groupSize - the number of people in the group 1, 2, 3, ... n (0 is not a person)!
     * @param likeList - represents people liking each other i.e. [1, 7] means person 1 likes person 7 but it is not reciprocal
     * @return the number of the sociopath. if the sociopath is not found, return -1. (everyone likes the sociopath, but the sociopath likes no-one.)
     */
    public int findTheSociopath (int groupSize, List<int []> likeList) {
        sociopathFound = false; // reset boolean

        // Validate the group size
        if (groupSize <= 0) { return -1; }

        // Validate all pairs to contain valid person numbers
        // Loop through all pairs and check if any person's number is less than 1 or greater than the groupSize
        for (int i = 0; i < likeList.size(); i++) {
            int[] pair = likeList.get(i);
            int liker = pair[0];
            int liked = pair[1];

            if (liker < 1 || liker > groupSize || liked < 1 || liked > groupSize) {
                return -1; // Returning -1 for invalid input (or if sociopath is just not found)
            }
        }

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
                sociopathFound = true;
                return person;
            }
        }
        return -1; // sociopath not found, doesnt exist
    }

    public boolean isSociopathFound() {
        return sociopathFound;
    }

    // Test method
    public static void main(String[] args) {
        Sociopath finder = new Sociopath();

        // Test 1 -- 2 likes no one; everyone likes 2
        List<int[]> test1 = java.util.Arrays.asList(new int[]{1, 2});
        int result1 = finder.findTheSociopath(2, test1);
        System.out.println("Test 1 -- Expected: 2, Got: " + result1 + ", Found: " + finder.isSociopathFound());

        // Test 2 -- No one likes 3
        List<int[]> test2 = java.util.Arrays.asList(new int[]{1, 2});
        int result2 = finder.findTheSociopath(3, test2);
        System.out.println("Test 2 -- Expected: -1, Got: " + result2 + ", Found: " + finder.isSociopathFound());

        // Test 3 -- 3 likes no one; everyone likes 3
        List<int[]> test3 = java.util.Arrays.asList(
                new int[]{1, 2},
                new int[]{1, 3},
                new int[] {2, 3}
        );
        int result3 = finder.findTheSociopath(3, test3);
        System.out.println("Test 3 -- Expected: 3, Got: " + result3 + ", Found: " + finder.isSociopathFound());

        // Test 4 -- Each person likes someone else
        List<int[]> test4 = java.util.Arrays.asList(
                new int[]{1, 3},
                new int[]{2, 3},
                new int[] {3, 1}
        );
        int result4 = finder.findTheSociopath(3, test4);
        System.out.println("Test 4 -- Expected: -1, Got: " + result4 + ", Found: " + finder.isSociopathFound());

        // Test 5 -- Invalid group size / invalid people
        List<int[]> test5 = java.util.Arrays.asList(new int[]{1, 2});
        int result5 = finder.findTheSociopath(0, test5);
        System.out.println("Test 5 -- Expected: -1, Got: " + result5 + ", Found: " + finder.isSociopathFound());

        // Test 6 -- 0 is not a person
        List<int[]> test6 = java.util.Arrays.asList(new int[]{1, 0});
        int result6 = finder.findTheSociopath(3, test6);
        System.out.println("Test 6 -- Expected: -1, Got: " + result6 + ", Found: " + finder.isSociopathFound());

        // Test 7 -- No relationships
        List<int[]> test7 = java.util.Arrays.asList();
        int result7 = finder.findTheSociopath(3, test7);
        System.out.println("Test 7 -- Expected: -1, Got: " + result7 + ", Found: " + finder.isSociopathFound());

        // Test 8 -- Person likes themselves
        List<int[]> test8 = java.util.Arrays.asList(
                new int[]{1, 1},
                new int[]{2, 3}
        );
        int result8 = finder.findTheSociopath(3, test8);
        System.out.println("Test 8 -- Expected: -1, Got: " + result8 + ", Found: " + finder.isSociopathFound());

        // Test 9 -- Duplicated relationships
        List<int[]> test9 = java.util.Arrays.asList(
                new int[]{1, 2},
                new int[]{1, 2},
                new int[]{1, 2}
        );
        int result9 = finder.findTheSociopath(2, test9);
        System.out.println("Test 9 -- Expected: -1, Got: " + result9 + ", Found: " + finder.isSociopathFound());

        // Test 10 -- Only one person
        List<int[]> test10 = java.util.Arrays.asList();
        int result10 = finder.findTheSociopath(1, test10);
        System.out.println("Test 10 -- Expected: 1, Got: " + result10 + ", Found: " + finder.isSociopathFound());
    }
}