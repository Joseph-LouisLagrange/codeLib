package genericClass;

/**
 * @author 作者 Your-Name:
 * @version 创建时间：2019年11月11日 下午10:05:35 类说明 :通用的二叉树结构，目的是最大限度的简化吸收数据的参数
 */
public class BinaryTree<T> {
	private BinaryTreeNode<T> rootNode = null;
	private LinkedList<BinaryTreeNode<T>> binaryTree = new LinkedList<BinaryTreeNode<T>>();
	private int treeHeight = 0;

	/**
	 * 初始化一颗空树
	 */
	public BinaryTree() {

	}

	/**
	 * 二叉树节点数量
	 * 
	 * @return
	 */
	public int binaryTreeNodeCount() {
		return this.binaryTree.getSize();
	}

	/**
	 * 获取两个关系节点的信息
	 * 
	 * @param selfElement
	 * @param parentElement
	 * @param trend
	 * @throws Exception
	 */
	public void addElement(T selfElement, T parentElement, byte trend) throws Exception {
		BinaryTreeNode<T> selfNode = new BinaryTreeNode<T>(selfElement);
		BinaryTreeNode<T> parentNode = new BinaryTreeNode<T>(parentElement);
		boolean i = false, j = false;
		for (BinaryTreeNode<T> t : this.binaryTree) {
			if (t.getData().equals(parentElement)) {
				parentNode = t;
				i = true;
			}
		}
		for (BinaryTreeNode<T> k : this.binaryTree) {
			if (k.getData().equals(selfElement)) {
				selfNode = k;
				j = true;
			}
		}
		if (trend == 0) {
			parentNode.setLeftChild(selfNode);
		} else if (trend == 1) {
			parentNode.setRightChild(selfNode);
		} else
			throw new Exception();

		if (!i)
			this.binaryTree.add(parentNode);
		if (!j)
			this.binaryTree.add(selfNode);
	}

	/**
	 * 单独获取根节点
	 * 
	 * @param rootElement
	 */
	public void addRootElement(T rootElement) {
		BinaryTreeNode<T> rootNode = new BinaryTreeNode<T>(rootElement);
		boolean i = false;
		for (BinaryTreeNode<T> t : this.binaryTree) {
			if (t.getData().equals(rootElement)) {
				rootNode = t;
				i = true;
			}
		}
		if (!i)
			this.binaryTree.add(rootNode);
		this.rootNode = rootNode;
	}
	private void treeHeight(int height, BinaryTreeNode<T> subrootNode) {
		if (subrootNode != null) {
			height++;
			treeHeight(height, subrootNode.getLeftChild());
			treeHeight(height, subrootNode.getRightChild());
		} else
			this.setTreeHeight(Math.max(height, treeHeight));
	}

	public int getTreeHeight() {
		treeHeight(0,this.rootNode);
		return treeHeight;
	}

	public void setTreeHeight(int treeHeight) {
		this.treeHeight = treeHeight;
	}

	public BinaryTreeNode<T> getRootNode() {
		return rootNode;
	}

}
