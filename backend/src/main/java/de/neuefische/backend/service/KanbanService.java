package de.neuefische.backend.service;

import de.neuefische.backend.model.ToDo;
import de.neuefische.backend.repo.ToDoRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class KanbanService {

    private final ToDoRepo toDoRepo;

    public List<ToDo> getAllToDos(){
        return toDoRepo.getToDoList();
    }

    public ToDo addToDo(String task) {

        return toDoRepo.addToDo(task);
    }
}
