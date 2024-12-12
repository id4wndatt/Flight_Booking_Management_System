package com.dnd.fbs;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTest {
	 private WebDriver driver;
	 
	 @BeforeEach
	 void setUp() {
		 System.setProperty("webdriver.edge.driver", "D:\\test_frontend_fbs\\BanVeMayBay-main\\egdedriver\\msedgedriver.exe");

	     driver = new EdgeDriver();
	     driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	     }
	 
	 @Test
	 void testLoginFalsePassword() {
	     driver.get("http://localhost:8080/account");

	     driver.findElement(By.id("username")).sendKeys("damdat");
	     driver.findElement(By.id("password")).sendKeys("123123");
	     driver.findElement(By.id("btnLogin")).click();
	        
	     WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	     wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("swal2-popup")));

	     WebElement swalText = driver.findElement(By.className("swal2-html-container"));
	     String errorMessage = swalText.getText().trim();
	     assertTrue(errorMessage.contains("Mật khẩu không chính xác !"), "Thông báo dự đoán lỗi không chính xác. Thông báo thật sự: " + errorMessage);
	     }
	 
	 @Test
	 void testLoginFailure() {
	     driver.get("http://localhost:8080/account");

	     driver.findElement(By.id("username")).sendKeys("damdatt");
	     driver.findElement(By.id("password")).sendKeys("123123");
	     driver.findElement(By.id("btnLogin")).click();
	        
	     WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	     wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("swal2-popup")));

	     WebElement swalText = driver.findElement(By.className("swal2-html-container"));
	     String errorMessage = swalText.getText().trim();
	     assertTrue(errorMessage.contains("Tài khoản này không tồn tại !"), "Thông báo dự đoán lỗi không chính xác. Thông báo thật sự: " + errorMessage);
	     }
	 
	 @Test
	 void testLoginSuccess() {
	     driver.get("http://localhost:8080/account");

	     driver.findElement(By.id("username")).sendKeys("damdat");
	     driver.findElement(By.id("password")).sendKeys("i1504203sS.");
	     driver.findElement(By.id("btnLogin")).click();
	     
	     WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	     wait.until(ExpectedConditions.urlToBe("http://localhost:8080/account/login"));

	      assertTrue(driver.getCurrentUrl().equals("http://localhost:8080/account/login"), "Đăng nhập thất bại, URL dự đoán 'http://localhost:8080/account/login', URL hiện tại " + driver.getCurrentUrl());
	     }
	 
	 @Test
	 void testLoginAdminSuccess() {
		 driver.get("http://localhost:8080/admin");

	     driver.findElement(By.id("username")).sendKeys("admin");
	     driver.findElement(By.id("password")).sendKeys("i1504203sS.");
	     driver.findElement(By.id("btnLogin")).click();
	     
	     WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	     wait.until(ExpectedConditions.urlContains("http://localhost:8080/admin/login"));

	     String currentUrl = driver.getCurrentUrl();
	     assertTrue(currentUrl.startsWith("http://localhost:8080/admin/login"),
	         "Đăng nhập lỗi, URL đăng nhập thành công 'http://localhost:8080/admin/login', URL hiện tại: " + currentUrl);
	 }
	 
	 @Test
	 void testLoginAdminFailure() {
		 driver.get("http://localhost:8080/admin");

	     driver.findElement(By.id("username")).sendKeys("adminn");
	     driver.findElement(By.id("password")).sendKeys("i1504203sS.");
	     driver.findElement(By.id("btnLogin")).click();
	     
	     WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	     wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("w3-panel")));

	     WebElement errorDiv = driver.findElement(By.className("w3-panel"));
	     String errorMessage = errorDiv.getText().trim();

	     assertTrue(errorMessage.contains("Tài khoản quản trị không tồn tại"),
	         "Thông báo dự đoán lỗi không chính xác. Thông báo hiện tại: " + errorMessage);
	 }
	 
	 @Test
	 void testLoginAdminRoleFailure() {
		 driver.get("http://localhost:8080/admin");

	     driver.findElement(By.id("username")).sendKeys("damdat");
	     driver.findElement(By.id("password")).sendKeys("i1504203sS.");
	     driver.findElement(By.id("btnLogin")).click();
	     
	     WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	     wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("w3-panel")));

	     WebElement errorDiv = driver.findElement(By.className("w3-panel"));
	     String errorMessage = errorDiv.getText().trim();

	     assertTrue(errorMessage.contains("Truy cập bị từ chối"),
	         "Thông báo dự đoán lỗi không chính xác. Thông báo hiện tại: " + errorMessage);
	 }
	 
	 @Test
	 void testLoginAdminFalsePassword() {
		 driver.get("http://localhost:8080/admin");

	     driver.findElement(By.id("username")).sendKeys("admin");
	     driver.findElement(By.id("password")).sendKeys("i1504203s");
	     driver.findElement(By.id("btnLogin")).click();
	     
	     WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	     wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("w3-panel")));

	     WebElement errorDiv = driver.findElement(By.className("w3-panel"));
	     String errorMessage = errorDiv.getText().trim();

	     assertTrue(errorMessage.contains("Mật khẩu không chính xác"),
	         "Thông báo dự đoán lỗi không chính xác. Thông báo hiện tại: " + errorMessage);
	 }
	  
	  @AfterEach
	  void tearDown() {
	     if (driver != null) {
	    	 driver.quit();
	    	 }
	     }
}
