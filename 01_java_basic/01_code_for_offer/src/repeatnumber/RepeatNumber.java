package repeatnumber;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 重复数字，找出数组中重复的数字
 */
public class RepeatNumber {

    /**
     * 思路：遍历数组，查看哈希表是否有该元素，有，即找到了重复元素，没有，则把该元素放入哈希表。
     * 时间复杂度O(N)，空间复杂度O(N)
     * @param nums
     * @return
     */
     public static List<Integer> repeatNumber(int[] nums){
         Map<Integer,Integer> map = new HashMap<>();
         List<Integer> temp = new ArrayList<>();
         for(int i=0;i<nums.length;i++){
             if(map.containsKey(nums[i])){
                 temp.add(nums[i]);
                 continue;
//                 return nums[i];
             }
             map.put(nums[i],i);
         }
         return temp;
//        throw  new IllegalArgumentException("no repeatNumber");
     }

    /**
     *思路：这种思路是书上给的标准答案，自己没有想到。因为这里有一个前提条件，长度未n的数组的数字在0到n-1范围内。对比上面的解法，这种解法空间复杂度O(1)，在已有数组进行操作。
     * 数组的数在0到n-1之间，那么，排序该数组，数字i应该在下标i的位置，由于有重复数字，则可能有的位置有重复数字，有的位置没有数字，下标不同，数字相等，则找到重复数字。
     * 时间复杂度O(N)，空间复杂度O(1)。虽然有两个循环，但每个数字最多交换两次就能找到它的位置
     * @param nums
     * @return
     */
     public static List<Integer> repeatNumber1(int[] nums){
         if(nums == null || nums.length<=0){
            throw new IllegalArgumentException("输入的数组为空");
         }
         for(int i=0;i<nums.length;i++){
             if(nums[i]<0 || nums[i]>nums.length-1){
                 throw new IllegalArgumentException("输入的数组内容与题目不符");
             }
         }
         List<Integer> temp = new ArrayList<>();
         for(int i =0;i<nums.length;i++){
             while(nums[i] != i){
                 if(nums[i] == nums[nums[i]]){
                      temp.add(nums[i]);
                 }
                 int flag = nums[i];
                 nums[i] = nums[flag];
                 nums[flag] = flag;
             }
         }

         return temp;
     }

     public static void main(String []args){
         List<Integer> i = RepeatNumber.repeatNumber(new int[]{1,2,3,4,6,2,4});
         List<Integer> j = RepeatNumber.repeatNumber(new int[]{1,2,3,4,6,2,4});
         System.out.print(i);
         System.out.print(j);
     }
}
