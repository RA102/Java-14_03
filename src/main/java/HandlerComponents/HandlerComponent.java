package HandlerComponents;

import java.util.List;
import java.util.Scanner;

enum Level {PARAGRAPH, SENTENCE, WORD, SYMBOL}

public class HandlerComponent {
    public  String text;
    public Paragraph paragraph = new Paragraph();
    public Sentence sentence = new Sentence();//предлож.
    public Word word = new Word();    //слова
    public Symbol symbol = new Symbol();
    Scanner scanner = new Scanner(System.in);
    //private String[] arrStr;
    Level level;

    public HandlerComponent(){  }
    public HandlerComponent(String text){
        setText(text);
    }
    public void go(){
        text = "В лесу родилась елочка, в лесу она росла. Зимой и летом стройная, зеленая была";
        System.out.println("Текст по умолчанию:");
        System.out.println(text);

        do{
            dialogSetText();
            //dialogSetLevel();

            System.out.println("Выйти?");
            scanner.nextLine();
            if(scanner.nextLine().equals("y")) break;
        }while (true);

    }
    private  void dialogSetText() {
        String changeTextKey = "n";
        System.out.println("Изменить текст?");

        changeTextKey = scanner.nextLine();
        if (changeTextKey.equals("y")){
            System.out.println("Введите новый текст:");
            setText(scanner.nextLine());
        }
        else return;
    }
    public void setLevel(){
        level = Level.PARAGRAPH; paragraph.run(text);
    }
    public void dialogSetLevel(){
        System.out.println("Выберите уровень обработки текста (Каждый уровень включает нижестоящие. Рекомендуется №1:");
        System.out.println("1) Paragraph \n2)Sentence \n3)Word \n4)Symbol");
        int levelInt = scanner.nextInt();
        switch (levelInt){
            case 1: level = Level.PARAGRAPH; paragraph.run(text); break;
            case 2: level = Level.SENTENCE; sentence.run(text); break;
            case 3: level = Level.WORD; word.run(text); break;
            case 4: level = Level.SYMBOL; symbol = new Symbol(text); break;
            default:
                System.out.println("Ошибка ввода!");
        }
    }
    public void setText(String text){
        this.text = text;
    }

    public void showComponent(){
        switch (level){
            case PARAGRAPH: paragraph.display(); break;
            case SENTENCE: sentence.display(); break;
            case WORD: word.display(); break;
            case SYMBOL: symbol.display();
        }
    }
    public List<Component> getPart(){
        if(level == null)  setLevel();     //dialogSetLevel();
        switch (level){
            case PARAGRAPH:
                try {
                    return paragraph.getComponents();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case SENTENCE:
                try {
                    return paragraph.getComponents();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case WORD:
                try {
                    return sentence.getComponents();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case SYMBOL:
                try {
                    return word.getComponents();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
                default:
                    System.out.println("Ошибка ввода!");
        }
        return null;
    }

    public String[] getPart(String key){
        switch (key){
            case "paragraph": return new String[]{paragraph.value()};
            case "sentence": return new String[]{sentence.value()};
            case "word": return new String[]{word.value()};
            case "symbol": return new String[]{symbol.value()};
        }
        return null;
    }

}
