package 课程设计5;

import java.util.ArrayList;
import java.util.Scanner;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
* @author 作者 Your-Name:
* @version 创建时间：2019年12月21日 下午5:28:21
* 类说明
*/
public class UseInterface extends Application {

	public static void main(String[] args) {
		launch(args);

	}
	@Override
	public void start(Stage primaryStage) throws Exception {
		DataUse u=new DataUse();
		u.add(new ContactPerson("段芝华","1586308823@qq.com","13387383220"));
		u.add(new ContactPerson("李媛","1530768033@qq.com","15973878388"));
		u.add(new ContactPerson("胡振海","254658861@qq.com","13034849188"));
		u.add(new ContactPerson("蒋科辉","363590318@qq.com","13410073580"));
		u.add(new ContactPerson("李拉","2399689823@qq.com","13707387031"));
		u.add(new ContactPerson("李伟","10769008@qq.com","15973802899"));
		u.add(new ContactPerson("李凤尾","506753876@qq.com","13673086150"));
		u.add(new ContactPerson("刘源","2293371031@qq.com","15173897920"));
		u.add(new ContactPerson("小丽由","1061675430@qq.com","18975672203"));
		u.add(new ContactPerson("谢文艺","541141887@qq.com","13973801687"));
		u.add(new ContactPerson("杨平","1025493987@qq.com","13787486908"));
		u.add(new ContactPerson("邹位","1262875178@qq.com","13787385566"));
		u.add(new ContactPerson("阿太","254653461@qq.com","13089149188"));
		ViewLayer v=new ViewLayer(u);
		Scene scene=new Scene(v.getListPane(),400,470);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
