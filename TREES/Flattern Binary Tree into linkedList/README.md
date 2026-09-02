# Flatten Binary Tree to Linked List

## Problem Statement

Given the root of a binary tree, flatten the tree into a **linked list in-place**.

The linked list should use the same `TreeNode` structure where:

* `right` pointer points to the next node.
* `left` pointer is always `null`.
* The nodes should follow the **preorder traversal** of the binary tree.

### Example

**Input:**

```text
        1
       / \
      2   5
     / \   \
    3   4   6
```

**Output:**

```text
1
 \
  2
   \
    3
     \
      4
       \
        5
         \
          6
```

---

## Approach

We use an **iterative approach** without using recursion or an additional stack.

For every current node:

1. If the current node has a left subtree:

   * Find the **rightmost node** of the left subtree.
   * Connect this rightmost node to the current node's original right subtree.
   * Move the left subtree to the right.
   * Set the left pointer to `null`.

2. Move to the next node using the `right` pointer.

### Why does this work?

Consider:

```text
        1
       / \
      2   5
     / \
    3   4
```

For node `1`:

* The left subtree starts at `2`.
* The rightmost node of the left subtree is `4`.
* Connect `4` to `5`.
* Move `2` to the right of `1`.
* Set `1.left = null`.

The tree becomes:

```text
1
 \
  2
 / \
3   4
     \
      5
```

The same process is repeated for every node until the entire tree becomes a linked list.

---

## Algorithm

```text
curr = root

while curr is not null:

    if curr.left is not null:

        prev = curr.left

        while prev.right is not null:
            prev = prev.right

        prev.right = curr.right

        curr.right = curr.left

        curr.left = null

    curr = curr.right
```

---

## Java Implementation

```java
class Solution {
    public void flatten(TreeNode root) {

        TreeNode curr = root;

        while (curr != null) {

            if (curr.left != null) {

                // Find the rightmost node of the left subtree
                TreeNode prev = curr.left;

                while (prev.right != null) {
                    prev = prev.right;
                }

                // Connect left subtree's rightmost node
                // to the original right subtree
                prev.right = curr.right;

                // Move left subtree to the right
                curr.right = curr.left;

                // Remove the left pointer
                curr.left = null;
            }

            // Move to the next node
            curr = curr.right;
        }
    }
}
```

---

## Complexity Analysis

### Time Complexity

**O(N)** in the amortized sense for this standard Morris-style flattening approach.

Each tree edge is traversed a bounded number of times while finding the rightmost node of left subtrees.

### Space Complexity

**O(1)**

No recursion stack, array, list, or explicit stack is used.

---

## Key Points

* Uses **preorder structure**: Root → Left → Right.
* Modifies the tree **in-place**.
* Every `left` pointer becomes `null`.
* Every `right` pointer points to the next node in preorder.
* Does not require recursion.
* Does not require an additional stack.

## Pattern to Remember

The important idea is:

```text
rightmost node of left subtree
            ↓
    connect to original right subtree

current.right = current.left
current.left = null
```

This technique is useful when a problem asks you to transform a binary tree **in-place** while maintaining preorder order.
