/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todolist;

import java.io.*;
import java.util.Arrays;

/**
 *
 * @author Маша
 */
public class ToDolIstTest {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        //String[] abc = System.setIn(new BufferedInputStream(new FileInputStream("input.txt")));
        //String[] abc = System.setIn(new FileInputStream(file));
        File file = new File("list.txt");
        String[] abc = {"0","1","2","3","4","5","6"};
        System.out.println(Arrays.toString(abc));
        //ToDoList.main(abc);
        ToDoList.main(System.setIn(System.in));
        //ToDoList.main(System.setIn(new FileInputStream(file)));
    }
}
