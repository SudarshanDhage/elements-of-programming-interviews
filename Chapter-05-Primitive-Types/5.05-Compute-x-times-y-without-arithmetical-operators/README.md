# 5.05 - Compute x * y Without Arithmetical Operators

## 📌 Problem Overview
Problem **5.05** from *Elements of Programming Interviews in Java* (Chapter 5: Primitive Types).

---

### 📝 Problem Description
Multiply two non-negative integers `x` and `y` using only assignment, bitwise operators (`>>`, `<<`, `|`, `&`, `^`, `~`), and equality checks. Arithmetic operators (`+`, `-`, `*`, `/`) are forbidden.

#### Input Format
- Two 64-bit non-negative integers `x` and `y`.

#### Output Format
- Return the product `x * y`.

#### Constraints
- $x, y \ge 0$

#### Examples
- **Example 1:** `x = 13`, `y = 9` -> Output: `117`

---

### 💡 Approaches & Complexity Analysis

#### Approach 1: Repeated Bitwise Addition (Brute Force)
- **Intuition & Mechanism:** Add y to a running accumulator x times. The addition is implemented using half-adder bitwise logic (carry = a & b, sum = a ^ b).
- **Time Complexity (TC):** $O(x * n) where n is word width (exponential in input size)$
- **Space Complexity (SC):** $O(1) auxiliary space$

#### Approach 2: Shift-and-Add (Russian Peasant / Optimal)
- **Intuition & Mechanism:** Decompose x into powers of 2. For each set bit i in x, add (y << i) to the running sum. Implement addition using bitwise XOR and carry propagation.
- **Time Complexity (TC):** $O(n^2) where n is the number of bits (64 iterations, each addition takes O(n))$
- **Space Complexity (SC):** $O(1) auxiliary space$

---

### ☕ Java Source Code
Complete implementations of all approaches with runnable test cases are provided in [Solution.java](./Solution.java):

```java
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
```

---

### 🔍 Edge Cases & Verification
- [ ] Zero value (`0`)
- [ ] Maximum values (`Long.MAX_VALUE`, `Integer.MAX_VALUE`)
- [ ] Negative values and sign-bit handling
- [ ] Inputs with all bits set to 1 or all bits set to 0
- [ ] Boundary powers of 2

