import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.Box;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Container;
import java.awt.Component;
import java.awt.Dimension; //todo, fewer import calls
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.chart.plot.PlotOrientation;



public class ProjUIDemo {
	
	private JFrame frame;
	private JButton button1; // this is a bad way to do this, fix it eventually
	private JButton button2;  //todo: make everything less static, easier to scale.
	private int width;
	private int height;
	

	/**
	 * Constructs a new ProjUIDemo object with the specified width and height.
	 * @param w the width of the window
	 * @param h the height of the window
	 */
	public ProjUIDemo(int w, int h) { 
		frame = new JFrame();
		button1 = new JButton("Start");
		button2 = new JButton("\u2190 Back");
		width = w;
		height = h;
	}

	/**
	 * Launches the application demo by setting up the frame, button listeners, and title.
	 * One method to set everything off, makes the tester class as small as possible.
	*/
	public void launchAppDemo() { 
		setUpFrame();
		setUpButtonListeners();
		setTitle();
	}
	
	/**
	 * Sets up the basic frame for the application with the specified width and height, sets the title to "Project GUI Demo",
	 * and sets the default close operation to exit the application when the user clicks the "X" button.
	*/
	public void setUpFrame() { 
		frame.setSize(width,height);
		frame.setTitle("Project GUI Demo.");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	

	/**
	 * Sets the title screen of the application by replacing all components in the JFrame's content pane with a title, subtitle,
	 * developer team label, etc. along with a "Start" button. This method uses a BoxLayout to vertically align the components
	 * in the center of the JFrame.
	*/
	public void setTitle(){ 
		
		Container cp = frame.getContentPane();
		
		nuke(); //boom
		
		BoxLayout box = new BoxLayout(cp,BoxLayout.Y_AXIS);
		cp.setLayout(box);
		
		JLabel blank = new JLabel(" "); 
		JLabel tit = new JLabel("this is a title"); //to be replaced, duh
		JLabel subtit = new JLabel("this is a subtitle");
		JLabel devteam= new JLabel("this is where our names go");
		JLabel etc = new JLabel("we can keep going");
		
		tit.setAlignmentX(Component.CENTER_ALIGNMENT); //there should be a better way to do this
		devteam.setAlignmentX(Component.CENTER_ALIGNMENT);
		subtit.setAlignmentX(Component.CENTER_ALIGNMENT);
		etc.setAlignmentX(Component.CENTER_ALIGNMENT);
		button1.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		cp.add(blank);
		cp.add(Box.createRigidArea(new Dimension(0,230)));
		
		cp.add(tit);
		cp.add(subtit);
		cp.add(devteam);
		cp.add(etc);
		cp.add(button1);
		
		frame.setVisible(true);
	}

	
	/**
	 * Sets up the "menu state" of the UI, which displays several visualization options as buttons.
	*/
	public void setMenu() { //moves to "menu state"
		Container cp = frame.getContentPane();
		nuke();
		
		GridLayout gl = new GridLayout(2,3);
		cp.setLayout(gl);
		JButton b1 = new JButton("Visualization 1 (demo) ");
		JButton b2 = new JButton("visualization 2 (unimplemented)");
		JButton b3 = new JButton("visualization 3 (unimplemented)");
		JButton b4 = new JButton("visualization 4 (unimplemented)");
		JButton b5 = new JButton("visualization 5 (unimplemented)");
		
		ActionListener dem1Link = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				
				setDemo1();
			}
		};
		b1.addActionListener(dem1Link);
		
		b2=unimpLink(b2);
		b3=unimpLink(b3);
		b4=unimpLink(b4);
		b5=unimpLink(b5);
		
		
		cp.add(b1);
		cp.add(b2);
		cp.add(b3);
		cp.add(b4);
		cp.add(b5);
		
		cp.add(button2);
		frame.setVisible(true);
	}
	
	/**
	 * Sets up the "unimplemented" state when a user selects a visualization option that has not yet been implemented.
	 * Displays a message indicating that the content has not been implemented and prompts the user to return to the menu.
	 */
	public void unimp() {
		Container cp = frame.getContentPane();
		
		nuke(); //boom
		
		BoxLayout box = new BoxLayout(cp,BoxLayout.Y_AXIS);
		cp.setLayout(box);
		
		JLabel blank = new JLabel(" "); //this is  scuffed as fuck
		JLabel msg = new JLabel("this content has not yet been implenented"); //to be replaced, duh
		JLabel msgsub = new JLabel("please return to the menu");
		
		JButton backbut = new JButton("\u2190 Back");
		
		ActionListener bBL = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				
				setMenu();
			}
		};
		backbut.addActionListener(bBL);
		
		msg.setAlignmentX(Component.CENTER_ALIGNMENT); //there should be a better way to do this
		msgsub.setAlignmentX(Component.CENTER_ALIGNMENT);
		backbut.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		cp.add(blank);//once again scuffed as fuck, but ill fix this later
		cp.add(Box.createRigidArea(new Dimension(0,230)));
		
		cp.add(msg);
		cp.add(msgsub);
		
		cp.add(backbut);
		
		frame.setVisible(true);
	}
	


	/**
	 * Sets the GUI to display a demo visualization.
	 * The method creates a new layout and adds a "Back" button and the demo chart to the container.
	 * The "Back" button returns the user to the main menu.
	*/
	
	public void setDemo1() {
		Container cp = frame.getContentPane();
		
		nuke(); //boom
		
		BoxLayout box = new BoxLayout(cp,BoxLayout.Y_AXIS);
		cp.setLayout(box);
		
		JButton backbut = new JButton("\u2190 Back");
		
		ActionListener bBL = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				
				setMenu();
			}
		};
		backbut.addActionListener(bBL);
		
		cp.add(backbut);
		
		
		cp.add(getDemoChart());
		
		
		frame.setVisible(true);
	}
	

	/**
	 * Removes all components from the content pane of the main JFrame
	 * The method removes all components, revalidates the container, and repaints it.
	 * An eraser method with a flair for the dramatic 
	*/
	private void nuke() { 
		Container cp = frame.getContentPane();
		cp.removeAll(); 
		cp.revalidate();
		cp.repaint();
	}
	
	/**
	 * Sets up the action listeners for the two buttons in the GUI.
	 * The first button returns the user to the main menu, and the second button sets the title of the GUI.
	*/
	public void setUpButtonListeners() {
		ActionListener buttonListener1 = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				setMenu();
			}
		};
		ActionListener buttonListener2 = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				
				setTitle();
			}
		};
		button1.addActionListener(buttonListener1);
		button2.addActionListener(buttonListener2);
	}
	
	

	/**
	 * Assigns an ActionListener to a given JButton that calls the unimp() method when clicked.
	 * 
	 * @param jb the JButton to assign the ActionListener to
	 * @return the modified JButton with the ActionListener assigned
	 */
	public JButton unimpLink(JButton jb) {
		ActionListener unimBL = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				
				unimp();
			}
		};
		jb.addActionListener(unimBL);
		return jb;
	}
	


	/**
	 * Creates a demo chart with a predefined set of data and returns it as a ChartPanel.
	 * The chart displays the relationship between the number of concurrent assignments and 
	 * the average energy drinks drunk per day.
	 * 
	 * @return a ChartPanel containing the demo chart
	*/
	private ChartPanel getDemoChart() {
		XYSeries series = new XYSeries("XYGraph");
		series.add(1, 0);
		series.add(2, 1);
		series.add(3, 4);
		series.add(4, 5);
		series.add(5, 7);
		series.add(6, 8);
		series.add(7, 10);
		series.add(8, 11);
		series.add(9, 12);
		series.add(10, 15);

		XYSeriesCollection dataset = new XYSeriesCollection();
	    dataset.addSeries(series);

		JFreeChart chart = ChartFactory.createXYLineChart("Demo Chart", "Number of concurrent assignments", "average energy drinks drunk per day",dataset,PlotOrientation.VERTICAL,true,true,false );
		
		return new ChartPanel(chart);
	}
	
}
