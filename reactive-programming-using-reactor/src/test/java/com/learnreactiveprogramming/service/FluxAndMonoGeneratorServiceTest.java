package com.learnreactiveprogramming.service;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

class FluxAndMonoGeneratorServiceTest {
  FluxAndMonoGeneratorService fluxAndMonoGeneratorService = new FluxAndMonoGeneratorService();

  @Test
  void namesFlux() {
    var namesFlux = fluxAndMonoGeneratorService.namesFlux();

    StepVerifier.create(namesFlux)
        .expectNext("Sam")
        .expectNext("Tim")
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

    StepVerifier.create(namesFlux_flatMap).expectNextCount(9).verifyComplete();
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

  @Test
  void namesFlux_flatMap_defaultIfEmpty() {
    var nameFLux_flatMap_defaultIfEmpty =
        fluxAndMonoGeneratorService.namesFlux_flatMap_defaultIfEmpty();
    StepVerifier.create(nameFLux_flatMap_defaultIfEmpty)
        .expectNext("d", "e", "f", "a", "u", "l", "t  ")
        .verifyComplete();
  }

  @Test
  void explore_concat() {
    var explore_concat = fluxAndMonoGeneratorService.explore_concat();
    StepVerifier.create(explore_concat)
        .expectNext("A")
        .expectNext("B")
        .expectNext("C")
        .expectNext("D")
        .expectNext("E")
        .expectNext("F")
        .verifyComplete();
  }

  @Test
  void explore_concatWith() {
    var explore_concatWith = fluxAndMonoGeneratorService.explore_concatWith();
    StepVerifier.create(explore_concatWith)
        .expectNext("A")
        .expectNext("B")
        .expectNext("C")
        .expectNext("D")
        .expectNext("E")
        .expectNext("F")
        .verifyComplete();
  }

  @Test
  void explore_concatWith_mono() {
    var explore_concatWith_mono = fluxAndMonoGeneratorService.explore_concatWith_mono();
    StepVerifier.create(explore_concatWith_mono).expectNext("A").expectNext("D").verifyComplete();
  }

  @Test
  void explore_merge() {
    var explore_merge = fluxAndMonoGeneratorService.explore_merge();
    StepVerifier.create(explore_merge)
        .expectNext("A")
        .expectNext("D")
        .expectNext("B")
        .expectNext("E")
        .expectNext("C")
        .expectNext("F")
        .verifyComplete();
  }

  @Test
  void explore_mergeWith() {
    var explore_mergeWith = fluxAndMonoGeneratorService.explore_mergeWith();
    StepVerifier.create(explore_mergeWith)
        .expectNext("A")
        .expectNext("B")
        .expectNext("C")
        .expectNext("D")
        .expectNext("E")
        .expectNext("F")
        .verifyComplete();
  }

  @Test
  void explore_mergeSequential() {
    var explore_mergeSequential = fluxAndMonoGeneratorService.explore_mergeSequential();
    StepVerifier.create(explore_mergeSequential)
        .expectNext("A")
        .expectNext("B")
        .expectNext("C")
        .expectNext("D")
        .expectNext("E")
        .expectNext("F")
        .verifyComplete();
  }

  @Test
  void explore_zip() {
    var explore_zip = fluxAndMonoGeneratorService.explore_zip();
    StepVerifier.create(explore_zip).expectNext("AD", "BE", "CF").verifyComplete();
  }

  @Test
  void explore_zip1() {
    var explore_zip1 = fluxAndMonoGeneratorService.explore_zip1();
    StepVerifier.create(explore_zip1).expectNext("AD14", "BE25", "CF36").verifyComplete();
  }

  @Test
  void explore_zipWith() {
    var explore_zipWith = fluxAndMonoGeneratorService.explore_zipWith();
    StepVerifier.create(explore_zipWith).expectNext("AD", "BE", "CF").verifyComplete();
  }

  @Test
  void explore_zipWith_mono() {
    var explore_zipWith_mono = fluxAndMonoGeneratorService.explore_zipWith_mono();
    StepVerifier.create(explore_zipWith_mono).expectNext("AD").verifyComplete();
  }
}
