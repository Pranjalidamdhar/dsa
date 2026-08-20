# Balanced Binary Tree

## Problem

Given the root of a binary tree, determine whether the tree is **height-balanced**.

A binary tree is height-balanced if, for every node, the difference between the heights of its left and right subtrees is **at most 1**.

## Approach

This solution uses **Depth-First Search (DFS)** with a bottom-up height calculation.

The helper method `dfHeight()` returns:

* The height of the subtree if it is balanced.
* `-1` if the subtree is unbalanced.

For each node:

1. Recursively calculate the height of the left subtree.
2. If the left subtree is unbalanced (`-1`), immediately return `-1`.
3. Recursively calculate the height of the right subtree.
4. If the right subtree is unbalanced, immediately return `-1`.
5. Check whether the height difference is greater than `1`.
6. If balanced, return the subtree height.

```java
public int dfHeight(TreeNode root) {
    if (root == null) {
        return 0;
    }

    int leftHeight = dfHeight(root.left);
    if (leftHeight == -1) {
        return -1;
    }

    int rightHeight = dfHeight(root.right);
    if (rightHeight == -1) {
        return -1;
    }

    if (Math.abs(leftHeight - rightHeight) > 1) {
        return -1;
    }

    return Math.max(leftHeight, rightHeight) + 1;
}
```

## Why `-1`?

Using `-1` as a special value allows the algorithm to propagate an unbalanced condition up the tree.

As soon as an unbalanced subtree is found, there is no need to calculate heights further for its ancestors.

## Complexity

* **Time:** `O(n)` — each node is visited once.
* **Space:** `O(h)` — recursion stack, where `h` is the height of the tree.

  * Balanced tree: `O(log n)`
  * Skewed tree: `O(n)`

## Example

For the following tree:

```text
        3
       / \
      9  20
         / \
        15  7
```

The left subtree has height `1` and the right subtree has height `2`.

The difference is `1`, so the tree is balanced.

**Output:**

```text
true
```

## Key Idea

> Calculate subtree heights from the bottom up while using `-1` to signal an unbalanced subtree.

This avoids repeatedly calculating the height of the same subtrees and gives an efficient `O(n)` solution.
