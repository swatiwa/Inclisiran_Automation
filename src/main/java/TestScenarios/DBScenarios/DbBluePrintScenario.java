package TestScenarios.DBScenarios;

import TestScenarios.BaseTestClass;
import Utils.DBUtils;
import org.testng.Assert;

import java.sql.*;

public class DbBluePrintScenario extends BaseTestClass {

    public void tempDbScenario(String requestId, String patientData) throws Exception{ //valueOne
        DBUtils utils = new DBUtils();
        utils.userName = prop.getProperty("sbandara");
        utils.password = prop.getProperty("password");
        utils.connString = prop.getProperty("connectionString");

        System.out.println(utils.connString);
        utils.query = "Select * from [SummaVoyager-UATEnv].dbo.ServiceRequests where ServiceRequestID='"+requestId+"'";
        ///col1|Col2|COl3
        ///aaa|bbb|ccc

        Statement stmt = null;

        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String actualValue = null;
        try {
            String url = "jdbc:sqlserver://192.168.102.120;databaseName=SummaVoyager-UATEnv";
            //Connection con = DriverManager.getConnection(utils.connString, utils.userName, utils.password);
            //Connection con = DriverManager.getConnection("jdbc:microsoft:sqlserver://192.168.102.120:1433;DatabaseName=SummaVoyager-UATEnv", utils.userName, utils.password);
            //Connection con = DriverManager.getConnection("jdbc:sqlserver://192.168.102.120:1433;databaseName=master;user=sbandara;password=pf8Fuilpvif1Q3Ni");

            Connection con = DriverManager.getConnection(url, utils.userName, utils.password);

            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(utils.query);

            while (rs.next()) {
                actualValue= rs.getString("PatientID");

                System.out.println(actualValue);
            }
        } catch (SQLException e ) {
           System.out.println("Exception found");
        } finally {
            if (stmt != null) { stmt.close(); }
        }
        Assert.assertEquals(patientData, actualValue);
    }


}
