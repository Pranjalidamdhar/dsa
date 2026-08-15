# Binary Tree Level Order Traversal

## Problem

Given the root of a binary tree, return the **level order traversal** of its nodes' values.

Level order traversal visits the tree **level by level**, from left to right.

### Example

For the binary tree:

```text
        3
       / \
      9  20
         / \
        15  7
```

The level order traversal is:

```text
[
  [3],
  [9, 20],
  [15, 7]
]
```

## Approach

This solution uses **Breadth-First Search (BFS)** with a queue.

1. If the root is `null`, return an empty list.
2. Add the root node to a queue.
3. While the queue is not empty:

   * Store the current queue size. This represents the number of nodes at the current level.
   * Process exactly that many nodes.
   * Add each node's value to the current level list.
   * Add its left and right children to the queue if they exist.
4. Add the current level list to the final result.
5. Return the result.

## Java Implementation

```java
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        List<List<Integer>> wrapList = new LinkedList<List<Integer>>();

        if (root == null) {
            return wrapList;
        }

        queue.offer(root);

        while (!queue.isEmpty()) {
            int levelnum = queue.size();
            List<Integer> subList = new ArrayList<Integer>();

            for (int i = 0; i < levelnum; i++) {
                if (queue.peek().left != null) {
                    queue.offer(queue.peek().left);
                }

                if (queue.peek().right != null) {
                    queue.offer(queue.peek().right);
                }

                subList.add(queue.poll().val);
            }

            wrapList.add(subList);
        }

        return wrapList;
    }
}
```

## Complexity

* **Time:** `O(n)` — every node is processed exactly once.
* **Space:** `O(n)` — the queue can contain up to `O(n)` nodes in the worst case.

## Key Idea

The important part is:

```java
int levelnum = queue.size();
```

At the beginning of each iteration, `levelnum` tells us exactly how many nodes belong to the **current level**. Processing only those nodes ensures that each level gets its own list.

## Pattern to Remember

This is a common **BFS / level-by-level tree traversal** pattern:

```text
while queue is not empty:
    size = queue.size()

    repeat size times:
        process current node
        add children to queue

    save current level
```

This pattern is useful for many binary-tree problems involving levels, minimum depth, averages per level, zigzag traversal, and level-based calculations.
