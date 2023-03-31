package SolutionForLab3;

/**
 * @author ChenMao
 * @create 2022-12-01 20:50
 */


public class BST {
    /*
        1. 实现下列关于二叉查找树、 红黑树的判断、 构建、 删除等操作。 并写出这些操作的流程图或伪代码。
        2. 请说明二叉查找树和红黑树的区别以及时间、 空间性能。

     */

    /*
        二叉搜索树的节点类 —— class TreeNode
        二叉搜索树的属性：要找到一颗二叉搜索树只需要知道这颗树的根节点。
     */



//    public TreeNode root;//BST的根节点

    private int lastVisit = Integer.MIN_VALUE;

    public static void main(String[] args) {

        TreeNode root = new TreeNode(5);
        BST test = new BST();
        int[] nums_input = {1,4,3,6};

        for (int num : nums_input){
            test.insert(root,num);
        }

       TreeOperation operation_test = new TreeOperation();
        operation_test.show(root);

        System.out.println("是否为二叉搜索树？："+ test.isBST(root));
    }

    /**
     * 查找是否存在节点
     * 思路：根据二叉排序树的特点：
     * ①如果要查找的值小于当前节点的值，那么，就往当前节点的左子树走
     * ②如果要查找的值大于当前节点的值，那么，就往当前节点的右子树走
     *
     * @param val 带查找的val
     * @return boolean是否存在
     */
    public boolean find(TreeNode root,int val) {
        TreeNode cur = root;
        while (cur != null) {
            if (val < root.val) {
                cur = cur.left;
            } else if (val > root.val) {
                cur = cur.right;
            } else {
                return true;
            }
        }
        return false;
    }

    /**
     * 往二叉树中插入节点
     *
     * 思路如下：
     *
     * @param val 待插入的节点
     */
    public void insert(TreeNode root,int val) {
        if (root == null) { //如果是空树，那么，直接插入
            root = new TreeNode(val);
            return;
        }

        TreeNode cur = root;
        TreeNode parent = null; //parent 为cur的父节点
        while (true) {
            if (cur == null) { //在遍历过程中，找到了合适是位置，就指针插入（没有重复节点）
                if (parent.val < val) {
                    parent.right = new TreeNode(val);
                } else {
                    parent.left = new TreeNode(val);
                }
                return;
            }

            if (val < cur.val) {
                parent = cur;
                cur = cur.left;
            } else if (val > cur.val) {
                parent = cur;
                cur = cur.right;
            } else {
                throw new RuntimeException("插入失败，已经存在val");
            }
        }
    }

    /**
     * 删除树中节点
     *
     * 思路如下：
     *
     * @param val 待删除的节点
     */
    public void remove(TreeNode root, int val) {
        if (root == null) {
            throw new RuntimeException("为空树，删除错误！");
        }
        TreeNode cur = root;
        TreeNode parent = null;
        //查找是否val节点的位置
        while (cur != null) {
            if (val < cur.val) {
                parent = cur;
                cur = cur.left;
            } else if (val > cur.val) {
                parent = cur;
                cur = cur.right;
            } else {
                break;
            }
        }
        if (cur == null) {
            throw new RuntimeException("找不到val，输入val不合法");
        }

        //cur 为待删除的节点
        //parent 为待删除的节点的父节点
        /*
         * 情况1：如果待删除的节点没有左孩子
         * 其中
         * ①待删除的节点有右孩子
         * ②待删除的节点没有右孩子
         * 两种情况可以合并
         */
        if (cur.left == null) {
            if (cur == root) { //①如果要删除的是根节点
                root = cur.right;
            } else if (cur == parent.left) { //②如果要删除的是其父节点的左孩子
                parent.left = cur.right;
            } else { //③如果要删除的节点为其父节点的右孩子
                parent.right = cur.right;
            }
        }
        /*
         * 情况2:如果待删除的节点没有右孩子
         *
         * 其中:待删除的节点必定存在左孩子
         */
        else if (cur.right == null) { //①如果要删除的是根节点
            if (cur == root) {
                root = cur.left;
            } else if (cur == parent.left) { //②如果要删除的是其父节点的左孩子
                parent.left = cur.left;
            } else { //③如果要删除的节点为其父节点的右孩子
                parent.right = cur.left;
            }
        }
        /*
         * 情况3:如果待删除的节点既有左孩子又有右孩子
         *
         * 思路:
         * 因为是排序二叉树,要找到整颗二叉树第一个大于该节点的节点,只需要,先向右走一步,然后一路往最左走就可以找到了
         * 因此:
         * ①先向右走一步
         * ②不断向左走
         * ③找到第一个大于待删除的节点的节点,将该节点的值,替换到待删除的节点
         * ④删除找到的这个节点
         * ⑤完成删除
         *
         */
        else {
            TreeNode nextParent = cur; //定义父节点,初始化就是待删除的节点
            TreeNode next = cur.right; //定义next为当前走到的节点,最终目的是找到第一个大于待删除的节点
            while (next.left != null) {
                nextParent = next;
                next = next.left;
            }
            cur.val = next.val; //找到之后,完成值的替换
            if (nextParent == cur) { //此时的父节点就是待删除的节点,那么说明找到的节点为父节点的右孩子(因为此时next只走了一步)
                nextParent.right = next.right;
            } else { //此时父节点不是待删除的节点,即next确实往左走了,且走到了头.
                nextParent.left = next.right;
            }
        }

    }

    public boolean isBST(TreeNode root){
        if(root==null)return true;              //空树也是BST

        boolean judgeleft = isBST(root.left);   //判断左子树是否是

        if(root.val >= lastVisit && judgeleft){ //当前节点比上次访问的节点的数大
            lastVisit = root.val;
        }else {
            return false;
        }
        boolean judegright = isBST(root.right);//判断右子树是否是
        return judegright;
    }



}
