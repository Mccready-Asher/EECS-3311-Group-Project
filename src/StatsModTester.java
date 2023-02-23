import javax.swing.JFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.data.time.Month;
import org.jfree.data.time.TimePeriodValues;

import java.util.Random;


public class StatsModTester {

	public static void main(String[] args) {  //this is way too much effort for this but i wanted it to look cool.
		GraphMod gm = new GraphMod();
		StatsMod sm = new StatsMod();
		Random rand = new Random();
		
		JFrame frame1 = new JFrame();
		JFrame frame2 = new JFrame();
		
		
		TimePeriodValues series1 = new TimePeriodValues("series 1");
		TimePeriodValues series2 = new TimePeriodValues("series 2");
		
		int year = 1922;
		int month = 1;
		
		int prevsr1 = 0;
		int prevsr2 = 0;
		
		while(year<2023) {
			series1.add(new Month(month, year), prevsr1);
			series2.add(new Month(month, year), prevsr2); //the specifics of the data's pattern can be changed here, but i have these settings so the result is at least interesting.
			
			prevsr1 += (rand.nextInt(7)-3);
			//prevsr2 += (rand.nextInt(100)-50);
			prevsr2 += (rand.nextInt(7)-3);
			
			
			month++;
			if(month>12) {
				month = 1;
				year++;
				prevsr2 = prevsr1+(rand.nextInt(7)-3);
			}
		}

		
		
		frame1.setSize(900,600);
		frame1.setTitle("Stats Module Test");
		frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame1.add(gm.getLineGraph("series 1", "year", "# of beeps", series1));
		frame1.setVisible(true);
		
		frame2.setSize(900,600);
		frame2.setTitle("Stats Module Test");
		frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame2.add(gm.getLineGraph("series 2", "year", "# of boops", series2));
		frame2.setVisible(true);
		
		
		System.out.println(sm.timeSeriesTTest(series1,series2));
	}

}
