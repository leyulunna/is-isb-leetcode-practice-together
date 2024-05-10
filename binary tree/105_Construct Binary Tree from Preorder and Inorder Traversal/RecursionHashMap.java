import java.util.HashMap;
import java.util.Map;

// # 时间复杂度：O(n)
// # 空间复杂度：O(n)
// # 思路 1：Recursion. 利用 map 紀錄 inorder 的 <nodeVal, index> 資訊後，
//           開始遍歷 preorder array，利用 inorder map 去建立 preorder node 的左邊子樹
//           與右邊子樹。調整 inorder left(left), right(right) 邊界，
//           建立每一個 preorder 元素的 subtree。

class Solution {
  int preorderIdx;
  Map<Integer, Integer> inorderMap;

  public TreeNode buildTree(int[] preorder, int[] inorder) {
    inorderMap = new HashMap<>();
    int n = preorder.length;
    for (int i = 0; i < n; i++) {
        inorderMap.put(inorder[i], i);
    }

    return build(preorder, inorder, 0, n - 1);
  }

  private TreeNode build(int[] preorder, int[] inorder, int left, int right) {
    if (left > right) {
        return null;
    }

    TreeNode node = new TreeNode(preorder[preorderIdx]);
    preorderIdx++;
    int inorderIdx = inorderMap.get(node.val);

    node.left = build(preorder, inorder, left, inorderIdx - 1);
    node.right = build(preorder, inorder, inorderIdx + 1, right);

    return node;
  }

  class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
      this.val = val;
      this.left = left;
      this.right = right;
    }
  }
}
