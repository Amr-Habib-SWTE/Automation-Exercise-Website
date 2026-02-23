package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.time.Duration;

//“I use a DriverFactory with ThreadLocal<WebDriver> to support parallel execution safely.
//Each test thread gets its own browser instance, which prevents cross-test interference and makes the framework scalable.”

public class DriverFactory {
//  Correct approach for parallel TestNG/JUnit execution
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static void initializeDriver() {
        ChromeOptions options = new ChromeOptions();
//      Solves common ChromeDriver 111+ issues.
        options.addArguments("--remote-allow-origins=*");
        driver.set(new ChromeDriver(options));
        getDriver().manage().window().maximize();
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}