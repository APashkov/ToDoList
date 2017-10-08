package com.pashkov.artem.todolist;

import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.UUID;

@RestController
public class ToDoListController {
    private File file = new File("list.txt");
    private List<ToDoListItem> todoList = ToDoList.readList(file);

    public ToDoListController() throws IOException, ClassNotFoundException {
    }

    @ResponseBody
    @RequestMapping(value = "/todolist", method = {RequestMethod.GET})
    public List home() {
        return todoList;
    }
    @RequestMapping(value = "/todolist", method = {RequestMethod.POST})
    public String post(@RequestParam("line") String line){
        todoList.add(new ToDoListItem() {
            {
                text = line;
                complete = false;
                id = UUID.randomUUID();
            }
        });
        return "The method POST, " + todoList;
    }
    @RequestMapping(value = "/todolist/{id}", method = {RequestMethod.PUT})
    public String put(@PathVariable("id") UUID index, @RequestParam("text") String text) {
        for (ToDoListItem item : todoList) {
            if (item.id.equals(index)) {
                item.text = text;
            }
        }
        return "The method PUT, " + todoList;
    }
    @RequestMapping(value = "/todolist/{id}", method = {RequestMethod.DELETE})
    public List<ToDoListItem> delete(@PathVariable("id") UUID index) throws ConcurrentModificationException {
        todoList.removeIf(object -> object.id.equals(index));
        /*for (ToDoListItem item : todoList) {
            if (item.id.equals(index)) {
                    todoList.remove(item);
            }
        }*/
        return todoList;
    }

}
