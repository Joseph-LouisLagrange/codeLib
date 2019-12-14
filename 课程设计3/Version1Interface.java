package 课程设计3;

import java.util.Random;
import java.util.zip.DataFormatException;

import com.sun.xml.internal.ws.spi.db.DatabindingException;

import genericClass.SortClass;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * @author 作者 Your-Name:
 * @version 创建时间：2019年12月10日 下午3:19:40 类说明 版本一的界面层无逻辑业务
 */
public class Version1Interface {
	protected static final char[] cardColors = { 'F', 'H', 'M', 'T' };
	protected int[] numbers = null;
	protected Pane pane = null;

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

	public Version1Interface() {
		numbers = new int[4];
		this.produceNumbers();
	}
	
	
	/**
	 * 随机产生牌号
	 */
	protected void produceNumbers() {
		Random rand = new Random();
		boolean[] conflictArea = new boolean[13];
		for (int i = 0; i < conflictArea.length; i++) {
			conflictArea[i] = false;
		}
		int j = 0;
		while (true) {
			int index = rand.nextInt(13);
			if (conflictArea[index]) {
				continue;
			} else {
				this.numbers[j] = index+1;
				conflictArea[index] = true;
			}
			j++;
			if (j >= 4)
				break;
		}
	}

	/**
	 * 随机产生牌的花色
	 * 
	 * @return M,T,
	 */
	protected char getCardColor() {
		Random rand = new Random();
		return this.cardColors[rand.nextInt(4)];
	}

	
	public int[] getNumbers() {
		return numbers;
	}

	public void setNumbers(int[] numbers) {
		this.numbers = numbers;
	}
	
	public Pane getImagesPane() {
		HBox hbox = new HBox();
		hbox.setPadding(new Insets(50));
		hbox.setSpacing(50);
		for (int i = 0; i < 4; i++) {
			Image image = new Image("file:F:\\Program Files\\Eclipse\\数据结构与算法\\src\\pukeImage\\" + this.getCardColor()
					+ numbers[i] + ".jpg");
			ImageView imageview = new ImageView(image);
			hbox.getChildren().add(imageview);
		}
		return hbox;
	}

	public Pane getPane() {
		this.pane = new VBox(); // 根节点
		HBox[] hboxs = new HBox[3]; // 由上到下的三个部分
		hboxs[0] = new HBox();
		hboxs[0].setPrefWidth(685);
		hboxs[0].setPadding(new Insets(10, 50, 10, 470));
		Button refreshBtn = new Button("Refresh");
		refreshBtn.setOnAction(t -> {
				this.produceNumbers();
				this.pane.getChildren().set(1,  this.getImagesPane());
		});
		refreshBtn.setPrefSize(150, 40);
		hboxs[0].getChildren().add(refreshBtn);
		hboxs[1]=(HBox) this.getImagesPane();
		Label label = new Label("Enter a expression:");
		label.setFont(new Font(20));
		TextField expressionInput = new TextField();
		expressionInput.setAlignment(Pos.CENTER_LEFT);
		expressionInput.setPrefHeight(40);
		expressionInput.setPrefWidth(238);
		Button verifyBtn = new Button("Verify");
		verifyBtn.setOnAction(t -> {
			Alert alert = new Alert(AlertType.INFORMATION);
			try {
				String textInput = expressionInput.getText().replaceAll("\\s", "");
				if (textInput.equals("")) {
					alert.setAlertType(AlertType.WARNING); // 内容为空
					alert.setContentText("Please enter content");
				} else {
					PolishExpression p = new PolishExpression(textInput);
					// 判断输入表达式的数字对应性
					Integer[] temp = new Integer[4];
					for (int i = 0; i < this.numbers.length; i++) {
						temp[i] = new Integer(this.numbers[i]);
					}
					SortClass.<Integer>quickSort(temp);
					Double[] inputNumbers = p.getNumbers();
					SortClass.<Double>quickSort(inputNumbers);
					if (temp.length != 4 || inputNumbers.length != 4)
						throw new DataFormatException();
					for (int i = 0; i < 4; i++) {
						if (temp[i].intValue() != inputNumbers[i].doubleValue()) {
							throw new DataFormatException();
						}
					}

					// 判断是否为24点
					if (Math.round(p.getResult()) == 24) {
						alert.setContentText("Correct");
					} else {
						alert.setContentText("inCorrect result");
					}
				}
			} catch (DataFormatException e) {
				alert.setAlertType(AlertType.ERROR);// 数字不匹配
				alert.setContentText("The numbers in the expression don't match the numbers in the set");
			} catch (Exception e) {
				alert.setAlertType(AlertType.ERROR); // 表达式格式错误
				alert.setContentText("Malformed expression");
			}
			alert.showAndWait();
		});
		verifyBtn.setPrefSize(150, 40);
		hboxs[2] = new HBox();
		hboxs[2].setPrefWidth(685);
		hboxs[2].setPadding(new Insets(10, 50, 10, 50));
		hboxs[2].getChildren().add(label);
		hboxs[2].getChildren().add(expressionInput);
		hboxs[2].getChildren().add(verifyBtn);
		pane.getChildren().addAll(hboxs);
		return pane;
	}

	protected void setPane(Pane pane) {
		this.pane = pane;
	}

}
