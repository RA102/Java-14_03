import HandlerComponents.HandlerComponent;
import HandlerInfoCountry.HandlerInfoCountry;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

public class Main {
    static ServerSocket listener = null;
    static Socket client = null;
    static ObjectInputStream in = null;
    static ObjectOutputStream out = null;
    static int port = 12345;
    static String msg = "";

    public static void main(String[] args) throws Exception {
        //1-ручн. режим
//        MyServer server = new MyServer("");
//        server.component.showComponent();
//
//        System.out.println("!!!");
//        String[] arr = server.component.getPart("sentence");       //("word");
//        for (String str: arr
//             ) {
//            System.out.println(str);
//        }
        //2
        MyApp.go();
    }
}
