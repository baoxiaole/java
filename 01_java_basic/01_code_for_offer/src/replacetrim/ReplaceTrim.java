package replacetrim;

/**
 * 替换字符串中得空格
 */
public class ReplaceTrim {

    public static String replaceTrim(String str){
         String target =  str.replaceAll(" ","%20");

         //遍历字符串，看一共有多少个空格
        char[] chars = str.toCharArray();
        int trimCount = 0;
        for(int i=0;i<chars.length;i++){
            if(chars[i]==' '){
                trimCount++;
            }
        }
        //new一个新数组，数组长度为原来数组得长度+tirmCount*2
        int oldLength = chars.length;
        int newLength = oldLength + trimCount*2;
        char[] newChars = new char[newLength];
        //从后往前遍历数组
        int i = oldLength - 1;
        int j = newLength - 1;
        while(i >= 0){
            if(chars[i] == ' '){
                newChars[j] = '0';
                newChars[j-1] = '2';
                newChars[j-2] = '%';
                j -= 3;
            }else{
                newChars[j] = chars[i];
                j--;
            }
            i--;
        }
        target = new String(newChars);
         return target;
    }

    public static void main(String []args){
        System.out.print(ReplaceTrim.replaceTrim("123 123 "));
    }
}
