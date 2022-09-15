package main;

import org.springframework.web.bind.annotation.*;
import response.ToDoList;

import java.util.List;

@RestController
public class ToDoListController {

    @GetMapping("/ToDoLists/")
    public List<ToDoList> list() {
        return StorageToDoList.getAllToDoLists();
    }

    @GetMapping("/ToDoLists/{id}")
    public ToDoList get(@PathVariable int id) {
        return StorageToDoList.get(id);
    }

    @PostMapping("/ToDoLists/")
    public int add(ToDoList toDoList) {
        return StorageToDoList.addToDoList(toDoList);
    }

    @PutMapping("ToDoLists/{id}")
    public ToDoList put(@PathVariable int id, String message, String authorName) {
        ToDoList toDoList = StorageToDoList.get(id);
        if (message.length() > 0) {
            toDoList.setMessage(message);
        }
        if (authorName.length() > 0) {
            toDoList.setAuthorName(authorName);
        }
        return toDoList;
    }

    @DeleteMapping("/ToDoLists/")
    public void deleteAllToDoLists() {
        StorageToDoList.deleteAllToDoLists();
    }

    @DeleteMapping("/ToDoLists/{id}")
    public void delete(@PathVariable int id) {
        StorageToDoList.deleteToDoList(id);
    }

}

