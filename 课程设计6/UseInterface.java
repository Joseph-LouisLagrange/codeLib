package 课程设计6;

import java.util.GregorianCalendar;

import genericClass.GraphList;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * @author 作者 Your-Name:
 * @version 创建时间：2019年12月18日 下午6:59:05 类说明
 */
public class UseInterface extends Application implements EventHandler<Event> {
		private Stage stage=null;
	@Override
	public void start(Stage stage) throws Exception {
		this.stage=stage;
		GraphList<String, String> g = new GraphList<String, String>(GraphList.DG);
		g.addVertexListNode("北京");
		g.addVertexListNode("西宁");
		g.addVertexListNode("兰州");
		g.addVertexListNode("重庆");
		g.addVertexListNode("郑州");
		g.addVertexListNode("天津");
		g.addVertexListNode("上海");
		g.addVertexListNode("南昌");
		g.addVertexListNode("株洲");
		g.addVertexListNode("广州");
		g.addVertexListNode("贵阳");
		VBox vbox = new VBox();
		ScrollPane scrollpane = new ScrollPane();
		CityDistributionImage cityImage = new CityDistributionImage(g, 600, 200.0, 300.0);
		scrollpane.setContent(cityImage.getCityImagePane());
		scrollpane.setPrefHeight(400);
		vbox.getChildren().add(scrollpane);
		vbox.getChildren().add(new CityEditImage(g,vbox).getCityEditImagePane());
		vbox.getChildren().add(new TrainManage(g,vbox).getTrainManagePane());
		scrollpane = new ScrollPane();
		scrollpane.setPrefHeight(400);
		vbox.getChildren().add(scrollpane);
		Scene scene = new Scene(vbox, 1200, 1000);
		scene.setRoot(vbox);
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) throws Exception {
		launch(args);
	}

	@Override
	public void handle(Event event) {
		try {
			start(this.stage);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
