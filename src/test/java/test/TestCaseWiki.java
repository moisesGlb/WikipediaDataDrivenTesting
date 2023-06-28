package test;

import Utilities.ExcelHandler;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import driver.DriverFactory;
import pages.WikiHomePage;
import pages.WikiResultadoPage;


public class TestCaseWiki {
    WebDriver driver;

    @DataProvider(name = "SearchWikipedia")
    public Object[][] DatosNuevos() throws Exception {
        String excelPath = System.getProperty("user.dir") + "\\src\\test\\resources\\excel\\testData.xlsx";
        String hoja = "testData";
        ExcelHandler.setExcelFile(excelPath, hoja);
        int iTestCaseRow = ExcelHandler.getRowUsed();
        return ExcelHandler.getTableArray(excelPath, hoja, iTestCaseRow, 3);
    }

    @BeforeMethod
    public void abrirBrowser(ITestContext context) {
        driver = DriverFactory.LevantarBrowser(driver, context);
    }

    @Test(dataProvider = "SearchWikipedia", description = "Validar y verificar que Wikipedia Home Page contiene el campo de busqueda")
    public void validarCajaTextoNuevo(String varBuscar, String resultado, String titulo) throws Exception {
        WikiHomePage wikihomepage = PageFactory.initElements(driver, WikiHomePage.class);
        WikiResultadoPage wikiRdopage = PageFactory.initElements(driver, WikiResultadoPage.class);
        wikihomepage.IngresarDatoCajaBusqueda(varBuscar);
        Reporter.log("Validar que el titulo sea " + resultado);
        Assert.assertTrue((driver.getCurrentUrl().contains(resultado)), "No contiene: " + driver.getCurrentUrl().toString());
        Reporter.log("Validar que el titulo sea " + titulo);
        Assert.assertTrue((wikiRdopage.ObtenerTitulo().contains(titulo)), "el valor " + wikiRdopage.ObtenerTitulo().toString() + " no se encontro en el titulo");
    }

    @AfterMethod
    public void CerrarBrowser() {
        Reporter.log("Cerrar Browser");
        DriverFactory.FinalizarBrowser(driver);
    }
}
