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
     * @throws java.lang.ClassNotFoundException
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        File file = new File("list.txt");
        FileInputStream fileInStream = new FileInputStream(file);
        ObjectInputStream objectInStream = new ObjectInputStream(fileInStream);
        List<ToDoListItem> todoList = (ArrayList<ToDoListItem>) objectInStream.readObject();
        objectInStream.close();
        String text = "Input number (1 - add thing, 2 - show things, 3 - delete thing, 4 - exit): ";
        int number = 0;
        while (number != 4) {
            System.out.println(text);
            Scanner in = new Scanner(System.in);
            number = in.nextInt();
            switch (number) {
                case 1:
                    System.out.println("add thing: ");
                    Scanner str = new Scanner(System.in);
                    String line = str.nextLine();
                    todoList.add(new ToDoListItem() {
                        {
                            Text = line;
                            Complete = false;
                        }
                    });
                    break;
                case 2:
                    printList(todoList);
                    break;
                case 3:
                    System.out.println("Etnter the number of deleting thing: ");
                    int numberItem = in.nextInt();
                    todoList.remove(numberItem);
                    printList(todoList);
                    break;
                case 4:
                    break;
                default:
                    System.out.println("It's not correct number");
            }
        }
        FileOutputStream fileOutStream = new FileOutputStream(file);
        ObjectOutputStream objectOutStream = new ObjectOutputStream(fileOutStream);
        objectOutStream.writeObject(todoList);
        objectOutStream.close();
    }
    public static void printList(List<ToDoListItem> todoList) {
        todoList.forEach((todoItem) -> {
            System.out.println(todoItem.Text);
        });
    }
}

class ToDoListItem implements Serializable {

    public String Text;
    public boolean Complete;
}
