# 5.02 - Swap Bits

## 📌 Problem Overview
Problem **5.02** from *Elements of Programming Interviews in Java* (Chapter 5: Primitive Types).

---

### 📝 Problem Description
Given a 64-bit integer `x` and two bit indices `i` and `j`, swap the bits at position `i` and position `j`.

#### Input Format
- A 64-bit integer `x`.
- Two bit positions `i` and `j` ($0 \le i, j < 64$).

#### Output Format
- Return the integer with bits at `i` and `j` swapped.

#### Constraints
- $0 \le i, j < 64$

#### Examples
- **Example 1:** `x = 73` (binary `01001001`), `i = 1`, `j = 6` -> Output: `11` (binary `00001011`)

---

### 💡 Approaches & Complexity Analysis

#### Approach 1: Extract, Clear, and Set (Brute Force)
- **Intuition & Mechanism:** Extract bit at i and bit at j. Clear both bit positions using an inverted mask, then re-insert bit i at position j and bit j at position i.
- **Time Complexity (TC):** $O(1) time$
- **Space Complexity (SC):** $O(1) auxiliary space$

#### Approach 2: Conditional Bit Flip with XOR (Optimal)
- **Intuition & Mechanism:** If the bits at i and j are identical, swapping them changes nothing. If they differ, flipping both bits achieves the swap. Use XOR with a bitmask having 1s at positions i and j.
- **Time Complexity (TC):** $O(1) time (constant operations)$
- **Space Complexity (SC):** $O(1) auxiliary space$

---

### ☕ Java Source Code
Complete implementations of all approaches with runnable test cases are provided in [Solution.java](./Solution.java):

```java
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
```

---

### 🔍 Edge Cases & Verification
- [ ] Zero value (`0`)
- [ ] Maximum values (`Long.MAX_VALUE`, `Integer.MAX_VALUE`)
- [ ] Negative values and sign-bit handling
- [ ] Inputs with all bits set to 1 or all bits set to 0
- [ ] Boundary powers of 2

