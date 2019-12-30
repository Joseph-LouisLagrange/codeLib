package 课程设计1;

import java.util.Scanner;
import java.util.zip.DataFormatException;

import genericClass.LinkedList;

/**
 * @author 作者 Your-Name:
 * @version 创建时间：2019年11月6日 上午10:48:40 类说明:基于链表数据结构的多项式对象
 */
public class Polynomial {
	private int itemsNumber = 0;
	private String sourcePolynomialString = null;
	private LinkedList<Item> polynomialList = null;

	/**
	 * 
	 * @param polynomialString
	 * @throws Exception 通过传入的字符串生成多项式
	 */
	public Polynomial(String polynomialString) throws Exception {
		polynomialList = new LinkedList<Item>();
		this.setSourcePolynomialString(polynomialString);
		this.makePolynomialList();

	}

	/**
	 * 默认的构造函数，默认的源字符串为"0"
	 * 
	 * @throws Exception
	 */
	public Polynomial() throws Exception {
		this("0");
	}
	
	
	private void setSourcePolynomialString(String polynomialString) {
		if (polynomialString == null)
			throw new NullPointerException();
		if(polynomialString.equals("")) 
			polynomialString="0";
		this.sourcePolynomialString = polynomialString.replaceAll(" ", "");
	}

	/**
	 * 
	 * @return 排序后的多项式字符串格式
	 * @throws Exception
	 */
	public String toSortedPolynomialString() throws Exception {
		return this.getsortedPolynomialByIndex().toPolynomialString();
	}

	/**
	 * 
	 * @return 多项式对象的直接字符串格式
	 * @throws Exception
	 */
	public String toPolynomialString() throws Exception {
		String sortedPolynomial = "";
		for (int i = 0; i < this.polynomialList.getSize(); i++) {
			String indexTag = "^";
			String index = Integer.toString(this.polynomialList.get(i).getIndex());
			String mark = Character.toString(this.polynomialList.get(i).getMark());
			String coefficient = Double.toString(this.polynomialList.get(i).getCoefficient());
			String variable = Character.toString(this.polynomialList.get(i).getVariable());
			if (Double.parseDouble(coefficient) == 0)
				continue;
			else {
				if (index.equals("1")) {
					index = "";
					indexTag = "";
				} else if (index.equals("0")) {
					index = "";
					variable = "";
					indexTag = "";
				}
			}
			sortedPolynomial = sortedPolynomial + mark + coefficient + variable + indexTag + index;
		}
		if(sortedPolynomial.length()==0)
			return "0";
		if (sortedPolynomial.charAt(0) == '+')
			sortedPolynomial = sortedPolynomial.substring(1);
		return sortedPolynomial;
	}

	/**
	 * 
	 * @return 根据index指数排序的升序多项式对象
	 * @throws Exception
	 */
	private Polynomial getsortedPolynomialByIndex() throws Exception {
		Polynomial result=new Polynomial();
		result.polynomialList=new LinkedList<Item>();
		LinkedList<Item> items = this.polynomialList.sort();
		for(int i=0;i<items.getSize();) {
			Item item=items.get(i);
			int j=i+1;
			for(;j<items.getSize();j++) {
				if(items.get(i).compareTo(items.get(j))!=0) {
					break;
				}
				item=Item.mergeItem(item, '+', items.get(j));
			}
			i=j;
			result.polynomialList.add(item);
		}
		return result;
	}

	
	
	/**
	 * 
	 * @return 反转的多项式对象
	 * @throws Exception
	 */
	public Polynomial getReversedPolynomial() throws Exception {
		LinkedList<Item> polynomialList = this.polynomialList.reverse();
		Polynomial reversePolynomial = new Polynomial(this.sourcePolynomialString);
		reversePolynomial.polynomialList = polynomialList;
		return reversePolynomial;
	}

	/**
	 * 生成链式多项式
	 * 
	 * @throws Exception
	 */
	private void makePolynomialList() throws Exception {
		String integralPolynomialString = this.sourcePolynomialString;
		// 为源字符串添加符号头 如：5x => +5x
		if (this.sourcePolynomialString.charAt(0) != '-' && this.sourcePolynomialString.charAt(0) != '+')
			integralPolynomialString = "+" + this.sourcePolynomialString;
		int i = 0, j = 0;
		while (i < integralPolynomialString.length()) {
			j = i + 1;
			String temp = null;
			while (j < integralPolynomialString.length()) {
				if ((integralPolynomialString.charAt(j) == '+' || integralPolynomialString.charAt(j) == '-')
						&&integralPolynomialString.charAt(j-1)!='^')
					break;
				j++;
			}
			temp = integralPolynomialString.substring(i, j);
			if (temp.length() >= 2) {
				char mark = temp.charAt(0);
				double coefficient = 1;
				char variable = 'x';
				int index = 0;
				int k = 1;

				// 计算系数
				while (true) {
					if (k >= temp.length() || Character.isLetter(temp.charAt(k))) {
						if (k == 1) {
							coefficient = 1;
						} else {
							try {
								coefficient = Double.parseDouble(temp.substring(1, k));
							} catch (NumberFormatException e) {
								throw new DataFormatException("错误位置为:" + (i + k) + "附近");
							}
						}
						break;
					}
					k++;
				}

				if (k < temp.length()) {
					variable = temp.charAt(k); // 提取变量符号
					k++;
					if (k == temp.length()) {
						index = 1;
					} else if (temp.charAt(k) == '^') {
						k++;
						try {
							index = Integer.parseInt(temp.substring(k));
						} catch (NumberFormatException e) {
							throw new DataFormatException("错误位置为:" + (i + k) + "附近");
						}
					} else
						throw new DataFormatException("错误位置为:" + (i + k) + "附近");
				} else { // 输入为常数项
					variable = 'x';
					index = 0;
				}
				polynomialList.insert(polynomialList.getSize(), new Item(mark, coefficient, variable, index));
			} else
				throw new DataFormatException("错误位置为:" + j + "附近");
			i = j;
		}
	}

	/**
	 * 对已排序的两个多项式链进行合并
	 * 
	 * @param polynomial1
	 * @param operator
	 * @param polynomial2
	 * @return 合并后的多项式对象
	 * @throws Exception
	 */
	public static Polynomial mergePolynomial(Polynomial polynomial1, char operator, Polynomial polynomial2)
			throws Exception {
		LinkedList<Item> polynomialList1 = polynomial1.getsortedPolynomialByIndex().polynomialList;
		LinkedList<Item> polynomialList2 = polynomial2.getsortedPolynomialByIndex().polynomialList;
		LinkedList<Item> newPolynomialList = new LinkedList<Item>();
		Polynomial newPolymial = new Polynomial();
		if (polynomial1 == null || polynomial2 == null)
			throw new NullPointerException();
		if (operator != '-' && operator != '+')
			throw new DataFormatException("多项式暂时只支持加法与减法");
		int i = 0, j = 0, k = 0;
		while (i < polynomialList1.getSize() && j < polynomialList2.getSize()) {
			if (polynomialList1.get(i).compareTo(polynomialList2.get(j)) < 0) {
				newPolynomialList.insert(k, polynomialList1.get(i));
				i++;
			} else if (polynomialList1.get(i).compareTo(polynomialList2.get(j)) > 0) {
				newPolynomialList.insert(k, polynomialList2.get(j));
				j++;
			} else {
				if (polynomialList1.get(i).getVariable() == polynomialList2.get(j).getVariable()) {
					Item newItem = Item.mergeItem(polynomialList1.get(i), operator, polynomialList2.get(j));
					newPolynomialList.insert(k, newItem);
				} else {
					newPolynomialList.insert(k, polynomialList1.get(i));
					k++;
					newPolynomialList.insert(k, polynomialList2.get(j));
				}
				i++;
				j++;
			}
			k++;
		}
		while (i < polynomialList1.getSize()) {
			newPolynomialList.insert(k, polynomialList1.get(i));
			k++;
			i++;
		}
		while (j < polynomialList2.getSize()) {
			newPolynomialList.insert(k, polynomialList2.get(j));
			k++;
			j++;
		}
		newPolymial.polynomialList = newPolynomialList;
		return newPolymial;
	}

	/**
	 * 题目要求的输出格式
	 * 
	 * @return 整数序列字符串
	 * @throws Exception
	 */
	public String toRequiredString() throws Exception {
		this.itemsNumber=0;
		Polynomial p = this.getsortedPolynomialByIndex().getReversedPolynomial();
		String polynomialString = "";
		for (int i = 0; i < p.polynomialList.getSize(); i++) {
			double coefficient = p.polynomialList.get(i).getCoefficient();
			if(coefficient==0)
				continue;
			int index = p.polynomialList.get(i).getIndex();
			if (p.polynomialList.get(i).getMark() == '-')
				coefficient *= (-1);
			polynomialString = polynomialString + "," + coefficient + (i + 1) + "," + index + (i + 1);
			this.itemsNumber++;
		}
		return this.itemsNumber + "," + polynomialString.substring(1);
	}

	/**
	 * 
	 * @return 多项式的求导表达式
	 * @throws Exception
	 */
	public Polynomial getDerivatePolynomial() throws Exception {
		Polynomial p = new Polynomial(this.toPolynomialString());
		for (int i = 0; i < p.polynomialList.getSize(); i++) {
			if (p.polynomialList.get(i).getIndex() < 0) {
				char mark = (p.polynomialList.get(i).getMark() == '-' ? '+' : '-');
				p.polynomialList.get(i).setMark(mark);
			} 
			p.polynomialList.get(i).setCoefficient(p.polynomialList.get(i).getCoefficient()*Math.abs(p.polynomialList.get(i).getIndex()));
			p.polynomialList.get(i).setIndex(p.polynomialList.get(i).getIndex()-1);
		}
		return p;
	}
	
	public static void switchOn(){
		String exit="exit";
		Scanner input=new Scanner(System.in);
		System.out.println();
		while(true) {
			System.out.print("Polynomial computer>");
			String polynomialString=input.nextLine();
			if(polynomialString.equals("")) {
				continue;
			}
			String[] OPF=polynomialString.trim().split(" ");
			try {
				Polynomial p=new Polynomial(OPF[0]);
				if(OPF[1].equals("-o")) {
					System.out.println(p.toSortedPolynomialString());
				}else if(OPF[1].equals("+")||OPF[1].equals("-")) {
					System.out.println(Polynomial.mergePolynomial(p, OPF[1].charAt(0),new Polynomial(OPF[2])).toSortedPolynomialString());
				}else if(OPF[1].equals("-d")) {
					System.out.println(p.getDerivatePolynomial().toSortedPolynomialString());
				}else {
					System.out.println(p.toPolynomialString());
				}
			} catch (Exception e) {
				System.out.println("error : "+e.getMessage());
			}
		}
	}
}
