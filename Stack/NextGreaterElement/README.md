# Next Greater Element I

## Problem

Given two arrays `nums1` and `nums2`, where `nums1` is a subset of `nums2`, find the **next greater element** for each element of `nums1`.

The next greater element of an element `x` is the first element to the right of `x` in `nums2` that is greater than `x`.

If no greater element exists, return `-1`.

### Example

**Input:**

```text
nums1 = [4, 1, 2]
nums2 = [1, 3, 4, 2]
```

**Output:**

```text
[-1, 3, -1]
```

Explanation:

* For `4`, there is no greater element → `-1`
* For `1`, the next greater element is `3`
* For `2`, there is no greater element → `-1`

---

## Approach

This solution uses a **Monotonic Stack** and a lookup array.

### 1. Traverse `nums2` from right to left

For every element:

* Remove elements from the stack that are smaller than or equal to the current element.
* The element remaining at the top of the stack is the next greater element.
* If the stack is empty, the answer is `-1`.
* Store the result in `nextGeneration`.
* Push the current element onto the stack.

### 2. Process `nums1`

For every element in `nums1`, look up its next greater element using the `nextGeneration` array.

This avoids searching through `nums2` repeatedly.

---

## Code

```java
class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] nextGeneration = new int[10001];
        Stack<Integer> st = new Stack<>();

        for (int i = nums2.length - 1; i >= 0; i--) {
            while (!st.isEmpty() && st.peek() <= nums2[i]) {
                st.pop();
            }

            nextGeneration[nums2[i]] =
                st.isEmpty() ? -1 : st.peek();

            st.push(nums2[i]);
        }

        for (int i = 0; i < nums1.length; i++) {
            nums1[i] = nextGeneration[nums1[i]];
        }

        return nums1;
    }
}
```

---

## Why Monotonic Stack?

The stack maintains elements in **decreasing order** from bottom to top.

When processing `nums2[i]`, any element smaller than or equal to it can never be the next greater element for the current value, so it is safely removed.

Each element is:

* Pushed onto the stack once.
* Popped from the stack at most once.

Therefore, the stack makes the solution efficient.

---

## Complexity

Let:

* `N = nums2.length`
* `M = nums1.length`

### Time Complexity

```text
O(N + M)
```

Each element of `nums2` is pushed and popped at most once, and `nums1` is processed once.

### Space Complexity

```text
O(N + K)
```

where `K = 10001` is the size of the lookup array.

The stack requires `O(N)` space.

---

## Key Concepts

* Monotonic Stack
* Next Greater Element
* Array Lookup
* Stack Optimization
* O(N) Time Complexity

## LeetCode

This solution solves **LeetCode 496 – Next Greater Element I**.
