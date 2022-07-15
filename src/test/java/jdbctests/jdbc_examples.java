package jdbctests;

import org.junit.jupiter.api.Test;

import java.sql.*;

public class jdbc_examples {

    String dbUrl = "jdbc:oracle:thin:@52.87.170.214:1521:XE";
    String dbUsername = "hr";
    String dbPassword = "hr";

    @Test
    public void test1() throws SQLException {
        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM departments");

        //move to first row
        //resultSet.next();

        //System.out.println(resultSet.getString(2));

        //display departments in 10 -administration -200-1700 format
        while(resultSet.next()) {
            System.out.println(resultSet.getInt(1) + " - " + resultSet.getString(2) + " - " +
                    resultSet.getString(3) + " - " + resultSet.getInt(4));
        }

        System.out.println("---------------");

        resultSet = statement.executeQuery("select * from regions");

        while(resultSet.next()) {
            System.out.println(resultSet.getInt(1) + " - " + resultSet.getString(2));
        }



        resultSet.close();
        statement.close();
        connection.close();

    }


    @Test
    public void test2() throws SQLException{
        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery("SELECT * FROM departments");

        //how to find how many rows we have for the query

       /* while (resultSet.next()){
            System.out.println(resultSet.getRow());
        }

        */

        resultSet.last();
        int rowCount = resultSet.getRow();
        System.out.println("rowCount = " + rowCount);

        resultSet = statement.executeQuery("SELECT * FROM employees");
        resultSet.last();
        int rowCount2 = resultSet.getRow();
        System.out.println("rowCount2 = " + rowCount2);

        resultSet.beforeFirst();

        while (resultSet.next()){
            System.out.println(resultSet.getString(2));
        }



        resultSet.close();
        statement.close();
        connection.close();
    }

    @Test
    public void test3() throws SQLException{
        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery("SELECT * FROM employees");

       //get the database related data inside the dbMetadata object
        DatabaseMetaData dbMetaData = connection.getMetaData();
        System.out.println("dbMetaData.getUserName() = " + dbMetaData.getUserName());
        System.out.println("dbMetaData.getDatabaseProductName() = " + dbMetaData.getDatabaseProductName());
        System.out.println("dbMetaData.getDatabaseProductVersion() = " + dbMetaData.getDatabaseProductVersion());
        System.out.println("dbMetaData.getDriverName() = " + dbMetaData.getDriverName());
        System.out.println("dbMetaData.getDriverVersion() = " + dbMetaData.getDriverVersion());

        //get the resultsetmetadata
        ResultSetMetaData rsMetadata = resultSet.getMetaData();

        int colCount = rsMetadata.getColumnCount();
        System.out.println(colCount);
        System.out.println(rsMetadata.getColumnName(1));
        System.out.println(rsMetadata.getColumnName(2));

        System.out.println("------------------");
        //print all column names
        for (int i = 1; i <= colCount ; i++) {
            System.out.println(rsMetadata.getColumnName(i));
        }


        //close connection
        resultSet.close();
        statement.close();
        connection.close();
    }


}
