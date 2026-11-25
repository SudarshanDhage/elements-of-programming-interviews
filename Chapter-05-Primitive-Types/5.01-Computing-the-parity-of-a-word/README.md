# 5.01 - Computing the Parity of a Word

## 📌 Problem Overview
Problem **5.01** from *Elements of Programming Interviews in Java* (Chapter 5: Primitive Types).

---

### 📝 Problem Description
Given a 64-bit integer, compute its parity. The parity of a binary word is `1` if the number of `1`s in the word is odd; otherwise, it is `0`.

#### Input Format
- A single 64-bit unsigned/signed integer `x`.

#### Output Format
- Return `(short) 1` if the count of set bits is odd, otherwise `(short) 0`.

#### Constraints
- $0 \le x < 2^{64}$

#### Examples
- **Example 1:** `x = 11` (binary `1011` -> three 1s) -> Output: `1`
- **Example 2:** `x = 12` (binary `1100` -> two 1s) -> Output: `0`

---

### 💡 Approaches & Complexity Analysis

#### Approach 1: Brute Force (Iterate Bit by Bit)
- **Intuition & Mechanism:** Inspect each of the 64 bits individually using a bitmask and shift. Maintain a running parity bit flipped whenever a 1 is encountered.
- **Time Complexity (TC):** $O(n) where n is the word size (64 operations)$
- **Space Complexity (SC):** $O(1) auxiliary space$

#### Approach 2: Erase Lowest Set Bit (Brian Kernighan Trick)
- **Intuition & Mechanism:** The bitwise identity x & (x - 1) clears the lowest set bit in x. Repeat this operation, toggling the parity bit each time until x becomes 0.
- **Time Complexity (TC):** $O(k) where k is the number of set bits (best: O(1) when 0, worst: O(n))$
- **Space Complexity (SC):** $O(1) auxiliary space$

#### Approach 3: Precomputed Lookup Table (Caching Slices)
- **Intuition & Mechanism:** Precompute the parity of all 16-bit numbers (65,536 entries). Partition the 64-bit integer into four 16-bit segments and compute parity as table[w0] ^ table[w1] ^ table[w2] ^ table[w3].
- **Time Complexity (TC):** $O(n / L) where L = 16 (exactly 4 table lookups, O(1) amortized)$
- **Space Complexity (SC):** $O(2^L) space for the lookup table (65,536 elements = ~64 KB)$

#### Approach 4: Parallel Bitwise Reduction (Optimal Fold)
- **Intuition & Mechanism:** XOR is associative. The parity of (b63...b0) equals the parity of (b63...b32) ^ (b31...b0). Repeatedly XOR the upper half of the word with the lower half: 32 -> 16 -> 8 -> 4 -> 2 -> 1.
- **Time Complexity (TC):** $O(log n) bitwise operations (6 steps for a 64-bit word)$
- **Space Complexity (SC):** $O(1) auxiliary space$

---

### ☕ Java Source Code
Complete implementations of all approaches with runnable test cases are provided in [Solution.java](./Solution.java):

```java
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
```

---

### 🔍 Edge Cases & Verification
- [ ] Zero value (`0`)
- [ ] Maximum values (`Long.MAX_VALUE`, `Integer.MAX_VALUE`)
- [ ] Negative values and sign-bit handling
- [ ] Inputs with all bits set to 1 or all bits set to 0
- [ ] Boundary powers of 2

