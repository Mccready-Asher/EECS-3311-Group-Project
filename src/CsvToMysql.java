import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CsvToMysql {
	/**
	 * The main method is used to import data from a CSV file into a MySQL database.
	 * The method first defines the file path, database connection details (URL, username, and password), 
	 * and a boolean variable named skip that is used to skip the first line of the CSV file (which is usually 
	 * the header row).
	 * Inside the try block, the method connects to the MySQL database using the provided connection details. 
	 * It then reads the CSV file using a BufferedReader object and inserts each row into the MySQL database 
	 * using a PreparedStatement. The PreparedStatement is used to insert data in a safe and efficient manner,
	 * preventing SQL injection attacks and optimizing query execution.
	 * Lastly, the method executes two SQL queries to remove extra quote marks left from parsing the file, prints 
	 * a success message to the console, and handles any exceptions that may occur during the process.
	 * 
	 * @param args
	 */
    public static void main(String[] args) {
        String csvFilePath = "C:\\Users\\Yousif\\Downloads\\18100205.csv"; //path to the csv file.
        String url = "jdbc:mysql://localhost:8080/proj3311"; // yours will probably on port 3306.
        String username = "root";
        String password = "my-secret-pw"; // this depends on what you set
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
