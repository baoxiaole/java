package algorithm;

/**
 * 最长公共前缀
 */
public class LongestCommonPrefix {

    public static String longestCommonPrefix(String[] strs){
        if(strs == null || strs.length ==0){
            return "";
        }
        int len = strs[0].length();
        int count;
        for(int i=0;i<len;i++){
            count = strs.length-1;
            for(int j = 1;j<strs.length;j++){
                if(!strs[j].startsWith(strs[0])){
                      count--;
                      break;
                }
            }
            if(count < strs.length-1){
                strs[0] = strs[0].substring(0,strs[0].length()-1);
            }else{
                break;
            }
        }
        return strs[0];
    }

    public static void main(String []args){
           System.out.print(LongestCommonPrefix.longestCommonPrefix(new String[]{"awww","eaw","qaw"}));
    }
}
