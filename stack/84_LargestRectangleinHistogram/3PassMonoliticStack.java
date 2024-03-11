import java.util.Arrays;
import java.util.Stack;
// # 时间复杂度：O(n)
// # 空间复杂度：O(n)
// # 思路 1： stack + 3 pass. 利用單調棧維護遞增元素，pop 出比自己大的棧元素，
//           目的是找到最接近 heights[i] 的較小元素
class Solution1 {
  // heights = [2,1,5,6,2,3]
  public int largestRectangleArea(int[] heights) {
      int n = heights.length;
      Stack<Integer> stack = new Stack<>();
      // prevSmaller: [-1,-1,-1,-1,-1,-1]
      int[] prevSmaller = new int[n];
      Arrays.fill(prevSmaller, -1);
      // heights = [2,1,5,6,2,3]
      //                ^
      // stack: [1] 棧頂
      // prevSmaller: [-1,-1,1,-1,-1,-1]
      for (int i = 0; i < n; i++) {
          // 如果棧頂元素大於等於當前索引 i 上的高度，出棧棧頂元素，
          // 棧頂元素永遠都小於索引 i 上的高度
          while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
            stack.pop();  
          }
          // stack 是空的，表示當前索引 i 是 heights 最小值
          // stack 不為空，prevSmaller[i] 紀錄最接近的最小值索引
          if (!stack.isEmpty()) {
            prevSmaller[i] = stack.peek();
          }
          stack.push(i);
      }
      // prevSmaller: [-1,-1,1,2,1,4]
      stack.clear();
      int[] nxtSmaller = new int[n];
      Arrays.fill(nxtSmaller, n);
      // nxtSmaller: [6,6,6,6,6,6]
      for (int i = n - 1; i >= 0; i--) {
          while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
              stack.pop();  
          }
          if (!stack.isEmpty()) {
              nxtSmaller[i] = stack.peek();
          }
          stack.push(i);
      }
      // nxtSmaller: [1,6,4,4,6,6]

      int ans = 0;
      for (int i = 0; i < n; i++) {
          int w = nxtSmaller[i] - prevSmaller[i] - 1;
          ans = Math.max(ans, heights[i] * w);
      }

      return ans;
  }
}