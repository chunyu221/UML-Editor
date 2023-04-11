import javax.swing.JFrame;
import java.awt.BorderLayout;


public class myFrame extends JFrame{
	
	private static myCanva canvas = new myCanva() ;
	private myToolBar toolbox = new myToolBar();
	private myMenu menulist = new myMenu();
	
	public myFrame() {
		canvas = myCanva.getInstance() ;
		
		this.setTitle("UML editor");
		this.setBounds(10, 10, 1280, 720);
		this.setLayout(new BorderLayout());
		this.getContentPane().add(canvas, BorderLayout.CENTER);
		this.add(menulist,BorderLayout.NORTH);
		this.add(toolbox , BorderLayout.WEST);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
	}


}
