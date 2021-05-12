package com.caremetx;

import TestScenarios.DBScenarios.SrTypeListValidation;
import Utils.ExcelReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception{
	// write your code here
        ExcelReader excelReader = new ExcelReader();

        excelReader.readExcel("Add_Patient");

        System.out.println();
    }
}
