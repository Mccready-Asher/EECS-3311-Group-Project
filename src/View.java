import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.time.Month;
import org.jfree.data.time.TimePeriodValues;
import org.jfree.data.time.TimePeriodValuesCollection;
import org.jfree.data.time.Year;
import org.jfree.data.xy.DefaultXYDataset;

public class View extends JPanel implements ActionListener {
	private Controller controller;
	private JComboBox<String> geoComboBox;
	private JComboBox<String> startComboBox;
	private JComboBox<String> endComboBox;
	private JComboBox<String> timeGranularityComboBox;
	private JButton displayButton;
//	private JTable table;
//	private ChartPanel chart;
	TimePeriodValuesCollection dataset;
	JFreeChart chart;
	ChartPanel cp;

	/**
	 * This constructor is creating a view for displaying a chart and a table of data based on
	 * the user's selections. It takes a Controller object as a parameter to get the data for 
	 * the view.
	 * 
	 * Creates a GUI with various components such as combo boxes and a display button, sets up a 
	 * chart for visualizing data, and adds them to the layout. It also retrieves data from the 
	 * controller to populate the combo boxes.
	 * 
	 * @param controller instance of the controller of the MVC
	 * @throws SQLException if there is an error connecting to or interacting with the SQL database
	 */
	public View(Controller controller) throws SQLException {
		this.controller = controller;

		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(800, 600));
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		topPanel.add(new JLabel("GEO:"));
		geoComboBox = new JComboBox<>();
		geoComboBox.addItem("");
		for (String r : controller.getDistinctGeoValues()) {
			geoComboBox.addItem(r);
		}
		topPanel.add(geoComboBox);
		
		topPanel.add(new JLabel("Start Month:"));
		startComboBox = new JComboBox<>();
		startComboBox.addItem("");
		for (String r : controller.getDistinctRefDateValues()) {
			startComboBox.addItem(r);
		}
		topPanel.add(startComboBox);
		
		topPanel.add(new JLabel("End Month:"));
		endComboBox = new JComboBox<>();
		endComboBox.addItem("");
		List<String> rev = controller.getDistinctRefDateValues();
		Collections.reverse(rev);
		for (String r : rev) {
			endComboBox.addItem(r);
		}
		topPanel.add(endComboBox);

//        topPanel.add(new JLabel("Ref Date:"));
//        refDateComboBox = new JComboBox<>();
//		topPanel.add(refDateComboBox);
		topPanel.add(new JLabel("Time Granularity:"));
		timeGranularityComboBox = new JComboBox<>();
		timeGranularityComboBox.addItem("Monthly");
		timeGranularityComboBox.addItem("Yearly");
		topPanel.add(timeGranularityComboBox);

		displayButton = new JButton("Display");
		displayButton.addActionListener(this);
		topPanel.add(displayButton);

		add(topPanel, BorderLayout.NORTH);

		dataset = new TimePeriodValuesCollection();
		chart = ChartFactory.createTimeSeriesChart("title", "xAxis", "yAxis", dataset, true, true, false);

//		table = new JTable();
		cp = new ChartPanel(chart);
		JScrollPane scrollPane = new JScrollPane(cp);
		add(scrollPane, BorderLayout.CENTER);
	}




	

	/**
	 *This method is called when the "Display" button is clicked. It retrieves the selected values from the GUI,
	 *gets the relevant data from the controller, and displays it in a table and chart.

	 *@param e the ActionEvent that triggered this method.
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == displayButton) {
			try {
				List<RowData> data;
				String start = (String) startComboBox.getSelectedItem();
				String end = (String) endComboBox.getSelectedItem();
				String geo = (String) geoComboBox.getSelectedItem();
				if (timeGranularityComboBox.getSelectedItem().equals("Monthly")) {
					data = controller.getCustomDataByGeo(geo, start, end, true);
				} else {
					data = controller.getCustomDataByGeo(geo, start, end, false);
				}
				System.out.println(data.size());
				displayTable(data);
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}



	/**
	 *Displays a chart with the data provided in a list of RowData objects.
	 *If the time granularity is set to "Monthly", it creates a TimePeriodValues object with month granularity,
	 *otherwise it creates a TimePeriodValues object with yearly granularity.
	 *The method removes the current series from the dataset and adds the new TimePeriodValues object to it.
	 *Then, it repaints the chart panel (if one exists).
	 *
	 *@param data a list of RowData objects to be displayed in the chart
*/
	void displayTable(List<RowData> data) {
		TimePeriodValues dataTP = new TimePeriodValues("whatev");
		for (RowData rowData : data) {
			if (timeGranularityComboBox.getSelectedItem().equals("Monthly")) {
				String[] date = rowData.getRefDate().split("-");
				dataTP.add(new Month(Integer.parseInt(date[1]), Integer.parseInt(date[0])),
						Double.parseDouble(rowData.getValue()));
			} else {
				dataTP.add(new Year(Integer.parseInt(rowData.getRefDate())), Double.parseDouble(rowData.getValue()));
			}
		}
		try {
			dataset.removeSeries(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		dataset.addSeries(dataTP);

		cp.repaint();
	}
}