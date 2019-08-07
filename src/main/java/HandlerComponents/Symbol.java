package HandlerComponents;

import java.util.*;

public class Symbol implements Component{
    private String character;

    public Symbol(String character) {
        if(character.length()<=1)
            this.character = character;
    }

    public Symbol() {  }

    public String value() {
        return character.toString();
    }
    public void display(){
        System.out.print(value());
    }

    public void add(Component... component) {
        throw new UnsupportedOperationException();
    }

    public void remove(Component... component) {
        throw new UnsupportedOperationException();
    }

    public Component getComponent(int index) {
        throw new UnsupportedOperationException();
    }
}
