package HandlerInfoCountry;

import java.io.*;
import java.lang.reflect.Array;
import java.nio.CharBuffer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.nio.file.Files.*;

public class HandlerInfoCountry<T> {
    public List<CountryInfo> infoCountryList = new ArrayList<>();
    public String text = "";
    public String file_name = "";
    public  List<List<String>> listsInfoTemp = new ArrayList<List<String>>() ;

    public HandlerInfoCountry(){}
    public HandlerInfoCountry(String file_name){
        this.file_name = file_name;
    }

    //считвание файлов
    public void fileRead(String... files)  {
        if(files == null||files.equals("[]")||files.equals("[ ]")|| files.equals("{}") || files.equals("{ }")) {
            System.out.println("Файлы для считывания не заданы! Выход.");
            System.exit(0);
        }
        for (String fname: files
        ) {
            String temp = null;
            try {
                temp = Files.readString(Paths.get(fname));
            } catch (IOException e) {
                e.printStackTrace();
            }
            String[] tempArrStr = temp.split(" ");
            listsInfoTemp.add(Arrays.asList(tempArrStr));
        }
    }

    public void setCountryInfoList() {
            int lenMax = 0;
            for (List<String> list: listsInfoTemp   //на случай если кол. country != capital
            ) {
                lenMax = lenMax>list.size()?lenMax:list.size();
            }

            for(int i =0; i < lenMax; i++)
                infoCountryList.add(new CountryInfo());

            for (List<String> list: listsInfoTemp
            ) {
                for (int i = 0; i < list.size(); i++) {
                    if (infoCountryList.get(i).getCountry() == null)
                        infoCountryList.get(i).setCountry(list.get(i));
                    else if (infoCountryList.get(i).getCapital() == null)
                        infoCountryList.get(i).setCapital(list.get(i));
                }
            }
        Collections.sort(infoCountryList);
    }

    //запись в несколько файлов
    public void fileWrite(String ...namesFile) {
        for (String fname: namesFile
        ) {
            File newFile = new File(fname);

            try {
                newFile.createNewFile();
                FileWriter writer = new FileWriter(fname);
                writer.write(infoCountryList.toString());
                writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public String getText(){
        if(infoCountryList.size() == 0) try {
            throw new Exception("Файл для считывания не задан!");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return infoCountryList.toString();
    }

    public void displayInfo() {

        infoCountryList.forEach(x -> System.out.println(x.toString()));
    }
}