package com.comarch.szkolenia.rest.controller;

import com.comarch.szkolenia.rest.model.Car;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class SimpleRestController {

    @RequestMapping(path = "/test1", method = RequestMethod.GET)
    public Car test1() {
        return new Car("Toyota", "Corolla", 300.0, 2024, "KR11", false);
    }

    @RequestMapping(path = "/test2" ,method = RequestMethod.POST)
    public void test2(@RequestBody Car car) {
        System.out.println(car);
    }

    @RequestMapping(path = "/test3/{arg1}/{arg2}", method = RequestMethod.POST)
    public Car test3(@PathVariable("arg1") String path1,
                      @PathVariable("arg2") String path2,
                      @RequestParam("parametr3") String param1,
                      @RequestParam("parametr4") String param2,
                      @RequestHeader("header1") String h1,
                      @RequestHeader("header2") String h2,
                      @RequestBody Car car) {
        System.out.println(path1);
        System.out.println(path2);
        System.out.println(param1);
        System.out.println(param2);
        System.out.println(h1);
        System.out.println(h2);
        System.out.println(car);

        return car;
    }

    @RequestMapping(path = "/test4", method = RequestMethod.GET)
    public ResponseEntity<Car> test4() {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header("nagowek1", "jakas wartosc")
                .header("naglowek2", "inna wartosc")
                .body(new Car("Toyota", "Corolla", 500.0, 2024, "KR11", false));
    }
}
