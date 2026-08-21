# Diameter of Binary Tree

## Problem

Given the root of a binary tree, find the **diameter** of the tree.

The diameter is the length of the **longest path between any two nodes** in the binary tree.

> The length of a path is measured by the number of edges between the nodes.

## Approach

We use **Depth-First Search (DFS)** and calculate the height of every node.

For each node:

1. Find the height of its left subtree.
2. Find the height of its right subtree.
3. The longest path passing through the current node is:

```text
leftHeight + rightHeight
```

4. Update the maximum diameter.
5. Return the height of the current node:

```text
1 + max(leftHeight, rightHeight)
```

### Why `lh + rh`?

Consider this tree:

```text
        1
       / \
      2   3
     / \
    4   5
```

At node `2`:

```text
left height  = 1
right height = 1

diameter through 2 = 1 + 1 = 2
```

At node `1`:

```text
left height  = 2
right height = 1

diameter through 1 = 2 + 1 = 3
```

Therefore, the diameter is `3`.

## Implementation

```java
class Solution {
    public int diameterOfBinaryTree(TreeNode root) {
        int[] dia = new int[1];

        height(root, dia);

        return dia[0];
    }

    private int height(TreeNode node, int[] dia) {
        if (node == null) {
            return 0;
        }

        int lh = height(node.left, dia);
        int rh = height(node.right, dia);

        dia[0] = Math.max(dia[0], lh + rh);

        return 1 + Math.max(lh, rh);
    }
}
```

## Why use `int[] dia`?

Java passes primitive `int` values **by value**. If we passed `int dia` to `height()`, changes made inside the recursive function would not update the value in `diameterOfBinaryTree()`.

Using an array allows all recursive calls to share the same mutable value:

```java
int[] dia = new int[1];
```

`dia[0]` stores the maximum diameter found so far.

## Complexity

Let `n` be the number of nodes.

### Time Complexity

```text
O(n)
```

Every node is visited exactly once.

### Space Complexity

```text
O(h)
```

where `h` is the height of the tree, due to the recursive call stack.

* Balanced tree: `O(log n)`
* Skewed tree: `O(n)`

## Key Insight

The important idea is:

```text
diameter at node = height(left) + height(right)
```

while:

```text
height(node) = 1 + max(height(left), height(right))
```

The recursive function simultaneously **calculates heights** and **tracks the maximum diameter**.
