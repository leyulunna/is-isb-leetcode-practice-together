import heapq
from typing import List

# 时间复杂度: O(nlogk)，其中n是数组的长度。我们首先遍历原数组，并使用哈希表记录出现次数，共需O(n)的时间；随后，我们遍历「出现次数数组」，由于堆的大小至多为k，每次堆操作需要O(logk)的时间，共需O(nlogk)的时间
# 空间复杂度: O(n)，哈希表的大小为O(n)，堆的大小为O(k)，共需O(n)的空间
# 思路：哈希表 + 最小堆，首先使用哈希表统计每个元素出现的频率，然后使用最小堆找出频率最大的k个元素

class Solution:
    def topKFrequent(self, nums: List[int], k: int) -> List[int]:
        # 使用哈希表统计每个元素出现的频率，key为元素nums[i]，value为频率
        frequency_map = {} 
        for i in range(len(nums)):
            frequency_map[nums[i]] = frequency_map.get(nums[i], 0) + 1
        
        # 初始化一个最小堆，用于存储元素及其出现频率，格式为(freq, key)
        min_heap = [] 

        # 遍历哈希表中的元素及其频率
        for key, freq in frequency_map.items():
            # 将元素及其频率以元组(freq, key)的形式加入最小堆
            # 注意：堆是基于频率进行排序的，因此频率放在元组的第一个位置
            heapq.heappush(min_heap, (freq, key))
            # 如果堆的大小超过了k，就弹出堆顶元素（即频率最小的元素），堆中始终保留目前频率最高的k个元素
            if len(min_heap) > k:
                heapq.heappop(min_heap)
        
        # 从堆中弹出所有元素并将它们的值（即nums中的元素）存入结果列表
        result = [0] * k
        for i in range(k):
            result[i] = heapq.heappop(min_heap)[1]

        return result

# Test
nums = [1,1,1,2,2,3]
k = 2
solution = Solution()
print(solution.topKFrequent(nums, k)) # [2, 1]