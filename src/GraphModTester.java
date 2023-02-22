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
