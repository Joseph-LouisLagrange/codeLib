package namespace;

/**
 * @author 作者 Your-Name:
 * @version 创建时间：2019年12月8日 下午2:48:13 类说明
 */
public class HeadSort {
	public static void main(String[] args) {
		int[] list = { 1, 5, 8, 10, 2, 50,786, 9, 0,3,3};
		for (int i = list.length - 1; i >= 0; i--) {
			heapAdjust(list, i, list.length);
		}
		for (int i = 1; i <= list.length; i++) {
			int length=list.length - i;
			int temp = list[0];
			list[0] = list[length];
			list[length] = temp;
			for (int j =length ; j >= 0; j--) {
				heapAdjust(list, j, length );
			}
			System.out.println(temp);
		}
	}

	public static void heapAdjust(int[] list, int low, int length) {
		for (int j = low * 2 + 1; j < length - 1; j = low * 2 + 1) {
			if (list[j] > list[j + 1])
				j++;
			if (list[low] < list[j])
				break;
			int temp = list[j];
			list[j] = list[low];
			list[low] = temp;
			low = j;
		}
	}
}
