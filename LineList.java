/** 
* 
* Student name: Landon Duba
* Completion date: 7/14/2024
*
* LineList.txt: the template file of LineList.java
* Student tasks: implement tasks as specified in this file
*
* LineList class is a linked-base list that represents the contents of a document
*
*/

//Import io library to handle possible errors in the load and save methods
import java.io.*;
//Import Scanner library to handle reading and saving the input on the file
import java.util.Scanner;

public class LineList {

   private Node head;

   public LineList() {   //constructor
      head = null;
   }
   //Don't modify anything before this line. 
   //Do not add any other instance variables.


   // *** Student tasks: implement the following methods *** 

   public void empty() {
      // delete all lines in the editor
      //Set the head of the LL to null, removing all lines
      head = null;
   }

   public void load(String fileName, boolean append) throws FileNotFoundException {
      // append is true, read lines and add to existing list,
      // otherwise, create new list.
      // Each line is stored in a Node.
      // You may need to handle exception within this method
      // or throw runtime exception only.
      Scanner fileScanner = new Scanner(new File(fileName));
      //If the user doesn't want an existing file, empty the editor
      if (!append) {
         empty();
      }
      Node current = head;
      //reading the file
      while (fileScanner.hasNextLine()) {
         String line = fileScanner.nextLine();
         Node addedNode = new Node(line);

         if (head == null) {
            head = addedNode;
            current = head;
         } else {
            current.setNext(addedNode);
            current = addedNode;
         }
      }
      fileScanner.close();
   }

   public void save(String fileName) throws IOException {
      // Save all lines represented with Nodes to a file.
      // Each line (Node) occupies a line in the saved file.
      // You may need to handle exception within this method
      // or throw runtime exception only.
      PrintWriter newFile = new PrintWriter(fileName);
      Node tempNode = head;
      //print lines to the file to save it
      while (tempNode != null) {
         newFile.println(tempNode.getLine());
         tempNode = tempNode.getNext();
      }
   }

   public void addLine(String line) {
      //append the line to the end of the list
      Node newNode = new Node(line);
      //if the list is empty, make it the head
      if (head == null) {
         head = newNode;
         return;
      }
      //if the list already exists, add it to the end
      Node tempNode = head;
      while (tempNode.getNext() != null) {
         tempNode = tempNode.getNext();
      }
      tempNode.setNext(newNode);
   }

   public void addLine(String line, int n) {
      //insert new line to nth line, if n > total number of line,
      //append to the end of the list.
      Node newNode = new Node(line);
      //check if the line should become the head
      if (n <= 1) {
         newNode.setNext(head);
         head = newNode;
         return;
      }

      Node currNode = head;
      //if n is not the head, iterate through the list until n
      for (int i = 1; i < n - 1 && currNode != null; i++) {
         currNode = currNode.getNext();
      }

      if (currNode == null || currNode.getNext() == null) {
         addLine(line);
      } else {
         newNode.setNext(currNode.getNext());
         currNode.setNext(newNode);
      }
   }

   public int words() {
      // count number of words, word may be separated with
      // \t,;.?!-@spaces ' * and "
      if (head == null){
         return 0;
      }
      int count = 0;
      Node currNode = head;
      //loop through each line, splitting the line up by the characters in the split function
      while(currNode != null){
         String[] words = currNode.getLine().split("[\\t;,.?!\\-@:\\s'\"*]+");
         for (String word : words) {
            if (!word.trim().isEmpty()) {
               count++;
            }
         }
         currNode = currNode.getNext();
      }
      return count;
   }

   public int lines() {
      // count number of lines, which is the same as the number of
      // nodes in the list.
      Node currNode = head;
      int count =0;
      //loop through all lines in the list
      while (currNode != null){
         count++;
         currNode = currNode.getNext();
      }
      return count;
   }

   public void delete(int n) {
      //delete nth line if exists. Otherwise do nothing.
      //if n is invalid then do nothing
      if (n < 1 || head == null) {
         return;
      }
      //if n is the head, then remove it
      if (n == 1) {
         head = head.getNext();
         return;
      }

      Node currNode = head;
      //iterate through all elements until the given one, then delete it
      for (int i=1; i < n - 1 && currNode.getNext() !=null; i++) {
         currNode = currNode.getNext();
      }

      if (currNode.getNext() == null) {
         return;
      }

      currNode.setNext(currNode.getNext().getNext());
   }

   public String toString() {
      // Return all lines represented by Nodes in the list. All lines
      // are in the same order as their corresponding nodes in the list.
      // All lines are separated with \n. No newline character should be
      // added after the last line.
      //if the list is empty, return an empty string
      if (head == null) {
         return "";
      }
      //use string builder to repeatedly add to the output
      StringBuilder res = new StringBuilder();
      Node currNode = head;
      while (currNode != null) {
         res.append(currNode.getLine());
         if (currNode.getNext() != null) {
            res.append("\n");
         }
         currNode = currNode.getNext();
      }
      return res.toString();
   }

   public void print() {
      // Print each line. Each line is proceeded with its corresponding line
      // numbers. Please refer to sample output.
      int count = 1;
      Node currNode = head;
      //update the count for each iteration and print the line with the count
      while (currNode != null) {
         System.out.println(count + ". " +currNode.getLine());
         currNode = currNode.getNext();
         count++;
      }
   }

   public void replace(String s1, String s2) {
      // Replace all occurrences of s1 with s2.
      Node currNode = head;
      //loop through each line, but fill in the replacement
      while (currNode != null) {
         currNode.setLine(currNode.getLine().replace(s1, s2));
         currNode = currNode.getNext();
      }
   }

   public void line(int n) {
      //get the number of lines, and check if the input is valid
      int numLines = lines();
      if (n < 1 || n > numLines) {
         System.out.println("Line " + n + " does not exist.");
         return;
      }
      //if the input is valid, print out the line
      Node currNode = head;
      for (int i = 1; i < n; i++) {
         currNode = currNode.getNext();
      }
      System.out.println(currNode.getLine());
   }
}
