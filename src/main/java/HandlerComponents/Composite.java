package HandlerComponents;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Composite implements Component {

    private List<Component> components;

    public Composite(Component...components){
        this.components=new ArrayList<Component>();
        for (Component component: components)
            this.components.add(component);
    }

    public void add(Component... components) {
        for (Component component: components)
            this.components.add(component);
    }

    public void remove(Component... components) {
        for (Component component: components)
            this.components.remove(component);
    }

    public Component getComponent(int index) {
        return components.get(index);
    }

    public String value() {
        String result = "";
        for (Component c: components)
            result += c.value();

        return result;
    }


    public void display() {
        for (Component c: components){
            c.display();
            //System.out.print(',');
        }

    }

    public List<Component> getComponents()throws Exception{
        try {
            if(components == null) throw new Exception("Композиция не разделена!");
        }catch (Exception e){
            e.printStackTrace();
        }
        return components;
    }
}
