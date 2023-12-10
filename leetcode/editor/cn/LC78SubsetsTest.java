//给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。 
//
// 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,2,3]
//输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
// 
//
// 示例 2： 
//
// 
//输入：nums = [0]
//输出：[[],[0]]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 10 
// -10 <= nums[i] <= 10 
// nums 中的所有元素 互不相同 
// 
//
// Related Topics 位运算 数组 回溯 👍 2200 👎 0

package editor.cn;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LC78SubsetsTest{
    @Test
    public void test(){
        Solution solution=new Solution();
        int[] nums={1,2};
        List<List<Integer>> res=solution.subsets(nums);
        List<List<Integer>> list = Arrays.asList(
                Arrays.asList(),
                Arrays.asList(1),
                Arrays.asList(2),
                Arrays.asList(1, 2)
        );
        Assertions.assertEquals(res,list);
    }
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<List<Integer>> subsets(int[] nums) {
            List<List<Integer>> res = new ArrayList<List<Integer>>();
            for (int i = 0; i < (1 << nums.length); i++) {
                List<Integer> sub = new ArrayList<Integer>();
                for (int j = 0; j < nums.length; j++)
                    if (((i >> j) & 1) == 1) sub.add(nums[j]);
                res.add(sub);
            }
            return res;
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}