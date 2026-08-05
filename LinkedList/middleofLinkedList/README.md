# Middle of the Linked List

## Problem Statement
Given the `head` of a singly linked list, return the **middle node** of the linked list.

- If there are two middle nodes, return the **second middle node**.

**LeetCode Difficulty:** Easy

---

## Approach

This solution uses the **Two Pointer (Fast & Slow Pointer)** technique.

- Initialize two pointers:
  - `slow` moves **one step** at a time.
  - `fast` moves **two steps** at a time.
- When the `fast` pointer reaches the end of the list, the `slow` pointer will be at the middle.
- If the list has an even number of nodes, the algorithm naturally returns the **second middle node**, as required.

---

## Algorithm

1. Initialize `slow` and `fast` pointers to the head.
2. Traverse the list while `fast` and `fast.next` are not `null`.
3. Move:
   - `slow = slow.next`
   - `fast = fast.next.next`
4. Return the `slow` pointer.

---

## Complexity Analysis

- **Time Complexity:** `O(n)`
- **Space Complexity:** `O(1)`

---

## Java Solution

```java
class Solution {
    public ListNode middleNode(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        return slow;
    }
}
```

---

## Key Concept

The **Fast & Slow Pointer** technique is an efficient way to find the middle of a linked list in a single traversal without calculating its length first.

---

## Tags

- Linked List
- Two Pointers
- Fast and Slow Pointer
