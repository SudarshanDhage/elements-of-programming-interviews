public class Solution {

    // Approach 1: Extract, Clear and Re-insert
    // Time Complexity: O(1) | Space Complexity: O(1)
    public static long swapBitsExtractClear(long x, int i, int j) {
        long bitI = (x >>> i) & 1L;
        long bitJ = (x >>> j) & 1L;
        if (bitI == bitJ) return x;

        // Clear bits at i and j
        x &= ~((1L << i) | (1L << j));
        // Set bitI at j, and bitJ at i
        x |= (bitI << j) | (bitJ << i);
        return x;
    }

    // Approach 2: Optimal XOR Flip (Only flip if bits differ)
    // Time Complexity: O(1) | Space Complexity: O(1)
    public static long swapBitsOptimal(long x, int i, int j) {
        if (((x >>> i) & 1L) != ((x >>> j) & 1L)) {
            long bitMask = (1L << i) | (1L << j);
            x ^= bitMask;
        }
        return x;
    }

    public static void main(String[] args) {
        long x = 73L; // binary 01001001
        int i = 1, j = 6;
        System.out.println("Approach 1: " + swapBitsExtractClear(x, i, j));
        System.out.println("Approach 2: " + swapBitsOptimal(x, i, j));
    }
}
