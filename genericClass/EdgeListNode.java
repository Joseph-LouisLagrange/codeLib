package genericClass;

/**
 * @author 作者 Your-Name:
 * @version 创建时间：2019年12月14日 下午9:31:18 类说明
 */
public class EdgeListNode<E> implements Comparable<EdgeListNode<E>>{
	private int vertexIndex = -1;
	private Weight weight=null;
	private E edgeNodeName=null;
	
	/**
	 * 
	 */
	public EdgeListNode() {
		super();
	}
	
	
	
	
	/**
	 * @param vertexIndex
	 * @param weight
	 */
	public EdgeListNode(int vertexIndex, E edgeNodeName,Weight weight) {
		super();
		this.setVertexIndex(vertexIndex);
		this.setEdgeNodeName(edgeNodeName);
		this.setWeight(weight);	
	}

	


	public E getEdgeNodeName() {
		return edgeNodeName;
	}




	public void setEdgeNodeName(E edgeNodeName) {
		this.edgeNodeName = edgeNodeName;
	}




	public Weight getWeight() {
		return weight;
	}



	public void setWeight(Weight weight) {
		if(weight==null)
			throw new NullPointerException("权重为空");
		this.weight = weight;
	}



	public int getVertexIndex() {
		return vertexIndex;
	}

	public void setVertexIndex(int vertexIndex) {
		if(vertexIndex<0)
			throw new IndexOutOfBoundsException("顶点指针越界");
		this.vertexIndex = vertexIndex;
	}




	@Override
	public int compareTo(EdgeListNode<E> o) {
		if(this.getVertexIndex()<o.getVertexIndex())
			return -1;
		else if(this.getVertexIndex()>o.getVertexIndex())
			return 1;
		else
			return 0;
	}
	
	
}
