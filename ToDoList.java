import java.util.ArrayList;
import java.util.Scanner;
import java.util.Formatter;
import java.util.Iterator;
import java.io.*;

class ToDoList {
    	public static void main (String [] args) throws IOException {
            ArrayList<String> things = new ArrayList<>();
            File file = new File("list.txt");
            FileWriter writer = new FileWriter(file, true);
            readFileToArrayList(file, things);
            Scanner in = new Scanner(System.in);
            Scanner str = new Scanner(System.in);
            String text = "Input number (1 - add thing, 2 - show things, 3 - delate thing, 4 - exit): ";
            int number = 0;
            String name;
            while (number != 4) {
                System.out.println(text);
		number = in.nextInt();
		if (number == 1) {
                    System.out.println("add thing: ");
                    name = str.nextLine();
                    things.add(name);
		} else {
                    
                    if (number == 2) {
			    if (number == 2) {                            
                            	printList(things);
                            }                                        
                    } else {
                        if (number == 3) {
                            System.out.println("Etnter the number of delating thing: ");
                            number = in.nextInt();
                            things.remove(number);
			    number = 0;
                            printList(things);
                        }
                            }
			}
            }
            writeArrayListToFile(file, things);
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