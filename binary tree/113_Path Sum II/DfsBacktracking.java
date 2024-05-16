import java.util.ArrayList;
import java.util.List;

// # 时间复杂度：O(n^2)
// # 空间复杂度：O(n^2)
// # 思路 1：DFS. 当我们遍历到叶子节点，且此时路径和恰为目标和时，我们就找到了一条满足条件的路径。

class Solution {
  List<List<Integer>> res;
  List<Integer> currPath;

  public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
    res = new ArrayList<>();
    currPath = new ArrayList<>();

    dfs(root, targetSum, 0);

    return res;
  }

  private void dfs(TreeNode node, int targetSum, int sum) {
    if (node == null) {
      return;
    }
    // 如果 sum 設成 global variable 會一直 sum up 每個 node.val
    // sum 如果是 function parameter，sum 會是當前 function call 的 variable
    sum += node.val;
    currPath.add(node.val);
    // 如果 currPath 的 sum 達到 targetSum，這邊最壞情況下會再跑 O(n)，複製 currPath ArrayList
    if (node.left == null && node.right == null && sum == targetSum) {
      res.add(new ArrayList<>(currPath));
    }

    dfs(node.left, targetSum, sum);
    dfs(node.right, targetSum, sum);
    // 這邊 backtracking 的原因
    // 如果當前節點是左子樹，回到當前節點的根節點，走右子樹
    // 如果當前節點是右子樹，回到當前節點的根節點，繼續回朔到根節點的父節點
    currPath.remove(currPath.size() - 1);
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