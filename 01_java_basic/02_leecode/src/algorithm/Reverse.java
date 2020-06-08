package algorithm;

/**
 * 反转数字
 */
public class Reverse {

    public static int reverse(int x){
        int target = 0;
        int len = 0;
        if(x>-10 && x <10){
            return x;
        }else if(x <= Integer.MIN_VALUE || x >= Integer.MAX_VALUE){
            return 0;
        }else if(x >=10){
             len = String.valueOf(x).length()-1;
        }else{
             len = String.valueOf(x).length()-2;
        }
        for(int i=len;i>=0;i--){
            int temp =  x/(int)Math.pow(10,i);
            x = x % (int)(Math.pow(10,i));
            target += temp*(Math.pow(10,len-i));
        }
        if(target >= Integer.MAX_VALUE || target <= Integer.MIN_VALUE){
            return 0;
        }
        return target;
    }

    public static void main(String []args){
        System.out.print(Reverse.reverse(2147483647));
    }
}
