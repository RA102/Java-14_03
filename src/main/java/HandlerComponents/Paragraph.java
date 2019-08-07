package HandlerComponents;

import java.util.StringTokenizer;

public class Paragraph extends Composite {
    String text;
    public Paragraph(){}
    public Paragraph(String text){
        this.text = text;
    }

    public void run(String text){
        if(this.text == null)
            setText(text);
        descent();
        ascent();

    }
    public void setText(String text) {
        this.text = text;
    }


    private void spliteText(){
        String firstPoints = ".!?";
        StringTokenizer tokenizer = new StringTokenizer(text, firstPoints, true);//
        while (tokenizer.hasMoreTokens()){
            try {
                getComponents().add(new Sentence(tokenizer.nextToken()));
            } catch (Exception e) {
                e.printStackTrace();
            }
            if(!tokenizer.hasMoreTokens()) break;
            try {
                getComponents().add(new Symbol(tokenizer.nextToken()) );
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void descent() {
        spliteText();
        try {
            for (Component component : getComponents()
            ) {
                if (component instanceof Sentence) {
                    ((Sentence) component).descent();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void ascent(){
        try {
            for (Component component: getComponents()
            ) {
                if(component instanceof Sentence){
                    ((Sentence) component).ascent();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
