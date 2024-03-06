from typing import List

# 时间复杂度: O(1)，遍历整个数独板需要O(9*9)，即O(81)的时间，这可以简化为O(1)，因为数独板的大小是固定的
# 空间复杂度: O(1)，最坏情况下数独板被全部填满，每个集合包含最多9个数字，空间复杂度为O(9*9*3)，即O(243)，这可以简化为O(1)
# 思路: 使用set跟踪每行、每列和每个3x3子宫格中已经出现的数字，从而验证数独的有效性

class Solution:
    def isValidSudoku(self, board: List[List[str]]) -> bool:
        # 初始化三个列表，每个列表都包含9个集合，用于追踪数独的每一行、每一列和每个3x3子宫格中出现过的数字
        rows = [set() for _ in range(9)]
        cols = [set() for _ in range(9)]
        areas = [set() for _ in range(9)]

        # 遍历题目给定的二维数组board
        for r in range(9):
            for c in range(9):
                # 如果当前单元格为空，则跳过本次迭代
                if board[r][c] == '.':
                    continue
                
                # 计算当前单元格所属的3x3子宫格索引
                area_index = (r // 3) * 3 + c // 3

                # 检查当前数字是否已在当前行、列、子宫格中出现过
                if board[r][c] in rows[r] or board[r][c] in cols[c] or board[r][c] in areas[area_index]:
                    return False # 如果出现，说明不符合数独规则
                else: # 如果未出现，就讲当前单元格中的数字加入hashset
                    rows[r].add(board[r][c])
                    cols[c].add(board[r][c])
                    areas[area_index].add(board[r][c])
                
        # 如果所有单元格都满足条件，说明数独有效
        return True

# Test
board = [
["5","3",".",".","7",".",".",".","."]
,["6",".",".","1","9","5",".",".","."]
,[".","9","8",".",".",".",".","6","."]
,["8",".",".",".","6",".",".",".","3"]
,["4",".",".","8",".","3",".",".","1"]
,["7",".",".",".","2",".",".",".","6"]
,[".","6",".",".",".",".","2","8","."]
,[".",".",".","4","1","9",".",".","5"]
,[".",".",".",".","8",".",".","7","9"]
]

solution = Solution()
print(solution.isValidSudoku(board)) # True