import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// # 时间复杂度：O(n), O(n) 遍歷數組 nums 紀錄頻率、QuickSort 排序紀錄頻率的哈希表，最好情况下，f(N)=O(N)+f(N/2)，
//             但由于我们在每次递归的开始会先随机选取中枢元素，故出现最坏情况的概率很低。平均情况下，时间复杂度为 O(N)。
// # 空间复杂度：O(n), O(n) 紀錄頻率的哈希表
// # 思路 2：先建立紀錄 nums[i] 對應頻率的哈希表，QuickSort 排序紀錄頻率的哈希表，基於已排序的元素數量和 k 值來決定是否繼續遞歸

class Solution {
  List<int[]> freqList;

  public int[] topKFrequent(int[] nums, int k) {
      // build the frequency map
      Map<Integer, Integer> freqMap = new HashMap<>();
      for (int num : nums) {
          freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
      }
      // Convert to list of {num, frequency}
      freqList = new ArrayList<>();
      for (Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {
          freqList.add(new int[]{entry.getKey(), entry.getValue()});
      }
      // 開始依照 frequency 排序 res 數組
      int[] res = new int[k];
      quickSort(0, freqList.size() - 1, res, 0, k);
      return res; // 确保返回 res 数组
  }

  private void quickSort(int start, int end, int[] res, int resIndex, int k) {
      if (start > end) return; // 添加递归终止条件

      Collections.swap(freqList, start, start + (end - start) / 2); // 将选择的pivot移动到开始位置
      int pivot = freqList.get(start)[1]; // pivot 移动到 start 了
      int temp = start;

      for (int i = start + 1; i <= end; i++) {
          // 使用双指针把不小于基准值的元素放到左边，
          // 小于基准值的元素放到右边
          if (freqList.get(i)[1] >= pivot) {
              temp++;
              Collections.swap(freqList, temp, i);
          }
      }
      Collections.swap(freqList, start, temp); // 将pivot交换到它最终的位置

      // 更新递归调用中的逻辑错误
      if (k <= temp - start) {
          // 前 k 大的值在左侧的子数组里
          quickSort(start, temp - 1, res, resIndex, k);
          // quickSort(0, 1, res, 0, 2);
      } else {
          // 前 k 大的值等于左侧的子数组全部元素
          // 加上右侧子数组中前 k - (index - start + 1) 大的值
          for (int i = start; i <= temp; i++) {
              res[resIndex++] = freqList.get(i)[0];
          }
          if (k > temp - start + 1) {
              quickSort(temp + 1, end, res, resIndex, k - (temp - start + 1));
          }
      }
  }
}
