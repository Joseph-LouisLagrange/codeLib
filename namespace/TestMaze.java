package namespace;
/** 
* @author 作者 Your-Name: 
* @version 创建时间：2019年10月8日 下午10:21:26 
* 类说明 
*/
public class TestMaze {
	public static void main(String[] args) {
		int[][] cells= {{0,0,0,0,0,0,0,0},
							{0,0,0,1,0,0,0,0},
							{0,0,1,1,1,1,1,0},
							{0,0,1,0,1,0,1,0},
							{0,0,1,0,1,0,1,0},
							{0,1,1,0,1,0,1,0},
							{0,1,0,0,0,0,1,0}};
		Maze maze=new Maze(cells,4,2);
		System.out.println("原始迷宫为:");
		for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < cells[i].length; j++) {
				System.out.print(cells[i][j]+" ");
			}
			System.out.println();
		}
		boolean hasRouter=maze.findRouter(6, 4, 'N');
		System.out.println();
		if(hasRouter)
			System.out.println("路径为:");
		Cell[][] newCells=maze.getRouter();
		for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < cells[i].length; j++) {
				if(newCells[i][j].isRouter())
					System.out.print(" *");
				else
					System.out.print(cells[i][j]+" ");
			}
			System.out.println();
		}
	}
	
}
