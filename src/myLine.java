import java.awt.Graphics;

public class myLine {
    protected int x1, x2, y1, y2;
    protected int arrow = 10;
    protected myPort[] ports = new myPort[2];

    public void draw(Graphics g) {

    }

    // public void createPort() {
    // myPort port1 = new myPort(x1, y1);
    // this.ports[0] = port1;
    // myPort port2 = new myPort(x2, y2);
    // this.ports[1] = port2;
    // }

    public void resetPort() {
        this.ports[0] = new myPort(x1, y1);
        this.ports[1] = new myPort(x2, y2);
    }

    public void resetPosition() {
        // System.out.println("line resetPosition");
        this.x1 = (int) this.ports[0].x;
        this.y1 = (int) this.ports[0].y;
        this.x2 = (int) this.ports[1].x;
        this.y2 = (int) this.ports[1].y;
    }

}
