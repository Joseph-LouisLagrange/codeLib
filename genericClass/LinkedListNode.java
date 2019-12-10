package genericClass;

/** 
* @author 作者 Your-Name: 
* @version 创建时间：2019年11月6日 上午11:34:13 
* 类说明 
*/
public class LinkedListNode<E> {
		private E data=null;
		private LinkedListNode<E> next=null;
		public LinkedListNode(E data, LinkedListNode<E> next) {
			this.setData(data);
			this.setNextNode(next);
		}
		public LinkedListNode(E data) {
			this(data,null);
		}
		
		public LinkedListNode() {
			this(null,null);
		}
		public E getData() {
			return data;
		}
		public void setData(E data) {
			this.data = data;
		}
		public LinkedListNode<E> getNextNode() {
			return next;
		}
		public void setNextNode(LinkedListNode<E> next) {
			this.next = next;
		}
}
