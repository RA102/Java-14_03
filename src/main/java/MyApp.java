import HandlerComponents.HandlerComponent;
import HandlerInfoCountry.HandlerInfoCountry;

import java.net.SocketException;
import java.util.Arrays;
import java.util.Scanner;

public class MyApp {
    static HandlerInfoCountry infoCountry = new HandlerInfoCountry();
    static HandlerComponent component= new HandlerComponent();
    static MyServer server = new MyServer();
    static String keyWord;

    public static void go(){
        Scanner scan = new Scanner(System.in);
        //server.component.dialogSetLevel();
        do {
            System.out.println("Выберите операцию:");
            System.out.println("1) Сохранить тестовый файл CountryInfo на компьютере");
            System.out.println("2) Показать массив компонентов файла CountryInfo по ключевому слову");
            System.out.println("3) Отправить клиенту данные по сокету");
            System.out.println("4) Получить пакет DataGramPacket по UDP");
            System.out.println("5) Cохранить полученный пакет в  файл на компьютере");
            System.out.println("6) Показать массив компонентов полученного пакета");
            System.out.println("7) Выход");
            int choice = scan.nextInt();
            switch (choice){
                case 1:
                    myDefaultFileWrite();
                    System.out.println("Файл записан в папку по умолчанию");
                    break;
                case 2:
                    //setKeyWord();
                    showCountryComponent(keyWord);
                    break;
                case 3:
                    server.exchangeTCPMessage();
                    break;
                case 4:
                    try {
                        server.receiveUDPMessage();
                    } catch (SocketException e) {
                        e.printStackTrace();
                    }
                    break;
                case 5:
                    if(server.flagRecievUDP)
                        server.outPackFileWrite();
                    else System.out.println("No packet. Exit!");
                    break;
                case 6:
                    setKeyWord();
                    Arrays.stream(server.getPart(keyWord)).forEach(x-> System.out.println(x)); ;
                    break;
                default:
                    System.out.println("Ошибка ввода!");
            }
        }while (true);
    }

    private static void setKeyWord() {
        System.out.println("Введите ключевое слово (symbol, words, sentences, paragraph) для вывода текста");
        Scanner scanner = new Scanner(System.in);
        keyWord = scanner.nextLine();
    }

    private static void myDefaultFileWrite(){
        infoCountry.fileRead("countries.txt", "capitals.txt");
        infoCountry.setCountryInfoList();
        infoCountry.fileWrite("new.txt");
    }

    private static void showCountryComponent(String keyWord){
        component.setText(infoCountry.getText());
        try {
            component.dialogSetLevel();
        } catch (Exception e) {
            e.printStackTrace();
        }
        component.showComponent();
    }

}
