package algorithm;

/**
 * 删除数组中的重复元素，不能使用额外的空间
 */
public class RemoveDuplicatesFromArray {

    public static int removeDupliactes(int[] nums){
            if(nums == null || nums.length == 0){
                return 0;
            }
        int index = 0;
        for(int i = 1;i<nums.length;i++){
              if(nums[i] != nums[index]){
                  nums[++index] = nums[i];
              }
        }
        return index + 1;
    }

    public static void main(String []args){
        int[] nums = {1,2,3,3,4,5};
        System.out.println(removeDupliactes(nums));
    }
}
