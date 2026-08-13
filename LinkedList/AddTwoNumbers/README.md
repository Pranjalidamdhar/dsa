# Add Two Numbers

## Problem

You are given two non-empty singly linked lists representing two non-negative integers.

The digits are stored in **reverse order**, and each node contains a single digit.

Add the two numbers and return the sum as a linked list, also represented in reverse order.

### Example

**Input:**

```text
l1 = [2,4,3]
l2 = [5,6,4]
```

These represent:

```text
342 + 465 = 807
```

**Output:**

```text
[7,0,8]
```

---

## Approach

The solution simulates normal digit-by-digit addition.

Since the linked lists store digits in reverse order, the first nodes contain the least significant digits. This allows us to add corresponding digits directly from the beginning of both lists.

For every position:

1. Get the digit from `l1`, or `0` if `l1` is exhausted.
2. Get the digit from `l2`, or `0` if `l2` is exhausted.
3. Add both digits along with the `carry`.
4. Store the last digit of the sum using `sum % 10`.
5. Update the carry using `sum / 10`.
6. Create a new node containing the resulting digit.
7. Move to the next nodes.

The loop continues while either list still has nodes or there is a remaining carry.

---

## Code

```java
class Solution {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode dummy = new ListNode(-1);
        ListNode temp = dummy;
        int carry = 0;

        while (l1 != null || l2 != null || carry != 0) {

            int dig1 = (l1 != null) ? l1.val : 0;
            int dig2 = (l2 != null) ? l2.val : 0;

            int sum = dig1 + dig2 + carry;

            int finSum = sum % 10;
            carry = sum / 10;

            ListNode newNode = new ListNode(finSum);

            temp.next = newNode;
            temp = temp.next;

            l1 = (l1 != null) ? l1.next : null;
            l2 = (l2 != null) ? l2.next : null;
        }

        return dummy.next;
    }
}
```

---

## Why Use a Dummy Node?

A dummy node makes building the resulting linked list easier.

Instead of handling the first node separately, we start with:

```java
ListNode dummy = new ListNode(-1);
ListNode temp = dummy;
```

Every newly calculated digit can then simply be attached using:

```java
temp.next = newNode;
temp = temp.next;
```

At the end, `dummy.next` points to the actual result.

The value `-1` stored in the dummy node is never part of the answer.

---

## Handling Different Lengths

The two linked lists may have different lengths.

For example:

```text
l1 = [9,9,9]
l2 = [1]
```

When `l2` becomes `null`, its digit is treated as `0`:

```java
int dig2 = (l2 != null) ? l2.val : 0;
```

This allows the addition to continue without special cases.

---

## Handling the Final Carry

The condition:

```java
while (l1 != null || l2 != null || carry != 0)
```

also checks `carry`.

This is important for cases such as:

```text
9 + 1 = 10
```

The result should be:

```text
[0,1]
```

After both lists are exhausted, `carry` is still `1`, so one final node is created.

---

## Complexity

Let `n` and `m` be the lengths of the two linked lists.

### Time Complexity

```text
O(max(n, m))
```

Each node is processed once.

### Space Complexity

```text
O(max(n, m))
```

The output linked list requires up to `max(n, m) + 1` nodes.

---

## Key Concepts

* Singly linked lists
* Linked-list traversal
* Carry handling
* Digit-by-digit addition
* Dummy node technique
* Handling lists of different lengths

## LeetCode

**Problem:** Add Two Numbers
**Problem Number:** 2
**Difficulty:** Medium
