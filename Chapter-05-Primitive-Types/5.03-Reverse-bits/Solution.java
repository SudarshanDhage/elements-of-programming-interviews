public class Solution {

    // Approach 1: Bit by Bit Shift
    // Time Complexity: O(n) | Space Complexity: O(1)
    public static long reverseBitsBruteForce(long x) {
        long result = 0;
        for (int i = 0; i < 64; ++i) {
            result = (result << 1) | (x & 1L);
            x >>>= 1;
        }
        return result;
    }

    // Approach 2: Lookup Table for 16-bit chunks
    // Time Complexity: O(1) | Space Complexity: O(2^16)
    private static final int[] REVERSE_TABLE = new int[1 << 16];
    static {
        for (int i = 0; i < (1 << 16); ++i) {
            int rev = 0;
            int temp = i;
            for (int b = 0; b < 16; ++b) {
                rev = (rev << 1) | (temp & 1);
                temp >>>= 1;
            }
            REVERSE_TABLE[i] = rev;
        }
    }

    public static long reverseBitsLookup(long x) {
        final int MASK = 0xFFFF;
        return ((long) REVERSE_TABLE[(int)(x & MASK)] << 48) |
               ((long) REVERSE_TABLE[(int)((x >>> 16) & MASK)] << 32) |
               ((long) REVERSE_TABLE[(int)((x >>> 32) & MASK)] << 16) |
               ((long) REVERSE_TABLE[(int)((x >>> 48) & MASK)]);
    }

    // Approach 3: Divide and Conquer (Bitwise Parallel Swap)
    // Time Complexity: O(log n) | Space Complexity: O(1)
    public static long reverseBitsOptimal(long x) {
        x = ((x & 0x5555555555555555L) << 1)  | ((x >>> 1)  & 0x5555555555555555L);
        x = ((x & 0x3333333333333333L) << 2)  | ((x >>> 2)  & 0x3333333333333333L);
        x = ((x & 0x0F0F0F0F0F0F0F0FL) << 4)  | ((x >>> 4)  & 0x0F0F0F0F0F0F0F0FL);
        x = ((x & 0x00FF00FF00FF00FFL) << 8)  | ((x >>> 8)  & 0x00FF00FF00FF00FFL);
        x = ((x & 0x0000FFFF0000FFFFL) << 16) | ((x >>> 16) & 0x0000FFFF0000FFFFL);
        return (x << 32) | (x >>> 32);
    }

    public static void main(String[] args) {
        long val = 0b10110010L;
        System.out.println("Approach 1 (Brute Force): " + reverseBitsBruteForce(val));
        System.out.println("Approach 2 (Lookup Table):" + reverseBitsLookup(val));
        System.out.println("Approach 3 (Optimal D&C): " + reverseBitsOptimal(val));
    }
}
