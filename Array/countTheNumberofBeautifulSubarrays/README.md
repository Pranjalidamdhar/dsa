# Beautiful Subarrays

## Problem

Given an integer array `nums`, a subarray is called **beautiful** if the bitwise XOR of all its elements is `0`.

Return the **number of beautiful subarrays**.

---

## Approach

We use the **Prefix XOR + HashMap** technique.

The key observation is:

If two prefix XOR values are the same, then the XOR of the elements between them is `0`.

For example:

```text
Prefix XOR:
A → B → B

XOR of subarray between the two B positions:

B ^ B = 0
```

Therefore, whenever the current prefix XOR has appeared before, every previous occurrence forms a beautiful subarray ending at the current position.

We store the frequency of each prefix XOR in a `HashMap`.

---

## Algorithm

1. Create a `HashMap` to store the frequency of each prefix XOR.
2. Initially store:

   ```java
   freq.put(0, 1);
   ```

   This handles subarrays whose XOR from index `0` is `0`.
3. Maintain a variable `XOR` for the current prefix XOR.
4. For every element:

   * Update the prefix XOR:

     ```java
     XOR ^= val;
     ```
   * Check whether this XOR has appeared before.
   * If it has, add its frequency to the answer.
   * Increase its frequency in the map.
5. Return the total count.

---

## Java Code

```java
import java.util.HashMap;

class Solution {
    public long beautifulSubarrays(int[] nums) {

        HashMap<Integer, Integer> freq = new HashMap<>();
        freq.put(0, 1);

        long count = 0;
        int XOR = 0;

        for (int val : nums) {
            XOR ^= val;

            if (freq.containsKey(XOR)) {
                count += freq.get(XOR);
            }

            freq.put(XOR, freq.getOrDefault(XOR, 0) + 1);
        }

        return count;
    }
}
```

---

## Example

### Input

```text
nums = [1, 2, 3]
```

### Prefix XOR

```text
Initial: 0

1 → 1
1 ^ 2 → 3
3 ^ 3 → 0
```

The prefix XOR `0` appears again, meaning the subarray:

```text
[1, 2, 3]
```

has XOR `0`.

So the answer is:

```text
1
```

---

## Why HashMap?

We need to know how many times the current prefix XOR has already appeared.

For example, if:

```text
XOR = 5
```

has appeared `3` times before, then there are `3` different subarrays ending at the current position whose XOR is `0`.

Therefore:

```java
count += freq.get(XOR);
```

---

## Complexity

* **Time Complexity:** `O(n)`
* **Space Complexity:** `O(n)`

Where `n` is the length of the array.

---

## Key Concept

> **Same Prefix XOR → XOR of the subarray between them is `0`**

This is a common and important pattern for solving subarray problems involving **XOR** efficiently.
