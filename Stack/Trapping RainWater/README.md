# Trapping Rain Water

## Problem

Given an array `height` where each element represents the height of a bar, compute how much rainwater can be trapped after raining.

**LeetCode:** Trapping Rain Water
**Difficulty:** Hard

---

## Approach

This solution uses the **Two Pointer** technique to achieve an optimal solution.

* Initialize two pointers:

  * `l` at the beginning of the array.
  * `r` at the end of the array.
* Keep track of:

  * `leftMax` → Maximum height seen from the left.
  * `rightMax` → Maximum height seen from the right.
* Compare the heights at both pointers:

  * If `height[l] < height[r]`, process the left side.
  * Otherwise, process the right side.
* If the current height is smaller than the corresponding maximum height, trapped water is:

  ```
  maxHeight - currentHeight
  ```
* Continue until both pointers meet.

This approach avoids using extra arrays and computes the answer in a single traversal.

---

## Algorithm

1. Initialize `leftMax`, `rightMax`, `l`, `r`, and `total`.
2. While `l <= r`:

   * If `height[l] < height[r]`:

     * Update `leftMax`.
     * Add trapped water if possible.
     * Move `l` forward.
   * Otherwise:

     * Update `rightMax`.
     * Add trapped water if possible.
     * Move `r` backward.
3. Return `total`.

---

## Time Complexity

* **O(n)** – Each element is visited at most once.

## Space Complexity

* **O(1)** – Only a few extra variables are used.

---

## Java Solution

```java
class Solution {
    public int trap(int[] height) {
        int leftMax = 0;
        int rightMax = 0;
        int total = 0;
        int l = 0;
        int r = height.length - 1;

        while (l <= r) {
            if (height[l] < height[r]) {
                if (leftMax > height[l]) {
                    total += leftMax - height[l];
                } else {
                    leftMax = height[l];
                }
                l++;
            } else {
                if (rightMax > height[r]) {
                    total += rightMax - height[r];
                } else {
                    rightMax = height[r];
                }
                r--;
            }
        }

        return total;
    }
}
```

---

## Example

**Input**

```text
height = [0,1,0,2,1,0,1,3,2,1,2,1]
```

**Output**

```text
6
```

**Explanation**

The bars trap a total of **6 units** of rainwater.

---

## Key Concepts

* Two Pointer Technique
* Greedy Approach
* Prefix Maximum
* Suffix Maximum
* Array Traversal
