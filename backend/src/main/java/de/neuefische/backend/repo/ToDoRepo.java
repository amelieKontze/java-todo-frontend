package de.neuefische.backend.repo;

import de.neuefische.backend.model.ToDo;
import lombok.AllArgsConstructor;
import lombok.Data;

import org.springframework.stereotype.Repository;


import java.util.*;


@Repository
@Data
public class ToDoRepo {

   private final Map<String, ToDo> toDoList = new HashMap<>();


   public List<ToDo> allToDos(){
       return new ArrayList<>(toDoList.values());
    }

   public ToDo addToDo(ToDo task){
       toDoList.put(task.getId(), task);
       return task;
   }

    public ToDo getActualTask(String id) {
       return toDoList.get(id);
    }

    public List<ToDo> getActualTask(String id, ToDo todo) {
       toDoList.put(id, todo);
        return allToDos();
    }

/*    public ToDo editToDo(String id, ToDo todo) {
        if (todo.getId().equals(id)) {
            toDoList.put(id, todo);
            return todo;
        } else throw new IllegalArgumentException("Id of task and id in path do not match");
    }
*/

    public List<ToDo> deleteToDo(String id) {
       toDoList.remove(id);
       return allToDos();
    }
}//end class
