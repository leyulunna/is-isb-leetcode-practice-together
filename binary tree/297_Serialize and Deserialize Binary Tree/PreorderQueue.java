import java.util.LinkedList;
import java.util.Queue;

// # 时间复杂度：O(n)
// # 空间复杂度：O(n)
// # 思路 1： PreOrder + DFS + Queue. PreOrder 序列化，再利用 queue 一層一層用 preorder 的方式轉回成 TreeNode

class Solution {
  public class Codec {
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
      if (root == null) return "#";
      
      return Integer.toString(root.val) + "," + serialize(root.left) + "," + serialize(root.right);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
      // System.out.println(data);

      String[] dataList = data.split(",");
      Queue<String> queue = new LinkedList<>();
      for (String s: dataList) {
        queue.offer(s);
      }

      // queue = {"1","2","#","#","3","4"}

      return buildSubtree(queue);
    }

    private TreeNode buildSubtree(Queue<String> q) {
      if (q.isEmpty()) {
          return null;
      }

      String s = q.poll();

      if (s.equals("#")) {
          return null;
      }
      // System.out.println(s);
      TreeNode node = new TreeNode(Integer.valueOf(s));

      node.left = buildSubtree(q);
      node.right = buildSubtree(q);

      return node;
    }
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
