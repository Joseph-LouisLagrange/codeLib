package 课程设计3;

import genericClass.BinaryTreeNode;
import genericClass.LinkedList;
import genericClass.Stack;

/**
 * @author 作者 Your-Name:
 * @version 创建时间：2019年12月12日 下午3:14:25 类说明 中序表达式转换为程序可直接吸收的波兰表达式及二叉树
 */
public class PolishExpression {
	private BinaryTreeNode<String> root = null; // 二叉树根
	private String polishMathExpression[] = null; // 波兰表达式字符串序列
	private double result = 0; // 计算结果
	private String srcMathExpression = null; // 源字符表达式
	private NumberStack stack1 = null; // 数字栈空间
	private OperatorStack stack2 = null; // 运算栈空间
	private LinkedList<Double> numbers = null; // 表达式的各个数字的缓存空间

	public PolishExpression(String mathExpression) throws Exception {
		this.srcMathExpression = " " + mathExpression; // 在前面加上一个空格字符方便历遍算法
		this.root = new BinaryTreeNode<String>();
		this.numbers = new LinkedList<Double>();
		this.compute(); // 计算相关值
	}

	/*
	 * 
	 */
	public BinaryTreeNode<String> getExpressionTreeRoot(){
		this.getExpressionTree(root, 0);
		return this.root;
	}
	
	/**
	 * 递归先序历遍序列生成树
	 * @param root
	 * @param index
	 */
	private void getExpressionTree(BinaryTreeNode<String> root, int index) {
		if (index < this.polishMathExpression.length) {
			root.setData(this.polishMathExpression[index]);
			if (this.polishMathExpression[index].length() == 1
					&& this.isLegalOperator(this.polishMathExpression[index].charAt(0))) {
				root.setLeftChild(new BinaryTreeNode<String>());
				root.setRightChild(new BinaryTreeNode<String>());
				getExpressionTree(root.getLeftChild(), index + 1);
				getExpressionTree(root.getRightChild(), index + 1);
			}
		}
	}

	public String[] getPolishMathExpression() {
		return this.polishMathExpression;
	}

	/**
	 * 判断是否为合法运算符
	 * 
	 * @param c
	 * @return 合法性
	 */
	private boolean isLegalOperator(char c) {
		return c == '+' || c == '-' || c == '*' || c == '/' || c == ')' || c == '(';
	}

	/**
	 * 计算产生相关波兰表达式与结果
	 * 
	 * @throws Exception
	 */
	private void compute() throws Exception {
		this.stack1 = new NumberStack(20);// 符号栈
		this.stack2 = new OperatorStack(20);// 运算符栈
		for (int i = this.srcMathExpression.length() - 1; i > 0; i--) {
			int j = i - 1;
			if (this.isLegalOperator(this.srcMathExpression.charAt(i))) {
				this.operateStack(this.srcMathExpression.charAt(i));
				continue;
			}
			for (; j >= 0; j--) {
				if (this.isLegalOperator(this.srcMathExpression.charAt(j)))
					break;
			}
			try {
				// 数字直接压入数字栈
				if (!stack1.isFull()) {
					double temp = Double.parseDouble(this.srcMathExpression.substring(j + 1, i + 1));
					this.numbers.add(temp);
					stack1.push(String.valueOf(temp));
				}
				// 符号压入符号栈
				if (j > 0) {
					operateStack(this.srcMathExpression.charAt(j));// 传入运算符
				}
			} catch (NumberFormatException e) {
				throw new NumberFormatException("错误发生在" + j + "附近");
			}
			i = j;
		}
		while (!stack2.isEmpty()) {
			stack1.push(stack2.pop());
		}
		this.result = Double.parseDouble(stack1.pop());
		this.polishMathExpression = stack1.getPushSequence();
	}

	private void operateStack(char operator) throws Exception {
		// System.out.println("获取:"+operator);
		if (!stack2.isFull()) {
			// 如果优先级小于栈顶运算符
			while (stack2.isPop(operator)) {
				// 出栈
				char temp = stack2.pop();
				// System.out.println("弹出:" + temp);
				if (temp == ')')
					break; // 直到遇到另外半个括号就抵消
				stack1.push(temp); // 把算符转入数字栈进行运算
			}
			if (operator != '(')
				stack2.push(operator);
		}
	}

	/*
	 * 返回计算结果
	 */
	public double getResult() throws Exception {
		return this.result;
	}

	/**
	 * 返回表达式中的各个数字
	 * 
	 * @return
	 * @throws Exception
	 */
	public Double[] getNumbers() throws Exception {
		Double[] temp = new Double[this.numbers.getSize()];
		int j = 0;
		for (Object i : this.numbers) {
			temp[j++] = (Double) i;
		}
		return temp;
	}
}
