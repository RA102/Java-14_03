import HandlerComponents.Component;
import HandlerComponents.Composite;
import HandlerComponents.HandlerComponent;
import HandlerInfoCountry.HandlerInfoCountry;
import org.graalvm.compiler.bytecode.BytecodeStream;

import java.awt.*;
import java.io.*;
import java.net.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

import static java.lang.System.in;
import static java.lang.System.setOut;

public class MyServer {
    ServerSocket listener = null;
    Socket client = null;
    ObjectInputStream in = null;
    ObjectOutputStream out = null;
    int port = 12345;
    String msgIn = "";
    String msgOut = "";
    String text;
    String file_name;
    HandlerInfoCountry infoCountry = new HandlerInfoCountry();
    HandlerComponent component = new HandlerComponent();
    boolean flagRecievUDP = false;

    public MyServer(){}
    public MyServer(String text){
        this.text = text;
        setTextComponents();
    }

    private void setTextComponents(){
        if(text == null || text.equals("") || text.equals("[]")  || text.equals("[ ]")) {
            if(infoCountry.getText() != null && !infoCountry.getText().equals("") &&
                    !infoCountry.getText().equals("[]") && !infoCountry.getText().equals("[ ]")){
                text = infoCountry.getText();
                component.setText(text);
            }
            else{                                                   //no CountryInfo!
                System.out.println("Выберите способ ввода текста");
                System.out.println("1) Ввод текста вручную;  2) Использовать текст из полученного пакета UDP");
                int key = 0;
                Scanner scanner = new Scanner(System.in);
                key = Integer.parseInt(scanner.nextLine());
                if(key == 1) setTextFileWrite();
                else {
                    outPackFileWrite();
                }
            }
        }
        component.setText(text);
        try {
            component.setLevel();          //dialogSetLevel();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void setTextFileWrite(){     //no CountryInfo!
        System.out.println("Введите текст для сохранения на компьютере");
        Scanner in = new Scanner(System.in);
        text = in.nextLine();
        inputFileName();
        fileWrite(file_name);
    }

    private void inputFileName() {
        System.out.println("Введите название файла (путь) для сохранения на компьютере");
        Scanner inFile = new Scanner(System.in);
        file_name = inFile.nextLine();
    }
    //запись до несколько файлов
    public void fileWrite( String...file_name){
        for (String fname: file_name
        ) {
            File newFile = new File(fname);
            try {
                newFile.createNewFile();
                FileWriter writer = new FileWriter(fname);
                writer.write(text);
                writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void outPackFileWrite(){
        if(!flagRecievUDP){
            try {
                receiveUDPMessage();
            } catch (SocketException e) {
                e.printStackTrace();
            }
            inputFileName();
        }
        else System.out.println("No packet. Exit!");
    }

    public List<Component> getPart(){
        return component.getPart();
    }
    public String[] getPart(String key){
        return component.getPart(key);
    }

    void exchangeTCPMessage(){
        setTextComponents();
        while (true) {
            try {
                listener = new ServerSocket(port);
                System.out.println("Waiting for connection");
                client = listener.accept();
                System.out.println("Client connected " + client.getInetAddress().getHostName());
                out = new ObjectOutputStream(client.getOutputStream()); //от (сервера)
                out.flush();
                in = new ObjectInputStream(client.getInputStream());    //в (сервер)
                do {
                    try {
                        sendMessage("Введите тип чанка информации: symbol, word или sentence");
                        msgIn = (String) in.readObject();
                        System.out.println("client> " + msgIn);
                        Arrays.stream(getPart(msgIn)).forEach(x->sendMessage(x));
                    } catch (ClassNotFoundException ex) {
                        ex.printStackTrace();
                    }
                } while (!msgIn.equals("exit"));
            } catch (IOException ex) {
                ex.printStackTrace();
            } finally {
                try {
                    if (in != null)
                        in.close();
                    if (out != null)
                        out.close();
                    if (listener != null)
                        listener.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    void sendMessage(String msgOut){
        try {
            out.writeObject(msgOut);
            out.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void receiveUDPMessage() throws SocketException {
        byte[] data = new byte[Short.MAX_VALUE];
        DatagramPacket pac = new DatagramPacket(data, data.length);
        DatagramSocket socket = new DatagramSocket(port);
        try {
            socket.setSoTimeout(6000);
            while (true){
                socket.receive(pac);
                flagRecievUDP = true;
                text = new String(pac.getData());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
