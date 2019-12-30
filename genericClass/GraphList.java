package genericClass;

import java.util.Arrays;

import 课程设计6.CityPathWeight;
import 课程设计6.Path;

/**
 * @author 作者 Your-Name:
 * @version 创建时间：2019年12月14日 下午9:54:34 类说明
 */
public class GraphList<T, E> {
	public static final boolean UDG = false, DG = true; // 图的有无向性
	private VertexListNode<T, E>[] vertexes = null; // 顶点表
	private int vertexCount = 0; // 当前顶点的数量
	private boolean isDG = true; // 是否为有向图
	private LinkedList<Path> paths=null;
	/**
	 * 
	 * @param edgeCount
	 */
	public GraphList(boolean isDG) {
		super();
		this.isDG = isDG;
		this.setVertexes(new VertexListNode[16]);
		this.paths=new LinkedList<Path>();
	}

	public boolean isDG() {
		return isDG;
	}

	public void setDG(boolean isDG) {
		this.isDG = isDG;
	}
	
	
	
	public LinkedList<Path> getPaths() {
		return paths;
	}

	public void setPaths(LinkedList<Path> paths) {
		this.paths = paths;
	}

	public void setVertexes(VertexListNode<T, E>[] vertexes) {
		this.vertexes = vertexes;
	}

	public int getVertexCount() {
		return vertexCount;
	}

	public void setVertexCount(int vertexCount) {
		if (this.vertexCount >= this.vertexes.length - 1)
			this.setVertexes(Arrays.copyOf(this.vertexes, this.vertexes.length + 16)); // 为顶点表申请更大的空间
		this.vertexCount = vertexCount;
	}

	/**
	 * 判断节点是否冲突
	 * 
	 * @param vertexName
	 * @return 是否节点冲突
	 */
	public boolean isRepeat(T vertexName) {
		for (int i = 0; i < this.vertexCount; i++) {
			if (vertexName.equals(this.vertexes[i].getData()))
				return true;
		}
		return false;
	}

	/**
	 * 添加新的图顶点
	 * 
	 * @param vertexName
	 * @throws Exception
	 */
	public void addVertexListNode(T vertexName) throws Exception {
		if (vertexName == null)
			throw new NullPointerException();
		if (this.isRepeat(vertexName))
			throw new Exception("节点冲突");
		this.vertexes[this.vertexCount] = new VertexListNode<T, E>(vertexName); // 添加新节点到顶点组中
		this.setVertexCount(this.vertexCount + 1);
	}

	/**
	 * 移除顶点
	 * 
	 * @param vertexName
	 * @throws Exception
	 */
	public void removeVertexListNode(T vertexName) throws Exception {
		int index = this.indexOf(vertexName);
		if (index == -1)
			throw new Exception("无此顶点");
		for (int i = 0; i < this.vertexCount; i++) {
			LinkedList<EdgeListNode<E>> edgeNodes = this.vertexes[i].getEdgeList();
			int k = -1;
			for (EdgeListNode<E> j : edgeNodes) {
				k++;
				if (j.getVertexIndex() == index) {
					edgeNodes.removeAt(k);
				} else if (j.getVertexIndex() > index) {
					j.setVertexIndex(j.getVertexIndex() - 1);
				}
			}
			// 数组向前覆盖移动元素
			if (i >= index) {
				this.vertexes[i] = this.vertexes[i + 1];
			}
		}
		this.setVertexCount(this.getVertexCount() - 1);
	}

	/**
	 * 添加顶点的路径以及路径的权值
	 * 
	 * @param vertexName1
	 * @param vertexName2
	 * @param weight
	 * @throws Exception
	 */
	public void addEdge(T vertexName1, T vertexName2, E edgeNodeName, Weight weight) throws Exception {
		if (this.isDG == GraphList.DG)
			this.addEdgeInDG(vertexName1, vertexName2, edgeNodeName, weight);
		else
			this.addEdgeInUDG(vertexName1, vertexName2, edgeNodeName, weight);
	}

	/**
	 * 判断有无该顶点
	 * 
	 * @param vertexName
	 * @return
	 */
	public boolean hasVertex(T vertexName) {
		for (int i = 0; i < this.vertexCount; i++) {
			if (vertexes[i].getData().equals(vertexName)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 判断顶点的索引值
	 * 
	 * @param vertexName
	 * @return
	 * @throws Exception
	 */
	private int indexOf(T vertexName) throws Exception {
		int i = 0;
		for (; i < this.vertexCount; i++) {
			if (this.vertexes[i].getData().equals(vertexName))
				return i;
		}
		if (i == this.vertexCount)
			throw new Exception("无此顶点");
		return -1;
	}

	/**
	 * 添加路径到无向图
	 * 
	 * @param vertexName1
	 * @param vertexName2
	 * @param weight
	 * @throws Exception
	 */
	protected void addEdgeInUDG(T vertexName1, T vertexName2, E edgeNodeName, Weight weight) throws Exception {
		if (this.hasVertex(vertexName1) && this.hasVertex(vertexName2)) {
			this.vertexes[this.indexOf(vertexName1)].addEdgeNode(this.indexOf(vertexName2), edgeNodeName, weight);
			this.vertexes[this.indexOf(vertexName2)].addEdgeNode(this.indexOf(vertexName1), edgeNodeName, weight);
		} else
			throw new Exception("顶点组中无此节点");
	}

	/**
	 * 添加路径到有向图
	 * 
	 * @param vertexName1
	 * @param vertexName2
	 * @param weight
	 * @throws Exception
	 */
	protected void addEdgeInDG(T vertexName1, T vertexName2, E edgeNodeName, Weight weight) throws Exception {
		if (this.hasVertex(vertexName1) && this.hasVertex(vertexName2)) {
			this.vertexes[this.indexOf(vertexName1)].addEdgeNode(this.indexOf(vertexName2), edgeNodeName, weight);
		} else
			throw new Exception("顶点组中无此节点");
	}

	/**
	 * 修改顶点间的路径信息
	 * 
	 * @param startVertexNode
	 * @param newEndVertexNode
	 * @param weight
	 * @throws Exception
	 */
	public void setEdgeNode(T startVertexNode, T endVertexNode, E edgeNodeName, Weight weight) throws Exception {
		if (this.isDG) {
			this.vertexes[this.indexOf(startVertexNode)].setWeightByVerTex(this.indexOf(endVertexNode), edgeNodeName,
					weight);
		} else {
			int endIndex = this.indexOf(endVertexNode);
			int startIndex = this.indexOf(startVertexNode);
			this.vertexes[startIndex].setWeightByVerTex(endIndex, edgeNodeName, weight);
			this.vertexes[endIndex].setWeightByVerTex(startIndex, edgeNodeName, weight);
		}
	}

	/**
	 * 移除两个邻接节点之间全部的路径关系
	 * 
	 * @param startVertexNode
	 * @param endVertexNode
	 * @throws Exception
	 */
	public void removeEdgeNode(T startVertexNode, T endVertexNode) throws Exception {
		if (this.isDG) {
			this.vertexes[this.indexOf(startVertexNode)].removeEdgeNode(this.indexOf(endVertexNode));
		} else {
			int endIndex = this.indexOf(endVertexNode);
			int startIndex = this.indexOf(startVertexNode);
			this.vertexes[startIndex].removeEdgeNode(endIndex);
			this.vertexes[endIndex].removeEdgeNode(startIndex);
		}
	}

	/**
	 * 移除两个顶点间的指定特定一个路径
	 * 
	 * @param startVertexNode
	 * @param endVertexNode
	 * @param edgeNodeName
	 * @throws Exception
	 */
	public void removeEdgeNodeByName(T startVertexNode, T endVertexNode, E edgeNodeName) throws Exception {
		if (this.isDG) {
			this.vertexes[this.indexOf(startVertexNode)].removeEdgeNode(this.indexOf(endVertexNode), edgeNodeName);
		} else {
			int endIndex = this.indexOf(endVertexNode);
			int startIndex = this.indexOf(startVertexNode);
			this.vertexes[startIndex].removeEdgeNode(endIndex, edgeNodeName);
			this.vertexes[endIndex].removeEdgeNode(startIndex, edgeNodeName);
		}
	}

	/**
	 * 邻接节点的查询
	 * 
	 * @param startVertexNode
	 * @return 顶点集合
	 * @throws Exception
	 */
	public Object[] getAdjacentVertexNode(T startVertexNode) throws Exception {
		Object[] list = new Object[this.vertexes[this.indexOf(startVertexNode)].getEdgeList().getSize()];
		boolean[] cossion = new boolean[this.vertexCount];
		Arrays.fill(cossion, false);
		int i = 0;
		for (EdgeListNode<E> e : this.vertexes[this.indexOf(startVertexNode)].getEdgeList()) {
			if (!cossion[e.getVertexIndex()]) {
				list[i++] = this.vertexes[e.getVertexIndex()].getData();
				cossion[e.getVertexIndex()] = true;
			}
		}
		return list;
	}

	/**
	 * 获取相邻节点间的路径节点
	 * 
	 * @param startVertexNode
	 * @param endVertexNode
	 * @return
	 * @throws Exception
	 */
	public EdgeListNode<E>[] getEdgeNodesByVertexToVertex(T startVertexNode, T endVertexNode) throws Exception {
		System.out.println(startVertexNode);
		int sIndex = this.indexOf(startVertexNode);
		int eIndex = this.indexOf(endVertexNode);
		if (sIndex < 0 || eIndex < 0)
			throw new Exception("无此节点");
		return this.vertexes[sIndex].getEdgeListNodeByVertexIndex(eIndex);
	}

	/**
	 * 获取由此节点的所以邻接路径
	 * 
	 * @param startVertexNode
	 * @return
	 * @throws Exception
	 */
	public Object[] getEdgeNodesByVertex(T startVertexNode) throws Exception {
		return this.getVerTexListNodes()[this.indexOf(startVertexNode)].getEdgeList().toArray();
	}

	/**
	 * 获取特定的路径节点
	 * 
	 * @param startVertexNode
	 * @param endVertexNode
	 * @param edgeNodeName
	 * @return
	 * @throws Exception
	 */
	public EdgeListNode<E> getEdgeNodeByAndName(T startVertexNode, T endVertexNode, E edgeNodeName) throws Exception {
		return this.vertexes[this.indexOf(startVertexNode)]
				.getEdgeListNodeByEdgeNodeNameAndIndex(this.indexOf(endVertexNode), edgeNodeName);
	}
	
	
	/**
	 * 返回顶点表
	 * @return
	 */
	public VertexListNode<T, E>[] getVerTexListNodes() {
		return this.vertexes;
	}
	
	
	
	
	/**
	 * 搜索所以路径
	 * @param currentVertexNode
	 * @param endVertexNode
	 * @throws Exception
	 */
	public void pathSearch(T startVertexNode, T endVertexNode,Path path) throws Exception{
		this.paths=new LinkedList<Path>();
		LinkedList<EdgeListNode<E>> routerList=new LinkedList<EdgeListNode<E>>();
		LinkedList<T> vertexNodes =new LinkedList<T>();
		this.pathSearch(startVertexNode,this.indexOf(startVertexNode), this.indexOf(endVertexNode),routerList, vertexNodes,path);
	}
	
	private void pathSearch(T startVertexNode,int index,int endIndex,LinkedList< EdgeListNode<E>> router,LinkedList<T> vertexNodes ,Path path) throws Exception {
			if(index==endIndex) {
				path=path.getNewPath(startVertexNode);
				int i=0;
				for(EdgeListNode<E> e:router) {
					path.addPath(e, vertexNodes.get(i));
					i++;
				}
				this.paths.add(path);
				return;
			}
			if(vertexes[index].getEdgeList().isEmpty())
				return;
			for(EdgeListNode<E> e: vertexes[index].getEdgeList()) {
				if(e.getVertexIndex()!=index) {
					router.add(e);
					vertexNodes.add(this.vertexes[e.getVertexIndex()].getData());
					this.pathSearch(startVertexNode,e.getVertexIndex(), endIndex, router,vertexNodes,path);
					vertexNodes.removeAt(vertexNodes.getSize()-1);
					router.removeAt(router.getSize()-1);
				}
			}
	}
	
	
	
	
}
