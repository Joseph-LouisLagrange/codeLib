package genericClass;
/** 
* @author 作者 Your-Name: 
* @version 创建时间：2019年12月15日 下午10:29:00 
* 类说明 
 * 
*/
public abstract class Weight implements Comparable<Weight> {
			abstract public Object getValue();
			abstract public Object getValueByKey(String key);
			abstract public String getPriorityKey();
			abstract public int compareTo(Weight o);
			abstract public Weight add(Weight d) throws Exception;
}
