# Subarray Sum Equals K

## Problem

Given an integer array `nums` and an integer `k`, find the total number of continuous subarrays whose sum is equal to `k`.

A subarray is a contiguous part of an array.

### Example

```text
Input:
nums = [1, 1, 1]
k = 2

Output:
2
```

The two subarrays are:

```text
[1, 1]
   [1, 1]
```

---

## Approach

We use **Prefix Sum + HashMap** to solve this problem efficiently.

### Key Idea

While traversing the array, maintain the current prefix sum.

Suppose the current prefix sum is:

```text
prefixSum
```

We need a previous prefix sum such that:

```text
prefixSum - previousSum = k
```

Therefore:

```text
previousSum = prefixSum - k
```

If `prefixSum - k` already exists in the HashMap, it means there are one or more subarrays ending at the current index whose sum is `k`.

We store the frequency of every prefix sum in the HashMap.

---

## Algorithm

1. Initialize `prefixSum = 0`.
2. Initialize `count = 0`.
3. Create a HashMap to store the frequency of prefix sums.
4. Put `(0, 1)` into the HashMap.

   * This handles subarrays that start from index `0`.
5. Traverse the array.
6. Add the current element to `prefixSum`.
7. Calculate:

```text
prefixSum - k
```

8. If it exists in the HashMap, add its frequency to `count`.
9. Store/update the current `prefixSum` in the HashMap.
10. Return `count`.

---

## Java Solution

```java
import java.util.HashMap;

class Solution {
    public int subarraySum(int[] nums, int k) {

        HashMap<Integer, Integer> map = new HashMap<>();

        map.put(0, 1);

        int prefixSum = 0;
        int count = 0;

        for (int num : nums) {

            prefixSum += num;

            if (map.containsKey(prefixSum - k)) {
                count += map.get(prefixSum - k);
            }

            map.put(prefixSum, map.getOrDefault(prefixSum, 0) + 1);
        }

        return count;
    }
}
```

---

## Dry Run

Consider:

```text
nums = [1, 2, 3]
k = 3
```

Initial:

```text
prefixSum = 0
count = 0

map = {0=1}
```

### Element = 1

```text
prefixSum = 1

prefixSum - k = 1 - 3 = -2
```

`-2` is not present.

```text
map = {0=1, 1=1}
```

### Element = 2

```text
prefixSum = 3

prefixSum - k = 3 - 3 = 0
```

`0` exists in the map.

So:

```text
count = count + 1
      = 1
```

This represents:

```text
[1, 2]
```

Update map:

```text
map = {0=1, 1=1, 3=1}
```

### Element = 3

```text
prefixSum = 6

prefixSum - k = 6 - 3 = 3
```

`3` exists in the map.

Therefore:

```text
count = 2
```

The second subarray is:

```text
[3]
```

Final answer:

```text
2
```

---

## Complexity

### Time Complexity

```text
O(n)
```

We traverse the array once, and HashMap operations take `O(1)` average time.

### Space Complexity

```text
O(n)
```

The HashMap can store up to `n` different prefix sums.

---

## Important Point

A simple sliding-window or two-pointer approach **does not work reliably when the array contains negative numbers**.

Prefix Sum + HashMap works with:

* Positive numbers
* Zero
* Negative numbers

That's why this approach is commonly used for this problem.

---

## Pattern to Remember

For **Subarray Sum = K** problems:

```text
Current Prefix Sum - Previous Prefix Sum = K
```

Therefore:

```text
Previous Prefix Sum = Current Prefix Sum - K
```

This is the main idea behind the solution.
