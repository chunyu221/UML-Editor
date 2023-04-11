import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class groupListener implements ActionListener {

    private myCanva canva = new myCanva();
    private grouper grouper = new grouper();

    @Override
    public void actionPerformed(ActionEvent e) {
        this.canva = myCanva.getInstance();
        int selectNum = this.canva.getSelectNum();
        // System.out.println(selectNum);
        if (selectNum > 1) {
            grouper.group();
            canva.repaint();
        }
    }
    
}
