# Majority Element

## Problem Statement

Given an array `nums` of size `n`, find the **majority element**.

The majority element is the element that appears **more than `n / 2` times** in the array.

You may assume that a majority element always exists.

### Example

**Input:**

```text
nums = [2, 2, 1, 1, 1, 2, 2]
```

**Output:**

```text
2
```

**Explanation:**

The array has `7` elements, so the majority element must appear more than `7 / 2 = 3` times.

`2` appears `4` times, so `2` is the majority element.

---

## Approach: Boyer-Moore Voting Algorithm

We use the **Boyer-Moore Voting Algorithm**.

The idea is to maintain:

* `el` → current candidate for the majority element
* `count` → vote count for the current candidate

For every element:

1. If `count == 0`, choose the current element as the new candidate.
2. If the current element is equal to the candidate, increment `count`.
3. Otherwise, decrement `count`.

Different elements cancel each other out.

Since the majority element appears more than `n / 2` times, it cannot be completely cancelled by the other elements. Therefore, the final candidate is the majority element.

---

## Dry Run

For:

```text
nums = [2, 2, 1, 1, 1, 2, 2]
```

| Element | Candidate | Count |
| ------: | --------: | ----: |
|       2 |         2 |     1 |
|       2 |         2 |     2 |
|       1 |         2 |     1 |
|       1 |         2 |     0 |
|       1 |         1 |     1 |
|       2 |         1 |     0 |
|       2 |         2 |     1 |

Final candidate:

```text
2
```

Therefore, the majority element is `2`.

---

## Java Solution

```java
class Solution {

    public int majorityElement(int[] nums) {

        int el = nums[0];
        int count = 1;
        int n = nums.length;

        for(int i = 1; i < n; i++){

            if(count == 0){
                el = nums[i];
                count = 1;
            }
            else if(nums[i] == el){
                count++;
            }
            else{
                count--;
            }
        }

        return el;
    }
}
```

---

## Complexity Analysis

### Time Complexity

```text
O(n)
```

We traverse the array only once.

### Space Complexity

```text
O(1)
```

Only two variables, `el` and `count`, are used.

---

## Key Takeaway

The **Boyer-Moore Voting Algorithm** finds the majority element in linear time using constant extra space.

**Pattern to remember:**

```text
Same element     → count++
Different element → count--
count == 0       → choose new candidate
```

This is a useful technique for **array frequency and majority-element problems**.
