import java.io.*;
import java.util.Scanner;


public class LineEditor {
    //Make LineList object to use the function from the lineList class
    private static LineList lineList = new LineList();
    private static Scanner scanner = new Scanner(System.in);

    //Function needed to print all options when the program is opended
    private static void startScreen(){
        System.out.println("Menu: m");
        System.out.println("Load File: load fileName appendOption(1-append, 0-new list)");
        System.out.println("Print: p");
        System.out.println("Display number of lines: lines");
        System.out.println("Display line: line lineNumber");
        System.out.println("Count words: words");
        System.out.println("Delete line: del lineNumberToDelete");
        System.out.println("Append line: a");
        System.out.println("Insert line: i lineNumberToInsert");
        System.out.println("Clear document: cls");
        System.out.println("Replace words: rep originalWord newWord");
        System.out.println("Save to a file: s fileName");
        System.out.println("Quit: quit");
    }

    //function to check which command the user input
    private static void checker(String command) {
        //split the input up to see what parameters were added
        String[] input = command.split(" ");
        try {
            if (input[0].equals("m")) {
                startScreen();
            } else if (input[0].equals("load")) {
                boolean append = input[2].equals("1");
                lineList.load(input[1], append);
            } else if (input[0].equals("s")) {
                lineList.save(input[1]);
            } else if (input[0].equals("a")) {
                System.out.print("Type a line: ");
                String line = scanner.nextLine();
                lineList.addLine(line);
            } else if (input[0].equals("i")) {
                System.out.print("Type a line: ");
                String insertLine = scanner.nextLine();
                int lineNumber = Integer.parseInt(input[1]);
                lineList.addLine(insertLine, lineNumber);
            } else if(input[0].equals("words")){
                System.out.println("Number of words: " + lineList.words());
            } else if (input[0].equals("lines")) {
                System.out.println("Number of lines: " + lineList.lines());
            } else if (input[0].equals("del")) {
                int lineNumberToDelete = Integer.parseInt(input[1]);
                lineList.delete(lineNumberToDelete);
            } else if(input[0].equals("p")){
                lineList.print();
            } else if (input[0].equals("rep")){
                lineList.replace(input[1], input[2]);
            } else if (input[0].equals("line")) {
                int lineNumberToDisplay = Integer.parseInt(input[1]);
                lineList.line(lineNumberToDisplay);
            } else if (input[0].equals("cls")) {
                lineList.empty();
            } else {
                System.out.println("Unknown command: " + command);
            }
        } catch(Exception e){
            System.out.println("Error executing command: " +e.getMessage());
        }
    }




    public static void main(String[] args) {
        //Find the file if entered as a command argument
        if(args.length > 0) {
            try {
                lineList.load(args[0], false);
            } catch (FileNotFoundException e) {
                System.out.println("File not found: " + args[0]);
            }
        }
        //show the initial menu
        startScreen();

        while(true) {
            System.out.print("->");
            String command = scanner.nextLine().trim();
            if (command.equals("quit")){
                break;
            }
            checker(command);
        }
    }

}
