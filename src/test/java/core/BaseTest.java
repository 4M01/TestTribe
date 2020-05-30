package core;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import java.io.FileInputStream;
import java.util.Properties;

public class BaseTest {
    public WebDriver driver;
    private Properties Config;

    @BeforeSuite
    public void suiteSetUp() {
        String configFilePath  =  System.getProperty("user.dir")+ "/src/test/resources/config.properties";
        Config = new Properties();
        try {
            Config.load(new FileInputStream(configFilePath));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @BeforeClass
    public void beforeClass() {
        if(System.getProperty("browserName") == null){
            String browserName = Config.getProperty("browserName");
        }
        DriverManagerFactory driverFactory = new DriverManagerFactory();
        driverFactory.initializeDriver("chrome");
    }

    @AfterClass
    public void afterClass() throws Exception {
        DriverManagerFactory driverFactory = new DriverManagerFactory();
        try{
            if(driverFactory.getWebDriver()!=null)
                driverFactory.getWebDriver().quit();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
