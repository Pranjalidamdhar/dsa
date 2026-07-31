# Reverse Linked List

## Problem Statement

Given the `head` of a singly linked list, reverse the list and return the new head.

---

## Solution Explanation

### Approach

We use an **iterative approach** with three pointers:

- **temp**: Traverses the linked list.
- **prev**: Keeps track of the previous node.
- **front**: Stores the next node before reversing the link.

### Algorithm

1. Initialize `prev` as `null` and `temp` as `head`.
2. Traverse the linked list until `temp` becomes `null`.
3. Store the next node in `front`.
4. Reverse the current node's link by setting `temp.next = prev`.
5. Move `prev` to `temp` and `temp` to `front`.
6. After the loop ends, `prev` points to the new head of the reversed list.

---

## Java Solution

```java
class Solution {
    public ListNode reverseList(ListNode head) {
        ListNode temp = head;
        ListNode prev = null;

        while (temp != null) {
            ListNode front = temp.next;
            temp.next = prev;
            prev = temp;
            temp = front;
        }

        return prev;
    }
}
```

---

## Dry Run

**Input:**
```
1 → 2 → 3 → 4 → 5 → NULL
```

**Output:**
```
5 → 4 → 3 → 2 → 1 → NULL
```

---

## Complexity Analysis

- **Time Complexity:** `O(n)` – Each node is visited exactly once.
- **Space Complexity:** `O(1)` – No extra space is used.

---

## Key Takeaways

- Reverse the list **in-place** by updating each node's `next` pointer.
- Use three pointers (`temp`, `prev`, and `front`) to avoid losing the remaining list.
- This solution is efficient with **linear time** and **constant space**.
