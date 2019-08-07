package HandlerComponents;

import java.util.StringTokenizer;

public class Sentence extends Composite {
    String text;
    public Sentence(){}
    public Sentence(String text){
        if(text.contains("!")|| text.contains(".")|| text.contains("?"))
            try {
                throw new Exception("Внимание недопустимые имеются разделители внутри предложения!");
            } catch (Exception e) {
                e.printStackTrace();
            }this.text = text;
    }
    public void run(String text){
        if(this.text == null)
            setText(text);
        descent();
        ascent();

    }
    public void setText(String text) {
        if (text.contains("!") || text.contains(".") || text.contains("?"))
            try {
                throw new Exception("Внимание имеются недопустимые разделители внутри предложения!");
            } catch (Exception e) {
                e.printStackTrace();

            }this.text = text;
    }

    private void spliteText(){
        String firstPoints = ", ";
        StringTokenizer tokenizer = new StringTokenizer(text, firstPoints, true);//
        String curr = "";
        while (tokenizer.hasMoreTokens()){
            try {
                curr = tokenizer.nextToken();
                if(curr.equals(" ")||curr.equals(","))
                    getComponents().add(new Symbol(curr) );
                else
                    getComponents().add(new Word(curr));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void descent(){
        spliteText();
    }
    public void ascent(){
        try {
            for (Component component: getComponents()
            ) {

                if(component instanceof Word){
                    ((Word) component).descent();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

