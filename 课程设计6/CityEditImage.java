package 课程设计6;

import genericClass.GraphList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.Pane;

/** 
* @author 作者 Your-Name: 
* @version 创建时间：2019年12月18日 下午6:48:08 
* 类说明 
*/
public class CityEditImage {
	private GraphList<String, String> gragh=null;
	private Pane pane=null;
	public CityEditImage(GraphList<String, String> gragh,Pane root) {
			this.gragh=gragh;
			this.pane=root;
	}
	
	public Pane getCityEditImagePane() {
		Pane pane=new Pane();
		TextField addInput=new TextField();
		addInput.setLayoutX(580);
		addInput.setLayoutY(10);
		pane.getChildren().add(addInput);
		Button addCityBtn=new Button("新增城市");
		addCityBtn.setPrefSize(80,50);
		addCityBtn.setLayoutX(800);
		addCityBtn.setOnMouseClicked(event->{
				try {
					String text=addInput.getText();
						if(text==null||text.equals(""))
					throw new Exception("请输入城市");
					this.gragh.addVertexListNode(text);
					
					this.pane.getChildren().set(2, new TrainManage(gragh,this.pane).getTrainManagePane());
					ScrollPane  scrollpane=(ScrollPane) this.pane.getChildren().get(0);
					 scrollpane.setContent(new CityDistributionImage(this.gragh,600, 200.0, 300.0).getCityImagePane());
					this.pane.getChildren().set(0,scrollpane);
				} catch (Exception e) {
					Alert alert=new Alert(AlertType.ERROR);
					alert.setContentText(e.getMessage());
					alert.showAndWait();
				}
		});
		TextField removeInput=new TextField();
		removeInput.setLayoutX(180);
		removeInput.setLayoutY(10);
		pane.getChildren().add(removeInput);
		Button removeCityBtn=new Button("删除城市");
		removeCityBtn.setPrefSize(80,50);
		removeCityBtn.setLayoutX(400);
		removeCityBtn.setOnMouseClicked(event->{
			try {
				String text=removeInput.getText();
					if(text==null||text.equals(""))
				throw new Exception("请输入城市");
				this.gragh.removeVertexListNode(text);		
				this.pane.getChildren().set(2, new TrainManage(gragh,this.pane).getTrainManagePane());
				ScrollPane scrollpane = (ScrollPane) this.pane.getChildren().get(0);
				 scrollpane.setContent(new CityDistributionImage(this.gragh,600, 200.0, 300.0).getCityImagePane());
				 
			} catch (Exception e) {
				Alert alert=new Alert(AlertType.ERROR);
				alert.setContentText(e.getMessage());
				alert.showAndWait();
			}
		});
        pane.getChildren().add(addCityBtn);
        pane.getChildren().add(removeCityBtn);
		return pane;
	}
}
