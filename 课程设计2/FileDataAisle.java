package 课程设计2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/** 
* @author 作者 Your-Name: 
* @version 创建时间：2019年11月24日 下午4:17:49 
* 类说明 
*/
public class FileDataAisle implements DataAisle{

	@Override
	public void exit(GenericDataPacket data) throws Exception {
		File file=new File("F:\\Program Files\\Eclipse\\数据结构与算法\\src\\课程设计2\\exit.dat");
		FileOutputStream out=null;
		ObjectOutputStream output=null;
		try {
			 out=new FileOutputStream(file);
			 output=new ObjectOutputStream(out);
			 output.writeObject(data);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}catch( IOException e2) {
			e2.printStackTrace();
		}finally {
				output.close();
				out.close();
		}
	}

	@Override
	public GenericDataPacket entrance() throws Exception {
		File file=new File("F:\\Program Files\\Eclipse\\数据结构与算法\\src\\课程设计2\\exit.dat");
		GenericDataPacket dataPacket=null;
		FileInputStream in=null;
		ObjectInputStream input=null;
		try {
			 in=new FileInputStream(file);
			 input=new ObjectInputStream(in);
			 dataPacket=(GenericDataPacket) input.readObject();
		} catch (FileNotFoundException e1) {
			
		}catch( IOException e2) {
			
		} catch (ClassNotFoundException e3) {

		}finally {
			input.close();
			in.close();
		}
		return dataPacket;
	}
}
