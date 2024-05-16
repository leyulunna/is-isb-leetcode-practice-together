// # 时间复杂度：O(n)
// # 空间复杂度：O(n)

class Solution {
  int greatCount;

  public int goodNodes(TreeNode root) {
    greatCount = 0;
    goThrough(root, root.val);
    return greatCount;
  }

  private void goThrough(TreeNode node, int greaterVal) {
    if (node == null) {
      return;
    }

    if (node.val >= greaterVal) {
      greatCount += 1;
    }

    greaterVal = Math.max(greaterVal, node.val);

    goThrough(node.left, greaterVal);
    goThrough(node.right, greaterVal);
  }

  class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
      this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
      this.val = val;
      this.left = left;
      this.right = right;
    }
  }
}