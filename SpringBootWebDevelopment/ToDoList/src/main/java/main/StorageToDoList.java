package main;

import response.ToDoList;

import java.util.*;

public class StorageToDoList {
    private static int id = 0;
    private static ArrayList<ToDoList> list = new ArrayList<>();

    private static HashMap<Integer, ToDoList> map = new HashMap<>();

    public static List<ToDoList> getAllToDoLists() {
        return new ArrayList<>(map.values());
    }

    public static int addToDoList(ToDoList toDoList) {
        int id = StorageToDoList.id + 1;
        toDoList.setId(id);
        map.put(id, toDoList);
        StorageToDoList.id++;
        return id;
    }

    public static ToDoList get(int id){
        return map.get(id);
    }

    public static void deleteToDoList(int id){
        map.remove(id);
    }

    public static void deleteAllToDoLists(){
        map.clear();
        StorageToDoList.id = 0;
    }
}
