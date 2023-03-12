package lms_attandance_automated_backend;


import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

public class MarkMultipleAccounts{
	public static List<Details> list;
	private static WebDriver driver[];
	public static boolean marked[];
	MarkMultipleAccounts(List<Details> list){
		this.list=list;
		driver=new WebDriver[list.size()];
		marked=new boolean[list.size()];
		int i;
		for(i=0;i<list.size();++i)marked[i]=false;
	}
	
	public static void login() {
		LoginAccounts loginAccounts[]=new LoginAccounts[list.size()];
		int i=0;
		
		EdgeOptions op=new EdgeOptions();
		op.addArguments("--disable-gpu", "--blink-settings=imagesEnabled=false");
		LoginAccounts.semaphore=0;
		//creation of threads
		System.out.println(new Date());
		for(Details details: list) {
//			attendanceMarker[i++]=new AttendanceMarker(details);
			driver[i]=new EdgeDriver(op);
			driver[i].manage().window().minimize();
		    new Thread(new LoginAccounts(details,driver[i])).start();
		    ++i;
		}
	}
	public static void mark() {
		int i;
		while(LoginAccounts.semaphore<list.size())System.out.println(LoginAccounts.semaphore);
		
		System.out.println(new Date());
		System.out.println(1);
		i=0;
		for(i=0;i<list.size();++i) {
//			System.out.println("loop -> "+i);
			if(marked[i])continue;
			try{new Thread(new OpenSubject(list.get(i),driver[i])).start();}
			catch(Exception e) {System.out.println("Here -> "+e);}
			
		}
		System.out.println(new Date());
		System.out.println(2);
		//execution of threads
//		synchronized(this){
//		ExecutorService es=Executors.newFixedThreadPool(4);
		
		
		
//			for(AttendanceMarker thread:attendanceMarker) {
////				System.out.println(Runtime.getRuntime().availableProcessors());
////				es.execute(thread);
////				new Thread(thread).start();
//				thread.run();
//			}
		
		
		
//			try {es.awaitTermination(5, TimeUnit.SECONDS);}
//			catch (InterruptedException e) {e.printStackTrace();}
//		}
	}
	public void close() {
		for(WebDriver i:driver) {
			i.close();
		}
	}
	
}
