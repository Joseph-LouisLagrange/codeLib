package 课程设计4;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author 作者 Your-Name:
 * @version 创建时间：2019年12月24日 下午7:33:32 类说明
 */
public class ToPuSort {
	private CourseGraph courseGraph = null;
	private double creditLimitInTerm = 0;
	private int termNumber = 0;
	private ArrayList<TermList> allTermList = new ArrayList<TermList>();

	public ToPuSort(CourseGraph courseGraph, double creditLimitInTerm, int termNumber) {
		this.setCourseGraph(courseGraph);
		this.setCreditLimitInTerm(creditLimitInTerm);
		this.setTermNumber(termNumber);
	}

	public CourseGraph getCourseGraph() {
		return courseGraph;
	}

	public void setCourseGraph(CourseGraph courseGraph) {
		this.courseGraph = courseGraph;
	}

	public double getCreditLimitInTerm() {
		return creditLimitInTerm;
	}

	public void setCreditLimitInTerm(double creditLimitInTerm) {
		this.creditLimitInTerm = creditLimitInTerm;
	}

	public int getTermNumber() {
		return termNumber;
	}

	public void setTermNumber(int termNumber) {
		this.termNumber = termNumber;
	}

	public void display(ArrayList<TermList> result) {
		System.out.println("排课的结果如下 : ");
		for (int i = 0; i < this.getTermNumber(); i++) {
			System.out.printf("%-20s ", "第" + (i + 1) + "学期");
		}
		System.out.println();
		for (TermList termList : result) {
			ArrayList<Term> terms = termList.getTermList();
			int i = 0;
			for (; i < termList.getTermsCount(); i++) {
				String courses = "";
				for (Course course : terms.get(i).getCoursesInTerm()) {
					courses += course.getCourseId() + " ";
				}
				System.out.printf("%-20s", courses);
			}
			for (; i < this.getTermNumber(); i++) {
				System.out.printf("%-20s", "无课程");
			}
			System.out.println();
		}
	}

	/**
	 * 返回均匀排课的排课链集合
	 * 
	 * @return
	 */
	public ArrayList<TermList> getUniform() {
		ArrayList<TermList> result = new ArrayList<TermList>();
		for (TermList tl : this.getAllTermList()) {
			if (result.isEmpty()) {
				result.add(tl);
			} else {
				if (result.get(0).getVarianceCourse(this.termNumber) > tl.getVarianceCourse(termNumber)) {
					result = new ArrayList<TermList>();
					result.add(tl);
				} else if (Double.compare(result.get(0).getVarianceCourse(this.termNumber),
						tl.getVarianceCourse(termNumber)) == 0) {
					result.add(tl);
				}
			}
		}
		return result;
	}

	/**
	 * 获得前面集中排课的排课链集合
	 * 
	 * @return
	 */
	public ArrayList<TermList> getConcentrated() {
		ArrayList<TermList> result = new ArrayList<TermList>();
		for (TermList tl : this.getAllTermList()) {
			if (result.isEmpty()) {
				result.add(tl);
			} else {
				if (result.get(0).getCourseConcentrationRate(this.termNumber) < tl
						.getCourseConcentrationRate(termNumber)) {
					result = new ArrayList<TermList>();
					result.add(tl);
				} else if (Double.compare(result.get(0).getCourseConcentrationRate(this.termNumber),
						tl.getCourseConcentrationRate(termNumber)) == 0) {
					result.add(tl);
				}
			}
		}
		return result;
	}

	public ArrayList<TermList> getAllTermList() {
		boolean[] iniTuPu = new boolean[this.getCourseGraph().getSize()];
		Arrays.fill(iniTuPu, false);
		TermList termList = new TermList();
		this.makeToPuList(iniTuPu, termList);
		return allTermList;
	}

	public void setAllTermList(ArrayList<TermList> allTermList) {
		this.allTermList = allTermList;
	}

	private boolean hasCourse(int[] course, int index) {
		for (int i : course) {
			if (i == index)
				return true;
		}
		return false;
	}

	public void makeToPuList(boolean[] sortedCourse, TermList termList) {
		boolean f = false;
		for (int i = 0; i < sortedCourse.length; i++) {
			if (!sortedCourse[i]) {
				f = true;
				break;
			}
		}
		if (termList.getTermsCount() > this.getTermNumber())
			return;
		// 递归停止开始录入学期链
		if (!f) {
			this.allTermList.add(termList);
			return;
		}
		ArrayList<Integer> TopologyCourses = new ArrayList<Integer>();
		// 取出同列可同时学习的课程索引
		for (int i = 0; i < this.courseGraph.getSize(); i++) {
			if (sortedCourse[i])
				continue;
			boolean isEnrollZero = false;
			for (int j = 0; j < this.courseGraph.getMatrix()[i].length; j++) {
				if (this.courseGraph.getMatrix()[i][j] == true && sortedCourse[j] == false) {
					isEnrollZero = true;
					break;
				}
			}
			if (!isEnrollZero)
				TopologyCourses.add(i); // 添加到可拓扑序列中
		}

		int[] iteratingAarray = new int[TopologyCourses.size()];
		for (int i = 0; i < iteratingAarray.length; i++) { // 初始化历遍数组
			iteratingAarray[i] = i;
		}
		int p = iteratingAarray.length - 1; // 初始化最大读取指针

		while (true) {
			if (iteratingAarray[0] >= iteratingAarray.length)
				break;
			Term term = new Term();
			boolean[] nextSortCourse = sortedCourse.clone();
			for (int i = 0; i <= p; i++) {
				nextSortCourse[TopologyCourses.get(iteratingAarray[i])] = true;
				term.addCourseInTerm(this.courseGraph.getCourseByIndex(TopologyCourses.get(iteratingAarray[i])));
			}
			if (Double.compare(term.getTotalCredits(), this.getCreditLimitInTerm()) <= 0) {
				TermList nextTermList = termList.clone();
				nextTermList.addTerm(term);
				this.makeToPuList(nextSortCourse, nextTermList);
			}

			// p的指针位置数值跳变
			iteratingAarray[p]++;
			// 整流
			for (int k = p, n = 0; k < iteratingAarray.length; k++, n++) {
				iteratingAarray[k] = iteratingAarray[p] + n;
			}
			// p指针重置
			p = 0;
			while (true) {
				if (iteratingAarray[p] >= iteratingAarray.length) {
					p--; // 前置一位
					break;
				}
				p++;
			}
		}
	}
}
