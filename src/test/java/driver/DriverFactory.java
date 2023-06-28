package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.ITestContext;
import org.testng.Reporter;

public class DriverFactory {
    private enum browsers {
        EXPLORER, FIREFOX, CHROME
    }

    private static final String driverPath = System.getProperty("user.dir")+"\\src\\test\\resources\\browserDriver\\";
    private static final String firefoxPath = "C:\\Program Files\\Mozilla Firefox\\";
    public static WebDriver LevantarBrowser(WebDriver driver, ITestContext context)
    {
        String browser = context.getCurrentXmlTest().getParameter("Browser");
        String URL = context.getCurrentXmlTest().getParameter("url");
        switch (browsers.valueOf(browser)) {
            case CHROME: // Using WebDriver
            {
                Reporter.log("CHROME Browser Selected");
                System.setProperty("webdriver.chrome.driver", driverPath+"chromedriver.exe");
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--remote-allow-origins=*");
                driver = new ChromeDriver(options);
                break;
            }
            case FIREFOX:// Using WebDriver
            {
                Reporter.log("FIREFOX Browser Selected");
                System.setProperty("webdriver.gecko.driver", driverPath+"geckodriver.exe");
                FirefoxOptions options = new FirefoxOptions();
                options.setBinary(firefoxPath+"firefox.exe");
                driver = new FirefoxDriver(options);
                break;
            }
            default:
                Reporter.log("No selecciono ningun browser correcto, se le asignara Chrome");
                System.setProperty("webdriver.chrome.driver", driverPath+"chromedriver.exe");
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--remote-allow-origins=*");
                driver = new ChromeDriver(options);
                break;
        }
        driver.manage().window().maximize();
        driver.get(URL);
        return driver;
    }
    public static void FinalizarBrowser(WebDriver driver) {
        //Utiles.escribir("Cerrando el browser");
        driver.quit();
        driver = null;
    }
}


