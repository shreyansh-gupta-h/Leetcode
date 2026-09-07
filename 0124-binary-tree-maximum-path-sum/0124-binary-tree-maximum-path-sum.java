/**
 * Definition for a binary tree node.g
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    int max=Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        solve(root);
        return max;
    }
    public int solve(TreeNode root) {
        if (root == null) return 0;
        int left = Math.max(0, solve(root.left));
        int right = Math.max(0, solve(root.right));
        int curr = root.val + left + right;
        max = Math.max(max, curr);
        return root.val + Math.max(left, right);
    }
}
 
