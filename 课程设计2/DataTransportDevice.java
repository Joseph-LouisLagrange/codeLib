package 课程设计2;
/** 
* @author 作者 Your-Name: 
* @version 创建时间：2019年11月21日 下午7:08:56 
* 类说明 数据传输组件，作为最外层部件使用
*/
public class DataTransportDevice {
			private DataAisle dataAisle=null;
			private GenericDataPacket dataPacket=null;
			/**
			 * 
			 * @param dataAisle 数据通道
			 * @param dataPacket 数据包编码格式
			 */
			public DataTransportDevice(DataAisle dataAisle, GenericDataPacket dataPacket) {
					this.dataAisle=dataAisle;
					this.dataPacket=dataPacket;
			}	
			/**
			 * 通过发送口发送数据包
			 * @param data 
			 * @throws Exception
			 */
			public void sender(String data) throws Exception {
				this.dataPacket.packing(data);
				this.dataAisle.exit(this.dataPacket);	
			}
			
			/**
			 * 通过接受口接受数据包
			 * @return
			 * @throws Exception
			 */
			public String receive() throws Exception {
				this.dataPacket=this.dataAisle.entrance();
				String data=this.dataPacket.unpacking();
				return data;
			}		
			
			public GenericDataPacket getDataPacket() {
				return this.dataPacket;
			}
}
