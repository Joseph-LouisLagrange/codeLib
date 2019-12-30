package namespace;

import java.util.Scanner;

/**
 * @author 作者 Your-Name:
 * @version 创建时间：2019年10月7日 上午10:49:16 类说明
 */
public class SY1_SqList1 {

	public static void main(String[] args) throws Exception {
		SqList<Student> students = new SqList<Student>(20);
		Student s1=new Student("2008001","Alan",'F',"13305731238","浙江省嘉兴市",60);
		Student s2=new Student("2008002","Danie",'M',"0573-82283288","浙江省嘉兴市",70);
		students.insert(0, s1);
		students.insert(1, s2);
		printHead();
		students.removeStu("2008001");
		students.displayStu();
//		SqList<Integer> L = new SqList<Integer>(20);
//		SqList<Integer> A = new SqList<Integer>(20);
//		SqList<Integer> B = new SqList<Integer>(20);
//		SqList<Integer> C = new SqList<Integer>(20);
//		Scanner sc = new Scanner(System.in);
//		System.out.println("请输入顺序表的长度:");
//		int n = sc.nextInt();
//		createSortSqList(A, n);
//		System.out.println("请输入顺序表的长度:");
//		n = sc.nextInt();
//		createSortSqList(B, n);
//		System.out.println("请输入顺序表的长度:");
//		n = sc.nextInt();
//		createSortSqList(C, n);
//		removePublic(A, B, C);
//		A.display();
//		L.insert(i,sc.nextInt());
//		System.out. println("请输入待插入的位置i(0~curlen)");
//		int i=sc.nextInt();
//		System.out. println("请输入待插入的元素值x:");
//		int x=sc.nextInt();
//		L.insert(i, x);
//		System.out. println("插入后的顺序表为:");
//		L.display();		
//		System.out. println("请输入待删除元素的位置(0~curlen-1)");
//		i=sc.nextInt();
//    	L.remove(i);
//		System.out. println("删除后的顺序表为:");
//		L.display();
//		System.out. println("请输入待查找的元素值:");
//		x=sc.nextInt();
//		int order =L.indexOf(x);
//		if (order == -1)
//			System.out.println("此顺序表中不包含值为"+x+"的元素!");
//		else
//			System.out.println("值为"+x+"的元素在顺序表中的第"+order+"个位置上");		
	}
	
	private static void printHead() {
		System.out.printf("%-20s%-20s%-20s%-20s%-20s\n","学号","姓名","性别","电话","地址");
	}
	
	private static void createSortSqList(SqList L, int n) {
		Scanner sc = new Scanner(System.in);
		System.out.print("请输入有序顺序表中的各个元素：");
		for (int i = 0; i < n; i++) {
			try {
				L.insert(i, sc.nextInt());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private static void removePublic(SqList A, SqList B, SqList C) {
		int i = 0, j = 0, k = 0;
		boolean f1 = false, f2 = false;
		while (i < A.length() && j < B.length() && k < C.length()) {
			if ((Integer) A.get(i) > (Integer) B.get(j)) {
				j++;
			} else if ((Integer) A.get(i) == (Integer) B.get(j)) {
				f1 = true;
			} else
				f1 = false;
			if ((Integer) A.get(i) > (Integer) C.get(k)) {
				k++;
			} else if ((Integer) A.get(i) == (Integer) C.get(k)) {
				f2 = true;
			} else
				f2 = false;
			if (f1 && f2) {
				A.remove(i);
			} else if (!(f1 || f2)) {
				i++;
			}
		}
	}
}
