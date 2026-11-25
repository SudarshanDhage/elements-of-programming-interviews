public class Solution {

    // Approach 1: Brute Force - Check each of the 64 bits
    // Time Complexity: O(n) | Space Complexity: O(1)
    public static short parityBruteForce(long x) {
        short result = 0;
        while (x != 0) {
            result ^= (x & 1);
            x >>>= 1;
        }
        return result;
    }

    // Approach 2: Clear Lowest Set Bit (Brian Kernighan)
    // Time Complexity: O(k) where k is set bits | Space Complexity: O(1)
    public static short parityEraseLowestBit(long x) {
        short result = 0;
        while (x != 0) {
            result ^= 1;
            x &= (x - 1); // Clears the lowest set bit
        }
        return result;
    }

    // Approach 3: Lookup Table (Precomputed 16-bit cache)
    // Time Complexity: O(n / L) = O(1) | Space Complexity: O(2^L)
    private static final short[] PRECOMPUTED_PARITY = new short[1 << 16];
    static {
        for (int i = 0; i < (1 << 16); ++i) {
            PRECOMPUTED_PARITY[i] = parityEraseLowestBit(i);
        }
    }

    public static short parityLookupTable(long x) {
        final int MASK_SIZE = 16;
        final int BIT_MASK = 0xFFFF;
        return (short) (
            PRECOMPUTED_PARITY[(int)((x >>> (3 * MASK_SIZE)) & BIT_MASK)] ^
            PRECOMPUTED_PARITY[(int)((x >>> (2 * MASK_SIZE)) & BIT_MASK)] ^
            PRECOMPUTED_PARITY[(int)((x >>> MASK_SIZE) & BIT_MASK)] ^
            PRECOMPUTED_PARITY[(int)(x & BIT_MASK)]
        );
    }

    // Approach 4: Parallel Bitwise Reduction (Divide & Conquer)
    // Time Complexity: O(log n) (6 operations) | Space Complexity: O(1)
    public static short parityOptimal(long x) {
        x ^= x >>> 32;
        x ^= x >>> 16;
        x ^= x >>> 8;
        x ^= x >>> 4;
        x ^= x >>> 2;
        x ^= x >>> 1;
        return (short) (x & 1);
    }

    public static void main(String[] args) {
        long testVal = 0b1011L; // 11 in decimal -> 3 set bits -> parity 1
        System.out.println("Approach 1 (Brute Force): " + parityBruteForce(testVal));
        System.out.println("Approach 2 (Erase Bit):   " + parityEraseLowestBit(testVal));
        System.out.println("Approach 3 (Lookup Table):" + parityLookupTable(testVal));
        System.out.println("Approach 4 (Optimal):     " + parityOptimal(testVal));
    }
}
