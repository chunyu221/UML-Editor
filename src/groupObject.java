import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

public class groupObject extends myObject {
    private ArrayList<myObject> objects = new ArrayList<myObject>();
    private ArrayList<myLine> lines = new ArrayList<myLine>();
    private Rectangle bound = new Rectangle();
    private myObject selectedObj = null;

    public void addObject(myObject obj) {
        objects.add(obj);
    }

    public void draw(Graphics g) {
        for (int i = 0; i < this.objects.size(); i++) {
            myObject obj =  objects.get(i);
            obj.draw(g);

            if (obj.ports[0] != null) {
                if (obj.selected == true) {
                    // System.out.println("paint port");
                    obj.ports[0].drawPort(g);
                    obj.ports[1].drawPort(g);
                    obj.ports[2].drawPort(g);
                    obj.ports[3].drawPort(g);
                }
                // paint line
                for (int j = 0; j < 4; j++) {
                    this.lines = obj.getPort(j).getLines();
                    g.setColor(Color.black);
                    for (int k = 0; k < this.lines.size(); k++) {
                        myLine line = this.lines.get(k);
                        line.draw(g);
                    }
                }
            }

            

            // paint bound
            if (selected) {
                g.setColor(new Color(0, 0, 255, 50));
                g.fillRect(this.bound.x, this.bound.y, this.bound.width, this.bound.height);
                g.setColor(Color.blue);
                g.drawRect(this.bound.x, this.bound.y, this.bound.width, this.bound.height);
            }
        }
    }

    public void setSelected() {
        this.selected = true;
        for (int i = 0; i < objects.size(); i++) {
            myObject obj = objects.get(i);
            obj.setSelected();
        }
    }

    public void unSelected() {
        this.selected = false;
        for (int i = 0; i < objects.size(); i++) {
            myObject obj = objects.get(i);
            obj.unSelected();
        }
    }

    public int checkInside(Point p) {
        for (int i = 0; i < this.objects.size(); i++) {
            myObject obj =  objects.get(i);
            if (obj.checkInside(p) != -1){
                this.selectedObj = obj;
                return 4;
            }
        }
        return -1;
    }

    public void resetPosition(int moveX, int moveY) {
        for (int i = 0; i < this.objects.size(); i++) {
            myObject obj =  objects.get(i);
            obj.resetPosition(moveX, moveY);
        }
        setBounds();
    }

    public void setBounds() {
        // Point minPoint, maxPoint;
        int xMin = Integer.MAX_VALUE, xMax = Integer.MIN_VALUE;
        int yMin = Integer.MAX_VALUE, yMax = Integer.MIN_VALUE;

        for (int i = 0; i < this.objects.size(); i++) {
            myObject obj = this.objects.get(i);
            Point p1 = obj.getPoint();
            Point p2 = new Point(p1.x+obj.width, p1.y+obj.height);
            if (p1.x < xMin) {
                xMin = p1.x;
            }
            if (p1.y < yMin) {
                yMin = p1.y;
            }
            if (p2.x > xMax) {
                xMax = p2.x;
            }
            if (p2.y > yMax) {
                yMax = p2.y;
            }

            // minPoint = new Point(xMin, yMin);
            this.bound.setBounds(xMin, yMin, xMax-xMin, yMax-yMin);
            this.x = xMin;
            this.y = yMin;
            this.width = xMax-xMin;
            this.height = yMax-yMin;
        }
    }

    public ArrayList<myObject> getGroupList() {
        return this.objects;
    }
}
