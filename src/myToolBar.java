import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

public class myToolBar  extends JPanel{
	
	private myButton Btn_tmp=null;
	private myCanva canvas=new myCanva();

	private myButton select = new myButton("Select", new selectMode());
	private myButton assline = new myButton("Association Line", new createLineMode("assline"));
	private myButton genline = new myButton("Generalization Line", new createLineMode("genline"));
	private myButton comline = new myButton("Composition Line", new createLineMode("comline"));
	private myButton class_ = new myButton("Class", new createObjMode("class"));
	private myButton usecase = new myButton("Usecase", new createObjMode("usecase"));
	
	public myToolBar(){
		this.canvas=myCanva.getInstance();
		this.setLayout(new GridLayout(6,1,0,0));
		this.setBackground(Color.black);

		this.select.addActionListener(new btnAction());
		this.assline.addActionListener(new btnAction());
		this.genline.addActionListener(new btnAction());
		this.comline.addActionListener(new btnAction());
		this.class_.addActionListener(new btnAction());
		this.usecase.addActionListener(new btnAction());
		
		this.add(select);
		this.add(assline);
		this.add(genline);
		this.add(comline);
		this.add(this.class_);
		this.add(this.usecase);

	}
	
	class btnAction implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(Btn_tmp != null)
				Btn_tmp.DisOnClick();
			Btn_tmp = (myButton) e.getSource();
			Btn_tmp.OnClick();
			canvas.setModetype(Btn_tmp.getMode());
			canvas.reloadMode();
			if (Btn_tmp.getMode().getModeType() != "selectMode") {
				canvas.resetSelectObj();
			}
			canvas.repaint();
		}
	}

	
}
