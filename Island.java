/*
 * Alan Licerio
 * CS 2401
 * Lab 4
 * Teacher: Dr. Ceberio
 * 10-11-19
 */

package Lab4;

class Island {
    static int islandWater(int[] land) {
        int result = 0;

        if (land.length == 0) {                 // If the array is empty, the method will return 0.
            return 0;
        }

        for (int i = 0; i < land.length; i++) {
            if (land.length < 3) {             // If the array size is less than 3, the method will return 0 because there is no way that
                return result;                 // water can be stored in it.
            } else if (land[i] < 0) {            // If the array contains a negative number at any of its index, the method will return 0.
                return result;
            }
        }

        int[] left = new int[land.length];    // Creates a new array that will store the max values of the array land, from left to right.
        int[] right = new int[land.length];   // Creates a new array that will store the max values of the array land, from right to left.

        //iterate the array from left to right
        int maxHeight = land[0];
        left[0] = land[0];

        for (int i = 1; i < land.length; i++) {
            if (land[i] < maxHeight) {
                left[i] = maxHeight;
            } else {
                left[i] = land[i];
                maxHeight = land[i];
            }
        }

        //iterate the array from right to left
        maxHeight = land[land.length - 1];
        right[land.length - 1] = land[land.length - 1];

        for (int i = land.length - 2; i >= 0; i--) {
            if (land[i] < maxHeight) {
                right[i] = maxHeight;
            } else {
                right[i] = land[i];
                maxHeight = land[i];
            }
        }

        //calculate total amount of water
        for (int i = 0; i < land.length; i++) {
            result += Math.min(left[i], right[i]) - land[i];
        }
        return result;
    }

    /*
     * Populates an array of size n with numbers from 1 to 12.
     */
    static int[] randomArrays(int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = (int) (Math.random() * 12) + 1;
        }
        return array;
    }

    public static void main(String[] args) {
        int numArrays = 250;
        long start;
        long end = 0;
        int size = 5000000; // This value is subject to change based on what the user wants.
        for (int i = 0; i < numArrays; i++) { // Loop that runs the method 200 times for every different size.
            int[] array = randomArrays(size);
            start = System.currentTimeMillis();
            islandWater(array);
            end += System.currentTimeMillis() - start;
        }
        System.out.println("Time for size " + size + " = " + (double) end / numArrays + " milliseconds.");
    }
}
