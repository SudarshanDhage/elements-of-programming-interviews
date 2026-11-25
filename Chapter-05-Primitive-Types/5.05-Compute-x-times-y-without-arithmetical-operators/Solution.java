public class Solution {

    // Bitwise Addition Helper
    // Time Complexity: O(n) | Space Complexity: O(1)
    public static long bitwiseAdd(long a, long b) {
        while (b != 0) {
            long carry = (a & b) << 1;
            a = a ^ b;
            b = carry;
        }
        return a;
    }

    // Approach 1: Repeated Bitwise Addition
    // Time Complexity: O(x * n) | Space Complexity: O(1)
    public static long multiplyBruteForce(long x, long y) {
        long sum = 0;
        for (long i = 0; i < x; ++i) {
            sum = bitwiseAdd(sum, y);
        }
        return sum;
    }

    // Approach 2: Shift and Add (Optimal)
    // Time Complexity: O(n^2) | Space Complexity: O(1)
    public static long multiplyOptimal(long x, long y) {
        long sum = 0;
        while (x != 0) {
            if ((x & 1L) != 0) {
                sum = bitwiseAdd(sum, y);
            }
            x >>>= 1;
            y <<= 1;
        }
        return sum;
    }

    public static void main(String[] args) {
        long x = 13L, y = 9L;
        System.out.println("Approach 1 (Repeated Add): " + multiplyBruteForce(x, y));
        System.out.println("Approach 2 (Shift & Add):  " + multiplyOptimal(x, y));
    }
}
