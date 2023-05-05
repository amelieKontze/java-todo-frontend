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
    private final RandomUUIDService uuid;


    public List<ToDo> getAllToDos(){
        return toDoRepo.allToDos();
    }

    public ToDo addToDo(ToDo task) {
            task.setId(uuid.getRandomId());
            return toDoRepo.addToDo(task);
    }

    public ToDo getActualTask(String id){
        return toDoRepo.getActualTask(id);
    }

    public List<ToDo> editToDo(String id, ToDo todo) {
        return toDoRepo.getActualTask(id, todo);
    }

    public List<ToDo> deleteTodo(String id) {
        return toDoRepo.deleteToDo(id);
    }
}
