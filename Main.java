import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Main {
  public static void main(String[] args) throws IOException
  {
    // Initialize Objects
    DLList list = new DLList();
    Scanner scan = new Scanner(System.in);
    boolean fileFound = false;
    // FileWriter myWriter = new FileWriter("almamater.txt");

    // Ask for file
    while (fileFound == false) {
    System.out.println("Enter what file you would like to access (case sensitive, do NOT enter .txt at the end): ");
    String name = scan.nextLine() + ".txt";
     if (name.equals("almamater.txt")) {
       fileFound = true;
       // Accesses File
       try {
        File myObj = new File(name);
        Scanner myReader = new Scanner(myObj);

        // Assign lines to Linked List
        while(myReader.hasNextLine()) {
          String data = myReader.nextLine();
          list.insertAtTail(data);
        }
         
        // Filler Nodes
        list.insertAtHead("Beginning of File");
        list.insertAtTail("End of File");

        // pointers and iterators 
        DLList.Node pointer = list.getNext(list.getFirst());

        
        String input = "";
        do { // all the inputs and their code
           System.out.print(list.getData(pointer)+"\n>");
            input = scan.nextLine();
          //System.out.println ("input = " + input);

            // Get next line
            if(input.equals("f")){
              if(list.getNext(pointer)!=null){
               pointer = list.getNext(pointer); 
              }
            }

            // Go a line back
            else if(input.equals("b")) {
              if(list.getPrev(pointer)!=null){
                pointer = list.getPrev(pointer);
              }
            }

            // Delete current line
            else if(input.equals("d")) {
              
              if ((pointer != list.getFirst()) && (pointer != list.getLast()))
              {
                System.out.println("delete-->" + list.getData(pointer));
                DLList.Node placeholder = list.getNext(pointer);
                list.deleteNode(list.getData(pointer));
                pointer = placeholder;
                
              }
              else {System.out.println("Cannot Delete This, Please Try Something Else");}  
            }

            // Go to first line
            else if(input.equals("h")) {
              pointer = list.getNext(list.getFirst());
            }

            // Go to last line
            else if(input.equals("t")) {
              pointer = list.getPrev(list.getLast()); 
            }

            // Insert line after current, display inserted line
            else if(input.equals("i")) {
              if (list.getData(pointer) != "End of File")
              {
                System.out.print("insert> ");
                list.insertAfter(pointer, scan.nextLine());
                pointer = list.getNext(pointer);
              }
              else {System.out.println("Cannot Add Here, Go Back to Add");}
            }

            // Print rest of the lines
            else if(input.equals("p")) {
              if (pointer != list.getLast())
              {
                System.out.print("Amount of Lines> ");//exception error in input
                int num = scan.nextInt(); // scan me
                System.out.println("--Beginning of Excerpt--");
                if (pointer == list.getFirst()){pointer=list.getNext(pointer);}
                for (int i = 0; (i<num)&&(pointer != list.getLast()); i++)
                {
                  System.out.println(list.getData(pointer));
                  pointer = list.getNext(pointer); 
                }
                scan.nextLine();
                System.out.println("--End of Excerpt--\n");
              }
              else{System.out.println("Can't Print This");}
              pointer = list.getPrev(pointer);
            }
            else {
              if (!input.equals("q"))
              System.out.println("Invalid Entry\n");
            }
          } while(!input.equals("q"));

         // Quit the program
         if (input.equals("q")) {
          FileWriter myWriter = new FileWriter("almamater.txt");
          myWriter.flush(); // Clear the text file
          pointer = list.getNext(list.getFirst());
          // Insert new lines to the file
          while (pointer != list.getLast()) {
            myWriter.write(list.getData(pointer) +"\n");
            pointer = list.getNext(pointer);
          }
          System.out.println("File has been Edited and Program has been Quit");
           myWriter.close();
         }            
        myReader.close();
       }
       // If error with reading file
       catch (FileNotFoundException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
       }
     }
     // If file is not found
     else {
       System.out.println("File not found; try again.");
     }
   }
   scan.close(); 
  }
}