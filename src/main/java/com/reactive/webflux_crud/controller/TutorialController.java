package com.reactive.webflux_crud.controller;

import com.reactive.webflux_crud.model.Tutorial;
import com.reactive.webflux_crud.service.TutorialService;
import com.reactive.webflux_crud.service.imp.TutorialServiceImp;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Tag(name = "TutorialService", description = "Service")
@RestController
@RequestMapping("/api")
public class TutorialController {

    @Autowired
    private TutorialService tutorialService;

    @GetMapping
    public Mono<String> helloWorld() {
        return Mono.just("Hello, Webflux CRUD!");
    }

    @PostMapping("/tutorial")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Void>saveTutorial(@RequestBody Tutorial tutorial){
        return  tutorialService.save(tutorial);
    }

    //save many
//    @PostMapping("/tutorial")
//    @ResponseStatus(HttpStatus.CREATED)
//    public Flux<Tutorial> saveMany(@RequestBody Flux<Tutorial> tutorials, @RequestParam int time){
//     return tutorialService.saveMany(tutorials, time);
//    }

    @GetMapping("/tutorials")
    @ResponseStatus(HttpStatus.OK)
    public Flux<Tutorial> getAllTutorials(){
        return tutorialService.findALl();
    }

    // get by id
    @GetMapping("/tutorial/{id}")
    @ResponseStatus(HttpStatus.OK)
    public  Mono<Tutorial> getTutorialById(@PathVariable Long id){
        return  tutorialService.findById(id);
    }

    // update tutorial by id
    @PostMapping("tutorial/{id}")
    @ResponseStatus(HttpStatus.OK)
    public  Mono<Tutorial> updateTutorial(@PathVariable Long id, @RequestBody Tutorial tutorial){
        return tutorialService.updateTutorial(id, tutorial);
    }

    // delete tutorial by id
    @DeleteMapping("tutorial/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public  Mono<Void> deleteTutorialById(@PathVariable Long id){
        return tutorialService.deleteTutorial(id);
    }

    // delete all tutorials
    @DeleteMapping("tutorials")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public  Mono<Void> deleteAllTutorials(){
        return tutorialService.deleteAll();
    }







}
