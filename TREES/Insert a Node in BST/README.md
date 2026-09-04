# Insert into a Binary Search Tree

## Problem Statement

Given the root node of a **Binary Search Tree (BST)** and an integer `val`, insert `val` into the BST and return the root of the resulting tree.

The inserted value should maintain the properties of a Binary Search Tree:

* Values smaller than a node are placed in the **left subtree**.
* Values greater than or equal to a node are placed in the **right subtree**.

---

## Approach

We use an **iterative approach** to insert the new value.

### Steps

1. If the `root` is `null`, create a new node containing `val` and return it.
2. Start from the root using a pointer `curr`.
3. Compare `val` with `curr.val`:

   * If `curr.val <= val`:

     * Move to the right subtree if it exists.
     * Otherwise, insert the new node as the right child.
   * If `curr.val > val`:

     * Move to the left subtree if it exists.
     * Otherwise, insert the new node as the left child.
4. Return the original `root`.

---

## Example

### Input

```text
BST:

       4
      / \
     2   7
    / \
   1   3

val = 5
```

### Output

```text
       4
      / \
     2   7
    / \  /
   1   3 5
```

The value `5` is greater than `4`, so we move right to `7`.

Since `5 < 7`, we move to the left of `7`.

The left child of `7` is empty, so `5` is inserted there.

---

## Code

```java
class Solution {
    public TreeNode insertIntoBST(TreeNode root, int val) {

        // If tree is empty, create a new root
        if (root == null) {
            return new TreeNode(val);
        }

        TreeNode curr = root;

        while (true) {

            // Move to the right subtree
            if (curr.val <= val) {

                if (curr.right != null) {
                    curr = curr.right;
                } else {
                    curr.right = new TreeNode(val);
                    break;
                }

            } 
            // Move to the left subtree
            else {

                if (curr.left != null) {
                    curr = curr.left;
                } else {
                    curr.left = new TreeNode(val);
                    break;
                }
            }
        }

        return root;
    }
}
```

---

## Complexity Analysis

### Time Complexity

**O(h)**

Where `h` is the height of the BST.

* In the best case, the tree is balanced: **O(log n)**
* In the worst case, the tree is skewed: **O(n)**

### Space Complexity

**O(1)**

The solution uses only a `curr` pointer and does not use recursion or an additional data structure.

---

## Key Concept

The important property of a Binary Search Tree is:

```text
          Root
         /    \
     Smaller  Greater/Equal
```

At every node, we decide whether to go **left or right** based on the value we want to insert.

This makes BST insertion efficient because we only follow **one path from the root to an empty position**.
