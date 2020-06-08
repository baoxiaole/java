/**
 * 二分查找
 */
public class BinarySearch {

    public static int binarySearch(int[] array,int target){
        int left = 0;
        int right = array.length - 1;
        while(left <= right){
            int mid = (right+left)/2;
            if(array[mid] == target){
                return mid;
            }else if(array[mid] > target){
                right = mid - 1;
            }else{
                left = mid + 1;
            }
        }
        throw new IllegalArgumentException("未找到与目标值匹配的数");
    }

    public static void main(String []args){
        int i =BinarySearch.binarySearch(new int[]{1,3,5,6,7},5);
        System.out.print(i);
    }
}
