package com.reactive.webflux_crud.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "tutorials")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Tutorial {

    @Id
    private Long id;
    private String title;
    private String description;
    private  boolean published;
}
