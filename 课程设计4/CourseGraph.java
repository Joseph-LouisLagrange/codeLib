package 课程设计4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @author 作者 Your-Name:
 * @version 创建时间：2019年12月24日 下午7:12:36 类说明
 */
public class CourseGraph {
	private Course[] courses = null;
	private boolean[][] matrix = null;
	private int size = 0;

	public CourseGraph() {
		this.courses = new Course[12];
		this.setMatrix(new boolean[12][12]);
		for (int i = 0; i < this.getMatrix().length; i++) {
			Arrays.fill(this.matrix[i], false);
		}
	}

	public void addCourses(Course currentCourse, Course... advancedPlacements) throws Exception {
		int currentCourseIndex = this.indexOf(currentCourse);
		if (currentCourseIndex != -1) {
			this.updateCourse(currentCourse);
		} else {
			this.addCourse(currentCourse);
			currentCourseIndex = this.size - 1;
		}
		for (Course advancedPlacement : advancedPlacements) {
			int advancedCourseIndex = this.indexOf(advancedPlacement);
			if (advancedCourseIndex == -1) { // 无此课程
				this.addCourse(advancedPlacement);
				advancedCourseIndex = this.size - 1;
			}
			// 更新邻接矩阵
			this.matrix[currentCourseIndex][advancedCourseIndex] = true;
		}
	}

	/**
	 * 判断课程是否重复
	 * 
	 * @param course
	 * @return
	 */
	private boolean isRepeat(Course course) {
		return this.indexOf(course) != -1;
	}

	/**
	 * 获取该课程的索引
	 * 
	 * @param course
	 * @return 有则返回第一个索引，否则返回-1
	 */
	private int indexOf(Course course) {
		for (int i = 0; i < this.size; i++) {
			if (this.courses[i].equals(course))
				return i;
		}
		return -1;
	}

	/**
	 * 更新课程信息
	 * 
	 * @param course
	 * @throws Exception 无此课程
	 */
	private void updateCourse(Course course) throws Exception {
		int index = this.indexOf(course);
		if (index == -1)
			throw new Exception("更新失败");
		this.courses[index] = course;
	}

	/**
	 * 添加课程
	 * 
	 * @param course
	 */
	private void addCourse(Course course) {
		this.courses[this.size++] = course;
		if (this.size == this.courses.length) {
			this.setCourses(Arrays.copyOf(this.courses, this.size + 12));
			boolean[][] newMatrix = Arrays.copyOf(this.getMatrix(), this.size + 12);
			for (int i = 0; i < size; i++) {
				newMatrix[i] = Arrays.copyOf(newMatrix[i], this.size + 12);
			}
			for (int i = size; i < newMatrix.length; i++) {
				newMatrix[i] = new boolean[newMatrix.length];
				Arrays.fill(newMatrix[i], false);
			}
			this.setMatrix(newMatrix);
		}
	}

	public Course getCourseByIndex(int index) {
		return this.courses[index];
	}

	public boolean[][] getMatrix() {
		return matrix;
	}

	public void setMatrix(boolean[][] matrix) {
		this.matrix = matrix;
	}

	public Course[] getCourses() {
		return courses;
	}

	public void setCourses(Course[] courses) {
		this.courses = courses;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
	
	
	

	/*
	 * 用户的命令行交互
	 */
	public static void switchOn() throws Exception {
		CourseGraph courseGraph = new CourseGraph();
		Scanner input = new Scanner(System.in);
		System.out.print("请指定学期总数：");
		int termNumber = input.nextInt();
		System.out.print("请指定每学期的学分上限：");
		double creditLimitInTerm = input.nextDouble();
		System.out.println("请添加课程信息( 课程名称 课程学分 [ -pre 前修课程1 前修课程2  前修课程3 ... ] ;  )：例如 ：C02 3 -pre C01 C00 ;");
		do {
			input.nextLine();
			System.out.print("Add course>");
			if(input.hasNext("stop")) {
				System.out.println("课程信息录入完成");
				break;
			}
			String currentCourseId = input.next();
			double currentCourseCredit = Double.parseDouble(input.next());
			String preTag = input.next();
			if (preTag.equals("-pre")) {
				ArrayList<Course> advancedPlacements = new ArrayList<Course>();
				while (!input.hasNext(";")) {
					advancedPlacements.add(new Course(input.next()));
				}
				courseGraph.addCourses(new Course(currentCourseId, currentCourseCredit),
						advancedPlacements.toArray(new Course[0]));
			} else {
				courseGraph.addCourses(new Course(currentCourseId, currentCourseCredit));
			}
		} while (true);
		//拓扑模块
		if(input.hasNextLine()) {
			input.nextLine();
		}
		System.out.println("请选择排课策略 : 1:课程均匀  2:课程集中在前面");
		do {
		System.out.print("Selection strategy>");
		if(input.hasNext("stop")) {
			System.out.println("Goodbye");
			break;
		}
		ToPuSort topuSort=new ToPuSort(courseGraph, creditLimitInTerm, termNumber);
		
		ArrayList<TermList> result=null;
		int select=input.nextInt();
		if(select==1) {
			result=topuSort.getUniform();
			
		}else if(select==2) {
			result=topuSort.getConcentrated();
		}
		else {
			System.out.println("选择策略错误");
			continue;
		}
		topuSort.display(result);
		}while(true);
	}
}
