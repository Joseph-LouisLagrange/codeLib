package 课程设计3;

import java.util.HashMap;

import genericClass.Stack;

/**
 * @author 作者 Your-Name:
 * @version 创建时间：2019年12月12日 下午9:24:32 类说明
 */
public class OperatorStack extends Stack<Character> {
	private static final HashMap<Character, Integer> PRIORITYMAP = new HashMap<Character, Integer>();
	// 使用静态块来加载算符优先级表
	static {
		PRIORITYMAP.put('(', 0);
		PRIORITYMAP.put('+', 1);
		PRIORITYMAP.put('-', 1);
		PRIORITYMAP.put('*', 2);
		PRIORITYMAP.put('/', 2);
		PRIORITYMAP.put(')', 3);
	}

	/**
	 * 
	 */
	public OperatorStack() {
		super();
	}

	/**
	 * @param maxSize
	 */
	public OperatorStack(int maxSize) {
		super(maxSize);

	}

	/**
	 * 对上层发出弹出信号
	 * 
	 * @return 是否需要弹出的信号
	 * @throws Exception
	 */
	public boolean isPop(Character o) throws Exception {
		//System.out.println("准备入" + o);
		if (o.equals('(')) {
			return true;
		}
		if (!this.isEmpty() && !this.getTop().equals(')')
				&& PRIORITYMAP.get(o).compareTo(PRIORITYMAP.get(this.getTop())) <= 0) {
			return true;
		}
		return false;
	}

}
