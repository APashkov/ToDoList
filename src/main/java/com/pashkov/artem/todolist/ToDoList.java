/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pashkov.artem.todolist;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.*;
import java.io.IOException;

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
        List<ToDoListItem> todoList = readList(file);
        //List<ToDoListItem> todoList = new ArrayList<>();
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
                            text = line;
                            complete = false;
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
        writeList(file, todoList);
    }

    private static void writeList(File file, List<ToDoListItem> todoList) throws IOException, FileNotFoundException {
        FileOutputStream fileOutStream = new FileOutputStream(file);
        try (ObjectOutputStream objectOutStream = new ObjectOutputStream(fileOutStream)) {
            objectOutStream.writeObject(todoList);
        }
    }

    public static List<ToDoListItem> readList(File file) throws FileNotFoundException, IOException, ClassNotFoundException {
        if (!file.exists()) {
            return new ArrayList<ToDoListItem>();
        }
        FileInputStream fileInStream = new FileInputStream(file);
        try (ObjectInputStream objectInStream = new ObjectInputStream(fileInStream)) {
            return (ArrayList<ToDoListItem>) objectInStream.readObject();
        }
    }

    private static void printList(List<ToDoListItem> todoList) {
        int index = 0;
        while (index < todoList.size()) {
            System.out.println(index + " - " + todoList.get(index));
            index++;
        }
    }
}

class ToDoListItem implements Serializable {

    public String text;
    public boolean complete;
    
    @Override
    public String toString() {
        return text;
    }
}
