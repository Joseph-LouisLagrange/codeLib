package namespace;
/** 
* @author 作者 Your-Name: 
* @version 创建时间：2019年10月7日 下午2:53:40 
* 类说明 
*/
public class Student {
		private String id=null;
		private String name=null;
		private char sex;
		private String teleNum=null;
		private String address=null;
		private double grade=0;
		/**
		 * @param id
		 * @param name
		 * @param sex
		 * @param teleNum
		 * @param address
		 * @param grade
		 */
		public Student(String id, String name, char sex, String teleNum, String address, double grade) {
			super();
			this.id = id;
			this.name = name;
			this.sex = sex;
			this.teleNum = teleNum;
			this.address = address;
			this.grade = grade;
		}
		public double getGrade() {
			return grade;
		}
		public void setGrade(double grade) {
			this.grade = grade;
		}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public char getSex() {
			return sex;
		}
		public void setSex(char sex) {
			this.sex = sex;
		}
		public String getTeleNum() {
			return teleNum;
		}
		public void setTeleNum(String teleNum) {
			this.teleNum = teleNum;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
}
