# Add One to a Number Represented by a Linked List

## 📌 Problem

Given a **singly linked list** where each node contains a single digit, the linked list represents a non-negative integer.

The task is to **add 1 to the number** represented by the linked list and return the updated linked list.

### Example

```text
Input:  1 → 2 → 9
Output: 1 → 3 → 0
```

```text
Input:  9 → 9 → 9
Output: 1 → 0 → 0 → 0
```

---

## 💡 Approach

The main challenge is handling the **carry** when adding `1`.

For example:

```text
1 → 2 → 9
```

Adding one starts from the last digit:

```text
9 + 1 = 10
```

So the last digit becomes `0` and a carry of `1` moves to the previous node.

The recursive `helper()` method processes the list from **right to left** by recursively reaching the last node first.

### Steps

1. Recursively traverse to the end of the linked list.
2. Return a carry of `1` when the end (`null`) is reached.
3. Add the carry to the current node's data.
4. If the resulting digit is less than `10`, return `0` because there is no further carry.
5. If the resulting digit is `10`, set the current node's value to `0` and return `1`.
6. After processing the head:

   * If a carry remains, create a new node containing `1` and place it before the original head.
   * Otherwise, return the original head.

---

## 🔍 Dry Run

Consider:

```text
9 → 9 → 9
```

Recursive calls reach the end:

```text
helper(null) → 1
```

Then processing happens backwards:

```text
9 + 1 = 10  → 0, carry = 1
9 + 1 = 10  → 0, carry = 1
9 + 1 = 10  → 0, carry = 1
```

A carry still remains after processing the head, so a new node is created:

```text
1 → 0 → 0 → 0
```

---

## 🧠 Code

```java
/* Structure of linked list Node
class Node{
    int data;
    Node next;

    Node(int x){
        data = x;
        next = null;
    }
}
*/

class Solution {

    public int helper(Node temp){
        if(temp == null){
            return 1;
        }

        int carry = helper(temp.next);

        temp.data = temp.data + carry;

        if(temp.data < 10){
            return 0;
        }

        temp.data = 0;
        return 1;
    }

    public Node addOne(Node head) {
        int carry = helper(head);

        if(carry == 1){
            Node NewNode = new Node(1);
            NewNode.next = head;
            return NewNode;
        }

        return head;
    }
}
```

---

## ⏱️ Complexity

### Time Complexity

**O(N)**

Each node is visited once.

### Space Complexity

**O(N)**

The recursive calls use the call stack, with a maximum depth of `N`.

---

## ✨ Key Insight

Since a linked list normally allows easy traversal from **left to right**, but addition must start from the **least significant digit**, recursion is used to process the nodes in reverse order without actually reversing the linked list.

The return value from `helper()` acts as the **carry**.

* `0` → no carry
* `1` → carry to the previous node

This makes the solution simple while preserving the original linked-list structure.
