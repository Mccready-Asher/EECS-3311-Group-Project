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
	public ChartPanel getLineGraph( String title, String xAxis, String yAxis, TimePeriodValues data){
	
		TimePeriodValuesCollection dataset = new TimePeriodValuesCollection();
	    dataset.addSeries(data);

		JFreeChart chart = ChartFactory.createTimeSeriesChart(title, xAxis, yAxis,dataset,true,true,false );
		
		return new ChartPanel(chart);
	}
}
