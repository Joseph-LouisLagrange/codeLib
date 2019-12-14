package 课程设计3;



import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

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
	
	
	public Pane getPane() {
		this.pane=super.getPane();
		Pane hbox=(Pane) this.pane.getChildren().get(0);
		Object button= hbox.getChildren().get(0);
		Button findSolutionBtn=new Button("Find a Solution");
		findSolutionBtn.setPrefSize(150, 40);
		TextField textInput=new TextField();
		textInput.setPrefSize(260, 40);
		hbox.getChildren().add(0, textInput);
		hbox.getChildren().add(0, findSolutionBtn);
		hbox.setPadding(new Insets(10,50,10,50));
		return  super.pane;
	}
}
