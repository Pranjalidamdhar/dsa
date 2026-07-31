# Reverse Linked List

## Problem Statement

Given the `head` of a singly linked list, reverse the list and return the new head.

### Example 1

**Input:**
```
head = [1,2,3,4,5]
```

**Output:**
```
[5,4,3,2,1]
```

### Example 2

**Input:**
```
head = [1,2]
```

**Output:**
```
[2,1]
```

### Example 3

**Input:**
```
head = []
```

**Output:**
```
[]
```

---

## Constraints

- The number of nodes in the list is in the range **[0, 5000]**.
- `-5000 <= Node.val <= 5000`

---

## Approach (Iterative)

We reverse the linked list by changing the direction of each node's `next` pointer.

### Steps

1. Initialize:
   - `prev = null`
   - `temp = head`
2. Traverse the linked list until `temp` becomes `null`.
3. Store the next node in `front`.
4. Reverse the current node's pointer:
   - `temp.next = prev`
5. Move both pointers one step ahead:
   - `prev = temp`
   - `temp = front`
6. After the loop ends, `prev` will point to the new head of the reversed linked list.

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
1 → 2 → 3 → NULL
```

| Step | prev | temp | Reversed List |
|------|------|------|---------------|
| Initial | NULL | 1 | NULL |
| 1 | 1 | 2 | 1 → NULL |
| 2 | 2 | 3 | 2 → 1 → NULL |
| 3 | 3 | NULL | 3 → 2 → 1 → NULL |

**Output:**

```
3 → 2 → 1 → NULL
```

---

## Complexity Analysis

- **Time Complexity:** `O(n)`
  - Each node is visited exactly once.

- **Space Complexity:** `O(1)`
  - No extra data structure is used.

---

## Key Concepts

- Singly Linked List
- Pointer Manipulation
- Iterative Traversal
- In-place Reversal

---

## LeetCode

**Problem:** Reverse Linked List  
**Difficulty:** Easy

---
```
