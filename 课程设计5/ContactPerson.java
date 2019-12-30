package 课程设计5;

/**
 * @author 作者 Your-Name:
 * @version 创建时间：2019年12月21日 下午3:27:16 类说明
 */
public class ContactPerson {
	private String name = null;
	private String[] telephone = null;
	private String mailbox = null;

	public ContactPerson(String name, String mailbox, String...telephone) throws Exception {
		this.setName(name);
		this.setMailbox(mailbox);
		this.setTelephone(telephone);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) throws Exception {
		if (name == null || name.equals(""))
			throw new Exception("名字不能为空");
		this.name = name;
	}

	public String[] getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone[]) throws Exception {
		for (int i = 0; i < telephone.length; i++) {
			if (!telephone[i].matches("^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\\d{8}$")) {
				throw new Exception("电话号码格式错误");
			}
		}
		this.telephone = telephone;
	}

	public String getMailbox() {
		return mailbox;
	}

	public void setMailbox(String mailbox) throws Exception {
		if (!mailbox.matches("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$"))
			throw new Exception("邮箱格式错误");
		this.mailbox = mailbox;
	}

	public String toString() {
		return this.name;
	}
}
