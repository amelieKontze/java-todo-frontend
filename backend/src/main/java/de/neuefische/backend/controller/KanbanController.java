package de.neuefische.backend.controller;

import de.neuefische.backend.service.KanbanService;
import lombok.RequiredArgsConstructor;
import de.neuefische.backend.model.ToDo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("")
@RequiredArgsConstructor
public class KanbanController {

    private final KanbanService kanbanService;


    @GetMapping("/api/todo")
    public List<ToDo> getAllToDos(){
        return kanbanService.getAllToDos();
    }

    @PostMapping("/api/todo")
    public ToDo addToDo(@RequestBody ToDo task){
        return kanbanService.addToDo(task);
    }

    @GetMapping("/board/todo")
    public List<ToDo> getAllToDosBoard(){
        return kanbanService.getAllToDos();
    }

    @GetMapping("/api/todo/{id}")
    public ToDo showDetails(@PathVariable String id){
        return kanbanService.getActualTask(id);
    }

    @PutMapping("/api/todo/{id}")
    public List<ToDo> editToDo(@PathVariable String id, @RequestBody ToDo todo){
        return kanbanService.editToDo(id, todo);
    }

    @DeleteMapping("/api/todo/{id}")
    public List<ToDo> deleteToDo(@PathVariable String id){
        return kanbanService.deleteTodo(id);
    }


}
