import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.EventListener;
import javax.swing.JPanel;

public class myCanva extends JPanel {

	private static myCanva instance = null;
	private Mode modetype;
	private EventListener listener;
	private ArrayList<myObject> objects = new ArrayList<myObject>();
	// private ArrayList<myLine> lines = new ArrayList<myLine>();
	private myLine line = null;
	private myObject selectedObj = null;
	private Rectangle selectZone = new Rectangle();
	private int selectNum = 0;

	public myCanva() {
	}

	public static myCanva getInstance() {
		if (instance == null) {
			instance = new myCanva();
		}
		return instance;
	}

	public ArrayList<myObject> getObjectList() {
		return this.objects;
	}

	public void setModetype(Mode mode) {
		this.modetype = mode;
	}

	public void addObject(myObject obj) {
		this.objects.add(obj);
	}

	// public void addLine(myLine line) {
	// this.lines.add(line);
	// }

	public void setLine(myLine line) {
		this.line = line;
	}

	public void reloadMode() {
		// System.out.println("setMode "+this.modename);
		this.removeMouseListener((MouseListener) this.listener);
		this.removeMouseMotionListener((MouseMotionListener) this.listener);
		this.listener = this.modetype;
		this.addMouseListener((MouseListener) this.listener);
		this.addMouseMotionListener((MouseMotionListener) this.listener);
	}

	public void setSelectedObj(myObject obj) {
		this.selectedObj = obj;
		this.selectNum = 1;
	}

	public myObject getSelectedObj() {
		this.repaint();
		return this.selectedObj;
	}

	public void resetSelectObj() {
		this.selectedObj = null;

		for (myObject obj : objects) {
			obj.unSelected();
		}
	}

	public int getSelectNum() {
		return selectNum;
	}

	public void setZoneBounds(int x, int y, int width, int height) {
		this.selectZone.setBounds(x, y, width, height);
	}

	public void checkSelectObj() {
		if (selectedObj != null) {
			return;
		}
		for (int i = 0; i < objects.size(); i++) {
			myObject obj = this.objects.get(i);
			if (this.checkInZone(obj) == true) {
				this.selectNum++;
				obj.setSelected();
			}
		}
	}

	public boolean checkInZone(myObject obj) {
		Point p1 = obj.getPoint();
		Point p2 = new Point(p1.x + obj.width, p1.y + obj.height);
		if (this.selectZone.contains(p1) && this.selectZone.contains(p2)) {
			return true;
		}
		return false;
	}

	public void paint(Graphics g) {
		Dimension dim = this.getSize();
		g.setColor(Color.white);
		g.fillRect(0, 0, dim.width, dim.height);
		// g.setColor(Color.black);
		// Graphics2D g2 = (Graphics2D) g;
		// g2.setStroke(new BasicStroke(2));

		// paint all objeccts
		for (int i = 0; i < this.objects.size(); i++) {
			myObject obj = this.objects.get(i);
			obj.draw(g);

			// // paint port
			// if (obj.selected == true) {
			// // System.out.println("paint port");
			// obj.ports[0].drawPort(g);
			// obj.ports[1].drawPort(g);
			// obj.ports[2].drawPort(g);
			// obj.ports[3].drawPort(g);
			// }
			// // paint line
			// for (int j = 0; j < 4; j++) {
			// this.lines = obj.getPort(j).getLines();
			// g.setColor(Color.black);
			// for (int k = 0; k < this.lines.size(); k++) {
			// myLine line = this.lines.get(k);
			// line.draw(g);
			// }
			// }
		}

		// paint all lines
		// g.setColor(Color.black);
		// for (int i = 0; i < this.lines.size(); i++) {
		// myLine line = this.lines.get(i);
		// line.draw(g);
		// }

		// 正在拉的線
		g.setColor(Color.red);
		if (this.line != null) {
			this.line.draw(g);
		}

		// paint selectZone
		if (!this.selectZone.isEmpty()) {
			g.setColor(new Color(0, 0, 255, 50));
			g.fillRect(this.selectZone.x, this.selectZone.y, this.selectZone.width, this.selectZone.height);
			g.setColor(Color.blue);
			g.drawRect(this.selectZone.x, this.selectZone.y, this.selectZone.width, this.selectZone.height);
		}
	}

}
