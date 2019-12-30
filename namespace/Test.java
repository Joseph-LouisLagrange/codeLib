package namespace;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/** 
* @author 作者 Your-Name: 
* @version 创建时间：2019年9月13日 上午11:40:45 
* 类说明 
*/
public class Test{
	
	public static void main(String[] args) {
		MyList<String> list=new MyList<String>();
		list.add(new String("123130"));
		list.add(new String("123131"));
		list.add(new String("123132"));
		list.add(new String("123134"));
		System.out.println(list.get(0));
		System.out.println(list.get(1));
		System.out.println(list.get(2));
		System.out.println(list.get(3));
		System.out.println(list.get(4));
	}
}

class MyList<T>{
		private Node<T> headNode=null;
		private Node<T> tail=null;
		private int size=0;
		public MyList() {
			this.headNode=new Node<T>();
			this.tail=this.headNode;
		}
		
		
		
		public void add(T o) {
			Node<T> addNode=new Node<T>(o, null);
			tail.setNext(addNode);
			tail=addNode;
			this.size++;
		}
		
		
		public T get(int index) {
			if(index>=size||index<0)
				throw new IndexOutOfBoundsException();
			Node<T> p=headNode.getNext();
			for(int i=1;i<=index;i++) {
				p=p.getNext();
			}
			return p.getData();
		}
		
}
class Node<T>{
	private T data;
	private Node<T> next;
	public Node(T data, Node<T> next) {
		this.data = data;
		this.next = next;
	}
	public Node(T data) {
		this.data = data;
	}
	public Node() {
		data=null;
		next=null;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	
	public Node<T> getNext() {
		return next;
	}
	
	public void setNext(Node<T> next) {
		this.next = next;
	}
	
}




