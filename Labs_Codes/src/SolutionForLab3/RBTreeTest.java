package SolutionForLab3;

/**
 * @author ChenMao
 * @create 2022-12-01 16:29
 */

public class RBTreeTest {

    private static final int a[] = {1,5,6,7,8,9,10,11,12,13,14,15};
    private static final boolean mDebugInsert = false;    // "插入"动作的检测开关(false，关闭；true，打开)
    private static final boolean mDebugDelete = true;    // "删除"动作的检测开关(false，关闭；true，打开)
    private static final int deleteList[] = {14,9,5};

    public static void main(String[] args) {
        int i, ilen = a.length;
        RBTree<Integer> tree=new RBTree<Integer>();

        System.out.printf("依次插入值为：");
        for(i=0; i<ilen; i++)
            System.out.printf("%d ", a[i]);
        System.out.printf("的节点后，");

        for(i=0; i<ilen; i++) {
            tree.insert(a[i]);
            // 设置mDebugInsert=true,测试"添加函数"
//            if (mDebugInsert) {
//                System.out.printf("== 添加节点: %d\n", a[i]);
//                System.out.printf("== 树的详细信息: \n");
//                tree.print();
//                System.out.printf("\n");
//            }
        }


        System.out.printf("中序遍历红黑树各节点: \n");
        tree.inOrder();


        // 设置mDebugDelete=true,测试"删除函数"
        int deleteLen = deleteList.length;
        if (mDebugDelete) {
            for(i=0; i<deleteLen; i++)
            {
                tree.remove(deleteList[i]);
                System.out.printf("删除节点: %d\n", deleteList[i]);
            }

            System.out.printf("\n 中序遍历红黑树各节点: \n");
            tree.inOrder();
        }

        // 销毁二叉树
        tree.clear();
    }
}
