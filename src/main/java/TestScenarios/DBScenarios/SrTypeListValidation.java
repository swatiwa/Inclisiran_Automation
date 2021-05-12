package TestScenarios.DBScenarios;

import TestScenarios.BaseTestClass;
import Utils.DBUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SrTypeListValidation extends BaseTestClass {

    public static List<String> getSRTypeList() throws Exception{ //valueOne
        DBUtils utils = new DBUtils();
        List<String> srValues = new ArrayList<String>();

//        utils.userName = prop.getProperty("username");
//        utils.password = prop.getProperty("password");
//        utils.connString = prop.getProperty("connectionString");

        utils.userName = "sbandara";
        utils.password = "pf8Fuilpvif1Q3Ni";
        utils.connString = "Data Source=192.168.102.119;Initial Catalog=Summa;User Id=sbandara;Password=pf8Fuilpvif1Q3Ni;";

        System.out.println(utils.connString);
        utils.query = "SELECT SR_TypeFullDesc FROM summa.dbo.SR_TypeList Where ProgramID = 250 And Deleted = 0 AND Active = 1";
        //utils.query = "USE Summa";
        ///col1|Col2|COl3
        ///aaa|bbb|ccc

        Statement stmt = null;

        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String actualValue = null;
        try {


            String url = "jdbc:sqlserver://192.168.102.119;";
            //Connection con = DriverManager.getConnection(utils.connString, utils.userName, utils.password);
            //Connection con = DriverManager.getConnection("jdbc:microsoft:sqlserver://192.168.102.120:1433;DatabaseName=SummaVoyager-UATEnv", utils.userName, utils.password);
            //Connection con = DriverManager.getConnection("jdbc:sqlserver://192.168.102.120:1433;databaseName=master;user=sbandara;password=pf8Fuilpvif1Q3Ni");

            Connection con = DriverManager.getConnection(url, utils.userName, utils.password);



            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(utils.query);

            while (rs.next()) {
                actualValue= rs.getString("SR_TypeFullDesc");
                srValues.add(actualValue);
            }
        } catch (SQLException e ) {
            System.out.println("Exception found");
        } finally {
            if (stmt != null) { stmt.close(); }
        }
        return srValues;
    }

}
