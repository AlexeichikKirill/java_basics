package main;

import org.springframework.web.bind.annotation.*;
import main.model.ToDoList;

import java.util.List;
@RestController
public class ToDoListController {

    @GetMapping("/ToDoLists/")
    public List<ToDoList> getToDoLists() {
        return StorageToDoList.getAllToDoLists();
    }

    @GetMapping("/ToDoLists/{id}")
    public ToDoList getToDoList(@PathVariable int id) {
        return StorageToDoList.get(id);
    }

    @PostMapping("/ToDoLists/")
    public int addToDoList(ToDoList toDoList) {
        return StorageToDoList.addToDoList(toDoList);
    }

    @PutMapping("/ToDoLists/{id}")
    public ToDoList putToDoList(@PathVariable int id, String message, String authorName) {
        ToDoList toDoList = StorageToDoList.get(id);
        if (message.length() > 0) {
            toDoList.setMessage(message);
        }
        if (authorName.length() > 0) {
            toDoList.setAuthorName(authorName);
        }
        return toDoList;
    }

    @PutMapping("/ToDoList/")
    public List<ToDoList> putToDoLists(@RequestBody List<ToDoList> list) {
        StorageToDoList.deleteAllToDoLists();
        list.forEach(StorageToDoList::addToDoList);
        return StorageToDoList.getAllToDoLists();
    }

    @DeleteMapping("/ToDoLists/{id}")
    public void delete(@PathVariable int id) {
        StorageToDoList.deleteToDoList(id);
    }

    @DeleteMapping("/ToDoLists/")
    public void deleteAllToDoLists() {
        StorageToDoList.deleteAllToDoLists();
    }

}

