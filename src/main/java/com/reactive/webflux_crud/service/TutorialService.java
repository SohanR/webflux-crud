package com.reactive.webflux_crud.service;

import com.reactive.webflux_crud.model.Tutorial;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TutorialService {
    Mono<Void> save(Tutorial tutorial);

    Flux<Tutorial> saveMany(Flux<Tutorial> tutorial, int time) ;

    Flux<Tutorial> findALl();

    Mono<Tutorial> findById(Long id);

    Mono<Tutorial> updateTutorial(Long id, Tutorial tutorial);

    Mono<Void> deleteTutorial(Long id);

    Mono<Void> deleteAll();
}
