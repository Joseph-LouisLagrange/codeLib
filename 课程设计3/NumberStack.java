package 课程设计3;

import genericClass.LinkedList;
import genericClass.Stack;

/**
 * @author 作者 Your-Name:
 * @version 创建时间：2019年12月12日 下午4:01:19 类说明
 */
public class NumberStack extends Stack<String> {
	private LinkedList<String> pushSequence=null;
	private int count = 0;
	{
		this.pushSequence=new LinkedList<String>();
	}
	public NumberStack() {
		super();
		
	}

	public NumberStack(int maxSize) {

		super(maxSize);
	
	}

	private boolean isOperator(char c) {
		return c == '+' || c == '-' || c == '*' || c == '/';
	}
	
	public String[] getPushSequence() throws Exception {
		String [] list=new String[this.pushSequence.toArray().length];
		int j=0;
		for(Object i :  this.pushSequence.reverse().toArray()) {
			list[j++]=(String) i;
		}
		return list;
	}

	public void push(String o) throws Exception {
		this.pushSequence.add(o);
		if (o.length() == 1 && this.isOperator(o.charAt(0))) {
			double number1 = Double.parseDouble(super.pop());
			double number2 = Double.parseDouble(super.pop());
			double result = 0;
			switch (o.charAt(0)) {
			case '+':
				result = number1 + number2;
				break;
			case '-':
				result = number1 - number2;
				break;
			case '*':
				result = number1 * number2;
				break;
			case '/':
				result = number1 / number2;
				break;
			}
			super.push(String.valueOf(result));
		} else
			super.push(o);
	}

	public void push(Character o) throws Exception {
		this.push(String.valueOf(o));
	}
}
