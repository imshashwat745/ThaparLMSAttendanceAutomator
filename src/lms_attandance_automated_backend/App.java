package lms_attandance_automated_backend;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class App{
	public static String links[]= {"https://ada-lms.thapar.edu/moodle/mod/attendance/view.php?id=106949",
			"https://ada-lms.thapar.edu/moodle/mod/attendance/view.php?id=106249",
			"https://ada-lms.thapar.edu/moodle/mod/attendance/view.php?id=105843",
			"https://ada-lms.thapar.edu/moodle/mod/attendance/view.php?id=105887"};
	private static MarkMultipleAccounts markMultipleAccounts;
	static List<Details> list;
	public static void init() {
		list=new ArrayList<>();
		
		//adding details of the students
		list.add(new Details("sshukla_be20@thapar.edu","Maa@9630",0));
		list.add(new Details("psingh2_be20@thapar.edu","Pran*1347",1));
		list.add(new Details("bsingh3_be20@thapar.edu","TIET149718#lms",2));
		markMultipleAccounts=new MarkMultipleAccounts(list);
	}
	public static void login(String args[]) {

		//to get the link of the subject to open
		String link=links[MainActivity.subjectIndex];
		
		//to change link according to the selected server
		if(MainActivity.serverMenu.getSelectedIndex()>0) {
			link=link.replaceFirst("ada-", MainActivity.serverMenu.getSelectedIndex()==4?"":
					MainActivity.servers[MainActivity.serverMenu.getSelectedIndex()]+"-");
			System.out.println(link);
		}
		Details.subjectLink=link;
		//this function logs in all accounts
		
		markMultipleAccounts.login();
		
	}
	public static void mark() {
		//this function marks the attendance on all accounts
		markMultipleAccounts.mark();
	}
	public static void close() {
		markMultipleAccounts.close();
	}
}
