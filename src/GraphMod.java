import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.time.TimePeriodValues;
import org.jfree.data.time.TimePeriodValuesCollection;
//import org.jfree.data.time.TimeTableXYDataset;
//import org.jfree.data.xy.XYSeries;
//import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.chart.plot.PlotOrientation;

public class GraphMod {
	/**
	 * Creates a line graph with the specified title, x-axis label, y-axis label, and data.
	 * 
	 * @param title the title of the graph
	 * @param xAxis the label for the x-axis
	 * @param yAxis the label for the y-axis
	 * @param data the data to be plotted on the graph
	 * @return a ChartPanel containing the line graph
	 */
	public ChartPanel getLineGraph( String title, String xAxis, String yAxis, TimePeriodValues data){
	
		TimePeriodValuesCollection dataset = new TimePeriodValuesCollection();
	    dataset.addSeries(data);

		JFreeChart chart = ChartFactory.createTimeSeriesChart(title, xAxis, yAxis,dataset,true,true,false );
		
		return new ChartPanel(chart);
	}

}
