package com.learnreactiveprogramming.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public class FluxAndMonoGeneratorService {

    Flux<String> namesFlux(){
         return Flux.fromIterable(List.of("Adam", "Anna", "Jack", "Jenny"));
    }

    Mono<String> nameMono(){
        return Mono.just("Adam");
    }

    public static void main(String[] args){
        FluxAndMonoGeneratorService fluxAndMonoGeneratorService = new FluxAndMonoGeneratorService();

        fluxAndMonoGeneratorService.namesFlux()
            .subscribe(
                name ->
                {
                    System.out.println("Name is: " + name);
                }
            );

        fluxAndMonoGeneratorService.nameMono().subscribe(
            name ->
            {
                System.out.println("Mono name is: " + name);
            }
        );
    }
}
