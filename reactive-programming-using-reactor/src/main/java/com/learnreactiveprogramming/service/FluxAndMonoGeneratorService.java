package com.learnreactiveprogramming.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class FluxAndMonoGeneratorService {

    public Flux<String> namesFlux(){
         return Flux.fromIterable(List.of("Sam", "Tim", "Jack", "Jenny"));
    }

    public Mono<String> nameMono(){
        return Mono.just("Adam");
    }

    public Flux<String> namesFluxMap(){
        //**filter string whose length > 3 **
        //convert to uppercase
        return namesFlux().map(String::toUpperCase)
             //filter string whose length > 3
             .filter(name -> name.length() > 3)
             //concatenate the length of the string
             .map(s-> s.length() + "-" + s );
    }


    public  Flux<String> namesFlux_flatMap() {
        //return individual character of ALEX, CHLOE
        return Flux.fromIterable(List.of("alex", "chloe")).map(String::toUpperCase)
                .filter(name -> name.length() > 3)
                .flatMap(this::splitString).log();
    }

    public Flux<String> splitString(String name){
        var charArray = name.split("");
        return Flux.fromArray(charArray);
    }

    public  Flux<String> namesFlux_flatMap_withDelay() {
        //return individual character of ALEX, CHLOE
        return Flux.fromIterable(List.of("alex", "chloe")).map(String::toUpperCase)
                .filter(name -> name.length() > 3)
                .flatMap(this::splitString_withDelay).log();


    }
        public Flux<String> splitString_withDelay(String name) {
        var charArray = name.split("");
        return Flux.fromArray(charArray)
                .delayElements(Duration.ofMillis(new Random().nextInt(1000)));
    }


        public static void main(String[] args){
        FluxAndMonoGeneratorService fluxAndMonoGeneratorService = new FluxAndMonoGeneratorService();

//        fluxAndMonoGeneratorService.namesFluxMap().subscribe(System.out::println);

//        fluxAndMonoGeneratorService.namesFlux()
//            .subscribe(
//                name ->
//                {
//                    System.out.println("Name is: " + name);
//                }
//            );
//
//        fluxAndMonoGeneratorService.nameMono().subscribe(
//            name ->
//            {
//                System.out.println("Mono name is: " + name);
//            }
//        );
            fluxAndMonoGeneratorService.namesFlux_flatMap_withDelay().subscribe(System.out::println);
        }
    }
