package core;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;


public class DriverManagerFactory {
    public WebDriver driver;

    public WebDriver getWebDriver() {
        return driver;
    }

    public void initializeDriver(String browserName) {
        switch (browserName.toLowerCase()){
            case "chrome":
                setUpChromeDriver();
                break;
            case "firefox":
                setUpFirefoxDriver();
                break;
        }
    }

    private void setUpFirefoxDriver() {
        WebDriverManager.firefoxdriver().setup();
        this.driver =  new FirefoxDriver();
    }

    private void setUpChromeDriver() {
        WebDriverManager.chromedriver().setup();
        DesiredCapabilities capabilities = new DesiredCapabilities();
        ChromeOptions options = new ChromeOptions();
        capabilities.setCapability("platformName", "WINDOWS");
        options.addArguments("start-maximized");
        options.addArguments("--disable-infobars");
        options.addArguments("--dns-prefetch-disable");
        options.merge(capabilities);
        driver = new ChromeDriver(options);
    }

}
