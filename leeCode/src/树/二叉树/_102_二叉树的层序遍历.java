package 树.二叉树;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import 树.二叉树._226_翻转二叉树.TreeNode;

/**
 * https://leetcode-cn.com/problems/binary-tree-level-order-traversal/
 * 
 * @author anderson
 *
 */
public class _102_二叉树的层序遍历 {
	
	public List<List<Integer>> levelOrder(TreeNode root) {
		List<List<Integer>> resList = new ArrayList<>();
		if (root == null) {
			return resList;
		}
		int levelSize = 1;
		
		List<Integer> list = new ArrayList<>();
		
		Queue<TreeNode> queue = new LinkedList<>();
		queue.offer(root);
		
		while (!queue.isEmpty()) {
			TreeNode node = queue.poll();
			list.add(node.val);
			levelSize--;
			if (node.left != null) {
				queue.offer(node.left);
			}
			
			if (node.right != null) {
				queue.offer(node.right);
			}
			
			if (levelSize == 0) {
				resList.add(list);
				levelSize = queue.size();
				list = new ArrayList<>();
			}
		}
		
		return resList;
    }
}
