package 课程设计5;

import java.util.ArrayList;

import com.sun.glass.ui.View;
import com.sun.org.apache.xpath.internal.FoundIndex;
import com.sun.xml.internal.org.jvnet.staxex.NamespaceContextEx.Binding;

import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.collections.ListChangeListener.Change;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.SelectionMode;
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
import javafx.util.Callback;

/**
 * @author 作者 Your-Name:
 * @version 创建时间：2019年12月21日 下午4:46:46 类说明
 */
public class ViewLayer {
	private ListView listView = null; // 列表视图
	private ObservableList<ContactPerson>[] listMeaasge = null; // 前后台的关联键
	private DataUse data = null;
	

	public ViewLayer(DataUse data) throws Exception {
		this.data = data;
		
		this.listMeaasge = data.getDirectionChart();
		this.listView = new ListView(); 																// 加载列表面板
		listView.setStyle("-fx-fixed-cell-size:50;");
		//this.listView.setStyle("-fx-vbar-policy:never;");
		listView.setPadding(new Insets(0,10,0,0));
		listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		listView.setCellFactory(new Callback<ListView<Object>, ListCell<Object>>() {
			@Override
			public ListCell<Object> call(ListView<Object> param) {
				return new ListCell<Object>() {
					@Override
					protected void updateItem(Object item, boolean empty) {
						super.updateItem(item, empty);
						if (empty || item == null) {
							setText(null);
							setGraphic(null);
						} else {
							if (item instanceof ContactPerson) {
								setText(item.toString());
								setTextFill(Color.BLACK);
								setDisable(false);
								setOnMouseClicked(event -> {
									if (event.getClickCount() == 2) {
										SubWinPop popWin = new SubWinPop(data, (ContactPerson) item);
										popWin.handle();
									}
								});
							} else {
								setText(((Label) item).getText());
								setDisable(true);
							}
						}
					}
				};
			}
		});

		// 初始化信息
		for (int i = 0; i < 27; i++) {
			if (!this.listMeaasge[i].isEmpty()) {
				Label label = new Label(String.valueOf((char) ('A' + i)));
				label.setFont(new Font(20));
				listView.getItems().add(label);
				listView.getItems().addAll(this.listMeaasge[i]);
			}

			// 根据监控的数据变化进行映射
			this.listMeaasge[i].addListener(new ListChangeListener<ContactPerson>() {
				@Override
				public void onChanged(Change<? extends ContactPerson> c) {
					int index = data.getCharIndex();
					int position = 0;
					int[]  correspondingQuantity=data.getCorrespondingQuantity();
					for (int i = 0; i < index; i++) {
						if (correspondingQuantity[i] != 0) {
							position += 1;
						}
						position += correspondingQuantity[i];
					}
					if (c.next()) {
						if (c.wasAdded()) {
							if (correspondingQuantity[index] == 1) {
								Label label = null;
								if (index < 26)
									label = new Label(String.valueOf((char) ('A' + index)));
								else
									label = new Label(String.valueOf('#'));
								label.setFont(new Font(20));
								label.setPrefWidth(380);
								listView.getItems().add(position, label);
							}
							listView.getItems().add(position + c.getTo(), c.getAddedSubList().get(0));
						}
						if (c.wasRemoved()) {
							listView.getItems().remove(position + c.getTo() + 1);
							if (correspondingQuantity[index] == 0)
								listView.getItems().remove(position);
						}
					}
				}
			});
		}
	}

	public Pane getListPane() {
		VBox vbox = new VBox();
		HBox hboxTop = new HBox();
		hboxTop.setAlignment(Pos.CENTER);
		hboxTop.setPrefHeight(40);
		TextField queryInput = new TextField();
		queryInput.setFocusTraversable(false);
		queryInput.setOnKeyReleased(event->{
			listView.getItems().clear();
			if(queryInput.getText().replaceAll("\\s", "").equals("")) {
				for (int i = 0; i < 27; i++) {
					if (!this.listMeaasge[i].isEmpty()) {
						Label label = new Label(String.valueOf((char) ('A' + i)));
						label.setFont(new Font(20));
						listView.getItems().add(label);
						listView.getItems().addAll(this.listMeaasge[i]);
					}
				}
			}else {
				listView.setItems(data.query(queryInput.getText()));
			}
		});
		queryInput.setPrefSize(400, 40);
		hboxTop.getChildren().addAll(queryInput);
		vbox.getChildren().add(hboxTop);
		vbox.getChildren().add(listView);

		HBox hboxBtn = new HBox();
		hboxBtn.setSpacing(30);
		hboxBtn.setAlignment(Pos.CENTER);
		Button addBtn = new Button("新增");
		addBtn.setPrefSize(100, 40);
		addBtn.setOnAction(event -> {
			SubWinPop popWin = new SubWinPop(data);
			popWin.handle();
		});
		Button removeBtn = new Button("删除");
		removeBtn.setPrefSize(100, 40);
		removeBtn.setOnAction(event -> {
		try {				
					this.data.removeAll(this.listView.getSelectionModel().getSelectedItems());
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		hboxBtn.getChildren().addAll(addBtn, removeBtn);
		vbox.getChildren().add(hboxBtn);
		return vbox;
	}

}
