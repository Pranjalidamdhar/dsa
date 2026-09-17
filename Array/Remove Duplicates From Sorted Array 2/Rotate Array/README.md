# 🔄 Rotate Array

## 📌 Problem

Given an integer array `nums`, rotate the array to the **right by `k` steps**, where `k` is non-negative.

### Example

```text
Input:
nums = [1,2,3,4,5,6,7]
k = 3

Output:
[5,6,7,1,2,3,4]
```

---

## 💡 Approach — Reversal Algorithm

We can rotate the array in-place using **three reversals**.

Instead of shifting every element one by one, we divide the array into two parts:

```text
[1,2,3,4] [5,6,7]
    ↑          ↑
 first part   last k elements
```

For `k = 3`, we want the last `3` elements to move to the beginning.

### Step 1: Reverse the first `n-k` elements

```text
[1,2,3,4] [5,6,7]

        ↓

[4,3,2,1] [5,6,7]
```

### Step 2: Reverse the last `k` elements

```text
[4,3,2,1] [5,6,7]

        ↓

[4,3,2,1] [7,6,5]
```

### Step 3: Reverse the entire array

```text
[4,3,2,1,7,6,5]

        ↓

[5,6,7,1,2,3,4]
```

The array is now rotated to the right by `k` positions.

---

## ⚠️ Handling Large `k`

If `k` is greater than the array length, rotating by `n` positions brings the array back to its original state.

Therefore:

```java
k = k % nums.length;
```

For example:

```text
nums.length = 7
k = 10

10 % 7 = 3
```

So rotating by `10` positions is equivalent to rotating by `3` positions.

---

## 💻 Java Solution

```java
class Solution {

    private void reverse(int[] nums, int left, int right) {

        while (left < right) {
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;

            left++;
            right--;
        }
    }

    public void rotate(int[] nums, int k) {

        k = k % nums.length;

        // Reverse first n-k elements
        reverse(nums, 0, nums.length - k - 1);

        // Reverse last k elements
        reverse(nums, nums.length - k, nums.length - 1);

        // Reverse the complete array
        reverse(nums, 0, nums.length - 1);
    }
}
```

---

## 🧠 Why Does This Work?

Suppose:

```text
nums = [1,2,3,4,5,6,7]
k = 3
```

Divide the array into:

```text
A = [1,2,3,4]
B = [5,6,7]
```

We need:

```text
B + A
```

The three reversals transform:

```text
A + B
```

into:

```text
reverse(A) + reverse(B)
```

and finally:

```text
reverse(reverse(A) + reverse(B))
```

which gives:

```text
B + A
```

Therefore:

```text
[1,2,3,4,5,6,7]
          ↓
[4,3,2,1,7,6,5]
          ↓
[5,6,7,1,2,3,4]
```

---

## ⏱️ Complexity

### Time Complexity

```text
O(n)
```

Each element is processed a constant number of times during the reversals.

### Space Complexity

```text
O(1)
```

No extra array is used. The rotation is performed **in-place**.

---

## 🔑 Key Takeaway

The important pattern to remember is:

```java
k = k % nums.length;

reverse(nums, 0, nums.length - k - 1);
reverse(nums, nums.length - k, nums.length - 1);
reverse(nums, 0, nums.length - 1);
```

### Pattern

```text
Reverse first part
        ↓
Reverse second part
        ↓
Reverse whole array
```

This is an efficient **O(n) time and O(1) space** solution for rotating an array to the right.

