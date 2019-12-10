package genericClass;
/** 
* @author 作者 Your-Name: 
* @version 创建时间：2019年11月11日 下午9:58:25 
* 类说明 树节点
*/
public class BinaryTreeNode <T>{
	private T data=null;
	private BinaryTreeNode<T> leftChild=null;
	private BinaryTreeNode<T> rightChild=null;
	public BinaryTreeNode() {
		
	}
	public BinaryTreeNode(T data) {
		this.setData(data);
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public BinaryTreeNode<T> getLeftChild() {
		return leftChild;
	}
	public void setLeftChild(BinaryTreeNode<T> leftChild) {
		this.leftChild = leftChild;
	}
	public BinaryTreeNode<T> getRightChild() {
		return rightChild;
	}
	public void setRightChild(BinaryTreeNode<T> rightChild) {
		this.rightChild = rightChild;
	}
	
}
