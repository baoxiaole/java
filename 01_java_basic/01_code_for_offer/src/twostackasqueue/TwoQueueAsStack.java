package twostackasqueue;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 两个队列实现栈
 */
public class TwoQueueAsStack {

    private Queue<Object> queue1 = new ArrayDeque<>();
    private Queue<Object> queue2 = new ArrayDeque<>();

    public int size(){
        return queue1.size()+queue2.size();
    }

    public void push(Object obj) {
           if(queue1.isEmpty() && queue2.isEmpty()){
               queue1.add(obj);
           }else if(!queue1.isEmpty() && queue2.isEmpty()){
               queue1.add(obj);
           }else if(queue1.isEmpty() && !queue2.isEmpty()){
               queue2.add(obj);
           }
    }

    public Object pop(){
        if(queue1.isEmpty() && queue2.isEmpty()){
            throw new IllegalArgumentException("stack is empty");
        }else if(!queue1.isEmpty() && queue2.isEmpty()){
            while(queue1.size()>1){
                queue2.add(queue1.remove());
            }
            return queue1.remove();
        }else if(queue1.isEmpty() && !queue2.isEmpty()){
            while(queue2.size()>1){
                queue1.add(queue2.remove());
            }
            return queue2.remove();
        }
        return null;
    }

    public static void main(String []args){
        TwoQueueAsStack  queue = new TwoQueueAsStack();
        queue.push(1);
        queue.push(3);
        queue.push(2);
        queue.push(4);
        System.out.println(queue);
        System.out.println(queue.pop());
        queue.push(5);
        while(queue.size() > 0){
            System.out.println(queue.pop());
        }


    }

}
