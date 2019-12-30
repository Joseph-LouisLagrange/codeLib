package 课程设计4;
/** 
* @author 作者 Your-Name: 
* @version 创建时间：2019年12月26日 上午8:56:16 
* 类说明 每个学期
*/

import java.util.ArrayList;

public class Term {
	private ArrayList<Course> coursesInTerm = null;
	private double totalCredits = 0;

	public Term() {
		this.coursesInTerm = new ArrayList<Course>();
	}

	public void addCourseInTerm(Course course) {
		this.coursesInTerm.add(course);
		this.totalCredits += course.getCredit();
	}

	public ArrayList<Course> getCoursesInTerm() {
		return coursesInTerm;
	}

	public int getCoursesCount() {
		return this.coursesInTerm.size();
	}

	public double getTotalCredits() {
		return totalCredits;
	}
}
