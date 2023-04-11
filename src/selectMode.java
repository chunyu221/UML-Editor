import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class selectMode extends Mode {
	private Point p = null;
	private int position = -1;
	private ArrayList<myObject> objects;

	public selectMode() {
		modeType = "selectMode";
	}
    
    public void mousePressed(MouseEvent e) {
		// System.out.println("select press");
		p = e.getPoint();
		objects = this.canva.getObjectList();
		
		// reset selection
		this.canva.resetSelectObj();

		// 越晚加的越上層
		for (int i = this.objects.size()-1; i >= 0; i--) {
			myObject obj = this.objects.get(i);
			this.position = obj.checkInside(p);

			if (this.position != -1) {
				this.canva.setSelectedObj(obj);
				obj.setSelected();
				break;
			}
		}
		this.canva.repaint();
	}

	public void mouseReleased(MouseEvent e) {
        // System.out.println("select release");
		this.p = null;
		this.canva.checkSelectObj();
		this.canva.setZoneBounds(0, 0, 0, 0);
		this.canva.repaint();
	}

    public void mouseDragged(MouseEvent e) {
        // System.out.println("select drag");

		int moveX = e.getX() - this.p.x;
		int moveY = e.getY() - this.p.y;
		
		// 有點到object
		if (this.canva.getSelectedObj() != null) {
			this.canva.getSelectedObj().resetPosition(moveX, moveY);
			this.p = e.getPoint();
		}
		else {
			// 看滑鼠往哪拉
			// 右下
			if (e.getX() > this.p.getX() && e.getY() > this.p.y) {
				this.canva.setZoneBounds(this.p.x, this.p.y, Math.abs(moveX), Math.abs(moveY) );
			}
			// 左下
			else if (e.getX() < this.p.getX() && e.getY() > this.p.y) {
				this.canva.setZoneBounds(e.getX(), this.p.y, Math.abs(moveX), Math.abs(moveY) );
			}
			// 右上
			else if (e.getX() > this.p.getX() && e.getY() < this.p.y) {
				this.canva.setZoneBounds(this.p.x, e.getY(), Math.abs(moveX), Math.abs(moveY) );
			}
			// 左上
			else {
				this.canva.setZoneBounds(e.getX(), e.getY(), Math.abs(moveX), Math.abs(moveY) );				
			}
		}
		this.canva.repaint();
		
	}
}
