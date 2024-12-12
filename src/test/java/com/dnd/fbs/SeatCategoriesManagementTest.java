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
public class SeatCategoriesManagementTest {
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
	void testAddSeatCategory() {
		driver.get("http://localhost:8080/admin/addSeatCategory");
			  
		driver.findElement(By.name("categoryName")).sendKeys("Test Seat Category");
		driver.findElement(By.name("luggageAttach")).sendKeys("Test Luggage Attach");
		driver.findElement(By.name("feeCategory")).sendKeys("100000");
			  
		driver.findElement(By.cssSelector("button[type='submit']")).click();
			  
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.urlContains(
		"http://localhost:8080/admin/seatCategoryList"));
			  
		String currentUrl = driver.getCurrentUrl();
		assertTrue(currentUrl.equals("http://localhost:8080/admin/seatCategoryList"), "URL dự đoán không đúng. URL hiện tại: " + currentUrl); }
			  
	@Test
			  
	@Order(2)
	void testUpdateSeatCategory() {
		driver.get("http://localhost:8080/admin/updateSeatCategory/13");
			  
		driver.findElement(By.name("categoryName")).sendKeys(" Update");
		driver.findElement(By.name("luggageAttach")).sendKeys(" Update");
			  
		driver.findElement(By.cssSelector("button[type='submit']")).click();
			  
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.urlContains("http://localhost:8080/admin/seatCategoryList"));
			  
		String currentUrl = driver.getCurrentUrl();
		assertTrue(currentUrl.equals("http://localhost:8080/admin/seatCategoryList"), "URL dự đoán không đúng. URL hiện tại: " + currentUrl); }
			 
	
	@Test
	@Order(3)
	void testDelSeatCategory() {
	    driver.get("http://localhost:8080/admin/seatCategoryList");

	    WebElement deleteButton = driver.findElement(By.xpath("//tr[td[text()='13']]/td/form/button[contains(text(),'Xoá')]"));
	    deleteButton.click();

	    driver.switchTo().alert().accept();
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    wait.until(ExpectedConditions.urlToBe("http://localhost:8080/admin/seatCategoryList"));

	    String currentUrl = driver.getCurrentUrl();
	    assertEquals("http://localhost:8080/admin/seatCategoryList", currentUrl, "URL không đúng sau khi xóa hạng ghế!");
	    
	}
	
	@AfterEach
	  void tearDown() {
	     if (driver != null) {
	    	 driver.quit();
	    	 }
	     } 
}
