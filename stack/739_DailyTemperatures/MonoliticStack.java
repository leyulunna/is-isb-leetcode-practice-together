import java.util.Stack;

// # 时间复杂度：O(n)
// # 空间复杂度：O(n)
// # 思路 1： stack. 利用單調棧拿到當前溫度的前一個較小溫度值的索引
class Solution1 {
  public int[] dailyTemperatures(int[] temperatures) {
      int n = temperatures.length;
      int[] days = new int[n];
      Stack<Integer> stack = new Stack<>();

      for (int i = 0; i < n; i++) {
          // 如果當前溫度比棧頂元素的溫度高，表示當前溫度是棧頂元素的下一個溫度較高值，
          // 因此 days[stack.peek()] 等於當前索引減去棧頂元素的索引，即是棧頂元素遇到下一個溫度較高值的間隔天數
          while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
            days[stack.peek()] = i - stack.peek();
            // days[stack.peek()] 已經拿到間隔天數，可以出棧棧頂元素的索引
            stack.pop();
          }

          stack.push(i);
      }

      return days;
  }
}
