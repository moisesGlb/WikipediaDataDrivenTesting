package Utilities;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Reporter;

import java.io.*;


public class ExcelHandler {

    private static XSSFSheet ExcelWSheet;
    private static XSSFWorkbook ExcelWBook;
    private static XSSFCell Cell;
    private static XSSFRow Row;


    public static void setExcelFile(String Path, String SheetName) throws Exception {
        try {
            Reporter.log("Abrir el archivo excel");
            FileInputStream ExcelFile = new FileInputStream(Path);
            Reporter.log("Acceder a la hoja del excel indicada para obtener datos");
            ExcelWBook = new XSSFWorkbook(ExcelFile);
            ExcelWSheet = ExcelWBook.getSheet(SheetName);
        } catch (Exception e) {
            throw (e); } }

    public static String getCellData(int RowNum, int ColNum)
            throws Exception {
        try {
            Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
            String CellData = Cell.getStringCellValue();
            return CellData;
        } catch (Exception e) {
            return "no se pudo obtener dato";
        }
    }

    public static Object[][] getTableArray(String FilePath, String
            SheetName, int iTestCaseRow, int columnas)
            throws Exception
    {
        String[][] tabArray = null;
        try {
            setExcelFile(FilePath, SheetName);
            int startCol = 0, ci = 0, cj = 0, totalRows = iTestCaseRow,
                    totalCols = columnas;
            tabArray = new String[totalRows][totalCols];
            for (int ley = 1; ley <= iTestCaseRow; ley++)
            {
                cj = 0;
                for (int j = startCol; j < totalCols; j++, cj++)
                {
                    tabArray[ci][cj] = getCellData(ley, j);
                    System.out.println(tabArray[ci][cj]);
                }
                ci++;
            }}catch (FileNotFoundException e)
        {
            Reporter.log("No se puedo abrir el excel");
            System.out.println("No se pudo abrir el excel");
            e.printStackTrace();
        }
        catch (IOException e)
        {
            Reporter.log("No se puedo abrir el excel");
            System.out.println("No se pudo abrir el excel");
            e.printStackTrace();
        }
        return (tabArray);
    }

    //** Metodos para determinar cuantas filas se enviaron **//
    public static int getRowUsed() throws Exception {
        try {
            int RowCount = ExcelWSheet.getLastRowNum();
            return RowCount;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw (e);
        }
    }

}
