# Binary Tree Maximum Path Sum

## Problem

Given the root of a binary tree, find the **maximum path sum**.

A path can start and end at any node in the tree, but it must follow connected parent-child relationships. The path does not need to pass through the root.

## Approach

Use **Depth-First Search (DFS)** with recursion.

For every node:

1. Calculate the maximum path sum coming from the left subtree.
2. Calculate the maximum path sum coming from the right subtree.
3. Ignore negative subtree contributions using `Math.max(0, ...)`.
4. Consider a path passing through the current node:

```text
left + node.val + right
```

5. Update the global maximum.
6. Return the maximum single-branch path to the parent:

```text
node.val + max(left, right)
```

The important distinction is that a path passing **through** a node can use both children, while the value returned to the parent can use only one child.

## Java Solution

```java
class Solution {
    public int maxPathSum(TreeNode root) {
        int[] maxVal = new int[1];
        maxVal[0] = Integer.MIN_VALUE;

        maxPathDown(root, maxVal);

        return maxVal[0];
    }

    private int maxPathDown(TreeNode node, int[] maxVal) {
        if (node == null) {
            return 0;
        }

        int left = Math.max(0, maxPathDown(node.left, maxVal));
        int right = Math.max(0, maxPathDown(node.right, maxVal));

        // Path passing through the current node
        maxVal[0] = Math.max(
            maxVal[0],
            left + right + node.val
        );

        // Maximum path that can be extended to the parent
        return node.val + Math.max(left, right);
    }
}
```

## Example

Consider the following tree:

```text
       -10
       /  \
      9    20
          /  \
         15   7
```

The maximum path is:

```text
15 → 20 → 7
```

Its sum is:

```text
15 + 20 + 7 = 42
```

Therefore:

```text
Output: 42
```

## Why Negative Values Are Ignored

For each child:

```java
Math.max(0, maxPathDown(child, maxVal))
```

If a subtree produces a negative path sum, including it would only make the current path worse. Therefore, we treat its contribution as `0`.

However, `maxVal` is initialized to `Integer.MIN_VALUE` rather than `0` because the entire tree can contain negative values.

For example:

```text
    -3
    / \
  -5  -2
```

The correct answer is `-2`, not `0`.

## Complexity

### Time Complexity

```text
O(n)
```

Each node is visited exactly once.

### Space Complexity

```text
O(h)
```

where `h` is the height of the tree, due to the recursive call stack.

For a balanced tree:

```text
O(log n)
```

For a completely skewed tree:

```text
O(n)
```

## Key Insight

The most important idea is:

```java
// For global answer: use both sides
left + node.val + right

// For parent: use only one side
node.val + Math.max(left, right)
```

This distinction allows the DFS to find paths that can begin and end at **any two nodes**, rather than restricting the path to start at the root.

## Edge Cases

* Single-node tree
* Tree containing only negative values
* Completely left-skewed tree
* Completely right-skewed tree
* Root having only one child
* Mixed positive and negative values
