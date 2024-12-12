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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PlaneManagementTest {
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
	void testAddPlane() {
		driver.get("http://localhost:8080/admin/addPlane");
		  
		driver.findElement(By.name("planeName")).sendKeys("VJA0029");
		driver.findElement(By.name("slSeat")).sendKeys("280");
		
        WebElement airlineSelect = driver.findElement(By.name("airlineID"));
        Select select = new Select(airlineSelect);
        select.selectByVisibleText("VietJet");
			  
		driver.findElement(By.cssSelector("button[type='submit']")).click();
			  
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.urlContains("http://localhost:8080/admin/listPlane"));
			  
		String currentUrl = driver.getCurrentUrl();
		assertTrue(currentUrl.equals("http://localhost:8080/admin/listPlane"), "URL dự đoán không đúng. URL hiện tại: " + currentUrl); 
	}
	
	@Test
	@Order(2)
	void testUpdatePlane() {
		driver.get("http://localhost:8080/admin/updatePlane/26");
		
        WebElement airlineSelect = driver.findElement(By.name("airlineID"));
        Select select = new Select(airlineSelect);
        select.selectByVisibleText("BamBoo Airline");
			  
		driver.findElement(By.cssSelector("button[type='submit']")).click();
			  
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.urlContains("http://localhost:8080/admin/listPlane"));
			  
		String currentUrl = driver.getCurrentUrl();
		assertTrue(currentUrl.equals("http://localhost:8080/admin/listPlane"), "URL dự đoán không đúng. URL hiện tại: " + currentUrl); 
	}
	
	@Test
	@Order(3)
	void testDelPlane() {
	    driver.get("http://localhost:8080/admin/listPlane/page/2?sortField=planeID&sortDir=asc");

	    WebElement deleteButton = driver.findElement(By.xpath("//tr[td[text()='26']]/td/form/button[contains(text(),'Xoá')]"));
	    deleteButton.click();

	    driver.switchTo().alert().accept();
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    wait.until(ExpectedConditions.urlToBe("http://localhost:8080/admin/listPlane"));

	    String currentUrl = driver.getCurrentUrl();
	    assertEquals("http://localhost:8080/admin/listPlane", currentUrl, "URL không đúng sau khi xoá chuyến bay!");
	}
	
	@AfterEach
	  void tearDown() {
	     if (driver != null) {
	    	 driver.quit();
	    	 }
	     } 
}
