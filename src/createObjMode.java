import java.awt.event.MouseEvent;

public class createObjMode extends Mode{

	private String objType;

	public createObjMode(String objType) {
		this.objType = objType;
		this.modeType = "createObjMode";
	}

	public void mousePressed(MouseEvent e) {
		// System.out.println("obj press");

		if(this.objType == "class") {
			this.canva.addObject(new myClass(e.getX(), e.getY()));
			this.canva.repaint();
		}
		else if (this.objType == "usecase") {
			this.canva.addObject(new myUseCase(e.getX(), e.getY()));
			this.canva.repaint();
		}

	}
}
