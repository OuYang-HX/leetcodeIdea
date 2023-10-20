// 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
//
// 算法的时间复杂度应该为 O(log (m+n)) 。 
//
// 
//
// 示例 1： 
//
// 
// 输入：nums1 = [1,3], nums2 = [2]
// 输出：2.00000
// 解释：合并数组 = [1,2,3] ，中位数 2
// 
//
// 示例 2： 
//
// 
// 输入：nums1 = [1,2], nums2 = [3,4]
// 输出：2.50000
// 解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
// 
//
// 
//
// 
//
// 提示： 
//
// 
// nums1.length == m 
// nums2.length == n 
// 0 <= m <= 1000 
// 0 <= n <= 1000 
// 1 <= m + n <= 2000 
// -10⁶ <= nums1[i], nums2[i] <= 10⁶ 
// 
//
// Related Topics 数组 二分查找 分治 👍 6825 👎 0


package editor.cn;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class LC4MedianOfTwoSortedArrays {
    public static void main(String[] args) {
        Solution solution = new LC4MedianOfTwoSortedArrays().new Solution();
        System.out.println(solution.findMedianSortedArrays(new int[]{1,3}, new int[]{2}));
    }

    // leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public double findMedianSortedArrays(int[] nums1, int[] nums2) {
            Solution3 solution = new Solution3();
            return solution.findMedianSortedArrays1(nums1, nums2);
        }
        class Solution2 {
            public double findMedianSortedArrays1(int[] nums1, int[] nums2){
                int count = 0;
                int maxCount = (nums1.length + nums2.length) / 2 + 1;
                int i = 0;
                int j = 0;
                int mid1 = 0;
                int mid2 = 0;
                if(nums1.length==0&&nums2.length==0)
                    return 0;
                while (count < maxCount) {
                    if (i < nums1.length) {
                        if (j < nums2.length) {
                            if (nums1[i] < nums2[j]) {
                                count++;
                                mid1 = mid2;
                                mid2 = nums1[i];
                                i++;
                            } else {
                                count++;
                                mid1 = mid2;
                                mid2 = nums2[j];
                                j++;
                            }
                        } else {
                            count++;
                            mid1 = mid2;
                            mid2 = nums1[i];
                            i++;
                        }
                    } else {
                        count++;
                        mid1 = mid2;
                        mid2 = nums2[j];
                        j++;
                    }
                }
                if ((nums1.length + nums2.length) % 2 == 1) {
                    return mid2;
                } else {
                    return (mid1 + mid2) / 2.0;
                }
            }
        }
        class Solution3 {
            public double findMedianSortedArrays1(int[] nums1, int[] nums2){
                if(nums1.length==0)
                    return nums2.length%2==1?nums2[nums2.length/2+1]:(nums2[nums2.length/2-1]+nums2[nums2.length])/2.0;
                if(nums2.length==0)
                    return nums1.length%2==1?nums1[nums1.length/2+1]:(nums1[nums1.length/2-1]+nums1[nums1.length])/2.0;
                SingleNum singleNum=new SingleNum();
                if(nums1.length<nums2.length)
                    return singleNum.f(nums1,nums2);
                return singleNum.f(nums2,nums1);
            }
            class SingleNum{
                public double f(int[] nums1, int[] nums2){
                    int midLeft=(nums1.length+nums2.length)/2;
                    //假设有较少的那个数组中有k个数位于中位数左边
                    //先处理偶数个
                    int left=1;
                    int right=nums1.length;
                    int k=(left+right)/2;
                    if((nums1.length+nums2.length)%2==0) {
                        while (k > left && k < right) {
                            if (nums1[k - 1] < nums2[midLeft - k] && nums1[k] > nums2[midLeft - k - 1]) {
                                return (Math.max(nums1[k - 1], nums2[midLeft - k - 1]) + Math.min(nums1[k], nums2[midLeft - k]));
                            } else if (nums1[k] < nums2[midLeft - k - 1]) {
                                left = k;
                                k = (left + right) / 2;
                            } else if (nums1[k - 1] > nums2[midLeft - k]) {
                                right = k;
                                k = (left + right) / 2;
                            }
                        }
                        if (k == left) {
                            return (nums2[midLeft - nums1.length - 1] + nums2[midLeft - nums1.length]) / 2.0;
                        }
                        return (nums2[midLeft - 1] + nums2[midLeft]) / 2.0;
                    }else {
                        while(k>=left&&k<=right){
                            if(nums1[k-1]<nums2[midLeft-k]&&nums1[k+1]>nums2[midLeft-k-1]){
                                return (Math.max(nums1[k-1],nums2[midLeft-k-1])+Math.min(nums1[k+1],nums2[midLeft-k]));
                            }else if(nums1[k+1]<nums2[midLeft-k-1]){
                                left=k;
                                k=(left+right)/2;
                            }else if(nums1[k-1]>nums2[midLeft-k]){
                                right=k;
                                k=(left+right)/2;
                            }
                        }
                        if(k==left){
                            return nums2[midLeft-nums1.length];
                        }
                        return nums2[midLeft];
                    }
                }
            }
        }
    }
// leetcode submit region end(Prohibit modification and deletion)

}