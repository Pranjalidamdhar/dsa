# Asteroid Collision

## Problem Statement

We are given an array of integers `asteroids`, where:

- Each value represents the size of an asteroid.
- A positive value (`+`) means the asteroid is moving to the right.
- A negative value (`-`) means the asteroid is moving to the left.

When two asteroids moving in opposite directions collide:

- The smaller asteroid explodes.
- If both are the same size, both explode.
- Asteroids moving in the same direction never collide.

Return the state of the asteroids after all collisions.

**LeetCode Difficulty:** Medium

---

## Approach

This solution uses a **Stack** to simulate asteroid collisions.

### Logic

- If the current asteroid is moving to the **right** (positive), push it onto the stack.
- If it is moving to the **left** (negative):
  - Compare it with the top of the stack while the top is a smaller positive asteroid.
  - Remove smaller asteroids that collide.
  - If both asteroids have the same size, remove the top asteroid.
  - If the stack becomes empty or the top asteroid is also moving left, push the current asteroid.

The stack always contains the asteroids that survive up to the current point.

---

## Algorithm

1. Create an empty stack.
2. Traverse the asteroid array.
3. If the asteroid is positive, push it onto the stack.
4. If the asteroid is negative:
   - Remove all smaller positive asteroids from the stack.
   - If the top asteroid has the same size, remove it.
   - If the stack is empty or the top asteroid is negative, push the current asteroid.
5. Store the remaining stack elements into an array and return it.

---

## Complexity Analysis

- **Time Complexity:** `O(n)`
  - Each asteroid is pushed and popped at most once.

- **Space Complexity:** `O(n)`
  - In the worst case, all asteroids remain in the stack.

---

## Java Solution

```java
class Solution {
    public int[] asteroidCollision(int[] asteroids) {
        int n = asteroids.length;
        Stack<Integer> st = new Stack<>();

        for (int i = 0; i < n; i++) {
            if (asteroids[i] > 0) {
                st.push(asteroids[i]);
            } else {
                while (!st.isEmpty() && st.peek() > 0 &&
                       st.peek() < Math.abs(asteroids[i])) {
                    st.pop();
                }

                if (!st.isEmpty() &&
                    st.peek() == Math.abs(asteroids[i])) {
                    st.pop();
                }
                else if (st.isEmpty() || st.peek() < 0) {
                    st.push(asteroids[i]);
                }
            }
        }

        int[] ans = new int[st.size()];
        for (int i = st.size() - 1; i >= 0; i--) {
            ans[i] = st.pop();
        }

        return ans;
    }
}
```

---

## Example

**Input**

```text
asteroids = [5, 10, -5]
```

**Output**

```text
[5, 10]
```

**Explanation**

- `10` and `-5` collide.
- Since `10 > 5`, `-5` explodes.
- Remaining asteroids are `[5, 10]`.

---

## Key Concepts

- Stack
- Simulation
- Collision Handling
- Arrays

---

## Tags

- Stack
- Array
- Simulation
- LeetCode Medium
