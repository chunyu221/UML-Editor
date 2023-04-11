import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class myUseCase extends myObject {
    public myUseCase(int x, int y) {
        this.x = x;
        this.y = y;
        this.width = 100;
        this.height = 60;
        this.name = "UseCase Name";
        this.createPort();
    }

    public void draw(Graphics g) {
        // System.out.println("usecase draw");
        g.setColor(Color.white);
        g.fillOval(this.x, this.y, this.width, this.height);
        g.setColor(Color.black);
        g.drawOval(this.x, this.y, this.width, this.height);

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

        int stringWidth = g.getFontMetrics(this.font).stringWidth(this.name);
        double space = (width - stringWidth)/2;
        g.setFont(this.font);
        g.drawString(this.name, this.x+(int)space, this.y+35);
    }
}
