# Sort Colors

## Problem

Given an array `nums` containing only `0`, `1`, and `2`, sort the array **in-place** so that all `0`s come first, followed by all `1`s, and then all `2`s.

### Example

```text
Input:
nums = [2, 0, 2, 1, 1, 0]

Output:
[0, 0, 1, 1, 2, 2]
```

---

## Approach

We use the **Dutch National Flag Algorithm**.

Instead of using a sorting algorithm, we divide the array into four sections using three pointers:

```text
[ 0s ][ 1s ][ Unknown ][ 2s ]
       ↑     ↑          ↑
      low   mid        high
```

### Three Pointers

* `low` → position where the next `0` should be placed
* `mid` → current element being processed
* `high` → position where the next `2` should be placed

Initially:

```java
low = 0;
mid = 0;
high = nums.length - 1;
```

---

## Algorithm

While `mid <= high`:

### Case 1: `nums[mid] == 0`

Swap `nums[mid]` with `nums[low]`.

Then:

```java
low++;
mid++;
```

The `0` is placed in its correct region.

---

### Case 2: `nums[mid] == 1`

`1` is already in the middle region.

Simply move:

```java
mid++;
```

---

### Case 3: `nums[mid] == 2`

Swap `nums[mid]` with `nums[high]`.

Then:

```java
high--;
```

We **do not increment `mid`**.

Why?

Because the element that comes from `high` is unknown. It could be `0`, `1`, or `2`, so we need to process it again.

---

## Code

```java
class Solution {
    public void sortColors(int[] nums) {

        int n = nums.length;

        int low = 0;
        int mid = 0;
        int high = n - 1;

        while (mid <= high) {

            if (nums[mid] == 0) {

                int temp = nums[mid];
                nums[mid] = nums[low];
                nums[low] = temp;

                low++;
                mid++;
            }

            else if (nums[mid] == 1) {
                mid++;
            }

            else {

                int temp = nums[mid];
                nums[mid] = nums[high];
                nums[high] = temp;

                high--;
            }
        }
    }
}
```

---

## Dry Run

Consider:

```text
nums = [2, 0, 2, 1, 1, 0]
```

Initially:

```text
low = 0
mid = 0
high = 5
```

### Step 1

`nums[mid] = 2`

Swap with `high`:

```text
[0, 0, 2, 1, 1, 2]
 ↑              ↑
mid            high
```

Decrease `high`.

---

### Step 2

`nums[mid] = 0`

Swap with `low`:

```text
[0, 0, 2, 1, 1, 2]
 ↑
low/mid
```

Move both `low` and `mid`.

---

### Continue

After processing all elements:

```text
[0, 0, 1, 1, 2, 2]
```

The array is sorted.

---

## Important Point

When `nums[mid] == 2`:

```java
high--;
```

but **do not do**:

```java
mid++;
```

because the new value at `mid` has not been checked yet.

This is one of the most important details of the Dutch National Flag Algorithm.

---

## Complexity

### Time Complexity

```text
O(n)
```

Each element is processed at most a constant number of times.

### Space Complexity

```text
O(1)
```

Only three pointers and a temporary variable are used.

---

## Key Takeaway

Remember the three rules:

```text
0 → swap with low  → low++, mid++

1 → mid++

2 → swap with high → high--
```

The algorithm sorts the array **in-place**, without using an extra array.
