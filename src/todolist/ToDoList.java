/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todolist;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Formatter;
import java.util.Iterator;
import java.io.*;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;


/**
 *
 * @author Artem
 */
public class ToDoList {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    	public static void main (String [] args) throws IOException {
            List<ToDoListItem> todoList = new ArrayList<>();                                  
                      
            ArrayList<String> things = new ArrayList<>();
            //List<List<String>> things = new ArrayList<>();
            File file = new File("list.txt");
            FileWriter writer = new FileWriter(file, true);
            readFileToArrayList(file, things);
            Scanner in = new Scanner(System.in);
            Scanner str = new Scanner(System.in);
            Scanner bool = new Scanner(System.in);
            String text = "Input number (1 - add thing, 2 - show things, 3 - delate thing, 4 - exit): ";
            int number = 0;
            String name;
            while (number != 4) {
                System.out.println(text);
		number = in.nextInt();
                switch(number){
                    case 1:
                        System.out.println("add thing: ");                     
                        todoList.add(new ToDoListItem() {{
                            Text = str.nextLine();
                            //Complete = false;
                            //BeginDate = new Date(2017, 8, 25);
                            //EndDate = new Date(2017, 8, 26);
                        }});                       
                        break;
                    case 2:                        
                        todoList.forEach((todoItem) -> {                            
                            System.out.println(todoItem.Text);
                        });
                        break;
                    case 3:
                        System.out.println("Etnter the number of delating thing: ");
                        number = in.nextInt();
                        things.remove(number);
                        number = 0;
                        printList(things);
                        break;
                    default:
                        System.out.println("It's not correct number");
                }		
            }
            //writeArrayListToFile(file, things);
            //writeListToFile(file, todoList);
            FileOutputStream fileOutStream = new FileOutputStream(file);
            ObjectOutputStream objectOutStream = new ObjectOutputStream(fileOutStream);
            objectOutStream.writeObject(todoList);
            objectOutStream.close();
            //OutputStream outStream = new FileOutputStream(file);
                    //outStream.write(todoList);
                
	}

	public static void writeLine(File file, String name) throws IOException {
		FileWriter writer = new FileWriter(file, true);
                try (BufferedWriter bufferWriter = new BufferedWriter(writer)) {
                    bufferWriter.write(name);
                    bufferWriter.newLine();                    
                }
	}
	public static void readFile(File file) throws IOException {
		Scanner listFile = new Scanner(file);
                while(listFile.hasNext()){
                    System.out.println(listFile.next());
                }
	}
        public static void writeListToFile(File file, List things) throws IOException {
                    Iterator <String> iteratorThings = things.iterator();
                    Formatter f = new Formatter(file);
                    f.format("%s","");
                    while(iteratorThings.hasNext()){
                        String line = iteratorThings.next();
                        writeLine(file, line);
                    }                
                }
	public static void writeArrayListToFile(File file, ArrayList things) throws IOException {
                    Iterator <String> iteratorThings = things.iterator();
                    Formatter f = new Formatter(file);
                    f.format("%s","");
                    while(iteratorThings.hasNext()){
                        String line = iteratorThings.next();
                        writeLine(file, line);
                    }                
                }
        public static ArrayList readFileToArrayList(File file, ArrayList things) throws IOException {
		Scanner listFile = new Scanner(file);
                things.clear();
                while(listFile.hasNext()){
                    things.add(listFile.next());
                }
		return(things);
	}
        public static void printList (ArrayList things) {
            int a = 0;
            while (a != things.size()) {
                System.out.println(a + " - " + things.get(a));
                a++;
            }
        }
}
class ToDoListItem implements Serializable {
    public String Text;
    //public boolean Complete;
    //public Date BeginDate;
    //public Date EndDate;
    
}
