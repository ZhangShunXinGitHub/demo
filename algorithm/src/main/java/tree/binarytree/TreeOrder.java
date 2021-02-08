package tree.binarytree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
/*
如果从bai下标从1开始存储，则du编号为i的结点的zhi主要关系为：
双亲：dao下取整 （i/2）
左孩子：2i
右孩子：2i+1
如果从下标从0开始存储，则编号为i的结点的主要关系为：
双亲：下取整 （(i-1)/2）,忽略小数部分
左孩子：2i+1
右孩子：2i+2
 */
public class TreeOrder {
    private static List<TreeNode> nodeList = new LinkedList<>();
    public static void main(String[] args) {
        //广度优先结构的完全二叉树结构【深度为k,除第k层外，其余都达到最大子节点个数，且第k层都连续集中在最左边】
        int[] nodeValue=new int[]{1,2,3,4,5,6,7,8,9};
        createBinaryTree(nodeValue);
        TreeNode root=nodeList.get(0);
        System.out.println("先序遍历");
        preOrderTraveral(root);
        System.out.println();
        System.out.println("中序遍历");
        inOrderTraveral(root);
        System.out.println();
        System.out.println("后续遍历");
        postOrderTraveral(root);
        System.out.println();
        System.out.println("广度优先遍历");
        bfs(root);
        System.out.println();
        System.out.println("深度优先遍历");
        List<List<Integer>> rst=new ArrayList<>();
        List<Integer> list=new ArrayList<>();
        dfs(root,rst,list);
        System.out.println(rst);
        System.out.println();
        //****************************************
        int[] nodeValue1=new int[]{1,1,0,1,1,0,1,0};
        nodeList.clear();
        createBinaryTree(nodeValue1);
        TreeNode root1=nodeList.get(0);
        System.out.println("二叉树剪枝---剪枝完为非完全二叉树，bfs遍历没问题，但是已完全二叉树形式打印，所以打印数据不能直接当作二叉树用来构建树");
        pruneTree(root1);
        bfs(root1);
        System.out.println();


    }

    private static TreeNode pruneTree(TreeNode root) {
        if(root == null)
            return null;
        root.leftChild=pruneTree(root.leftChild);
        root.rightChild=pruneTree(root.rightChild);
        if(root.leftChild == null && root.rightChild == null && root.data == 0){
            return null;
        }
        return root;
    }

    /*
       构建二叉树
       @param int[] 输入序列
       @return
     */
    public static void createBinaryTree(int[] nodevalue){
        if(nodevalue==null || nodevalue.length == 0){
            return ;
        }
        int len=nodevalue.length;
        //将数组转为node节点
        for(int i=0; i<len;i++){
            TreeNode node = new TreeNode(nodevalue[i]);
            nodeList.add(node);
        }

        //给所有父节点[双亲]设置子节点，利用完全二叉树双亲关系，最后一个双亲为 编号为 nodelist.size() -1; 双亲为 (nodelist.size-1-1)/2 下取整
        //此处先遍历到倒数第二个双亲，因为最后一个双亲的子节点可能没有右节点
        int lastFatherNodeIndex=(len-1-1)/2;
        for(int nodeIndex=0; nodeIndex<lastFatherNodeIndex; nodeIndex++){
            nodeList.get(nodeIndex).leftChild=nodeList.get(2*nodeIndex +1);
            nodeList.get(nodeIndex).rightChild=nodeList.get(2*nodeIndex +2);
        }
        //处理最后一个双亲节点关系

        nodeList.get(lastFatherNodeIndex).leftChild=nodeList.get(lastFatherNodeIndex*2 +1); //设置左节点
        if(len%2==1){ //如果节点数量为奇数，则说明最后一个父节点有右节点
            nodeList.get(lastFatherNodeIndex).rightChild=nodeList.get(lastFatherNodeIndex*2 + 2);
        }
    }

    /*
    遍历当前节点值
    @param nodelist
    @param node
     */
    public static void checkCurrentNode(TreeNode node){
        System.out.print(node.data+"  ");
    }

    /*
        递归实现
        二叉树先-前序遍历 根->左->右【根 -> 前序遍历左 -> 前序遍历右子树】
        @param node 二叉树节点
     */
    public static void preOrderTraveral(TreeNode node){
        if(node == null) //重要，当遇到叶子节点用来停止向下遍历
            return;
        checkCurrentNode(node);
        preOrderTraveral(node.leftChild);
        preOrderTraveral(node.rightChild);
    }

    /*
    递归实现
     二叉树中序遍历 左->根->右【中序左子树 -> 根 -> 中序右子树】有序数组
     @param node 二叉树根节点
     */
    public static void inOrderTraveral(TreeNode node){
        if(node == null) //重要，当遇到叶子节点用来停止向下遍历
            return;
        inOrderTraveral(node.leftChild);
        checkCurrentNode(node);
        inOrderTraveral(node.rightChild);

    }

    /*
    递归实现
    二叉树后序遍历 左->右->根【后序遍历左子树-> 后续遍历右子树 ->根】
     */
    public static void postOrderTraveral(TreeNode node){
        if(node == null) //重要，当遇到叶子节点用来停止向下遍历
            return;
        postOrderTraveral(node.leftChild);
        postOrderTraveral(node.rightChild);
        checkCurrentNode(node);
    }
    /*
    广度优先遍历(从上到下遍历二叉树)---完全二叉树
    @param root
     */
    public static void bfs(TreeNode root){
        if(root == null)
            return;
        LinkedList<TreeNode> queue =new LinkedList<>();//先进先出队列做暂存
        queue.offer(root);//首先将根节点存入队尾
        //当队列有值时，每次取出队首node打印，打印之后判断node是否有子节点，若有，则将子节点加入队列尾部
        while(queue.size() > 0){
            TreeNode node=queue.peek(); // does not remove first(head) element
            queue.poll(); // 取出队首元素打印, removes first(head) element
            checkCurrentNode(node);
            if(node.leftChild!=null){ //如果有左子节点，则将其加入队列尾部
                queue.offer(node.leftChild);
            }
            if(node.rightChild!=null){ //如果有右子节点，则将其加入队列尾部
                queue.offer(node.rightChild);
            }

        }

    }

    /*
    深度优先遍历
    @param node
    @param rst
    @param list
     */
    public static void dfs(TreeNode node,List<List<Integer>> rst,List<Integer> list){
        if(node == null) return;
        if(node.leftChild == null && node.rightChild==null) {

            list.add(node.data);
            rst.add(new ArrayList<>(list)); //新建list存入rst，避免后面被remove
            list.remove(list.size() - 1);
        }
        list.add(node.data);
        dfs(node.leftChild,rst,list);
        dfs(node.rightChild,rst,list);
        list.remove(list.size()-1);
    }
}
