# 5.03 - Reverse Bits

## 📌 Problem Overview
Problem **5.03** from *Elements of Programming Interviews in Java* (Chapter 5: Primitive Types).

---

### 📝 Problem Description
Write a function that takes a 64-bit integer `x` and returns the 64-bit integer formed by reversing the order of the bits in `x`.

#### Input Format
- A 64-bit integer `x`.

#### Output Format
- Return a 64-bit integer where bit `i` is swapped with bit `63 - i`.

#### Constraints
- $0 \le x < 2^{64}$

#### Examples
- **Example 1:** `x = (1000...0000)_2` -> Output: `(0000...0001)_2`

---

### 💡 Approaches & Complexity Analysis

#### Approach 1: Bit-by-Bit Shift (Brute Force)
- **Intuition & Mechanism:** Iterate 64 times. In each iteration, extract the least significant bit of x and append it to the result shifted left.
- **Time Complexity (TC):** $O(n) where n = 64 operations$
- **Space Complexity (SC):** $O(1) auxiliary space$

#### Approach 2: Precomputed Lookup Table (Chunked Reversal)
- **Intuition & Mechanism:** Precompute a reversal table for 16-bit integers. Reverse the 64-bit number by reversing each 16-bit chunk via the lookup table and placing them in reverse chunk order.
- **Time Complexity (TC):** $O(n / L) = O(1) (4 table lookups)$
- **Space Complexity (SC):** $O(2^L) space for 16-bit table (~64 KB)$

#### Approach 3: Divide and Conquer / Bitwise Swapping (Optimal)
- **Intuition & Mechanism:** Swap adjacent 1-bit pairs, then 2-bit pairs, then 4-bit, 8-bit, 16-bit, and finally 32-bit halves using bitmasks and bit shifts in parallel.
- **Time Complexity (TC):** $O(log n) bitwise operations$
- **Space Complexity (SC):** $O(1) auxiliary space$

---

### ☕ Java Source Code
Complete implementations of all approaches with runnable test cases are provided in [Solution.java](./Solution.java):

```java
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
```

---

### 🔍 Edge Cases & Verification
- [ ] Zero value (`0`)
- [ ] Maximum values (`Long.MAX_VALUE`, `Integer.MAX_VALUE`)
- [ ] Negative values and sign-bit handling
- [ ] Inputs with all bits set to 1 or all bits set to 0
- [ ] Boundary powers of 2

