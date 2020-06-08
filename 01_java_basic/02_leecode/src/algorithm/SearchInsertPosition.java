package algorithm;

/**
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 */
public class SearchInsertPosition {

    //思路：对于以排序数组的查找，用二分查找可以实现。
    public static int searchInsertPosition(int[] nums,int target){
        if(nums == null || nums.length ==0){
            return 0;
        }
        int start = 0;
        int end = nums.length - 1;
        while(start <= end){
            int middle = (start+end)/2;
            if(nums[middle] == target){
                return middle;
            }
            if(nums[middle] < target){
                start = middle + 1;
            }else{
                end = middle - 1;
            }
        }
        return start;
    }

    public static void main(String[] args){
        int[] nums = {1,3,5,6};
        int target = 8;
        System.out.println(searchInsertPosition(nums,target));
    }
}
