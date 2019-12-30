package 课程设计4;

/**
 * @author 作者 Your-Name:
 * @version 创建时间：2019年12月24日 下午7:07:20 类说明 每个课程
 */
public class Course {
	private String courseId;
	private double credit = 0;

	public Course(String courseId, double credit) {
		this.setCourseId(courseId);
		this.setCredit(credit);
	}
	
	public Course(String courseId) {
		this.setCourseId(courseId);
	}

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public double getCredit() {
		return credit;
	}

	public void setCredit(double credit) {
		this.credit = credit;
	}

	public boolean equals(Object o) {
		return this.getCourseId().equals(((Course) o).getCourseId());
	}
}
