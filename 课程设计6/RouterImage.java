package 课程设计6;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import genericClass.EdgeListNode;
import genericClass.GraphList;
import genericClass.LinkedList;
import javafx.beans.value.ObservableObjectValue;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import sun.awt.image.ImageWatched.Link;

/**
 * @author 作者 Your-Name:
 * @version 创建时间：2019年12月19日 下午3:14:09 类说明
 */
public class RouterImage {
	String strDateFormat = "yyyy-MM-dd HH:mm:ss";
	SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);
	String strDate = "dd HH:mm:ss";
	String strDate2="HH:mm:ss";
	SimpleDateFormat sdf2 = new SimpleDateFormat(strDate );
	private GraphList<String, String> graph = null;
	private String startCity = null;
	private String endCity = null;
	private String sortKey="时长优先";
	public RouterImage(GraphList<String, String> graph, String startCity, String endCity,String sortKey) throws Exception {		
		this.graph = graph;
		this.startCity = startCity;
		this.endCity = endCity;
		this.sortKey=sortKey;
	}
	
	private int compareByKey(CityPath  a,CityPath  b) throws Exception {
			if(this.sortKey.equals("时长优先")) {
				return Long.compare(a.getTotalTime(), b.getTotalTime());
			}else if(this.sortKey.equals("中转优先")) {
				return Integer.compare(a.getTransitNumber(), b.getTransitNumber());
			}else if(this.sortKey.equals("费用优先")) {
				return Double.compare(a.getTotalFee(), b.getTotalFee());
			}else
				throw new Exception("无此排序键");
	}
	
	public void sortByKey(LinkedList<Path> routers) throws Exception {
		for(int i=0;i<routers.getSize();i++) {
			for (int j=i;j<routers.getSize()-1;j++) {
				 if(this.compareByKey((CityPath)routers.get(j), (CityPath)routers.get(j+1))>0) {
					 	routers.exchange(j, j+1);
				 }
			}
		}
	}

	public Pane getPane() throws Exception {
		VBox vbox = new VBox();
		vbox.setSpacing(20);
		this.graph.pathSearch(startCity, endCity, new CityPath());
		LinkedList<Path> routers = this.graph.getPaths();
		this.sortByKey(routers);
		for (Path p : routers) {
			HBox hbox = new HBox();
			hbox.setSpacing(20);
			CityPath temp = (CityPath) p;
			String lastCity = temp.getStartCity();
			VBox a = new VBox();
			a.setAlignment(Pos.CENTER);
			a.getChildren().add(new Label(sdf.format(Calendar.getInstance().getTime())));
			a.getChildren().add(new Label("当前位置"));
			hbox.getChildren().add(a);
			for (int i = 0; i < temp.getWaitTimes().getSize(); i++) {	
				a = new VBox();
				a.setAlignment(Pos.CENTER);
				a.getChildren().add(new Label(temp.getWaitTimes().get(i)));
				a.getChildren().add(new Label("---------->"));
				a.getChildren().add(new Label("等待时长"));
				hbox.getChildren().add(a);

				a = new VBox();
				a.setAlignment(Pos.CENTER);
				a.getChildren().add(new Label(
						sdf.format(((CityPathWeight) temp.getEdges().get(i).getWeight()).getStartTime().getTime())));
				a.getChildren().add(new Label(lastCity));
				hbox.getChildren().add(a);

				a = new VBox();
				a.setAlignment(Pos.CENTER);
				a.getChildren().add(new Label(
						temp.getEdges().get(i).getWeight().getValueByKey(CityPathWeight.DURATION).toString()));
				a.getChildren().add(new Label("---------->"));
				a.getChildren().add(new Label(temp.getEdges().get(i).getEdgeNodeName()));
				hbox.getChildren().add(a);
				
				a = new VBox();
				a.setAlignment(Pos.CENTER);
				a.getChildren().add(new Label(
						sdf.format(((CityPathWeight) temp.getEdges().get(i).getWeight()).getEndTime().getTime())));
				a.getChildren().add(new Label(temp.getCitys().get(i)));
				lastCity=temp.getCitys().get(i);
				hbox.getChildren().add(a);
			}
			
			a = new VBox();
			a.setAlignment(Pos.CENTER);
			if(temp.getTotalTime()< 28800000*3) {
				a.getChildren().add(new Label("总时长 :"+new SimpleDateFormat(strDate2).format(new Date(temp.getTotalTime()-28800000))));
			}else {
				a.getChildren().add(new Label("总时长 :"+sdf2.format(new Date(temp.getTotalTime()-28800000*4))));
			}
		
			a.getChildren().add(new Label("总费用 :"+temp.getTotalFee()));
			a.getChildren().add(new Label("中转数量 :"+temp.getTransitNumber()));
			hbox.getChildren().add(a);
			
			
			vbox.getChildren().add(hbox);
		}
		return vbox;
	}

}
