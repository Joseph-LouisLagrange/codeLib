package genericClass;

/**
 * @author 作者 Your-Name:
 * @version 创建时间：2019年12月14日 下午9:39:22 类说明
 */
public class VertexListNode<T, E> {
	private T data = null;
	private LinkedList<EdgeListNode<E>> EdgeList = null;

	{
		this.EdgeList = new LinkedList<EdgeListNode<E>>();
	}

	public VertexListNode(T data) {
		this.setData(data);
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		if (data == null)
			throw new NullPointerException("节点的键名不能为空");
		this.data = data;
	}

	public LinkedList<EdgeListNode<E>> getEdgeList() {
		return EdgeList;
	}

	public void setEdgeList(LinkedList<EdgeListNode<E>> edgeList) {
		EdgeList = edgeList;
	}

	/**
	 * 节点间是否存在路径
	 * 
	 * @param vertexIndex
	 * @return
	 */
	public boolean hasEdgeNode(int vertexIndex) {
		boolean f = false;
		for (EdgeListNode<E> e : this.EdgeList) {
			if (e.getVertexIndex() == vertexIndex)
				f = true;
		}
		return f;
	}

	/**
	 * 添加该顶点的邻接边节点
	 * 
	 * @param vertexIndex
	 * @param weight
	 * @throws Exception
	 */
	public void addEdgeNode(int vertexIndex, E edgeNodeName, Weight weight) throws Exception {
		if(this.getEdgeListNodeByEdgeNodeNameAndIndex(vertexIndex, edgeNodeName)!=null)
			throw new Exception("路径已存在");
		this.getEdgeList().add(new EdgeListNode<E>(vertexIndex, edgeNodeName, weight));
	}

	/**
	 * 根据路径名称找出邻接路径节点
	 * 
	 * @param vertexIndex
	 * @param edgeNodeName
	 * @return 节点间的一个路径节点
	 * @throws Exception
	 */
	public EdgeListNode<E> getEdgeListNodeByEdgeNodeNameAndIndex(int vertexIndex, E edgeNodeName) throws Exception {
		EdgeListNode<E>[] container = this.getEdgeListNodeByVertexIndex(vertexIndex);
		EdgeListNode<E> node = null;
		int count = 0;
		for (EdgeListNode<E> e : container) {
			if (e.getEdgeNodeName().equals(edgeNodeName)) {
				count++;
				node = e;
			}
		}
		if (count > 1) {
			throw new Exception("路径冲突");
		}
		return node;
	}

	/**
	 * 根据另外一个顶点找出之间的邻接路径节点集合，可能有多个
	 * 
	 * @param vertexIndex
	 * @return 该节点间的路径
	 * @throws Exception
	 */
	public EdgeListNode<E>[] getEdgeListNodeByVertexIndex(int vertexIndex) throws Exception {
		LinkedList<EdgeListNode<E>> temp = new LinkedList<EdgeListNode<E>>();
		for (EdgeListNode<E> e : this.EdgeList) {
			if (e.getVertexIndex() == vertexIndex) {
				temp.add(e);
			}
		}
		EdgeListNode<E>[] container = new EdgeListNode[temp.getSize()];
		int j = 0;
		for (EdgeListNode<E> i : temp) {
			container[j++] = i;
		}
		return container;
	}

	/**
	 * 重新设置邻接节点间的权重
	 * 
	 * @param vertexIndex
	 * @param weight
	 * @throws Exception
	 */
	public void setWeightByVerTex(int vertexIndex, E edgeNodeName, Weight weight) throws Exception {
//		EdgeListNode<E>[] edgeNodes = this.getEdgeListNodeByVertexIndex(vertexIndex);
//		for (int i = 0; i < edgeNodes.length; i++) {
//			if(edgeNodes[i].getEdgeNodeName().equals(edgeNodeName))
//				edgeNodes[i].setWeight(weight);
//		}
		EdgeListNode<E> edgeNode = this.getEdgeListNodeByEdgeNodeNameAndIndex(vertexIndex, edgeNodeName);
		edgeNode.setWeight(weight);
	}

	/**
	 * 移除节点间的特定名称的路径
	 * @param edgeNodeName
	 * @param vertexIndex
	 * @throws Exception
	 */
	public void removeEdgeNode(int vertexIndex, E edgeNodeName) throws Exception {
		EdgeListNode<E> edgeNode = this.getEdgeListNodeByEdgeNodeNameAndIndex(vertexIndex, edgeNodeName);
			int i = 0;
			for (EdgeListNode<E> e : this.EdgeList) {
				if (e == edgeNode) {
					this.EdgeList.removeAt(i);
					break;
				}
				i++;
			}
			if(edgeNode==null)
				throw new Exception("无此路径");
		}
	
	/**
	 * 移除同一节点间的所有路径
	 * @param vertexIndex
	 * @throws Exception
	 */
	public void removeEdgeNode(int vertexIndex) throws Exception {
		EdgeListNode<E>[] edgeNodes = this.getEdgeListNodeByVertexIndex(vertexIndex);
		int i = 0, j = 0;
		for (EdgeListNode<E> e : this.EdgeList) {
			if(j>=edgeNodes.length)
				break;
			if (e == edgeNodes[j]) {
				this.EdgeList.removeAt(i);
				j++;
			} else
				i++;
		}
		if(j==0)
			throw new Exception("无此路径");
	}

	/**
	 * 修改边的邻接节点
	 * @param oldVertexIndex
	 * @param newVertexIndex
	 * @param edgeNodeName
	 * @throws Exception
	 */
	public void setEdgeNode(int oldVertexIndex, int newVertexIndex, E edgeNodeName) throws Exception {
		EdgeListNode<E> edgeNode = this.getEdgeListNodeByEdgeNodeNameAndIndex(oldVertexIndex, edgeNodeName);
		edgeNode.setVertexIndex(newVertexIndex);
	}
	
	/**
	 * 修改边节点的新邻接节点
	 * @param oldVertexIndex
	 * @param newVertexIndex
	 * @throws Exception
	 */
	public void setEdgeNode(int oldVertexIndex, int newVertexIndex) throws Exception {
		EdgeListNode<E>[] edgeNodes=this.getEdgeListNodeByVertexIndex(oldVertexIndex);
		for(EdgeListNode<E> e : edgeNodes) {
				e.setVertexIndex(newVertexIndex);
		}
	}	
}
