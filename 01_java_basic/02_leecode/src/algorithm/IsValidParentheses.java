package algorithm;

import java.util.Stack;

/**
 * 有效的括号
 */
public class IsValidParentheses {

    public static boolean isValid(String s) {
        if (s == null) {
            return false;
        }

        //配对的话，用栈来做比较简单
        Stack<Character> stack = new Stack<>();
        for (Character c : s.toCharArray()) {
            if (c == '(') {
                stack.push(')');
            } else if (c == '{') {
                stack.push('}');
            } else if (c == '[') {
                stack.push(']');
            } else if (stack.isEmpty() || stack.pop() != c) {
                return false;
            }
        }
        return stack.isEmpty();
    }

    public static void main(String []args){
        System.out.println(IsValidParentheses.isValid("{}{}{}()"));
    }
}
