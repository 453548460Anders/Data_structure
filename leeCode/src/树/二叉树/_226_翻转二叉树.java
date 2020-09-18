package 树.二叉树;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode-cn.com/problems/invert-binary-tree/
 * 
 * @author anderson
 *
 */
public class _226_翻转二叉树 {
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}
	
	public static void main(String[] args) {
		
	}
	
	public TreeNode invertTree(TreeNode root) {
		if (root == null) {
			return root;
		}
		
		Queue<TreeNode> queue = new LinkedList<>();
		queue.offer(root);
		
		while (!queue.isEmpty()) {
			
			TreeNode node = queue.poll();
			
			// 交换元素
			TreeNode tempNode = node.left;
			node.left = node.right;
			node.right = tempNode;
			
			if (node.left != null) {
				queue.offer(node.left);
			}
			
			if (node.right != null) {
				queue.offer(node.right);
			}
		}
		
		return root;
    }
	
	// 前序遍历(中, 后序也可以)
	public TreeNode invertTree2(TreeNode root) {
		if (root == null) return null;
		
		TreeNode tmpNode = root.left;
		root.left = root.right;
		root.right = tmpNode;
		
		invertTree2(root.left);
		invertTree2(root.right);
		
		return root;
    }
}
