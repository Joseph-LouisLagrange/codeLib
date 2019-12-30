package 课程设计5;
/** 
* @author 作者 Your-Name: 
* @version 创建时间：2019年12月21日 下午5:43:02 
* 类说明 
*/

import java.util.ArrayList;
import java.util.Arrays;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DataUse {
	private int[] correspondingQuantity = null;
	private int charIndex = -1;
	private ObservableList<ContactPerson>[] directionChart = null;

	{
		this.correspondingQuantity = new int[27]; // 记录每个首字母的已有名字数量
		Arrays.fill(correspondingQuantity, 0);
		this.directionChart = new ObservableList[27]; // 初始化索引数组:a,b,c->d,# （#表示数字）
		for (int i = 0; i < 27; i++) {
			this.directionChart[i] = FXCollections.observableList(new ArrayList<ContactPerson>());
		}
	}

	public void add(ContactPerson contactPerson) {
		char[] headChar = PinYin4jUtils.getHeadByString(contactPerson.getName()); // 汉字的首字母序列[a,b,c]
		ObservableList<ContactPerson> list = this.getIndexInDirectionChart(headChar[0]);
		String letterSequence = Arrays.toString(headChar);
		int insertIndex = 0;
		for (ContactPerson c : list) {
			if (letterSequence.compareTo(Arrays.toString(PinYin4jUtils.getHeadByString(c.getName()))) >= 0) { // 使用字典为比较标准来排序
				break;
			}
			insertIndex++;
		}
		this.charIndex = this.getIndexByHeadChar(headChar[0]);
		list.add(insertIndex, contactPerson); // 插入该排序的位置
	}

	/**
	 * 移除对应的联系人
	 * 
	 * @param contactPerson
	 * @throws Exception
	 */
	public boolean remove(ContactPerson contactPerson) throws Exception {
		char[] headChar = PinYin4jUtils.getHeadByString(contactPerson.getName());
		this.charIndex = this.getIndexByHeadChar(headChar[0]);
		ObservableList<ContactPerson> list = this.getIndexInDirectionChart(headChar[0]);
		return list.remove(contactPerson);
	}

	/**
	 * 快速得到该首字母的存储链位置 如 a->0,b->1,,,#->26
	 * 
	 * @param headChar
	 * @return 该首字母的存储链
	 */
	private ObservableList<ContactPerson> getIndexInDirectionChart(char headChar) {
		return this.directionChart[this.getIndexByHeadChar(headChar)];
	}

	/**
	 * 获得指定首字母开头的存储链索引
	 * 
	 * @param headChar
	 * @return
	 */
	private int getIndexByHeadChar(char headChar) {
		if ('a' <= headChar && 'z' >= headChar)
			return headChar - 'a';
		else if ('A' <= headChar && 'Z' >= headChar)
			return headChar - 'A';
		else
			return 26;
	}

	public ObservableList<ContactPerson> queryByTele(String tele) {
		ArrayList<ContactPerson> result = new ArrayList<ContactPerson>();
		for (int index = 0; index < 27; index++) {
			for (ContactPerson c : this.directionChart[index]) {
				for (String s : c.getTelephone()) {
					if (s.contains(tele)) {
						result.add(c);
					}
				}
			}
		}
		return FXCollections.observableList(result);
	}

	
	public ObservableList<ContactPerson> queryByName(String name) {
		ArrayList<ContactPerson> result = new ArrayList<ContactPerson>();
		for (int index = 0; index < 27; index++) {
			for (ContactPerson c : this.directionChart[index]) {
				if (c.getName().contains(name)) {
					result.add(c);
				}
			}
		}
		return FXCollections.observableList(result);
	}

	public ObservableList<ContactPerson> query(String keyText){
			ObservableList<ContactPerson> c1=this.queryByName(keyText);
			ObservableList<ContactPerson> c2=this.queryByTele(keyText);
			ObservableList<ContactPerson> c3=this.queryByMail(keyText);
			c2.removeAll(c1);
			c2.removeAll(c3);
			c2.addAll(c1);
			c2.addAll(c3);
			return c2;
	}
	
	public ObservableList<ContactPerson> queryByMail(String mail) {
		ArrayList<ContactPerson> result = new ArrayList<ContactPerson>();
		for (int index = 0; index < 27; index++) {
			for (ContactPerson c : this.directionChart[index]) {
				if (c.getMailbox().contains(mail)) {
					result.add(c);
				}
			}
		}
		return FXCollections.observableList(result);
	}

	public ObservableList<ContactPerson>[] getDirectionChart() {
		return directionChart;
	}

	public int getCharIndex() {
		return charIndex;
	}

	public int[] getCorrespondingQuantity() {
		for (int i = 0; i < this.correspondingQuantity.length; i++) {
			this.correspondingQuantity[i] = this.directionChart[i].size();
		}
		return correspondingQuantity;
	}

	/**
	 * 一次性删除大量元素
	 * 
	 * @param result
	 * @throws Exception
	 */
	public void removeAll(ObservableList<ContactPerson> result) throws Exception {
		ContactPerson[] temp = result.toArray(new ContactPerson[0]);
		for (int i = 0; i < temp.length; i++) {
			this.remove(temp[i]);
		}
	}

}
