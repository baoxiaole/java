package smallestinrotatearray;

/**
 * 旋转数组的最小值。旋转数组的定义：把一个排序数组的前若干项放到数组的尾部。
 */
public class SmallestInRotateArray {

    //思路一：遍历一次数组，就能找到最小值。时间复杂度O(n)
    public static int smallest(int[] rotateArr){
        if(rotateArr == null || rotateArr.length==0){
            return 0;
        }
        int temp = rotateArr[0];
        for(int i=1;i<rotateArr.length;i++){
            if(rotateArr[i] < temp){
                temp = rotateArr[i];
            }
        }
        return temp;
    }

    //思路二：考虑旋转数组的特点，它由两个排序的递增数组构成，这里可以用二分查找的思路来处理。时间复杂度为O(logn)
    //第一个指针总是指向前面递增的数组，第二个指针总是指向后面递增的数组，最终结果是第一个指针指向前面递增数组的最后一个元素，
    // 第二个指针指向后面递增数组的第一个元素，也就是我们要找的值
    public static int smallestBinary(int[] rotateArr){
        if(rotateArr == null || rotateArr.length==0){
            return 0;
        }
        int start = 0;
        int end = rotateArr.length - 1;
        int middle = 0;
        while(start != end -1){
            middle = (start + end)/2;
            if(rotateArr[middle] >= rotateArr[start]){//中间值大于起始值，说明最小值在后半段
                start = middle;
            }else{
                end = middle;
            }
        }
        return rotateArr[end];
    }

    //这里还有特例，把排序数组前0个元素移动到数组尾部，即数组本身就是一个旋转数组
    //还有一个特例，{1,0,1,1,1}，这是{0,1,1,1,1}的旋转数组，这种情况还是只能用顺序查找

    public static int smallestBinaryUpdate(int[] rotateArr){
        if(rotateArr == null || rotateArr.length==0){
            return 0;
        }
        int start = 0;
        int end = rotateArr.length - 1;
        int middle = 0;
        //数组本身就是旋转数组
        if(rotateArr[start] < rotateArr[end]){
            return rotateArr[start];
        }
        while(start != end -1){
            middle = (start + end)/2;
            //特殊数组
            if(rotateArr[start] == rotateArr[end] && rotateArr[start] == rotateArr[middle]){
                return MinInOrder(rotateArr,start,end);
            }
            if(rotateArr[middle] >= rotateArr[start]){//中间值大于起始值，说明最小值在后半段
                start = middle;
            }else{
                end = middle;
            }
        }
        return rotateArr[end];
    }

    public static int MinInOrder(int[] arr,int start,int end){
         int result = arr[start];
         for(int i = start+1;i<end;i++){
             if(arr[i]<result){
                 result = arr[i];
             }
         }
         return result;
    }

    public static void main(String []args){
//        int[] arr = {1,0,1,1,1,1};
//        int[] arr = {1,2,3,4,5,6};
        int[] arr = {3,4,5,1,1,2};
        System.out.println(smallest(arr));
        System.out.println(smallestBinary(arr));
        System.out.println(smallestBinaryUpdate(arr));
    }
}
