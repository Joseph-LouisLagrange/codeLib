package 课程设计3;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/** 
* @author 作者 Your-Name: 
* @version 创建时间：2019年12月10日 下午3:16:35 
* 类说明 
*/
public class UseInterface extends Application{

	public static void main(String[] args) throws Exception {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Version2Interface v=new Version2Interface(1,3,4,2);
		Pane pane=v.getPane();
		Scene scene = new Scene(pane,685,400);
		primaryStage.setMinWidth(685);
		primaryStage.setMinHeight(400);
		primaryStage.setMaxHeight(400);
		primaryStage.setMaxWidth(685);
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setTitle("24-Point Card Game");
	}

}
