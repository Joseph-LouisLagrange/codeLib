package genericClass;

/**
 * @author 作者 Your-Name:
 * @version 创建时间：2019年12月10日 下午3:20:50 类说明 栈
 */
public class Stack<T> {
	private Object[] stack = null;
	private int top;

	public Stack() {
	}

	public Stack(int maxSize) {
		top = -1;
		stack = new Object[maxSize];
	}

	public boolean isEmpty() {
		return top == -1;
	}

	public T getTop() throws Exception {
		if (!isEmpty()) {
			return (T) stack[top];
		} else
			throw new Exception("当前栈为空");
	}

	public void push(T o) throws Exception {
		if (top == stack.length)
			throw new Exception("当前栈为空");
		else
			stack[++top] = o;
	}

	public T pop() throws Exception {
		if (top == -1) {
			throw new Exception("当前栈为空");
		} else {
			return (T) stack[top--];
		}
	}

	public int length() {
		return top + 1;
	}

}
