package main;

import main.model.ToDoList;
import main.model.ToDoListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class DefaultController {

    @Autowired
    private ToDoListRepository toDoListRepository;

    @RequestMapping("/")
    public String index(Model model) {
        Iterable<ToDoList> iterable = toDoListRepository.findAll();
        ArrayList<ToDoList> list = new ArrayList<>();
        iterable.forEach(list::add);
        model.addAttribute("tDLs", list);
        return "index";
    }
}
