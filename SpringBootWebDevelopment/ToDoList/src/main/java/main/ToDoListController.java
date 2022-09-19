package main;

import main.model.ToDoListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import main.model.ToDoList;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class ToDoListController {

    @Autowired
    private ToDoListRepository toDoListRepository;

    @GetMapping("/ToDoLists/")
    public List<ToDoList> getToDoLists() {
        Iterable<ToDoList> toDoListIterable = toDoListRepository.findAll();
        List<ToDoList> lists = new ArrayList<>();
        for (ToDoList toDoList : toDoListIterable) {
            lists.add(toDoList);
        }
        return lists;
    }

    @GetMapping("/ToDoLists/{id}")
    public ToDoList getToDoList(@PathVariable int id) {
        Optional<ToDoList> optional = toDoListRepository.findById(id);
        if (!optional.isPresent()){
            return null;
        }
        return optional.get();
    }

    @PostMapping("/ToDoLists/")
    public int addToDoList(ToDoList toDoList) {
        ToDoList tds = toDoListRepository.save(toDoList);
        return tds.getId();
    }

    @PutMapping("/ToDoLists/{id}")
    public ToDoList putToDoList(@PathVariable int id, String message, String authorName) {
        ToDoList toDoList = toDoListRepository.findById(id).get();
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
        Iterable<ToDoList> iterable = list;
        toDoListRepository.saveAll(iterable);
        return list;
    }

    @DeleteMapping("/ToDoLists/{id}")
    public void delete(@PathVariable int id) {
        toDoListRepository.deleteById(id);
    }

    @DeleteMapping("/ToDoLists/")
    public void deleteAllToDoLists() {
        toDoListRepository.deleteAll();
    }

}

