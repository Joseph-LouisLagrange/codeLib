package 课程设计6;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import genericClass.Weight;

/**
 * @author 作者 Your-Name:
 * @version 创建时间：2019年12月15日 下午10:11:56 类说明
 */
public class CityPathWeight extends Weight {
	public static final String STARTTIME = "starttime", ENDTIME = "endtime", DURATION = "duration", MONEY = "money";
	private Long duration = new Long(0); // 时长
	private GregorianCalendar startTime = null; // 出发时间
	private GregorianCalendar endTime = null; // 到达时间
	private Double money = new Double(0); // 费用
	private String priorityKey = "duration";
	
	/**
	 * @throws Exception
	 * 
	 */
	public CityPathWeight() throws Exception {
		this(new GregorianCalendar(), new GregorianCalendar(), new Double(1), CityPathWeight.DURATION);
	}

	/**
	 * @param startTime
	 * @param endTime
	 * @param money
	 * @param priorityKey
	 * @throws Exception
	 */
	public CityPathWeight(GregorianCalendar startTime, GregorianCalendar endTime, Double money, String priorityKey)
			throws Exception {
		super();
		this.setStartTime(startTime);
		//System.out.println(startTime.getTime());
		this.setEndTime(endTime);
		this.setDuration(this.endTime.getTimeInMillis() - this.startTime.getTimeInMillis());
		this.setMoney(money);
		this.priorityKey = priorityKey;
	}
	
	public String getPriorityKey() {
		return this.priorityKey;
	}

	/**
	 * 设置权重字段优先级
	 * 
	 * @param priorityKey
	 * @throws Exception
	 */
	public void setPriorityKey(String priorityKey) throws Exception {
		if (!priorityKey.equals(CityPathWeight.STARTTIME) && !priorityKey.equals(CityPathWeight.DURATION)
				&& !priorityKey.equals(CityPathWeight.ENDTIME) && !priorityKey.equals(CityPathWeight.MONEY))
			throw new Exception("路径的优先权重错误");
		this.priorityKey = priorityKey;
	}

	public Long getDuration() {
		return duration;
	}

	public void setDuration(Long duration) throws Exception {
		if (duration < 0)
			throw new Exception("路径时间为负数");
		this.duration = duration;
	}

	public GregorianCalendar getStartTime() {
		
		return startTime;
	}

	public void setStartTime(GregorianCalendar startTime) throws Exception {
//		if (startTime.getTimeInMillis()<=System.currentTimeMillis())
//			throw new Exception("出发时间错误");
		this.startTime = startTime;
		if(this.endTime!=null)
			this.setDuration(this.endTime.getTimeInMillis() - this.startTime.getTimeInMillis());
		
	}

	public GregorianCalendar getEndTime() {
		return endTime;
	}

	public void setEndTime(GregorianCalendar endTime) throws Exception {
		this.endTime = endTime;
		if(this.startTime!=null) 
			this.setDuration(this.endTime.getTimeInMillis() - this.startTime.getTimeInMillis());
		
	}

	public void setMoney(Double money) throws Exception {
		if (money <= 0)
			throw new Exception("路径花费为负数");
		this.money = money;
	}

	public double getMoney() {
		return money;
	}

	@Override
	public int compareTo(Weight o) {
		if (!this.getPriorityKey().equals(o.getPriorityKey())) {
			return Integer.MAX_VALUE;
		}
		if (o.getValue() instanceof Comparable && this.getValue() instanceof Comparable) {
			return ((Comparable<Object>) this.getValue()).compareTo(o.getValue());
		}
		return Integer.MIN_VALUE;
	}

	@Override
	public Object getValue() {
		if (this.getPriorityKey() == CityPathWeight.DURATION)
			return this.getDuration();
		else if (this.getPriorityKey() == CityPathWeight.MONEY)
			return this.getMoney();
		else if (this.getPriorityKey() == CityPathWeight.STARTTIME)
			return this.getStartTime();
		else if (this.getPriorityKey() == CityPathWeight.ENDTIME)
			return this.getEndTime();
		else
			return null;
	}

	@Override
	public Object getValueByKey(String key) {
		String strDateFormat = "HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);
		switch(key){
		case CityPathWeight.STARTTIME:return sdf.format(this.getStartTime().getTime()); 
		case CityPathWeight.ENDTIME:return sdf.format(this.getEndTime().getTime()); 
		case CityPathWeight.DURATION:return sdf.format(new Date(this.duration-28800000)); 
		case CityPathWeight.MONEY:return this.getMoney(); 
		default:return null;
		}
	}

	@Override
	public Weight add(Weight d) throws Exception {
		CityPathWeight temp=(CityPathWeight)d;
		CityPathWeight c=new CityPathWeight(this.startTime, this.endTime, this.money, CityPathWeight.DURATION);
		if (this.getPriorityKey() == CityPathWeight.DURATION) {
			c.setDuration(temp.getDuration()+this.getDuration());
			return c;
		}
		else if (this.getPriorityKey() == CityPathWeight.MONEY) {
			c.setMoney(this.money.doubleValue()+temp.getMoney());
			return c;
		}
		else
			return null;
	}	
}
