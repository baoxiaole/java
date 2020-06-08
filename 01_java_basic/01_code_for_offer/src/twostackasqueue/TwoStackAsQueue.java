package twostackasqueue;

import java.util.Stack;

/**
 * 两个栈实现队列
 */
public class TwoStackAsQueue {

    private Stack<Object> stack1 = new Stack<Object>();
    private Stack<Object> stack2 = new Stack<Object>();

    //队尾添加元素
    public void appendTail(Object obj){
         stack1.push(obj);
    }

    //队首删除元素
    public Object deleteHead(){
         if(stack2.empty()){
             while(!stack1.empty()){
                 stack2.push(stack1.pop());
             }
         }

         if(stack2.empty()){
             throw new IllegalArgumentException("queue is empty");
         }

         return stack2.pop();
    }

    public static void main(String []args){
        TwoStackAsQueue queue = new TwoStackAsQueue();
        queue.appendTail(1);
        queue.appendTail(4);
        queue.appendTail(2);
        queue.appendTail(5);
        System.out.println(queue.deleteHead());

    }
}
