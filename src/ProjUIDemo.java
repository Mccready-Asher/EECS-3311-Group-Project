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




public class ProjUIDemo {
	
	private JFrame frame;
	private JButton button1; // this is a bad way to do this, fix it eventually
	private JButton button2;  //todo: make everything less static, easier to scale.
	private int width;
	private int height;
	
	public ProjUIDemo(int w, int h) { 
		frame = new JFrame();
		button1 = new JButton("Start");
		button2 = new JButton("\u2190 Back");
		width = w;
		height = h;
	}
	public void launch() { //one method to set everything off, makes the tester class as small as possible.
		setUpFrame();
		setUpButtonListeners();
		setTitle();
	}
	
	public void setUpFrame() { //basic frame setup, state-agnostic
		frame.setSize(width,height);
		frame.setTitle("Project GUI Demo.");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public void setTitle(){ //deletes everything end sets to the "title screen" state
		
		Container cp = frame.getContentPane();
		
		nuke(); //boom
		
		BoxLayout box = new BoxLayout(cp,BoxLayout.Y_AXIS);
		cp.setLayout(box);
		
		JLabel blank = new JLabel(" "); //this is  scuffed as fuck
		JLabel tit = new JLabel("this is a title"); //to be replaced, duh
		JLabel subtit = new JLabel("this is a subtitle");
		JLabel devteam= new JLabel("this is where our names go");
		JLabel etc = new JLabel("we can keep going");
		
		tit.setAlignmentX(Component.CENTER_ALIGNMENT); //there should be a better way to do this
		devteam.setAlignmentX(Component.CENTER_ALIGNMENT);
		subtit.setAlignmentX(Component.CENTER_ALIGNMENT);
		etc.setAlignmentX(Component.CENTER_ALIGNMENT);
		button1.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		cp.add(blank);//once again scuffed as fuck, but ill fix this later
		cp.add(Box.createRigidArea(new Dimension(0,230)));
		
		cp.add(tit);
		cp.add(subtit);
		cp.add(devteam);
		cp.add(etc);
		cp.add(button1);
		
		frame.setVisible(true);
	}
	
	public void setMenu() { //moves to "menu state"
		Container cp = frame.getContentPane();
		nuke();
		
		GridLayout gl = new GridLayout(2,3);
		cp.setLayout(gl);
		JButton b1 = new JButton("visualization 1 (unimplemented)");
		JButton b2 = new JButton("visualization 2 (unimplemented)");
		JButton b3 = new JButton("visualization 3 (unimplemented)");
		JButton b4 = new JButton("visualization 4 (unimplemented)");
		JButton b5 = new JButton("visualization 5 (unimplemented)");
		
		b1=unimpLink(b1);
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
	
	
	
	private void nuke() { //eraser method with a flair for the dramatic 
		Container cp = frame.getContentPane();
		cp.removeAll(); //remember your 3 Rs!
		cp.revalidate();
		cp.repaint();
	}
	
	
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
	
}
