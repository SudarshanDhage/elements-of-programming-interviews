# 5.04 - Find a Closest Integer with the Same Weight

## 📌 Problem Overview
Problem **5.04** from *Elements of Programming Interviews in Java* (Chapter 5: Primitive Types).

---

### 📝 Problem Description
The weight of a non-negative integer `x` is the number of bits set to `1`. Given `x`, find a different non-negative integer `y` such that `weight(x) == weight(y)` and the absolute difference `|x - y|` is minimized.

#### Input Format
- A 64-bit non-negative integer `x` (not all 0s and not all 1s).

#### Output Format
- Return the closest integer `y` having the same number of set bits.

#### Constraints
- $0 < x < 2^{63} - 1$ (assumes at least one 0 and at least one 1)

#### Examples
- **Example 1:** `x = 6` (binary `110`) -> Output: `5` (binary `101`), difference is 1.

---

### 💡 Approaches & Complexity Analysis

#### Approach 1: Outward Linear Search (Brute Force)
- **Intuition & Mechanism:** Test x - 1, x + 1, x - 2, x + 2, ... and return the first integer whose popcount matches popcount(x).
- **Time Complexity (TC):** $O(2^n) worst case$
- **Space Complexity (SC):** $O(1) auxiliary space$

#### Approach 2: Swap Lowest Differing Consecutive Bits (Optimal)
- **Intuition & Mechanism:** To minimize |x - y|, swap the two consecutive bits at positions k and k+1 that differ, where k is as small as possible (closest to LSB). Swapping bits at k and k+1 changes x by exactly 2^k.
- **Time Complexity (TC):** $O(n) where n is word width (at most 63 iterations)$
- **Space Complexity (SC):** $O(1) auxiliary space$

---

### ☕ Java Source Code
Complete implementations of all approaches with runnable test cases are provided in [Solution.java](./Solution.java):

```java
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
```

---

### 🔍 Edge Cases & Verification
- [ ] Zero value (`0`)
- [ ] Maximum values (`Long.MAX_VALUE`, `Integer.MAX_VALUE`)
- [ ] Negative values and sign-bit handling
- [ ] Inputs with all bits set to 1 or all bits set to 0
- [ ] Boundary powers of 2

