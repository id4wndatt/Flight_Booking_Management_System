package com.dnd.fbs;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UserManagementTest {
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
	void testUpdateRoleUser() {
	    driver.get("http://localhost:8080/admin/updateUser/37");
	    
        WebElement roleSelect = driver.findElement(By.name("role"));
        Select select = new Select(roleSelect);
        select.selectByVisibleText("Admin");

	    driver.findElement(By.cssSelector("button[type='submit']")).click();
	    
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    wait.until(ExpectedConditions.urlContains("http://localhost:8080/admin/userList"));

	    String currentUrl = driver.getCurrentUrl();
	    assertTrue(currentUrl.equals("http://localhost:8080/admin/userList"),
	               "Expected URL not matched. Actual: " + currentUrl);
	}
	
	@AfterEach
	  void tearDown() {
	     if (driver != null) {
	    	 driver.quit();
	    	 }
	     } 
}
