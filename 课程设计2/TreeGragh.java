package 课程设计2;

import genericClass.BinaryTree;
import genericClass.BinaryTreeNode;
import genericClass.LinkedList;
import javafx.application.Application;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * @author 作者 Your-Name:
 * @version 创建时间：2019年11月27日 上午11:05:44 类说明 打印树图像并对其进行智能地优化
 */
public class TreeGragh<T> {
	BinaryTree<T> BinTree = null;
	Pane pane = null;
	double iniBranche = 400;
	double brancheChangeRate = 0.8;
	double angle = Math.PI * 5 / 6;
	double angleChangeRate = 0.6;
	double radius =30;
	double rootStartX = 0;
	double rootStartY = 0;
	double maxWidth = 0;
	double[] lengthList = null;
	double[] angleList = null;
	LinkedList<Double>[] coordinateList = null;

	/**
	 * 获取必要参数
	 * 
	 * @param BinTree
	 * @param rootStartX
	 * @param rootStartY
	 * @param maxWidth
	 * @throws Exception
	 */
	public TreeGragh(BinaryTree<T> BinTree, double rootStartX, double rootStartY, double maxWidth) throws Exception {
		this.BinTree = BinTree;
		this.pane = new Pane();
		this.lengthList = new double[this.BinTree.getTreeHeight()];
		this.angleList = new double[this.BinTree.getTreeHeight()];
		this.rootStartX = rootStartX;
		this.rootStartY = rootStartY;
		this.maxWidth = maxWidth;
		imageOptimization();
		this.buildTree(this.BinTree.getRootNode(), this.rootStartX, this.rootStartY, this.rootStartX, this.rootStartY, this.angle,
				this.iniBranche);
		this.addNodeContent(this.BinTree.getRootNode(), this.rootStartX, this.rootStartY, this.rootStartX, this.rootStartY, this.angle,
				this.iniBranche);
	}

	public void setBrancheChangeRate(double brancheChangeRate) {
		if (brancheChangeRate <= 0.8 && brancheChangeRate > 0)
			this.brancheChangeRate = brancheChangeRate;
	}

	public void setAngleChangeRate(double angleChangeRate) {
		if (angleChangeRate <= 0.9 && angleChangeRate > 0)
			this.angleChangeRate = angleChangeRate;
	}

	/**
	 * 对树图像进行预解析，推算其可适应的参数值
	 * 
	 * @throws Exception
	 */
	private void imageOptimization() throws Exception {
		do {
			boolean f1 = false, f2 = false, f3 = false, f4 = false;
			this.coordinateList = new LinkedList[this.BinTree.getTreeHeight()];

			for (int i = 0; i < this.coordinateList.length; i++) {
				this.coordinateList[i] = new LinkedList<Double>();
			}
			this.scanningImage(this.BinTree.getRootNode(), rootStartX, rootStartY, rootStartX, rootStartY, this.angle,
					this.iniBranche, 0);
			for (int i = 0; i < this.coordinateList.length; i++) {
				this.coordinateList[i] = this.coordinateList[i].sort();
			}
//			for (int i = 0; i < this.coordinateList.length; i++) {
//				for (int j = 0; j < this.coordinateList[i].getSize() ; j++) {
//					System.out.print(this.coordinateList[i].get(j)+" ");
//				}
//				System.out.println();
//			}
			f1 = this.isCrossBorder();
			f2 = this.isBroCollsion();
			f3 = this.isCollision();
			//f4 = this.isBrancheCollsion();
			if(f2) {
				this.setAngleChangeRate(this.angleChangeRate+0.05);
				this.iniBranche+=10;
				this.radius-=1;
			}else if(!f2&&f3) {
				this.iniBranche-=10;
				this.setBrancheChangeRate(this.brancheChangeRate-0.05);
				this.setAngleChangeRate(this.angleChangeRate-0.05);
			}else if(f1) {
				this.rootStartX+=10;
			}else
				break;
		} while (true);
	}

	/**
	 * 图像的虚拟扫描
	 * 
	 * @param node
	 * @param startX
	 * @param startY
	 * @param endX
	 * @param endY
	 * @param angle
	 * @param length
	 * @param level
	 * @throws Exception
	 */
	private void scanningImage(BinaryTreeNode<T> node, double startX, double startY, double endX, double endY,
			double angle, double length, int level) throws Exception {
		if (node != null) {
			if (level < this.lengthList.length) {
				this.lengthList[level] = length;
				this.angleList[level] = angle;
			}
			this.coordinateList[level].add(new Double(endX));
			scanningImage(node.getLeftChild(), endX, endY, endX - length * Math.sin(angle / 2),
					endY + length * Math.cos(angle / 2), angle * this.angleChangeRate, length * this.brancheChangeRate,
					level + 1);
			scanningImage(node.getRightChild(), endX, endY, endX + length * Math.sin(angle / 2),
					endY + length * Math.cos(angle / 2), angle * this.angleChangeRate, length * this.brancheChangeRate,
					level + 1);
		}
	}

	/**
	 * 判断是否为兄弟碰撞
	 * 
	 * @return
	 */
	private boolean isBroCollsion() {
		for (int i = 0; i < this.lengthList.length; i++) {
			if (this.lengthList[i] * Math.sin(this.angleList[i] / 2) < this.radius) {
				
				return true;
			}
		}
		return false;
	}

	/**
	 * 判断是否树枝过短导致双亲与孩子相碰
	 * 
	 * @return
	 * @throws Exception
	 */
	private boolean isBrancheCollsion() throws Exception {
		return this.lengthList[this.lengthList.length - 1] < 2 * this.radius;
	}

	/**
	 * 判断是否同级的树节点发生碰撞
	 * 
	 * @return
	 * @throws Exception
	 */
	private boolean isCollision() throws Exception {
		for (int i = 0; i < this.coordinateList.length; i++) {
			for (int j = 0; j < this.coordinateList[i].getSize() - 1; j++) {
				if (this.coordinateList[i].get(j + 1) - this.coordinateList[i].get(j) <= 2 * this.radius) {
					
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 判断是否树图像越界
	 * 
	 * @return
	 * @throws Exception
	 */
	private boolean isCrossBorder() throws Exception {
		for (int i = 0; i < this.coordinateList.length; i++) {
			if (this.coordinateList[i].get(0)< 0)
				return true;
		}
		return false;
	}

	/**
	 * 得到树的Pane呈现节点
	 * 
	 * @return
	 */
	public Pane getTreePane() {
		return this.pane;
	}

	/**
	 * 根据最优参数历遍二叉树构建图像
	 * 
	 * @param node
	 */
	private void buildTree(BinaryTreeNode<T> node, double startX, double startY, double endX, double endY, double angle,
			double length) {
		if (node != null) {
			Line line = new Line();
			line.setStartX(startX);
			line.setStartY(startY);
			line.setEndX(endX);
			line.setEndY(endY);
			this.pane.getChildren().add(line);
			buildTree(node.getLeftChild(), endX, endY, endX - length * Math.sin(angle / 2),
					endY + length * Math.cos(angle / 2), angle * this.angleChangeRate, length * this.brancheChangeRate);
			buildTree(node.getRightChild(), endX, endY, endX + length * Math.sin(angle / 2),
					endY + length * Math.cos(angle / 2), angle * this.angleChangeRate, length * this.brancheChangeRate);
		}
	}

	/**
	 * 对数添加文本内容
	 * 
	 * @param node
	 * @param startX
	 * @param startY
	 * @param endX
	 * @param endY
	 * @param angle
	 * @param length
	 */
	private void addNodeContent(BinaryTreeNode<T> node, double startX, double startY, double endX, double endY,
			double angle, double length) {
		if (node != null) {
			double offset = -length * 0.2;
			String bit = "0";
			if (startX < endX) {
				offset *= -1;
				bit = "1";
			}
			Text bitText = new Text(bit);
			bitText.setFont(new Font(20));
			bitText.setX(endX / 2 + startX / 2 + offset);
			bitText.setY(endY / 2 + startY / 2);
			this.pane.getChildren().add(bitText);
			Circle circle = new Circle();
			circle.setCenterX(endX);
			circle.setCenterY(endY);
			circle.setRadius(this.radius);
			circle.setFill(Color.WHITE);
			this.pane.getChildren().add(circle);
			if (node.getData().toString().length() == 1) {
				Text text = new Text(node.getData().toString());
				text.setFont(new Font(20));
				text.setFill(Color.RED);
				text.setX(endX);
				text.setY(endY);
				this.pane.getChildren().add(text);
			}
			addNodeContent(node.getLeftChild(), endX, endY, endX - length * Math.sin(angle / 2),
					endY + length * Math.cos(angle / 2), angle * this.angleChangeRate, length * this.brancheChangeRate);
			addNodeContent(node.getRightChild(), endX, endY, endX + length * Math.sin(angle / 2),
					endY + length * Math.cos(angle / 2), angle * this.angleChangeRate, length * this.brancheChangeRate);
		}
	}
}
