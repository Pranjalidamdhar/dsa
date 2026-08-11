# Previous Smaller Element

This repository contains two approaches to find the **previous smaller element** for every element in an array:

1. **Brute Force Approach** — `O(N²)`
2. **Optimized Approach using Monotonic Stack** — `O(N)`

---

## Problem Statement

Given an integer array `arr`, for every element, find the **first smaller element to its left**.

If there is no smaller element on the left, return `-1`.

### Example

**Input:**

```text
arr = [4, 5, 2, 10, 8]
```

**Output:**

```text
[-1, 4, -1, 2, 2]
```

### Explanation

| Element | Previous Smaller Element |
| ------: | -----------------------: |
|       4 |                       -1 |
|       5 |                        4 |
|       2 |                       -1 |
|      10 |                        2 |
|       8 |                        2 |

For example:

* For `5`, the previous element `4` is smaller → `4`
* For `2`, there is no smaller element before it → `-1`
* For `10`, `2` is the first smaller element from the left → `2`
* For `8`, `2` is the first smaller element from the left → `2`

---

# Approach 1: Brute Force

### Idea

For every element `arr[i]`, traverse the array backwards from `i - 1`.

The first element smaller than `arr[i]` is the required previous smaller element.

If no such element exists, store `-1`.

### Algorithm

1. Iterate through every element of the array.
2. Start from the element immediately to its left.
3. Move towards the beginning of the array.
4. If an element smaller than the current element is found:

   * Store it.
   * Stop searching.
5. If no smaller element is found, store `-1`.

### Code

```java
class Solution {
    public static ArrayList<Integer> prevSmaller(int[] arr) {
        int n = arr.length;
        ArrayList<Integer> nse = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int prev = -1;

            for (int j = i - 1; j >= 0; j--) {
                if (arr[j] < arr[i]) {
                    prev = arr[j];
                    break;
                }
            }

            nse.add(prev);
        }

        return nse;
    }
}
```

### Complexity

* **Time Complexity:** `O(N²)`
* **Space Complexity:** `O(N)`

The nested loop can potentially examine almost every previous element for each array element.

---

# Approach 2: Optimized Approach — Monotonic Stack

### Idea

The brute-force approach repeatedly checks elements that may not be useful.

We can optimize this using a **monotonic increasing stack**.

The stack stores elements that can potentially be the previous smaller element for future elements.

For every `arr[i]`:

1. Remove elements from the stack while they are **greater than or equal to** `arr[i]`.
2. After removing them:

   * If the stack is empty, there is no previous smaller element → `-1`.
   * Otherwise, the top of the stack is the previous smaller element.
3. Add `arr[i]` to the stack.

### Code

```java
class Solution {
    public static ArrayList<Integer> prevSmaller(int[] arr) {
        int n = arr.length;
        ArrayList<Integer> nse = new ArrayList<>();
        Stack<Integer> st = new Stack<>();

        for (int i = 0; i < n; i++) {
            while (!st.isEmpty() && st.peek() >= arr[i]) {
                st.pop();
            }

            int prev = st.isEmpty() ? -1 : st.peek();

            nse.add(prev);
            st.push(arr[i]);
        }

        return nse;
    }
}
```

### Why Does the Stack Work?

Consider:

```text
arr = [4, 5, 2, 10, 8]
```

Processing the array:

```text
4 → stack: [4]
5 → previous smaller = 4
    stack: [4, 5]

2 → remove 5
    remove 4
    stack becomes empty
    previous smaller = -1
    stack: [2]

10 → previous smaller = 2
     stack: [2, 10]

8 → remove 10
    previous smaller = 2
    stack: [2, 8]
```

Result:

```text
[-1, 4, -1, 2, 2]
```

The key observation is that elements that are greater than or equal to the current element can never be the previous smaller element for the current element or for certain future elements, so they can safely be removed.

---

# Brute Force vs Optimized

| Approach    | Time Complexity | Space Complexity | Technique       |
| ----------- | --------------- | ---------------- | --------------- |
| Brute Force | `O(N²)`         | `O(N)`           | Nested Loop     |
| Optimized   | `O(N)`          | `O(N)`           | Monotonic Stack |

The **optimized approach is preferred** because every element is pushed onto the stack once and popped at most once.

Therefore, the total stack operations are linear:

```text
O(N) pushes + O(N) pops = O(N)
```

---

# Important Concept

This problem is a variation of the **Previous Smaller Element (PSE)** pattern.

The general monotonic-stack pattern is:

```java
while (!stack.isEmpty() && stack.peek() >= current) {
    stack.pop();
}

if (stack.isEmpty()) {
    answer = -1;
} else {
    answer = stack.peek();
}

stack.push(current);
```

This pattern is useful in many problems involving:

* Previous Smaller Element
* Next Smaller Element
* Previous Greater Element
* Next Greater Element
* Stock Span
* Largest Rectangle in Histogram

---

# Edge Cases

### 1. Single Element

```text
Input:  [5]
Output: [-1]
```

### 2. Increasing Array

```text
Input:  [1, 2, 3, 4]
Output: [-1, 1, 2, 3]
```

### 3. Decreasing Array

```text
Input:  [4, 3, 2, 1]
Output: [-1, -1, -1, -1]
```

### 4. Duplicate Elements

```text
Input:  [2, 2, 3]
Output: [-1, -1, 2]
```

Because the problem requires a **strictly smaller** element, elements equal to the current element are removed using:

```java
stack.peek() >= arr[i]
```

---

# Key Takeaway

The brute-force solution is straightforward and easy to understand, but it takes `O(N²)` time.

The optimized solution uses a **monotonic increasing stack** to reduce the time complexity to:

```text
Time:  O(N)
Space: O(N)
```

Whenever you see a problem asking for the **nearest/previous/next greater or smaller element**, consider using a **monotonic stack**.
