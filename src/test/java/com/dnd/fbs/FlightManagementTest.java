package com.dnd.fbs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FlightManagementTest {
	private WebDriver driver;
	
	@BeforeEach
	 void setUp() {
		 System.setProperty("webdriver.edge.driver", "D:\\test_frontend_fbs\\BanVeMayBay-main\\egdedriver\\msedgedriver.exe");

	     driver = new EdgeDriver();
	     driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	     
	     driver.get("http://localhost:8080/admin");
	     driver.findElement(By.id("username")).sendKeys("admin");
	     driver.findElement(By.id("password")).sendKeys("i1504203sS.");
	     driver.findElement(By.id("btnLogin")).click();
	     }
	 
	@Test
	@Order(1)
	void testAddFlight() {
		driver.get("http://localhost:8080/admin/addFlight");
		  
		driver.findElement(By.name("departingForm")).sendKeys("Hà Nội");
		driver.findElement(By.name("arrivingAt")).sendKeys("Thuỵ Sĩ");
		driver.findElement(By.name("dateFlight")).sendKeys("12-12-2024");
		driver.findElement(By.name("flightTime")).sendKeys("18:00");
		driver.findElement(By.name("departureTime")).sendKeys("13:30");
		driver.findElement(By.name("feeFlightx")).sendKeys("7890000");
			  
		driver.findElement(By.cssSelector("button[type='submit']")).click();
			  
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.urlContains("http://localhost:8080/admin/flightList"));
			  
		String currentUrl = driver.getCurrentUrl();
		assertTrue(currentUrl.equals("http://localhost:8080/admin/flightList"), "URL dự đoán không đúng. URL hiện tại: " + currentUrl); 
		}

		
	@Test
	@Order(2)
	void testUpdateFlight() {
		driver.get("http://localhost:8080/admin/updateFlight/28");
		
		driver.findElement(By.name("dateFlight")).sendKeys("12-12-2024");
		driver.findElement(By.name("flightTime")).sendKeys("18:00");
		driver.findElement(By.name("departureTime")).sendKeys("14:30");
			  
		driver.findElement(By.cssSelector("button[type='submit']")).click();
			  
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.urlContains("http://localhost:8080/admin/flightList"));
			  
		String currentUrl = driver.getCurrentUrl();
		assertTrue(currentUrl.equals("http://localhost:8080/admin/flightList"), "URL dự đoán không đúng. URL hiện tại: " + currentUrl); 
	}
		  
	@Test
	@Order(3)
	void testDelFlight() {
	    driver.get("http://localhost:8080/admin/flightList/page/2?sortField=flightID&sortDir=asc");

	    WebElement deleteButton = driver.findElement(By.xpath("//tr[td[text()='28']]/td/form/button[contains(text(),'Xoá')]"));
	    deleteButton.click();

	    driver.switchTo().alert().accept();
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    wait.until(ExpectedConditions.urlToBe("http://localhost:8080/admin/flightList"));

	    String currentUrl = driver.getCurrentUrl();
	    assertEquals("http://localhost:8080/admin/flightList", currentUrl, "URL không đúng sau khi xoá chuyến bay!");
	}
		 
	
	@AfterEach
	  void tearDown() {
	     if (driver != null) {
	    	 driver.quit();
	    	 }
	     } 
}
