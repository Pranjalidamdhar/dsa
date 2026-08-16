# Preorder Traversal of Binary Tree

## Problem Statement

Given the root of a binary tree, return the **preorder traversal** of its nodes' values.

In **preorder traversal**, nodes are visited in the following order:

**Root → Left → Right**

---

## Approach

This solution uses an **iterative approach with a Stack** instead of recursion.

### Steps

1. Create an empty list `preOrder` to store the traversal.
2. If `root` is `null`, return the empty list.
3. Push the root node into the stack.
4. While the stack is not empty:

   * Pop a node from the stack.
   * Add its value to `preOrder`.
   * Push the **right child** into the stack if it exists.
   * Push the **left child** into the stack if it exists.
5. Return the `preOrder` list.

### Why push the right child first?

A stack follows **LIFO (Last In, First Out)**.

Since we want:

`Root → Left → Right`

we push the **right child first**, followed by the **left child**. This ensures the left child is processed first.

---

## Example

Consider the following binary tree:

```text
        1
       / \
      2   3
     / \
    4   5
```

Preorder traversal:

```text
1 → 2 → 4 → 5 → 3
```

Output:

```text
[1, 2, 4, 5, 3]
```

---

## Complexity Analysis

* **Time Complexity:** `O(n)`

  * Each node is visited exactly once.

* **Space Complexity:** `O(n)`

  * The stack can contain up to `O(n)` nodes in the worst case.

---

## Java Implementation

```java
class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> preOrder = new ArrayList<>();

        if (root == null) {
            return preOrder;
        }

        Stack<TreeNode> st = new Stack<>();
        st.push(root);

        while (!st.isEmpty()) {
            root = st.pop();
            preOrder.add(root.val);

            // Push right first
            if (root.right != null) {
                st.push(root.right);
            }

            // Push left second
            if (root.left != null) {
                st.push(root.left);
            }
        }

        return preOrder;
    }
}
```

## Key Concept

> **Preorder = Root → Left → Right**

For iterative preorder traversal using a stack:

**Push Right → Push Left → Process Left First**
