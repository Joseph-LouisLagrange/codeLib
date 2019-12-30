package namespace;
/** 
* @author 作者 Your-Name: 
* @version 创建时间：2019年10月8日 下午9:05:44 
* 类说明 
*/
public class Cell {
	
	private boolean accessible=false;
	private boolean router=false;
	public Cell(boolean accessible) {
		this.accessible=accessible;
	}
	public boolean isAccessible() {
		return accessible;
	}
	public void setAccessible(boolean accessible) {
		this.accessible = accessible;
	}
	public boolean isRouter() {
		return router;
	}
	public void setRouter(boolean router) {
		this.router = router;
	}
}
