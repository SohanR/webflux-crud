package com.reactive.webflux_crud.service.imp;

import com.reactive.webflux_crud.model.Tutorial;
import com.reactive.webflux_crud.repository.TutorialRepository;
import com.reactive.webflux_crud.service.TutorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TutorialServiceImp implements TutorialService {

    @Autowired
    private TutorialRepository tutorialRepository;

//    @Override
//    public Mono<Void> save(Tutorial tutorial) {
//        List<Tutorial> tutorials = new ArrayList<>();
//
//        for (long i = 1; i < 1000; i++) {
//            Tutorial newTutorial = new Tutorial();
//            newTutorial.setTitle(tutorial.getTitle() + i);
//            newTutorial.setDescription(tutorial.getDescription() +i);
//            tutorials.add(newTutorial);
//        }
//       return tutorialRepository.saveAll(tutorials).then();
//    }


    @Override
    public Mono<Void> save(Tutorial tutorial) {
        return Flux.generate(() -> 1L, (state, sink) -> {
                    if (state < 1000) {
                        Tutorial newTutorial = new Tutorial();
                        newTutorial.setTitle(tutorial.getTitle() + state);
                        newTutorial.setDescription(tutorial.getDescription() + state);
                        sink.next(newTutorial);
                        return state + 1;
                    } else {
                        sink.complete();
                        return state;
                    }
                })
                .cast(Tutorial.class)
                .flatMap(tut -> tutorialRepository.save(tut)
                        .onErrorContinue((e, obj) -> {
                            // Log error here or handle it as needed
                            System.err.println("Error saving tutorial: " + obj + " due to " + e.getMessage());
                        }))
                .then();
    }


    @Override
    public Flux<Tutorial> saveMany(Flux<Tutorial> tutorial, int time) {
//        List<Flux<Tutorial>> tutorials = new ArrayList<>();
//        for (var i = 0; i < time; i++) {
//            tutorial.setId((long) i);
//            tutorials.add(tutorial);
//        }
//        return tutorialRepository.saveAll(tutorials);
        return null;
    }

    @Override
    public Flux<Tutorial> findALl() {
        return tutorialRepository.findAll();
    }

    @Override
    public Mono<Tutorial> findById(Long id) {
        return tutorialRepository.findById(id);
    }

    @Override
    public Mono<Tutorial> updateTutorial(Long id, Tutorial tutorial) {
        return tutorialRepository.findById(id).map(Optional::of)
                .defaultIfEmpty(Optional.empty())
                .flatMap(ot -> {
                    if(ot.isPresent()){
                        tutorial.setId(id);
                        return  tutorialRepository.save(tutorial);
                    }
                    return Mono.empty();
                });
    }

    @Override
    public Mono<Void> deleteTutorial(Long id) {
        return tutorialRepository.deleteById(id);
    }

    @Override
    public  Mono<Void> deleteAll() {
        return  tutorialRepository.deleteAll();
    }
}
