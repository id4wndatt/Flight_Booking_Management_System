package com.dnd.fbs;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UserInforTest {
	private WebDriver driver;
	 
	 @BeforeEach
	 void setUp() {
		 System.setProperty("webdriver.edge.driver", "D:\\test_frontend_fbs\\BanVeMayBay-main\\egdedriver\\msedgedriver.exe");

	     driver = new EdgeDriver();
	     driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	     
	     driver.get("http://localhost:8080/account");
	     driver.findElement(By.id("username")).sendKeys("vietthinh01");
	     driver.findElement(By.id("password")).sendKeys("i1504203sS.");
	     driver.findElement(By.id("btnLogin")).click();
	     }
	 
	 @Test
	 void testUpdateInforSuccess() {
		 driver.get("http://localhost:8080/account/information/3");
		 
		 driver.findElement(By.name("fullname")).sendKeys("Nguyen Viet Thinh");
	     driver.findElement(By.name("sex")).sendKeys("Nam");
	     driver.findElement(By.name("birthday")).sendKeys("01/01/2003");
	     driver.findElement(By.name("address")).sendKeys("Bac Giang");
	     driver.findElement(By.id("cccd")).sendKeys("1234567890");
	     driver.findElement(By.name("nationality")).sendKeys("Viet Nam");
	     driver.findElement(By.name("phone")).sendKeys("0123456789");
	     
	     WebElement button = driver.findElement(By.id("btnInfor"));
	     ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
	     
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    wait.until(ExpectedConditions.elementToBeClickable(By.id("btnInfor")));

	     WebElement swalText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("swal2-popup")));
	     String successMessage = swalText.getText().trim();
	     assertTrue(successMessage.contains("Lưu thông tin thành công !"), "Thông báo dự đoán thành công không chính xác. Thông báo thật sự: " + successMessage);
	 }
	 
	 @AfterEach
	  void tearDown() {
	     if (driver != null) {
	    	 driver.quit();
	    	 }
	     }
}
