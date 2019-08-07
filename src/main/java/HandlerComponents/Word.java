package HandlerComponents;

public class Word extends Composite {
    String text;
    public Word(){}
    public Word(String text){
        if(text.contains(" ")||text.contains("!")|| text.contains(".")|| text.contains("?")||text.contains(","))
            try {
                throw new Exception("Внимание имеются разделители внутри слова!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        this.text = text;
    }
    public void run(String text){
        if(this.text == null)
            setText(text);
        descent();
    }

    public void setText(String text) {
        if(text.contains(" ")||text.contains("!")|| text.contains(".")|| text.contains("?")||text.contains(","))
            try {
                throw new Exception("Внимание имеются разделители внутри слова!");
            } catch (Exception e) {
                e.printStackTrace();
            }this.text = text;
    }

    public void descent(){
        String[] arrSymbol = text.split("");
        for (String symbol: arrSymbol
        ) {
            try {
                getComponents().add(new Symbol(symbol));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
