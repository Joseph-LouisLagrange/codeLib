package namespace;

import java.util.Arrays;

/**
 * @author 作者 Your-Name:
 * @version 创建时间：2019年10月8日 下午9:07:33 类说明
 */
public class Maze {
	private Cell[][] cells = null;
	private boolean finish = false;
	private int startX = 0;
	private int startY = 0;
	public Maze(int[][] maze, int startX, int startY) {
		this.cells = new Cell[maze.length][maze[0].length];
		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze[i].length; j++) {
					this.cells[i][j]=new Cell(maze[i][j]==1);
			}
		}
		this.startX = startX;
		this.startY = startY;
	}
	
	
	public boolean findRouter(int x, int y, char routerDirection) {
		boolean[] isRouter = new boolean[4];
		Arrays.fill(isRouter, false);
		if (y == this.cells.length || x == this.cells[0].length||x==-1||y==-1) {
			this.finish = true;
			return true;
		}
		
		if (!this.cells[y][x].isAccessible()) {
			return false;
		}
		if (routerDirection != 'S' && !this.finish)
			isRouter[0] = findRouter(x, y - 1, 'N');
		if (routerDirection != 'W' && !this.finish)
			isRouter[1] = findRouter(x + 1, y, 'E');
		if (routerDirection != 'N' && !this.finish)
			isRouter[2] = findRouter(x, y + 1, 'S');
		if (routerDirection != 'E' && !this.finish)
			isRouter[3] = findRouter(x - 1, y, 'W');
		cells[y][x].setRouter(isRouter[0] || isRouter[1] || isRouter[2] || isRouter[3]);
		return isRouter[0] || isRouter[1] || isRouter[2] || isRouter[3];
	}	
	public Cell[][] getRouter(){
		return this.cells;
	}
}
