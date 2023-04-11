import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class myMenu extends JMenuBar{
	private JMenu file = new JMenu("File");
	private JMenu edit = new JMenu("Edit");
	private JMenuItem changeName=new JMenuItem("Change object name");
	private JMenuItem groupObj=new JMenuItem("Group object");
	private JMenuItem UngroupObj=new JMenuItem("Ungroup object");
	
	public myMenu() {		
		this.add(this.file);
		this.add(this.edit);
		
		this.edit.add(this.changeName);
		this.edit.add(this.groupObj);
		this.edit.add(this.UngroupObj);

		this.changeName.addActionListener(new changeObjectName());
		this.groupObj.addActionListener(new groupListener());
		this.UngroupObj.addActionListener(new ungroupListener());
	}

	
	
}
