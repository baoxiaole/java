package rebuildbinarytree;

/**
 * 重建二叉树
 */
public class RebuildBinaryTree {

    public static void main(String []args){
           int[] preOrder = {1,2,4,7,3,5,6,8};
           int[] inOrder = {4,7,2,1,5,3,8,6};
           BinaryTreeNode node = rebuildBinaryTree(preOrder,inOrder);
           printTree(node);
    }

    //二叉树节点
    public static class BinaryTreeNode{
        int value;
        BinaryTreeNode left;
        BinaryTreeNode right;
    }

    /**
     *
     * @param preOrder   前序遍历结果
     * @param inOrder   中序遍历结果
     * @return
     */
    public static BinaryTreeNode rebuildBinaryTree(int[] preOrder,int[] inOrder){
        if(preOrder==null||inOrder==null||preOrder.length!=inOrder.length||preOrder.length<1){
              return null;
        }
           return build(preOrder,0,preOrder.length-1,inOrder,0,inOrder.length-1);
    }

    /**
     *
     * @param preOrder  前序遍历序列
     * @param ps   前序遍历起始位置
     * @param pe   前序遍历结束位置
     * @param inOrder   中序遍历序列
     * @param is   中序遍历起始位置
     * @param ie   中序遍历结束位置
     * @return
     */
    public static BinaryTreeNode build(int[] preOrder,int ps,int pe,int[] inOrder,int is,int ie){
           if(ps > pe){
               return null;
           }
           //前序遍历序列的第一个值是根节点
           int value = preOrder[ps];
           //在中序遍历序列中找到根节点
           int index = is;
           while(index <= ie && inOrder[index] != value){
               index++;
           }
           if(index > ie){
               throw new IllegalArgumentException("输入数据有误！！！");
           }
           BinaryTreeNode node = new BinaryTreeNode();
           node.value = value;
           //当前节点的左子树的个数为index-is
           //左子树对应的前序遍历的位置在preOrder[ps+1,ps+index-is]
           //左子树对应的中序遍历的位置在inOrder[is,index-1]
           node.left = build(preOrder,ps+1,ps+index-is,inOrder,is,index-1);
           //当前节点的右子树的个数为ie-index
           //右子树对应的前序遍历位置在preOrder[ps+index-is+1,pe]
           //右子树对应的中序遍历位置在inOrder[index+1,ie]
           node.right = build(preOrder,ps+index-is+1,pe,inOrder,index+1,ie);
           return node;
    }

    //中序遍历递归打印
    public static void printTree(BinaryTreeNode node){
           if(node!=null){
               printTree(node.left);
               System.out.print(node.value+" ");
               printTree(node.right);
           }
    }
}
