package 课程设计6;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import genericClass.EdgeListNode;
import genericClass.LinkedList;

/**
 * @author 作者 Your-Name:
 * @version 创建时间：2019年12月20日 下午6:50:12 类说明
 */
public class CityPath extends Path<String, String> {
	String strDateFormat = "HH:mm:ss";
	SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);
	private LinkedList<String> citys = null;
	private LinkedList<EdgeListNode<String>> edges = null;
	private String startCity = null;
	private LinkedList<String> waitTimes = null;
	private double totalFee = 0;
	public CityPath(String startCity) {
		this.startCity = startCity;
		this.citys = new LinkedList<String>();
		this.edges = new LinkedList<EdgeListNode<String>>();
		waitTimes = new LinkedList<String>();
	}

	public CityPath() {

	}

	public LinkedList<String> getWaitTimes() {
		return waitTimes;
	}

	public void setWaitTimes(LinkedList<String> waitTimes) {
		this.waitTimes = waitTimes;
	}

	public LinkedList<String> getCitys() {
		return citys;
	}

	public void setCitys(LinkedList<String> citys) {
		this.citys = citys;
	}

	public LinkedList<EdgeListNode<String>> getEdges() {
		return edges;
	}

	public void setEdges(LinkedList<EdgeListNode<String>> edges) {
		this.edges = edges;
	}

	public String getStartCity() {
		return startCity;
	}

	public void setStartCity(String startCity) {
		this.startCity = startCity;
	}

	/**
	 * 添加下一个路径与城市
	 * 
	 * @param e
	 * @param nextCity
	 * @throws Exception
	 */
	public void addPath(EdgeListNode<String> e, String nextCity) throws Exception {
		Calendar nextStartTime=(Calendar) ((CityPathWeight) e.getWeight()).getStartTime().clone();
		Calendar nextEndTime= (Calendar) ((CityPathWeight) e.getWeight()).getEndTime().clone();
		Calendar lastEndTime=null;
		int durationDay = nextEndTime.get(Calendar.DAY_OF_MONTH) - nextStartTime.get(Calendar.DAY_OF_MONTH);
		if (!this.edges.isEmpty()) {
			EdgeListNode<String> lastEdge = this.edges.get(this.edges.getSize() - 1);
			lastEndTime = (Calendar) ((CityPathWeight) lastEdge.getWeight()).getEndTime().clone();
//			nextEndTime = (Calendar) ((CityPathWeight) e.getWeight()).getEndTime().clone();
//			nextStartTime = (Calendar) ((CityPathWeight) e.getWeight()).getStartTime().clone();
			// 当天的车次已开走的情况，需要等到明天
			if (lastEndTime.get(Calendar.HOUR_OF_DAY) > nextStartTime.get(Calendar.HOUR_OF_DAY)
					|| (lastEndTime.get(Calendar.HOUR_OF_DAY) == nextStartTime.get(Calendar.HOUR_OF_DAY)
							&& lastEndTime.get(Calendar.MINUTE) > nextStartTime.get(Calendar.MINUTE))) {
				// 路途相差的天数
				nextStartTime.set(Calendar.DAY_OF_MONTH, lastEndTime.get(Calendar.DAY_OF_MONTH) + 1);
				nextEndTime.set(Calendar.DAY_OF_MONTH, nextStartTime.get(Calendar.DAY_OF_MONTH) + durationDay);
			}
			// 列车未开走,当天就可以走
			else {
				nextStartTime.set(Calendar.DAY_OF_MONTH, lastEndTime.get(Calendar.DAY_OF_MONTH));
				nextEndTime.set(Calendar.DAY_OF_MONTH, nextStartTime.get(Calendar.DAY_OF_MONTH) + durationDay);
			}
			// 计算等待时间
			this.waitTimes.add(
					sdf.format(new Date(nextStartTime.getTimeInMillis() - lastEndTime.getTimeInMillis() - 28800000)));
		} else {
			if (System.currentTimeMillis() < ((CityPathWeight) e.getWeight()).getStartTime().getTimeInMillis()) {
				this.waitTimes.add(sdf.format(new Date(((CityPathWeight) e.getWeight()).getStartTime().getTimeInMillis()
						- System.currentTimeMillis() - 28800000)));
			} else if (System.currentTimeMillis() > ((CityPathWeight) e.getWeight()).getStartTime().getTimeInMillis()) {
				this.waitTimes.add(sdf.format(new Date(((CityPathWeight) e.getWeight()).getStartTime().getTimeInMillis()
						- System.currentTimeMillis()+28800000*2)));
				System.out.println("已开走");
				nextStartTime.set(Calendar.DAY_OF_MONTH, nextStartTime.get(Calendar.DAY_OF_MONTH) + 1);
				nextEndTime.set(Calendar.DAY_OF_MONTH, nextStartTime.get(Calendar.DAY_OF_MONTH) + durationDay);
			} else {
				this.waitTimes.add(sdf.format(new Date(28800000 * 4)));
			}
		}
		this.citys.add(nextCity);
		this.edges.add(new EdgeListNode<String>(e.getVertexIndex(),e.getEdgeNodeName(),
				new CityPathWeight((GregorianCalendar)nextStartTime,(GregorianCalendar)nextEndTime,((CityPathWeight)e.getWeight()).getMoney(),"money")));
		this.totalFee += ((CityPathWeight) e.getWeight()).getMoney();// 收集费用
	}

	@Override
	public Path<String, String> getNewPath(String startVertex) {
		return new CityPath(startVertex);
	}

	/**
	 * 总时长
	 * 
	 * @return
	 * @throws Exception
	 */
	public long getTotalTime() throws Exception {
		CityPathWeight arrivals = (CityPathWeight) (this.getEdges().get(this.getEdges().getSize() - 1).getWeight());
		return arrivals.getEndTime().getTimeInMillis() - System.currentTimeMillis();
	}

	/**
	 * 总费用
	 * 
	 * @return
	 */
	public double getTotalFee() {
		return this.totalFee;
	}

	/**
	 * 中转数量
	 * 
	 * @return
	 */
	public int getTransitNumber() {
		return this.citys.getSize() - 1;
	}
}
