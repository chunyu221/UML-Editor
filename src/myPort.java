import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

public class myPort extends Rectangle {
    
    protected int x, y;
    private int edge = 6;
    private ArrayList<myLine> lines = new ArrayList<myLine>();

    public myPort(int x, int y) {
        this.x = x;
        this.y = y;
        setBounds(x, y, edge, edge);
    }

    public void resetPort(int x, int y) {
        this.x = x;
        this.y = y;
        setBounds(x, y, edge, edge);
    }

    public void drawPort(Graphics g) {
        g.setColor(Color.red);
        g.fillRect(this.x-3, this.y-3, this.edge, this.edge);
        g.drawRect(this.x-3, this.y-3, this.edge, this.edge);
    }

    public void addLine(myLine line) {
        // System.out.println("addLine");
        this.lines.add(line);
    }

    public void resetLine() {
        // System.out.println("resetline");
        // System.out.println(this.lines.size());
        for (int i = 0; i < this.lines.size(); i++) {
            myLine line = this.lines.get(i);
            line.resetPosition();
        }
    }

    public ArrayList<myLine> getLines() {
        return this.lines;
    }
}
