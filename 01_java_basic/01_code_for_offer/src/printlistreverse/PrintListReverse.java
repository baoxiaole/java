package printlistreverse;

import java.util.Stack;

/**
 * 从尾到头打印链表
 */
public class PrintListReverse {

    /**
     * 递归的方式
     * @param headNode
     */
    public static void printListReverse(ListNode headNode){
           if(headNode != null){
               if(headNode.next != null){
                   printListReverse(headNode.next);
               }
               System.out.print(headNode.data);
           }
    }

    /**
     * 用栈的方式
     * @param headNode
     */
    public static void printListReverseByStack(ListNode headNode){
        Stack<ListNode> stack = new Stack<>();
        while(headNode != null){
            stack.push(headNode);
            headNode = headNode.next;
        }
        while(!stack.empty()){
            System.out.print(stack.pop().data);
        }
    }

    public static void main(String []args){
        ListNode listNode = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        listNode.next = listNode2;
        listNode2.next = listNode3;
        PrintListReverse.printListReverse(listNode);
        System.out.println();
        PrintListReverse.printListReverseByStack(listNode);
    }

}
