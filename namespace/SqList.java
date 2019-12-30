package namespace;

/**
 * @author 作者 Your-Name:
 * @version 创建时间：2019年10月7日 上午10:20:35 类说明
 */
public class SqList<T> {
	private Object[] listElem = null;
	private int curLen = 0;

	public SqList(int length) throws Exception {
		if (length < 0)
			throw new Exception("顺序表容量不能为负");
		this.listElem = new Object[length];
	}

	public SqList() throws Exception {
		this(20);
	}

	public int length() {
		return this.curLen;
	}

	public T get(int i) {
		return (T) listElem[i];
	}

	
	
	public void insert(int i, T x) throws Exception {
		if (this.curLen == listElem.length)
			throw new Exception("顺序表已满");
		if (i < 0 || i > this.curLen)
			throw new ArrayIndexOutOfBoundsException("插入位置不合法");
		for (int j = curLen; j > i; j--) {
			listElem[j] = listElem[j - 1];
		}
		this.listElem[i] = x;
		this.curLen++;
	}

	public void remove(int i) {
		if (i < 0 || i >= curLen)
			throw new ArrayIndexOutOfBoundsException("删除位置不合法");
		for (int j = i; j < curLen - 1; j++) {
			listElem[j] = listElem[j + 1];
			
		}
		curLen--;
	}

	public int search(String id) {
		for (int i = 0; i < this.curLen; i++) {
			if (((Student) get(i)).getId().equals(id)) {
				System.out.printf("%-20s%-20s%-20c%-20s%-20s\n", ((Student) get(i)).getId(),
						((Student) get(i)).getName(), ((Student) get(i)).getSex(), ((Student) get(i)).getTeleNum(),
						((Student) get(i)).getAddress());
				return i;
			}
		}
		return -1;
	}

	public void removeStu(String id) {
		System.out.println("删除学生如下 : ");
		int index=this.search(id);
		if((-1)!=index)
			this.remove(index);	
	}
	
	
	
	public void alterStu(String id,double grade) {
		T student=this.get(search(id));
		((Student)student).setGrade(grade);
	}
	
	public int indexOf(T x) {
		for (int j = 0; j < curLen; j++) {
			if (listElem[j].equals(x))
				return j;
		}
		return -1;
	}
	public void displayStu() {
		System.out.println("已有学生:");
		for (int i = 0; i < curLen; i++) {
			System.out.printf("%-20s%-20s%-20c%-20s%-20s\n", ((Student) get(i)).getId(),
					((Student) get(i)).getName(), ((Student) get(i)).getSex(), ((Student) get(i)).getTeleNum(),
					((Student) get(i)).getAddress());
		}
	}
	public void display() {
		
		for (int i = 0; i < curLen; i++)
			System.out.print(listElem[i] + " ");
	}
}
