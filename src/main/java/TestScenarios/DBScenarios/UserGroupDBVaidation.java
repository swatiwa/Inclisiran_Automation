package TestScenarios.DBScenarios;

import TestScenarios.BaseTestClass;
import Utils.DBUtils;

import java.sql.*;

public class UserGroupDBVaidation extends BaseTestClass {

        public String getMostRecentPatientCreated() throws Exception{ //valueOne
            DBUtils utils = new DBUtils();
            utils.userName = prop.getProperty("sbandara");
            utils.password = prop.getProperty("password");
            utils.connString = prop.getProperty("connectionString");

            System.out.println(utils.connString);
            utils.query = "SELECT TOP 1 C.FirstName FROM [summa].[dbo].[ServiceRequests] as SR JOIN [Summa].[dbo].[ContactAddresses] as CA ON SR.ContactAddressID = CA.ContactAddressID AND CA.Deleted = 0 AND CA.Rank = 1 LEFT JOIN [Summa].[dbo].[Contacts] as C ON SR.ContactID = C.ContactID Where C.ProgramID =250 Order by C.Created DESC";
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
                    actualValue= rs.getString("FirstName");

                    System.out.println("FIrst NAme : "+actualValue);
                }
            } catch (SQLException e ) {
                System.out.println("Exception found");
            } finally {
                if (stmt != null) { stmt.close(); }
            }
            return actualValue;
        }

    }
