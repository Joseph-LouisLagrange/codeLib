package 课程设计6;

import genericClass.EdgeListNode;
import genericClass.GraphList;
import genericClass.SortClass;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * @author 作者 Your-Name:
 * @version 创建时间：2019年12月18日 下午12:57:48 类说明
 */
public class QueryTrainTable<T, E> implements EventHandler<Event> {

	GraphList<T, E> graph = null;
	T startVertexNode = null;
	T endVertexNode = null;

	public QueryTrainTable(GraphList<T, E> graph, T startVertexNode, T endVertexNode) throws Exception {
		this.graph = graph;
		this.setStartVertexNode(startVertexNode);
		this.endVertexNode = endVertexNode;
	}

	public QueryTrainTable(GraphList<T, E> graph, T startVertexNode) throws Exception {
		this.graph = graph;
		this.setStartVertexNode(startVertexNode);
	}

	public GraphList<T, E> getGraph() {
		return graph;
	}

	public void setGraph(GraphList<T, E> graph) {
		this.graph = graph;
	}

	public T getStartVertexNode() {

		return startVertexNode;
	}

	public void setStartVertexNode(T startVertexNode) throws Exception {
		if (startVertexNode == null)
			throw new Exception("起始节点为空");
		this.startVertexNode = startVertexNode;
	}

	public T getEndVertexNode() {
		return endVertexNode;
	}

	public void setEndVertexNode(T endVertexNode) {
		this.endVertexNode = endVertexNode;
	}

	/**
	 * 查询所有邻接路径
	 * 
	 * @param startVertexNode
	 * @return
	 * @throws Exception
	 */
	public EdgeListNode<E>[] QueryByVertex(T startVertexNode) throws Exception {
		Object[] temp = this.graph.getEdgeNodesByVertex(startVertexNode);
		EdgeListNode<E>[] list = new EdgeListNode[temp.length];
		int i = 0;
		for (Object e : temp) {
			list[i++] = (EdgeListNode<E>) e;
		}
		return list;
	}

	/**
	 * 查询指定顶点间的路径
	 * 
	 * @param startVertexNode
	 * @param endVertexNode
	 * @return
	 * @throws Exception
	 */
	public EdgeListNode<E>[] QueryByVerToVer(T startVertexNode, T endVertexNode) throws Exception {
		return this.graph.getEdgeNodesByVertexToVertex(startVertexNode, endVertexNode);
	}

	@Override
	public void handle(Event event) {
		Stage stage = new Stage();

		try {
			EdgeListNode<E>[] list = null;
			if (this.endVertexNode == null) {
				list = this.QueryByVertex(this.startVertexNode);
			} else {
				list = this.QueryByVerToVer(startVertexNode, endVertexNode);
			}

			Scene scene = new Scene(this.getTrainMessagePane(list), 800, 600);
			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unused")
	public Pane getTrainMessagePane(EdgeListNode<E>[] list) {
		VBox vbox = new VBox();
		vbox.setPrefSize(800, 600);
		if (list == null || list.length == 0) {
			Label label = new Label("暂无由此为始发站的班次");
			label.setPadding(new Insets(1, 0, 1, 80));
			label.setFont(new Font(20));
			vbox.getChildren().add(label);
		} else {
			SortClass.quickSort(list);
			for (int i = 0; i < list.length;) {
				VBox vboxTemp = new VBox();
				String vertexNodeName = (String) this.graph.getVerTexListNodes()[list[i].getVertexIndex()].getData();
				Label label = new Label((String) this.startVertexNode + "-->" + vertexNodeName);
				label.setFont(new Font(20));
				vboxTemp.getChildren().add(label);
				int j = i;
				while (list[i].getVertexIndex() == list[j].getVertexIndex()) {
					HBox hbox = new HBox();
					Text name = new Text((String) list[j].getEdgeNodeName());
					name.setFont(new Font(20));
					hbox.getChildren().add(name);
					Text startTime = new Text("出发时刻:" + list[j].getWeight().getValueByKey(CityPathWeight.STARTTIME));
					startTime.setFont(new Font(20));
					hbox.getChildren().add(startTime);
					Text endTime = new Text("到达时刻:" + list[j].getWeight().getValueByKey(CityPathWeight.ENDTIME));
					endTime.setFont(new Font(20));
					hbox.getChildren().add(endTime);
					Text duration = new Text("时长为:" + list[j].getWeight().getValueByKey(CityPathWeight.DURATION));
					duration.setFont(new Font(20));
					hbox.getChildren().add(duration);
					Text money = new Text("费用为:" + list[j].getWeight().getValueByKey(CityPathWeight.MONEY));
					money.setFont(new Font(20));
					hbox.getChildren().add(money);
					hbox.setSpacing(20);
					vboxTemp.getChildren().add(hbox);
					j++;
					if (j >= list.length)
						break;
				}
				i = j;
				vboxTemp.setSpacing(5);
				vbox.getChildren().add(vboxTemp);
			}
		}
		return vbox;
	}

}
