import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class createLineMode extends Mode {
    private String lineType;
    private Point start, end, tmpPoint;     // 按下、放開、拖曳中的點
    private ArrayList<myObject> objects;
    private myPort port1, port2;
    // private myObject obj1 = null, obj2 = null;

    public createLineMode(String lineType) {
        this.lineType = lineType;
        this.modeType = "createLineMode";
    }

    public void mousePressed(MouseEvent e) {
		// System.out.println("line press");
        this.start = e.getPoint();
        this.objects = canva.getObjectList();
        this.port1 = findConnectObj(start);
        if (port1 != null) {
            this.start.x = this.port1.x;
            this.start.y = this.port1.y;
        }
	}

	public void mouseReleased(MouseEvent e) {
        // System.out.println("line release");
        this.end = e.getPoint();
        this.port2 = findConnectObj(end);

        // create line successfully
        if ((this.port1 != null) && (this.port2 != null)) {

            if (lineType == "assline") {
                myAssLine newLine = new myAssLine(this.port1, this.port2);
                this.port1.addLine(newLine);
                this.port2.addLine(newLine);
                // System.out.println(this.port1.getLines().size());
            }
            else if (lineType == "genline") {
                myGenLine newLine = new myGenLine(this.port1, this.port2);
                this.port1.addLine(newLine);
                this.port2.addLine(newLine);
                // System.out.println(this.port1.getLines().size());
            }
            else if (lineType == "comline") {
                myComLine newLine = new myComLine(this.port1, this.port2);
                this.port1.addLine(newLine);
                this.port2.addLine(newLine);
                // System.out.println(this.port1.getLines().size());
            }
        }
        
        this.canva.setLine(null);    
        this.canva.repaint();
    }

    public void mouseDragged(MouseEvent e) {
        // System.out.println("line drag");
        if (port1 != null) {
            this.tmpPoint = e.getPoint();
        
            if (lineType == "assline") {
                this.canva.setLine(new myAssLine((int)this.start.getX(), (int)this.start.getY(), (int)this.tmpPoint.getX(), (int)this.tmpPoint.getY()));
            }
            else if (lineType == "genline") {
                this.canva.setLine(new myGenLine((int)this.start.getX(), (int)this.start.getY(), (int)this.tmpPoint.getX(), (int)this.tmpPoint.getY()));
            }
            else if (lineType == "comline") {
                this.canva.setLine(new myComLine((int)this.start.getX(), (int)this.start.getY(), (int)this.tmpPoint.getX(), (int)this.tmpPoint.getY()));
            }
            
            this.canva.repaint();
        }
	}

    public myPort findConnectObj(Point p) {
        // System.out.println("findConnectObj");
        for(int i = this.objects.size()-1; i >= 0; i--) {
            myObject obj = objects.get(i);

            int position = obj.checkInside(p);
            // if it is a grouped object
            if (position == 4) {
                return null;
            }
            // 1-3
            else if (position != -1) {
                // System.out.println(position);
                return(obj.getPort(position));
            }
        }
        return null;
    }
}
