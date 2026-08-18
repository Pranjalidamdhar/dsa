# Inorder Traversal of Binary Tree

## Problem Statement

Given the root of a binary tree, return the **inorder traversal** of its nodes' values.

In **inorder traversal**, nodes are visited in the following order:

**Left → Root → Right**

### Example

For the following tree:

```text
        1
         \
          2
         /
        3
```

The inorder traversal is:

```text
[1, 3, 2]
```

## Approach

This solution uses an **iterative approach with a Stack** instead of recursion.

### Algorithm

1. Create an empty `List` to store the inorder traversal.
2. Create a `Stack<TreeNode>` to keep track of nodes.
3. Start with `node = root`.
4. Keep moving toward the **left subtree** and push each node onto the stack.
5. When there is no left node:

   * Pop a node from the stack.
   * Add its value to the result.
   * Move to its **right subtree**.
6. Repeat until both the stack is empty and `node` is `null`.
7. Return the result list.

## Code

```java
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {

        List<Integer> inorder = new ArrayList<>();
        Stack<TreeNode> st = new Stack<>();

        TreeNode node = root;

        while (true) {

            // Go to the leftmost node
            if (node != null) {
                st.push(node);
                node = node.left;
            }

            else {
                // Stop when there are no nodes left to process
                if (st.isEmpty()) {
                    break;
                }

                // Process the node
                node = st.pop();
                inorder.add(node.val);

                // Move to the right subtree
                node = node.right;
            }
        }

        return inorder;
    }
}
```

## Why Use a Stack?

Recursion naturally maintains the path of nodes using the **call stack**.

In the iterative solution, we explicitly use a `Stack<TreeNode>` to store the nodes that need to be processed after visiting their left subtree.

For example:

```text
        1
       / \
      2   3
     / \
    4   5
```

Processing order:

```text
Push 1
Push 2
Push 4
Pop 4 → result
Pop 2 → result
Move to 5
Pop 5 → result
Pop 1 → result
Move to 3
Pop 3 → result
```

Result:

```text
[4, 2, 5, 1, 3]
```

## Complexity Analysis

### Time Complexity

```text
O(n)
```

Every node is pushed and popped from the stack once.

### Space Complexity

```text
O(h)
```

where `h` is the height of the tree.

* Balanced tree: `O(log n)`
* Skewed tree: `O(n)`

## Key Concept

The main idea is:

```text
Go Left → Process Root → Go Right
```

The stack helps us remember the parent nodes while we travel down the left subtree.

## LeetCode

**Problem:** Binary Tree Inorder Traversal
**Pattern:** Tree Traversal + Stack
**Traversal:** Left → Root → Right
