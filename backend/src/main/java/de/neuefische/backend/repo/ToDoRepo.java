package de.neuefische.backend.repo;

import de.neuefische.backend.model.ToDo;
import lombok.AllArgsConstructor;
import lombok.Data;

import org.springframework.stereotype.Repository;


import java.util.ArrayList;
import java.util.List;


@Repository
@Data
public class ToDoRepo {

   private final List<ToDo> toDoList = new ArrayList<>();

   public ToDo addToDo(String task){
       ToDo toDo = new ToDo(task);
       toDoList.add(toDo);
       return toDo;
   }
}
