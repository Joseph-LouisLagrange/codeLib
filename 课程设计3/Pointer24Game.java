package 课程设计3;

import genericClass.LinkedList;

/**
 * @author 作者 Your-Name:
 * @version 创建时间：2019年12月3日 下午9:22:43 类说明 向上层返回表达式
 */
public class Pointer24Game {
	private LinkedList<String> resultString=new LinkedList<String>();
	 private double[] number = new double[4];
	 private String[] exp =new String[4];
	public Pointer24Game(int a,int b,int c,int d) {
		number[0]=a;
		exp[0]=String.valueOf(a);
		number[1]=b;
		exp[1]=String.valueOf(b);
		number[2]=c;
		exp[2]=String.valueOf(c);
		number[3]=d;
		exp[3]=String.valueOf(d);
	}
	public String[] getResultStrings() {
		this.game(4);
		String[] list=new String[this.resultString.getSize()];
		int j=0;
		for(Object i : this.resultString.toArray()) {
			list[j++]=(String) i;
		}
		return list;
	}

	public  void game(int n) {
		if (n == 1) {
			if (number[0] == 24) {
				resultString.add(exp[0]);
			}
		} else {
			for (int i = 0; i < n; i++) {
				for (int j = i + 1; j < n; j++) {
					double a = number[i];
					double b = number[j];
					number[j] = number[n - 1];
					String str1 = exp[i];
					String str2 = exp[j];
					exp[j] = exp[n - 1];
					//加法
					exp[i] = "(" + str1 + "+" + str2 + ")";
					number[i] = a + b;
					game(n - 1);
					//减法
					exp[i] = "(" + str1 + "-" + str2 + ")";
					number[i] =a - b;
					game(n - 1);
					//乘法
					exp[i] = "(" + str1 + "*" + str2 + ")";
					number[i] = a * b;
					game(n - 1);
					//除法
					if ( b != 0) {
						exp[i] = "(" + str1 + "/" + str2 + ")";
						number[i] =a / b;
						game(n - 1);
					}
					//减法
					exp[i] = "(" + str2 + "-" + str1 + ")";
					number[i] = b - a;
					game(n - 1);
					//除法
					if (a != 0) {
						exp[i] = "(" + str2 + "/" + str1 + ")";
						number[i] = b / a;
						game(n - 1);
					}
					//状态还原
					number[i] = a;
					number[j] = b;
					exp[i] = str1;
					exp[j] = str2;
				}
			}
		}
	}
}
