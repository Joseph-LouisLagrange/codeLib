package genericClass;

import java.util.Iterator;
import java.util.NoSuchElementException;


/** 
* @author 作者 Your-Name: 
* @version 创建时间：2019年11月21日 下午7:55:42 
* 类说明 链表
*/
public class LinkedList<E> implements Iterable<E>,Iterator<E> {
	private LinkedListNode<E> head=null;
	private LinkedListNode<E>next=null;
	private LinkedListNode<E> tail=null;
	private int size=0;
	public LinkedList() {
		head=new LinkedListNode<E>();
		next=head;
		tail=head;
	}
	
	/**
	 * 吸收数组建立链表
	 * @param list[] 
	 * @throws Exception
	 */
	public LinkedList(E[] list) throws Exception {
		this();
		for(int i=0;i<list.length;i++) {
			this.add(list[i]);
		}
	}
	
	public void add(E x){
		LinkedListNode<E> newNode=new LinkedListNode<E>(x);
		tail.setNextNode(newNode);
		tail=newNode;
		this.size++;
	}
	/**
	 * 低效的搜索，在多次历遍时非常不推荐使用
	 * @param i
	 * @return 对应索引的值
	 * @throws Exception
	 */
	public E get(int i)throws Exception{
		if(i<0||i>=this.size)
			throw new IndexOutOfBoundsException();
		LinkedListNode<E> p=head;
		while(i>=0) {
			p=p.getNextNode();
			i--;
		}
		return p.getData();
	}
	
	/**
	 * 对指定位置插入值
	 * @param i
	 * @param x
	 * @throws Exception
	 */
	public void insert(int i,E x)throws Exception{
		if(i<0||i>this.size)
			throw new IndexOutOfBoundsException();
		LinkedListNode<E> node=new LinkedListNode<E>(x);
		LinkedListNode<E> p=head;
		while(i>0) {
			p=p.getNextNode();
			i--;
		}
		node.setNextNode(p.getNextNode());
		p.setNextNode(node);
		this.size++;
	}
	
	
	/**
	 * 交换指定两个位置的节点
	 * @param index1
	 * @param index2
	 */
	public void exchange(int index1,int index2) {
		if(index1>=this.getSize()||index2>=this.getSize())
			throw new IndexOutOfBoundsException();
		int minIndex=Math.min(index1,index2);
		int maxIndex=index1+index2-minIndex;
		LinkedListNode<E> tempNode=null;
		LinkedListNode<E> p=this.head;
		for(int i=0;i<=maxIndex;i++) {
			p=p.getNextNode();
			if(i==minIndex) {
				tempNode=p;
			}
			if(i==maxIndex) {
				E temp=tempNode.getData();
				tempNode.setData(p.getData());
				p.setData(temp);
			}
		}
	}
	
	public void replace(int index,E e) throws Exception{
		if(index<0||index>=size)
			throw new IndexOutOfBoundsException();
		this.insert(index, e);
		this.removeAt(index+1);
	}
	//删除指定位置的节点
	public void removeAt (int i)throws Exception{
		if(i<0||i>=size)
			throw new IndexOutOfBoundsException();
		LinkedListNode<E> p=head;
		while(i>0) {
			p=p.getNextNode();
			i--;
		}
		p.setNextNode(p.getNextNode().getNextNode());
		if(p.getNextNode()==null)
			this.tail=p;
		size--;
	}
	
	/**
	 * 删除全部的元素
	 */
	public void removeAll() {
		this.head.setNextNode(null);
	}
	
	/**
	 * 
	 * @return 链表长度
	 */
	public int getSize() {
		return this.size;
	}
	
	protected void setSize(int size) {
		this.size=size;
	}
	
	
	
	protected LinkedListNode<E> getHead() {
		return head;
	}

	protected void setHead(LinkedListNode<E> head) {
		this.head = head;
	}

	/**
	 * 
	 * @return 是否为空
	 */
	public boolean isEmpty(){
		return size==0;
	}
	
	/**
	 * 
	 * @return 链表的数组转化
	 */
	public Object[] toArray(){
		Object[] list=new Object[this.size];
		int i=0;
		LinkedListNode<E> p=this.head.getNextNode();
		while(i<this.getSize()&&p!=null) {
			list[i]=p.getData();
			p=p.getNextNode();
			i++;
		}
		return list;
	}
	/**
	 * 不会破坏源链表
	 * @return  当前链表的升序链表
	 * @throws Exception
	 */
	public LinkedList<E> sort() throws Exception {
		Object[] list=this.toArray();
		SortClass.<Object>mergeSort(list);
		return new LinkedList<E>((E[]) list);
	}
	/**
	 * ,不会破坏源链表
	 * @return  当前链表的反转链表
	 * @throws Exception
	 */
	public LinkedList<E> reverse() throws Exception {
		E[] list=(E[]) this.toArray();
		int length=list.length;
		for(int i=0;i<length/2;i++) {
			E temp=list[i];
			list[i]=list[length-1-i];
			list[length-1-i]=temp;
		}
		return new LinkedList<E>(list);
	}

	@Override
	public boolean hasNext() {
			if(next.getNextNode()==null) {
				return false;
			}
		return true;
	}

	@Override
	public E next() {
		if(!this.hasNext())
			throw new NoSuchElementException();
		next=next.getNextNode();
		E data=next.getData();
		return data;
	}
	@Override
	public Iterator<E> iterator() {
		this.next=this.head;
		return this;
	}
}
