import javax.swing.JFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.data.time.Month;
import org.jfree.data.time.TimePeriodValues;
import org.jfree.data.xy.XYSeries;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;


public class GraphModTester {




	/**
	 * This code is a Java program that creates a line graph using data from the MySQL database table and user input.
	 * The program starts by creating a Scanner object to read user input, and a GraphMod object which contains methods 
	 * for generating different types of graphs. It also creates a JFrame object, which is a window that will display the graph.
	 * The user is prompted to enter a title for the graph, labels for the X and Y axes, and a label for the data. Then, a TimePeriodValues 
	 * object is created to store the data.
	 * Next, the program connects to a MySQL database using the DriverManager class, and executes a query to retrieve data from a table named range18100205.
	 * The query selects the average value of a column named VALUE for each month in the table.
	 * The program then loops through the results of the query and adds each data point to the TimePeriodValues object. The X values are represented by Month
	 * objects, and the Y values are represented by doubles.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		GraphMod gm = new GraphMod();
		JFrame frame = new JFrame();
		String url = "jdbc:mysql://localhost:8080/proj3311"; // yours will probably on port 3306.
        String username = "root";
        String password = "my-secret-pw"; // this depends on what you set


		System.out.println("What would you like your graph's title to be?");
		String title = sc.nextLine();
		
		System.out.println("What would you like the X axis title to be?");
		String xAxis = sc.nextLine();
		
		System.out.println("What would you like the Y axis title to be?");
		String yAxis = sc.nextLine();
		
		System.out.println("What would you your data's label to be?");
		String dataname = sc.nextLine();
		sc.close();
		TimePeriodValues series = new TimePeriodValues(dataname);
		
//		boolean wantmoredata = true;

        try (Connection connection = DriverManager.getConnection(url, username, password)) { // try connecting
        	// query to group averages for months
        	ResultSet rs = connection.createStatement().executeQuery("SELECT REF_DATE, AVG(CASE WHEN VALUE <> '' THEN VALUE END) as avg_value\r\n"
        			+ "FROM range18100205\r\n"
        			+ "GROUP BY REF_DATE;");
            // go through the result and add to the series
        	while (rs.next()) {
                String refDate = rs.getString("REF_DATE");
                double value = rs.getDouble("avg_value");
                String[] moYr = refDate.split("-");
                // Add the data point to the series
                series.add(new Month(Integer.parseInt(moYr[1]), Integer.parseInt(moYr[0])), value);
            }

        } catch (SQLException e) {
        	e.printStackTrace();
        } catch (Exception e) {
        	e.printStackTrace();
        }
//		System.out.println("Now for the data, ints only please");
		
//		while(wantmoredata) {
//			
//			System.out.println("X Value:");
//			int x = sc.nextInt();
//			
//			System.out.println("Y Value:");
//			int y = sc.nextInt();
//			
//			series.add(x,y);
//			
//			System.out.println("Would you like to add another datapoint? y/n");
//			char r = sc.next().charAt(0);
//			
//			wantmoredata=r=='y'||r=='Y';
//			
//		}
		System.out.println("Generating graph");
		
		frame.setSize(900,600);
		frame.setTitle("Graph Module Test");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(gm.getLineGraph(title, xAxis, yAxis, series));
		frame.setVisible(true);
		
		

	}

}
