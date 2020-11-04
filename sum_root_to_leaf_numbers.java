// Time Complexity : O(n), where n is the size of the tree
// Space Complexity :O(n), where n is the height of the tree (in the worst case i.e skewd binary tree)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this :

//Three Liner explanation in plain english
//1. Perform Inorder traversal, visiting the left tree first. After visiting a node before pushing to the the Stack, calculate the 
    //runningSum and then push the node along with its runningSum in the stack
//2. Once the left subtree is traversed, pop the stack and check if the current root is the leaf node. if yes, you have traversed one 
    //root-to-leaf path, so add the currents root node runningSum to the result.
//3.Keep doing this till either root is null OR stack is empty

// Your code here along with comments explaining your approach

/**
 * Definition for a binary tree node.
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
    //class to hold node with its runningSum
    class Pair{
        TreeNode node;
        int value;
        
        public Pair(TreeNode node, int value){
            this.node = node;
            this.value = value;
        }
        
        public TreeNode getKey(){
            return this.node;
        }
        
        public int getValue(){
            return this.value;
        }
    }
    public int sumNumbers(TreeNode root) {
        Stack<Pair> stack = new Stack<>();
        int runningSum = 0;
        int result =0;
       while(root != null || !stack.isEmpty()){
           //explore the left subtree
           while(root!=null){
               //form number of the current node with the runningSum
               runningSum = runningSum*10 + root.val;
               //add the root and runningSum to the stack
               Pair pair = new Pair(root, runningSum);
               stack.push(pair);
               root = root.left;
           }
           
           //pop the pair from the stack
           Pair temp = stack.pop();
           //get the node from the popped pair
           root = temp.getKey();
           //get the runningSum from the popped pair
           runningSum = temp.getValue();
           
           //if root is the leaf node, no left and right child, you have explored one root-to-leaf path
           //so add the runningSum to the result
           if(root != null && root.left == null && root.right == null){
               result +=runningSum;
           }
           //continue to the right child
           root = root.right;
       } 
        return result;
    }
}