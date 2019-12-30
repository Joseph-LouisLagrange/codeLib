package 课程设计6;

import genericClass.EdgeListNode;

/**
 * @author 作者 Your-Name:
 * @version 创建时间：2019年12月20日 下午7:57:58 类说明
 */
public abstract class Path<T, E> {
	abstract public void addPath(EdgeListNode<E> e, T nextVertex) throws Exception;
	abstract public Path getNewPath(T startVertex);
	abstract public String getStartCity();

}
