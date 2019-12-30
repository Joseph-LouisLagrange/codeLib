package 课程设计6;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import genericClass.GraphList;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;

import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * @author 作者 Your-Name:
 * @version 创建时间：2019年12月18日 下午8:12:56 类说明
 */
public class TrainManage {
	private static final EventHandler<? super MouseEvent> String = null;
	private final Integer[] hours = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22,
			23 };
	private final Integer[] minuteOrSecond = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20,
			21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 45, 46, 47, 48,
			49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59 };

	private Pane pane = null;
	private GraphList<String, String> gragh = null;
	static String[] name = null;
	String[] optionalNames = null;

	public TrainManage(GraphList<String, String> gragh, Pane root) {
		this.pane = root;
		this.gragh = gragh;
		name = new String[this.gragh.getVertexCount()];
		for (int i = 0; i < name.length; i++) {
			this.name[i] = this.gragh.getVerTexListNodes()[i].getData();
		}
	}

	public void setGraph(GraphList<String, String> graph) {
		this.gragh = graph;
		name = new String[this.gragh.getVertexCount()];
		for (int i = 0; i < name.length; i++) {
			this.name[i] = this.gragh.getVerTexListNodes()[i].getData();
		}
	}

	private static String[] getOptionalNames(String selectedName) {
		String[] optionalNames = new String[name.length - 1];
		int i = 0;
		for (String s : name) {
			if (!s.equals(selectedName)) {
				optionalNames[i++] = s;
			}
		}
		return optionalNames;
	}

	static ChoiceBox<String> choiceBoxEnd = null;
	static ChoiceBox<String> choiceBoxStart = null;

	public Pane getTrainManagePane() throws Exception {
		VBox vbox = new VBox();
		vbox.setPadding(new Insets(20));
		HBox hboxBtns = new HBox();
		HBox hbox = new HBox();
		Label startLabel = new Label("始发站:");
		choiceBoxStart = new ChoiceBox<String>(FXCollections.observableArrayList(this.name));
		choiceBoxStart.setPrefWidth(75);
		choiceBoxStart.setValue("");
		choiceBoxStart.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Object>() {
			@Override
			public void changed(ObservableValue observable, Object oldValue, Object newValue) {
				if (newValue != null) {
					if (!newValue.equals(oldValue) && oldValue != null) {
						String value = choiceBoxEnd.getValue();
						choiceBoxEnd.setItems(FXCollections.observableArrayList(getOptionalNames((String) newValue)));
						choiceBoxEnd.setValue(value);
					}
				}
			}
		});
		HBox hboxTemp = new HBox();
		hboxTemp.getChildren().add(startLabel);
		hboxTemp.getChildren().add(choiceBoxStart);
		hbox.getChildren().add(hboxTemp);

		hboxTemp = new HBox();
		Label startHour = new Label("时:");
		ChoiceBox<Integer> startChoiceBoxHour = new ChoiceBox<Integer>(FXCollections.observableArrayList(this.hours));
		hboxTemp.getChildren().add(startHour);
		hboxTemp.getChildren().add(startChoiceBoxHour);
		hbox.getChildren().add(hboxTemp);

		hboxTemp = new HBox();
		Label startMinute = new Label("分:");
		ChoiceBox<Integer> StartChoiceBoxMinute = new ChoiceBox<Integer>(
				FXCollections.observableArrayList(this.minuteOrSecond));
		hboxTemp.getChildren().addAll(startMinute, StartChoiceBoxMinute);
		hbox.getChildren().add(hboxTemp);

		Label endLabel = new Label("到达站:");
		choiceBoxEnd = new ChoiceBox<String>(FXCollections.observableArrayList(this.name));
		choiceBoxEnd.setPrefWidth(75);
		choiceBoxEnd.setValue("");
		choiceBoxEnd.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
			@Override
			public void changed(ObservableValue observable, Object oldValue, Object newValue) {
				if (newValue != null) {
					if (!newValue.equals(oldValue) && oldValue != null) {
						String value = choiceBoxStart.getValue();
						choiceBoxStart.setItems(FXCollections.observableArrayList(getOptionalNames((String) newValue)));
						choiceBoxStart.setValue(value);
					}
				}
			}
		});
		hboxTemp = new HBox();
		hboxTemp.getChildren().add(endLabel);
		hboxTemp.getChildren().add(choiceBoxEnd);
		hbox.getChildren().add(hboxTemp);

		hboxTemp = new HBox();
		Label endHour = new Label("时:");
		ChoiceBox<Integer> endChoiceBoxHour = new ChoiceBox<Integer>(FXCollections.observableArrayList(this.hours));
		hboxTemp.getChildren().addAll(endHour, endChoiceBoxHour);
		hbox.getChildren().add(hboxTemp);

		hboxTemp = new HBox();
		Label endMinute = new Label("分:");
		ChoiceBox<Integer> endChoiceBoxMinute = new ChoiceBox<Integer>(
				FXCollections.observableArrayList(this.minuteOrSecond));
		hboxTemp.getChildren().addAll(endMinute, endChoiceBoxMinute);
		hbox.getChildren().add(hboxTemp);

		hboxTemp = new HBox();
		Label trainNumber = new Label("车次号:");
		TextField trainNumberInput = new TextField();
		hboxTemp.getChildren().add(trainNumber);
		hboxTemp.getChildren().add(trainNumberInput);
		hbox.getChildren().add(hboxTemp);

		hboxTemp = new HBox();
		Label money = new Label("费用:");
		TextField moneyInput = new TextField();
		hboxTemp.getChildren().addAll(money, moneyInput);
		hbox.getChildren().add(hboxTemp);

		hbox.setSpacing(12);

		// 新增车次
		Button addTrainBtn = new Button("新增车次");
		addTrainBtn.setOnMouseClicked(event -> {
			try {
				Calendar temp = Calendar.getInstance();
				int year = temp.get(Calendar.YEAR);
				int month = temp.get(Calendar.MONTH);
				int startDay = temp.get(Calendar.DAY_OF_MONTH);
				int endDay = temp.get(Calendar.DAY_OF_MONTH);
				if (startChoiceBoxHour.getValue() > endChoiceBoxHour.getValue()
						|| (endChoiceBoxMinute.getValue() <= StartChoiceBoxMinute.getValue()
								&& startChoiceBoxHour.getValue() == endChoiceBoxHour.getValue())) {
					endDay++;
				}
				this.gragh.addEdge(choiceBoxStart.getValue(), choiceBoxEnd.getValue(), trainNumberInput.getText(),
						new CityPathWeight(
								new GregorianCalendar(year, month, startDay, startChoiceBoxHour.getValue(),
										StartChoiceBoxMinute.getValue(), 0),
								new GregorianCalendar(year, month, endDay, endChoiceBoxHour.getValue(),
										endChoiceBoxMinute.getValue(), 0),
								Double.parseDouble(moneyInput.getText()), CityPathWeight.DURATION));
				ScrollPane scrollPane = (ScrollPane) this.pane.getChildren().get(0);
				scrollPane.setContent(new CityDistributionImage(this.gragh, 600, 200.0, 300.0).getCityImagePane());
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		// 删除车次
		Button removeTrainBtn = new Button("删除车次");
		removeTrainBtn.setOnMouseClicked(event -> {
			try {
				if (trainNumberInput.getText().equals(""))
					this.gragh.removeEdgeNode(choiceBoxStart.getValue(), choiceBoxEnd.getValue());
				else
					this.gragh.removeEdgeNodeByName(choiceBoxStart.getValue(), choiceBoxEnd.getValue(),
							trainNumberInput.getText());
				ScrollPane scrollPane = (ScrollPane) this.pane.getChildren().get(0);
				scrollPane.setContent(new CityDistributionImage(this.gragh, 600, 200.0, 300.0).getCityImagePane());
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		// 修改车次
		Button alterTrainBtn = new Button("修改车次");
		alterTrainBtn.setOnMouseClicked(event -> {
			Calendar temp = Calendar.getInstance();
			try {
				int year = temp.get(Calendar.YEAR);
				int month = temp.get(Calendar.MONTH);
				int day = temp.get(Calendar.DAY_OF_MONTH);
				String startVertexNode = choiceBoxStart.getValue();
				String endVertexNode = choiceBoxEnd.getValue();
				String edgeNodeName = trainNumberInput.getText();
				Integer startHourTemp = startChoiceBoxHour.getValue();
				Integer startMinuteTemp = StartChoiceBoxMinute.getValue();
				Integer endHourTemp = endChoiceBoxHour.getValue();
				Integer endMinuteTemp = endChoiceBoxMinute.getValue();
				CityPathWeight p = (CityPathWeight) this.gragh
						.getEdgeNodeByAndName(startVertexNode, endVertexNode, edgeNodeName).getWeight();
				if (!moneyInput.getText().replaceAll("\\s", "").equals("")) {
					p.setMoney(Double.parseDouble(moneyInput.getText().replaceAll("\\s", "")));
				}
				if (startChoiceBoxHour.getValue() != null && StartChoiceBoxMinute.getValue() != null) {
					p.setStartTime(new GregorianCalendar(year, month, day, startHourTemp, startMinuteTemp, 0));
				}
				if (endChoiceBoxHour.getValue() != null && endChoiceBoxMinute.getValue() != null) {
					if(startHourTemp.compareTo(endHourTemp)>=0||(startHourTemp.compareTo(endHourTemp)==0&&startMinuteTemp.compareTo(endMinuteTemp)>=0))
						p.setEndTime(new GregorianCalendar(year, month, day+1, endHourTemp, endMinuteTemp, 0));
				}
				this.gragh.setEdgeNode(startVertexNode, endVertexNode, edgeNodeName, p);
				ScrollPane scrollPane = (ScrollPane) this.pane.getChildren().get(0);
				scrollPane.setContent(new CityDistributionImage(this.gragh, 600, 200.0, 300.0).getCityImagePane());
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		VBox vboxRadio = new VBox();
		ToggleGroup group = new ToggleGroup();
		RadioButton button1 = new RadioButton("时长优先");
		button1.setUserData("时长优先");
		button1.setToggleGroup(group);
		button1.setSelected(true);
		RadioButton button2 = new RadioButton("中转优先");
		button2.setUserData("中转优先");
		button2.setToggleGroup(group);
		RadioButton button3 = new RadioButton("费用优先");
		button3.setUserData("费用优先");
		button3.setToggleGroup(group);
		vboxRadio.getChildren().add(button1);
		vboxRadio.getChildren().add(button2);
		vboxRadio.getChildren().add(button3);

		Button queryTrainBtn = new Button("查询车次");
		queryTrainBtn.setOnMouseClicked(event -> {
			try {
				ScrollPane scrollPane = (ScrollPane) this.pane.getChildren().get(3);
				System.out.println(group.getSelectedToggle().getUserData().toString());
				scrollPane.setContent(new RouterImage(gragh, choiceBoxStart.getValue(), choiceBoxEnd.getValue(),
						group.getSelectedToggle().getUserData().toString()).getPane());
			} catch (Exception e) {
				e.printStackTrace();
			}

		});
		hboxBtns.getChildren().addAll(addTrainBtn, removeTrainBtn, alterTrainBtn, vboxRadio, queryTrainBtn);
		hboxBtns.setSpacing(180);
		hboxBtns.setPadding(new Insets(20, 0, 10, 0));
		vbox.getChildren().addAll(hbox, hboxBtns);

		return vbox;
	}

}
