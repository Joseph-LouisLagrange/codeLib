package namespace;

import java.util.Scanner;

public class ReplicationExperiment{
public static void main( String[] args) throws Exception{
	//改：  SeqList SeqList = new SeqList(20);//构造一个存储容量为0的空顺序表
			SeqList<Integer> seqList = new SeqList<Integer>(20);     //指定泛型的具体类型，即Integer( int )类型
			Scanner sc= new Scanner(System.in);
			System. out.println("请输入顺序表的长度:");
			int n= sc. nextInt();
			System.out.println("请输入顺序表中的各元素的值:");
			for(int i=0;i<n;i++)
//				L.insert(i,sc.nextInt());
				seqList.insert(sc.nextInt());
			System.out. println("请输入待插入的位置i(0~curlen)");
			int i=sc.nextInt();
			System.out. println("请输入待插入的元素值x:");
			int x=sc.nextInt();
//			L.insert(i, x);
			seqList.insert(i,x);
			System.out. println("插入后的顺序表为:"+seqList.toString());
//			L.display();
			
			System.out. println("请输入待删除元素的位置(0~curlen-1)");
			i=sc.nextInt();
//			L.remove();
			seqList.remove(i);
			System.out. println("删除后的顺序表为:"+seqList.toString());
			
			 
//			L.display();
			System.out. println("请输入待查找的元素值:");
			x=sc.nextInt();
		int order =seqList.search(x);
		if (order == -1)
				System.out.println("此顺序表中不包含值为"+x+"的元素!");
		else
		System.out.println("值为"+x+"的元素在顺序表中的第"+order+"个位置上");
			
}
}



//顺序表类，实现ADT List<T>声明的方法，T表示数据元素的数据类型
 class SeqList<T> extends Object{
	//对象数组存储顺序表的数据元素，protected声明
	protected Object[] element ;
	//顺序表元素的个数（表长）
	protected int n ;
	
	//构造容量为length的空表
	public SeqList(int length){
		//申请数组的存储空间，元素为null
		this.element = new Object[length];
		//若length<0，则跑出负数组长度异常java.lang.NegativeArraySizeException
		this.n = 0 ;
	}
	
	//创建默认容量的空表，构造方法重载
	public SeqList(){
		//调用本类已经声明的指定参数列表的构造方法
		this(64) ;
	}
	
	//构造顺序表，由values数组提供元素
	public SeqList(T values[]){
		//创建容量为values.length的空表
		//若values==null，则用空对象调用方法，抛出NullPointerException异常
		this(values.length) ;
		//复制数组元素，T(n) = O(n)
		for(int i=0;i<values.length;i++){
			//对象引用赋值
			this.element[i] = values[i] ;
		}
		this.n = element.length ;
	}
	
	//判断顺序表是否为空，若为空则返回true
	public boolean isEmpty(){
		return this.n == 0 ;
	}
	
	//返回顺序表元素个数
	public int size(){
		return this.n ;
	}
	
	//返回第i个元素，0≤i≤n。若i越界，则返回null
	public T get(int i){
		if(i>=0 && i<this.n){
			//返回数组元素引用的对象，传递对象引用
			return (T)this.element[i] ;
		}
		else{
			return null ;
		}
	}
	
	//设置第i个元素为x，0≤i≤n。若i越界，则抛出序号越界异常；若x==null，则抛出空指针异常
	public void set(int i,T x){
		if(x == null){
			//抛出空指针异常
			throw new NullPointerException("x == null") ;
		}
		else if(i>=0 && i<this.n){
			this.element[i] = x ;
		}
		else{
			//抛出序号越界异常
			throw new java.lang.IndexOutOfBoundsException(i+"");
		}
	}
	
	//返回顺序表所有元素的描述字符串，形式为“（,）”，覆盖Objcet类的toString()方法
	public String toString(){
		//返回类型
		String str = this.getClass().getName()+"(" ;
		if(this.n>0){
			str += this.element[0].toString() ;
		}
		for(int i=1;i<this.n;i++){
			//执行T类的toString()方法，运行时多态
			str += "," +this.element[i].toString();
		}
		//空表返回()
		return str+")" ;
	}
	
	//插入x作为第i个元素，x!=null，返回x序号。若x==null，则抛出空对象异常。T(n)=O(n)。
	//对序号i采取容错措施，若i<0，则插入x在最前；若i>n，则插入x在最后
	public int insert(int i,T x){
		if(x == null){
			//抛出空指针异常
			throw new NullPointerException("x == null") ;
		}
		//插入位置i容错，插入在最前
		else if(i<0){
			i = 0 ;
		}
		//插入位置i容错，插入在最后
		else if(i>=this.n){
			i = this.n ;
		}
		//数组引用赋值，source也引用element
		Object source[] = this.element ;
		//若数组空间已满，则扩充顺序表的数组容量
		if(this.n == element.length){
			//重新申请一个容量更大的数组
			this.element = new Object[source.length*2] ;
			//复制当前数组前i-1个元素
			for(int j=0;j<i;j++){
				this.element[j] = source[j];
			}
		}
		//从i开始至表尾的元素向后移动，次序从后向前
		for(int j=this.n-1;j>=i;j--){
			this.element[j+1] = source[j] ;
		}
		this.element[i] = x;
		this.n++ ;
		//返回x的序号
		return i ; 
	}
	
	//顺序表表位插入x元素，返回x序号。成员方法重载。插入操作中，this.n加1。
	public int insert(T x){
		return this.insert(this.n, x) ;
	}
	
	//删除第i个元素，0≤i≤n，返回被删除的元素。若i越界，则返回null
	public T remove(int i){
		if(this.n>0 && i>=0 && i<this.n){
			//old中存储被删除元素
			T old = (T)this.element[i] ;
			for(int j=i;j<this.n-1;j++){
				//元素前移一个位置
				this.element[j] = this.element[j+1] ;
			}
			//设置数组元素对象为空，释放原引用实例
			this.element[this.n-1] = null ;
			this.n--;
			//返回old局部变量引用的对象，传递对象引用
			return old ;
		}
		return null ;
	}
	
	//删除线性表所有元素
	public void clear(){
		//设置长度为0，未释放数组空间
		this.n = 0 ;
	}
	
	//顺序查找首次出现的与key相等的元素，返回元素序号i，0≤i≤n；查找不成功则返回-1
	//若key==null，则抛出空指针异常NullPointerException
	public int search(T key){
		for(int i=0;i<this.n;i++){
			//执行T类的equals()方法，运行时多态
			if(key.equals(this.element[i])){
				return i ;
			}
		}
		//空表或未找到时，返回-i
		return -1 ;
	}
	
	//判断是否包含关键字为key的元素
	public boolean contains(T key){
		return this.search(key)!=-1 ;
	}	
}