package de.neuefische.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

import java.util.ArrayList;
import java.util.UUID;

@Data
@With
@AllArgsConstructor
@NoArgsConstructor
public class ToDo {

    private String description;
    private String status;
    private String id;

}//end class
