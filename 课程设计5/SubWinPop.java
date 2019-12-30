package 课程设计5;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * @author 作者 Your-Name:
 * @version 创建时间：2019年12月27日 上午10:01:44 类说明
 */
public class SubWinPop {
	private DataUse data = null;
	private ContactPerson selectedContact = null;

	public SubWinPop(DataUse data, ContactPerson selectedContact) {
		this.data = data;
		this.selectedContact = selectedContact;
	}

	public SubWinPop(DataUse data) {
		this.data = data;
	}

	public void handle() {
		Stage stage = new Stage();
		VBox vbox = new VBox();
		vbox.setSpacing(30);
		vbox.setPrefWidth(325);
		vbox.setPadding(new Insets(30));
		HBox hbox = new HBox();
		Label labelName = new Label("姓名 : ");
		TextField nameInput = new TextField();
		nameInput.setFocusTraversable(false);
		nameInput.setAlignment(Pos.CENTER);
		nameInput.setPrefWidth(200);
		hbox.getChildren().addAll(labelName, nameInput);
		vbox.getChildren().add(hbox);

		hbox = new HBox();
		Label labelTele = new Label("电话 : ");
		TextArea teleInput = new TextArea();
		teleInput.setFocusTraversable(false);
		teleInput.setPrefSize(200, 100);
		hbox.getChildren().addAll(labelTele, teleInput);
		vbox.getChildren().add(hbox);

		hbox = new HBox();
		Label labelMail = new Label("邮箱 : ");
		TextField mailInput = new TextField();
		mailInput.setFocusTraversable(false);
		mailInput.setPrefWidth(200);
		mailInput.setAlignment(Pos.CENTER);
		hbox.getChildren().addAll(labelMail, mailInput);
		vbox.getChildren().add(hbox);

		hbox = new HBox();
		hbox.setAlignment(Pos.CENTER);
		hbox.setSpacing(50);
		Button saveBtn = new Button("保存");
		Button rebackBtn = new Button("返回");

		hbox.getChildren().addAll(saveBtn, rebackBtn);
		vbox.getChildren().add(hbox);
		if (selectedContact != null) {
			nameInput.setText(selectedContact.getName());
			String temp = "";
			for (String i : selectedContact.getTelephone()) {
				temp += i + "\n";
			}
			teleInput.setText(temp);
			mailInput.setText(selectedContact.getMailbox());
		}
		saveBtn.setOnAction(ev -> {
			try {
				if (selectedContact != null) {
					this.data.remove(selectedContact);
				}
				String[] teles = teleInput.getText().replace(" ", "").split("\n");
				this.data.add(new ContactPerson(nameInput.getText(), mailInput.getText(), teles));
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				stage.close();
			}
		});
		rebackBtn.setOnAction(eve -> {
			stage.close();
		});
		Scene scene = new Scene(vbox,325, 400);
		stage.setScene(scene);
		stage.show();

	}

}
