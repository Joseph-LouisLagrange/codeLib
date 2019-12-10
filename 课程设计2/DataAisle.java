package 课程设计2;
/** 
* @author 作者 Your-Name: 
* @version 创建时间：2019年11月21日 下午7:05:32 
* 类说明 数据的通道接口
*/
public interface DataAisle {
	/**
	 * 数据出口
	 * @param data
	 * @throws Exception
	 */
		public void exit(GenericDataPacket data) throws Exception;
		/**
		 * 数据入口
		 * @return
		 * @throws Exception
		 */
		public GenericDataPacket entrance() throws Exception;
}
