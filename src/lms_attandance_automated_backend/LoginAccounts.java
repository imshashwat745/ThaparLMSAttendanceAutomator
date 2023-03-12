package lms_attandance_automated_backend;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

public class LoginAccounts implements Runnable{
	private Details details;
	private WebDriver driver ;
	public static int semaphore;
	
	
	public LoginAccounts(Details details,WebDriver driver) {
		this.details = details;
	    this.driver = driver;
		
	}
	
	
	private void login() {
		String uname=details.getUsername();
		String pwd=details.getPassword();
		String subjectLink=details.getSubjectLink();
		
		
		
		
		//Initializing the chrome driver

//		driver.manage().window().maximize();//for maximizing the window
//		open the lms login page
//		driver.manage().window().minimize();
		driver.get(subjectLink);
//		find the text box of username
			WebElement username=driver.findElement(By.id("username"));
	//		find the text box of password
			WebElement password=driver.findElement(By.id("password"));
	//		find the login button
			WebElement login=driver.findElement(By.id("loginbtn"));
	//		send username and password to lms and login
			username.sendKeys(uname);
			password.sendKeys(pwd);
			login.click();
			try {
				driver.findElement(By.id("loginbtn"));
			}
			catch(Exception e) {
				++semaphore;
			}
			if(semaphore==MarkMultipleAccounts.list.size()) {
//				if all accounts are logged in then this semaphore variable which is updating
//				continuously one every login will be equal to number of students
				MainActivity.loginBtn.setText("All Accounts Logged in");
				MainActivity.loginBtn.setForeground(new Color(0, 255, 0));
			}
//			notify();
//			++semaphore;
//			notify();
	}
	public void run() {
		login();
	}
}
