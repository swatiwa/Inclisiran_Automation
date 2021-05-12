package Utils;

import TestActions.CreateNewContext;
import TestFramework.Pages.Home.Portlet;
import TestFramework.Pages.Index;
import TestFramework.Pages.SR.Create;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;


public class ExcelReader {

    public static HashMap<String,String> testData = new HashMap<>();

    public  void readExcel(String sheetName) throws IOException {
        testData.clear();
        String keyName = "";
        String value = "";
        FileInputStream fis = new FileInputStream(new File("src\\main\\java\\TestScenarios\\TestDataFiles\\HCPTestDataFiles\\HCP_Requests.xls"));

        //creating workbook instance that refers to .xls file
        HSSFWorkbook wb = new HSSFWorkbook(fis);

        //creating a Sheet object to retrieve the object
        HSSFSheet sheet = wb.getSheet(sheetName);
        FormulaEvaluator formulaEvaluator = wb.getCreationHelper().createFormulaEvaluator();

        for (Row row : sheet)    //iteration over row using for each loop
        {
            int rowcounter = 1;
            int counter = 0;

            for (Cell cell : row)    //iteration over cell using for each loop
            {
                if (counter == 0) {
                    keyName = cell.getStringCellValue();
                }
                if (counter == 1) {
                    value = cell.getStringCellValue();
                }
                counter = counter + 1;
            }
            System.out.print("KeyName is: '" + keyName + "', and the Value is: '" + value + "'\n");
            testData.put(keyName, value);

        }
    }

}


