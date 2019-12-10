package 课程设计1;

import java.util.zip.DataFormatException;

/**
 * @author 作者 Your-Name:
 * @version 创建时间：2019年11月6日 上午10:55:04 类说明
 */
public class Item implements Comparable<Item> {
	private char mark = '+'; // 符号
	private double coefficient = 1; // 系数
	private char variable; // 变量符号
	private int index = 0; // 指数
	// 一般项有系数，变量，指数

	public Item(char mark, double coefficient, char variable, int index) {
		this.setMark(mark);
		this.setCoefficient(coefficient);
		this.setVariable(variable);
		this.setIndex(index);
	}

	/**
	 * 常数项的特殊项式
	 * @param coefficient
	 */
	public Item(double coefficient) {
		this.setCoefficient(coefficient);
		this.setIndex(0);
	}

	public char getMark() {
		return mark;
	}

	public void setMark(char mark) {
		this.mark = mark;
	}

	public double getCoefficient() {
		return coefficient;
	}

	public void setCoefficient(double coefficient) {
		this.coefficient = coefficient;
	}

	public char getVariable() {
		return variable;
	}

	public void setVariable(char variable) {
		this.variable = variable;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	
	public int compareTo(Item o) {
		if (this.getIndex() > o.getIndex())
			return 1;
		else if (this.getIndex() < o.getIndex())
			return -1;
		else
			return 0;
	}

	/**
	 *  对两个同级项式的合并，不会影响源项式
	 * @param item1
	 * @param operator
	 * @param item2
	 * @return 合并后的项式
	 * @throws DataFormatException
	 */
	public static Item mergeItem(Item item1, char operator, Item item2) throws DataFormatException {
		if (item1.getIndex() != item2.getIndex())
			throw new DataFormatException();
		if(item1.getVariable()!=item2.getVariable())
			throw new DataFormatException();
		char mark = item2.getMark();
		if (operator == '-') {
			mark = (mark == '-' ? '+' : '-');
		} else if (operator != '+') {
			throw new DataFormatException("多项式暂时只支持加法与减法");
		}
		if(mark==item1.getMark()) {
			return new Item(mark,item1.getCoefficient()+item2.getCoefficient(),item1.getVariable(),item1.getIndex());
		}
		else if(item1.getCoefficient()>=item2.getCoefficient())
			return new Item(item1.getMark(),item1.getCoefficient()-item2.getCoefficient(),item1.getVariable(),item1.getIndex());
		else
			return new Item(mark,item2.getCoefficient()-item1.getCoefficient(),item1.getVariable(),item1.getIndex());
	}

}
