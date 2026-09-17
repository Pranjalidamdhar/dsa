# Remove Duplicates from Sorted Array II

## Problem

Given a sorted integer array `nums`, remove duplicates **in-place** such that each element appears **at most twice**.

The relative order of the elements should be maintained.

Return the number of elements `k` after removing the extra duplicates.

The first `k` elements of `nums` should contain the final result.

---

## Example

### Input

```text
nums = [1,1,1,2,2,3]
```

### Output

```text
5
```

The modified array becomes:

```text
[1,1,2,2,3]
```

Each element appears at most twice.

---

# Approach 1: HashMap

### Idea

Since every element is allowed to appear at most **two times**, we can use a `HashMap` to store the frequency of each element.

For every element:

1. Get its current frequency from the map.
2. If the frequency is less than `2`, keep the element.
3. Increase its frequency in the map.
4. Place the element at the current `ind` position.
5. If its frequency is already `2`, skip it.

### Java Implementation

```java
class Solution {
    public int removeDuplicates(int[] nums) {

        HashMap<Integer, Integer> s = new HashMap<>();

        int ind = 0;

        for (int i = 0; i < nums.length; i++) {

            int count = s.getOrDefault(nums[i], 0);

            if (count < 2) {

                s.put(nums[i], count + 1);

                nums[ind] = nums[i];
                ind++;
            }
        }

        return ind;
    }
}
```

### Complexity

* **Time:** `O(n)`
* **Space:** `O(n)` in the worst case because of the `HashMap`

---

# Approach 2: Optimal Solution

### Idea

The array is already **sorted**, so we don't need a `HashMap`.

We use two pointers:

* `i` → traverses the original array.
* `ind` → points to the position where the next valid element should be placed.

The key condition is:

```java
ind < 2 || nums[i] != nums[ind - 2]
```

### Why `ind - 2`?

Each number is allowed to appear **at most twice**.

When we are about to insert `nums[i]`, we compare it with the element **two positions behind**.

If:

```java
nums[i] == nums[ind - 2]
```

then the current value would become the **third occurrence**, so we skip it.

Otherwise, we keep it.

### Java Implementation

```java
class Solution {
    public int removeDuplicates(int[] nums) {

        int ind = 0;

        for (int i = 0; i < nums.length; i++) {

            if (ind < 2 || nums[i] != nums[ind - 2]) {
                nums[ind] = nums[i];
                ind++;
            }
        }

        return ind;
    }
}
```

---

## Dry Run

Consider:

```text
nums = [1,1,1,2,2,3]
```

Initially:

```text
ind = 0
```

### First `1`

```text
ind < 2 → true
```

Keep it.

```text
[1,...]
ind = 1
```

### Second `1`

```text
ind < 2 → true
```

Keep it.

```text
[1,1,...]
ind = 2
```

### Third `1`

Now:

```text
nums[i] = 1
nums[ind - 2] = nums[0] = 1
```

Therefore:

```text
nums[i] == nums[ind - 2]
```

Skip it.

### First `2`

```text
nums[i] = 2
nums[ind - 2] = nums[0] = 1
```

They are different, so keep `2`.

```text
[1,1,2,...]
```

### Second `2`

Keep it.

```text
[1,1,2,2,...]
```

### `3`

Keep it.

Final array:

```text
[1,1,2,2,3]
```

Return:

```text
5
```

---

# Comparison

| Approach     |   Time |  Space | Main Idea                                 |
| ------------ | -----: | -----: | ----------------------------------------- |
| HashMap      | `O(n)` | `O(n)` | Store frequency of each number            |
| Two Pointers | `O(n)` | `O(1)` | Compare with element two positions behind |

---

## Optimal Approach — Key Insight

Because the array is sorted, duplicate values appear next to each other.

Since every value can occur **at most twice**, we only need to check whether the current value is equal to the value **two positions before the insertion position**.

```java
nums[i] != nums[ind - 2]
```

If they are equal, it is the third occurrence → **skip**.

If they are different, it is safe to keep → **insert**.

---

## Complexity of Optimal Solution

```text
Time Complexity  : O(n)
Space Complexity : O(1)
```

The solution modifies the array **in-place**, using only a few variables.

---

## Key DSA Concept

**Two Pointers + In-Place Array Modification**

This problem is a good example of how a property of the input — **the array being sorted** — can eliminate the need for an extra data structure such as a `HashMap`.
