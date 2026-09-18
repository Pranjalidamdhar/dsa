# Move Zeroes

## Problem

Given an integer array `nums`, move all `0`s to the end of the array while maintaining the **relative order of the non-zero elements**.

The operation must be performed **in-place**, without creating another array.

### Example

```text
Input:
[0, 1, 0, 3, 12]

Output:
[1, 3, 12, 0, 0]
```

The non-zero elements remain in the same order:

```text
1 → 3 → 12
```

---

## Approach — Two Pointers

We use two pointers:

* `j` → points to the position of the first `0`
* `i` → searches for the next non-zero element

### Step 1: Find the first zero

```java
int j = -1;
```

Initially, `j` is `-1`, meaning we haven't found a zero yet.

We traverse the array:

```java
for(int i = 0; i < nums.length; i++){
    if(nums[i] == 0){
        j = i;
        break;
    }
}
```

Once we find the first zero, we store its index in `j`.

For example:

```text
[1, 2, 0, 4, 0, 5]
       ↑
       j
```

Here:

```text
j = 2
```

Everything before `j` is already non-zero.

---

## Step 2: Handle the case when there is no zero

```java
if(j == -1){
    return;
}
```

If `j` is still `-1`, there is no zero in the array.

For example:

```text
[1, 2, 3, 4]
```

The array is already correct, so we simply return.

---

## Step 3: Find non-zero elements

Now `i` starts from:

```java
j + 1
```

```java
for(int i = j + 1; i < nums.length; i++){
```

The purpose of `i` is to search for the next non-zero element.

```java
if(nums[i] != 0)
```

When a non-zero element is found, we swap it with the zero at position `j`.

---

## Swapping

```java
int temp = nums[j];

nums[j] = nums[i];

nums[i] = temp;
```

For example:

```text
[1, 2, 0, 4, 0, 5]
       ↑     ↑
       j     i
```

Swap `0` and `4`:

```text
[1, 2, 4, 0, 0, 5]
```

Then:

```java
j++;
```

Now `j` points to the next position where a non-zero element should be placed.

---

## Dry Run

Consider:

```text
nums = [0, 1, 0, 3, 12]
```

### Initial state

```text
j = 0
```

`j` points to the first zero:

```text
[0, 1, 0, 3, 12]
 ↑
 j
```

### Find `1`

```text
i = 1
nums[i] = 1
```

Swap:

```text
[1, 0, 0, 3, 12]
    ↑
    j
```

Then:

```text
j = 1
```

### Find `3`

`i` continues moving:

```text
i = 2 → nums[2] = 0
i = 3 → nums[3] = 3
```

Swap:

```text
[1, 3, 0, 0, 12]
```

Then:

```text
j = 2
```

### Find `12`

```text
i = 4
nums[i] = 12
```

Swap:

```text
[1, 3, 12, 0, 0]
```

Final result:

```text
[1, 3, 12, 0, 0]
```

---

## Code

```java
class Solution {
    public void moveZeroes(int[] nums) {

        int j = -1;

        // Find the first zero
        for(int i = 0; i < nums.length; i++){
            if(nums[i] == 0){
                j = i;
                break;
            }
        }

        // No zero found
        if(j == -1){
            return;
        }

        // Find non-zero elements and move them forward
        for(int i = j + 1; i < nums.length; i++){
            if(nums[i] != 0){

                int temp = nums[j];
                nums[j] = nums[i];
                nums[i] = temp;

                j++;
            }
        }
    }
}
```

---

## Why This Works

At every step:

```text
j → position of the next zero to replace
i → searches for the next non-zero
```

Whenever `i` finds a non-zero:

```text
Swap nums[i] with nums[j]
              ↓
          j moves forward
```

This gradually moves every non-zero element toward the beginning while pushing zeroes toward the end.

The relative order of non-zero elements is preserved.

---

## Complexity

### Time Complexity

```text
O(n)
```

We traverse the array at most twice.

### Space Complexity

```text
O(1)
```

We use only a few variables and modify the array in-place.

---

## Key Takeaway

The important two-pointer pattern is:

```text
j → position to fill
i → search for a valid element
```

Whenever:

```java
nums[i] != 0
```

we swap:

```java
nums[j] ↔ nums[i]
```

and move:

```java
j++;
```

This is a useful **two-pointer + in-place swapping** pattern for array problems.
