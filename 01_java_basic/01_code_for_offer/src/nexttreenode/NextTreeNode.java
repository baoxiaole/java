package nexttreenode;

/**
 * 树中某个节点的下一个节点
 */
public class NextTreeNode {

    public static class TreeNode{
        int value;
        TreeNode parent;
        TreeNode left;
        TreeNode right;
    }

    public static TreeNode getNextTreeNode(TreeNode treeNode){
        if(treeNode == null){
            return null;
        }
        TreeNode nextNode = new TreeNode();
        if(treeNode.right != null){
            //节点有右子树的情况,下一个节点就是右子树最左得节点
            nextNode = treeNode.right;
            while (nextNode.left != null){
                nextNode = nextNode.left;
            }
        }else if(treeNode.parent != null){
            //节点没有右子树，
            // 如果该节点是它父节点得左子节点，父节点就是它下一个节点，
            //如果是它父节点得右子节点，就向上找父节点，直到找到一个节点是它父节点得左子节点，那么它的父节点就是要找得节点
            TreeNode pCurrent = treeNode;
            TreeNode pParent = treeNode.parent;
            while(pParent != null && pCurrent == pParent.right){
                  pCurrent = pParent;
                  pParent = pParent.parent;
            }
            nextNode = pParent;
        }
        return nextNode;
    }
}
