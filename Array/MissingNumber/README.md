# Missing Number

## Problem

Given an array `nums` containing `n` distinct numbers taken from the range `[0, n]`, return the **only number that is missing** from the array.

### Example

```text
Input:  nums = [3, 0, 1]
Output: 2
```

The numbers should be:

```text
0, 1, 2, 3
```

But `2` is missing.

---

## Approach — XOR

We can solve this problem using the **XOR (Exclusive OR)** operation.

The important properties of XOR are:

```text
a ^ a = 0
a ^ 0 = a
```

This means that if we XOR the same number twice, it cancels out.

For example:

```text
1 ^ 2 ^ 3 ^ 2 ^ 1

= (1 ^ 1) ^ (2 ^ 2) ^ 3
= 0 ^ 0 ^ 3
= 3
```

So, if we XOR:

1. All numbers from `1` to `n`
2. All elements present in the array

Every number that exists in both sets will cancel out, leaving only the **missing number**.

---

## Code

```java
class Solution {
    public int missingNumber(int[] nums) {

        int XOR1 = 0;
        int XOR2 = 0;

        for(int i = 0; i < nums.length; i++) {

            XOR2 ^= nums[i];
            XOR1 ^= (i + 1);
        }

        return XOR1 ^ XOR2;
    }
}
```

---

## Step-by-Step Explanation

Suppose:

```text
nums = [3, 0, 1]
```

Here:

```text
n = 3
```

The complete range is:

```text
0, 1, 2, 3
```

The missing number is `2`.

### `XOR2`

This stores the XOR of all elements in the array:

```text
XOR2 = 3 ^ 0 ^ 1
```

### `XOR1`

Inside the loop:

```java
XOR1 ^= (i + 1);
```

So we calculate:

```text
1 ^ 2 ^ 3
```

At the end:

```text
XOR1 = 1 ^ 2 ^ 3
XOR2 = 3 ^ 0 ^ 1
```

Now:

```text
XOR1 ^ XOR2

= (1 ^ 2 ^ 3) ^ (3 ^ 0 ^ 1)

= 2
```

All duplicate numbers cancel because:

```text
x ^ x = 0
```

Therefore, the remaining number is:

```text
2
```

---

## Why `i + 1`?

The loop starts from:

```java
i = 0
```

But we want to XOR:

```text
1, 2, 3, ..., n
```

Therefore:

```java
XOR1 ^= (i + 1);
```

For an array of length `3`:

```text
i = 0 → i + 1 = 1
i = 1 → i + 1 = 2
i = 2 → i + 1 = 3
```

So `XOR1` contains:

```text
1 ^ 2 ^ 3
```

The number `0` does not need to be explicitly included because:

```text
x ^ 0 = x
```

---

## Complexity

### Time Complexity

```text
O(n)
```

We traverse the array only once.

### Space Complexity

```text
O(1)
```

Only two integer variables are used.

---

## Key Takeaway

The main idea is:

```text
All numbers XOR Array numbers
             ↓
      Duplicate numbers cancel
             ↓
       Missing number remains
```

Remember these two XOR properties:

```text
x ^ x = 0
x ^ 0 = x
```

This allows us to find the missing number without sorting the array or using extra space.
