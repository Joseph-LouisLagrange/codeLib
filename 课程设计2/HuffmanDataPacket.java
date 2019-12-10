package 课程设计2;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import genericClass.BinaryTree;
import genericClass.BinaryTreeNode;
import genericClass.DecimalTransformDevice;
import genericClass.SortClass;

/**
 * @author 作者 Your-Name:
 * @version 创建时间：2019年11月21日 下午7:32:56 类说明
 */
public class HuffmanDataPacket extends GenericDataPacket {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1425922374882296791L;
	/**
	 * 
	 */

	// 只序列化码表和哈夫曼编码,还有后缀码的长度
	private transient String sourceData = null;   //源数据
	private transient LinkedHashMap<Character, String> codeTable =null;      //编码表
	private transient BinaryTree<String> huffmanTree=null;				//哈夫曼树
	private transient String StringBitCode=null;
	private Byte[] dataCode = null;							//已压缩的编码
	private int dataCodeSuffix = 0;						//编码后缀长度
	private LinkedHashMap<String, Character> decodeTable = null;    				//解码表

	/**
	 * 构造函数实例化数据域
	 * @throws Exception
	 */
	public HuffmanDataPacket() throws Exception {
	
	}
	
	/**
	 * 题目要求建议删去
	 * @param dataCode
	 */
	public void setDataCode(Byte[] dataCode) {
		this.dataCode = dataCode;
	}

	/**
	 * 题目要求,建议删去
	 * @return
	 */
	public void setDataCodeSuffix(int dataCodeSuffix) {
		this.dataCodeSuffix = dataCodeSuffix;
	}

	
	/**
	 * 题目要求,建议删去
	 * @return
	 */
	public String getStringBitCode() {
		return this.StringBitCode;
	}

	/**
	 * 题目要求,建议删去
	 * @return
	 */
	public BinaryTree<String> getHuffmanTree() {
		return huffmanTree;
	}



	/**
	 * 以频次来进行排序
	 * @param <K>
	 * @param <V>
	 * @param map
	 * @return sortedMap
	 */
	private static <K, V> LinkedHashMap<K, V> sortByValue(LinkedHashMap<K, V> map) {
		LinkedHashMap<Object, Object> sortedMap = new LinkedHashMap<Object, Object>();
		Object[] keys = new Object[map.size()];
		Object tempKey = null;
		Object[] values = new Object[keys.length];
		Object tempValue = null;
		boolean flag = true;
		Iterator<Entry<K, V>> iter = map.entrySet().iterator();
		int e = 0;
		while (iter.hasNext()) {
			Entry<K, V> entry = iter.next();
			keys[e] = entry.getKey();
			values[e] = entry.getValue();
			e++;
		}
		for (int i = 0; flag && i < keys.length - 1; i++) {
			flag = false;
			for (int j = i; j < keys.length - 1; j++) {
				if (((Comparable<V>) values[j]).compareTo((V) values[j + 1]) > 0) {
					tempValue = values[j];
					values[j] = values[j + 1];
					values[j + 1] = tempValue;
					tempKey = keys[j];
					keys[j] = keys[j + 1];
					keys[j + 1] = tempKey;
					flag = true;
				}
			}
		}
		for (int k = 0; k < keys.length; k++) {
			sortedMap.put(keys[k], values[k]);
		}
		return (LinkedHashMap<K, V>) sortedMap;
	}

	
	
	
	
	/**
	 * 计算频次表再生成哈夫曼树
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	private BinaryTree<String> codeing(String data) throws Exception {
		LinkedHashMap<String, Double> charFrequency = new LinkedHashMap<String, Double>(16, (float) 0.75,true); // 频次表
		int[] valueList = new int[data.length()];
		for (int i = 0; i < data.length(); i++) {
			valueList[i] = (int) data.charAt(i);
		}
		SortClass.radixSort(valueList);
		int i = 0, j = 0, count = 0;
		while (i < valueList.length) {
			j = i + 1;
			count = 1;
			while (j < valueList.length && valueList[j] == valueList[i]) {
				count++;
				j++;
			}
			charFrequency.put(String.valueOf((char) valueList[i]), (double) count / valueList.length);
			i = j;
		}
		BinaryTree<String> binTree = new BinaryTree<String>();
		// 利用频次表生成哈夫曼树
		while (charFrequency.size() > 0) {		
			String key1 = "", key2 = "", newKey = "";
			Double newValue;
			int e = 0;
			charFrequency = sortByValue(charFrequency);
			Iterator<Entry<String, Double>> iter = charFrequency.entrySet().iterator();
			while (iter.hasNext()) {
				e++;
				Map.Entry entry = iter.next();
					if(e==1)
						key1 = (String) entry.getKey();
					if(e==2)
						key2 = (String) entry.getKey();	
			}
			newKey = key1 + key2;
			if (e == 1) {
				binTree.addRootElement(newKey);
				break;
			} else if (e != 0) {
				binTree.addElement(key1, newKey, (byte) 0);
				binTree.addElement(key2, newKey, (byte) 1);
			}
			newValue = charFrequency.get(key1) + charFrequency.get(key2);
			charFrequency.remove(key1);
			charFrequency.remove(key2);
			charFrequency.put(newKey, newValue);
		}
		return binTree;
	}
	
	
	/**
	 * 把String类型的数据转换为String类型的比特串
	 * @param data
	 * @return
	 */
	private String compileToStringBitCode(String data, HashMap<Character, String> codeTable) {
		StringBuffer StringBitCode = new StringBuffer();
		// 编译为字符串二进制哈夫曼编码
		for (int k = 0; k < data.length(); k++) {
			StringBitCode.append(codeTable.get(data.charAt(k)));
		}
		// 计算StringBitCode码的后缀长度
		this.dataCodeSuffix = StringBitCode.length() % 8;
		return StringBitCode.toString();
	}
		
	
	/**
	 * * 将String类型的比特串转换为Byte[] 流数据码,完成数据地再压缩
	 * 
	 * @param StringBitCode
	 * @param codeTable
	 * @return
	 * @throws Exception
	 */
	private Byte[] makeDataCode(String StringBitCode) throws Exception {
		Byte[] dataCode = new Byte[StringBitCode.length() / 8 + (this.dataCodeSuffix == 0 ? 0 : 1)];
		// 表示二进制转为十进制
		DecimalTransformDevice DTD = new DecimalTransformDevice("2", "10");
		int i = 0, j = 0;
		for (; i + 8 < StringBitCode.length() && j < dataCode.length; i += 8, j++) {
			dataCode[j] = (byte) Integer.parseInt(DTD.getDecimalCode(StringBitCode.substring(i, i + 8)));
		}
		if (j < dataCode.length)
			dataCode[j] = (byte) Integer.parseInt(DTD.getDecimalCode(StringBitCode.substring(i)));
		return dataCode;
	}

	/**
	 * 历遍哈夫曼树生成编码表
	 * 
	 * @param node
	 * @param rootCode
	 */
	private void makeCodeTable(BinaryTreeNode<String> node, String rootCode) {
		if (node != null) {
			if (node.getData().length() == 1) {
				this.codeTable.put(node.getData().charAt(0), rootCode);
				this.decodeTable.put(rootCode, node.getData().charAt(0));
			}
			makeCodeTable(node.getLeftChild(), rootCode + "0");
			makeCodeTable(node.getRightChild(), rootCode + "1");
		}
	}

	
	/**
	 * 数据封装打包服务
	 * 对数据进行哈夫曼打包
	 */
	public void packing(String data) {
		this.codeTable= new LinkedHashMap<Character, String>();
		this.decodeTable= new LinkedHashMap<String, Character>();
		this.sourceData = data;
		try {
			BinaryTree<String> binTree = this.codeing(data); // 生成哈夫曼树
			this.huffmanTree=binTree;
			BinaryTreeNode<String> root = binTree.getRootNode(); // 获取树根
			this.makeCodeTable(root, "0"); // 生成码表键值对
			this.StringBitCode=this.compileToStringBitCode(this.sourceData,this.codeTable);
			this.dataCode = this.makeDataCode(this.StringBitCode); // 译为可传输压缩Byte数据
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

	/**
	 * 数据包地解包服务还原String数据本身
	 */
	public String unpacking() {
		String data = "";
		StringBuffer dataBitCode = new StringBuffer();// 数据的字符串比特码
		try {
			DecimalTransformDevice DTD = new DecimalTransformDevice("10", "2");
			for (byte i : this.dataCode) {
				String StringBitCode = DTD.getDecimalCode(String.valueOf(i & 0xff));			
				String zeroBit = "";
				int k = 0;
				// 零比特填充
				for (; k < 8 - StringBitCode.length(); k++) {
					zeroBit += "0";
				}
				dataBitCode.append(zeroBit + StringBitCode);
			}
				if(this.dataCodeSuffix!=0)
				dataBitCode = dataBitCode.delete(dataBitCode.length() - 8,
						dataBitCode.length() - this.dataCodeSuffix);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 译码
		String temp = dataBitCode.toString();
		for (int i = 0, j = i + 1; j <= temp.length(); j++) {
			Character ch = this.decodeTable.get(temp.substring(i, j));
			if (ch != null) {
				data += ch;
				i = j;
			}
		}
		temp = null;
		return data;
	}
}
