package 课程设计2;

import java.io.Serializable;

/** 
* @author 作者 Your-Name: 
* @version 创建时间：2019年11月21日 下午7:28:05 
* 类说明 : 标准数据传输包，一切自定义要求继承该类
*/
public abstract class GenericDataPacket implements Serializable {
			/**
	 * 
	 */
	private static final long serialVersionUID = -3682337237416625236L;

			/**
	 * 
	 */
			/**
			 * 解包函数用于打开数据包
			 * @return  数据
			 */
			public abstract String unpacking();
			/**
			 * 打包函数封装数据包
			 * @param data
			 */
			public abstract void packing(String data);
}
