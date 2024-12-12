package com.dnd.fbs;

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
public class AirlineManagementTest {
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
	void testAddAirline() {
	    driver.get("http://localhost:8080/admin/airlineCompanies/new");
	    driver.findElement(By.name("airlineName")).sendKeys("Test Airline");
	    driver.findElement(By.name("image")).sendKeys("D:\\test_frontend_fbs\\BanVeMayBay-main\\airlineCompany-photos\\1\\jetstar.png");

	    driver.findElement(By.cssSelector("input[type='submit']")).click();

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    wait.until(ExpectedConditions.urlContains("http://localhost:8080/admin/airlineCompanies/page/1?sortField=airlineID&sortDir=asc&keyword=Test%20Airline"));

	    String currentUrl = driver.getCurrentUrl();
	    assertTrue(currentUrl.equals("http://localhost:8080/admin/airlineCompanies/page/1?sortField=airlineID&sortDir=asc&keyword=Test%20Airline"),
	               "URL dự đoán không đúng. URL hiện tại: " + currentUrl);
	}
    
	@Test
    @Order(2)
	void testUpdateAirline() {
        driver.get("http://localhost:8080/admin/airlineCompanies/edit/41");
        
	    driver.findElement(By.name("airlineName")).sendKeys(" Update");	    
	    driver.findElement(By.cssSelector("input[type='submit']")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    wait.until(ExpectedConditions.urlContains("http://localhost:8080/admin/airlineCompanies/page/1?sortField=airlineID&sortDir=asc&keyword=Test%20Airline%20Update"));

	    String currentUrl = driver.getCurrentUrl();
	    assertTrue(currentUrl.equals("http://localhost:8080/admin/airlineCompanies/page/1?sortField=airlineID&sortDir=asc&keyword=Test%20Airline%20Update"),
	               "URL dự đoán không đúng. URL hiện tại: " + currentUrl);
	}
    
	@Test
    @Order(3)
	void testDelAirline() {
	    driver.get("http://localhost:8080/admin/airlineCompanies/page/2?sortField=airlineID&sortDir=asc");

	    driver.findElement(By.xpath("//a[@href='/admin/airlineCompanies/delete/41']")).click();
	    driver.findElement(By.id("yesButton")).click();

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    wait.until(ExpectedConditions.urlContains("/admin/airlineCompanies"));
	    
	    WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("alert-success")));
	    String messageText = successMessage.getText().trim();
	    
	    assertTrue(messageText.contains("Mã số Hãng hàng không là: 4 has been deleted successfully"),
	               "URL dự đoán không đúng. URL hiện tại: " + messageText);
	}
	
	@AfterEach
	  void tearDown() {
	     if (driver != null) {
	    	 driver.quit();
	    	 }
	     } 
}
