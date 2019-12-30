package 课程设计6;

import genericClass.EdgeListNode;
import genericClass.GraphList;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * @author 作者 Your-Name:
 * @version 创建时间：2019年12月17日 下午9:43:00 类说明
 */
public class CityDistributionImage {
	private GraphList<String, String> graph = null;
	private double[][] coordinatePoints = null;
	private double centerXPosition = 0;
	private double centerYPosition = 0;
	private double radiu = 200;
	private double offset=0;
	public CityDistributionImage(GraphList<String, String> gragh, double centerXPosition, double centerYPosition,
			double radiu) {
		this.setGraph(gragh);
		
		this.centerXPosition = centerXPosition;
		this.centerYPosition = centerYPosition;
		this.radiu = radiu;
		offset = Math.PI * 2 / this.graph.getVertexCount();
		while(2*this.radiu*Math.sin(offset/2)<100) {
			this.radiu+=50;
		}
	}

	public Pane getCityImagePane() throws Exception {
		return this.getCityImagePane(this.centerXPosition, this.centerYPosition, this.radiu);
	}

	public Pane getCityImagePane(double centerXPosition, double centerYPosition, double radiu) throws Exception {
		this.coordinatePoints = new double[this.graph.getVertexCount()][2];
		Button[] buttons = new Button[this.graph.getVertexCount()];
		double angle = 0;
		Pane pane = new Pane();
		for (int i = 0; i < this.graph.getVertexCount(); i++) {
			buttons[i] = new Button(this.graph.getVerTexListNodes()[i].getData());
			buttons[i].setFont(new Font(20));
			buttons[i].setOnMouseClicked(new QueryTrainTable<String, String>(this.graph, buttons[i].getText()));
			double x = this.centerXPosition + this.radiu * Math.cos(angle);
			double y = this.centerYPosition + this.radiu * Math.sin(angle);
			this.coordinatePoints[i][0] = x;
			this.coordinatePoints[i][1] = y;
			if (x <= 0 || y <= 0) {
				if (x <= 0) {
					this.centerXPosition += 10;
				}
				if (y <= 0) {
					this.centerYPosition += 10;
				}
				return this.getCityImagePane(this.centerXPosition, this.centerYPosition, this.radiu -= 10);
			}
			buttons[i].setLayoutX(x);
			buttons[i].setLayoutY(y);
			angle += offset;
		}
		pane.getChildren().add(this.getEdgesImage());
		pane.getChildren().addAll(buttons);
		return pane;
	}

	private Pane getEdgesImage() {
		Pane pane = new Pane();
		for (int i = 0; i < this.graph.getVertexCount(); i++) {
			for (EdgeListNode<String> j : this.getGraph().getVerTexListNodes()[i].getEdgeList()) {
				Line line = new Line(this.coordinatePoints[i][0] + 20, this.coordinatePoints[i][1] + 20,
						this.coordinatePoints[j.getVertexIndex()][0] + 20,
						this.coordinatePoints[j.getVertexIndex()][1] + 20);

				pane.getChildren().add(line);
			}
		}
		return pane;
	}

	public GraphList<String, String> getGraph() {
		return graph;
	}

	public void setGraph(GraphList<String, String> graph) {
		this.graph = graph;
	}

}
