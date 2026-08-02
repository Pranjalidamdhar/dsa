# Remove Duplicates from Doubly Linked List

## 📌 Problem Statement

Given the head of a **sorted doubly linked list**, remove all duplicate nodes so that every element appears only once. Return the head of the modified linked list.

---

## 🧠 Approach

Since the doubly linked list is **sorted**, duplicate elements always appear consecutively.

The idea is to traverse the list using two pointers:

- `temp1` points to the current unique node.
- `temp2` moves ahead to skip all duplicate nodes.

Once all duplicates are skipped:
- Connect `temp1.next` to the first non-duplicate node.
- Update the `prev` pointer of the new next node.
- Continue the process until the end of the list.

---

## 🚀 Algorithm

1. Initialize `temp1` with the head of the list.
2. Initialize `temp2` as `temp1.next`.
3. Traverse the linked list:
   - Skip all consecutive nodes having the same value as `temp1`.
   - Link `temp1.next` to the first different node.
   - If the new node exists, update its `prev` pointer.
   - Move `temp1` to the next unique node.
4. Return the head of the modified list.

---

## 💻 Java Solution

```java
class Solution {
    Node removeDuplicates(Node headRef) {
        Node temp1 = headRef;
        Node temp2 = temp1.next;

        while (temp1 != null) {
            while (temp2 != null && temp1.data == temp2.data) {
                temp2 = temp2.next;
            }

            temp1.next = temp2;

            if (temp2 != null) {
                temp2.prev = temp1;
            }

            temp1 = temp2;

            if (temp1 != null) {
                temp2 = temp1.next;
            }
        }

        return headRef;
    }
}
```

---

## 📊 Complexity Analysis

| Complexity | Value |
|------------|-------|
| **Time Complexity** | **O(N)** |
| **Space Complexity** | **O(1)** |

- **Time Complexity:** Each node is visited at most once.
- **Space Complexity:** No extra memory is used.

---

## 📝 Example

### Input

```
1 ⇄ 2 ⇄ 2 ⇄ 3 ⇄ 3 ⇄ 4 ⇄ 4 ⇄ 5
```

### Output

```
1 ⇄ 2 ⇄ 3 ⇄ 4 ⇄ 5
```

---

## ✅ Key Points

- Works only because the linked list is **sorted**.
- Removes duplicate nodes **in-place**.
- Correctly updates both `next` and `prev` pointers.
- Uses **constant extra space**.
- Efficient **single-pass** traversal.

---

## 🏷️ Tags

- Linked List
- Doubly Linked List
- Data Structures
- Two Pointers
- Java
- GeeksforGeeks
