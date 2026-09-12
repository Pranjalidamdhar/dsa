# Inorder Successor in BST

## Problem Statement

Given a **Binary Search Tree (BST)** and a node `k`, find the **inorder successor** of node `k`.

The inorder successor of a node is the node that appears **immediately after it in the inorder traversal** of the BST.

If the inorder successor does not exist, return `-1`.

### Example

Consider the following BST:

```text
        20
       /  \
     10    30
       \
       15
```

For `k = 15`, the inorder traversal is:

```text
10 → 15 → 20 → 30
```

Therefore, the inorder successor of `15` is:

```text
20
```

---

## Approach

Since the tree is a **Binary Search Tree**, we can find the successor without performing a complete inorder traversal.

We maintain a variable `successor` to store the possible successor.

### Steps

1. Start from the root.
2. If `k.data >= root.data`:

   * The current node cannot be the successor because its value is smaller than or equal to `k`.
   * Move to the **right subtree**.
3. Otherwise:

   * The current node is greater than `k`, so it can be a possible successor.
   * Store it in `successor`.
   * Move to the **left subtree** to find a smaller value that is still greater than `k`.
4. Continue until `root` becomes `null`.
5. If a successor was found, return its data.
6. Otherwise, return `-1`.

---

## Code

```java
/*
Definition for Node
class Node {
    public int data;
    public Node left;
    public Node right;

    public Node(int val) {
        data = val;
        left = right = null;
    }
};
*/

class Solution {
    public int inOrderSuccessor(Node root, Node k) {
        Node successor = null;

        while (root != null) {
            if (k.data >= root.data) {
                root = root.right;
            } 
            else {
                successor = root;
                root = root.left;
            }
        }

        return successor != null ? successor.data : -1;
    }
}
```

---

## Why This Works

For every node in a BST:

* All values in the **left subtree** are smaller.
* All values in the **right subtree** are greater.

When `root.data > k.data`, the current root is a valid successor candidate. However, there might be a smaller value greater than `k` in the left subtree, so we continue searching there.

When `root.data <= k.data`, the current node cannot be the successor, so we move to the right subtree.

Thus, `successor` eventually contains the **smallest value greater than `k`**.

---

## Dry Run

For the BST:

```text
        20
       /  \
     10    30
       \
       15
```

Let:

```text
k = 15
```

### Step 1

```text
root = 20
```

Since:

```text
15 < 20
```

`20` can be a successor.

```text
successor = 20
root = 10
```

### Step 2

```text
root = 10
```

Since:

```text
15 >= 10
```

`10` cannot be the successor.

Move right:

```text
root = 15
```

### Step 3

```text
root = 15
```

Since:

```text
15 >= 15
```

Move right.

Eventually:

```text
root = null
```

The stored successor is:

```text
20
```

### Output

```text
20
```

---

## Complexity Analysis

Let `h` be the height of the BST.

### Time Complexity

```text
O(h)
```

We follow only one path from the root to a leaf.

For a balanced BST:

```text
O(log n)
```

For a skewed BST:

```text
O(n)
```

### Space Complexity

```text
O(1)
```

Only a single `successor` variable is used, so no additional recursion or data structure is required.

---

## Key Concept

The important idea is:

> **When `root.data > k.data`, store the root as a possible successor and move left.**

This works because we want the **smallest value that is greater than `k`**.

---

## Related Concept

### Inorder Traversal

For a BST, inorder traversal produces values in **sorted order**:

```text
Left → Root → Right
```

Therefore, the inorder successor of `k` is simply the **next greater value** than `k`.

---

## Tags

`Java` `Binary Search Tree` `BST` `Binary Tree` `Inorder Traversal` `Tree` `DSA`
