package genericClass;

import java.util.Arrays;

/**
 * @author 作者 Your-Name:
 * @version 创建时间：2019年11月10日 下午9:56:51 类说明:排序数组的每个元素都必须继承有Comparable<T> 接口
 */
public class SortClass {
	
	/**
	 * 
	 * @param <T>
	 * @param list 分治法排序最后整合在一起 如果使用递归算法，必须保证每次都传入地址引用或者全局变量，使得每次递归操作有效
	 */
	public static  <T> void mergeSort(T[] list) {
		if (list.length > 1) { // 保证结束递归的条件，退回上一级，就是说（理想情况下）第一个开始排序树节点为最左下的节点，从算法的角度这个过程类似于数的后序历遍
			T[] listLeft = Arrays.copyOf(list, list.length / 2);
			mergeSort(listLeft);
			T[] listRight = Arrays.copyOfRange(list, list.length / 2, list.length);
			mergeSort(listRight);
			merge(listLeft, listRight, list); // 对有序两个数组的整合
		}
	}

	public static <T> void merge(T[] listLeft, T[] listRight, T[] list) {
		int i = 0, j = 0, k = 0;
		while (i < listLeft.length && j < listRight.length) {
			if (((Comparable<T>)listLeft[i]).compareTo(listRight[j])<0) {
				list[k] = listLeft[i];
				i++;
			} else {
				list[k] = listRight[j];
				j++;
			}
			k++;
		}
		while (i < listLeft.length) {
			list[k] = listLeft[i];
			k++;
			i++;
		}
		while (j < listRight.length) {
			list[k] = listRight[j];
			j++;
			k++;
		}
	}
	
	public static <T> void quickSort(T[] list){
		int first=0;
		int last=list.length-1;
		quickSort(list,first,last);
	}
	private static <T> void quickSort(T[] list,int first,int last){
		if(last>first) {
		int lastMainInedex=partSort(list,first,last);
		quickSort(list, first, lastMainInedex-1);
		quickSort(list, lastMainInedex+1, last);
		}
	}
	private static <T> int partSort(T[] list,int first,int last) {
		int low=first+1;
		int high=last;
		while(low<high) {
			while(low<=high&&((Comparable<T>)list[low]).compareTo(list[first])<=0) {
				low++;
			}
			while(low<=high&&((Comparable<T>)list[high]).compareTo(list[first])>0) {
				high--;
			}
			if(high>low) {
				T temp=list[low];
				list[low]=list[high];
				list[high]=temp;
			}
		}
		if(((Comparable<T>)list[high]).compareTo(list[first])<0) {
			T temp=list[first];
			list[first]=list[high];
			list[high]=temp;
		}
		return high;
	}
	
	/**
	 * @param <T>
	 * @param list 
	 * 此算法默认长度为0的数组为有序状态，插入2个数后再进行数的依次插入操作
	 * 是一种数组局部扩张排序的算法，对内存的消耗较小，不会产生临时数组
	 */
	public static <T> void insertionSort(T[] list) {
		for (int i = 1; i < list.length; i++) {			//i=1,保证先拿出2个数字自排序
			int k = i - 1;
			T temp = list[i];   //保存list[i]，防止被list[k]覆盖
			for (; k >= 0 && ((Comparable<T>)temp).compareTo(list[k])<0 ; k--) {
				list[k + 1] = list[k];
			}
			list[k + 1] = temp;      //插入空位
		}
	}
	/**
	 * 基数排序算法
	 * @param list
	 */
	public static  void radixSort(int[] list){
		    int maxBit= 1; //保存最大的位数
		    int p = 10;
		    for(int i = 0; i < list.length; ++i)
		    {
		        while(list[i] >= p)
		        {
		            p *= 10;
		            ++maxBit;
		        }
		    }
		    int e=0;
		    int n=1;//代表位数对应的数：1,10,100...
		    int k=0;//保存每一位排序后的结果用于下一位的排序输入
		    int length=list.length;
		    int[][] bucket=new int[10][length];//排序桶用于保存每次排序后的结果，这一位上排序结果相同的数字放在同一个桶里
		    int[] order=new int[10];//用于保存每个桶里有多少个数字
		    while(e<maxBit)
		    {
		        for(int num:list) //将数组array里的每个数字放在相应的桶里
		        {
		            int digit=(num/n)%10;
		            bucket[digit][order[digit]]=num;
		            order[digit]++;
		        }
		        for(int i=0;i<10;i++)//将前一个循环生成的桶里的数据覆盖到原数组中用于保存这一位的排序结果
		        {
		            if(order[i]!=0)//这个桶里有数据，从上到下遍历这个桶并将数据保存到原数组中
		            {
		                for(int j=0;j<order[i];j++)
		                {
		                    list[k]=bucket[i][j];
		                    k++;
		                }
		            }
		            order[i]=0;//将桶里计数器置0，用于下一次位排序
		        }
		        n*=10;
		        k=0;//将k置0，用于下一轮保存位排序结果
		        e++;
		    }
	}

}
