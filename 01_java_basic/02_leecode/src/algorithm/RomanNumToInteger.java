package algorithm;


import java.util.List;

/**
 * 罗马数字转换为整数
 */
public class RomanNumToInteger {

    public static Integer romanNumToInteger(String romanNum){
        if(null == romanNum || romanNum.equals("")){
            throw new IllegalArgumentException("输入不能为空！");
        }
        int target = 0;
        char[] nums = romanNum.toCharArray();
        for(int i =0;i<nums.length;i++){
            String temp = "";
            if(i+1 < nums.length){
                temp = String.valueOf(nums[i+1]);
            }else{
                temp = "";
            }//防止数组越界
            switch(String.valueOf(nums[i]) + temp){
                case "IV" :
                    target = target + 4;
                    i++;
                    break;
                case "IX" :
                    target = target + 9;
                    i++;
                    break;
                case "XL" :
                    target = target + 40;
                    i++;
                    break;
                case "XC" :
                    target = target + 90;
                    i++;
                    break;
                case "CD" :
                    target = target + 400;
                    i++;
                    break;
                case "CM" :
                    target = target + 900;
                    i++;
                    break;
                 default:
                     switch (nums[i]){
                         case 'I':
                             target = target + 1;
                             break;
                         case 'V':
                             target = target + 5;
                             break;
                         case 'X':
                             target = target + 10;
                             break;
                         case 'L':
                             target = target + 50;
                             break;
                         case 'C':
                             target = target + 100;
                             break;
                         case 'D':
                             target = target + 500;
                             break;
                         case 'M':
                             target = target + 1000;
                             break;
                         default:
                             throw new IllegalArgumentException("输入得罗马字符串有非法数字！");
                     }
            }
        }

        return target;
    }

    public static void main(String []arg){
        System.out.print(RomanNumToInteger.romanNumToInteger(""));
    }
}
