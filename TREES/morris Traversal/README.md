# Inorder Traversal of Binary Tree — Morris Traversal

## 📌 Problem Statement

Given the root of a binary tree, return the **inorder traversal** of its nodes' values.

### Inorder Traversal

Inorder traversal visits nodes in the following order:

**Left → Root → Right**

For example:

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

---

## 💡 Approach — Morris Traversal

The solution uses **Morris Inorder Traversal**.

Normally, inorder traversal requires either:

* Recursion → `O(h)` space
* Stack → `O(h)` space

Morris Traversal avoids using both a stack and recursion by temporarily modifying the tree structure.

### How it works

We maintain a pointer `curr` starting from the root.

#### Case 1: `curr.left == null`

There is no left subtree.

So:

1. Add `curr.val` to the result.
2. Move to the right child.

```java
inorder.add(curr.val);
curr = curr.right;
```

#### Case 2: `curr.left != null`

We need to find the **inorder predecessor** of `curr`.

The predecessor is the rightmost node in `curr`'s left subtree.

```java
TreeNode prev = curr.left;

while(prev.right != null && prev.right != curr){
    prev = prev.right;
}
```

There are then two possibilities.

### Case 2A: Predecessor's right pointer is `null`

Create a temporary link from the predecessor back to `curr`.

```java
prev.right = curr;
curr = curr.left;
```

This allows us to return to `curr` after processing its left subtree.

### Case 2B: Predecessor's right pointer is already `curr`

This means the left subtree has been completely processed.

So:

1. Remove the temporary link.
2. Add `curr.val`.
3. Move to the right subtree.

```java
prev.right = null;
inorder.add(curr.val);
curr = curr.right;
```

---

## 🔄 Example

Consider:

```text
        1
         \
          2
         /
        3
```

### Step-by-step

* `1` has no left child → add `1`
* Move to `2`
* `2` has a left child (`3`)
* `3` is the predecessor of `2`
* Create a temporary link: `3 → 2`
* Move to `3`
* Add `3`
* Follow the temporary link back to `2`
* Remove the temporary link
* Add `2`

Result:

```text
[1, 3, 2]
```

---

## 🧠 Algorithm

1. Initialize an empty result list.
2. Set `curr = root`.
3. While `curr` is not `null`:

   * If `curr.left` is `null`:

     * Add `curr.val`.
     * Move to `curr.right`.
   * Otherwise:

     * Find the inorder predecessor of `curr`.
     * If predecessor's right pointer is `null`:

       * Set `predecessor.right = curr`.
       * Move to `curr.left`.
     * Otherwise:

       * Remove the temporary link.
       * Add `curr.val`.
       * Move to `curr.right`.
4. Return the result list.

---

## 💻 Java Solution

```java
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {

        List<Integer> inorder = new ArrayList<>();
        TreeNode curr = root;

        while (curr != null) {

            if (curr.left == null) {
                inorder.add(curr.val);
                curr = curr.right;
            } 
            else {

                TreeNode prev = curr.left;

                while (prev.right != null && prev.right != curr) {
                    prev = prev.right;
                }

                if (prev.right == null) {
                    prev.right = curr;
                    curr = curr.left;
                } 
                else {
                    prev.right = null;
                    inorder.add(curr.val);
                    curr = curr.right;
                }
            }
        }

        return inorder;
    }
}
```

---

## ⏱️ Complexity Analysis

### Time Complexity

**O(n)**

Each node is visited a constant number of times.

### Space Complexity

**O(1)** auxiliary space.

The algorithm does not use recursion or a stack.

> The returned `List<Integer>` itself requires **O(n)** space to store the answer. The **extra/auxiliary space** used by Morris traversal is `O(1)`.

---

## ✅ Advantages

* No recursion required.
* No stack required.
* Uses `O(1)` auxiliary space.
* Runs in `O(n)` time.
* Restores the original tree structure after traversal.

---

## 📚 Key Concept

The important idea behind Morris Traversal is:

```text
Find predecessor
      ↓
Create temporary link
      ↓
Process left subtree
      ↓
Return using temporary link
      ↓
Remove temporary link
      ↓
Process current node
      ↓
Move to right subtree
```

### Inorder Pattern

```text
Left → Root → Right
```

Morris Traversal is especially useful when an inorder traversal is required with **constant auxiliary space**.
