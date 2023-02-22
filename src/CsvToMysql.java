import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CsvToMysql {

    public static void main(String[] args) {
        String csvFilePath = "C:\\Users\\Yousif\\Downloads\\18100205.csv"; //path to the csv file.
        String url = "jdbc:mysql://localhost:8080/proj3311"; // yours will probably on port 3306.
        String username = "root";
        String password = "my-secret-pw";
        boolean skip = true; 
        
        try (Connection connection = DriverManager.getConnection(url, username, password)) { // try connecting
            BufferedReader reader = new BufferedReader(new FileReader(csvFilePath));
            String line;
            //create the table
            connection.createStatement().execute("CREATE TABLE range18100205\r\n"
            		+ "(\r\n"
            		+ "    REF_DATE VARCHAR(512),\r\n"
            		+ "    GEO VARCHAR(512),\r\n"
            		+ "    DGUID VARCHAR(512),\r\n"
            		+ "    New_housing_price_indexes VARCHAR(512),\r\n"
            		+ "    UOM	VARCHAR(512),\r\n"
            		+ "    UOM_ID INT,\r\n"
            		+ "    SCALAR_FACTOR VARCHAR(512),\r\n"
            		+ "    SCALAR_ID VARCHAR(512),\r\n"
            		+ "    VECTOR VARCHAR(512),\r\n"
            		+ "    COORDINATE DOUBLE,\r\n"
            		+ "    VALUE VARCHAR(512),\r\n"
            		+ "    STATUS VARCHAR(512),\r\n"
            		+ "    SYMBOL VARCHAR(512),\r\n"
            		+ "    `TERMINATED` VARCHAR(512),\r\n"
            		+ "    DECIMALS VARCHAR(512) );");
            
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO range18100205 VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            while ((line = reader.readLine()) != null) {
            	if (!skip) {					
            		String[] data = line.split("\",\""); // this delimiter won't be the same for all CSVs.
            		preparedStatement.setString(1, data[0]);
            		preparedStatement.setString(2, data[1]);
            		preparedStatement.setString(3, data[2]);
            		preparedStatement.setString(4, data[3]);
            		preparedStatement.setString(5, data[4]);
            		preparedStatement.setInt(6, Integer.parseInt(data[5]));
            		preparedStatement.setString(7, data[6]);
            		preparedStatement.setString(8, data[7]);
            		preparedStatement.setString(9, data[8]);
            		preparedStatement.setDouble(10, Double.parseDouble(data[9]));
            		preparedStatement.setString(11, data[10]);
            		preparedStatement.setString(12, data[11]);
            		preparedStatement.setString(13, data[12]);
            		preparedStatement.setString(14, data[13]);
            		preparedStatement.setString(15, data[14]);
            		preparedStatement.executeUpdate();
				}
            	skip = false; // to skip first line
            }
            reader.close();
            // queries to remove extra quote marks left from parsing the file.
            connection.createStatement().execute("UPDATE range18100205 SET REF_DATE = RIGHT(REF_DATE, LENGTH(REF_DATE) - 1);");
            connection.createStatement().execute("UPDATE range18100205 SET  DECIMALS = LEFT(DECIMALS, LENGTH(DECIMALS) - 1);");

            System.out.println("Data imported successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}