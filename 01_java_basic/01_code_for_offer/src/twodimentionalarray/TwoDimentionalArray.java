package twodimentionalarray;

/**
 * 二维数组中的查找
 */
public class TwoDimentionalArray {

    public static boolean findNumberFromTwoDimentionalArray(int[][] arr,int target){
        if(arr == null || arr.length == 0){
            return false;
        }
        int rows = arr.length-1;//行数
        int columns = arr[0].length-1;//列数
        int row = 0;
        while(row<=rows && columns >= 0){
            if(arr[row][columns] == target){
                return true;
            }else if(arr[row][columns] > target){
                columns--;
            }else{
                row++;
            }
        }
        return false;
    }

    public static void main(String []args){
        System.out.print(TwoDimentionalArray.findNumberFromTwoDimentionalArray(new int[][]{{1,4,7},{2,5,8},{3,6,9}},10));
    }
}
