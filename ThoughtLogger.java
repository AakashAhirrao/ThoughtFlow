package JavaIO;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class ThoughtLogger {

    static String filepath = "";

    public static void fileInitializer(){

        String timestamp = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));

        filepath =  "record_"+timestamp+".txt";
    }

    public static String userInput(){

        String text = "";
        try(Scanner sc = new Scanner(System.in)) {

            System.out.println("Welcome to Thought Logger!!! 💭💭");
            System.out.println("What's on your mind today");
            System.out.println();
            System.out.print("--> ");
            text = sc.nextLine();
        }
        catch (Exception e){
            System.out.println("Something went wrong while taking input");
        }
        return text;
    }

    public static void FileLogger(String text, String filepath){
        try(OutputStreamWriter writer = new OutputStreamWriter(
                new FileOutputStream(filepath))){

            writer.write(text);
        }
        catch (FileNotFoundException e){
            throw new RuntimeException(e);
        }
        catch (IOException e){
            System.out.println("Something went wrong while logging the thoughts");
        }
    }

    public static void recordReader(String filepath){
        try(InputStreamReader reader = new InputStreamReader(
                new FileInputStream(filepath))){
            int ch;
            while((ch = reader.read()) != -1){
                System.out.print((char) ch);
            }

        }
        catch (FileNotFoundException e){
            throw new RuntimeException(e);
        }
        catch (IOException e){
            System.out.println("Something went wrong while reading your thoughts :(");
        }
    }

    public static void main(String[] args){

        fileInitializer();
        FileLogger(userInput(), filepath);
        recordReader(filepath);
    }
}
