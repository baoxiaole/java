package algorithm;

/**
 * 移除数组中和给定值相同的元素
 */
public class RemoveElement {

    public static int removeElement(int[] nums,int val){
        if(nums == null || nums.length ==0){
            return 0;
        }
        int index = 0;
        for(int i=0;i<nums.length;i++){
            if(nums[index] != val){
                nums[index] = nums[i];
                index++;
            }
        }
         return index;
    }

    public static void main(String []args){
        int[] nums = {1,2,2,3,4};
        int val = 2;
        System.out.println(removeElement(nums,val));
    }
}
