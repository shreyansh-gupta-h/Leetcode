/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode() : val(0), left(nullptr), right(nullptr) {}
 *     TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
 *     TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left), right(right) {}
 * };
 */
class Solution {
    int sum = 0;
public:
    int sumNumbers(TreeNode* root) {
        int num=0;
        find(root, num);
        return sum;
    }
private:
    void find(TreeNode* root , int num){
        if(root== NULL)return ;
        if(root->left == NULL && root->right == NULL){
            num = num*10 + root ->val;
            sum+= num;
            return ;
        }
        num = num*10 + root->val;
        find(root->left, num);
        find(root->right , num);
    }
};




