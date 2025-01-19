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
                .flatMap(this::splitString).log();
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

    public Flux<String> explore_concat(){
        var abcFlux = Flux.just("A" , "B", "C");
        var defFlux = Flux.just("D", "E" , "F");

        return Flux.concat(abcFlux, defFlux).log();
    }

    public Flux<String> explore_concatWith(){
        var abcFlux = Flux.just("A" , "B", "C");
        var defFlux = Flux.just("D", "E" , "F");

        return abcFlux.concatWith(defFlux).log();
    }

    public Flux<String> explore_concatWith_mono(){
        var aMono = Mono.just("A");
        var dMono = Mono.just("D");

        return aMono.concatWith(dMono).log();
    }

    public Flux<String> explore_merge(){
        var abcFlux = Flux.just("A" , "B", "C").delayElements(Duration.ofMillis(100));

        var defFlux = Flux.just("D", "E" , "F").delayElements(Duration.ofMillis(125));

        return Flux.merge(abcFlux, defFlux).log();
    }

    public Flux<String> explore_mergeWith(){
        var abcFlux = Flux.just("A" , "B", "C");

        var defFlux = Flux.just("D", "E" , "F");

        return abcFlux.mergeWith(defFlux).log();
    }

    public Flux<String> explore_mergeSequential() {
        var abcFlux = Flux.just("A", "B", "C").delayElements(Duration.ofMillis(100));

        var defFlux = Flux.just("D", "E", "F").delayElements(Duration.ofMillis(125));

        return Flux.mergeSequential(abcFlux, defFlux).log();
    }

    public Flux<String> explore_zip() {
        var abcFlux = Flux.just("A", "B", "C");
        var defFlux = Flux.just("D", "E", "F");

        return Flux.zip(abcFlux, defFlux, (t1, t2) -> t1 + t2).log();
    }

    public Flux<String> explore_zip1() {
        var abcFlux = Flux.just("A", "B", "C");
        var defFlux = Flux.just("D", "E", "F");
        var _123Flux = Flux.just("1", "2", "3");
        var _456Flux = Flux.just("4", "5", "6");

        return Flux.zip(abcFlux, defFlux, _123Flux, _456Flux)
                .map(t4 -> t4.getT1() + t4.getT2() + t4.getT3() + t4.getT4())
                .log(); //AD14, BE25, CF36
    }

    public Flux<String> explore_zipWith() {
        var abcFlux = Flux.just("A", "B", "C");
        var defFlux = Flux.just("D", "E", "F");

        return abcFlux.zipWith(defFlux, (T1,T2) -> T1 + T2)
        .log(); //AD, BE, CF;
    }

    public Mono<String> explore_zipWith_mono() {
        var aMono = Mono.just("A");
        var dMono = Mono.just("D");

        return aMono.zipWith(dMono).map(t -> t.getT1() + t.getT2()).log();
    }

        public static void main(String[] args){
        FluxAndMonoGeneratorService fluxAndMonoGeneratorService = new FluxAndMonoGeneratorService();

//        System.out.println("nameFluxMap");
//        fluxAndMonoGeneratorService.namesFluxMap().subscribe(System.out::println);


//        System.out.println("nameFlux");
//        fluxAndMonoGeneratorService.namesFlux()
//            .subscribe(
//                name ->
//                {
//                    System.out.println("Name is: " + name);
//                }
//            );

//        System.out.println("nameMono");
//        fluxAndMonoGeneratorService.nameMono().subscribe(
//            name ->
//            {
//                System.out.println("Mono name is: " + name);
//            }
//        );

//            System.out.println("nameMono_flatMap_withDelay");
//            fluxAndMonoGeneratorService.namesFlux_flatMap_withDelay().subscribe(System.out::println);

//            System.out.println("nameMono_concatMap_withDelay");
//            fluxAndMonoGeneratorService.namesFlux_concatMap_withDelay().subscribe(System.out::println);

//            System.out.println("nameMono");
//            fluxAndMonoGeneratorService.nameMono().subscribe(System.out::println);

//            System.out.println("nameMono_flatMap");
//            fluxAndMonoGeneratorService.nameMono_flatMap().subscribe(System.out::println);

//            System.out.println("nameMono_flatMapMany");
//            fluxAndMonoGeneratorService.nameMono_flatMapMany().subscribe(System.out::println);

//            System.out.println("namesFlux_flatMap_defaultIfEmpty");
//            fluxAndMonoGeneratorService.namesFlux_flatMap_defaultIfEmpty().subscribe(System.out::println);

//            System.out.println("explore_concat");
//            fluxAndMonoGeneratorService.explore_concat().subscribe(System.out::println);
//
//            System.out.println("explore_concatWith");
//            fluxAndMonoGeneratorService.explore_concatWith().subscribe(System.out::println);

//            System.out.println("explore_concatWith_mono");
//            fluxAndMonoGeneratorService.explore_concatWith_mono().subscribe(System.out::println);
//
//            System.out.println("explore_merge");
//            fluxAndMonoGeneratorService.explore_merge().subscribe(System.out::println);

//            System.out.println("explore_mergeWith");
//            fluxAndMonoGeneratorService.explore_mergeWith().subscribe(System.out::println);

//            System.out.println("explore_mergeSequential");
//            fluxAndMonoGeneratorService.explore_mergeSequential().subscribe(System.out::println);

//            System.out.println("explore_zip");
//            fluxAndMonoGeneratorService.explore_zip().subscribe(System.out::println);

//            System.out.println("explore_zip1");
//            fluxAndMonoGeneratorService.explore_zip1().subscribe(System.out::println);

            System.out.println("explore_zipWith");
            fluxAndMonoGeneratorService.explore_zipWith().subscribe(System.out::println);

//            System.out.println("explore_zipWith_mono");
//            fluxAndMonoGeneratorService.explore_zipWith_mono().subscribe(System.out::println);
    }

    public Flux<String> splitString(String name){
        var charArray = name.split("");
        return Flux.fromArray(charArray);

    }

}
