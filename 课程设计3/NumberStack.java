package 课程设计3;

import genericClass.Stack;

/**
 * @author 作者 Your-Name:
 * @version 创建时间：2019年12月12日 下午4:01:19 类说明
 */
public class NumberStack extends Stack<String> {
	public NumberStack() {
		super();
	}

	public NumberStack(int maxSize) {
		super(maxSize);
	}
	
	private boolean isOperator(char c) {
		return c == '+' || c == '-' || c == '*' || c == '/';
	}
	
	public void push(String o) throws Exception {
		//System.out.println("数字栈入:"+o);
		//o为算符
		if(o.length()==1&&this.isOperator(o.charAt(0))) {
			double number1=Double.parseDouble(super.pop());
			double number2=Double.parseDouble(super.pop());
			double result=0;
			switch(o.charAt(0)) {
			case '+':result=number1+number2;break;
			case '-':result=number1-number2;break;
			case '*':result=number1*number2;break;
			case '/':result=number1/number2;break;
			}
			super.push(String.valueOf(result));
			//System.out.println("result:"+result);
		}
		else
			super.push(o);
	}
	public void push(Character o) throws Exception {
		this.push(String.valueOf(o));
	}
}
