# Next Greater Elements II

## Problem

Given a **circular array** `nums`, return an array where `nge[i]` contains the **next greater element** for `nums[i]`.

If no greater element exists, return `-1`.

Because the array is circular, after reaching the last element, we continue searching from the beginning.

### Example

```text
Input:
nums = [1, 2, 1]

Output:
[2, -1, 2]
```

Explanation:

* For `1` at index `0`, the next greater element is `2`.
* For `2` at index `1`, there is no greater element → `-1`.
* For `1` at index `2`, we wrap around and find `2`.

---

## Approach

This solution uses a **monotonic stack**.

Since the array is circular, we simulate traversing the array **twice**:

```java
for (int i = 2 * n - 1; i >= 0; i--)
```

The actual array index is obtained using:

```java
i % n
```

This allows us to treat:

```text
[1, 2, 1]
```

as if we were processing:

```text
[1, 2, 1, 1, 2, 1]
```

### Stack Logic

The stack stores potential **next greater elements**.

For every element:

1. Remove elements from the stack that are smaller than or equal to the current element.
2. If the stack is empty, there is no greater element.
3. Otherwise, the top of the stack is the next greater element.
4. Push the current element onto the stack.

The important condition is:

```java
while (!st.isEmpty() && st.peek() <= nums[i % n]) {
    st.pop();
}
```

After removing all elements that cannot be the answer, the stack top is the nearest greater element.

---

## Code

```java
class Solution {
    public int[] nextGreaterElements(int[] nums) {
        Stack<Integer> st = new Stack<>();
        int n = nums.length;
        int[] nge = new int[n];

        for (int i = 2 * n - 1; i >= 0; i--) {
            while (!st.isEmpty() && st.peek() <= nums[i % n]) {
                st.pop();
            }

            if (i < n) {
                nge[i] = st.isEmpty() ? -1 : st.peek();
            }

            st.push(nums[i % n]);
        }

        return nge;
    }
}
```

---

## Why Do We Traverse Twice?

For a normal array, an element can only look to its right.

For a circular array, an element near the end may need to look at elements at the beginning.

For example:

```text
nums = [5, 4, 3, 6, 2]

                    ↘
                    5 → 6
                    4 → 6
                    3 → 6
                    6 → -1
                    2 → 5  ← wraps around
```

Traversing `2 * n` positions ensures that elements at the beginning are available as possible greater elements for elements near the end.

---

## Complexity

### Time Complexity

```text
O(n)
```

Although the loop runs `2n` times, every element is pushed onto and popped from the stack at most a constant number of times.

### Space Complexity

```text
O(n)
```

The stack can contain up to `n` elements, and the result array requires `O(n)` space.

---

## Key Takeaway

The combination of:

* **Monotonic Stack**
* **Reverse Traversal**
* **Circular Array using `i % n`**

allows us to solve the problem efficiently in **O(n)** time instead of checking every element to its right, which would take **O(n²)** time.
