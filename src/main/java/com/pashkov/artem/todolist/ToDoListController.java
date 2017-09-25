package com.pashkov.artem.todolist;

import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
public class ToDoListController {
    private File file = new File("list.txt");
    private List<ToDoListItem> todoList = ToDoList.readList(file);

    public ToDoListController() throws IOException, ClassNotFoundException {
    }

    @ResponseBody
    @RequestMapping("/todolist")
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
        return "The method POST, param - " + line + " list " + todoList;
    }
}
