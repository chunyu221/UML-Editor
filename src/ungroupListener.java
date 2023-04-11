import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ungroupListener implements ActionListener {

    private myCanva canva = new myCanva();
    private grouper grouper = new grouper();

    @Override
    public void actionPerformed(ActionEvent e) {
        this.canva = myCanva.getInstance();
        int selectNum = canva.getSelectNum();
        if (selectNum == 1 && canva.getSelectedObj().isGrouped()) {
            grouper.ungroup();
            canva.repaint();
        }
    }
    
}
