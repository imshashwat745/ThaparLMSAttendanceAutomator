package lms_attandance_automated_backend;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class OpenSubject implements Runnable{
	private Details details;
	private WebDriver driver ;
	
	
	public OpenSubject(Details details, WebDriver driver) {
		super();
		this.details = details;
		this.driver = driver;
	}
	
	
	private void openSubject() {
		
//		String subjectLink="https://www.google.com/";
//		driver.get(subjectLink);
//		driver.close();
		int index=details.getIndex();
		try {
			WebElement codeBox=driver.findElement(By.name("qrpass"));
			codeBox.sendKeys(MainActivity.textField.getText());
			codeBox.sendKeys(Keys.ENTER);
			try{WebElement ele = driver.findElement(By.xpath("//button[text()="+ "'Continue']"));
			ele.click();}
			catch(Exception e) {
				//if continue button of attendance page is not found then attendance is marked
				MarkMultipleAccounts.marked[index]=true;
				++MainActivity.numberOfMarked;
				MainActivity.updateMarked();
				System.out.print("Attendance Marked Successfully");
			}
		}
		catch(Exception e) {
			//if text box not found then attendance is marked
			MarkMultipleAccounts.marked[index]=true;
			++MainActivity.numberOfMarked;
			MainActivity.updateMarked();
			System.out.println("Attendance Marked Successfully");
		}
	}
	
	public void run() {
		openSubject();
	}
}
