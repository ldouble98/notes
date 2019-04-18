/**
 * @Author: small_double
 * @Date: 19-4-9 下午5:46
 * 输入两棵二叉树A，B，判断B是不是A的子结构。（
 * ps：我们约定空树不是任意一个树的子结构）
 */
public class 树的子结构 {
    public class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;

        }

    }
    /**
     *
     * */
    public String pre(TreeNode root){
        StringBuffer str = new StringBuffer();

        return "";
    }
    public Boolean isSubtree(TreeNode root1,TreeNode root2){
        if(root2==null)
            return true;
        if(root1==null)
            return false;
        if(root1.val!=root2.val){
            return false;
        }
        return isSubtree(root1.left,root2.left)&&isSubtree(root1.right,root2.right);
    }
    public boolean HasSubtree(TreeNode root1, TreeNode root2) {
        boolean flag = false;
        if (root2 == null || root1 == null) return flag;
        if(root1.val == root2.val){
            // isSubtree(root1.)
            flag = isSubtree(root1,root2);
        }
        if(!flag){
            flag = HasSubtree(root1.left,root2);
        }
        if(!flag){
            flag = HasSubtree(root1.right, root2);
        }
        return flag;
    }
}
