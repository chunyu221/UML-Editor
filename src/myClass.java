import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class myClass extends myObject {
    
    public myClass(int x, int y) {
        this.x = x;
        this.y = y;
        this.width = 80;
        this.height = 100;
        this.name = "Class Name";
        this.createPort();
    }

    public void draw(Graphics g) {
        // System.out.println("class draw");
        g.setColor(Color.white);
        g.fillRect(this.x, this.y, this.width, this.height);
        g.drawRect(this.x, this.y, this.width, this.height);
        
        g.setColor(Color.black);
        // 外框
        g.drawRect(this.x, this.y, this.width, this.height);
        
        // paint port
        if (this.selected == true) {
            // System.out.println("paint port");
            this.ports[0].drawPort(g);
            this.ports[1].drawPort(g);
            this.ports[2].drawPort(g);
            this.ports[3].drawPort(g);
        }
        // paint line
        for (int j = 0; j < 4; j++) {
            ArrayList<myLine> lines = this.getPort(j).getLines();
            g.setColor(Color.black);
            for (int k = 0; k < lines.size(); k++) {
                myLine line = lines.get(k);
                line.draw(g);
            }
        }

        int d = this.height/3;
        g.drawLine(this.x, this.y+d, this.x+width, this.y+d);
        g.drawLine(this.x, this.y+d*2, this.x+width, this.y+d*2);

        int stringWidth = g.getFontMetrics(this.font).stringWidth(this.name);
        double space = (width - stringWidth)/2;
        g.setFont(this.font);
        g.drawString(this.name, this.x+(int)space, this.y+24);
    }
}
