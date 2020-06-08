package algorithm;

/**
 * 最大子序和。给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 */
public class MaxSubArray {

    //最暴力的解法，三次循环，O(N^3)
    public static int maxSubArrayON3(int[] nums){
        if(nums ==null || nums.length ==0){
            return 0;
        }
        int max = Integer.MIN_VALUE;
        int sum ;
        for(int i=0;i<nums.length;i++){//子序列左起点
            for(int j=i;j<nums.length;j++){//子序列右起点
                sum = 0;
                for(int k=i;k<=j;k++){//子序和
                    sum += nums[k];
                }
                if(sum > max){
                    max = sum;
                }
            }
        }
        return max;
    }

    //暴力法改进，两次循环，O(N^2)
    public static int maxSubArrayON2(int[] nums){
        if(nums ==null || nums.length ==0){
            return 0;
        }
        int max = Integer.MIN_VALUE;
        int sum;
        for(int i=0;i<nums.length;i++){//子序列左起点
            sum = 0;
            for(int j=i;j<nums.length;j++){//子序列右起点
                sum += nums[j];//计算子序列和，根据前一次的序列来计算新的序列
                if(sum > max){
                    max = sum;
                }
            }
        }
        return max;
    }

    //动态规划，若子序列和为负数，则舍弃。最大子序列的起点和终点都不可能是负数。O(N)
    public static int maxSubArrayON(int[] nums){
        if(nums ==null || nums.length ==0){
            return 0;
        }
        int current=nums[0];
        int sum=nums[0];
        for(int i=1;i<nums.length;i++){
            if(current < 0){
                current = nums[i];
            }else{
                current += nums[i];
            }
            if(current > sum){
                sum = current;
            }
        }
        return sum;
    }

    //分治法，求出左边最大子序和，求出右边最大子序和，求出跨左右的最大子序和，比较三者最大者。O(nlogn)
    public static int maxSubArrayNLOGN(int[] nums,int left,int right){
        if(nums ==null || nums.length ==0){
            return 0;
        }
        //递归的基准情况:待处理序列只有一个元素
        if(left == right){
            if(nums[left] > 0){
                return nums[left];
            }else{
                return 0;
            }
        }
        //递归求出左半部分和右半部分的最大子序列和
        int center = (left + right) / 2;
        int maxLeftSum = maxSubArrayNLOGN(nums, left, center);
        int maxRightSum = maxSubArrayNLOGN(nums, center + 1, right);
        //求出左半部分中包含最右边元素的子序列的最大和
        int maxLeftBorderSum = 0, leftBorderSum = 0;
        for(int i = center; i >= left; i--) {
            leftBorderSum += nums[i];
            if(leftBorderSum > maxLeftBorderSum) {
                maxLeftBorderSum = leftBorderSum;
                }
            }
        //求出右半部分中包含最左边元素的子序列的最大和
        int maxRightBorderSum = 0, rightBorderSum = 0;
        for(int i = center + 1; i <= right; i++) {
            rightBorderSum += nums[i];
            if(rightBorderSum > maxRightBorderSum) {
                maxRightBorderSum = rightBorderSum;
            }
        }
        //跨越两个部分的最大子序列和
        int maxLeftRightSum = maxLeftBorderSum + maxRightBorderSum;
        //maxLeftSum、maxRightSum、maxLeftRightSum中的最大值即为最大子序列和
        int maxSubSum = 0;
        maxSubSum = maxLeftSum > maxRightSum ? maxLeftSum: maxRightSum;
        maxSubSum = maxSubSum > maxLeftRightSum ? maxSubSum: maxLeftRightSum;
        return maxSubSum;
    }

    public static void main(String[] args){
        int[] nums = {1,-8,-2,5,-8,3};
        System.out.println(maxSubArrayON3(nums));
        System.out.println(maxSubArrayON2(nums));
        System.out.println(maxSubArrayON(nums));
        System.out.println(maxSubArrayNLOGN(nums,0,nums.length-1));
    }
}
