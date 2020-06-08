package algorithm;

/**
 * 回文数
 */
public class IsPalindrome {

    public static boolean isPalindrome(int num){
        if(num < 0 || (num %10 ==0 && num != 0)){
             return false;
        }
        int revertedNumber = 0;
        while(num > revertedNumber) {
            revertedNumber = revertedNumber * 10 + num % 10;
            num /= 10;
        }
        return num == revertedNumber || num == revertedNumber/10;
    }

    public static void main(String []args){

        System.out.print(IsPalindrome.isPalindrome(12121));
    }
}
