public class Solution {

    // Approach 1: Linear Outward Search (Brute Force)
    // Time Complexity: O(2^n) worst case | Space Complexity: O(1)
    public static long closestIntSameWeightBruteForce(long x) {
        int targetWeight = Long.bitCount(x);
        for (long diff = 1; diff < Long.MAX_VALUE; ++diff) {
            if (x - diff > 0 && Long.bitCount(x - diff) == targetWeight) {
                return x - diff;
            }
            if (Long.MAX_VALUE - diff >= x && Long.bitCount(x + diff) == targetWeight) {
                return x + diff;
            }
        }
        throw new IllegalArgumentException("No valid integer found");
    }

    // Approach 2: Swap Lowest Differing Consecutive Bits (Optimal)
    // Time Complexity: O(n) | Space Complexity: O(1)
    public static long closestIntSameWeightOptimal(long x) {
        final int NUM_BITS = 62;
        for (int i = 0; i < NUM_BITS; ++i) {
            if (((x >>> i) & 1L) != ((x >>> (i + 1)) & 1L)) {
                // Swap bit i and bit i + 1
                x ^= (1L << i) | (1L << (i + 1));
                return x;
            }
        }
        throw new IllegalArgumentException("All bits are 0 or 1");
    }

    public static void main(String[] args) {
        long x = 6L; // binary 110
        System.out.println("Approach 1 (Brute Force): " + closestIntSameWeightBruteForce(x));
        System.out.println("Approach 2 (Optimal):     " + closestIntSameWeightOptimal(x));
    }
}
