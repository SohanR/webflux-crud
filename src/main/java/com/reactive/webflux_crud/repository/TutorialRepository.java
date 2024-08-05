package com.reactive.webflux_crud.repository;

import com.reactive.webflux_crud.model.Tutorial;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

@ReadingConverter
public interface TutorialRepository  extends R2dbcRepository<Tutorial, Long> {
}
