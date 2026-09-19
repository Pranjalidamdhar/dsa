# Max Consecutive Ones

## Problem Statement

Given a binary array `nums`, return the maximum number of consecutive `1`s in the array.

### Example

**Input:**

```text
nums = [1,1,0,1,1,1]
```

**Output:**

```text
3
```

**Explanation:**

There are two groups of consecutive `1`s:

* `[1,1]` → length `2`
* `[1,1,1]` → length `3`

Therefore, the maximum number of consecutive `1`s is **3**.

---

## Approach

We can solve this problem using a simple **linear traversal**.

We maintain two variables:

* `count` → keeps track of the current consecutive `1`s.
* `maxi` → stores the maximum consecutive `1`s found so far.

### Steps

1. Traverse the array from left to right.
2. If the current element is `1`:

   * Increment `count`.
   * Update `maxi` with the maximum of `maxi` and `count`.
3. If the current element is `0`:

   * Reset `count` to `0` because the consecutive sequence is broken.
4. Return `maxi`.

---

## Java Solution

```java
class Solution {

    public int findMaxConsecutiveOnes(int[] nums) {

        int n = nums.length;
        int maxi = 0;
        int count = 0;

        for (int j = 0; j < n; j++) {

            if (nums[j] == 1) {
                count++;
                maxi = Math.max(maxi, count);
            } 
            else {
                count = 0;
            }
        }

        return maxi;
    }
}
```

---

## Dry Run

For:

```text
nums = [1, 1, 0, 1, 1, 1]
```

| Element | `count` | `maxi` |
| ------: | ------: | -----: |
|       1 |       1 |      1 |
|       1 |       2 |      2 |
|       0 |       0 |      2 |
|       1 |       1 |      2 |
|       1 |       2 |      2 |
|       1 |       3 |      3 |

Final answer:

```text
3
```

---

## Complexity Analysis

### Time Complexity

```text
O(n)
```

We traverse the array exactly once.

### Space Complexity

```text
O(1)
```

Only a few variables are used, regardless of the input size.

---

## Key Concept

This problem demonstrates how to find the **longest consecutive sequence** using a simple counter.

The important idea is:

```java
if (nums[j] == 1)
    count++;
else
    count = 0;
```

Whenever we encounter `0`, the current sequence of consecutive `1`s ends, so we reset the counter.

---

## Pattern

**Array → Traversal → Counting Consecutive Elements**

This pattern is useful for problems involving:

* Consecutive `1`s
* Consecutive characters
* Longest streaks
* Continuous sequences
* Maximum run length
