import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.chart.plot.PlotOrientation;

public class GraphMod {
	public ChartPanel getLineGraph( String title, String xAxis, String yAxis, XYSeries data){
	
		XYSeriesCollection dataset = new XYSeriesCollection();
	    dataset.addSeries(data);

		JFreeChart chart = ChartFactory.createXYLineChart(title, xAxis, yAxis,dataset,PlotOrientation.VERTICAL,true,true,false );
		
		return new ChartPanel(chart);
	}
}
