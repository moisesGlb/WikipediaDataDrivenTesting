package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import java.time.Duration;

public class WikiResultadoPage {
    WebDriver driver;

    public WikiResultadoPage(WebDriver ldriver)
    {
        this.driver=ldriver;
    }
    @FindBy (id = "firstHeading")
    private WebElement lblTitulo;

    public String ObtenerTitulo ( )
    {
        Reporter.log("Localizar y comprobar que el titulo este disponible");
        return lblTitulo.getText();
    }

    public String ObtenerTituloDriver( String dato) throws Exception
    {
        WebDriverWait wait = new WebDriverWait (driver, Duration.ofSeconds(66));
        wait.until(ExpectedConditions.titleContains(dato));
        return driver.getTitle().toString();
    }

}