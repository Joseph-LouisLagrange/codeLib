package 课程设计3;

import java.util.Arrays;

/**
 * @author 作者 Your-Name:
 * @version 创建时间：2019年12月15日 下午8:25:49 类说明
 */
public class Version3 {

	public static void main(String[] args) {
		int[] allNumbers = new int[52];
		for(int i=1;i<=13;i++){
			for(int j=0;j<4;j++){
				allNumbers[(i-1)*4+j]=i;
			}
		}
		int total = 0;
		int count=0;
		for (int i = 0; i < 52; i++) {
			for (int j = i + 1; j < 52; j++) {
				for (int k = j + 1; k < 52; k++) {
					for (int t = k + 1; t < 52; t++) {
						count++;
						Pointer24Game game = new Pointer24Game(allNumbers[i], allNumbers[j], allNumbers[k],
								allNumbers[t]);
						if(game.getResultStrings().length!=0) {
							total++;
						}
						game=null;
					}
				}
			}
		}
		System.out.println("Total number of combinations is" + count);
		System.out.println("Total number of combinations with solutions is " + total);
		System.out.println("The solution ratio is " + (double)total/(double)count);
	}
}
