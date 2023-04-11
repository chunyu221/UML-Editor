import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;

public class myObject {
    protected int x, y;
    protected String name;
    protected boolean selected = false, grouped = false;
    protected int width, height;
    protected myPort[] ports = new myPort[4];
    protected Font font = new Font(Font.DIALOG, Font.BOLD, 12);

    public String getObjName() {
        return this.name;
    }

    public void changeObjName(String newName) {
        this.name = newName;
    }

    public void draw(Graphics g) {

    }

    public void show(Graphics g) {

    }

    public void setSelected() {
        this.selected = true;
    }

    public void unSelected() {
        this.selected = false;
    }

    public boolean isSelected() {
        return this.selected;
    }

    public void setGrouped() {
        grouped = true;
    }

    public void unGrouped() {
        grouped = false;
    }

    public boolean isGrouped() {
        return grouped;
    }

    public void createPort() {
        // 上右下左
        int[] portXs = {this.x+(this.width/2), this.x+this.width, 
                        this.x+(this.width/2), this.x};
        int[] portYs = {this.y, this.y+(this.height/2), 
                        this.y+this.height, this.y+(this.height/2)};

        for (int i = 0; i < 4; i++) {
            myPort port = new myPort(portXs[i], portYs[i]);
            this.ports[i] = port;
        }
    }

    public void setPort() {
        int[] portXs = {this.x+(this.width/2), this.x+this.width, 
                    this.x+(this.width/2), this.x};
        int[] portYs = {this.y, this.y+(this.height/2), 
                    this.y+this.height, this.y+(this.height/2)};
        
        for (int i = 0; i < 4; i++) {
            this.ports[i].x = portXs[i];
            this.ports[i].y = portYs[i];
        }
    }

    public myPort getPort(int i) {
        return this.ports[i];
    }

    public int checkInside(Point p) {
        
        // 找中心點，分成4塊
        Point center = new Point(this.x + (this.width/2), this.y + (this.height/2));
        Point[] points = {new Point(this.x, this.y), new Point(this.x+this.width, this.y), 
                        new Point(this.x+this.width, this.y+this.height), new Point(this.x, this.y+this.height)};

        for (int i = 0; i < 4; i++) {
            Polygon block = new Polygon();
            int secondIndex = (i+1)%4;
            block.addPoint(center.x, center.y);
            block.addPoint(points[i].x, points[i].y);
            block.addPoint(points[secondIndex].x, points[secondIndex].y);

            // check if in the triangle block
            if (block.contains(p)) {
                // return 滑鼠點在哪個範圍 
                return i;
            }
        }
        return -1;
    }

    public void resetPosition(int moveX, int moveY) {

        this.x += moveX;
        this.y += moveY;
        
        setPort();
        for (int i = 0; i < 4; i++) {
            this.ports[i].resetLine();
        }
    }

    public Point getPoint() {
        return new Point(this.x, this.y);
    }
}
