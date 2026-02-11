package JavaIO;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class ThoughtLogger {

    static String filepath = "records.txt";

/*    public static void fileInitializer() {

        String timestamp = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));

        filepath = "record_" + timestamp + ".txt";
    }*/

    public static String userInput(Scanner sc) {

        StringBuilder text = new StringBuilder();
        try {

            System.out.println("Welcome to Thought Logger!!! 💭💭");
            System.out.println("What's on your mind today (Type 'Q' on a new line to finish)");
            System.out.println("-----------------------------------------------------------");
            System.out.print("--> ");
            while (true) {
                String line = sc.nextLine();

                if (line.equalsIgnoreCase("Q")) {
                    System.out.print("Exiting Notes.....");
                    System.out.print("\nSaving Notes.");
                    for (int i = 0; i < 3; i++) {
                        System.out.print(".");
                        Thread.sleep(1000);
                    }
                    System.out.println("\n----------------------------------------------------------------");
                    System.out.printf("Date: %s | Time: %s", LocalDate.now(), LocalTime.now());
                    System.out.println("\n----------------------------------------------------------------");
                    System.out.print("NOTE SAVED...");
                    break;
                }

                text.append(line).append(System.lineSeparator());
            }

        } catch (InterruptedException e) {
            System.out.println("Program was interrupted when saving");
        } catch (Exception e) {
            System.out.println("Something went wrong while taking input");
        }
        return text.toString();
    }

    public static void FileLogger(String text, String filepath) {

        String dateStr = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String timeStr = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));

        try (OutputStreamWriter writer = new OutputStreamWriter(
                new FileOutputStream(filepath), StandardCharsets.UTF_8)) {
            writer.write("[ENTRY START]\n");
            writer.write("Date: " + dateStr + "\n");
            writer.write("Time: " + timeStr + "\n");
            writer.write("------------------------------------\n");
            writer.write(text);
            writer.write("------------------------------------\n");
            writer.write("[ENTRY END]\n");
            writer.write("\n");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            System.out.println("Something went wrong while logging the thoughts");
        }
    }

    public static void recordReader(String filepath) {
        try (InputStreamReader reader = new InputStreamReader(
                new FileInputStream(filepath), StandardCharsets.UTF_8)) {
            int ch;
            while ((ch = reader.read()) != -1) {
                System.out.print((char) ch);
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            System.out.println("Something went wrong while reading your thoughts :(");
        }
    }

    public static void main(String[] args) {

        try (Scanner sc = new Scanner(System.in)) {

//            fileInitializer();
            FileLogger(userInput(sc), filepath);
//            recordReader(filepath);
        }
    }
}
