import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// # 时间复杂度：O(n), O(n) 遍歷數組 nums 紀錄頻率、O(n) 遍歷頻率數組 freqArr 並將其放入對應的頻率 List
// # 空间复杂度：O(n), O(n) 紀錄頻率的哈希表、O(n) 頻率數組 freqArr
// # 思路 1：先建立紀錄 nums[i] 對應頻率的哈希表，遍歷並桶排序紀錄頻率的哈希表並將其放入數組 freqArr 裡對應的頻率 List

class Solution1 {
  public int[] topKFrequent(int[] nums, int k) {
      // build the frequency map
      Map<Integer, Integer> freqMap = new HashMap<>();
      for (int num : nums) {
          freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
      }

      // 使用桶排序將對應的頻率數組（頻率由小到大）
      List<Integer>[] freqArr = new List[nums.length + 1]; 
      for (int key: freqMap.keySet()) {
          int freq = freqMap.get(key);
          if (freqArr[freq] == null) {
              freqArr[freq] = new ArrayList<>();
          }
          freqArr[freq].add(key);
      }

      // 從頻率由小到大的數組取出前 K 高頻的數字
      List<Integer> getKFreq = new ArrayList<>();
      for (int i = freqArr.length - 1; i >= 0; i--) {
          if (freqArr[i] == null) continue; // skip 沒有此頻率
          getKFreq.addAll(freqArr[i]);
      }

      int[] res = new int[k];
      for (int i = 0; i < k; i++) {
          res[i] = getKFreq.get(i);
      }

      return res;
  }
}