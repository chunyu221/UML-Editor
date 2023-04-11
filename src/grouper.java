import java.util.ArrayList;

public class grouper {
    protected myCanva canva = myCanva.getInstance();
    private ArrayList<myObject> objects = canva.getObjectList();

    public void group() {
        // System.out.println("group");
        groupObject group = new groupObject();
        for (int i = 0; i < objects.size(); i++) {
            myObject obj = objects.get(i);
            if (obj.isSelected()) {
                group.addObject(obj);
                this.objects.remove(i);
                i--;
            }
        }
        group.setBounds();
        this.objects.add(group);
        group.setGrouped();
        group.unSelected();
    }

    public void ungroup() {
        groupObject group = (groupObject)this.canva.getSelectedObj();
        ArrayList<myObject> groupObjects = group.getGroupList();
        for (int i = 0; i < groupObjects.size(); i++) {
            myObject obj = groupObjects.get(i);
            this.objects.add(obj);
        }
        this.objects.remove(this.canva.getSelectedObj());
    }
}
