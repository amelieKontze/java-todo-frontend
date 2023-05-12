package de.neuefische.backend.service;

import de.neuefische.backend.model.ToDo;
import de.neuefische.backend.repo.ToDoRepo;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.concurrent.ThreadPoolExecutor;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class KanbanServiceTest {
    private final RandomUUIDService uuid = mock(RandomUUIDService.class);
    private final ToDoRepo toDoRepo = mock(ToDoRepo.class);
    private final KanbanService kanbanService = new KanbanService(toDoRepo, uuid);

//    @Test
//    void getAllToDos() {
//        //given
//        Map<String, String> toDos = new HashMap();
//
//        toDos.put("1", "Clean Kitchen");
//        toDos.put("2", "Vacuum");
//
//        //when
//        List<ToDo> actual = kanbanService.getAllToDos();
//
//        //then
//        assertEquals(toDos, actual);
//    }


/*  //Wie geht es mit UUID?
    @Test
    void addToDo_ShouldAddToDoToList() {
        //given
        ToDo addToDo = new ToDo("1", "OPEN", "1");
        ArrayList<ToDo> expected = new ArrayList<>(List.of(
                new ToDo("1", "OPEN", "1")
        ));
        //when
        kanbanService.addToDo(addToDo);
        List<ToDo> actual = kanbanService.getAllToDos();
        //then
        assertEquals(expected, actual);
    }
*/

    @Test
    void getActualTask_ShouldGetToDoById() {
        //given
        ToDo addToDo = new ToDo("1", "OPEN", "1");
        when(toDoRepo.getActualTask("1")).thenReturn(addToDo);
        //when
        ToDo actual = kanbanService.getActualTask("1");
        //then
        verify(toDoRepo).getActualTask("1");
        assertEquals(addToDo, actual);
    }

/*    @Test
    void editToDo() {
        //given

        //when

        //then

    }*/

/*    @Test
    void deleteTodo() {
        //given
        ToDo deleteToDo = new ToDo("1", "OPEN", "1");
        //when
        when(toDoRepo.deleteToDo("1")).thenReturn(deleteToDo);
        ToDo actual = kanbanService.deleteTodo("1");
        //then

    }*/


}//end class