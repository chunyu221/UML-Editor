import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class changeObjectName implements ActionListener {

    private myCanva canva = new myCanva();
    @Override
    public void actionPerformed(ActionEvent e) {
        this.canva = myCanva.getInstance();
        myObject obj = this.canva.getSelectedObj();

        if (obj != null) {
            JFrame inputFrame = new JFrame("Change Object Name");
            inputFrame.setSize(400, 200);

            JPanel panel = new JPanel();


            JTextField textField = new JTextField(obj.getObjName());
            panel.add(textField);

            JButton OK = new JButton("OK");
            JButton cancel = new JButton("Cancel");
            panel.add(OK);
            panel.add(cancel);

            inputFrame.add(panel);
            inputFrame.setVisible(true);

            OK.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    canva.getSelectedObj().changeObjName(textField.getText());
                    inputFrame.dispose();
                }
            });

            cancel.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    inputFrame.dispose();
                }
            });
        }
    }    
}
