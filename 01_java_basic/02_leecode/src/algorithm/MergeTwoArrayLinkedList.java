package algorithm;

/**
 * 合并两个有序链表
 */
public class MergeTwoArrayLinkedList {

    public static class ListNode {
       int val;
       ListNode next;
       ListNode(int x) { val = x; }
  }

  //递归
  public static ListNode merge(ListNode listNode1,ListNode listNode2){
        if(listNode1 == null){
            return listNode2;
        }
        if(listNode2 == null){
            return listNode1;
        }
        ListNode list0 = null;
        if(listNode1.val < listNode2.val){
            list0 = listNode1;
            list0.next = merge(listNode1.next,listNode2);
        }else{
            list0 = listNode2;
            list0.next = merge(listNode1,listNode2.next);
        }

        return list0;
  }
  //循环
  public static ListNode mergeByLoop(ListNode listNode1,ListNode listNode2){
        ListNode listNode = new ListNode(0);
        ListNode firstNode = listNode;
        //两个链表都不为空时
        while(listNode1 != null && listNode2 != null){
            if(listNode1.val <= listNode2.val){
                listNode.next = listNode1;
                listNode1 = listNode1.next;
            }else{
                listNode.next = listNode2;
                listNode2 = listNode2.next;
            }
            listNode = listNode.next;
        }
        //当其中一个链表为空时，拼接另外一个不为空的链表
        while(listNode1 != null){
            listNode.next = listNode1;
            listNode1 = listNode1.next;
        }
      while(listNode2 != null){
          listNode.next = listNode2;
          listNode2 = listNode2.next;
      }
      //头节点是不需要的
      return firstNode.next;
  }

  public static void main(String []args){
        ListNode listNode1 = new ListNode(1);
        ListNode l1 = new ListNode(2);
        ListNode l2 = new ListNode(6);
        ListNode l3 = new ListNode(8);
        listNode1.next = l1;
        l1.next = l2;
        l2.next = l3;

      ListNode listNode2 = new ListNode(0);
      ListNode l21 = new ListNode(3);
      ListNode l22 = new ListNode(5);
      ListNode l23 = new ListNode(7);
      listNode2.next = l21;
      l21.next = l22;
      l22.next = l23;

//      ListNode targetListNode = merge(listNode1,listNode2);
//      while(targetListNode!=null){
//          System.out.print("targetListNode:"+targetListNode.val);
//          targetListNode = targetListNode.next;
//      }

      ListNode targetListNode1 = mergeByLoop(listNode1,listNode2);
      while(targetListNode1!=null){
          System.out.print("targetListNode1:"+targetListNode1.val);
          targetListNode1 = targetListNode1.next;
      }
  }
}
