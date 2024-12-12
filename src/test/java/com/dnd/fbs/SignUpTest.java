package com.dnd.fbs;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SignUpTest {
    private WebDriver driver;

    @BeforeEach
    void setUp() {
        System.setProperty("webdriver.edge.driver", "D:\\test_frontend_fbs\\BanVeMayBay-main\\egdedriver\\msedgedriver.exe");
        driver = new EdgeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    @Order(1)
    void testSignUpSuccess() {
        driver.get("http://localhost:8080/account/signup");

        driver.findElement(By.id("username")).sendKeys("nguyenhuong");
        driver.findElement(By.id("password")).sendKeys("i230903sS.");
        driver.findElement(By.id("refill_password")).sendKeys("i230903sS.");
        driver.findElement(By.id("email")).sendKeys("nguyenhuong2309@gmail.com");
        driver.findElement(By.id("btnSignUp")).click();
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(By.id("btnInfor")));


        WebElement swalText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("swal2-popup")));
        String successMessage = swalText.getText().trim();
        
        assertTrue(successMessage.contains("Đăng ký thành công !"), "Thông báo dự đoán thành công không chính xác. Thông báo thật sự: " + successMessage);
    }
    
    @Test
    @Order(2)
    void testSignUpFalseUsername() {
        driver.get("http://localhost:8080/account/signup");

        driver.findElement(By.id("username")).sendKeys("nguyenhuong"); // Tên người dùng đã tồn tại
        driver.findElement(By.id("password")).sendKeys("i230903sS.");
        driver.findElement(By.id("refill_password")).sendKeys("i230903sS.");
        driver.findElement(By.id("email")).sendKeys("nguyenhuong2309@gmail.com");
        driver.findElement(By.id("btnSignUp")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement swalText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("swal2-popup")));
        String errorMessage = swalText.getText().trim();
        
        assertTrue(errorMessage.contains("Tên người dùng đã được sử dụng!"), 
                "hông báo dự đoán lỗi không chính xác. Thông báo thật sự: " + errorMessage);
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
