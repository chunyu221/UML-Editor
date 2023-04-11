import java.awt.Graphics;

public class myAssLine extends myLine {

    public myAssLine(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        resetPort();
    }

    public myAssLine(myPort port1, myPort port2) {
        this.ports[0] = port1;
        this.ports[1] = port2;
        resetPosition();
    }
    
    public void draw(Graphics g) {
        g.drawLine(this.x1, this.y1, this.x2, this.y2);

        int dx = this.x2 - this.x1, dy = this.y2 - this.y1;
		double D = Math.sqrt(dx*dx + dy*dy);
		double xm = D - this.arrow, xn = xm, ym = this.arrow, yn = -this.arrow, x;
		double sin = dy/D, cos = dx/D;
		
		x = xm*cos - ym*sin + this.x1;
        ym = xm*sin + ym*cos + this.y1;
        xm = x;
        
        x = xn*cos - yn*sin + this.x1;
        yn = xn*sin + yn*cos + this.y1;
        xn = x;
        
        g.drawLine((int)xm, (int)ym, this.x2, this.y2);   
        g.drawLine((int)xn, (int)yn, this.x2, this.y2); 
    }
}
