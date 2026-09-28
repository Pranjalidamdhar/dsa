# Binary Search

## Problem

Given a **sorted array of integers** `nums` and an integer `target`, return the index of `target` if it exists in the array. Otherwise, return `-1`.

The array is sorted in **ascending order**.

---

## Approach

We use the **Binary Search** algorithm.

Instead of checking every element one by one, binary search repeatedly divides the search range into two halves.

### Steps

1. Set two pointers:

   * `low = 0`
   * `high = nums.length - 1`

2. Calculate the middle index:

   ```java
   int mid = low + (high - low) / 2;
   ```

3. Compare `nums[mid]` with `target`:

   * If `nums[mid] == target`, return `mid`.
   * If `nums[mid] < target`, search in the **right half**.
   * If `nums[mid] > target`, search in the **left half**.

4. Continue until `low > high`.

5. If the target is not found, return `-1`.

---

## Code

```java
class Solution {
    public int search(int[] nums, int target) {
        int n = nums.length;

        int low = 0;
        int high = n - 1;

        while (low <= high) {

            int mid = low + (high - low) / 2;

            if (nums[mid] == target) {
                return mid;
            }
            else if (nums[mid] < target) {
                low = mid + 1;
            }
            else {
                high = mid - 1;
            }
        }

        return -1;
    }
}
```

---

## Example

### Input

```text
nums = [-1, 0, 3, 5, 9, 12]
target = 9
```

### Output

```text
4
```

### Explanation

Binary search works as follows:

```text
[-1, 0, 3, 5, 9, 12]
          ↑
         mid = 2
```

`nums[2] = 3`

Since `3 < 9`, search in the right half:

```text
[5, 9, 12]
    ↑
   mid
```

`nums[mid] = 9`, so the target is found at index `4`.

---

## Complexity

| Complexity | Value      |
| ---------- | ---------- |
| Time       | `O(log n)` |
| Space      | `O(1)`     |

### Why `O(log n)`?

At every step, binary search eliminates approximately **half of the remaining elements**.

For example:

```text
n → n/2 → n/4 → n/8 → ...
```

Therefore, the time complexity is **O(log n)**.

---

## Important Point

Binary Search can only be directly applied when the array is **sorted**.

For example:

```text
[-1, 0, 3, 5, 9, 12]  ✅ Sorted
```

but:

```text
[5, 1, 9, 3, 7]       ❌ Not sorted
```

---

## Key Concept

> **Binary Search = Divide the search space into half at every step.**
