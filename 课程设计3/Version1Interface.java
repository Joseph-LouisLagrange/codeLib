package 课程设计3;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * @author 作者 Your-Name:
 * @version 创建时间：2019年12月10日 下午3:19:40 类说明 版本一的界面层无逻辑业务
 */
public class Version1Interface {
	private int[] numbers = null;
	private Pane pane = null;

	/**
	 * 获取四张牌的牌号
	 * 
	 * @param a 牌数1
	 * @param b 牌数2
	 * @param c 牌数3
	 * @param d 牌数4
	 */
	public Version1Interface(int a, int b, int c, int d) {
		numbers = new int[4];
		numbers[0] = a;
		numbers[1] = b;
		numbers[2] = c;
		numbers[3] = d;
	}
	/**
	 * 随机产生牌的花色
	 * @return M,T,
	 */
	private char getCardColor() {
		
	}
	
	public int[] getNumbers() {
		return numbers;
	}

	public void setNumbers(int[] numbers) {
		this.numbers = numbers;
	}

	public Pane getPane() {
		this.pane = new VBox();
		HBox hbox = new HBox();
		Button refreshBtn = new Button("Refresh");
		hbox.getChildren().add(refreshBtn);
		Image[] images = new Image[4];
		for (int i = 0; i < images.length; i++) {
			images[i]=new Image("");
		}
		return pane;
	}

	public void setPane(Pane pane) {
		this.pane = pane;
	}

}
