package 课程设计3;

/**
 * @author 作者 Your-Name:
 * @version 创建时间：2019年12月15日 下午8:25:49 类说明
 */
public class Version3 {

	public static void main(String[] args) {
		int[] allNumbers = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
		int total = 0;
		for (int i = 0; i < 13; i++) {
			for (int j = i + 1; j < 13; j++) {
				for (int k = j + 1; k < 13; k++) {
					for (int t = k + 1; t < 13; t++) {
						
						Pointer24Game game = new Pointer24Game(allNumbers[i], allNumbers[j], allNumbers[k],
								allNumbers[t]);		
						total +=game.getResultStrings().length;
					}
				}
			}
		}
		System.out.println(total);
	}
}
