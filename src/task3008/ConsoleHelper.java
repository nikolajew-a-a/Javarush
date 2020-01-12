package task3008;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleHelper {
    static private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    static public void writeMessage(String message){
        System.out.println(message);
    }

    static public String readString(){
        try {
            return reader.readLine();
        }
        catch (IOException e){
            System.out.println( "Произошла ошибка при попытке ввода текста. Попробуйте еще раз.");
            return readString();
        }
    }

    static public int readInt(){
        try {
            return Integer.parseInt(readString());
        }
        catch (NumberFormatException e){
            System.out.println( "Произошла ошибка при попытке ввода числа. Попробуйте еще раз.");
            return readInt();
        }
    }
}
