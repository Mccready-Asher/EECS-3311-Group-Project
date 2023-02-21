import javax.swing.JFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.data.xy.XYSeries;
import java.util.Scanner;


public class GraphModTester {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		GraphMod gm = new GraphMod();
		JFrame frame = new JFrame();
		
		System.out.println("What would you like your graph's title to be?");
		String title = sc.nextLine();
		
		System.out.println("What would you like the X axis title to be?");
		String xAxis = sc.nextLine();
		
		System.out.println("What would you like the Y axis title to be?");
		String yAxis = sc.nextLine();
		
		System.out.println("What would you your data's label to be?");
		String dataname = sc.nextLine();
		
		XYSeries series = new XYSeries(dataname);
		
		boolean wantmoredata = true;
		
		System.out.println("Now for the data, ints only please");
		
		while(wantmoredata) {
			
			System.out.println("X Value:");
			int x = sc.nextInt();
			
			System.out.println("Y Value:");
			int y = sc.nextInt();
			
			series.add(x,y);
			
			System.out.println("Would you like to add another datapoint? y/n");
			char r = sc.next().charAt(0);
			
			wantmoredata=r=='y'||r=='Y';
			
		}
		System.out.println("Generating graph");
		
		frame.setSize(900,600);
		frame.setTitle("Graph Module Test");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(gm.getLineGraph(title, xAxis, yAxis, series));
		frame.setVisible(true);
		
		

	}

}
