package 课程设计5;

import java.util.ArrayList;
import java.util.Scanner;

import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.beans.property.Property;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableListValue;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

/** 
* @author 作者 Your-Name: 
* @version 创建时间：2019年12月21日 下午1:14:40 
* 类说明 
*/
public class List extends Application{
	
	
	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage arg0) throws Exception {
		ListView<String> lists = new ListView<>();
		ArrayList<String> list = new ArrayList<String>();
		//list.add("b");
		    ObservableList<String> observableList = FXCollections.observableList(list);
		    observableList.addListener(new ListChangeListener<String>() {

				@Override
				public void onChanged(Change<? extends String> c) {
					if(c.next())
					System.out.println(list.get(list.size()-1));
				}
			});
		observableList.add("item one");
		observableList.add("item 2");
		observableList.add("item 3");
		observableList.add(0, "h");
		Scanner sc=new Scanner(System.in);
		Scene scene=new Scene(lists,500,400);
		arg0.setScene(scene);
		arg0.show();
	}

}
