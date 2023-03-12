package lms_attandance_automated_backend;

public class Details {
	private String username,password;
	public static String subjectLink;
	private int index;
	
	public Details(String username, String password,int index) {
		super();
		this.username = username;
		this.password = password;
		this.index=index;
	}

	public int getIndex() {
		return index;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getSubjectLink() {
		return subjectLink;
	}

	
}
