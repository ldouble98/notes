/**
 * @Author: small_double
 * @Date: 19-4-9 下午6:18
 */
public class 二叉树的镜像 {
    public class TreeNode{
        public int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val){
            this.val = val;
        }
    }
    public void Mirror(TreeNode root) {
        if(root==null) return;
        TreeNode left = root.left;
        TreeNode right = root.right;
        root.left = right;
        root.right = left;
        Mirror(left);
        Mirror(right);

    }
    public static void main(String[] args) {

    }
}
