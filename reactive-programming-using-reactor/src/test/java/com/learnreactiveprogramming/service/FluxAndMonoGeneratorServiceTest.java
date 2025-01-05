package com.learnreactiveprogramming.service;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;



class FluxAndMonoGeneratorServiceTest {
    FluxAndMonoGeneratorService fluxAndMonoGeneratorService = new FluxAndMonoGeneratorService();

    @Test
    void namesFlux(){
        var namesFlux = fluxAndMonoGeneratorService.namesFlux();

        StepVerifier.create(namesFlux)
                .expectNext("Adam")
                .expectNext("Anna")
                .expectNext("Jack")
                .expectNext("Jenny")
                .verifyComplete();
    }


    @Test
    void namesFlux_flatMap() {
        var namesFlux_flatMap = fluxAndMonoGeneratorService.namesFlux_flatMap();

        StepVerifier.create(namesFlux_flatMap)
                .expectNext("A")
                .expectNext("L")
                .expectNext("E")
                .expectNext("X")
                .expectNext("C")
                .expectNext("H")
                .expectNext("L")
                .expectNext("O")
                .expectNext("E")
                .verifyComplete();
    }

    @Test
    void namesFlux_flatMap_withDelay() {
        var namesFlux_flatMap = fluxAndMonoGeneratorService.namesFlux_flatMap_withDelay();

        StepVerifier.create(namesFlux_flatMap)
                .expectNextCount(9)
                .verifyComplete();
    }

    @Test
    void namesFlux_concatMap_withDelay() {
        var namesFlux_concatMap = fluxAndMonoGeneratorService.namesFlux_concatMap_withDelay();
        StepVerifier.create(namesFlux_concatMap)
                .expectNext("A")
                .expectNext("L")
                .expectNext("E")
                .expectNext("X")
                .expectNext("C")
                .expectNext("H")
                .expectNext("L")
                .expectNext("O")
                .expectNext("E")
                .verifyComplete();
    }

    @Test
    void nameMono_flatMapMany() {
        var nameMono_flatMapMany = fluxAndMonoGeneratorService.nameMono_flatMapMany();
        StepVerifier.create(nameMono_flatMapMany)
                .expectNext("A")
                .expectNext("L")
                .expectNext("E")
                .expectNext("X")
                .verifyComplete();
    }
}