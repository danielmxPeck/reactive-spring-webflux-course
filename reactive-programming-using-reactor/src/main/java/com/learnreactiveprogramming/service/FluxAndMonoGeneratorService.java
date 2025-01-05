package com.learnreactiveprogramming.service;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Slf4j
public class FluxAndMonoGeneratorService {

    public Flux<String> namesFlux(){
         return Flux.fromIterable(List.of("Sam", "Tim", "Jack", "Jenny"));
    }

    public Mono<String> nameMono(){
        return Mono.just("Adam").map(String::toUpperCase);
    }

    public Mono <List<String>> nameMono_flatMap(){
        return Mono.just("alex")
                .map(String::toUpperCase)
                .flatMap(this::splitStringMono);
    }

    private Mono<List<String>> splitStringMono(String s) {
        var charArray = s.split("");
        System.out.println("charArray: " + Arrays.toString(charArray));
        var charList = List.of(charArray);
        System.out.println("charList: " + charList);
        return Mono.just(charList);
    }


    public Flux<String> namesFluxMap(){
        return namesFlux().map(String::toUpperCase)
             //filter string whose length > 3
             .filter(name -> name.length() > 3)
             //concatenate the length of the string
             .map(s-> s.length() + "-" + s );
    }


    public  Flux<String> namesFlux_flatMap() {
        return Flux.fromIterable(List.of("alex", "chloe")).map(String::toUpperCase)
                .filter(name -> name.length() > 3)
                .flatMap(this::splitString);
    }

    public  Flux<String> namesFlux_flatMap_defaultIfEmpty() {
        return Flux.fromIterable(List.of("alex", "chloe")).map(String::toUpperCase)
                .filter(name -> name.length() < 3)
                .defaultIfEmpty("default")
                .flatMap(this::splitString);
    }


    public  Flux<String> namesFlux_flatMap_withDelay() {
        //return individual character of ALEX, CHLOE
        return Flux.fromIterable(List.of("alex", "chloe")).map(String::toUpperCase)
                .filter(name -> name.length() > 3)
                .flatMap(this::splitString_withDelay);


    }
        public Flux<String> splitString_withDelay(String name) {
        var charArray = name.split("");
        return Flux.fromArray(charArray)
                .delayElements(Duration.ofMillis(new Random().nextInt(1000)));
    }

    public  Flux<String> namesFlux_concatMap_withDelay() {
        //return individual character of ALEX, CHLOE
        return Flux.fromIterable(List.of("alex", "chloe")).map(String::toUpperCase)
                .filter(name -> name.length() > 3)
                .concatMap(this::splitString_withDelay);
    }

    public Flux<String> nameMono_flatMapMany(){
        return Mono.just("alex")
                .filter(name -> name.length() > 3)
                .map(String::toUpperCase)
                .flatMapMany(this::splitString).log();
    }


        public static void main(String[] args){
        FluxAndMonoGeneratorService fluxAndMonoGeneratorService = new FluxAndMonoGeneratorService();

        System.out.println("nameFluxMap");
        fluxAndMonoGeneratorService.namesFluxMap().subscribe(System.out::println);


        System.out.println("nameFlux");
        fluxAndMonoGeneratorService.namesFlux()
            .subscribe(
                name ->
                {

                    System.out.println("Name is: " + name);
                }
            );

        System.out.println("nameMono");
        fluxAndMonoGeneratorService.nameMono().subscribe(
            name ->
            {

                System.out.println("Mono name is: " + name);
            }
        );

            System.out.println("nameMono_flatMap_withDelay");
            fluxAndMonoGeneratorService.namesFlux_flatMap_withDelay().subscribe(System.out::println);

            System.out.println("nameMono_concatMap_withDelay");
            fluxAndMonoGeneratorService.namesFlux_concatMap_withDelay().subscribe(System.out::println);

            System.out.println("nameMono");
            fluxAndMonoGeneratorService.nameMono().subscribe(System.out::println);

            System.out.println("nameMono_flatMap");
            fluxAndMonoGeneratorService.nameMono_flatMap().subscribe(System.out::println);

            System.out.println("nameMono_flatMapMany");
            fluxAndMonoGeneratorService.nameMono_flatMapMany().subscribe(System.out::println);

            System.out.println("namesFlux_flatMap_defaultIfEmpty");
            fluxAndMonoGeneratorService.namesFlux_flatMap_defaultIfEmpty().subscribe(System.out::println);
    }

    public Flux<String> splitString(String name){
        var charArray = name.split("");
        return Flux.fromArray(charArray);
    }

}
