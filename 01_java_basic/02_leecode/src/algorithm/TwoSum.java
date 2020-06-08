package algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * 两数之和
 */
public class TwoSum {

    public static void main(String[] args){
        int[] nums = {2,4,9,6,7,3,5};
        int target = 8;
        System.out.println(twosum1(nums,target)[0]+","+twosum1(nums,target)[1]);
    }

    //暴力循环
    public static int[] twosum(int[] nums, int target){

        if(nums == null || nums.length < 2){
            throw new IllegalArgumentException("illegal nums");
        }

        for(int i=0;i<nums.length;i++){
            for(int j = i + 1;j<nums.length;j++){
                if(nums[i] + nums[j] == target){
                    return new int[]{i,j};
                }
            }
        }

        throw new IllegalArgumentException("no towsum solution");
    }

    public static int[] twosum1(int[] nums, int target){

        if(nums == null || nums.length < 2){
            throw new IllegalArgumentException("illegal nums");
        }

        Map<Integer,Integer> map = new HashMap<Integer,Integer>();
        for(int i=0;i<nums.length;i++){
            int temp = target - nums[i];
            if(map.containsKey(temp)){
                return new int[]{map.get(temp),i};
            }
            map.put(nums[i],i);
        }

        throw new IllegalArgumentException("no towsum solution");
    }
}
