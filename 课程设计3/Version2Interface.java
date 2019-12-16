package 课程设计3;

import java.util.Arrays;
import java.util.zip.DataFormatException;

import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * @author 作者 Your-Name:
 * @version 创建时间：2019年12月14日 下午7:45:11 类说明
 */
public class Version2Interface extends Version1Interface {

	/**
	 * 
	 */
	public Version2Interface() {
		super();

	}

	/**
	 * @param a
	 * @param b
	 * @param c
	 * @param d
	 */
	public Version2Interface(int a, int b, int c, int d) {
		super(a, b, c, d);
	}

	/**
	 * 加载用户自定义输入的节点
	 * 
	 * @return
	 */
	private Pane getInputNumbersPane() {
		VBox vbox = new VBox();
		HBox[] hboxs = new HBox[3];
		Label label = new Label("Input four numbers between 1 to 13 :");
		label.setFont(new Font(30));
		hboxs[0] = new HBox();
		hboxs[0].getChildren().add(label);
		TextField[] numbersInput = new TextField[4];
		for (int i = 0; i < 4; i++) {
			numbersInput[i] = new TextField();
			numbersInput[i].setPrefSize(200, 150);
			numbersInput[i].setFont(new Font(50));
		}
		hboxs[1] = new HBox();
		hboxs[1].getChildren().addAll(numbersInput);
		TextArea textArea = new TextArea();
		textArea.setPrefWidth(600);
		textArea.setBackground(new Background(new BackgroundFill(Color.LAVENDERBLUSH, null, null)));
		textArea.setFont(new Font(20));
		Button findBtn = new Button("Find a Solution");
		findBtn.setOnMouseClicked(t -> {
			Alert alert = new Alert(AlertType.ERROR);
			int[] numbers = new int[4];
			try {
				for (int i = 0; i < numbersInput.length; i++) {
					if (numbersInput[i].getText().equals("") || numbersInput[i].getText() == null)
						throw new NullPointerException("输入数据为空"); // 输入内容空异常
					numbers[i] = Integer.parseInt(numbersInput[i].getText());
					if (numbers[i] > 13 || numbers[i] < 1) {
						throw new DataFormatException("数字越界"); // 数字越界异常
					}
				}
				// 传递给底层逻辑
				Pointer24Game game = new Pointer24Game(numbers[0], numbers[1], numbers[2], numbers[3]);
				String content = "";
				// 获取表达式组
				for (String i : game.getResultStrings()) {
					content += i + "\n";
				}
				if (content == "")
					content = "No solution";
				textArea.setText(content);
			} catch (NumberFormatException e) {
				alert.setContentText("数字的输入格式有误");
				alert.showAndWait();
			} catch (DataFormatException e) {
				alert.setContentText("请输入1-13之间不同的整数");
				alert.showAndWait();
			} catch (NullPointerException e) {
				alert.setContentText("请输入4个整数");
				alert.showAndWait();
			}
		});
		findBtn.setPrefSize(200, 260);
		findBtn.setBackground(new Background(new BackgroundFill(Color.BURLYWOOD, null, null)));
		findBtn.setOnMouseEntered(t -> {
			findBtn.setCursor(Cursor.HAND);
			findBtn.setBackground(new Background(new BackgroundFill(Color.DARKTURQUOISE, null, null)));
		});
		findBtn.setOnMouseExited(t -> {
			findBtn.setBackground(new Background(new BackgroundFill(Color.BURLYWOOD, null, null)));
		});
		hboxs[2] = new HBox();
		hboxs[2].getChildren().addAll(textArea, findBtn);
		vbox.getChildren().addAll(hboxs);
		return vbox;
	}

	public Pane getPane() {
		this.pane = super.getPane();
		Pane hbox = (Pane) this.pane.getChildren().get(0);
		Button findSolutionBtn = new Button("Find a Solution");
		TextArea textArea = new TextArea();
		textArea.setPrefSize(238, 40);
		findSolutionBtn.setPrefSize(150, 40);
		findSolutionBtn.setOnMouseClicked(t -> {
			if (t.getClickCount() == 2) {
				Stage stage = new Stage();// 弹出窗口
				Scene scene = new Scene(this.getInputNumbersPane(), 800, 410);
				stage.setScene(scene);
				stage.setMaxWidth(scene.getWidth());
				stage.setMaxHeight(scene.getHeight());
				stage.show();
			} else {
				Pointer24Game game = new Pointer24Game(this.numbers[0], this.numbers[1], this.numbers[2],
						this.numbers[3]);
				String content = "";
				for (String i : game.getResultStrings()) {
					content += i + "\n";
				}
				if (content == "")
					content = "No solution";
				textArea.setText(content);
			}
		});
		hbox.getChildren().add(0, textArea);
		hbox.getChildren().add(0, findSolutionBtn);
		hbox.setPadding(new Insets(10, 50, 0, 50));
		return super.pane;
	}
}
