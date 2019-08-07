package HandlerComponents;

import java.util.StringTokenizer;

public interface Component {
    public String value();       //public Character character()

    public void display();
    public void add(Component... component);
    public void remove(Component... component);
    public Component getComponent(int index);


}
