package algorithm;

import com.sun.xml.internal.ws.util.StringUtils;

/**
 * 实现str，返回第二个字符串在第一个字符串中第一次出现的位置
 * bxl
 * 2019/3/19 15:51
 */
public class ImplementStr {

    public static void main(String[] args) throws Exception {
        System.out.println(strStr("1","1"));
    }

    public static int strStr(String haystack,String needle) throws Exception{
        //两者其一为空，抛出空指针
        if(haystack == null || needle == null){
           throw new Exception();
        }
        //目标字符串为""，返回0
        if(needle.equals("")){
            return 0;
        }
        char[] source = haystack.toCharArray();
        char[] target = needle.toCharArray();
        int sourceCount = source.length;
        int targetCount = target.length;
        //目标字符串比源字符串长，源字符串不可能包含目标字符串
        if(targetCount > sourceCount){
            return -1;
        }

        for(int i=0;i<sourceCount;i++){
            if(source[i] != target[0]){
                //从源字符串找目标字符串的第一个字符
                while (++i<sourceCount && source[i] != target[0]);
            }
            //找到了目标字符串的第一个字符
            if(i<sourceCount-1){
                //找接下来的字符
                int j = i+1;
                for(int k = 1; j<targetCount && source[j] == target[k];j++,k++);
                //找到剩下的字符
                if(j == targetCount-1){
                    return i;
                }
            }

        }
        return -1;
    }
}
