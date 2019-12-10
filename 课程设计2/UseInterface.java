package 课程设计2;



import genericClass.BinaryTree;
import genericClass.DecimalTransformDevice;
import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import sun.awt.ScrollPaneWheelScroller;

/**
 * @author 作者 Your-Name:
 * @version 创建时间：2019年11月25日 下午4:28:58 类说明 用户的交互界面
 */
public class UseInterface extends Application {
	HuffmanDataPacket dataPacket=null;
	FileDataAisle dataAisle=null;
	public void start(Stage primaryStage) throws Exception {
		dataAisle=new FileDataAisle();
		dataPacket=new  HuffmanDataPacket();
		DataTransportDevice serviceTransition=new DataTransportDevice(dataAisle, dataPacket);
		HBox hbox1 = new HBox();
		HBox hbox2 = new HBox();
		BorderPane root = new BorderPane();
		Label label1 = new Label("Enter a text : ");
		label1.setFont(new Font(20));
		label1.setPadding(new Insets(2, 1, 0, 0));
		label1.setAlignment(Pos.BASELINE_LEFT);
		TextField textInput = new TextField();
		Button showTreeBtn = new Button();	
		showTreeBtn.setText("show Huffman Tree");
		hbox1.getChildren().addAll(label1, textInput, showTreeBtn);
		hbox1.setAlignment(Pos.CENTER);
		root.setTop(hbox1);
		//打印这棵树
		ScrollPane scrollpane = new ScrollPane(); 
	        root.setCenter(scrollpane);
	        showTreeBtn.setOnAction((e)-> {	
				try {								
					serviceTransition.sender(textInput.getText());
					System.out.println(dataPacket.getHuffmanTree().getRootNode().getData());
					TreeGragh<String> treeImage=new TreeGragh<String>(this.dataPacket.getHuffmanTree(),960,30,1910);
					scrollpane.setContent(treeImage.getTreePane());
					Alert alert = new Alert(AlertType.INFORMATION);
		            alert.titleProperty().set("编码");
					if(textInput.getText()==null||textInput.getText().equals("")) {
						alert.setAlertType(AlertType.WARNING);
						alert.headerTextProperty().set("内容为空");
					}else
						alert.headerTextProperty().set(textInput.getText()+":is encoded to "+dataPacket.getStringBitCode());            
		            alert.showAndWait();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			});
		Label label2 = new Label("Enter a bit string : ");
		label2.setFont(new Font(20));
		label2.setPadding(new Insets(2, 1, 0, 0));
		label2.setAlignment(Pos.BASELINE_LEFT);
		TextField codeInput = new TextField();
		Button decodeBtn = new Button("Decode Text");
		decodeBtn.setOnAction((e)->{
			String StringBitCode=codeInput.getText();	
			try {
				int dataCodeSuffix = StringBitCode.length() % 8;
				Byte[] dataCode = new Byte[StringBitCode.length() / 8 + (dataCodeSuffix == 0 ? 0 : 1)];
				// 表示二进制转为十进制
				DecimalTransformDevice DTD = new DecimalTransformDevice("2", "10");
				int i = 0, j = 0;
				for (; i + 8 < StringBitCode.length() && j < dataCode.length; i += 8, j++) {
					dataCode[j] = (byte) Integer.parseInt(DTD.getDecimalCode(StringBitCode.substring(i, i + 8)));
				}
				if (j < dataCode.length)
					dataCode[j] = (byte) Integer.parseInt(DTD.getDecimalCode(StringBitCode.substring(i)));
				dataPacket.setDataCode(dataCode);
				dataPacket.setDataCodeSuffix(dataCodeSuffix);
				dataAisle.exit(dataPacket);
				Alert alert = new Alert(AlertType.INFORMATION);
	            alert.titleProperty().set("解码");
				if(codeInput.getText()==null||codeInput.getText().equals("")) {
					alert.setAlertType(AlertType.WARNING);
					alert.headerTextProperty().set("内容为空");
				}else {
					alert.headerTextProperty().set(codeInput.getText()+":is decoded to "+serviceTransition.receive());            
					dataPacket=(HuffmanDataPacket) serviceTransition.getDataPacket();
				}
	            alert.showAndWait();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
		hbox2.getChildren().addAll(label2, codeInput, decodeBtn);
		root.setBottom(hbox2);
		codeInput.prefWidthProperty().bind(root.widthProperty().subtract(300));
		textInput.prefWidthProperty().bind(root.widthProperty().subtract(300));
		Scene scene = new Scene(root, 1910, 800);
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setTitle("数据编码系统");
	}
	

	public static void main(String[] args) throws Exception {
		
		
		launch(args);
	}
}
