package genericClass;



/** 
* @author 作者 Your-Name: 
* @version 创建时间：2019年11月21日 下午7:55:42 
* 类说明 任意长度的进制转换器
*/
public class DecimalTransformDevice {
			private int sourceDecimal=10;
			private	int destDecimal=10;
			public DecimalTransformDevice(String sourceDecimal,String destDecimal) throws Exception {
				this.setDestDecimal(destDecimal);
				this.setSourceDecimal(sourceDecimal);
			}
			
			/**
			 * 翻译进制码
			 * @param sourceCode
			 * @return 目标进制码
			 * @throws Exception
			 */
			public String getDecimalCode(String sourceCode) throws Exception {
				sourceCode=new StringBuffer(sourceCode).reverse().toString();
				StringBuffer destCode=new StringBuffer();
				String resultCode="";
				int cardinalNumber=1;
				for(int i=0;i<sourceCode.length();i++) {
					//清空destCode
					destCode.delete(0, destCode.length());
					//判断是否为数字
					if(!Character.isDigit(sourceCode.charAt(i)))
						throw new Exception();
					int value=Integer.parseInt(Character.toString(sourceCode.charAt(i)))*cardinalNumber;
					while(true) {
						if(value<this.destDecimal) {
							destCode.append(value);
							break;
						}
						destCode.append(value%this.destDecimal);
						value=value/this.destDecimal;
					}
					cardinalNumber=cardinalNumber*this.sourceDecimal;
					resultCode=this.addNumber(resultCode, destCode.reverse().toString());
				}
				return resultCode;
			}
			
			/**
			 * 计算两个十进制数字的和
			 * @param number1
			 * @param number2
			 * @return 两个数字的和
			 */
			private String addNumber(String number1,String number2) {
				number1=new StringBuffer(number1).reverse().toString();
				number2=new StringBuffer(number2).reverse().toString();
				StringBuffer result=new StringBuffer();
				int length=Math.min(number1.length(), number2.length());
				int value=0;    //表示进位
				int i=0;
				for(;i<length;i++) {
					result.append((value+Integer.parseInt(Character.toString(number2.charAt(i)))
							+Integer.parseInt(Character.toString(number1.charAt(i))))%this.destDecimal);
					value=(value+Integer.parseInt(Character.toString(number2.charAt(i)))
							+Integer.parseInt(Character.toString(number1.charAt(i))))/this.destDecimal;
				}
				for(;i<number1.length();i++) {
					result.append((value+Integer.parseInt(Character.toString(number1.charAt(i))))%this.destDecimal);
					value=(value+Integer.parseInt(Character.toString(number1.charAt(i))))/this.destDecimal;
				}
				for(;i<number2.length();i++) {
					result.append((value+Integer.parseInt(Character.toString(number2.charAt(i))))%this.destDecimal);
					value=(value+Integer.parseInt(Character.toString(number2.charAt(i))))/this.destDecimal;
				}
				if(value!=0)
					result.append(value);
				return   result.reverse().toString() ;
			}
			private void setSourceDecimal(String sourceDecimal) throws Exception {
				int temp=Integer.valueOf(sourceDecimal);
				if(temp<=0)
					throw new Exception();
				this.sourceDecimal = temp;
			}
			
			private void setDestDecimal(String destDecimal) throws Exception {
				int temp=Integer.valueOf(destDecimal);
				if(temp<=0)
					throw new Exception();
				this.destDecimal = temp;
			}
			
}
