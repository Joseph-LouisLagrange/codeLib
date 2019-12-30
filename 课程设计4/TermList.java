package 课程设计4;
/** 
* @author 作者 Your-Name: 
* @version 创建时间：2019年12月26日 上午10:50:45 
* 类说明 学期链
*/

import java.util.ArrayList;

public class TermList {
	private ArrayList<Term> termList = null;

	public TermList() {
		this.termList = new ArrayList<Term>();
	}

	public int getTermsCount() {
		return this.termList.size();
	}

	public void addTerm(Term term) {
		this.termList.add(term);
	}
	
	
	/**
	 * 计算课程的前面学期集中率
	 * @param numberSemesters
	 * @return
	 */
	public double getCourseConcentrationRate(int numberSemesters) {
		double concentrationRate = 0;
		for (int i = 0; i < this.termList.size(); i++) {
			concentrationRate += (numberSemesters - i) * this.termList.get(i).getCoursesCount();
		}
		return concentrationRate;
	}

	/**
	 * 根据指定的学期数计算课程数量方差
	 * 
	 * @param numberSemesters
	 * @return
	 */
	public double getVarianceCourse(int numberSemesters) {
		double average = 0, count = 0;
		for (Term term : this.termList) {
			count += term.getCoursesCount();
		}
		average = count / numberSemesters;
		count = 0;
		for (Term term : this.termList) {
			count += Math.pow(term.getCoursesCount() - average, 2);
		}
		for (int i = this.termList.size(); i < numberSemesters; i++) {
			count += Math.pow(average, 2);
		}
		return count / numberSemesters;
	}

	/**
	 * 深度克隆
	 */
	public TermList clone() {
		TermList cloneTermList = new TermList();
		for (Term t : this.termList) {
			cloneTermList.addTerm(t);
		}
		return cloneTermList;
	}

	public ArrayList<Term> getTermList() {
		return termList;
	}

	public void setTermList(ArrayList<Term> termList) {
		this.termList = termList;
	}

}
