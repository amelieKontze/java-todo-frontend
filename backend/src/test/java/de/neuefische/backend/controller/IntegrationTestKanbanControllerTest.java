package de.neuefische.backend.controller;



import com.fasterxml.jackson.databind.ObjectMapper;
import de.neuefische.backend.model.ToDo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class IntegrationTestKanbanControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Test
    @DirtiesContext
    void getAllToDos_ShouldReturnEmptyToDos() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/todo"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

    @Test
    @DirtiesContext
    void addToDo_ShouldAddToDoToList() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/todo")
                        .contentType("application/json")
                        .content("""
                    {
                        "description": "Hallo",
                        "status": "OPEN"              
                    }
                """))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                    {
                        "description": "Hallo",
                        "status": "OPEN"               
                    }
                """)).andExpect(jsonPath("$.id").isNotEmpty());
    }

    @Test
    @DirtiesContext
    void getAllToDosFromBoard_ShouldReturnEmptyToDosFromBoard() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/board/todo"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

    @Test
    @DirtiesContext
    void showDetails_ShouldShowToDoDetails() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/todo")
                .contentType("application/json")
                .content("""
                    {
                        "description": "Hallo", "status": "OPEN"}
                """))
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();

        ObjectMapper objectMapper = new ObjectMapper();
        ToDo toDo = objectMapper.readValue(content, ToDo.class);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/todo/"+ toDo.getId()))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        {
                        "description": "Hallo", "status": "OPEN"}
                """)).andExpect(jsonPath("$.id").value(toDo.getId()));
    }

    @Test
    @DirtiesContext
    void showDetailsWithInvalidId_ShouldThrowStatus404() throws Exception {

       mockMvc.perform(MockMvcRequestBuilders.get("/api/todo/"))
               .andExpect(status().is(404));
    }

    @Test
    @DirtiesContext
    void editToDo_ChangesDoTo() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/todo")
                        .contentType("application/json")
                        .content("""
                    {
                        "description": "Hallo", "status": "OPEN"}
                """))
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();

        ObjectMapper objectMapper = new ObjectMapper();
        ToDo toDo = objectMapper.readValue(content, ToDo.class);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/todo/"+ toDo.getId())
                        .contentType("application/json")
                        .content("""
                    {
                        "description": "Hallo", "status": "IN_PROGRESS"}
                """))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        [{
                        "description": "Hallo", "status": "IN_PROGRESS"}]
                 """));

    }

    @Test
    @DirtiesContext
    void deleteToDo() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/todo")
                        .contentType("application/json")
                        .content("""
                    {
                        "description": "Hallo", "status": "OPEN"}
                """))
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();

        ObjectMapper objectMapper = new ObjectMapper();
        ToDo toDo = objectMapper.readValue(content, ToDo.class);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/todo/"+ toDo.getId()))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        []
                """));
    }

}//end class