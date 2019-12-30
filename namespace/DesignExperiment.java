package namespace;
import java.util.Scanner;

public class DesignExperiment {
	public static void main(String[] args) throws Exception{
		SeqList<Integer> A = new SeqList<Integer>(20);     //指定泛型的具体类型，即Integer( int )类型
		SeqList<Integer> B = new SeqList<Integer>(20);    
		SeqList<Integer> C = new SeqList<Integer>(20);
		Scanner sc= new Scanner(System.in);
		System. out.println("请输入顺序表A的长度:");
		int a= sc. nextInt();
		creatSortSeqList(A,a);
		System. out.println("请输入顺序表B的长度:");
		int b= sc. nextInt();
		creatSortSeqList(B,b);
		System. out.println("请输入顺序表C的长度:");
		int c= sc. nextInt();
		creatSortSeqList(C,c);
		removePublic(A,B,C);
	}

	private static void creatSortSeqList(SeqList<Integer> seqList,int n) throws Exception{
		Scanner sc= new Scanner(System.in);
		System.out.println("请输入有序顺序表中的各元素的值:");
		for(int i=0;i<n;i++)
			seqList.insert(i,sc.nextInt());
	}
	
	private static void removePublic(SeqList<Integer> A,SeqList<Integer> B,SeqList<Integer> C) throws Exception {
		int i=0,j=0,k=0;
		while (j<B.element.length&&j<C.element.length){
			System.out.println("loop");
			if((Integer)B.get(j)<(Integer)C.get(k)){
				j++;
			}else if((Integer)B.get(j)>(Integer)C.get(k)){
				k++;
			}else{
				while(i<A.element.length&&(Integer)A.get(i)<(Integer)B.get(j)){
					i++;
				}if(i<A.element.length&&(Integer)A.get(i)==(Integer)B.get(j)){
					A.remove(i);
				}
				j++;
				k++;
			}
		}
	}
	
}























