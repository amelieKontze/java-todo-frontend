package de.neuefische.backend.controller;



import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

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
    void getAllToDosBoard_ShouldReturnAllDoDos() throws Exception {
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
    void showDetails() {
    }

    @Test
    void editToDo() {
    }

    @Test
    void deleteToDo() {
    }
}//end class