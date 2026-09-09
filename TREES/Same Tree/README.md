# Same Tree

## Problem

Given the roots of two binary trees `p` and `q`, determine whether the two trees are **identical**.

Two binary trees are considered the same if:

* They have the same structure.
* The corresponding nodes have the same values.

### Example

**Tree 1:**

```text
    1
   / \
  2   3
```

**Tree 2:**

```text
    1
   / \
  2   3
```

**Output:**

```text
true
```

---

## Approach

We use **recursion** to compare both trees node by node.

For every pair of corresponding nodes:

1. If either node is `null`, check whether both nodes are `null`.

   * If both are `null`, the trees are the same at this position.
   * If only one is `null`, the structures are different.
2. Compare the values of the two nodes.
3. Recursively compare their left subtrees.
4. Recursively compare their right subtrees.
5. The trees are the same only if all these conditions are true.

### Algorithm

```text
isSameTree(p, q):

    If p or q is null:
        return p == q

    If p.val != q.val:
        return false

    Compare left subtrees
    Compare right subtrees

    Return true if both subtrees are same
```

---

## Java Solution

```java
class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {

        if (p == null || q == null) {
            return p == q;
        }

        return (p.val == q.val)
                && isSameTree(p.left, q.left)
                && isSameTree(p.right, q.right);
    }
}
```

---

## Complexity Analysis

### Time Complexity

**O(n)**

Each corresponding node is visited once, where `n` is the number of nodes being compared.

### Space Complexity

**O(h)**

The recursive call stack can go up to the height `h` of the tree.

* Balanced tree: `O(log n)`
* Skewed tree: `O(n)`

---

## Key Concept

This problem is a good example of **recursive tree traversal**.

The important idea is:

> Compare the current nodes, then recursively compare their left and right subtrees.

If any node value or tree structure differs, the result is `false`.

---

## LeetCode

**Problem:** Same Tree
**Difficulty:** Easy
**Topic:** Binary Tree, Recursion, DFS
